package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import java.util.Date;

import org.gwtbootstrap3.client.ui.Anchor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Account extends Composite implements AccountsI {

	@UiField
	Anchor myprofile;

	@UiField
	Anchor updatePassword;

	@UiField
	Anchor logout;

	@UiField
	SpanElement name;

	@UiField
	SpanElement timezone;

	@UiField
	ImageElement image_user;

	private static AccountUiBinder uiBinder = GWT.create(AccountUiBinder.class);

	interface AccountUiBinder extends UiBinder<Widget, Account> {
	}

	public Account() {
		initWidget(uiBinder.createAndBindUi(this));
		Timer timer = new Timer() {
			public void run() {
				timezone.setInnerHTML(DateTimeFormat
						.getFormat("dd EEE MMM yyyy - hh:mm:ss")
						.format(new Date()).split("-")[0]);
			}
		};
		timer.schedule(1000);
	}

	@Override
	public void setName(String name) {
		this.name.setInnerHTML(name);
	}

	@Override
	public void setTimeZone(String str) {
		this.timezone.setInnerHTML(str);
	}

	@Override
	public void setProfileImage(String profileURL) {
		if (profileURL != null && !profileURL.isEmpty())
			image_user.setSrc(profileURL);
	}

	@Override
	public void setMyProfileUrl(String myProfileURL) {
		myprofile.setHref(myProfileURL);
	}

	@Override
	public void setUpdatePasswordURL(String updatePasswordURL) {
		updatePassword.setHref(updatePasswordURL);
	}

	public HandlerRegistration addMyProfileHandler(ClickHandler handler) {
		return myprofile.addClickHandler(handler);
	}

	public HandlerRegistration addUpdatePasswordHandler(ClickHandler handler) {
		return updatePassword.addClickHandler(handler);
	}

	@Override
	public HandlerRegistration addLogoutHandler(ClickHandler handler) {
		return logout.addClickHandler(handler);
	}

}
