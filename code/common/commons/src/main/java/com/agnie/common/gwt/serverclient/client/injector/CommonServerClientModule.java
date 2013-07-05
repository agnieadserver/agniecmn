package com.agnie.common.gwt.serverclient.client.injector;

import com.agnie.common.gwt.serverclient.client.helper.URLInfo;
import com.agnie.common.gwt.serverclient.client.helper.URLInfoImpl;
import com.google.gwt.inject.client.AbstractGinModule;

public class CommonServerClientModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(URLInfo.class).to(URLInfoImpl.class);
	}
}
