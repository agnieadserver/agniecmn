package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface TextPasswordResources extends ClientBundle {
	public static TextPasswordResources	INSTANCE	= GWT.create(TextPasswordResources.class);

	@Source("TextPasswordCss.css")
	TextPasswordCss css();
}
