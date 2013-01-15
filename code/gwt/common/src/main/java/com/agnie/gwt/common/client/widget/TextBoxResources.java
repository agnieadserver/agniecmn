package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;

public interface TextBoxResources extends ClientBundle {
	public static TextBoxResources	INSTANCE	= GWT.create(TextBoxResources.class);

	@Source("TextBoxCss.css")
	TextBoxCss css();

	@Source("warning-small.png")
	ImageResource warning();
	
	@Source("cross-small.png")
	ImageResource closeBtn();

	@Source("textBoxErrorTriangle.png")
	ImageResource textBoxTriangle();
}
