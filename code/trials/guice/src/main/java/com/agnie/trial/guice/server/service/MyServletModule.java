/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.trial.guice.server.service;

import com.google.inject.name.Names;
import com.google.inject.servlet.ServletModule;
import com.google.inject.servlet.ServletScopes;

public class MyServletModule extends ServletModule {

	@Override
	protected void configureServlets() {
		bind(String.class).annotatedWith(Names.named("sir-name")).to(String.class).in(ServletScopes.REQUEST);
		filter("/*").through(ExtractSirName.class);
		serve("/samples/sampleService").with(SampleServiceImpl.class);
	}
}
