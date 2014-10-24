/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.util.tablefile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * AbstractTableFileIterator will be abstract or base for reading csv, or excel sheet or any other file in table format
 * as a Generic Bean/POJO iterator. Which takes InputStream or Reader and iterate over it line by line. When you iterate
 * it, it will return entity bean for every record with values populated as per following rule. Use SimpleIterators if
 * table is mapped to single bean, and doesn't contain any child at any level.
 * <p>
 * Entity you want to get populate should attach TableHeader annotation to the property setters. TableHeader takes one
 * parameter "name".
 * <ul>
 * <li>If you don't specify name parameter, it will try to search for header with the name exactly matching to the bean
 * property with first character in capital case. If you have different header name in your table file you can map it to
 * the property with this name parameter.
 * </ul>
 * 
 * Additionally one can attach constraints on the column like @NotNull. While iterating over the records if record is
 * found to be violating constraints of any column. Then it will either throw ConstraintViolationException or it can
 * keep the list of violating constraints and return it when it is asked. It is configurable when you are instantiating
 * the TableFileIterator.
 * 
 */
public abstract class AbstractTableFileIterator<T> implements Iterator<T> {

	protected static final Log	logger					= LogFactory.getLog(AbstractTableFileIterator.class);
	private boolean				tokenProduced			= false;
	protected Class<T>			cls;
	protected long				rowcount				= 0;
	protected boolean			throwValidationErrors	= false;
	private Map<String, String>	nextTokens				= null;
	protected TokenProcessor<T>	processor;

	/**
	 * Sub class of this class must call init() method a the end of the constructor to initialise the Iterator.
	 * 
	 * @param cls
	 * @throws IOException
	 */
	public AbstractTableFileIterator(Class<T> cls) throws IOException {
		this(cls, false);
	}

	/**
	 * Sub class of this class must call init() method a the end of the constructor to initialise the Iterator.
	 * 
	 * @param cls
	 * @param throwValidationErrors
	 *            boolean flag which will indicate iterator. If it has to throw exception immediately or it has to
	 *            collect those errors and provide it after record is retrieved.
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public AbstractTableFileIterator(Class<T> cls, boolean throwValidationErrors) throws IOException {
		this.cls = cls;
		this.throwValidationErrors = throwValidationErrors;
		processor = TokenProcessorFactory.getConverter(cls, throwValidationErrors);
	}

	/**
	 * Check if iterator has next record to read.
	 */
	public boolean hasNext() {
		if (!tokenProduced) {
			try {
				nextTokens = readTokens();
				tokenProduced = true;
			} catch (IOException e) {
				logger.error("Error while reading the tokens from Table File", e);
			}
		}
		return (nextTokens != null && nextTokens.size() > 0);
	}

	/**
	 * Retrieve the next record as bean
	 */
	public T next() {
		if (!tokenProduced) {
			try {
				nextTokens = readTokens();
				tokenProduced = true;
			} catch (IOException e) {
				logger.error("Error while reading the tokens from Table File", e);
				return null;
			}
		}
		T bean = null;
		try {
			bean = getBean();

		} catch (IllegalArgumentException e) {
			// TODO: Need to throw run time exception in case of any error.
			logger.error("Table file bean setting error", e);
		} catch (InstantiationException e) {
			logger.error("Table file bean setting error", e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			logger.error("Table file bean setting error", e);
		} catch (InvocationTargetException e) {
			logger.error("Table file bean setting error", e);
		}
		return bean;
	}

	/**
	 * getBean will read list of nextTokens, instantiate the given bean, validate it against every property, populate
	 * the bean as per the rule and finally return the same. If it violates any of the constraints of property it will
	 * throw RuntimeException ConstraintViolationException with csv header name, row count and list of violating
	 * validators
	 * 
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private T getBean() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if ((nextTokens != null && nextTokens.size() > 0)) {
			List<Map<String, String>> rawTokens = new ArrayList<Map<String, String>>();
			rawTokens.add(nextTokens);
			while (true) {
				try {
					Map<String, String> lastTokens = nextTokens;
					nextTokens = readTokens();
					if (processor.checkIfNextRecord(nextTokens, lastTokens)) {
						return processor.getBean(rawTokens);
					}
					rawTokens.add(nextTokens);
				} catch (IOException e) {
					logger.error("Error while reading the tokens from Table File", e);
				}
			}
		}
		return null;
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Abstract method to read one line of file and separate every column value as a token and populate it in to
	 * nextTokens list. Which will be used by subsequent methods
	 * 
	 * @throws IOException
	 */
	protected abstract Map<String, String> readTokens() throws IOException;

}
