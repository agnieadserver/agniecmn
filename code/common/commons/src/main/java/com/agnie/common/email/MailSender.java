package com.agnie.common.email;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.MessagingException;

public interface MailSender {

	/**
	 * Send the mail with passed on parameters from account identified by sender key.
	 * 
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
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws MessagingException
	 */
	void sendMail(String senderKey, Recipient recipient, String subject, String message) throws FileNotFoundException, IOException, MessagingException;
}
