package com.test;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

public class Initializer {

	@Inject
	public Initializer(PersistService service) {
		service.start();
	}

}
