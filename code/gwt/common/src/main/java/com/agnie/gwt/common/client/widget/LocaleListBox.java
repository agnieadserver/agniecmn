/**
 * 
 */

package com.agnie.gwt.common.client.widget;

import com.agnie.gwt.common.client.enums.Language;
import com.agnie.gwt.common.client.enums.QueryString;
import com.agnie.gwt.common.client.helper.URLGenerator;
import com.agnie.gwt.common.client.helper.URLInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.ListBox;

/**
 * Common language selector widget
 * 
 */
public class LocaleListBox extends ListBox {

	public static final String	SELECT	= "select";

	public LocaleListBox() {

		setName(QueryString.LOCALE.getKey());
		String selectedLocale = Location.getParameter(QueryString.LOCALE.getKey());
		if (selectedLocale == null || "".equals(selectedLocale)) {
			selectedLocale = Language.ENGLISH.getCode();
		}

		for (Language language : Language.values()) {
			addItem(language.getLabel(), language.getCode());

		}

		int localeCount = getItemCount();
		for (int index = 0; index < localeCount; index++) {
			if (getValue(index).equalsIgnoreCase(selectedLocale)) {
				setSelectedIndex(index);
				break;
			}
		}

		addChangeHandler(new ChangeHandler() {

			public void onChange(ChangeEvent event) {

				String selectLocale = getSelectedLocale();
				if (!SELECT.equals(selectLocale)) {
					Window.Location.assign(URLGenerator.getChangeLocaleURL((URLInfo) GWT.create(URLInfo.class), selectLocale));
				}
			}
		});
	}

	/**
	 * to retrieve selected locale
	 * 
	 * @return
	 */
	public String getSelectedLocale() {

		return getValue(getSelectedIndex());
	}

}
