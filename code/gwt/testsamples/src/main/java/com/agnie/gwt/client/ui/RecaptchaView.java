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
