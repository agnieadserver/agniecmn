package com.agnie.trial.guice.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Samples implements EntryPoint {

	public void onModuleLoad() {
		RootPanel.get().add(new HTML("This is just a sample"));
	}
}
