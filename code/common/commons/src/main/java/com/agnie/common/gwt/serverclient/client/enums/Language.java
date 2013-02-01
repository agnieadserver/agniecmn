package com.agnie.common.gwt.serverclient.client.enums;

import java.io.Serializable;

import com.agnie.common.gwt.serverclient.client.renderer.Title;

/**
 * There is a corresponding Language enum in Persistence client module. If you make some changes here you need to also
 * copy the same changes over there also. We had to maintain these enums separately as there is an issue in
 * mvn-helper-plugin which generates RequestFactory interfaces.
 * 
 */
public enum Language implements Serializable, Title {
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

	@Override
	public String getTitle() {
		return label;
	}
}
