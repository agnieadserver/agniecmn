package com.demoInjector.agnie.client.presenter;

import com.agnie.gwt.common.client.mvp.Place;
import com.demoInjector.agnie.client.factory.RPCClientfactory;
import com.demoInjector.agnie.client.factory.Viewfactory;
import com.google.inject.Inject;

public abstract class RootPresenter implements Presenter {

	@Inject
	Viewfactory					viewFactory;

	@Inject
	RPCClientfactory			rpcClientFactory;

	protected Place<PlaceToken>	place;

	@Override
	public void postRender() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkPermission(String permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPlace(Place<PlaceToken> place) {
		this.place = place;
	}

}
