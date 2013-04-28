package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface DandDResources extends ClientBundle {
	public static DandDResources	INSTANCE	= GWT.create(DandDResources.class);

	@Source("DandDCss.css")
	DandDCss css();

	@Source("cross2.png")
	ImageResource crossImg();

}
