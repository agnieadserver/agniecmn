package com.agnie.common.gwt.serverclient.client.helper;

import com.agnie.common.gwt.serverclient.client.enums.Cokie;
import com.agnie.common.gwt.serverclient.client.enums.QueryString;
import com.google.gwt.user.client.Cookies;

public class URLGenerator {
	/*
	 * TODO Initialize through maven build variables.
	 */
	public static final String	LOGIN_REQUEST	= "login.jsp";
	public static final String	MAIN_PAGE		= "useradmin.jsp";

	private static boolean fillParams(StringBuffer url, URLInfo params) {
		String param = params.getParameter(QueryString.GWT_DEV_MODE.getKey());
		boolean qpexists = false;
		if (param != null && !("".equals(param.trim()))) {
			if (!qpexists) {
				url.append(QueryString.QUERY_STR_SPLITTER.getKey());
				qpexists = true;
			} else {
				url.append(QueryString.PARAM_SPLITTER.getKey());
			}
			url.append(QueryString.GWT_DEV_MODE.getKey() + "=" + param);
		}
		param = params.getParameter(QueryString.DOMAIN.getKey());
		if (param != null && !("".equals(param.trim()))) {
			if (!qpexists) {
				url.append(QueryString.QUERY_STR_SPLITTER.getKey());
				qpexists = true;
			} else {
				url.append(QueryString.PARAM_SPLITTER.getKey());
			}
			url.append(QueryString.DOMAIN.getKey() + "=" + param);
		}
		return qpexists;
	}

	public static String getLoginURL(URLInfo params) {
		StringBuffer url = new StringBuffer();
		String location = params.getHostURL();
		if (location.contains(QueryString.QUERY_STR_SPLITTER.getKey())) {
			location = location.substring(0, location.indexOf(QueryString.QUERY_STR_SPLITTER.getKey()));
		}
		location = location.substring(0, location.lastIndexOf("/"));
		url.append(location + "/" + LOGIN_REQUEST);
		boolean qpexists = fillParams(url, params);
		String param = params.getParameter(QueryString.LOCALE.getKey());
		if (param != null && !("".equals(param.trim()))) {
			if (!qpexists) {
				url.append(QueryString.QUERY_STR_SPLITTER.getKey());
				qpexists = true;
			} else {
				url.append(QueryString.PARAM_SPLITTER.getKey());
			}
			url.append(QueryString.LOCALE.getKey() + "=" + param);
		}
		param = params.getParameter(QueryString.SOURCE.getKey());
		if (param != null && !("".equals(param.trim()))) {
			// param = location + "/" + MAIN_PAGE;
			// } else if (param.contains(LOGIN_REQUEST)) {
			// param = param.substring(0, location.lastIndexOf("/") + 1);
			// }
			if (!qpexists) {
				url.append(QueryString.QUERY_STR_SPLITTER.getKey());
				qpexists = true;
			} else {
				url.append(QueryString.PARAM_SPLITTER.getKey());
			}
			url.append(QueryString.SOURCE.getKey() + "=" + param);
		}

		return url.toString();
	}

	public static String getChangeLocaleURL(URLInfo params, String newLocale) {
		String path = params.getHostURL();
		StringBuffer url = new StringBuffer();
		String postfix = "";
		String location = null;
		if (path.contains(QueryString.PAGE_SECTION_LOCATOR.getKey())) {
			postfix = path.substring(path.indexOf(QueryString.PAGE_SECTION_LOCATOR.getKey()));
			location = path.substring(0, path.indexOf(QueryString.PAGE_SECTION_LOCATOR.getKey()));
		} else {
			location = path;
		}
		if (location.contains(QueryString.QUERY_STR_SPLITTER.getKey())) {
			location = path.substring(0, path.indexOf(QueryString.QUERY_STR_SPLITTER.getKey()));
			url.append(location);
			boolean qpexists = fillParams(url, params);
			String param = params.getParameter(QueryString.SOURCE.getKey());
			if (param != null && !("".equals(param.trim()))) {
				if (!qpexists) {
					url.append(QueryString.QUERY_STR_SPLITTER.getKey());
					qpexists = true;
				} else {
					url.append(QueryString.PARAM_SPLITTER.getKey());
				}
				url.append(QueryString.SOURCE.getKey() + "=" + param);
			}
			if (!qpexists) {
				url.append(QueryString.QUERY_STR_SPLITTER.getKey());
				qpexists = true;
			} else {
				url.append(QueryString.PARAM_SPLITTER.getKey());
			}
			url.append(QueryString.LOCALE.getKey() + "=" + newLocale);
		} else {
			url.append(location);
			url.append(QueryString.QUERY_STR_SPLITTER.getKey() + QueryString.LOCALE.getKey() + "=" + newLocale);
		}
		url.append(postfix);
		return url.toString();
	}

