package com.sample.celltable.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.sample.celltable.client.injector.SampleInjector;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CellTableEntryPoint implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or returns an error.
	 */
	// private static final String SERVER_ERROR = "An error occurred while "
	// + "attempting to contact the server. Please check your network "
	// + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	// private final GreetingServiceAsync greetingService = GWT
	// .create(GreetingService.class);

	// CelltableBinder cellTableBinder = new CelltableBinder();

	//

	private SampleInjector	injector	= GWT.create(SampleInjector.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		injector.getAppController().go();

	}

}
