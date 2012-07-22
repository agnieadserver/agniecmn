package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;

public interface SuggestionBoxResources extends ClientBundle {
	public static SuggestionBoxResources	INSTANCE	= GWT.create(SuggestionBoxResources.class);

	@Source("SuggestionBoxCss.css")
	SuggestionBoxCss css();

	@Source("search.png")
	DataResource search();
}
