/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.client.ui;

import org.gwtbootstrap3.extras.select.client.ui.Select;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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

	@UiField
	Select	listbox;

	public ListBoxSample() {
		initWidget(uiBinder.createAndBindUi(this));
		listbox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				StringBuffer test = new StringBuffer();
				for (String selected : listbox.getAllSelectedValues()) {
					test.append("- " + selected);
				}
				// Window.alert("Changed...." + test);

			}
		});
	}
}
