/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
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
