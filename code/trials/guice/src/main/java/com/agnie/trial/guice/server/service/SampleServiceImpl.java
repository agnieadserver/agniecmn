/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.trial.guice.server.service;

import com.agnie.trial.guice.client.SampleService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@Singleton
public class SampleServiceImpl extends RemoteServiceServlet implements SampleService {

	Provider<String>	sirName;

	@Inject
	public void setSirName(@Named("sir-name") Provider<String> sirName) {
		this.sirName = sirName;
	}

	private static final long	serialVersionUID	= 1L;

	@Override
	public String getMessage(String name) {

		return "Hello " + name + ", " + sirName.get();
	}

}
