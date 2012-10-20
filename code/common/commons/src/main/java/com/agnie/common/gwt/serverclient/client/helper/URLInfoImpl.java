package com.agnie.common.gwt.serverclient.client.helper;

import java.util.Set;

import com.google.gwt.user.client.Window.Location;

/**
 * 
 */
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

}
