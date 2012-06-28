package com.agnie.common.util.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.formula.functions.T;

import com.agnie.common.util.tablefile.ConstraintViolationException;
import com.agnie.common.util.tablefile.InvalidColumnValueException;
import com.agnie.common.util.validator.Validator;

public class GenericConverter<B> {
	private static final Log								logger	= LogFactory.getLog(GenericConverter.class);

	private static final Map<Class<T>, GenericConverter<T>>	map		= new HashMap<Class<T>, GenericConverter<T>>();

	private TypeInfo										metaInfo;

	protected Class<B>										cls;

	public static GenericConverter<T> getConverter(Class<T> cls) {
		GenericConverter<T> converter = map.get(cls);
		if (converter == null) {
			converter = new GenericConverter<T>(cls);
			map.put(cls, converter);
		}
		converter.metaInfo = new TypeInfo(cls);
		return converter;
	}

	protected static GenericConverter<T> getConverter(Class<T> cls, TypeInfo info) {
		GenericConverter<T> converter = map.get(cls);
		if (converter == null) {
			converter = new GenericConverter<T>(cls);
			map.put(cls, converter);
		}
		converter.metaInfo = info;
		return converter;
	}

	protected GenericConverter(Class<B> cls) {
		this.cls = cls;
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

	private Map<String, ? extends Object> getInShape(List<Map<String, String>> rowTokens) {
		Map<String, ? extends Object> tokens = new HashMap<String, Object>();
		if (metaInfo.containsCollectionType() || metaInfo.containsMultiColumnType()) {
			Iterator<TypeInfo> itrProperties = metaInfo.getImmidiateAllPropertiesIterator();
			while (itrProperties.hasNext()) {
				TypeInfo property = itrProperties.next();
				// single column type and not collection type
				if (!property.isMultiColumnType() && !property.isCollectionType()) {
//					tokens.put(property.getHeaderName(), value)
				}
			}
		} else {
			tokens = rowTokens.get(0);
		}
		return tokens;
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

}
