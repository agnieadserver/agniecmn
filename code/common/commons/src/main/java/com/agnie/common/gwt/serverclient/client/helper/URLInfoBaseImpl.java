/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.gwt.serverclient.client.helper;

public abstract class URLInfoBaseImpl implements URLInfo {
	public static final String	AGNIE_BASE_DOMAIN	= "agnie.net";

	/**
	 * Request urls base url.
	 * 
	 * @return
	 */
	public String getRootHostURL() {
		String url = (getProtocol().contains("HTTPS") || getProtocol().contains("https") ? "https" : "http");
		url += "://" + getHost();
		return url;
	}

	/**
	 * Base url with root context
	 * 
	 * @return
	 */
	public String getRootContextURL() {
		String completeUrl = getHostBaseURL();
		String url = getRootHostURL();
		if (completeUrl.contains(PROD_ROOT_CONTEXT)) {
			return url + "/" + PROD_ROOT_CONTEXT;
		}
		return url;
	}
}
