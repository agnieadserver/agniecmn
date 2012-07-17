package com.agnie.gwt.common.client.widget;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.user.client.ui.TextBox;
/**
 * TextBoxLabel is an extension of GWT TextBox to show  label as text field default value(in little bit light color).
 * @param String Label 
 *
 */
public class TextBoxLabel extends TextBox{
	private static TextBoxLabelResources	resource	= TextBoxLabelResources.INSTANCE;

	static {
		resource.css().ensureInjected();
	}
	
	public  String label=new String();

	public TextBoxLabel() {
		this(null);
	}

	public TextBoxLabel(String label) {
		
		setLabel(label);
		
		super.addFocusHandler(new FocusHandler() {
			
			@Override
			public void onFocus(FocusEvent event) {
				setText("");
				removeStyle();
			}
		});
		super.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
				String text=getText();
				if(text.trim().isEmpty()||text.trim().equals(getLabel())){
					setText(getLabel());
					addStyle();
				}
			}
		});
	}
	protected void removeStyle() {
		super.removeStyleName("text-field-label");
	}
	protected void addStyle(){
		super.addStyleName("text-field-label");
	}
	/**
	 * Sets default Label to text field,if end-user focus on text box this label disappears.
	 * @param label
	 */
	public void setLabel(String label) {
		if (label != null && !("".equals(label))) {
			this.label=label;
			addStyle();
			super.setText(this.label);
		}
	}
	/**
	 * returns default Label set by widget-user
	 * @return String label value
	 */
	public String getLabel(){
		return label;
	}
	/**
	 * returns textBox value entered by end-user.
	 * if end user don't enter any new value it returns empty.
	 */
	public String getText(){
		String text=super.getText();
		if(text.isEmpty()||text.equals(getLabel()))
			return "";
		else
			return text;
	}
}
