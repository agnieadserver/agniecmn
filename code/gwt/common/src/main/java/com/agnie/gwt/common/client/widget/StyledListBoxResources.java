package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.resources.client.ImageResource;

public interface StyledListBoxResources extends ClientBundle {
	public static StyledListBoxResources	INSTANCE	= GWT.create(StyledListBoxResources.class);

	@Source("StyledListBoxCss.css")
	StyledListBoxCss css();
	
	@Source("arrowDark.png")
	DataResource arrowDark();
	
}
