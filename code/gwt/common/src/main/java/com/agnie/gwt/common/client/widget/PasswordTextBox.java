package com.agnie.gwt.common.client.widget;

/**
 * A type of widget PasswordBox extends existing <br>
 * TextBox 'widget with error message feature'.
 * 
 * 
 */
public class PasswordTextBox extends TextBox {

	public PasswordTextBox() {
		super.textBox.getElement().setAttribute("type", "password");
	}

}
