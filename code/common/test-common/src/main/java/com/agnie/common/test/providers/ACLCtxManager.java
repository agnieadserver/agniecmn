package com.agnie.common.test.providers;

import com.agnie.common.server.auth.ACLContext;

/**
 * This class should not be instantiated directly and used. It should be always need to used by getting it injected from
 * Guice injector.
 * 
 */
public class ACLCtxManager {

	private ACLContext	context;

	/**
	 * @return the context
	 */
	public ACLContext getContext() {
		return context;
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public void setContext(ACLContext context) {
		this.context = context;
	}

}
