package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface SelectUnselectResources extends ClientBundle {
	public static SelectUnselectResources	INSTANCE	= GWT.create(SelectUnselectResources.class);

	@Source("SelectUnselectCss.css")
	SelectUnselectCss css();
}
