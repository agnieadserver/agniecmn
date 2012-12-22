package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface MenuPanResources extends ClientBundle {
	public static MenuPanResources	INSTANCE	= GWT.create(MenuPanResources.class);

	@Source("MenuPanCss.css")
	MenuPanCss css();
	
}
