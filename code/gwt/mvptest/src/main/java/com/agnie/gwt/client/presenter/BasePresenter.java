package com.agnie.gwt.client.presenter;

import com.agnie.gwt.client.ViewFactory;
import com.agnie.gwt.common.client.mvp.MainView;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;

public class BasePresenter implements Presenter {

	protected ViewFactory	viewFactory;

	public boolean go() {
		HTMLPanel content = viewFactory.getMainContentPanel();
		content.clear();
		content.add(new Label(viewFactory.getMainHeading()));
		return true;
	}

	public void postRender(MainView view) {
		// TODO Auto-generated method stub

	}

	public boolean checkPermission(String permission) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setViewFactory(ViewFactory viewFactory) {
		this.viewFactory = viewFactory;
	}

}
