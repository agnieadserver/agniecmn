/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author Pandurang Patil 23-Aug-2014
 *
 */
public interface ProtoAdminClientBundle extends ClientBundle {
	static final ProtoAdminClientBundle	INSTANCE	= GWT.create(ProtoAdminClientBundle.class);

	@Source("assets/javascripts/theme.custom.js")
	TextResource customTheme();

	@Source("assets/javascripts/theme.init.js")
	TextResource themeInit();

	@Source("assets/javascripts/theme.js")
	TextResource theme();

	@Source("assets/vendor/nanoscroller/nanoscroller.js")
	TextResource nanoscroller();

	@Source("assets/vendor/bootstrapwizard/jquery.bootstrap.wizard.js")
	TextResource wizard();

	@Source("assets/vendor/modernizr/modernizr.js")
	TextResource modernizr();
}
