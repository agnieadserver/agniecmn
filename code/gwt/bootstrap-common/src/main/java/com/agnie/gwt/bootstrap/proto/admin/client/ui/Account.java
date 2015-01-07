package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import org.gwtbootstrap3.client.ui.Anchor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Account extends Composite implements AccountsI {

	@UiField
	Anchor							myprofile;

	@UiField
	Anchor							updateprofile;

	@UiField
	Anchor							logout;

	@UiField
	SpanElement						name;

	@UiField
	ImageElement					image_user;

	private static AccountUiBinder	uiBinder	= GWT.create(AccountUiBinder.class);

	interface AccountUiBinder extends UiBinder<Widget, Account> {
	}

	public Account() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setName(String name) {
		this.name.setInnerHTML(name);
	}

	@Override
	public void setProfileImage(String profileURL) {
		image_user.setSrc(profileURL);
	}

	@Override
	public void setMyProfileUrl(String myProfileURL) {
		myprofile.setHref(myProfileURL);

	}

	@Override
	public void setUpdatePasswordURL(String updatePasswordURL) {
		updateprofile.setHref(updatePasswordURL);

	}

	@Override
	public void setLogoutUrl(String urlLogout) {
		logout.setHref(urlLogout);
	}

}
