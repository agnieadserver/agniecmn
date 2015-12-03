/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.email;

import java.util.HashMap;
import java.util.Map;

import javax.mail.Message;

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
    private MailSender          mailSender;

    @Inject
    @Named(TestModule.TEST_TEMPLATE)
    private MessageTemplate     msgTpl;

    private static final String MSG_SUB = "Junit Test";

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

    @Test
    public void sendMailTestWithBCC() {
        try {
            Map<String, String> variables = new HashMap<String, String>();
            variables.put("test", "Junit");

            Recipient recipient = new Recipient("mail@agnie.co.in");
            recipient.put(Message.RecipientType.BCC, "saurabh@agnie.net");

            mailSender.sendMail(new Email("verifier", recipient, MSG_SUB, msgTpl.getMessage(variables)));
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
}
