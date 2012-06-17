package com.agnie.gwt.common.client.enums;

import java.io.Serializable;

public enum Language implements Serializable {
	MARATHI("mr", "मराठी"), HINDI("hi", "हिन्दी"), ENGLISH("en", "English");
	private String	code;
	private String	label;

	private Language(String code, String label) {
		this.code = code;
		this.label = label;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

}
