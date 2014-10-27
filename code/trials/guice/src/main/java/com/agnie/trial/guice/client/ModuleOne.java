package com.agnie.trial.guice.client;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class ModuleOne extends AbstractModule {

	@Override
	protected void configure() {

		bind(DependencyTwo.class).in(Scopes.SINGLETON);
		bind(MediumInterface.class).to(SampleMedium.class);

		bind(String.class).annotatedWith(Names.named("other")).toInstance(new String("other value"));
	}

	@Provides
	@Named("excel")
	String getUniqueId() {
		return "excel";
	}

	@Provides
	@Named("pdf")
	String getpdf() {
		return "pdf";
	}

}
