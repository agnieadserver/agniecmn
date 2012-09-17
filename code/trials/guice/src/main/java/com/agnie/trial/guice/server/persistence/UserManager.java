package com.agnie.trial.guice.server.persistence;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

public class UserManager {
	@Inject
	EntityManager	em;

	@Transactional
	public Contact saveContact(Contact contact) {
		em.persist(contact);
		return contact;
	}

	@Transactional
	public User saveUser(User user) {
		em.persist(user);
		return user;
	}
}
