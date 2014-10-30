package com.demoInjector.agnie.client.ui;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;

import com.demoInjector.agnie.client.presenter.EditUserPresenter;
import com.demoInjector.agnie.shared.USerDataBase.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class EditUser extends Composite {

	private static EditUserUiBinder	uiBinder	= GWT.create(EditUserUiBinder.class);

	User							user		= null;
	EditUserPresenter				presenter;

	interface EditUserUiBinder extends UiBinder<Widget, EditUser> {
	}

	public EditUser() {
		initWidget(uiBinder.createAndBindUi(this));

		buttonSave.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String fName = getfName();
				String lName = getlName();
				String age = getAge();

				if (EditUser.this.user == null)
					return;
				EditUser.this.user.setfName(fName);
				EditUser.this.user.setlName(lName);
				EditUser.this.user.setAge(Integer.parseInt(age));
				EditUser.this.presenter.Edituser(user);
			}
		});

		buttonCancel.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				EditUser.this.presenter.showList();
			}
		});

	}

	public void setEdituser(User user) {
		this.user = user;
		textfName.setText(this.user.getfName());
		textLName.setText(this.user.getlName());
		textAge.setText(this.user.getAge() + "");

	}

	@UiField
	Button	buttonSave;

	@UiField
	Button	buttonCancel;

	@UiField
	TextBox	textfName;

	@UiField
	TextBox	textLName;

	@UiField
	TextBox	textAge;

	public String getfName() {
		return textfName.getText().toString();
	}

	public String getlName() {
		return textLName.getText().toString();
	}

	public String getAge() {
		return textAge.getText().toString();
	}

	public void setEdituserPresenter(EditUserPresenter presenter) {
		this.presenter = presenter;
	}

	public void setEmpty() {

		// textfName.setText("");
		// textLName.setText("");
		// textAge.setText("");
	}

}
