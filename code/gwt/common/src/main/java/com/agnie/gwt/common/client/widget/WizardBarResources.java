/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
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

	@Source("last_transparent.png")
	ImageResource lastButton();

}
