package com.agnie.gwt.client.ui;

import com.agnie.gwt.common.client.widget.FormFieldContainer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class HomeContentView extends Composite {
	interface MyUiBinder extends UiBinder<Widget, HomeContentView> {
	}

	private static MyUiBinder	uiBinder	= GWT.create(MyUiBinder.class);

	@UiField
	FormFieldContainer errorTest;
	public HomeContentView() {
		initWidget(uiBinder.createAndBindUi(this));
		errorTest.setError("ThisField is required", true);
	}

}
