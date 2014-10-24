/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.util.converter;

/**
 * Abstract definition for tokenizer, to tokenize single column collection type
 * 
 */
public abstract class Tokenizer {

	protected String	tokenSeparator;

	/**
	 * @param tokenSeparator
	 */
	public Tokenizer(String tokenSeparator) {
		super();
		this.tokenSeparator = tokenSeparator;
	}

	public abstract String[] tokenize(String rawToken);

	/**
	 * @return the tokenSeparator
	 */
	public String getTokenSeparator() {
		return tokenSeparator;
	}

}
