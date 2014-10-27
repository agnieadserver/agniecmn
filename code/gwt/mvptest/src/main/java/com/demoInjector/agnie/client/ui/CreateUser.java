package com.demoInjector.agnie.client.ui;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;

import com.demoInjector.agnie.client.presenter.CreateuserPresenter;
import com.demoInjector.agnie.shared.USerDataBase.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class CreateUser extends Composite {

	private static EntryFormUiBinder	uiBinder	= GWT.create(EntryFormUiBinder.class);

	CreateuserPresenter					presenter	= null;

	interface EntryFormUiBinder extends UiBinder<Widget, CreateUser> {
	}

	@Inject
	public CreateUser() {
		initWidget(uiBinder.createAndBindUi(this));
		buttonSave.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String fName = getFirstName();
				String lName = getLastName();
				String age = getAge();

				User user = new User();

				user.setfName(fName);
				user.setlName(lName);
				user.setAge(Integer.parseInt(age));

				CreateUser.this.presenter.AddUsers(user);

			}
		});

		buttonCancel.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				CreateUser.this.presenter.showList();
			}
		});
	}

	public void setPresenter(CreateuserPresenter presenter) {
		this.presenter = presenter;
	}

	@UiField
	TextBox	textfName;

	@UiField
	TextBox	textLName;

	@UiField
	TextBox	textAge;

	@UiField
	Button	buttonSave;

	@UiField
	Button	buttonCancel;

	public String getFirstName() {

		if (textfName.getText().trim().equalsIgnoreCase("")) {
			return null;
		} else

			return textfName.getText();
	}

	public String getLastName() {
		if (textLName.getText().trim().equalsIgnoreCase("")) {
			return null;
		} else

			return textLName.getText();
	}

	public String getAge() {
		if (textAge.getText().trim().equalsIgnoreCase("")) {
			return null;
		} else

			return textAge.getText();

	}

	public void setfName(String fName) {
		textfName.setText(fName);
	}

	public void setlName(String lName) {
		textLName.setText(lName);

	}

	public void setAge(String age) {
		textAge.setText(age);
	}

}
