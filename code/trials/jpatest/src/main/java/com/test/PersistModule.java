package com.test;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;

public class PersistModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new JpaPersistModule("test"));
		bind(Initializer.class).asEagerSingleton();
	}
}
