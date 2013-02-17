package com.agnie.common.gwt.serverclient.client.helper;

import com.agnie.common.gwt.serverclient.client.enums.QueryString;
import com.google.gwt.user.client.Window;

public class URLGenerator {
	public static final String	HASH_CHANGE_PASS	= "#CHANGE_PASS";
	public static final String	HASH_UPDATE_PROFILE	= "#UPDATE_PROFILE";
	public static final String	LANDING_PAGE		= "landing.jsp";

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

	/**
	 * This will generate url for Change password page.
	 * 
	 * @param params
	 *            existing url information helper
	 * @param domain
	 *            agnie application domain
	 * @param devMode
	 *            GWT dev mode parameter.
	 * @return
	 */
	public String getChangePassUrl(URLInfo params, String domain, String devMode) {
		return getLandingBaseUrl(params, domain, devMode) + HASH_CHANGE_PASS;
	}

	/**
	 * This will generate url for Update profile.
	 * 
	 * @param params
	 *            existing url information helper
	 * @param domain
	 *            agnie application domain
	 * @param devMode
	 *            GWT dev mode parameter.
	 * @return
	 */
	public String getUpdateProfUrl(URLInfo params, String domain, String devMode) {
		return getLandingBaseUrl(params, domain, devMode) + HASH_UPDATE_PROFILE;
	}

	/**
	 * This will generate base landing page url in case of UPDATE_PROFILE and CHANGE_PASS word page.
	 * 
	 * @param params
	 *            existing url information helper
	 * @param domain
	 *            agnie application domain
	 * @param devMode
	 *            GWT dev mode parameter.
	 * @return
	 */
	public String getLandingBaseUrl(URLInfo params, String domain, String devMode) {
		String baseUrl = getLandingPageUrl(params, domain, devMode);
		StringBuffer url = new StringBuffer(baseUrl);
		boolean qpexists = false;
		if (baseUrl.contains(QueryString.QUESTION_MARK.getKey()))
			qpexists = true;
		String param = Window.Location.getHash();
		if (param != null) {
			if (!qpexists) {
				url.append(QueryString.QUESTION_MARK.getKey());
				qpexists = true;
			} else {
				url.append(QueryString.AMPERSAND.getKey());
			}
			// remove '#' from param.
			url.append(QueryString.HISTORY_HASH.getKey() + "=" + param.substring(1, param.length()));
		}
		if (!qpexists) {
			url.append(QueryString.QUESTION_MARK.getKey());
			qpexists = true;
		} else {
			url.append(QueryString.AMPERSAND.getKey());
		}
		url.append(QueryString.SOURCE.getKey() + "="
				+ params.getUTF8EncodedURL(params.getHostBaseURL() + (params.getQueryString() != null ? QueryString.QUESTION_MARK.getKey() + params.getQueryString() : "")));
		return url.toString();
	}

	/**
	 * This will generate base Landing page url
	 * 
	 * @param params
	 *            existing url information helper
	 * @param domain
	 *            agnie application domain
	 * @param devMode
	 *            GWT dev mode parameter.
	 * @return
	 */
	public String getLandingPageUrl(URLInfo params, String domain, String devMode) {
		// TODO: This method has to read the base url from some config file. As it needs to be configured value when it
		// will get used from other than useradmin / 3a4users application.
		StringBuffer url = new StringBuffer();
		String location = params.getHostURL();
		location = location.substring(0, location.lastIndexOf("/"));
		url.append(location + "/" + LANDING_PAGE);
		boolean qpexists = false;
		if (devMode != null && !(devMode.isEmpty())) {
			url.append(QueryString.QUESTION_MARK.getKey());
			qpexists = true;
			url.append(QueryString.GWT_DEV_MODE.getKey() + "=" + devMode);
		}
		if (domain != null && !("".equals(domain.trim()))) {
			if (!qpexists) {
				url.append(QueryString.QUESTION_MARK.getKey());
				qpexists = true;
			} else {
				url.append(QueryString.AMPERSAND.getKey());
			}
			url.append(QueryString.DOMAIN.getKey() + "=" + domain);
		}
		String param = params.getParameter(QueryString.LOCALE.getKey());
		if (param != null && !("".equals(param.trim()))) {
			if (!qpexists) {
				url.append(QueryString.QUESTION_MARK.getKey());
				qpexists = true;
			} else {
				url.append(QueryString.AMPERSAND.getKey());
			}
			url.append(QueryString.LOCALE.getKey() + "=" + param);
		}
		param = params.getParameter(QueryString.SESSION.getKey());
		if (param != null && !("".equals(param.trim()))) {
			if (!qpexists) {
				url.append(QueryString.QUESTION_MARK.getKey());
				qpexists = true;
			} else {
				url.append(QueryString.AMPERSAND.getKey());
			}
			url.append(QueryString.SESSION.getKey() + "=" + param);
		}
		return url.toString();
	}

	/**
	 * To navigate user to back to the page from where he has come to either change password or update profile page.
	 * 
	 * @param params
	 * @return
	 */
	public String getSourceUrl(URLInfo params) {
		String param = params.getParameter(QueryString.SOURCE.getKey());
		if (param != null && !param.isEmpty()) {
			return params.decodeUTF8URL(param)
					+ (params.getParameter(QueryString.HISTORY_HASH.getKey()) != null ? QueryString.HASH.getKey() + params.getParameter(QueryString.HISTORY_HASH.getKey()) : "");
		}
		return null;
	}
}
