package com.agnie.gwt.client.injector;

import com.agnie.gwt.client.SampleAppController;
import com.google.gwt.inject.client.AbstractGinModule;

public class MVPModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(SampleAppController.class).asEagerSingleton();
	}

}
