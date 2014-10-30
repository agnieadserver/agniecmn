package com.demoInjector.agnie.client.presenter;

import com.agnie.gwt.common.client.mvp.Place;

public interface Presenter extends com.agnie.gwt.common.client.mvp.Presenter {

	public void setPlace(Place<PlaceToken> place);
}
