package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface FormPanelResources extends ClientBundle {

	public static FormPanelResources	INSTANCE	= GWT.create(FormPanelResources.class);

	@Source("FormPanelCss.css")
	FormPanelCss css();

}
