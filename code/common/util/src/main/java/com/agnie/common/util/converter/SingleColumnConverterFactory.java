/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.util.converter;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.reflections.Reflections;

import com.agnie.common.util.client.converter.For;

/**
 * 
 * Converter to convert single token to respective value
 * 
 */
public class SingleColumnConverterFactory {
	protected static final Log												logger			= LogFactory.getLog(SingleColumnConverterFactory.class);
	/*
	 * mapping map will hold the mapping between constraint created as a annotation and its respective validator
	 */
	private static java.util.Map<Class<?>, AbstractSingleColumnConverter>	mapping			= new HashMap<Class<?>, AbstractSingleColumnConverter>();

	private static SingleColumnConverterFactory								INSTANCE		= null;
	private static BasicEnumSingleColumnConverter							enumConverter	= new BasicEnumSingleColumnConverter();

	private SingleColumnConverterFactory() {
		initialize();
	}

	public static SingleColumnConverterFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SingleColumnConverterFactory();
		}
		return INSTANCE;
	}

	/**
	 * Initializing mapping between class and respective converter
	 */
	private void initialize() {
		try {
			// TODO: Need to explore more on how to add more package to scan dynamically.
			Reflections reflections = new Reflections("com.agnie.common.util.converter");
			Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(For.class);
			for (Class<?> klass : annotated) {
				For type = klass.getAnnotation(For.class);
				if (klass.getSuperclass().equals(AbstractSingleColumnConverter.class)) {
					Constructor<?> ctor = klass.getConstructor();
					AbstractSingleColumnConverter converter = (AbstractSingleColumnConverter) ctor.newInstance();
					for (Class<?> cls : type.value()) {
						mapping.put(cls, converter);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}

		logger.info("Single column converter factory mapper got initialized");

	}

	/**
	 * get Converter for given class
	 * 
	 * @param class
	 * @return
	 */
	public AbstractSingleColumnConverter getConverter(Class<?> cls) {
		if (cls.isEnum()) {
			return enumConverter;
		}
		AbstractSingleColumnConverter converter = mapping.get(cls);
		return converter;
	}

}
