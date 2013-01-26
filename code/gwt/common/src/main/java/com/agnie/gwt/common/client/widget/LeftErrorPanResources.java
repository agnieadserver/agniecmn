package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;

public interface LeftErrorPanResources extends ClientBundle {
	public static LeftErrorPanResources	INSTANCE	= GWT.create(LeftErrorPanResources.class);

	@Source("LeftErrorPanCss.css")
	LeftErrorPanCss css();

	@Source("warning-small.png")
	ImageResource warning();
	
	@Source("cross-small.png")
	ImageResource closeBtn();

	@Source("LeftErrorPanTriangle.png")
	ImageResource textBoxTriangle();

}
