package com.agnie.gwt.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class HomeContentView extends Composite {
	interface MyUiBinder extends UiBinder<Widget, HomeContentView> {
	}

	private static MyUiBinder	uiBinder	= GWT.create(MyUiBinder.class);

	public HomeContentView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
