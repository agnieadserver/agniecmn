package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface TabBarResources extends ClientBundle {

	public static TabBarResources	INSTANCE	= GWT.create(TabBarResources.class);

	@Source("TabBarCss.css")
	TabBarCss css();

}
