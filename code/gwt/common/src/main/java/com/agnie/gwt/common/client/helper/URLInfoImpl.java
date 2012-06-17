package com.agnie.gwt.common.client.helper;

import com.google.gwt.user.client.Window.Location;

/**
 * 
 */
public class URLInfoImpl implements URLInfo {

	@Override
	public String getParameter(String name) {
		return Location.getParameter(name);
	}

	@Override
	public String getHostURL() {
		return Location.getHref();
	}

}
