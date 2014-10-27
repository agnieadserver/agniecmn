/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.demoInjector.agnie.client.presenter;

import com.agnie.gwt.common.client.mvp.Place;
import com.demoInjector.agnie.client.injector.DemoInjector;
import com.demoInjector.agnie.client.ui.CreateUser;
import com.demoInjector.agnie.shared.USerDataBase.User;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author rajgaurav
 *
 */

@Singleton
public class CreateuserPresenter extends RootPresenter {

	@Inject
	DemoInjector	injector;

	@Override
	public boolean go() {
		CreateUser cPage = viewFactory.getCreateUserPage();
		cPage.setPresenter(this);
		RootPanel.get("container").clear();
		RootPanel.get("container").add(cPage);
		return true;
	}

	public void AddUsers(User user) {

		rpcClientFactory.getRpcService().addUser(user, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				// History.newItem("user");

				showList();

			}

			@Override
			public void onFailure(Throwable caught) {

			}
		});
	}

	public void showList() {
		Place<PlaceToken> place = new Place<PlaceToken>(PlaceToken.LIST);
		injector.getApController().getPlaceManager().changePlace(place);
	}

}
