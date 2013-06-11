package com.agnie.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class samples implements EntryPoint {
	private PlatformFactory	platformFactory	= GWT.create(PlatformFactory.class);

	public void onModuleLoad() {
		SampleAppController appController = platformFactory.getInjector().getAppController();
		appController.setViewFactory(platformFactory.getViewFactory());
		appController.go();
	}
}
