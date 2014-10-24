/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.client;

import com.agnie.gwt.client.injector.MVPInjector;
import com.agnie.gwt.client.injector.ValueProvider;
import com.agnie.gwt.client.presenter.CreatePresenter;
import com.agnie.gwt.client.presenter.ListPresenter;
import com.agnie.gwt.common.client.mvp.AppController;
import com.agnie.gwt.common.client.mvp.Place;
import com.agnie.gwt.common.client.mvp.Presenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class SampleAppController extends AppController<PlaceToken> {

	@Inject
	MVPInjector				injector;
	private ViewFactory		viewFactory;
	@Inject
	private ListPresenter	listPresenter;
	@Inject
	private CreatePresenter	createPresenter;
	@Inject
	ValueProvider			stringProvider;

	//
	// @Inject
	// @Named("Selected-String")
	// private ValueProvider valueProvider;

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
		stringProvider.set("Hi This works");
		// valueProvider.set("Hi This works");
		if (place != null) {
			switch (place.getPlace()) {
			case LIST:
				presenter = listPresenter;
				break;
			case CREATE:
				presenter = createPresenter;
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

	public void setViewFactory(ViewFactory viewFactory) {
		this.viewFactory = viewFactory;
		listPresenter.setViewFactory(viewFactory);
		createPresenter.setViewFactory(viewFactory);
	}

}
