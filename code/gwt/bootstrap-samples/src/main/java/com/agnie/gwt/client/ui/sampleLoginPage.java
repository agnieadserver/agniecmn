/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.client.ui;

import com.agnie.gwt.bootstrap.proto.admin.client.ui.RichPasswordBox;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.RichTextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ganesh Dawar
 *
 *         4:19:05 pm
 */
public class sampleLoginPage extends Composite {
	@UiField
	Button									login;
	@UiField
	RichTextBox								emailField;
	@UiField
	RichPasswordBox							pwdField;

	private static sampleLoginPageUiBinder	uiBinder	= GWT.create(sampleLoginPageUiBinder.class);

	interface sampleLoginPageUiBinder extends UiBinder<Widget, sampleLoginPage> {
	}

	/**
	 * Because this class has a default constructor, it can be used as a binder template. In other words, it can be used
	 * in other *.ui.xml files as follows: <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
	 * xmlns:g="urn:import:**user's package**"> <g:**UserClassName**>Hello!</g:**UserClassName> </ui:UiBinder> Note that
	 * depending on the widget that is used, it may be necessary to implement HasHTML instead of HasText.
	 */
	public sampleLoginPage() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("login")
	public void loginClickHandler(ClickEvent event) {
		emailField.clearErrorMessage();
		pwdField.clearErrorMessage();
		if (emailField.getValue().isEmpty()) {
			emailField.setErrorMessage("Email required...");
		}
		if (pwdField.getValue().isEmpty()) {
			pwdField.setErrorMessage("Password required...");
		}
	}
}
