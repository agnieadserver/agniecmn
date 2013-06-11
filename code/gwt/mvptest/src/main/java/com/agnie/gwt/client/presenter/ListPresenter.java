package com.agnie.gwt.client.presenter;

import java.util.List;

import com.agnie.gwt.client.Person;
import com.agnie.gwt.client.injector.ValueProvider;
import com.agnie.gwt.client.ui.ListView;
import com.agnie.gwt.client.ui.ListView.Presenter;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ListPresenter extends BasePresenter implements Presenter {
	@Inject
	ValueProvider	stringProvider;

	public ListPresenter() {
		GWT.log("List Presenter constructor");
	}

	@Override
	public boolean go() {
		super.go();
		GWT.log("String value provider => " + stringProvider.get());
		HTMLPanel content = viewFactory.getMainContentPanel();
		ListView lview = viewFactory.getListView();
		lview.init();
		content.add(lview);
		return true;
	}

	public void fillData(List<Person> list) {
		for (int index = 0; index < 7; index++) {
			list.add(new Person("fname " + index, "lname " + index, "age " + index, "emailid" + index + "@test.com"));
		}
	}
}
