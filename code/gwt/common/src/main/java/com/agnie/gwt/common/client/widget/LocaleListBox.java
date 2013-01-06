/**
 * 
 */

package com.agnie.gwt.common.client.widget;

import java.util.ArrayList;
import java.util.List;

import com.agnie.common.gwt.serverclient.client.enums.QueryString;
import com.agnie.common.gwt.serverclient.client.helper.URLGenerator;
import com.agnie.common.gwt.serverclient.client.helper.URLInfo;
import com.agnie.gwt.common.client.enums.Language;
import com.agnie.gwt.common.client.renderer.LanguageCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;

/**
 * Common language selector widget
 * 
 */
public class LocaleListBox extends CustomListBox<Language> {

	public static final String	SELECT	= "select";

	private URLGenerator		ug		= new URLGenerator();

	List<Language>				list;

	public LocaleListBox() {
		super(new LanguageCell());
		setTitle(QueryString.LOCALE.getKey());
		setList(getList());
		init();
	}

	public void init() {
		String selectedLocale = Location.getParameter(QueryString.LOCALE.getKey());
		if (selectedLocale == null || "".equals(selectedLocale)) {
			selectedLocale = Language.ENGLISH.getCode();
		}

		int localeCount = getItemCount();
		for (int index = 0; index < localeCount; index++) {
			if (list.get(index).getCode().equalsIgnoreCase(selectedLocale)) {
				setSelectedItem(list.get(index), true);
				break;
			}
		}

		/*
		 * As per single thread functionality of Browsers,To avoid infinite loop ,putting addChangeHandler in Sheduler
		 */

		Scheduler.get().scheduleDeferred(new Command() {
			public void execute() {
				addChangeHandler(new CustomListBox.ChangeHandler() {

					@Override
					public void onChange() {

						String selectLocale = getSelectedLocale().getCode();

						if (!SELECT.equals(selectLocale)) {
							Window.Location.assign(ug.getChangeLocaleURL((URLInfo) GWT.create(URLInfo.class), selectLocale));
						}
					}
				});
			}
		});
	}

	public List<Language> getList() {
		list = new ArrayList<Language>();

		for (Language language : Language.values()) {
			list.add(language);
		}
		return list;
	}

	/**
	 * to retrieve selected locale
	 * 
	 * @return
	 */
	public Language getSelectedLocale() {
		return getSelectedItem();
	}

}
