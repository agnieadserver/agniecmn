package com.agnie.gwt.client.presenter;

import com.agnie.gwt.client.ViewFactory;
import com.agnie.gwt.common.client.mvp.Presenter;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;

public class BasePresenter implements Presenter {
	protected ViewFactory	viewFactory;

	public BasePresenter(ViewFactory viewFactory) {
		super();
		this.viewFactory = viewFactory;
	}

	public void go() {
		HTMLPanel content = viewFactory.getMainContentPanel();
		content.clear();
		content.add(new Label(viewFactory.getMainHeading()));
	}

}
