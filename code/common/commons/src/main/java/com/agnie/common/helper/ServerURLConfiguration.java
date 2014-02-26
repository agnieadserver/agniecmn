package com.agnie.common.helper;

import com.agnie.common.gwt.serverclient.client.helper.URLConfiguration;
import com.agnie.common.gwt.serverclient.client.helper.URLGenerator;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

/**
 * @author Pandurang Patil 12-Feb-2014
 * 
 */
@Singleton
public class ServerURLConfiguration implements URLConfiguration {

	@Inject
	@Named(URLGenerator.USER_ADMIN_ROOT_ENDPOINT)
	private String	uaBaseURL;
	@Inject
	@Named(URLGenerator.BILLING_APP_ENDPOINT)
	private String	billingBaseURL;
	@Inject
	@Named(URLGenerator.RECAPTCHA_PUBLIC_KEY)
	private String	recapPublicKey;

	@Override
	public String get3ABaseURL() {
		return uaBaseURL;
	}

	@Override
	public String getBillingAppUrl() {
		return billingBaseURL;
	}

	@Override
	public String recaptchaPublicKey() {
		return recapPublicKey;
	}

}
