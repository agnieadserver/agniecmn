package com.agnie.gwt.common.client.helper;

import com.agnie.gwt.common.client.enums.QueryString;
import com.google.gwt.user.client.Window.Location;

/**
 * to retrieve common query string parameters.
 * 
 */
public class QueryStringProcessor {

	public static String	DEFAULT_LOCALE	= "en";
	private static String	currentLocale	= null;

	public static String getCurrentLocale() {

		if (currentLocale == null) {
			String selectedLocale = Location.getParameter(QueryString.LOCALE.getKey());
			if (selectedLocale != null && !("".equals(selectedLocale))) {
				currentLocale = selectedLocale;
			} else {
				currentLocale = DEFAULT_LOCALE;
			}
		}
		return currentLocale;
	}
}
