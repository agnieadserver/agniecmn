package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface PageTitleResources extends ClientBundle {
	public static PageTitleResources	INSTANCE	= GWT.create(PageTitleResources.class);

	@Source("PageTitleCss.css")
	PageTitleCss css();
}
