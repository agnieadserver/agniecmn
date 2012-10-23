package com.agnie.common.gwt.serverclient.client.helper;

import java.util.Set;

public interface URLInfo {
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
}
