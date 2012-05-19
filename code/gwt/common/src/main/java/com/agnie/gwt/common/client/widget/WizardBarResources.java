package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface WizardBarResources extends ClientBundle {

	public static WizardBarResources	INSTANCE	= GWT.create(WizardBarResources.class);

	@Source("WizardBarCss.css")
	WizardBarCss css();

	@Source("navBtn.gif")
	ImageResource navButton();

	@Source("navCurrentBtn.gif")
	ImageResource navCurrentButton();

	@Source("navDoneBtn.gif")
	ImageResource navDoneButton();

	@Source("navLastDoneBtn.gif")
	ImageResource navLastDoneButton();

}
