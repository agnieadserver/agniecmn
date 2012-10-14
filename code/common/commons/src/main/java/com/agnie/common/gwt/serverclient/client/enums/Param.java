package com.agnie.common.gwt.serverclient.client.enums;

public enum Param {

	RANGE_START("start-index"), RANGE_END("end-index"), SEARCH_KEYWORD("search-key"), APP_USER_STATUS_FILTER("app-user-status-filter");

	private String	key;

	private Param(String key) {
		this.key = key;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

}