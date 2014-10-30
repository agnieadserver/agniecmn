package com.demoInjector.agnie.client;

import com.agnie.gwt.common.client.mvp.AppController;
import com.agnie.gwt.common.client.mvp.Place;
import com.agnie.gwt.common.client.mvp.Presenter;
import com.demoInjector.agnie.client.presenter.CreateuserPresenter;
import com.demoInjector.agnie.client.presenter.EditUserPresenter;
import com.demoInjector.agnie.client.presenter.ListuserPresenter;
import com.demoInjector.agnie.client.presenter.PlaceToken;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DemoController extends AppController<PlaceToken> {

	public DemoController() {
		super(PlaceToken.class);
		// TODO Auto-generated constructor stub
	}

	@Inject
	CreateuserPresenter	userPresenter;

	@Inject
	ListuserPresenter	listPresenter;

	@Inject
	EditUserPresenter	edituserPresnter;

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
		com.demoInjector.agnie.client.presenter.Presenter presenter = null;
		if (place != null) {
			switch (place.getPlace()) {
			case LIST:
				presenter = listPresenter;
				break;
			case CREATE:
				presenter = userPresenter;
				break;
			case EDIT:
				presenter = edituserPresnter;
			default:
				// TODO: Need to add default case to show error page.
			}
		}
		presenter.setPlace(place);
		return presenter;
	}

}
