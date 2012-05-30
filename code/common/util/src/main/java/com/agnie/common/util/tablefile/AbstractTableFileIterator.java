package com.agnie.common.util.tablefile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agnie.common.util.validator.Validator;
import com.agnie.common.util.validator.ValidatorFactory;

/**
 * AbstractTableFileIterator will be abstract or base for reading csv, or excel sheet or any other file in table format
 * as a Generic Bean/POJO iterator. Which takes InputStream or Reader and iterate over it line by line. When you iterate
 * it, it will return entity bean for every record with values populated as per following rule.
 * <p>
 * Entity you want to get populate should attach TableHeader annotation to the property setters. TableHeader takes one
 * parameter "name".
 * <ul>
 * <li>If you don't specify name parameter, it will try to search for header with the name exactly matching to the bean
 * property with first character in capital case. If you have different header name in your table file you can map it to
 * the property with this name parameter.
 * </ul>
 * 
 * Additionally one can attach constraints on the column like @NotNull. While iterating over the records if record is
 * found to be voilating constraints of any column. Then it will through the ConstraintViolationException
 * 
 */
public abstract class AbstractTableFileIterator<T> implements Iterator<T> {

	protected static final Log		logger			= LogFactory.getLog(AbstractTableFileIterator.class);
	private List<String>			headerList		= new ArrayList<String>();
	private boolean					tokenProduced	= false;
	private Class<T>				cls;
	private Map<Integer, MetaInfo>	methodMap		= new HashMap<Integer, MetaInfo>();
	protected List<String>			nextTokens;
	protected long					rowcount		= 0;

	/**
	 * Sub class of this class must call init() method a the end of the constructor to initalise the Iterator.
	 * 
	 * @param cls
	 * @throws IOException
	 */
	public AbstractTableFileIterator(Class<T> cls) throws IOException {
		this.cls = cls;
	}

	/**
	 * This will initialize the TableFileIterator with header values and meta data of the bean
	 * 
	 * @throws IOException
	 */
	protected void init() throws IOException {
		readTokens();
		headerList = nextTokens;
		tokenProduced = false;
		processMeta();
	}

	/**
	 * Check if iterator has next record to read.
	 */
	public boolean hasNext() {
		if (!tokenProduced) {
			try {
				readTokens();
				tokenProduced = true;
			} catch (IOException e) {
				logger.info("Error while reading the tokens from CSV", e);
			}
		}
		return (nextTokens != null && nextTokens.size() > 0);
	}

	/**
	 * Retrieve the next record as bean
	 */
	public T next() {
		if (!tokenProduced) {
			try {
				readTokens();
				tokenProduced = true;
			} catch (IOException e) {
				logger.info("Error while reading the tokens from Table File", e);
				return null;
			}
		}
		T bean = null;
		try {
			bean = getBean();

		} catch (IllegalArgumentException e) {
			// TODO: Need to throw run time exception in case of any error.
			logger.info("Table file bean setting error", e);
		} catch (InstantiationException e) {
			logger.info("Table file bean setting error", e);
		} catch (IllegalAccessException e) {
			logger.info("Table file bean setting error", e);
		} catch (InvocationTargetException e) {
			logger.info("Table file bean setting error", e);
		}
		// Here while returning the bean token is consumed so set tokenProduced state to false so next token can be
		// produced
		tokenProduced = false;
		return bean;
	}

	/**
	 * getBean will read list of nextTokens, instantiate the given bean, validate it against every property, populate
	 * the bean as per the rule and finally return the same. If it violates any of the constraints of property it will
	 * throw RuntimeException ConstraintViolationException with csv header name, row count and list of violating
	 * validators
	 * 
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private T getBean() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		T bean = cls.newInstance();
		for (int index = 0; index < headerList.size(); index++) {
			MetaInfo meta = methodMap.get(index);
			if (meta != null) {
				String token = nextTokens.get(index);
				List<Validator> validators = meta.getValidators();
				List<Validator> failed = validate(validators, token);
				if (failed == null) {
					try {
						populateBeanWithToken(meta.getMethod(), token, bean);
					} catch (NumberFormatException e) {
						logger.error("error while setting value for column '" + meta.getMethod().getName() + "' expected number, actual value =\"" + token + "\"", e);
						throw new InvalidColumnValueException(headerList.get(index), rowcount, token, "NUMBER_EXPECTED");
					}
				} else {
					throw new ConstraintViolationException(headerList.get(index), rowcount, failed);
				}
			}
		}
		return bean;
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

	/**
	 * validate will validate the given row token with the validators applied on the property of the bean. And if it
	 * finds that it is violating any validators then it will return the list of violating validators. If it returns
	 * null that means validation is successful.
	 * 
	 * @param contraints
	 * @param token
	 * @return
	 */
	private List<Validator> validate(List<Validator> validators, String token) {
		List<Validator> failedValidators = new ArrayList<Validator>();
		if (validators != null && validators.size() > 0) {
			for (Validator validator : validators) {
				if (!validator.validate(token)) {
					failedValidators.add(validator);
				}
			}
			return (failedValidators.size() > 0 ? failedValidators : null);
		}
		return null;
	}

