package com.agnie.common.email;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.MessagingException;

public interface MailSender {

	/**
	 * Send the mail with passed on parameters from account identified by sender key.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws MessagingException
	 */
	void sendMail(Email email) throws FileNotFoundException, IOException, MessagingException;

	/**
	 * Send the mail with passed on parameters from account identified by sender key.
	 * 
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws MessagingException
	 */
	void sendMailAsync(Email email);
}
