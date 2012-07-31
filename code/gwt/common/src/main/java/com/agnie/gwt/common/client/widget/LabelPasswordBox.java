package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;

/**
 * LabelPasswordBox is an extension of GWT LabelTextBox to show  label as text field default value(in little bit light color).
 *  
 *
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
				getElement().setAttribute("type","password");
			}
		});
		super.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent arg0) {
				String value=getText();
				GWT.log("value=="+value);
				if(value.equals("")||value.equals("password")){
					getElement().setAttribute("type","text");
				}else{
					getElement().setAttribute("type","password");
				}
			}
		});
	}
	
	public String getText(){
		String text=super.getText();
		if(text.isEmpty()||text.equals(getLabel()))
			return "";
		else
			return text;
	}
		
}
