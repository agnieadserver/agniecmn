package com.sample.celltable.client.presenter;

import java.util.List;

import com.agnie.gwt.common.client.mvp.Place;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sample.celltable.client.injector.SampleInjector;
import com.sample.celltable.client.ui.ListPage;
import com.sample.celltable.shared.USerDataBase.User;

@Singleton
public class ListPresenter extends RootPresenter {

	AsyncDataProvider<User>	dataProvider	= null;

	@Inject
	SampleInjector			injector;

	private void loadDataInCellTable() {

		GWT.log("Inside USerData BInder LoadData Method()");
		dataProvider = new AsyncDataProvider<User>() {
			@Override
			protected void onRangeChanged(HasData<User> display) {

				final Range rangeList = display.getVisibleRange();

				final int start = rangeList.getStart();
				int range = start + rangeList.getLength();
				GWT.log("Start Index :" + start + " End Index : " + range);
				clientFactory.getGreetingService().listUserData(start, range, new AsyncCallback<List<User>>() {
					@Override
					public void onSuccess(List<User> result) {

						dataProvider.updateRowData(start, result);
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error @ list User data" + ((Exception) caught).getMessage());

					}
				});

			}
		};

		dataProvider.addDataDisplay(viewFactory.getListPage().getCellTable());

	}

	public void showForm() {

		GWT.log("Set Create Screen");

		Place<PlaceToken> place = new Place<PlaceToken>(PlaceToken.CREATE);
		injector.getAppController().getPlaceManager().changePlace(place);
	}

	@Override
	public boolean go() {
		loadDataInCellTable();
		ListPage lPage = viewFactory.getListPage();
		lPage.setListPresenter(this);
		RootPanel.get("container").clear();
		RootPanel.get("container").add(lPage);

		return true;
	}

}
