package com.agnie.gwt.client;

import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class samples implements EntryPoint {
	public void onModuleLoad() {
		SampleAppController appController = new SampleAppController();
		appController.go();
	}
}
