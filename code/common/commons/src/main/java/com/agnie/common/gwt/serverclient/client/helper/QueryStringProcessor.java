/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.gwt.serverclient.client.helper;

import com.agnie.common.gwt.serverclient.client.enums.QueryString;
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
