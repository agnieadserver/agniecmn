package com.agnie.trial.guice.server.persistence;

import javax.persistence.EntityManager;

import com.google.inject.Inject;

public class TestService {

	@Inject
	EntityManager	em;

	public void initTest() {
		em.close();
	}
}
