package com.agnie.common.util.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * If you add new validator with its respective constraint annotation. Add the required mapping of constraint and its
 * respective validator here in this interface
 * 
 */
@Mapper({ @Map(constraint = NotNull.class, validator = NotNullValidator.class) })
public class ValidatorFactory {

	/*
	 * mapping map will hold the mapping between constraint created as a annotation and its respective validator
	 */
	private static java.util.Map<Class<? extends Annotation>, Class<? extends Validator>>	mapping	= new HashMap<Class<? extends Annotation>, Class<? extends Validator>>();

	/**
	 * Here mapper annotations are read and mapping configuration is initialised when first time this class is used.
	 */
	static {
		Mapper mapper = ValidatorFactory.class.getAnnotation(Mapper.class);
		for (Map map : mapper.value()) {
			mapping.put(map.constraint(), map.validator());
		}
	}

	/**
	 * get validator mapped to given annotation
	 * 
	 * @param an
	 * @return
	 */
	private Validator getValidator(Annotation an) {
		try {
			Class<? extends Validator> validatorCls = mapping.get(an.annotationType());
			if (validatorCls != null) {
				Constructor<? extends Validator> ctor = validatorCls.getDeclaredConstructor(Annotation.class);
				Validator validator = ctor.newInstance(an);
				return validator;
			} else {
				/*
				 * TODO: throw exception for not finding the mapping and provide help message to add mapping of
				 * constraint against validator
				 */
			}
		} catch (NoSuchMethodException e) {
			// TODO Add logger
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Add logger
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Add logger
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Add logger
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Add logger
			e.printStackTrace();
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
