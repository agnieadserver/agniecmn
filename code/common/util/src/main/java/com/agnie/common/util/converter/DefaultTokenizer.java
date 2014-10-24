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
