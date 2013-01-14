package com.agnie.gwt.common.client.widget;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * LabelTextBox is an extension of GWT TextBox to show label as text field default value(in little bit light color).
 * 
 * @param String
 *            Label
 * 
 */
public class LabelTextBox extends TextBox {
	private static LabelTextBoxResources	resource	= LabelTextBoxResources.INSTANCE;

	static {
		resource.css().ensureInjected();
	}

	private String							label		= new String();
	protected boolean						dirtyFlag	= false;

	public LabelTextBox() {
		this(null);
	}

	public LabelTextBox(String label) {

		setLabel(label);
		this.label=label;
		/*TODO:It is not working on some Chromium version so need to check.*/
//		super.addFocusHandler(new FocusHandler() {
//
//			@Override
//			public void onFocus(FocusEvent event) {
//				if (!dirtyFlag) {
//					setText("");
//				}
//				removeStyle();
//			}
//		});

		super.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				String text = getValue();
				if (text.trim().isEmpty()) {
					reset();
				}
			}
		});
		
		super.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (!dirtyFlag) {
					setText("");
				}
				removeStyle();
				dirtyFlag = true;
			}
		});

	}
	/**
	 * To reset textbox with default label.
	 */
	public void reset(){
		setText(getLabel());
		addStyle();
		dirtyFlag = false;
	}

	/**
	 * to get textbox
	 * 
	 * @return
	 */

	public TextBox getTextBox() {
		return this;
	}

	/**
	 * to get ErrorMessage Panel
	 * 
	 * @return
	 */
	public HTMLPanel getErrorPan() {
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
