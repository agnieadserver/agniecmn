package com.agnie.trial.guice.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Samples implements EntryPoint {

	public void onModuleLoad() {
		Cookies.setCookie("sir-name", "Patil");
		SampleServiceAsync service = GWT.create(SampleService.class);
		service.getMessage("Pranoti", new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				RootPanel.get().add(new HTML("Call was successfull => " + result));
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
		RootPanel.get().add(new HTML("This is just a sample"));
	}

}
