package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface DecoratedPanelResources extends ClientBundle {
	public static DecoratedPanelResources	INSTANCE	= GWT.create(DecoratedPanelResources.class);

	@Source("DecoratedPanelCss.css")
	DecoratedPanelCss css();

	@Source("cross2.png")
	ImageResource closeBtn();

	@Source("min.png")
	ImageResource minBtn();

	@Source("max.png")
	ImageResource maxBtn();

}
