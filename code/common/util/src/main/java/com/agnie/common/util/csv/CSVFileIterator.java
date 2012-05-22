package com.agnie.common.util.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import au.com.bytecode.opencsv.CSVReader;

/**
 * CSVFileIterator will be CSV to Bean Generic iterator. Which takes InputStream
 * or Reader and iterate over it line by line. When you iterate it, it will
 * return entity bean for every record with values populated as per following
 * rule.
 * <p>
 * Entity you want to get populate should attach CSVHeader annotation to the
 * property setters. CSVHeader takes two parameters "name" and list of
 * "constraints" ( list of CSVConstraint enum).
 * <ul>
 * <li>If you don't specify name parameter, it will try to search for the header
 * with name exactly matching to the bean property with first character in
 * capital case. If you have different header name in CSV you can map it to the
 * property with this name parameter.
 * <li>For constraints you can specify the listed constraints for that property
 * like CSVConstraint.NOTNULL (This will validate the CSV for empty values for
 * the given column/header).
 * </ul>
 * 
 */
public class CSVFileIterator<T> implements Iterator<T> {

	protected static final Log logger = LogFactory
			.getLog(CSVFileIterator.class);
	private CSVReader reader;
	private List<String> headerList = new ArrayList<String>();
	private List<String> nextTokens;
	private boolean tokenToRead = true;
	private long rowcount = 0;
	private Class<T> cls;
	private Map<Integer, MetaInfo> methodMap = new HashMap<Integer, MetaInfo>();

	public CSVFileIterator(InputStream stream, Class<T> cls) throws IOException {
		this(new InputStreamReader(stream), cls);
	}

	public CSVFileIterator(Reader reader, Class<T> cls) throws IOException {
		this.cls = cls;
		this.reader = new CSVReader(reader,
				au.com.bytecode.opencsv.CSVParser.DEFAULT_SEPARATOR,
				au.com.bytecode.opencsv.CSVParser.DEFAULT_QUOTE_CHARACTER, true);
		readTokens();
		headerList = nextTokens;
		processMeta();
	}

	public boolean hasNext() {
		try {
			readTokens();
		} catch (IOException e) {
			logger.info("Error while reading the tokens from CSV", e);
		}
		tokenToRead = false;
		return (nextTokens != null && nextTokens.size() > 0);
	}

	public T next() {
		if (tokenToRead) {
			try {
				readTokens();
			} catch (IOException e) {
				logger.info("Error while reading the tokens from CSV", e);
				return null;
			}
		}
		tokenToRead = true;
		T bean = null;
		try {
			bean = getBean();
		} catch (IllegalArgumentException e) {
			logger.info("CSV bean setting error", e);
		} catch (InstantiationException e) {
			logger.info("CSV bean setting error", e);
		} catch (IllegalAccessException e) {
			logger.info("CSV bean setting error", e);
		} catch (InvocationTargetException e) {
			logger.info("CSV bean setting error", e);
		}

		return bean;
	}

	/**
	 * getBean will read list of nextTokens, instantiate the given bean,
	 * validate it against every property, populate the bean as per the rule and
	 * finally return the same. If it violates any of the constraints of the
	 * property it will throw RuntimeException CSVConstraintViolationException
	 * with csv header name, row count and list of violating constraints
	 * 
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private T getBean() throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		T bean = cls.newInstance();
		for (int index = 0; index < headerList.size(); index++) {
			MetaInfo meta = methodMap.get(index);
			if (meta != null) {
				String token = nextTokens.get(index);
				CSVConstraint[] conts = meta.getConstraints();
				List<CSVConstraint> failed = validate(conts, token);
				if (failed == null) {
					try {
						populateBeanWithToken(meta.getMethod(), token, bean);
					} catch (NumberFormatException e) {
						logger.info("CSV bean setting error", e);
						throw new InvalidColumnValueException(
								headerList.get(index), rowcount, token,
								"expected number, actual value =\"" + token
										+ "\"");
					}
				} else {
					throw new CSVConstraintViolationException(
							headerList.get(index), rowcount, failed);
				}
			}
		}
		return bean;
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

	/**
	 * validate will validate the given row token with the constraints applied
	 * on the property of the bean. And if it finds that it is violating any
	 * constraints then it will return the list of violating constraints. If it
	 * returns null that means validation is successful.
	 * 
	 * @param contraints
	 * @param token
	 * @return
	 */
	private List<CSVConstraint> validate(CSVConstraint[] contraints,
			String token) {
		if (contraints != null && contraints.length > 0) {
			List<CSVConstraint> failingConstraints = new ArrayList<CSVConstraint>();
			for (CSVConstraint csvConstraint : contraints) {
				if (!validate(csvConstraint, token)) {
					failingConstraints.add(csvConstraint);
				}
			}
			return (failingConstraints.size() > 0 ? failingConstraints : null);
		}
		return null;
	}

