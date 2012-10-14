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
