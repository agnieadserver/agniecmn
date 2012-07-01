package com.agnie.common.util.converter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.agnie.common.util.tablefile.GeneralException;
import com.agnie.common.util.tablefile.TableBean;
import com.agnie.common.util.tablefile.TableHeader;
import com.agnie.common.util.validator.Validator;
import com.agnie.common.util.validator.ValidatorFactory;

/**
 * 
 * Class which holds information about bean class
 * 
 */

public class TypeInfo {

	private Class<?>				cls;
	private Map<String, TypeInfo>	propertyMapping		= new HashMap<String, TypeInfo>();
	private boolean					collectionType		= false;
	private boolean					multiColumn			= false;
	private String					headerName;
	private Method					method;
	private List<Validator>			validators;
	private List<String>			singleColumnHeaders	= new ArrayList<String>();
	private List<TypeInfo>			childs				= new ArrayList<TypeInfo>();
	private Tokenizer				tokenizer;

	public TypeInfo(Class<?> cls, Method method, List<Validator> validators, String headerName) {
		this(cls, false, method, validators, headerName, null);
	}

	public TypeInfo(Class<?> cls, boolean collectionType, Method method, String headerName) {
		this(cls, collectionType, method, null, headerName, null);
	}

	/**
	 * By default it treats the given class to be of multicoloumn type class
	 * 
	 * @param cls
	 */
	public TypeInfo(Class<?> cls) {
		this(cls, false, null, null, null, null);
	}

	public TypeInfo(Class<?> cls, boolean collectionType, Method method, List<Validator> validators, String headerName, Tokenizer tokenizer) {
		super();
		this.cls = cls;
		this.collectionType = collectionType;
		this.multiColumn = checkIfMultiColumn(cls);
		this.method = method;
		this.validators = validators;
		this.headerName = headerName;
		this.tokenizer = tokenizer;
		/*
		 * if input type is of mutlicolumn then only explore its methods
		 */
		if (multiColumn) {
			for (Method innerMeth : cls.getMethods()) {
				String methodName = innerMeth.getName();
				if (innerMeth.getParameterTypes().length > 0) {
					Class<?> paramType = innerMeth.getParameterTypes()[0];
					// Here we assume setter or add has only one parameter passed to it
					if (innerMeth.getParameterTypes().length > 1 || innerMeth.getParameterTypes().length < 1) {
						// TODO: Throw exception
					}
					String hedToken = "";
					TableHeader hed = innerMeth.getAnnotation(TableHeader.class);
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
							List<Validator> valids = valFactory.getMethodValidator(innerMeth);
							propertyMapping.put(hedToken, new TypeInfo(paramType, innerMeth, valids, hedToken));
							singleColumnHeaders.add(hedToken);
						} else {
							/*
							 * In case of multiple column type TableHeader annotation will be ignored
							 */

							String property = methodName.substring(3);
							TypeInfo child = new TypeInfo(paramType, false, innerMeth, property);
							propertyMapping.put(property, child);
							childs.add(child);
						}
					} else if (methodName.startsWith("add")) {
						if (!checkIfMultiColumn(paramType)) {
							tokenizer = TokenizerFactory.getInstance().getTokenizer(innerMeth);
							if (tokenizer != null) {
								// TODO: Need to add mechanism to throw error in case developers need to restrict using
								// some separators kind of exclusion list
							}
							ValidatorFactory valFactory = ValidatorFactory.getInstance();
							List<Validator> valids = valFactory.getMethodValidator(innerMeth);
							propertyMapping.put(hedToken, new TypeInfo(paramType, true, innerMeth, valids, hedToken, tokenizer));
							singleColumnHeaders.add(hedToken);
						} else {
							/*
							 * In case of multiple column type TableHeader annotation will be ignored
							 */
							String property = methodName.substring(3);
							TypeInfo child = new TypeInfo(paramType, true, innerMeth, null, property, null);
							propertyMapping.put(property, child);
							childs.add(child);
						}
					}
				}
			}
		} else {
			AbstractSingleColumnConverter converter = SingleColumnConverterFactory.getInstance().getConverter(cls);
			if (converter == null) {
				throw new GeneralException("Programming error: '" + cls.getCanonicalName() + "' need to be either multicolumn type or you need to define SingleColumnConverter for the same");
			}
		}
	}

	private boolean checkIfMultiColumn(Class<?> paramType) {

		MultiColumnType mutliAnn = paramType.getAnnotation(MultiColumnType.class);
		boolean resp = (mutliAnn != null);
		if (resp) {
			boolean implemented = false;
			for (Class<?> interfaces : paramType.getInterfaces()) {
				if (TableBean.class.equals(interfaces)) {
					implemented = true;
					break;
				}
			}
			if (!implemented) {
				throw new GeneralException("Programming error: class '" + paramType.getCanonicalName() + "' has been used as multicolumn type, so it needs to implement '"
						+ TableBean.class.getCanonicalName() + "' ");
			}
		}
		return resp;
	}

	/**
	 * @return the tokenizer
	 */
	public Tokenizer getTokenizer() {
		return tokenizer;
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

	public String getHeaderName() {
		return headerName;
	}

	public boolean containsCollectionType() {
		if (childs == null || childs.isEmpty()) {
			return false;
		}

		for (TypeInfo info : childs) {
			if (info.isCollectionType())
				return true;
			if (info.containsCollectionType())
				return true;
		}
		return false;
	}

	public boolean containsMultiColumnType() {
		if (childs == null || childs.isEmpty()) {
			return false;
		}

		for (TypeInfo info : childs) {
			if (info.isMultiColumnType())
				return true;
			if (info.containsMultiColumnType())
				return true;
		}
		return false;
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

	public Iterator<TypeInfo> getImmidiateAllPropertiesIterator() {
		return propertyMapping.values().iterator();
	}

	/**
	 * This would return a Iterator to iterate over all multicolumn Types
	 * 
	 * @return
	 */
	public List<TypeInfo> getChilds() {
		return childs;
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
