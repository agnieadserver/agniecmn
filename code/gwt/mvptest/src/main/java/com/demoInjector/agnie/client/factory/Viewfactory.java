package com.demoInjector.agnie.client.factory;

import com.demoInjector.agnie.client.ui.CreateUser;
import com.demoInjector.agnie.client.ui.EditUser;
import com.demoInjector.agnie.client.ui.ListUsers;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class Viewfactory {

	@Inject
	CreateUser	createUserPage;

	@Inject
	ListUsers	listuserPage;

	@Inject
	EditUser	editUser;

	public CreateUser getCreateUserPage() {
		return createUserPage;
	}

	public ListUsers getListuserPage() {
		return listuserPage;
	}

	public EditUser getEditPage() {
		return editUser;
	}

}
