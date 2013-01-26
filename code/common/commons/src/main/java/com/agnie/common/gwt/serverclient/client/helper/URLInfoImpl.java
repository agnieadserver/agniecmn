package com.agnie.common.gwt.serverclient.client.helper;

import java.util.Set;

import com.agnie.common.gwt.serverclient.client.enums.QueryString;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window.Location;

public class URLInfoImpl implements URLInfo {

	public String getParameter(String name) {
		return Location.getParameter(name);
	}

	public String getHostURL() {
		return Location.getHref();
	}

	public Set<String> getParameterKeySet() {
		return (Location.getParameterMap() != null ? Location.getParameterMap().keySet() : null);
	}

	public String[] getAllValues(String name) {
		return (Location.getParameterMap() != null ? (Location.getParameterMap().get(name) != null ? Location.getParameterMap().get(name).toArray(new String[0]) : null) : null);
	}

	public String getHost() {
		return Location.getHost();
	}

	public String getUTF8EncodedURL(String url) {
		return URL.encode(url);
	}

	public String decodeUTF8URL(String encodedUrl) {
		return URL.decode(encodedUrl);
	}

	public String getQueryString() {
		String queryString = Location.getQueryString();
		if (queryString != null && !(queryString.isEmpty())) {
			return Location.getQueryString().substring(queryString.indexOf(QueryString.QUESTION_MARK.getKey()) + 1, queryString.length());
		}
		return null;
	}

	public String getHostBaseURL() {
		String location = Location.getHref();
		if (location.contains(QueryString.QUESTION_MARK.getKey())) {
			location = location.substring(0, location.indexOf(QueryString.QUESTION_MARK.getKey()));
		} else if (location.contains(QueryString.HASH.getKey())) {
			location = location.substring(0, location.indexOf(QueryString.HASH.getKey()));
		}
		return location;
	}

}
