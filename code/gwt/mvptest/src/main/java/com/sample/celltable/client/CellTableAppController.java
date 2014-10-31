package com.sample.celltable.client;

import com.agnie.gwt.common.client.mvp.AppController;
import com.agnie.gwt.common.client.mvp.Place;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sample.celltable.client.presenter.CreatePresenter;
import com.sample.celltable.client.presenter.ListPresenter;
import com.sample.celltable.client.presenter.PlaceToken;
import com.sample.celltable.client.presenter.Presenter;

@Singleton
public class CellTableAppController extends AppController<PlaceToken> {

	public CellTableAppController() {
		super(PlaceToken.class);

	}

	@Inject
	ListPresenter	userPresenter;

	@Inject
	CreatePresenter	formPresenter;

	@Override
	protected HTMLPanel getMainContentRootPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected PlaceToken getDefaultPlace() {

		return PlaceToken.LIST;
	}

	@Override
	protected Presenter getPresenterForPlace(Place<PlaceToken> place) {

		Presenter presenter = null;

		switch (place.getPlace()) {
		case CREATE:

			presenter = formPresenter;

			break;
		case LIST:

			presenter = userPresenter;

			break;

		default:
			break;
		}
		presenter.setPlace(place);
		return presenter;
	}
}
