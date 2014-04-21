/*******************************************************************************
 * ? 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;

public interface GreenButtonResources extends ClientBundle {

	public static GreenButtonResources	INSTANCE	= GWT.create(GreenButtonResources.class);

	@Source("GreenButtonCss.css")
	GreenButtonCss css();

	@Source("button_left.png")
	ImageResource leftGrnBtn();

	@Source("button_right.png")
	ImageResource rightGrnBtn();

	@ImageOptions(repeatStyle = RepeatStyle.Horizontal)
	@Source("button_body.png")
	ImageResource bodyGrnBtn();

}
