package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface TextBoxResources extends ClientBundle {
	public static TextBoxResources	INSTANCE	= GWT.create(TextBoxResources.class);

	@Source("TextBoxCss.css")
	TextBoxCss css();

}