	public static String getAfterLoginRedirectUrl(URLInfo params) {
		StringBuffer url = new StringBuffer();
		String location = params.getHostURL();
		if (location.contains(QueryString.QUERY_STR_SPLITTER.getKey())) {
			location = location.substring(0, location.indexOf(QueryString.QUERY_STR_SPLITTER.getKey()));
		}
		location = location.substring(0, location.lastIndexOf("/"));
		String param = params.getParameter(QueryString.SOURCE.getKey());
		if (param == null || "".equals(param.trim())) {
			param = location + "/" + MAIN_PAGE;
		} else if (param.contains(LOGIN_REQUEST)) {
			param = param.substring(0, location.lastIndexOf("/") + 1);
		}
		url.append(param);
		boolean qpexists = fillParams(url, params);
		param = params.getParameter(QueryString.LOCALE.getKey());
		if (param != null && !("".equals(param.trim()))) {
			if (!qpexists) {
				url.append(QueryString.QUERY_STR_SPLITTER.getKey());
				qpexists = true;
			} else {
				url.append(QueryString.PARAM_SPLITTER.getKey());
			}
			url.append(QueryString.LOCALE.getKey() + "=" + param);
		}
		String sessionid = Cookies.getCookie(Cokie.AUTH.getKey());
		if (sessionid != null && !("".equals(sessionid.trim()))) {
			if (!qpexists) {
				url.append(QueryString.QUERY_STR_SPLITTER.getKey());
				qpexists = true;
			} else {
				url.append(QueryString.PARAM_SPLITTER.getKey());
			}
			url.append(QueryString.SESSION.getKey() + "=" + sessionid);
		}
		return url.toString();
	}

	public static String getAfterLoginRedirectUrl(URLInfo params, String homeURL) {
		StringBuffer url = new StringBuffer();
		String location = params.getHostURL();
		if (location.contains(QueryString.QUERY_STR_SPLITTER.getKey())) {
			location = location.substring(0, location.indexOf(QueryString.QUERY_STR_SPLITTER.getKey()));
		}
		location = location.substring(0, location.lastIndexOf("/"));
		String param = params.getParameter(QueryString.SOURCE.getKey());
		if (param == null || "".equals(param.trim())) {
			if (homeURL == null || "".equals(homeURL.trim())) {
				param = location + "/" + MAIN_PAGE;
			} else {
				param = homeURL;
			}
		} else if (param.contains(LOGIN_REQUEST)) {
			param = param.substring(0, location.lastIndexOf("/") + 1);
		}
		url.append(param);
		boolean qpexists = fillParams(url, params);
		param = params.getParameter(QueryString.LOCALE.getKey());
		if (param != null && !("".equals(param.trim()))) {
			if (!qpexists) {
				url.append(QueryString.QUERY_STR_SPLITTER.getKey());
				qpexists = true;
			} else {
				url.append(QueryString.PARAM_SPLITTER.getKey());
			}
			url.append(QueryString.LOCALE.getKey() + "=" + param);
		}
		String sessionid = Cookies.getCookie(Cokie.AUTH.getKey());
		if (sessionid != null && !("".equals(sessionid.trim()))) {
			if (!qpexists) {
				url.append(QueryString.QUERY_STR_SPLITTER.getKey());
				qpexists = true;
			} else {
				url.append(QueryString.PARAM_SPLITTER.getKey());
			}
			url.append(QueryString.SESSION.getKey() + "=" + sessionid);
		}
		return url.toString();
	}
}
