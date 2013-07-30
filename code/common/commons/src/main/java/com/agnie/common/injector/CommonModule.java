package com.agnie.common.injector;

import org.codehaus.jackson.map.ObjectMapper;

import com.agnie.common.email.GmailSender;
import com.agnie.common.email.MailSender;
import com.agnie.common.gwt.serverclient.client.helper.URLInfo;
import com.agnie.common.helper.ServerURLInfo;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class CommonModule extends AbstractModule {

	public static final String	GMAIL_SENDER	= "gmail";

	@Override
	protected void configure() {
		bind(ObjectMapper.class).asEagerSingleton();
		bind(MailSender.class).annotatedWith(Names.named(GMAIL_SENDER)).to(GmailSender.class);
		bind(URLInfo.class).to(ServerURLInfo.class);
	}
}
