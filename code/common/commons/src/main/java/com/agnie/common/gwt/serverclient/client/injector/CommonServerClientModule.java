/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.gwt.serverclient.client.injector;

import com.agnie.common.gwt.serverclient.client.helper.URLInfo;
import com.agnie.common.gwt.serverclient.client.helper.URLInfoImpl;
import com.google.gwt.inject.client.AbstractGinModule;

public class CommonServerClientModule extends AbstractGinModule {

	public static final String	CURRENT_APP_DOMAIN	= "agnie-domain";

	@Override
	protected void configure() {
		bind(URLInfo.class).to(URLInfoImpl.class);
	}
}
