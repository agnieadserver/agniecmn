package com.agnie.common.util.converter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.agnie.common.util.tablefile.BaseTableBean;
import com.agnie.common.util.tablefile.DevException;
import com.agnie.common.util.tablefile.TableBean;
import com.agnie.common.util.tablefile.TableHeader;
import com.agnie.common.util.validator.NotNullValidator;
import com.agnie.common.util.validator.Validator;
import com.agnie.common.util.validator.ValidatorFactory;

/**
 * 
 * Class which holds information about bean class
 * 
 */

public class TypeInfo {

	private Class<?>				cls;
	private Map<String, TypeInfo>	propertyMapping				= new HashMap<String, TypeInfo>();
	private boolean					collectionType				= false;
	private boolean					multiColumn					= false;
	private String					headerName;
	private Method					method;
	private List<Validator>			validators;
	private List<String>			singleColumnHeaders			= new ArrayList<String>();
	private List<String>			singleColumnNotNullHeaders	= new ArrayList<String>();
	private List<TypeInfo>			childs						= new ArrayList<TypeInfo>();
	private Tokenizer				tokenizer;
	private boolean					notNull;

	public TypeInfo(Class<?> cls, Method method, List<Validator> validators, String headerName, boolean notNull) {
		this(cls, false, method, validators, headerName, null, notNull);
	}

	public TypeInfo(Class<?> cls, boolean collectionType, Method method, String headerName) {
		this(cls, collectionType, method, null, headerName, null, false);
	}

	/**
	 * By default it treats the given class to be of multicoloumn type class
	 * 
	 * @param cls
	 */
	public TypeInfo(Class<?> cls) {
		this(cls, false, null, null, null, null, false);
	}

	public TypeInfo(Class<?> cls, boolean collectionType, Method method, List<Validator> validators, String headerName, Tokenizer tokenizer, boolean notNull) {
		super();
		this.cls = cls;
		this.collectionType = collectionType;
		this.multiColumn = checkIfMultiColumn(cls);
		this.method = method;
		this.validators = validators;
		this.headerName = headerName;
		this.tokenizer = tokenizer;
		this.notNull = notNull;
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
							boolean checkNotNull = false;
							ValidatorFactory valFactory = ValidatorFactory.getInstance();
							List<Validator> valids = valFactory.getMethodValidator(innerMeth);
							if (valids != null && valids.size() > 0) {
								for (Validator validator : valids) {
									if (NotNullValidator.class.equals(validator.getClass())) {
										checkNotNull = true;
									}
								}
							}
							propertyMapping.put(hedToken, new TypeInfo(paramType, innerMeth, valids, hedToken, checkNotNull));
							if (checkNotNull) {
								singleColumnNotNullHeaders.add(hedToken);
							}
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
							} else {
								throw new DevException("Programming error: property '" + hedToken + "' is single column collection type property. Which requires tokenizer to be specified.",
										"property.require.tokenizer");
							}
							ValidatorFactory valFactory = ValidatorFactory.getInstance();
							List<Validator> valids = valFactory.getMethodValidator(innerMeth);
							propertyMapping.put(hedToken, new TypeInfo(paramType, true, innerMeth, valids, hedToken, tokenizer, false));
							singleColumnHeaders.add(hedToken);
						} else {
							/*
							 * In case of multiple column type TableHeader annotation will be ignored
							 */
							String property = methodName.substring(3);
							TypeInfo child = new TypeInfo(paramType, true, innerMeth, null, property, null, false);
							propertyMapping.put(property, child);
							childs.add(child);
						}
					}
				}
			}

			// If current type is multicolumn type and it contains at least one multi column collection type data type.
			// Then it has to adhere to requirment that current type must have at least one single column property with
			// notNull constraint applied to it.
			if (containsCollectionType()) {
				boolean singleColNonCollType = false;

				for (TypeInfo child : propertyMapping.values()) {
					if (!child.multiColumn && !child.collectionType) {
						List<Validator> vals = child.getValidators();
						if (vals != null && vals.size() > 0) {
							for (Validator validator : vals) {
								if (NotNullValidator.class.equals(validator.getClass())) {
									singleColNonCollType = true;
									break;
								}
							}
						}
					}
					if (singleColNonCollType) {
						break;
					}
				}
				// If single column non collection type and having NotNull constraint is not present
				if (!singleColNonCollType) {
					throw new DevException(
							"Programming error: Type containing multi column collection type property must have at least one single column non collection type and having NotNull constraint applied to it",
							"require.one.single.noncollection.notnull");

				}
			}
		} else {
			AbstractSingleColumnConverter converter = SingleColumnConverterFactory.getInstance().getConverter(cls);
			if (converter == null) {
				throw new DevException("Programming error: '" + cls.getCanonicalName() + "' need to be either multicolumn type or you need to define SingleColumnConverter for the same",
						"neither.multicolumn.nor.has.converter");
			}
		}
	}

	private boolean checkIfMultiColumn(Class<?> paramType) {

		MultiColumnType mutliAnn = paramType.getAnnotation(MultiColumnType.class);
		boolean resp = (mutliAnn != null);
		if (resp) {
			boolean implemented = false;
			/*
			 * TODO: Below check to see if given class has implemented TableBean interface need to be improved to check.
			 * If it has been implemented at any level of given class
			 */
			for (Class<?> interfaces : paramType.getInterfaces()) {
				if (TableBean.class.equals(interfaces)) {
					implemented = true;
					break;
				}
			}
			if (BaseTableBean.class.equals(paramType.getSuperclass())) {
				implemented = true;
			}
			if (!implemented) {
				throw new DevException("Programming error: class '" + paramType.getCanonicalName() + "' has been used as multicolumn type, so it needs to implement '"
						+ TableBean.class.getCanonicalName() + "' ", "need.toimplement.tablebean.interface");
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
	 * @return the notNull
	 */
	public boolean isNotNull() {
		return notNull;
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

	public List<String> getImmNotNullSingleColList() {
		return singleColumnNotNullHeaders;
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
