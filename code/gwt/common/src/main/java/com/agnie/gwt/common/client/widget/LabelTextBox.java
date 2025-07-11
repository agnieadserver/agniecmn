/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.common.client.widget;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * LabelTextBox is an extension of GWT TextBox to show label as text field default value(in little bit light color).
 * 
 */
public class LabelTextBox extends TextBox {
	private static LabelTextBoxResources	resource	= LabelTextBoxResources.INSTANCE;

	static {
		resource.css().ensureInjected();
	}

	private String							label		= new String();

	public LabelTextBox() {
		this(null);
	}

	public LabelTextBox(String label) {

		setLabel(label);
		this.label = label;
		/* TODO:It is not working on some Chromium version so need to check. */
		// super.addFocusHandler(new FocusHandler() {
		//
		// @Override
		// public void onFocus(FocusEvent event) {
		// if (!dirtyFlag) {
		// setText("");
		// }
		// removeStyle();
		// }
		// });

		super.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				String text = getValue();
				if (text.isEmpty()) {
					reset();
				}
			}
		});

		super.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				removeStyle();
			}
		});

	}

	/**
	 * To hide errorPan when this widget getsRemoved from Parent.
	 */
	@Override
	public void onDetach() {
		super.onDetach();// To avoid Uncaught exception<Should only call onAttach when the widget is detached from the
							// browser's document>
		this.errorPan.hide();
		this.reset();
	}

	public void onFirstKeyPress(char character) {
		super.onFirstKeyPress(character);
		super.setText("");
	}

	@Override
	public void setText(String text) {
		if (text != null && !text.isEmpty()) {
			this.dirtyFlag = true;
			super.setText(text);
		}

	}

	/**
	 * To reset textbox with default label.
	 */
	public void reset() {
		super.reset();
		super.setText(getLabel());
		addStyle();
	}

	/**
	 * to get textbox
	 * 
	 * @return Text box
	 */

	public TextBox getTextBox() {
		return this;
	}

	/**
	 * to get ErrorMessage Panel
	 * 
	 * @return error panel
	 */
	public PopupPanel getErrorPan() {
		return this.errorPan;
	}

	protected void removeStyle() {
		super.textBox.removeStyleName("text-field-label");
	}

	protected void addStyle() {
		super.textBox.addStyleName("text-field-label");
	}

	/**
	 * Sets default Label to text field,if end-user focus on text box this label disappears.
	 * 
	 * @param label
	 *            field label
	 */
	public void setLabel(String label) {
		if (label != null && !("".equals(label))) {
			this.label = label;
			addStyle();
			super.setText(this.label);
		}
	}

	/**
	 * returns default Label set by widget-user
	 * 
	 * @return String label value
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * returns textBox value entered by end-user. if end user don't enter any new value it returns empty.
	 */

	@Override
	public String getValue() {
		String text = super.getValue();
		if (dirtyFlag)
			return text;
		else
			return "";
	}

}
