package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.IsWidget;

public interface AccountsI extends IsWidget {

	public void setName(String name);

	public void setProfileImage(String profileURL);

	public void setMyProfileUrl(String myProfileURL);

	public void setUpdatePasswordURL(String updatePasswordURL);

	public HandlerRegistration addLogoutHandler(ClickHandler handler);
}
