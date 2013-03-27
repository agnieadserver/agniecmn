package com.agnie.gwt.client.presenter;

import com.agnie.gwt.client.ViewFactory;
import com.agnie.gwt.client.ui.CreateView.Presenter;
import com.google.gwt.user.client.ui.HTMLPanel;

public class CreatePresenter extends BasePresenter implements Presenter {

	public CreatePresenter(ViewFactory viewFactory) {
		super(viewFactory);
	}

	@Override
	public void go() {
		super.go();
		HTMLPanel content = viewFactory.getMainContentPanel();
		content.add(viewFactory.getCreateView());
	}

}
