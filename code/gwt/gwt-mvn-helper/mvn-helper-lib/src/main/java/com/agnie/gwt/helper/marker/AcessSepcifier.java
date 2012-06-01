package com.agnie.gwt.helper.marker;

public enum AcessSepcifier {

	PUBLIC("public"), PRIVATE("private"), PROTECTED("protected"), DEFAULT(""), FINAL("final"), STATIC("static"), NATIVE("native");

	private String	keyword;

	private AcessSepcifier(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

}
