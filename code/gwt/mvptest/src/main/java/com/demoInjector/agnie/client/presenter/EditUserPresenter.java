/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.demoInjector.agnie.client.presenter;

import java.util.List;

import com.agnie.gwt.common.client.mvp.Place;
import com.demoInjector.agnie.client.injector.DemoInjector;
import com.demoInjector.agnie.client.ui.EditUser;
import com.demoInjector.agnie.shared.USerDataBase.User;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author rajgaurav
 *
 */
@Singleton
public class EditUserPresenter extends RootPresenter {

	@Inject
	DemoInjector	injector;

	@Override
	public boolean go() {
		int id = Integer.parseInt(this.place.get("id"));
		EditUser editPage = viewFactory.getEditPage();
		editPage.setEdituserPresenter(this);
		RootPanel.get("container").clear();
		RootPanel.get("container").add(editPage);
		getUserDetails(id);
		return true;
	}

	public void getUserDetails(int id) {
		rpcClientFactory.getRpcService().getUser(id, new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(User result) {
				viewFactory.getEditPage().setEdituser(result);
			}
		});
	}

	public void showList() {
		Place<PlaceToken> place = new Place<PlaceToken>(PlaceToken.LIST);
		injector.getApController().getPlaceManager().changePlace(place);
	}

	public void Edituser(User user) {

		rpcClientFactory.getRpcService().editUser(user, new AsyncCallback<List<User>>() {

			@Override
			public void onSuccess(List<User> result) {
				Window.alert("Record Editaed");
				viewFactory.getEditPage().setEmpty();
				showList();
			}

			@Override
			public void onFailure(Throwable caught) {

				Window.alert("Error on Edit User Presnter" + caught.getMessage());

			}
		});
	}

}
