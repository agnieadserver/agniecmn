/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
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
		this(stream, cls, false);
	}

	public CSVFileIterator(InputStream stream, Class<T> cls, boolean throwValidationErrors) throws IOException {
		this(new InputStreamReader(stream), cls, throwValidationErrors);
	}

	public CSVFileIterator(Reader reader, Class<T> cls) throws IOException {
		this(reader, cls, false);
	}

	public CSVFileIterator(Reader reader, Class<T> cls, boolean throwValidationErrors) throws IOException {
		super(cls, throwValidationErrors);
		this.reader = new CSVReader(reader, au.com.bytecode.opencsv.CSVParser.DEFAULT_SEPARATOR, au.com.bytecode.opencsv.CSVParser.DEFAULT_QUOTE_CHARACTER, true);

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
