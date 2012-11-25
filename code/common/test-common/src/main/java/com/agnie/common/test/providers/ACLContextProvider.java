package com.agnie.common.test.providers;

import com.agnie.common.server.auth.ACLContext;
import com.google.inject.Provider;

/**
 * This provider has been specifically been created to facilitate setting
 * required ACLContext for testing purpose. This class shouldn't be used in main
 * code.
 * 
 */
public class ACLContextProvider implements Provider<ACLContext> {

	private ACLCtxManager mgr = ACLCtxManager.getInstance();

	public ACLContext get() {
		return mgr.getContext();
	}

}