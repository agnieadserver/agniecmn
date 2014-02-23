package com.agnie.common.gwt.serverclient.client.helper;

/**
 * @author Pandurang Patil 12-Feb-2014
 * 
 */
public class ClientURLConfiguration implements URLConfiguration {

	@Override
	public String get3ABaseURL() {
		return getConstValue(URLGenerator.USER_ADMIN_ROOT_ENDPOINT);
	}

	@Override
	public String getBillingAppUrl() {
		return getConstValue(URLGenerator.BILLING_APP_ENDPOINT);
	}

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

}
