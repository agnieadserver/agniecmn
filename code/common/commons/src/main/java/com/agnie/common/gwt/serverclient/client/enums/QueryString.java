package com.agnie.common.gwt.serverclient.client.enums;

public enum QueryString {
	SOURCE("source"), SESSION("sessionid"), GWT_DEV_MODE("gwt.codesvr"), LOCALE("locale"), QUESTION_MARK("?"), AMPERSAND("&"), HASH("#"), USER_NAME("user"), DOMAIN("domain"), SELECTED_DOMAIN(
			"sel-domain"), HISTORY_HASH("history-token"), SELECTED_CONTEXT("sel-context"), CONTEXT("context");

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
