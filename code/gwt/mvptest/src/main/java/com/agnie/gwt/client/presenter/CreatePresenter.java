package com.agnie.gwt.client.presenter;

import com.agnie.gwt.client.ui.CreateView.Presenter;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Singleton;

@Singleton
public class CreatePresenter extends BasePresenter implements Presenter {

	@Override
	public boolean go() {
		super.go();
		HTMLPanel content = viewFactory.getMainContentPanel();
		content.add(new Label("Create Page"));
		return true;
	}

}
