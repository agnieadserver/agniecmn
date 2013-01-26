package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * LabelPasswordBox is an 'extended GWT LabelTextBox' to show label(e.g.'password')<br>
 * as text field default value(in little bit light color).<br>
 * <br>
 * on focus it switch to password field until end user enter valid password <br>
 * other than if end user enter invalid or null it switches to text field
 */
public class LabelPasswordBox extends LabelTextBox {
	private static LabelTextBoxResources	resource	= LabelTextBoxResources.INSTANCE;

	static {
		resource.css().ensureInjected();
	}

	public LabelPasswordBox() {
		this(null);
	}

	public LabelPasswordBox(String label) {

		super.setLabel(label);

		super.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				changeTypeToPass();
				removeStyle();
			}
		});
		// super.addFocusHandler(new FocusHandler() {
		//
		// @Override
		// public void onFocus(FocusEvent arg0) {
		// changeTypeToPass();
		// removeStyle();
		// }
		// });
		super.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent arg0) {
				String value = getValue();
				GWT.log("value==" + value);
				if ("".equals(value)) {
					changeTypeToText();
					addStyle();
				} else {
					changeTypeToPass();
					removeStyle();
				}
			}
		});

	}

	/**
	 * To reset field with default label.
	 */
	public void reset() {
		super.reset();
		changeTypeToText();
	}
	

	/**
	 * to get ErrorMessage Panel
	 * 
	 * @return
	 */
	public PopupPanel getErrorPan() {
		return this.errorPan;
	}

	/**
	 * changes type of text field to password field
	 */
	private void changeTypeToPass() {
		super.textBox.getElement().setAttribute("type", "password");
	}

	/**
	 * To resume to text field / changes type of password field to text field
	 */
	private void changeTypeToText() {
		super.textBox.getElement().setAttribute("type", "text");
	}
}
