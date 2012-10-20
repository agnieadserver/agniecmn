package com.agnie.common.gwt.serverclient.client.helper;

import java.util.Set;

public interface URLInfo {
	String getParameter(String name);

	String[] getAllValues(String name);

	String getHostURL();

	Set<String> getParameterKeySet();
}
