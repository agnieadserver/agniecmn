package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface LabelPasswordBoxResources extends ClientBundle{
	public static LabelPasswordBoxResources	INSTANCE	= GWT.create(LabelPasswordBoxResources.class);

	@Source("LabelPasswordBoxCss.css")
	LabelPasswordBoxCss css();
}
