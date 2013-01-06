package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface CustomMenuPanResources extends ClientBundle {
	public static CustomMenuPanResources	INSTANCE	= GWT.create(CustomMenuPanResources.class);

	@Source("CustomMenuPanCss.css")
	CustomMenuPanCss css();

}
