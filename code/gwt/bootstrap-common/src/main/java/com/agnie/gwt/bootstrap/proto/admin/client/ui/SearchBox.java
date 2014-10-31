/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author rajgaurav
 *
 */
public class SearchBox extends Composite {

	private static SearchComponentUiBinder	uiBinder	= GWT.create(SearchComponentUiBinder.class);

	interface SearchComponentUiBinder extends UiBinder<Widget, SearchBox> {
	}

	public SearchBox() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button	button;

	@UiField
	TextBox	input;

	public void setPlaceHolder(String placeHolder) {
		input.setPlaceholder(placeHolder);
	}

	public HandlerRegistration addClickHandler(ClickHandler clickHandler) {
		return button.addClickHandler(clickHandler);
	}

	/**
	 * get text from searchBox
	 */
	public String getText() {

		return input.getText();

	}
}
