package com.agnie.common.test.providers;

import com.agnie.common.server.auth.ACLContext;

/**
 * 
 *
 */
public class ACLCtxManager {

	private static ACLCtxManager instance;

	private ACLContext context;

	private ACLCtxManager() {

	}

	public static ACLCtxManager getInstance() {
		if (instance == null) {
			synchronized (ACLCtxManager.class) {
				if (instance == null) {
					instance = new ACLCtxManager();
				}
			}
		}
		return instance;
	}

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
