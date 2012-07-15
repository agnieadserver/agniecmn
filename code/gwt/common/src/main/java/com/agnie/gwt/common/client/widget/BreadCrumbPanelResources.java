package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface BreadCrumbPanelResources extends ClientBundle{

	public static BreadCrumbPanelResources	INSTANCE	= GWT.create(BreadCrumbPanelResources.class);

	@Source("BreadCrumbPanelCss.css")
	BreadCrumbPanelCss css();


}
