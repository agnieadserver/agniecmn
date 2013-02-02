package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface LoaderResources extends ClientBundle {
	public static LoaderResources	INSTANCE	= GWT.create(LoaderResources.class);

	@Source("getting-synced.gif")
	ImageResource gettingSynced();

	@Source("loader.gif")
	ImageResource defaultLoader();

	@Source("communicating.gif")
	ImageResource communicating();

	@Source("LoaderCss.css")
	LoaderCss css();
}
