/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.injector;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.inject.Named;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.agnie.common.email.GmailSender;
import com.agnie.common.email.MailSender;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;

public class CommonModule extends AbstractModule {
	private static Logger		logger					= LoggerFactory.getLogger(CommonModule.class);
	public static final String	AGNIE_PROJECT			= "agnie-project";
	public static final String	GMAIL_SENDER			= "gmail";
	public static final String	AGNIE_HOME				= "agnie-home";
	public static final String	AGNIE_HOME_ENV			= "AGNIE_HOME";
	public static final String	VERSION					= "version";
	public static final String	CLIENT_PROXY_TIMEOUT	= "client-proxy-timeout";
	public static final String	agnieHome				= System.getenv(AGNIE_HOME_ENV);
	public static final String	SERVER_IP				= "server.ip";

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
		} catch (Exception e) {
			logger.error("Error while reading version.properties file: ", e);
		}

		Properties globalConfig = new Properties();
		try {
			System.out.println(agnieHome + "/global/config/global-config.properties");
			globalConfig.load(new FileInputStream(new File(agnieHome + "/global/config/global-config.properties")));
			Names.bindProperties(binder(), globalConfig);
		} catch (Exception e) {
			logger.error("Error while reading global-config.properties file: ", e);
		}
	}
}
