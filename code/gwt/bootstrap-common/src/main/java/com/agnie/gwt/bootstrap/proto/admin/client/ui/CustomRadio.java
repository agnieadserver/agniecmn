/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Pandurang Patil 28-Aug-2014
 *
 */
public class CustomRadio extends Composite {

	private static ToggleSwitchUiBinder	uiBinder	= GWT.create(ToggleSwitchUiBinder.class);

	interface ToggleSwitchUiBinder extends UiBinder<Widget, CustomRadio> {
	}

	@UiField
	HTMLPanel		mainpanel;

	@UiField
	InputElement	radio;

	@UiField
	LabelElement	label;

	CustomRadioType	lastType;

	public CustomRadio() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setChecked(boolean flag) {
		radio.setChecked(flag);
	}

	public boolean isChecked() {
		return radio.isChecked();
	}

	public void setLabel(String lable) {
		label.setInnerText(lable);
	}

	public String getLabel() {
		return label.getInnerText();
	}

	public void setName(String name) {
		radio.setName(name);
	}

	public void setType(final CustomRadioType type) {
		if (lastType != null) {
			mainpanel.removeStyleName(lastType.getType());
		}
		mainpanel.addStyleName(type.getType());
		lastType = type;
	}

}
