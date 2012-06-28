package com.agnie.common.util.converter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.agnie.common.util.tablefile.TableHeader;
import com.agnie.common.util.validator.Validator;
import com.agnie.common.util.validator.ValidatorFactory;

public class TypeInfo {

	private Class<?>				cls;
	private Map<String, TypeInfo>	propertyMapping		= new HashMap<String, TypeInfo>();
	private boolean					collectionType		= false;
	private boolean					multiColumn			= false;
	private Method					method;
	private List<Validator>			validators;
	private List<String>			singleColumnHeaders	= new ArrayList<String>();
	private List<TypeInfo>			childs				= new ArrayList<TypeInfo>();

	public TypeInfo(Class<?> cls, Method method, List<Validator> validators) {
		this(cls, false, method, validators);
	}

	public TypeInfo(Class<?> cls, boolean collectionType, Method method) {
		this(cls, collectionType, method, null);
	}

	/**
	 * By default it treats the given class to be of multicoloumn type class
	 * 
	 * @param cls
	 */
	public TypeInfo(Class<?> cls) {
		this(cls, false, null, null);
	}

	public TypeInfo(Class<?> cls, boolean collectionType, Method method, List<Validator> validators) {
		super();
		this.cls = cls;
		this.collectionType = collectionType;
		this.multiColumn = checkIfMultiColumn(cls);
		this.method = method;
		this.validators = validators;

		/*
		 * if input type is of mutlicolumn then only explore its methods
		 */
		if (multiColumn) {
			for (Method meth : cls.getMethods()) {
				String methodName = meth.getName();
				if (meth.getParameterTypes().length > 0) {
					Class<?> paramType = meth.getParameterTypes()[0];
					// Here we assume setter or add has only one parameter passed to it
					if (meth.getParameterTypes().length > 1 || meth.getParameterTypes().length < 1) {
						// TODO: Throw exception
					}
					String hedToken = "";
					TableHeader hed = meth.getAnnotation(TableHeader.class);
					if (hed != null && !("".equals(hed.name()))) {
						hedToken = hed.name();
					} else {
						hedToken = methodName.substring(3);
					}
					if (methodName.startsWith("set")) {
						/*
						 * Check if given property is single column property of multiple Column property
						 */
						// TODO: need to build converter information for a given property
						if (!checkIfMultiColumn(paramType)) {
							ValidatorFactory valFactory = ValidatorFactory.getInstance();
							List<Validator> valids = valFactory.getMethodValidator(meth);
							propertyMapping.put(hedToken, new TypeInfo(paramType, meth, valids));
							singleColumnHeaders.add(hedToken);
						} else {
							/*
							 * In case of multiple column type TableHeader annotation will be ignored
							 */

							String property = methodName.substring(3);
							TypeInfo child = new TypeInfo(paramType, false, meth);
							propertyMapping.put(property, child);
							childs.add(child);
						}
					} else if (methodName.startsWith("add")) {
						if (!checkIfMultiColumn(paramType)) {
							propertyMapping.put(hedToken, new TypeInfo(paramType, true, meth, null));
							singleColumnHeaders.add(hedToken);
						} else {
							/*
							 * In case of multiple column type TableHeader annotation will be ignored
							 */
							String property = methodName.substring(3);
							TypeInfo child = new TypeInfo(paramType, true, method, null);
							propertyMapping.put(property, child);
							childs.add(child);
						}
					}
				}
			}
		}
	}

	private boolean checkIfMultiColumn(Class<?> paramType) {

		MultiColumnType mutliAnn = paramType.getAnnotation(MultiColumnType.class);
		return mutliAnn != null;
	}

	public boolean isMultiColumnType() {
		return multiColumn;
	}

	public boolean isCollectionType() {
		return collectionType;
	}

	/**
	 * @return the cls
	 */
	public Class<?> getCls() {
		return cls;
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
		// TODO : Need to return cloned copy of list as there is possibility of list getting modified outside of this
		// class.
		return validators;
	}

	public List<String> getImmidiateSingleColumnList() {
		// TODO: Need to check if we have to return cloned copy of list. As there is a possibility of list getting
		// modified outside this class in current case
		return singleColumnHeaders;
	}

	public List<String> getAllSingleColumnList() {
		if (childs == null || childs.isEmpty()) {
			return singleColumnHeaders;
		} else {
			List<String> list = new ArrayList<String>(singleColumnHeaders);
			for (TypeInfo type : childs) {
				list.addAll(type.getAllSingleColumnList());
			}
			return list;
		}
	}

	public Iterator<String> getImmidiateAllPropertiesIterator() {
		return propertyMapping.keySet().iterator();
	}

	/**
	 * Retrieve TypeInfo instance for given column header be it direct or inner child.
	 * 
	 * @param header
	 * @return
	 */
	public TypeInfo getTypeInfo(String header) {
		TypeInfo info = null;
		info = propertyMapping.get(header);
		if (info == null) {
			for (TypeInfo child : childs) {
				info = child.getTypeInfo(header);
				if (info != null)
					return info;
			}
		}
		return info;
	}
}
