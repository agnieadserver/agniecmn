package com.agnie.gwt.common.client.base;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface BasePageResources extends ClientBundle {

	public static BasePageResources	INSTANCE	= GWT.create(BasePageResources.class);

	@Source("BasePageCss.css")
	BasePageCss css();

	@Source("header_wrap.jpg")
	ImageResource headerWrap();
}
