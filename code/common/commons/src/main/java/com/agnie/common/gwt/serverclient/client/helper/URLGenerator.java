package com.agnie.common.gwt.serverclient.client.helper;

import com.agnie.common.gwt.serverclient.client.enums.QueryString;

public class URLGenerator {

	/**
	 * Internal method to add parameter.
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	protected boolean fillParams(StringBuffer url, URLInfo params) {
		String param = params.getParameter(QueryString.GWT_DEV_MODE.getKey());
		boolean qpexists = false;
		if (param != null && !("".equals(param.trim()))) {
			if (!qpexists) {
				url.append(QueryString.QUESTION_MARK.getKey());
				qpexists = true;
			} else {
				url.append(QueryString.AMPERSAND.getKey());
			}
			url.append(QueryString.GWT_DEV_MODE.getKey() + "=" + param);
		}
		param = params.getParameter(QueryString.DOMAIN.getKey());
		if (param != null && !("".equals(param.trim()))) {
			if (!qpexists) {
				url.append(QueryString.QUESTION_MARK.getKey());
				qpexists = true;
			} else {
				url.append(QueryString.AMPERSAND.getKey());
			}
			url.append(QueryString.DOMAIN.getKey() + "=" + param);
		}
		return qpexists;
	}

	/**
	 * This can be used generically to change the locale of given application can be called from server and client both
	 * the sides.
	 * 
	 * @param params
	 * @param newLocale
	 * @return
	 */
	public String getChangeLocaleURL(URLInfo params, String newLocale) {
		// sample URL "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997#sample=test"
		String path = params.getHostURL();
		StringBuffer url = new StringBuffer();
		String postfix = "";
		String location = null;
		if (path.contains(QueryString.HASH.getKey())) {
			// just remove "#sample=test"
			postfix = path.substring(path.indexOf(QueryString.HASH.getKey()));
			// remain "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997"
			location = path.substring(0, path.indexOf(QueryString.HASH.getKey()));
		} else {
			location = path;
		}
		if (location.contains(QueryString.QUESTION_MARK.getKey())) {
			// removed all query parameters including "?" so it removes "?gwt.server=127.0.0.1:9997"
			location = path.substring(0, path.indexOf(QueryString.QUESTION_MARK.getKey()));
			// remains "http://localhost:8080/useradmin/login.jsp"
			url.append(location);
			// execution comes here that means url already contains the query parameters. So add "?"
			url.append(QueryString.QUESTION_MARK.getKey());
			// added "?" so "http://localhost:8080/useradmin/login.jsp?"
			boolean first = true;
			boolean addedMinimumOneParam = false;
			for (String paramKey : params.getParameterKeySet()) {
				// skip locale key add rest of the parameters in url
				if (!(QueryString.LOCALE.getKey().equals(paramKey))) {
					if (first) {
						first = false;
					} else {
						url.append(QueryString.AMPERSAND.getKey());
					}
					boolean innerFirst = true;
					for (String value : params.getAllValues(paramKey)) {
						if (innerFirst) {
							innerFirst = false;
						} else {
							url.append(QueryString.AMPERSAND.getKey());
						}
						url.append(paramKey + "=" + value);
					}
					addedMinimumOneParam = true;
				}
			}
			// after loop url becomes "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997"
			if (addedMinimumOneParam) {
				// If at least one parameter is added into query parameter
				url.append(QueryString.AMPERSAND.getKey());
			}
			// after loop url becomes "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&"
			url.append(QueryString.LOCALE.getKey() + "=" + newLocale);
			// after loop url becomes "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&locale=en"
		} else {
			url.append(location);
			url.append(QueryString.QUESTION_MARK.getKey() + QueryString.LOCALE.getKey() + "=" + newLocale);
		}
		url.append(postfix);
		// after loop url becomes
		// "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&locale=en#sample=test"
		return url.toString();
	}
}
