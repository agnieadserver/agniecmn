package com.sample.celltable.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sample.celltable.client.presenter.CreatePresenter;
import com.sample.celltable.client.presenter.RootPresenter;
import com.sample.celltable.client.presenter.ListPresenter;

@Singleton
public class AppController implements ValueChangeHandler<String> {

	@Inject
	ListPresenter	userPresenter;

	@Inject
	CreatePresenter			formPresenter;

	public AppController() {
		History.addValueChangeHandler(this);
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String nameEvent = event.getValue();

		RootPresenter presenter = null;

		if (nameEvent != null && nameEvent.equalsIgnoreCase("user")) {

			GWT.log("InSide User In AppControler");
			presenter = userPresenter;

		} else if (nameEvent != null && nameEvent.equalsIgnoreCase("form")) {

			presenter = formPresenter;
		}

		presenter.render();

	}

	public void go() {
		if (History.getToken().equals("")) {
			History.newItem("user");

		} else {
			History.fireCurrentHistoryState();
		}

	}
}
