package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;

public interface ErrorTextBoxResources extends ClientBundle{
	public static ErrorTextBoxResources	INSTANCE	= GWT.create(ErrorTextBoxResources.class);

	@Source("ErrorTextBoxCss.css")
	ErrorTextBoxCss css();
	
	@Source("close.png")
	ImageResource closeBtn();
	
	@Source("warning.png")
	ImageResource warning();
	
	
}
