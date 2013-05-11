package com.agnie.gwt.client;

import com.agnie.gwt.client.injector.MVPInjector;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class samples implements EntryPoint {
	private MVPInjector	injector	= GWT.create(MVPInjector.class);

	public void onModuleLoad() {
		SampleAppController appController = injector.getAppController();
		appController.go();
	}
}
