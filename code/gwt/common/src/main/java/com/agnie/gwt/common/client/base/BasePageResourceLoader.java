package com.agnie.gwt.common.client.base;

public class BasePageResourceLoader {

	static {
		BasePageResources.INSTANCE.css().ensureInjected();
	}
}
