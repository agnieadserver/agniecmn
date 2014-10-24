/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.util.tablefile;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agnie.common.util.client.converter.ConversionException;
import com.agnie.common.util.client.tablefile.ConstraintViolationException;
import com.agnie.common.util.client.tablefile.DevException;
import com.agnie.common.util.client.tablefile.InvalidColumnValueException;
import com.agnie.common.util.client.tablefile.TableBean;
import com.agnie.common.util.converter.AbstractSingleColumnConverter;
import com.agnie.common.util.converter.SingleColumnConverterFactory;
import com.agnie.common.util.converter.Tokenizer;
import com.agnie.common.util.converter.TypeInfo;
import com.agnie.common.util.validator.Validator;

/**
 * It will help AbstractTableFileIterator to convert list of tokens into corresponding bean object.
 * 
 * @param <B>
 */
public class TokenProcessor<B> {
	private static final Log	logger	= LogFactory.getLog(TokenProcessor.class);

	private TypeInfo			metaInfo;

	protected Class<B>			cls;

	protected boolean			throwErrors;

	/**
	 * Don't instantiate using constructors even in the same class or respective derived class. Instead use
	 * getConverter() static method.
	 * 
	 * @param cls
	 */
	protected TokenProcessor(Class<B> cls) {
		this(cls, false);
		this.cls = cls;
	}

	/**
	 * Don't instantiate using constructors even in the same class or respective derived class. Instead use
	 * getConverter() static method.
	 * 
	 * @param cls
	 * @param throwErrors
	 */
	protected TokenProcessor(Class<B> cls, boolean throwErrors) {
		this.cls = cls;
		this.throwErrors = throwErrors;
	}

