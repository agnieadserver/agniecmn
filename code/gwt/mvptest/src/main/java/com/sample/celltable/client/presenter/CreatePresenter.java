package com.sample.celltable.client.presenter;

import com.agnie.gwt.common.client.mvp.Place;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sample.celltable.client.injector.SampleInjector;
import com.sample.celltable.client.ui.CreatePage;
import com.sample.celltable.shared.USerDataBase.User;

@Singleton
public class CreatePresenter extends RootPresenter {

	@Inject
	SampleInjector	injector;

	public void showUSerDataForm(User user) {
		clientFactory.getGreetingService().addUser(user, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				Place<PlaceToken> place = new Place<PlaceToken>(PlaceToken.LIST);
				injector.getAppController().getPlaceManager().changePlace(place);
				;
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error @ Add User Page");
			}
		});

	}

	@Override
	public boolean go() {
		GWT.log("Inside Create Presenter");

		CreatePage cPage = viewFactory.getCreatePage();
		cPage.setPresenter(this);
		RootPanel.get("container").clear();
		RootPanel.get("container").add(cPage);

		return true;
	}
}
