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

public interface AccountResources extends ClientBundle {
	public static AccountResources	INSTANCE	= GWT.create(AccountResources.class);

	@Source("AccountCss.css")
	AccountCss css();

	@Source("arrow.png")
	ImageResource arrowImg();

	@Source("arrowDark.png")
	ImageResource arrowDarkImg();

	@Source("person.png")
	ImageResource person();

	@Source("acc-triangle-silver.png")
	ImageResource accTriangleSilver();
	
	@Source("acc-triangle.png")
	ImageResource accTriangle();
}
