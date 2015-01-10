/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.gwt.serverclient.client.helper;

import java.util.Set;

public interface URLInfo {
	public String	PROD_ROOT_CONTEXT	= "userapp";

	/**
	 * 
	 * @param name
	 *            of query parameter
	 * @return corresponding value of the parameter in query string.
	 */
	String getParameter(String name);

	/**
	 * 
	 * @param name
	 *            of query parameter
	 * @return all values associated with given query parameter key. If you add multiple query parameters with same key
	 *         name then you can retrieve all list of values for given single key
	 */
	String[] getAllValues(String name);

	/**
	 * Complete HostURL including query string parameters
	 * 
	 * @return complete URL
	 */
	String getHostURL();

	/**
	 * 
	 * @return host and port number from URL.
	 */
	String getHost();

	/**
	 * 
	 * @return set of all key names in a query string
	 */
	Set<String> getParameterKeySet();

	/**
	 * 
	 * @param url
	 *            which need to be encoded
	 * @return UTF-8 encoded URL
	 */
	String getUTF8EncodedURL(String url);

	/**
	 * 
	 * @param encodedUrl
	 *            which is already encoded and need to be decoded.
	 * @return UTF-8 decoded url.
	 */
	String decodeUTF8URL(String encodedUrl);

	/**
	 * retrieve complete Query String from the URL
	 * 
	 * @return complete query String
	 */
	String getQueryString();

	/**
	 * Complete URL excluding Query Parameters.
	 * 
	 * @return base URL excluding params
	 */
	String getHostBaseURL();

	/**
	 * Request URL protocol
	 * 
	 * @return request protocol HTTP / HTTPS
	 */
	String getProtocol();

	/**
	 * Request urls base url.
	 * 
	 * @return base url with out path
	 */
	String getRootHostURL();

	/**
	 * Base url with root context
	 * 
	 * @return base URL with root conext
	 */
	String getRootContextURL();

	/**
	 * Get referrer url from header.
	 * 
	 * @return referrer URL
	 */
	String getReferrer();

	boolean isSecure();
}
