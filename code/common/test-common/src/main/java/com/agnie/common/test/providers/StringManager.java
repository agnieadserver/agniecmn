package com.agnie.common.test.providers;

import com.google.inject.Singleton;

/**
 * @author Pandurang Patil 10-Feb-2014
 * 
 */
@Singleton
public class StringManager {

	private String	value;

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
