package com.agnie.gwt.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class TabletListView extends Composite implements ListView {
	interface MyUiBinder extends UiBinder<Widget, TabletListView> {
	}

	private static MyUiBinder	uiBinder	= GWT.create(MyUiBinder.class);

	public TabletListView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setPresenter(Presenter presenter) {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub

	}
}
