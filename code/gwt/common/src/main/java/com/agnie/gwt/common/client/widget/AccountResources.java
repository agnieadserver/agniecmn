package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface AccountResources extends ClientBundle {
	public static AccountResources	INSTANCE	= GWT.create(AccountResources.class);

	@Source("AccountCss.css")
	AccountCss css();
	
	@Source("arrow.png")
	ImageResource arrowImg();
	
	@Source("arrowDark.png")
	ImageResource arrowDarkImg();
	
	@Source("person50x50.png")
	ImageResource person();
}
