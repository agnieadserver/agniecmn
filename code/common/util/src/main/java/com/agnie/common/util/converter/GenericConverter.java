package com.agnie.common.util.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
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

		Map<String, ? extends Object> map = getInShape(rowTokens);
		Iterator<TypeInfo> itrProperties = metaInfo.getImmidiateAllPropertiesIterator();
		B bean = cls.newInstance();
		while (itrProperties.hasNext()) {
			TypeInfo property = itrProperties.next();
			if (property.isMultiColumnType() && property.isCollectionType()) {
				// Multicolumn type and of collection type
			} else if (property.isMultiColumnType()) {
				// Multicolumn type but not collection type
			} else if (property.isCollectionType()) {
				// Single column collection type
			} else {
				// single column type and not collection type
				String token = (String)map.get(property.getHeaderName());
				List<Validator> validators = meta.getValidators();
				List<Annotation> failedConstraints = validate(validators, token);
			}
		}
			String token = nextTokens.get(index);
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

	@SuppressWarnings("rawtypes")
	private Map<String, ? extends Object> getInShape(List<Map<String, String>> rowTokens) {
		Map tokens = new HashMap();
		if (metaInfo.containsCollectionType() || metaInfo.containsMultiColumnType()) {

			/*
			 * Iterate over every row tokens mapped with column header name Retrieve all single column header and add it
			 * against given property/column header. Then Iterate over multicolumn types and take out row records which
			 * are specific to given multicolumn type
			 */

			boolean first = true;
			List<TypeInfo> childs = metaInfo.getChilds();
			for (TypeInfo child : childs) {
				tokens.put(child.getHeaderName(), new ArrayList<Map<String, String>>());
			}
			for (Map<String, String> map : rowTokens) {
				if (first) {
					// Here we assume that single column tokens will be there only in first row.
					List<String> singColList = metaInfo.getImmidiateSingleColumnList();
					for (String header : singColList) {
						tokens.put(header, map.get(header));
					}
					first = false;
					for (TypeInfo child : childs) {
						((List<Map<String, String>>) tokens.get(child.getHeaderName())).add(retirveMultiColumnTokens(map, child));
					}
				}

			}
		} else {
			tokens = rowTokens.get(0);
		}
		return tokens;
	}

	private static Map<String, String> retirveMultiColumnTokens(Map<String, String> map, TypeInfo type) {
		Map<String, String> tokens = new HashMap<String, String>();
		for (String header : type.getAllSingleColumnList()) {
			tokens.put(header, map.get(header));
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
