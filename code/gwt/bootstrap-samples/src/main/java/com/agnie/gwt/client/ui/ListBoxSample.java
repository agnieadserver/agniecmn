/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.client.ui;

import java.util.ArrayList;
import java.util.List;

import org.gwtbootstrap3.extras.select.client.ui.Option;

import com.agnie.common.gwt.serverclient.client.renderer.Title;
import com.agnie.gwt.bootstrap.extras.client.widgets.Select;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Pandurang Patil 20-Aug-2014
 *
 */
public class ListBoxSample extends Composite {

	private static SamplePageUiBinder	uiBinder	= GWT.create(SamplePageUiBinder.class);

	interface SamplePageUiBinder extends UiBinder<Widget, ListBoxSample> {
	}

	private class Person implements Title {
		private String	fname;
		private String	lname;

		public Person(String fname, String lname) {
			this.fname = fname;
			this.lname = lname;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.agnie.common.gwt.serverclient.client.renderer.Title#getTitle()
		 */
		@Override
		public String getTitle() {
			return fname + " - " + lname;
		}
	}

	List<Person>	list	= new ArrayList<ListBoxSample.Person>();

	@UiField
	Select<Person>	listbox;

	public ListBoxSample() {
		initWidget(uiBinder.createAndBindUi(this));
		list.add(new Person("Pandurang", "Patil"));
		list.add(new Person("Sandy", "Mukho"));
		list.add(new Person("Raj", "Dubey"));
		list.add(new Person("Saurabh", "Sameer"));
		listbox.setList(list);
		listbox.addItem(new Person("Ramu", "Kaka"));
		listbox.setSelected(list.get(3));
		listbox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				StringBuffer test = new StringBuffer();
				for (String selected : listbox.getAllSelectedValues()) {
					test.append("- " + selected);
				}
				Window.alert("Changed...." + listbox.getSelectedItem().getTitle());
			}
		});
	}

	@UiHandler("add")
	public void addHandler(ClickEvent event) {
		Option option = new Option();
		option.setText("new option");
		option.setId("90");
		listbox.add(option);
		listbox.refresh();
	}
}
