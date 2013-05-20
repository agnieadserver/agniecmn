package com.agnie.gwt.client.presenter;

import com.agnie.gwt.client.ViewFactory;
import com.agnie.gwt.common.client.mvp.MainView;
import com.agnie.gwt.common.client.mvp.Presenter;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;

public class BasePresenter implements Presenter {

	@Inject
	protected ViewFactory	viewFactory;

	public void go() {
		HTMLPanel content = viewFactory.getMainContentPanel();
		content.clear();
		content.add(new Label(viewFactory.getMainHeading()));
	}

	public void postRender(MainView view) {
		// TODO Auto-generated method stub

	}

	public boolean checkPermission(String permission) {
		// TODO Auto-generated method stub
		return false;
	}

}