	/**
	 * This will validate individual constraints. As of now I have added only
	 * NOTNULL constraint. If some new constraints is added tomorrow. Logic to
	 * check that constraint can be added here or one can extend this Iterator
	 * and overide this method and their own logic
	 * 
	 * @param constraint
	 * @param token
	 * @return
	 */
	protected boolean validate(CSVConstraint constraint, String token) {
		switch (constraint) {
		case NOTNULL:
			if (token == null || "".equals(token)) {
				return false;
			}
			break;

		}
		return true;
	}

	/**
	 * This will populate individual properties of the bean with the given token
	 * by converting it into required types.
	 * 
	 * @param method
	 * @param token
	 * @param bean
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("rawtypes")
	private void populateBeanWithToken(Method method, String token, T bean)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		if (token != null && !("".equals(token))) {
			Class paramCls = method.getParameterTypes()[0];
			Object[] param = { getParameter(paramCls, token) };
			method.invoke(bean, param);
		}
	}

	/**
	 * This will do the job of converting individual token to the required type
	 * as per the bean. Currently this method takes care of converting only
	 * primitive and String type. If one need to add some more type it can be
	 * done by overriding this method.
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
			} else if (float.class.equals(paramCls)
					|| Float.class.equals(paramCls)) {
				return Float.parseFloat(token);
			} else if (long.class.equals(paramCls)
					|| Long.class.equals(paramCls)) {
				return Long.parseLong(token);
			} else if (double.class.equals(paramCls)
					|| Double.class.equals(paramCls)) {
				return Double.parseDouble(token);
			} else if (boolean.class.equals(paramCls)
					|| Boolean.class.equals(paramCls)) {
				return Boolean.parseBoolean(token);
			} else {
				return token;
			}
		} else {
			return null;
		}
	}

	/**
	 * This method will read the header list and identify the mapping between
	 * bean property and CSV header.
	 * 
	 */
	private void processMeta() {
		for (Method meth : cls.getMethods()) {
			String methodName = meth.getName();
			if (methodName.startsWith("set")) {
				String hedToken = "";
				CSVHeader hed = meth.getAnnotation(CSVHeader.class);
				if (hed != null) {
					if (!("".equals(hed.name()))) {
						hedToken = hed.name();
					} else {
						hedToken = methodName.substring(3);
					}
					CSVConstraint[] constraints = hed.constraints();
					boolean found = false;
					for (int index = 0; index < headerList.size(); index++) {
						String csvHead = headerList.get(index);
						if (hedToken.equals(csvHead)) {
							methodMap.put(index,
									new MetaInfo(meth, constraints));
							found = true;
						}
					}
					if (!found) {
						/**
						 * TODO: throw and exception for not finding the
						 * matching setter for the given bean. or just log the
						 * messsage
						 */
						logger.debug("CSV matching cloumn header not found for property \""
								+ hedToken + "\"");
					}
				}
			}
		}
	}

	/**
	 * This method will read csv file line by line and convert it into tokens
	 * separated by ","
	 * 
	 * @throws IOException
	 */
	private void readTokens() throws IOException {
		nextTokens = new ArrayList<String>();
		if (reader != null) {
			String[] tokens = reader.readNext();
			if (tokens != null) {
				for (String token : tokens) {
					nextTokens.add(token.trim());
				}
			} else {
				nextTokens = null;
			}
		}
		rowcount++;
	}

}

/**
 * Supplementary class to CSVFileIterator to keep method and constraint
 * information against CSVheader.
 * 
 */
class MetaInfo {
	private Method method;
	private CSVConstraint[] constraints;

	/**
	 * @param method
	 * @param constraints
	 */
	public MetaInfo(Method method, CSVConstraint[] constraints) {
		this.method = method;
		this.constraints = constraints;
	}

	/**
	 * @return the method
	 */
	public Method getMethod() {
		return method;
	}

	/**
	 * @param method
	 *            the method to set
	 */
	public void setMethod(Method method) {
		this.method = method;
	}

	/**
	 * @return the constraints
	 */
	public CSVConstraint[] getConstraints() {
		return constraints;
	}

	/**
	 * @param constraints
	 *            the constraints to set
	 */
	public void setConstraints(CSVConstraint[] constraints) {
		this.constraints = constraints;
	}

}
