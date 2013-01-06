package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface LabelTextBoxResources extends ClientBundle {
	public static LabelTextBoxResources	INSTANCE	= GWT.create(LabelTextBoxResources.class);

	@Source("LabelTextBoxCss.css")
	LabelTextBoxCss css();
}
