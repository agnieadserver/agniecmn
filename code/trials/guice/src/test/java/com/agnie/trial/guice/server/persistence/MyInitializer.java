package com.agnie.trial.guice.server.persistence;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistService;

@Singleton
public class MyInitializer {
	@Inject
	public MyInitializer(PersistService service) {
		service.start();
	}
}
