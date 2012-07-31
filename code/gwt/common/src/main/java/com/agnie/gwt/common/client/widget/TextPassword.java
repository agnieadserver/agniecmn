package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TextPassword extends Composite {
	private static TextPasswordResources	resource	= TextPasswordResources.INSTANCE;

	static {
		resource.css().ensureInjected();
	}

	interface MyUiBinder extends UiBinder<Widget, TextPassword> {
	}

	private static MyUiBinder	uiBinder	= GWT.create(MyUiBinder.class);

	protected HTMLPanel			container;
	@UiField
	TextBox						textBox;
	@UiField
	PasswordTextBox				passBox;

	public TextPassword() {
		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		initWidget(container);
		textBox.setText("password");
		passBox.setVisible(false);
		textBox.addFocusHandler(new FocusHandler() {

			@Override
			public void onFocus(FocusEvent event) {
				passBox.setVisible(true);
				passBox.setFocus(true);
				textBox.setVisible(false);
			}
		});
		textBox.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				GWT.log("textBox text==" + textBox.getText());
				if (("password".equals(textBox.getText())) || textBox.getText().equals("")) {
					textBox.setVisible(true);
					textBox.setText("password");
				} else {
					textBox.setVisible(false);
					passBox.setVisible(true);
					passBox.setText(textBox.getText());
				}
			}
		});
	}

	public static TextPasswordResources getResources() {
		return resource;
	}

}
