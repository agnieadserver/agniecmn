/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.util.converter;

/**
 * Default tokenizer which will separate the tokens by separator
 * 
 */
public class DefaultTokenizer extends Tokenizer {

	public DefaultTokenizer(String tokenSeparator) {
		super(tokenSeparator);
	}

	@Override
	public String[] tokenize(String rawToken) {
		if (rawToken != null) {
			return rawToken.split(tokenSeparator);
		}
		return null;
	}
}
