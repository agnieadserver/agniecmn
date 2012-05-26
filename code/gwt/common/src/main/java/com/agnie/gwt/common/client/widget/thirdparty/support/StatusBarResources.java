package com.agnie.gwt.common.client.widget.thirdparty.support;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface StatusBarResources extends ClientBundle {

	public static StatusBarResources	INSTANCE	= GWT.create(StatusBarResources.class);

	@Source("ProgressBarCss.css")
	ProgressBarCss css();

}
