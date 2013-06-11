package com.agnie.gwt.client.ui;

import java.util.List;

import com.agnie.gwt.client.Person;
import com.google.gwt.user.client.ui.IsWidget;

public interface ListView extends IsWidget {

	public void init();

	public interface Presenter extends com.agnie.gwt.common.client.mvp.Presenter {
		public void fillData(List<Person> list);
	}
}
