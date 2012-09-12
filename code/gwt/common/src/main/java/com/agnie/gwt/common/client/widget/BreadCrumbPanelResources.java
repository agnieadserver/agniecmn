package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;

public interface BreadCrumbPanelResources extends ClientBundle{

	public static BreadCrumbPanelResources	INSTANCE	= GWT.create(BreadCrumbPanelResources.class);

	@Source("BreadCrumbPanelCss.css")
	BreadCrumbPanelCss css();

	@Source("breadcrumb_sepatator.png")
	ImageResource breadCrumbSep();
}
