/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.client.ui;

import com.agnie.gwt.client.service.RecaptchService;
import com.agnie.gwt.client.service.RecaptchServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.sfeir.captcha.client.ui.Captcha;
import com.sfeir.captcha.shared.CaptchaResult;

public class RecaptchaView extends Composite {
	interface MyUiBinder extends UiBinder<Widget, RecaptchaView> {
	}

	private static MyUiBinder	uiBinder	= GWT.create(MyUiBinder.class);

	@UiField(provided = true)
	Captcha						captcha		= new Captcha("6LdyNO8SAAAAAAdtyMbSMTqtWbx27YUe3JajZmfH");
	@UiField
	Button						validate;
	RecaptchServiceAsync		service		= GWT.create(RecaptchService.class);

	public RecaptchaView() {
		initWidget(uiBinder.createAndBindUi(this));
		captcha.addLoadHandler(new Captcha.LoadHandler() {

			@Override
			public void onLoad() {
				// Window.alert("Ok this is loaded...");
			}
		});
	}

	@UiHandler("validate")
	public void validate(ClickEvent event) {
		CaptchaResult result = captcha.validateCaptcha();
		service.validate(result, new AsyncCallback<Boolean>() {

			public void onSuccess(Boolean result) {
				if (result) {
					Window.alert("Valid");
				} else {
					Window.alert("Invalid");
				}
			}

			public void onFailure(Throwable caught) {
				Window.alert("Server error: " + caught);
			}
		});
	}
}
