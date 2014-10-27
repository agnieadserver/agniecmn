package com.demoInjector.agnie.client.presenter;

import java.util.List;

import com.demoInjector.agnie.client.ui.ListUsers;
import com.demoInjector.agnie.shared.USerDataBase.User;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.inject.Singleton;

@Singleton
public class ListuserPresenter extends RootPresenter {

	AsyncDataProvider<User>	dataProvider	= null;

	private void loadDataInCellTable() {
		dataProvider = new AsyncDataProvider<User>() {
			@Override
			protected void onRangeChanged(HasData<User> display) {

				final Range rangeList = display.getVisibleRange();

				final int start = rangeList.getStart();
				int range = start + rangeList.getLength();

				rpcClientFactory.getRpcService().listUserData(start, range, new AsyncCallback<List<User>>() {
					@Override
					public void onSuccess(List<User> result) {

						dataProvider.updateRowData(start, result);
					}

					@Override
					public void onFailure(Throwable caught) {
						GWT.log("@@@@@@@@@@Came " + caught.getMessage());
					}
				});
			}
		};

		if (viewFactory.getListuserPage() != null)
			dataProvider.addDataDisplay(viewFactory.getListuserPage().getCellTable());
		else {
			GWT.log("value is Null Here");
		}
	}

	@Override
	public boolean go() {

		loadDataInCellTable();
		ListUsers lPage = viewFactory.getListuserPage();
		lPage.setListPresenter(this);
		RootPanel.get("container").clear();
		RootPanel.get("container").add(lPage);
		return true;
	}

	public void showForm() {
		History.newItem("form");

	}

	public void showEditPage() {
		History.newItem("edit");
	}

}
