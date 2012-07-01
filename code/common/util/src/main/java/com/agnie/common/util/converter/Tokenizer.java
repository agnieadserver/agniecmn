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
