package com.agnie.common.test.providers;

import com.agnie.common.gwt.serverclient.client.dto.UserAccount;
import com.google.inject.Singleton;

/**
 * @author Pandurang Patil 10-Feb-2014
 * 
 */
@Singleton
public class LoggedInUserManager {

	private UserAccount	currentUser;

	/**
	 * @return the currentUser
	 */
	public UserAccount getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser
	 *            the currentUser to set
	 */
	public void setCurrentUser(UserAccount currentUser) {
		this.currentUser = currentUser;
	}

}
