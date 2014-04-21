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
import com.google.gwt.resources.client.ClientBundle.Source;

public interface LeftErrorPanResources extends ClientBundle {
	public static LeftErrorPanResources	INSTANCE	= GWT.create(LeftErrorPanResources.class);

	@Source("LeftErrorPanCss.css")
	LeftErrorPanCss css();

	@Source("error-small.png")
	ImageResource error();
	
	@Source("cross-small.png")
	ImageResource closeBtn();

	@Source("LeftErrorPanTriangle.png")
	ImageResource textBoxTriangle();

}
