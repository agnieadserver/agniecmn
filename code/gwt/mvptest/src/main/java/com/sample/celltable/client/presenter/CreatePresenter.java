package com.sample.celltable.client.presenter;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Singleton;
import com.sample.celltable.client.ui.CreatePage;
import com.sample.celltable.shared.USerDataBase.User;

@Singleton
public class CreatePresenter extends RootPresenter {

	@Override
	public void render() {
		super.render();

		CreatePage cPage = viewFactory.getCreatePage();
		cPage.setPresenter(this);
		RootPanel.get("container").clear();
		RootPanel.get("container").add(cPage);
	}

	public void showUSerDataForm(User user) {
		clientFactory.getGreetingService().addUser(user, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				History.newItem("user");
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error @ Add User Page");
			}
		});

	}

}
