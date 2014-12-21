/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.common.client.mvp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HTMLPanel;

public abstract class AppController<PLACE extends Enum<PLACE>> implements ValueChangeHandler<String> {

	protected Class<PLACE>			placeType;
	protected Place<PLACE>			lastPlace;
	protected PlaceManager<PLACE>	placeMgr;
	protected MainView				currentView;
	protected HandlerRegistration	defaultSubmitHandler;

	public AppController(Class<PLACE> placeType) {

		GWT.log(placeType.getName().toString() + "<--" + placeType);
		this.placeType = placeType;
		History.addValueChangeHandler(this);
		placeMgr = new PlaceManager<PLACE>(this, placeType);
		defaultSubmitHandler = Event.addNativePreviewHandler(submitHandler);
	}

	public PlaceManager<PLACE> getPlaceManager() {
		return placeMgr;
	}

	public void go() {
		if ("".equals(History.getToken())) {
			History.newItem(new Place<PLACE>(getDefaultPlace()).toString());
		} else {
			History.fireCurrentHistoryState();
		}
	}

	public void onValueChange(ValueChangeEvent<String> event) {
		if (currentView == null || currentView.shouldWeProceed()) {
			String token = event.getValue();
			GWT.log("AppContorller on value change Stringtoken==" + token);
			if (token != null) {
				Presenter presenter = getPresenterForPlace(placeMgr.getTokenToPlace(placeType, token));
				currentView = presenter.getMainView();
				if (presenter != null) {
					processRequest(presenter);
				}
			}
		} else {

			GWT.log("reached in Appcontroler class");
			// Just roll back the history token to the one from which page change action is initiated.
			History.newItem(lastPlace.toString(), false);
		}
	}

	public void processRequest(Presenter presenter) {
		presenter.go();
		presenter.postRender();
	}

	public void setLastPlace(Place<PLACE> place) {

		this.lastPlace = place;
	}

	private NativePreviewHandler	submitHandler	= new NativePreviewHandler() {

														@Override
														public void onPreviewNativeEvent(NativePreviewEvent event) {
															if (event.getTypeInt() == Event.ONKEYPRESS) {
																if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
																	GWT.log("Default Enter key press event occured...");
																	if (currentView != null) {
																		currentView.defaultEnterPressed();
																	}
																}
															}
														}
													};

	protected abstract HTMLPanel getMainContentRootPanel();

	protected abstract PLACE getDefaultPlace();

	protected abstract Presenter getPresenterForPlace(Place<PLACE> place);

}
