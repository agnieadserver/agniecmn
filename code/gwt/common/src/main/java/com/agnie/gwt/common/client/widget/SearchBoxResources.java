package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface SearchBoxResources extends ClientBundle {
	public static SearchBoxResources	INSTANCE	= GWT.create(SearchBoxResources.class);

	@Source("SearchBoxCss.css")
	SearchBoxCss css();
}
