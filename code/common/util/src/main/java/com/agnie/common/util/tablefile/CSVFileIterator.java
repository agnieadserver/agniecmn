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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import au.com.bytecode.opencsv.CSVReader;

/**
 * CSVFileIterator will be CSV to Bean Generic iterator. Which takes InputStream or Reader and iterate over it line by
 * line. When you iterate it, it will return entity bean for every record with values populated as per following rule.
 * <p>
 * Entity you want to get populate should attach TableHeader annotation to the property setters. TableHeader takes one
 * parameter i.e "name".
 * <ul>
 * <li>If you don't specify name parameter, it will try to search for the header with name exactly matching to the bean
 * property with first character in capital case. If you have different header name in CSV you can map it to the
 * property with this name parameter.
 * </ul>
 * 
 */
public class CSVFileIterator<T> extends AbstractTableFileIterator<T> {

	protected static final Log	logger				= LogFactory.getLog(CSVFileIterator.class);
	private CSVReader			reader;
	private ArrayList<String>	indexMappedHeaders	= new ArrayList<String>();

	public CSVFileIterator(InputStream stream, Class<T> cls) throws IOException {
		this(stream, cls, new CSVConfig(), false);
	}

	public CSVFileIterator(InputStream stream, Class<T> cls, CSVConfig config, boolean throwValidationErrors) throws IOException {
		this(new InputStreamReader(stream), cls, config, throwValidationErrors);
	}

	public CSVFileIterator(Reader reader, Class<T> cls) throws IOException {
		this(reader, cls, new CSVConfig());
	}

	public CSVFileIterator(Reader reader, Class<T> cls, CSVConfig config) throws IOException {
		this(reader, cls, config, false);
	}

	/***
	 * Build CSV File Iterator
	 * 
	 * @param reader
	 *            File Reader
	 * @param cls
	 *            Class instance of bean class.
	 * @param throwValidationErrors
	 *            flag to indicate if Exception should be thrown in case of validation errors. Or continue processing
	 *            the records till the end even with the validation errors.
	 * @throws IOException
	 */
	public CSVFileIterator(Reader reader, Class<T> cls, CSVConfig config, boolean throwValidationErrors) throws IOException {
		super(cls, throwValidationErrors);
		this.reader = new CSVReader(reader, config.getSeparator(), config.getQuoteChar(), config.getEscape(), config.getSkipLine(), config.getStrictQuote(), config.getIgnoreLeadingWhiteSpaces());

		if (reader != null) {
			String[] tokens = this.reader.readNext();
			if (tokens != null) {
				for (String token : tokens) {
					indexMappedHeaders.add(token.trim());
				}
			}
		}
	}

	/**
	 * This method will read csv file line by line and convert tokens which are separated by "," into list of tokens
	 * mapped to header
	 * 
	 * @throws IOException
	 *             exception
	 */
	protected Map<String, String> readTokens() throws IOException {
		Map<String, String> colMapedTokens = null;
		if (reader != null) {
			String[] tokens = reader.readNext();
			if (tokens != null) {
				colMapedTokens = new HashMap<String, String>();
				for (int index = 0; index < tokens.length; index++) {
					colMapedTokens.put(indexMappedHeaders.get(index), tokens[index]);
				}
			}
			rowcount++;
			return colMapedTokens;
		}
		return null;
	}

}
