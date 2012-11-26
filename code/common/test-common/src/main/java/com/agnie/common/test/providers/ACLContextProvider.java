package com.agnie.common.test.providers;

import com.agnie.common.server.auth.ACLContext;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * This provider has been specifically been created to facilitate setting required ACLContext for testing purpose. This
 * class shouldn't be used in main code. Usage is limited to single threaded environment. If its being used in multi
 * threaded environment then behaviour is not guarenteed.
 * 
 */
public class ACLContextProvider implements Provider<ACLContext> {

	@Inject
	private ACLCtxManager	mgr;

	public ACLContext get() {
		return mgr.getContext();
	}

}