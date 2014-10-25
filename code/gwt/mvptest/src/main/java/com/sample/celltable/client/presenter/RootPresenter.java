package com.sample.celltable.client.presenter;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.sample.celltable.client.factory.ClientFactory;
import com.sample.celltable.client.factory.ViewFactory;

public abstract class RootPresenter implements Presenter {

	@Inject
	protected ClientFactory	clientFactory;

	@Inject
	protected ViewFactory	viewFactory;

	@Override
	public void render() {

		GWT.log("INside Parent Class Constructor");
		RootPanel.get("Header").clear();

		RootPanel.get("Header").add(viewFactory.getHeader());
		RootPanel.get("footer").clear();
		RootPanel.get("footer").add(viewFactory.getFooter());
	}

}
