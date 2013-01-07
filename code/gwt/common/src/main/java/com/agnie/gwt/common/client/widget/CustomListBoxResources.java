package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface CustomListBoxResources extends ClientBundle {
	public static CustomListBoxResources	INSTANCE	= GWT.create(CustomListBoxResources.class);

	@Source("CustomListBoxCss.css")
	CustomListBoxCss css();

	@Source("arrowDark.png")
	ImageResource arrowDarkImg();
	
	@Source("arrowDarkUp.png")
	ImageResource arrowDarkUpImg();

}
