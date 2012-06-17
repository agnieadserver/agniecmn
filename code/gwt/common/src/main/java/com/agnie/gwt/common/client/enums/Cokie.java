package com.agnie.gwt.common.client.enums;

public enum Cokie {
	AUTH("OZTU"), STATUS("status"), USER("user");

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
