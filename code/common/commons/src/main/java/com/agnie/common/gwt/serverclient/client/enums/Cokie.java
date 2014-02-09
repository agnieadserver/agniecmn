package com.agnie.common.gwt.serverclient.client.enums;

public enum Cokie {
	AUTH("OZTU"), STATUS("status"), USER("user"), DNT_RMEMBER("DNRMB");

	private String	key;

	private Cokie(String key) {
		this.key = key;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
}
