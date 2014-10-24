/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.email;

/**
 * @author Pandurang Patil 15-Mar-2014
 * 
 */
public class Email {
	private String		senderKey;
	private Recipient	recipient;
	private String		subject;
	private String		message;

	/**
	 * @param senderKey
	 *            Number of accounts with their keys can be configured inside config files. Each account will be
	 *            identified by a unique key. Caller need to pass that key to identify which account details need to be
	 *            used to send given mail.
	 * @param recipient
	 *            recipients to whom email need to be sent.
	 * @param subject
	 *            Subject of the email.
	 * @param message
	 *            body of the email.
	 */
	public Email(String senderKey, Recipient recipient, String subject, String message) {
		super();
		this.senderKey = senderKey;
		this.recipient = recipient;
		this.subject = subject;
		this.message = message;
	}

	/**
	 * @return the senderKey
	 */
	public String getSenderKey() {
		return senderKey;
	}

	/**
	 * @return the recipient
	 */
	public Recipient getRecipient() {
		return recipient;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Email [senderKey=" + senderKey + ", recipient=" + recipient + ", subject=" + subject + ", message=" + message + "]";
	}

}
