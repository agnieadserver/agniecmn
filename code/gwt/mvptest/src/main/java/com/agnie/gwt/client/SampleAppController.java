package com.agnie.gwt.client;

import com.agnie.gwt.client.presenter.ListPresenter;
import com.agnie.gwt.common.client.mvp.AppController;
import com.agnie.gwt.common.client.mvp.Place;
import com.agnie.gwt.common.client.mvp.Presenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTMLPanel;

public class SampleAppController extends AppController<PlaceToken> {
	private ViewFactory	viewFactory	= SuperFactory.getInstance().viewFactory();

	public SampleAppController() {
		super(PlaceToken.class);
	}

	@Override
	protected PlaceToken getDefaultPlace() {
		return PlaceToken.LIST;
	}

	@Override
	protected Presenter getPresenterForPlace(Place<PlaceToken> place) {
		GWT.log("getPresenterForPlace==");
		Presenter presenter = null;
		if (place != null) {
			switch (place.getPlace()) {
			case LIST:
				presenter = new ListPresenter(viewFactory);
				break;
			case CREATE:
				// presenter = new CreateNewAppPresenter(eventBus, viewFactory, clientFactory);
				break;
			default:
				// TODO: Need to add default case to show error page.
			}
		}
		return presenter;
	}

	@Override
	protected HTMLPanel getMainContentRootPanel() {
		return viewFactory.getMainContentPanel();
	}

}