	void setTypeInfo(TypeInfo info) {
		metaInfo = info;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public B getBean(List<Map<String, String>> rowTokens) {

		try {
			Map<String, ? extends Object> map = getInShape(rowTokens);
			Iterator<TypeInfo> itrProperties = metaInfo.getImmidiateAllPropertiesIterator();
			B b = cls.newInstance();
			TableBean bean = (TableBean) b;
			while (itrProperties.hasNext()) {
				TypeInfo property = itrProperties.next();
				if (property.isMultiColumnType() && property.isCollectionType()) {
					// Multicolumn type and of collection type
					// TODO: Here you need to iterate over list and check for availability of internal collection. If
					// yes
					// then check next record for having at least one single column that has some value
					List<Map<String, String>> propertyTokens = (List<Map<String, String>>) map.get(property.getHeaderName());
					if (propertyTokens != null && propertyTokens.size() > 0) {
						if (property.containsCollectionType()) {
							List<Map<String, String>> singleTokens = new ArrayList<Map<String, String>>();
							singleTokens.add(propertyTokens.get(0));
							TokenProcessor processor = TokenProcessorFactory.getConverter(property.getCls(), property, throwErrors);
							Map<String, String> lastMapOfToken = null;
							Map<String, String> mapOfToken = null;
							for (int index = 1; index < propertyTokens.size(); index++) {
								lastMapOfToken = mapOfToken;
								mapOfToken = propertyTokens.get(index);
								if (processor.checkIfNextRecord(mapOfToken, lastMapOfToken)) {
									property.getMethod().invoke(b, processor.getBean(singleTokens));
									singleTokens = new ArrayList<Map<String, String>>();
								}
								singleTokens.add(mapOfToken);
							}
							// Final records need to be processed outside the loop.
							property.getMethod().invoke(b, processor.getBean(singleTokens));
						} else {
							for (Map<String, String> mapOfToken : propertyTokens) {
								List<Map<String, String>> singleTokens = new ArrayList<Map<String, String>>();
								singleTokens.add(mapOfToken);
								TokenProcessor processor = TokenProcessorFactory.getConverter(property.getCls(), property, throwErrors);
								property.getMethod().invoke(b, processor.getBean(singleTokens));
							}
						}
					}
				} else if (property.isMultiColumnType()) {
					// Multicolumn type but not collection type

					List<Map<String, String>> propertyTokens = (List<Map<String, String>>) map.get(property.getHeaderName());
					if (propertyTokens != null && propertyTokens.size() > 0) {
						TokenProcessor processor = TokenProcessorFactory.getConverter(property.getCls(), throwErrors);
						property.getMethod().invoke(b, processor.getBean(propertyTokens));
					}
				} else if (property.isCollectionType()) {
					// Single column collection type
					String token = (String) map.get(property.getHeaderName());
					if (token != null) {
						Tokenizer tokenizer = property.getTokenizer();
						//
						String[] tokens = tokenizer.tokenize(token);
						if (tokens != null) {
							List<Validator> validators = property.getValidators();
							String beanProperty = property.getMethod().getName().substring(3);
							for (String listToken : tokens) {
								List<String> failedConstraints = validate(validators, listToken);
								if (failedConstraints == null) {
									try {
										populateBeanWithToken(property.getMethod(), listToken, bean);
									} catch (ConversionException e) {
										logger.error("error while converting value for column '" + property.getHeaderName() + "'.", e);
										if (throwErrors) {
											throw new InvalidColumnValueException(property.getHeaderName(), listToken, e);
										} else {
											failedConstraints = new ArrayList<String>();
											failedConstraints.add("invalid." + e.getMessage());
											bean.insertError(beanProperty, listToken, failedConstraints);
										}
									}
								} else {
									if (throwErrors) {
										throw new ConstraintViolationException(property.getHeaderName(), failedConstraints);
									}
									bean.insertError(beanProperty, listToken, failedConstraints);
								}
							}
						}
					}
				} else {
					// single column type and not collection type
					String token = (String) map.get(property.getHeaderName());
					List<Validator> validators = property.getValidators();
					String beanProperty = property.getMethod().getName().substring(3);
					List<String> failedConstraints = validate(validators, token);
					if (failedConstraints == null) {
						try {

							populateBeanWithToken(property.getMethod(), token, bean);
						} catch (ConversionException e) {
							logger.error("error while converting value for column '" + property.getHeaderName() + "'.", e);
							if (throwErrors) {
								throw new InvalidColumnValueException(property.getHeaderName(), token, e);
							} else {
								failedConstraints = new ArrayList<String>();
								failedConstraints.add("invalid." + e.getMessage());
								bean.insertError(beanProperty, token, failedConstraints);
							}
						}
					} else {
						if (throwErrors) {
							throw new ConstraintViolationException(property.getHeaderName(), failedConstraints);
						}
						bean.insertError(beanProperty, token, failedConstraints);
					}
				}
			}
			return b;
		} catch (Exception e) {
			logger.error("Programming issue :", e);
			throw new DevException("Programming issue :", e);
		}
	}

	/**
	 * 
	 * @param curntMapOfToken
	 * @param lastMapOfToken
	 * @return
	 */
	public boolean checkIfNextRecord(Map<String, String> curntMapOfToken, Map<String, String> lastMapOfToken) {
		if (lastMapOfToken == null || lastMapOfToken.size() == 0) {
			return false;
		} else if (curntMapOfToken != null && curntMapOfToken.size() > 0) {
			List<String> singleCols = metaInfo.getImmNotNullSingleColList();
			StringBuffer crntStr = new StringBuffer("");
			StringBuffer lastStr = new StringBuffer("");
			for (String header : singleCols) {
				String crntToken = curntMapOfToken.get(header);
				if (crntToken != null && !("".equals(crntToken))) {
					crntStr.append(crntToken);
				}
				String lastToken = lastMapOfToken.get(header);
				if (lastToken != null && !("".equals(lastToken))) {
					lastStr.append(lastToken);
				}
			}
			if ("".equals(crntStr.toString())) {
				return false;
			}
			if (("".equals(lastStr.toString()) && (!"".equals(crntStr))) || (!crntStr.toString().equals(lastStr.toString()))) {
				return true;
			}

		} else {
			return true;
		}
		return false;
	}

	/**
	 * This method will convert
	 * 
	 * @param rowTokens
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
						String token = map.get(header);
						if (token != null) {
							tokens.put(header, token);
						}
					}
					first = false;
				}
				for (TypeInfo child : childs) {
					((List<Map<String, String>>) tokens.get(child.getHeaderName())).add(retirveMultiColumnTokens(map, child));
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
			String token = map.get(header);
			if (token != null) {
				tokens.put(header, map.get(header));
			}
		}
		return tokens;
	}

	/**
	 * It will validate the given token with the validators applied on the property of the bean. And if it finds that it
	 * is violating any constraints then it will return the list of violating constraints. If it returns null that means
	 * validation is successful.
	 * 
	 * @param validators
	 * @param token
	 * @return
	 */
	private List<String> validate(List<Validator> validators, String token) {
		List<String> failedConstraints = new ArrayList<String>();
		if (validators != null && validators.size() > 0) {
			for (Validator validator : validators) {
				if (!validator.validate(token)) {
					failedConstraints.add("constraint." + validator.getConstraint().annotationType().getSimpleName().toLowerCase() + ".fail");
				}
			}
			return (failedConstraints.size() > 0 ? failedConstraints : null);
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
	private void populateBeanWithToken(Method method, String token, TableBean bean) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, ConversionException {
		if (token != null && !("".equals(token))) {
			Class paramCls = method.getParameterTypes()[0];
			AbstractSingleColumnConverter converter = SingleColumnConverterFactory.getInstance().getConverter(paramCls);
			Object[] param = { converter.convert(token, paramCls) };
			method.invoke(bean, param);
		}
	}

}
