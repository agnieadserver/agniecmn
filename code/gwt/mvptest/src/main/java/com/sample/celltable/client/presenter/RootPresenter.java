package com.sample.celltable.client.presenter;

import com.agnie.gwt.common.client.mvp.Place;
import com.google.inject.Inject;
import com.sample.celltable.client.factory.ClientFactory;
import com.sample.celltable.client.factory.ViewFactory;

public abstract class RootPresenter implements Presenter {

	@Inject
	protected ClientFactory		clientFactory;

	@Inject
	protected ViewFactory		viewFactory;

	protected Place<PlaceToken>	place;

	
	@Override
	public boolean checkPermission(String permission) {
		return false;
	}

	@Override
	public void postRender() {

	}

	@Override
	public void setPlace(Place<PlaceToken> token) {
		this.place = token;
	}
}
