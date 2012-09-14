package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface WizardBarResources extends ClientBundle {

	public static WizardBarResources	INSTANCE	= GWT.create(WizardBarResources.class);

	@Source("WizardBarCss.css")
	WizardBarCss css();

	@Source("inactive.png")
	ImageResource navButton();

	@Source("current_btn.png")
	ImageResource navCurrentButton();

	@Source("done.png")
	ImageResource navDoneButton();

	@Source("last_done.png")
	ImageResource navLastDoneButton();

}
