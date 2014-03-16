package com.agnie.common.email;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.agnie.common.injector.CommonModule;
import com.agnie.common.test.injector.GuiceTestRunner;
import com.agnie.common.test.injector.WithModules;
import com.google.inject.Inject;
import com.google.inject.name.Named;

@RunWith(GuiceTestRunner.class)
@WithModules({ CommonModule.class, TestModule.class })
public class GmailEmailSenderTest {

	@Inject
	@Named(CommonModule.GMAIL_SENDER)
	private MailSender			mailSender;

	@Inject
	@Named(TestModule.TEST_TEMPLATE)
	private MessageTemplate		msgTpl;

	private static final String	MSG_SUB	= "Junit Test";

	@Test
	public void sendMailTest() {
		try {
			Map<String, String> variables = new HashMap<String, String>();
			variables.put("test", "Junit");

			mailSender.sendMail(new Email("verifier", new Recipient("mail@agnie.co.in"), MSG_SUB, msgTpl.getMessage(variables)));
			Assert.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
}
