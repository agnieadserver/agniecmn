package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface SlideButtonScaleResources extends ClientBundle{
	
	public static SlideButtonScaleResources INSTANCE=GWT.create(SlideButtonScaleResources.class);
	
	@Source("SlideButtonScaleCss.css")
	SlideButtonScaleCss css();
}
