package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface SlideButtonDragResources extends ClientBundle{
	
	public static SlideButtonDragResources INSTANCE=GWT.create(SlideButtonDragResources.class);
	
	@Source("SlideButtonDragCss.css")
	SlideButtonDragCss css();
}
