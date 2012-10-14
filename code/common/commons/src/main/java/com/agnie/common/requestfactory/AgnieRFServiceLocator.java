package com.agnie.common.requestfactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;

public class AgnieRFServiceLocator implements ServiceLocator {

	@Inject
	private Injector	injector;

	public Object getInstance(Class<?> clazz) {
		return injector.getInstance(clazz);
	}
}
