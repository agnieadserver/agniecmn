package com.agnie.gwt.client.presenter;

import java.util.List;

import com.agnie.gwt.client.Person;
import com.agnie.gwt.client.ui.ListView;
import com.agnie.gwt.client.ui.ListView.Presenter;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Singleton;

@Singleton
public class ListPresenter extends BasePresenter implements Presenter {

	@Override
	public void go() {
		super.go();
		HTMLPanel content = viewFactory.getMainContentPanel();
		ListView lview = viewFactory.getListView();
		lview.setPresenter(this);
		lview.init();
		content.add(lview);
	}

	public void fillData(List<Person> list) {
		for (int index = 0; index < 7; index++) {
			list.add(new Person("fname " + index, "lname " + index, "age " + index, "emailid" + index + "@test.com"));
		}
	}
}
