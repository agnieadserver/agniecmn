/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.gwt.serverclient.client.helper;

/**
 * @author Pandurang Patil 12-Feb-2014
 * 
 */
public class ClientURLConfiguration implements URLConfiguration {
	/**
	 * It is required to include config.jsp inside main / landing jsp page of your application. Which will make sure
	 * required javascript values are injected in main page.
	 * 
	 * @param key
	 * @return
	 */
	private native String getConstValue(String key)/*-{
													return $wnd.constants[key];
													}-*/;

	@Override
	public String get3ABaseURL() {
		return getConstValue(URLGenerator.USER_ADMIN_ROOT_ENDPOINT);
	}

	@Override
	public String getBillingAppUrl() {
		return getConstValue(URLGenerator.BILLING_APP_ENDPOINT);
	}

	@Override
	public String recaptchaPublicKey() {
		return getConstValue(URLGenerator.RECAPTCHA_PUBLIC_KEY);
	}

}
