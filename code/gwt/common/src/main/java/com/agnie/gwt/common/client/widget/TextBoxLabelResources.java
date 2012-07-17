package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface TextBoxLabelResources extends ClientBundle{
	public static TextBoxLabelResources	INSTANCE	= GWT.create(TextBoxLabelResources.class);

	@Source("TextBoxLabelCss.css")
	TextBoxLabelCss css();
}
