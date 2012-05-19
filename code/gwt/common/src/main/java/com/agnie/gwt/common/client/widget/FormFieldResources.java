package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface FormFieldResources extends ClientBundle {

	public static FormFieldResources	INSTANCE	= GWT.create(FormFieldResources.class);

	@Source("FormFieldCss.css")
	FormFieldCss css();

}
