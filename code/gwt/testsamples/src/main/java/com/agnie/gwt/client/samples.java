package com.agnie.gwt.client;

import com.agnie.gwt.client.renderer.RendererSample;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class samples implements EntryPoint {
	public void onModuleLoad() {
		RendererSample rendSample = new RendererSample();
		RootPanel.get().add(rendSample);
	}
}
