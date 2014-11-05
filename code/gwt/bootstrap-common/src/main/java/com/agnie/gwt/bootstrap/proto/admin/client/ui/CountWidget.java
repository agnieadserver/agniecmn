/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.Icon;
import org.gwtbootstrap3.client.ui.constants.IconType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author rajgaurav
 *
 */
public class CountWidget extends Composite {

	private static CountWidgetUiBinder	uiBinder	= GWT.create(CountWidgetUiBinder.class);

	interface CountWidgetUiBinder extends UiBinder<Widget, CountWidget> {
	}

	public CountWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Heading	title;

	@UiField
	Heading	count;

	@UiField
	Anchor	anchormsg;

	@UiField
	Icon	icon;

	public void setTitle(String title) {
		this.title.setText(title);
	}

	public void setAnchorTitle(String message, String href) {
		anchormsg.setText(message);
		anchormsg.setHref(href);
	}

	public void setCount(Double value) {
		count.setText(value + "");
	}

	public double getCount() {
		return Double.parseDouble(count.getText());
	}

	public void seticon(IconType icontype) {
		icon.setType(icontype);
	}

}
