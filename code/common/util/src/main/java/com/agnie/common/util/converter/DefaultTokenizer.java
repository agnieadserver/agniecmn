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
