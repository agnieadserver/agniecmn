package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * LabelPasswordBox is an 'extended GWT LabelTextBox' to show  label(e.g.'password')<br> as text field default value(in little bit light color).<br>
 *  <br>on focus it switch to password field until end user enter valid password <br>other than 
 *      if end user enter invalid or null it switches to text field
 */
public class LabelPasswordBox extends LabelTextBox{
	private static LabelTextBoxResources	resource	= LabelTextBoxResources.INSTANCE;

	static {
		resource.css().ensureInjected();
	}
	

	public LabelPasswordBox() {
		this(null);
	}

	public LabelPasswordBox(String label) {
		
		
		super.addFocusHandler(new FocusHandler() {
			
			@Override
			public void onFocus(FocusEvent arg0) {
				changeTypeToPass();
				removeStyle();
			}
		});
		super.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent arg0) {
				String value=getValue();
				GWT.log("value=="+value);
				if("".equals(value)){
					changeTypeToText();
					addStyle();
				}else{
					changeTypeToPass();
					removeStyle();
				}
			}
		});
	}
	
	/**
	 * to get textbox
	 * @return
	 */
	public TextBox getTextBox() {
		return this.getTextBox();
	}
	
	/**
	 * to get ErrorMessage Panel
	 * @return
	 */
	public HTMLPanel getErrorPan(){
		return this.errorPan;
	}
	
	/**
	 * changes type of text field to password field
	 */
	private void changeTypeToPass(){
		super.textBox.getElement().setAttribute("type", "password");
	}
	private void changeTypeToText(){
		super.textBox.getElement().setAttribute("type", "text");
	}
}
