package com.agnie.common.util.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.reflections.Reflections;

/**
 * factory to retrieve respective validator for given constraint.
 * 
 */
public class ValidatorFactory {
	protected static final Log											logger		= LogFactory.getLog(ValidatorFactory.class);
	/*
	 * mapping map will hold the mapping between constraint created as a annotation and its respective validator
	 */
	private static java.util.Map<Class<? extends Annotation>, Class<?>>	mapping		= new HashMap<Class<? extends Annotation>, Class<?>>();

	private static ValidatorFactory										INSTANCE	= null;

	private ValidatorFactory() {
		initialize();
	}

	public static ValidatorFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ValidatorFactory();
		}
		return INSTANCE;
	}

	/**
	 * Initializing mapping between constraint and respective validator
	 */
	private void initialize() {
		try {
			// TODO: Need to explore more on how to add more package to scan dynamically.
			Reflections reflections = new Reflections("com.agnie.common.util.validator");
			Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Constraint.class);
			for (Class<?> klass : annotated) {
				Constraint type = klass.getAnnotation(Constraint.class);
				if (klass.getSuperclass().equals(Validator.class)) {
					mapping.put(type.value(), klass);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Validator factory mapper got initialized");

	}

	/**
	 * get validator mapped to given annotation
	 * 
	 * @param an
	 * @return
	 */
	private Validator getValidator(Annotation an) {
		try {
			Class<?> validatorCls = mapping.get(an.annotationType());
			if (validatorCls != null) {
				Constructor<?> ctor = validatorCls.getDeclaredConstructor(Annotation.class);
				Validator validator = (Validator) ctor.newInstance(an);
				return validator;
			} else {
				/*
				 * TODO: throw exception for not finding the mapping and provide help message to add mapping of
				 * constraint against validator
				 */
			}
		} catch (NoSuchMethodException e) {
			logger.error(e);
		} catch (IllegalArgumentException e) {
			logger.error(e);
		} catch (InstantiationException e) {
			logger.error(e);
		} catch (IllegalAccessException e) {
			logger.error(e);
		} catch (InvocationTargetException e) {
			logger.error(e);
		}

		return null;
	}

	/**
	 * get validators for constraints applied on given methods.
	 * 
	 * @param method
	 * @return
	 */
	public List<Validator> getMethodValidator(Method method) {

		List<Validator> list = new ArrayList<Validator>();

		for (Class<? extends Annotation> annotationCls : mapping.keySet()) {
			Annotation an = method.getAnnotation(annotationCls);
			if (an != null) {
				list.add(getValidator(an));
			}
		}
		return list;
	}

	/**
	 * get validators for constraints applied on given field.
	 * 
	 * @param field
	 * @return
	 */
	public List<Validator> getFieldValidator(Field field) {

		List<Validator> list = new ArrayList<Validator>();

		for (Class<? extends Annotation> annotationCls : mapping.keySet()) {
			Annotation an = field.getAnnotation(annotationCls);
			if (an != null) {
				list.add(getValidator(an));
			}
		}
		return list;
	}
}
