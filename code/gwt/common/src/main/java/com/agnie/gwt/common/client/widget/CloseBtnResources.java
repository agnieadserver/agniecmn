package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface CloseBtnResources extends ClientBundle {
	public static CloseBtnResources	INSTANCE	= GWT.create(CloseBtnResources.class);

	@Source("CloseBtnCss.css")
	CloseBtnCss css();
	
	@Source("cross2.png")
	ImageResource crossImg();
	
}
