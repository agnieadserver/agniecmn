package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface MessagePanelResources extends ClientBundle {
	public static MessagePanelResources	INSTANCE	= GWT.create(MessagePanelResources.class);

	@Source("MessagePanelCss.css")
	MessagePanelCss css();

	@Source("error.png")
	ImageResource error();

	@Source("info.png")
	ImageResource info();

	@Source("warning.png")
	ImageResource warning();

	@Source("cross2.png")
	ImageResource closeBtn();

}
