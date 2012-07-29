package com.agnie.gwt.client;

import com.agnie.gwt.client.renderer.UserRenderer;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class samples implements EntryPoint {
	public void onModuleLoad() {
		GWT.log("IN onmoduleLoad Start.");
		//RendererSample rendSample = new RendererSample();
		UserRenderer ur=new UserRenderer();
		
		GWT.log("IN onmoduleLoad Mid.");
		RootPanel.get().add(ur);
		GWT.log("IN onmoduleLoad End.");
	}
}
