package com.agnie.common.test.providers;

import com.agnie.common.gwt.serverclient.client.dto.UserAccount;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Pandurang Patil 10-Feb-2014
 * 
 */
public class LoggedInUserProvider implements Provider<UserAccount> {

	@Inject
	private LoggedInUserManager	mgr;

	public UserAccount get() {
		return mgr.getCurrentUser();
	}

}
