package com.agnie.common.util.tablefile;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agnie.common.util.client.converter.ConversionException;
import com.agnie.common.util.client.tablefile.ConstraintViolationException;
import com.agnie.common.util.client.tablefile.DevException;
import com.agnie.common.util.client.tablefile.InvalidBeanType;
import com.agnie.common.util.client.tablefile.InvalidColumnValueException;
import com.agnie.common.util.client.tablefile.TableBean;
import com.agnie.common.util.converter.AbstractSingleColumnConverter;
import com.agnie.common.util.converter.SingleColumnConverterFactory;
import com.agnie.common.util.converter.TypeInfo;
import com.agnie.common.util.validator.Validator;

/**
 * It will help AbstractTableFileIterator to convert list of tokens into corresponding bean object.
 * 
 * @param <B>
 */
public class SimpleTokenProcessor<B> {
	private static final Log	logger	= LogFactory.getLog(SimpleTokenProcessor.class);

	private TypeInfo			metaInfo;

	protected Class<B>			cls;

	protected boolean			throwErrors;

	/**
	 * Don't instantiate using constructors even in the same class or respective derived class. Instead use
	 * getConverter() static method.
	 * 
	 * @param cls
	 */
	protected SimpleTokenProcessor(Class<B> cls) {
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
	protected SimpleTokenProcessor(Class<B> cls, boolean throwErrors) {
		this.cls = cls;
		this.throwErrors = throwErrors;
	}

	void setTypeInfo(TypeInfo info) {
		metaInfo = info;
	}

	public B getBean(Map<String, String> rowTokens) {

		try {
			Iterator<TypeInfo> itrProperties = metaInfo.getImmidiateAllPropertiesIterator();
			B b = cls.newInstance();
			TableBean bean = (TableBean) b;
			while (itrProperties.hasNext()) {
				TypeInfo property = itrProperties.next();
				if (property.isMultiColumnType() || property.isCollectionType()) {
					// TODO: add proper error message
					throw new InvalidBeanType();
				} else {
					// single column type and not collection type
					String token = (String) rowTokens.get(property.getHeaderName());
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
		} catch (InvalidBeanType e) {
			throw e;
		} catch (Exception e) {
			logger.error("Programming issue :", e);
			throw new DevException("Programming issue :", e);
		}
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