	/**
	 * This will populate individual properties of the bean with the given token by converting it into required types.
	 * 
	 * @param method
	 * @param token
	 * @param bean
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("rawtypes")
	private void populateBeanWithToken(Method method, String token, T bean) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (token != null && !("".equals(token))) {
			Class paramCls = method.getParameterTypes()[0];
			Object[] param = { getParameter(paramCls, token) };
			method.invoke(bean, param);
		}
	}

	/**
	 * This will do the job of converting individual token to the required type as per the bean. Currently this method
	 * takes care of converting only primitive and String type. If one need to add some more type it can be done by
	 * overriding this method.
	 * 
	 * @param paramCls
	 * @param token
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected Object getParameter(Class paramCls, String token) {
		if (token != null && !("".equals(token))) {
			if (int.class.equals(paramCls) || Integer.class.equals(paramCls)) {
				return Integer.parseInt(token);
			} else if (float.class.equals(paramCls) || Float.class.equals(paramCls)) {
				return Float.parseFloat(token);
			} else if (long.class.equals(paramCls) || Long.class.equals(paramCls)) {
				return Long.parseLong(token);
			} else if (double.class.equals(paramCls) || Double.class.equals(paramCls)) {
				return Double.parseDouble(token);
			} else if (boolean.class.equals(paramCls) || Boolean.class.equals(paramCls)) {
				return Boolean.parseBoolean(token);
			} else {
				return token;
			}
		} else {
			logger.error("There is no convertor avaialble for class '" + paramCls.getCanonicalName()
					+ "' You need to extend your TableFileIteraotr and extend getParameter method to support new data type");
			return null;
		}
	}

	/**
	 * This method will read the header list and identify the mapping between bean property and TableHeader.
	 * 
	 */
	private void processMeta() {
		for (Method meth : cls.getMethods()) {
			String methodName = meth.getName();
			if (methodName.startsWith("set")) {
				String hedToken = "";
				TableHeader hed = meth.getAnnotation(TableHeader.class);
				if (hed != null) {
					if (!("".equals(hed.name()))) {
						hedToken = hed.name();
					} else {
						hedToken = methodName.substring(3);
					}
					ValidatorFactory valFactory = new ValidatorFactory();
					List<Validator> validators = valFactory.getMethodValidator(meth);
					boolean found = false;
					for (int index = 0; index < headerList.size(); index++) {
						String csvHead = headerList.get(index);
						if (hedToken.equals(csvHead)) {
							methodMap.put(index, new MetaInfo(meth, validators));
							found = true;
						}
					}
					if (!found) {
						/**
						 * TODO: throw and exception for not finding the matching setter for the given bean. or just log
						 * the messsage
						 */
						logger.debug("CSV matching cloumn header not found for property \"" + hedToken + "\"");
					}
				}
			}
		}
	}

	/**
	 * Abstract method to read one line of file and separate every column value as a token and populate it in to
	 * nextTokens list. Which will be used by subsequent methods
	 * 
	 * @throws IOException
	 */
	protected abstract void readTokens() throws IOException;

}

/**
 * Supplementary class to AbstractTableFileIterator to keep method and validators information against Header.
 * 
 */
class MetaInfo {
	private Method			method;
	private List<Validator>	validators;

	/**
	 * @param method
	 * @param validators
	 */
	public MetaInfo(Method method, List<Validator> validators) {
		this.method = method;
		this.validators = validators;
	}

	/**
	 * @return the method
	 */
	public Method getMethod() {
		return method;
	}

	/**
	 * @return the validators
	 */
	public List<Validator> getValidators() {
		return validators;
	}

}
