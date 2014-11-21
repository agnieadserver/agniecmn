/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.util.tablefile;

import au.com.bytecode.opencsv.CSVParser;

/**
 * @author Pandurang Patil 19-Nov-2014
 *
 */
public class CSVConfig {
	private Character	separator					= CSVParser.DEFAULT_SEPARATOR;
	private Character	quoteChar					= CSVParser.DEFAULT_QUOTE_CHARACTER;
	private Character	escape						= CSVParser.DEFAULT_ESCAPE_CHARACTER;
	private Integer		skipLine					= 0;
	private Boolean		strictQuote					= CSVParser.DEFAULT_STRICT_QUOTES;
	private Boolean		ignoreLeadingWhiteSpaces	= CSVParser.DEFAULT_IGNORE_LEADING_WHITESPACE;

	public CSVConfig() {
		super();
	}

	/**
	 * @param separator
	 */
	public CSVConfig(Character separator) {
		super();
		this.separator = separator;
	}

	/**
	 * @param separator
	 * @param quoteChar
	 */
	public CSVConfig(Character separator, Character quoteChar) {
		super();
		this.separator = separator;
		this.quoteChar = quoteChar;
	}

	/**
	 * @param separator
	 * @param quoteChar
	 * @param skipLine
	 */
	public CSVConfig(Character separator, Character quoteChar, Integer skipLine) {
		super();
		this.separator = separator;
		this.quoteChar = quoteChar;
		this.skipLine = skipLine;
	}

	/**
	 * @param separator
	 * @param quoteChar
	 * @param escape
	 * @param skipLine
	 * @param strictQuote
	 * @param ignoreLeadingWhiteSpaces
	 */
	public CSVConfig(Character separator, Character quoteChar, Character escape, Integer skipLine, Boolean strictQuote, Boolean ignoreLeadingWhiteSpaces) {
		super();
		this.separator = separator;
		this.quoteChar = quoteChar;
		this.escape = escape;
		this.skipLine = skipLine;
		this.strictQuote = strictQuote;
		this.ignoreLeadingWhiteSpaces = ignoreLeadingWhiteSpaces;
	}

	/**
	 * @return the separator
	 */
	public Character getSeparator() {
		return separator;
	}

	/**
	 * @param separator
	 *            the separator to set
	 */
	public void setSeparator(Character separator) {
		this.separator = separator;
	}

	/**
	 * @return the quoteChar
	 */
	public Character getQuoteChar() {
		return quoteChar;
	}

	/**
	 * @param quoteChar
	 *            the quoteChar to set
	 */
	public void setQuoteChar(Character quoteChar) {
		this.quoteChar = quoteChar;
	}

	/**
	 * @return the escape
	 */
	public Character getEscape() {
		return escape;
	}

	/**
	 * @param escape
	 *            the escape to set
	 */
	public void setEscape(Character escape) {
		this.escape = escape;
	}

	/**
	 * @return the skipLine
	 */
	public Integer getSkipLine() {
		return skipLine;
	}

	/**
	 * @param skipLine
	 *            the skipLine to set
	 */
	public void setSkipLine(Integer skipLine) {
		this.skipLine = skipLine;
	}

	/**
	 * @return the strictQuote
	 */
	public Boolean getStrictQuote() {
		return strictQuote;
	}

	/**
	 * @param strictQuote
	 *            the strictQuote to set
	 */
	public void setStrictQuote(Boolean strictQuote) {
		this.strictQuote = strictQuote;
	}

	/**
	 * @return the ignoreLeadingWhiteSpaces
	 */
	public Boolean getIgnoreLeadingWhiteSpaces() {
		return ignoreLeadingWhiteSpaces;
	}

	/**
	 * @param ignoreLeadingWhiteSpaces
	 *            the ignoreLeadingWhiteSpaces to set
	 */
	public void setIgnoreLeadingWhiteSpaces(Boolean ignoreLeadingWhiteSpaces) {
		this.ignoreLeadingWhiteSpaces = ignoreLeadingWhiteSpaces;
	}

}
