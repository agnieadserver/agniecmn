package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.resources.client.ImageResource;

public interface AccountResources extends ClientBundle {
	public static AccountResources	INSTANCE	= GWT.create(AccountResources.class);

	@Source("AccountCss.css")
	AccountCss css();
	
	@Source("arrowDark.png")
	DataResource arrowDark();
	
	@Source("arrow.png")
	DataResource arrow();
	
	
	@Source("person50x50.png")
	ImageResource person();
}
