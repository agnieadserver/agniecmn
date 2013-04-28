package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface DandDDragResources extends ClientBundle {

	public static DandDDragResources	INSTANCE	= GWT.create(DandDDragResources.class);

	@Source("DandDDragCss.css")
	DandDDragCss css();
}
