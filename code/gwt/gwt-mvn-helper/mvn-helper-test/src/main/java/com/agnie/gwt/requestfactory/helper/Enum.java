package com.agnie.gwt.requestfactory.helper;

import java.io.Serializable;

public class Enum implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public static enum Status {
		ACTIVE, DELETED;
	}

	public static enum UserStatus {
		SIGNEDUP, EMAIL_VERIFIED, ACTIVE, DISABLED;
	}

}