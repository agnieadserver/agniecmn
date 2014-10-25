package com.sample.celltable.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sample.celltable.shared.USerDataBase.User;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;

	void listUserData(int start, int range, AsyncCallback<List<User>> callback);

	void addUser(User user, AsyncCallback<Void> callback);
}
