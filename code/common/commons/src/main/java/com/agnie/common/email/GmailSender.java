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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.agnie.common.exception.UnexpectedException;
import com.agnie.common.shutdown.ShutdownHook;
import com.agnie.common.shutdown.ShutdownProcessor;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class GmailSender implements MailSender, ShutdownHook {
	public static final String		GMAIL_MAIL_CONFIG	= "gmail.properties";
	private static Logger			logger				= LoggerFactory.getLogger(GmailSender.class);
	// maintains different email account meta data cached.
	private EmailAccounts			emailAccounts;
	// SMTP JavaMail properties for Gmail.
	private Properties				mailProps;
	private Map<String, Session>	mailSessions		= new HashMap<String, Session>();
	ExecutorService					pool;

	@Inject
	public GmailSender(EmailAccounts accounts, ShutdownProcessor shutdownProcessor) {
		shutdownProcessor.register(this);
		pool = Executors.newSingleThreadExecutor();
		this.emailAccounts = accounts;
	}

	/**
	 * Load Gmail SMTP properties.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */

	private synchronized void loadMailProps() throws FileNotFoundException, IOException {
		mailProps = new Properties();
		mailProps.load(getClass().getResourceAsStream(GMAIL_MAIL_CONFIG));
	}

	/**
	 * Initialise the session for given account key. If it is already not initialised.
	 * 
	 * @param key
	 *            Key of the email account which identifies given email account meta data inside EmailAccounts.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private synchronized void initSession(String key) throws FileNotFoundException, IOException {

		if (mailSessions.get(key) == null) {
			final EmailAccount acc = emailAccounts.getEmailAccount(key);
			if (mailProps == null) {
				loadMailProps();
			}
			if (acc != null) {
				mailSessions.put(key, Session.getDefaultInstance(mailProps, new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {

						return new PasswordAuthentication(acc.getUsername(), acc.getPassword());
					}
				}));
			} else {
				logger.error("No Account found with key: " + key);
				throw new UnexpectedException("No Account found with key: " + key);
			}
		}
	}

	/**
	 * Retrieve mail session for given key
	 * 
	 * @param key
	 *            Key of the email account which identifies given email account meta data inside EmailAccounts.
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private Session getSession(String key) throws FileNotFoundException, IOException {

		if (mailSessions.get(key) == null) {
			initSession(key);
		}
		return mailSessions.get(key);
	}

	public void sendMailAsync(Email email) {
		Callable<Void> task = new Callable<Void>() {
			private Email	mail;

			Callable<Void> setEmail(Email email) {
				this.mail = email;
				return this;
			}

			@Override
			public Void call() {
				try {
					sendMail(mail);
				} catch (Exception e) {
					logger.error("msg => " + mail + " not sent", e);
				}
				return null;
			}
		}.setEmail(email);
		pool.submit(task);
	}

	public void sendMail(Email email) throws FileNotFoundException, IOException, MessagingException {

		EmailAccount acc = emailAccounts.getEmailAccount(email.getSenderKey());
		Session session = getSession(email.getSenderKey());
		if (session != null && acc != null) {
			InternetAddress iad = new InternetAddress(acc.getFrom());
			iad.setPersonal(acc.getFromName());
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(iad);
			Address[] replyToAdds = InternetAddress.parse(acc.getReplyTo());
			msg.setReplyTo(replyToAdds);
			msg.setSubject(email.getSubject());
			Recipient recipient = email.getRecipient();
			msg.setRecipients(Message.RecipientType.TO, recipient.get(Message.RecipientType.TO));
			Address[] ccAddress = recipient.get(Message.RecipientType.CC);
			if (ccAddress != null) {
				msg.setRecipients(Message.RecipientType.CC, ccAddress);
			}
			Address[] bccAddress = recipient.get(Message.RecipientType.BCC);
			if (bccAddress != null) {
				msg.setRecipients(Message.RecipientType.BCC, bccAddress);
			}
			msg.setText(email.getMessage(), "utf-8", "html");
			Transport.send(msg);
		} else {
			logger.error("Message not sent as either session of account information is not intialised or email account is not configured");
			throw new UnexpectedException("Message not sent as either session of account information is not intialised or email account is not configured");
		}
	}

	@Override
	public void shutdown(boolean sync) {
		logger.info("Async email thread is going down");
		pool.shutdown();
	}

}
