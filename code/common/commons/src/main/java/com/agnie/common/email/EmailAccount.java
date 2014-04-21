/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.email;

public class EmailAccount {

	// username to login to email account
	private String	username;
	// password to login to email account
	private String	password;
	// what from email address that need to be used while sending the email for
	// given account
	private String	from;
	// From Name that should be used while sending the email
	private String	fromName;

	/**
	 * @return the username
	 */
	public String getUsername() {

		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {

		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {

		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {

		this.password = password;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {

		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(String from) {

		this.from = from;
	}

	/**
	 * @return the fromName
	 */
	public String getFromName() {

		return fromName;
	}

	/**
	 * @param fromName
	 *            the fromName to set
	 */
	public void setFromName(String fromName) {

		this.fromName = fromName;
	}

}
