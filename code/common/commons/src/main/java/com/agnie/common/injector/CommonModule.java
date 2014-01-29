package com.agnie.common.injector;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.inject.Named;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.agnie.common.email.GmailSender;
import com.agnie.common.email.MailSender;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;

public class CommonModule extends AbstractModule {
	private static Logger		logger					= Logger.getLogger(CommonModule.class);

	public static final String	GMAIL_SENDER			= "gmail";
	public static final String	AGNIE_HOME				= "agnie-home";
	public static final String	AGNIE_HOME_ENV		= "AGNIE_HOME";
	public static final String	VERSION					= "version";
	public static final String	CLIENT_PROXY_TIMEOUT	= "client-proxy-timeout";
	private String				agnieHome				= System.getenv(AGNIE_HOME_ENV);

	@Provides
	@Named(AGNIE_HOME)
	public String getAdworksHome() {
		return agnieHome;
	}

	@Override
	protected void configure() {
		bind(ObjectMapper.class).asEagerSingleton();
		bind(MailSender.class).annotatedWith(Names.named(GMAIL_SENDER)).to(GmailSender.class);

		Properties version = new Properties();
		try {
			version.load(getClass().getResourceAsStream("version.properties"));
			Names.bindProperties(binder(), version);
		} catch (IOException e) {
			logger.error("Error while reading version.properties file: ", e);
		}

		Properties globalConfig = new Properties();
		try {
			System.out.println(agnieHome + "/global/config/global-config.properties");
			globalConfig.load(new FileInputStream(new File(agnieHome + "/global/config/global-config.properties")));
			Names.bindProperties(binder(), globalConfig);
		} catch (IOException e) {
			logger.error("Error while reading global-config.properties file: ", e);
		}
	}
}
