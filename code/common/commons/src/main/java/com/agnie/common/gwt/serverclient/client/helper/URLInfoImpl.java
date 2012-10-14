package com.agnie.common.gwt.serverclient.client.helper;

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

}
