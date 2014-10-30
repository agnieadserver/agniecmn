package com.demoInjector.agnie.client;

import com.demoInjector.agnie.client.injector.DemoInjector;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DemoInject implements EntryPoint {

	DemoInjector	injector	= GWT.create(DemoInjector.class);

	public void onModuleLoad() {

		injector.getApController().go();
	}
}
