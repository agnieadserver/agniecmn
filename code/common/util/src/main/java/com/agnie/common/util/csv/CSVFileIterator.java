package com.agnie.common.util.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

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

	protected static final Log	logger	= LogFactory.getLog(CSVFileIterator.class);
	private CSVReader			reader;

	public CSVFileIterator(InputStream stream, Class<T> cls) throws IOException {
		this(new InputStreamReader(stream), cls);
	}

	public CSVFileIterator(Reader reader, Class<T> cls) throws IOException {
		super(cls);
		this.reader = new CSVReader(reader, au.com.bytecode.opencsv.CSVParser.DEFAULT_SEPARATOR, au.com.bytecode.opencsv.CSVParser.DEFAULT_QUOTE_CHARACTER, true);
		init();
	}

	/**
	 * This method will read csv file line by line and convert tokens which are separated by "," into list of tokens
	 * 
	 * @throws IOException
	 */
	protected void readTokens() throws IOException {
		nextTokens = new ArrayList<String>();
		if (reader != null) {
			String[] tokens = reader.readNext();
			if (tokens != null) {
				for (String token : tokens) {
					nextTokens.add(token.trim());
				}
			} else {
				nextTokens = null;
			}
		}
		rowcount++;
	}

}
