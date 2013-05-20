package com.agnie.gwt.client.injector;

import com.google.inject.Singleton;

@Singleton
public class ValueProvider {

	private String	value;

	public void set(String value) {
		this.value = value;
	}

	public String get() {
		return value;
	}
}
