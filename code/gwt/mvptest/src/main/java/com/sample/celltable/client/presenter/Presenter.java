package com.sample.celltable.client.presenter;

import com.agnie.gwt.common.client.mvp.Place;

public abstract interface Presenter extends com.agnie.gwt.common.client.mvp.Presenter {

	public void setPlace(Place<PlaceToken> token);

}
