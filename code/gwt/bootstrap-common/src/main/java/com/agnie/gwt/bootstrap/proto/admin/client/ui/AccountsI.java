package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.IsWidget;

public interface AccountsI extends IsWidget {

	void setName(String name);

	void setProfileImage(String profileURL);

	void setMyProfileUrl(String myProfileURL);

	void setUpdatePasswordURL(String updatePasswordURL);

	HandlerRegistration addLogoutHandler(ClickHandler handler);

	void setTimeZone(String timeZone);
}
