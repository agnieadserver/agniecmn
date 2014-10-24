/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.email;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class EmailAccounts {

	public static final String			EMAIL_ACCOUNTS_CONFIG	= "mail_accounts.json";

	// map of email accounts attached to a key
	private Map<String, EmailAccount>	mailAccounts;
	// jackson object mapper to read json contents from config file.
	private ObjectMapper				mapper;

	@Inject
	public EmailAccounts(ObjectMapper mapper) {

		this.mapper = mapper;
	}

	private synchronized void init() throws JsonParseException, JsonMappingException, IOException {

		if (mailAccounts == null) {
			mailAccounts = mapper.readValue(getClass().getResource("/" + EMAIL_ACCOUNTS_CONFIG), new TypeReference<Map<String, EmailAccount>>() {
			});

		}
	}

	public EmailAccount getEmailAccount(String key) throws JsonParseException, JsonMappingException, IOException {

		if (mailAccounts == null) {
			init();
		}
		return mailAccounts.get(key);
	}

}
