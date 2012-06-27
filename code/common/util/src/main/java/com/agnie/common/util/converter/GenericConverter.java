package com.agnie.common.util.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.formula.functions.T;

import com.agnie.common.util.tablefile.ConstraintViolationException;
import com.agnie.common.util.tablefile.InvalidColumnValueException;
import com.agnie.common.util.tablefile.TableHeader;
import com.agnie.common.util.validator.Validator;
import com.agnie.common.util.validator.ValidatorFactory;

public class GenericConverter<B> {
	protected static final Log		logger		= LogFactory.getLog(GenericConverter.class);

	protected Map<String, MetaInfo>	methodMap	= new HashMap<String, MetaInfo>();

	protected Class<B>				cls;

	protected GenericConverter(Map<String, MetaInfo> methodMap, Class<B> cls) {
		this.methodMap = methodMap;
		this.cls = cls;

		methodMap = new HashMap<String, MetaInfo>();

		for (Method meth : cls.getMethods()) {
			String methodName = meth.getName();
			if (methodName.startsWith("set")) {
				// Here it is assumed every setter will have single parameter, Passed entity need to pure java bean.
				Class<?> field = meth.getParameterTypes()[0];
				String hedToken = "";
				TableHeader hed = meth.getAnnotation(TableHeader.class);
				if (hed != null) {
					if (!("".equals(hed.name()))) {
						hedToken = hed.name();
					} else {
						hedToken = methodName.substring(3);
					}
					ValidatorFactory valFactory = ValidatorFactory.getInstance();
					List<Validator> validators = valFactory.getMethodValidator(meth);
					methodMap.put(hedToken.toLowerCase(), new MetaInfo(meth, validators, field));
				}
			}
		}
	}

	public B getBean(List<Map<String, String>> rowTokens) {

		// Below commented code has kept just for reference
		// Method m;
		// Type[] genericParameterTypes = m.getGenericParameterTypes();
		// for (int i = 0; i < genericParameterTypes.length; i++) {
		// if( genericParameterTypes[i] instanceof ParameterizedType ) {
		// Type[] parameters = ((ParameterizedType)genericParameterTypes[i]).getActualTypeArguments();
		// //parameters[0] contains java.lang.String for method like "method(List<String> value)"
		//
		// }
		// }

		B bean = cls.newInstance();
		MetaInfo meta = methodMap.get(index);
		if (meta != null) {
			String token = nextTokens.get(index);
			List<Validator> validators = meta.getValidators();
			List<Annotation> failedConstraints = validate(validators, token);
			if (failedConstraints == null) {
				try {
					populateBeanWithToken(meta.getMethod(), token, bean);
				} catch (NumberFormatException e) {
					logger.error("error while setting value for column '" + meta.getMethod().getName() + "' expected number, actual value =\"" + token + "\"", e);
					throw new InvalidColumnValueException(headerList.get(index), rowcount, token, "NUMBER_EXPECTED");
				}
			} else {
				if (throwValidationErrors) {
					throw new ConstraintViolationException(headerList.get(index), rowcount, failedConstraints);
				}
				String property = meta.getMethod().getName().substring(3);
				lastBeanfailedConstraints.put(property, failedConstraints);
			}
		}
	}

	private Map<String, Object> getInShape(List<Map<String, String>> rowTokens) {
		Map<String, Object> tokens = new HashMap<String, Object>();
		for (String key : methodMap.keySet()) {
			MetaInfo meta = methodMap.get(key);
			if
		}
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
				StringTokenizer st = new StringTokenizer(token, ".");
				token = st.nextToken();
				return Integer.parseInt(token);
			} else if (float.class.equals(paramCls) || Float.class.equals(paramCls)) {
				return Float.parseFloat(token);
			} else if (long.class.equals(paramCls) || Long.class.equals(paramCls)) {
				StringTokenizer st = new StringTokenizer(token, ".");
				token = st.nextToken();
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

}

/**
 * Supplementary class to AbstractTableFileIterator to keep method and validators information against Column Header
 * Name.
 * 
 */
class MetaInfo {
	private Method			method;
	private List<Validator>	validators;
	private Class<?>		field;

	/**
	 * @param method
	 * @param validators
	 * @param field
	 */
	MetaInfo(Method method, List<Validator> validators, Class<?> field) {
		this.method = method;
		this.validators = validators;
		this.field = field;
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

	public boolean isListTypeParameter() {
		return List.class.equals(field) || Set.class.equals(field) || field.isArray();
	}

	/**
	 * @return the field
	 */
	public Class<?> getParameterType() {
		return field;
	}

}