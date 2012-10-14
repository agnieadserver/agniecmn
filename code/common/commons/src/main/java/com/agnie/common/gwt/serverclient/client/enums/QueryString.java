package com.agnie.common.gwt.serverclient.client.enums;

public enum QueryString {
	SOURCE("source"), SESSION("sessionid"), GWT_DEV_MODE("gwt.codesvr"), LOCALE("locale"), QUERY_STR_SPLITTER("?"), PARAM_SPLITTER("&"), PAGE_SECTION_LOCATOR("#"), USER_NAME("user"), DOMAIN("domain");

	private String	key;

	private QueryString(String key) {
		this.key = key;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

}
