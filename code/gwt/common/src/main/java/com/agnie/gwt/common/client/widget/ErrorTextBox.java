package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * ErrorTextBox is a TextBox with error message
 * 
 * 
 */
public class ErrorTextBox extends Composite {
	private static ErrorTextBoxResources	resource	= ErrorTextBoxResources.INSTANCE;

	static {
		resource.css().ensureInjected();
	}

	interface MyUiBinder extends UiBinder<Widget, ErrorTextBox> {
	}

	private static MyUiBinder	uiBinder	= GWT.create(MyUiBinder.class);

	@UiField
	TextBox						textBox;

	@UiField
	HTMLPanel					errorPan;

	@UiField
	ImageElement				img;

	@UiField
	SpanElement					message;

	@UiField
	AnchorElement				close;

	protected HTMLPanel			container;

	public ErrorTextBox() {

		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		initWidget(container);
		
//		textBox.addFocusHandler(new FocusHandler() {
//			
//			@Override
//			public void onFocus(FocusEvent event) {
//				errorPan.addStyleName(resource.css().errorPanDisplay());
//			}
//		});
	}

	public void setText(String text) {
		this.textBox.setText(text);
	}

	public void addBlurHandler(BlurHandler handler) {
		this.textBox.addBlurHandler(handler);
	}

	public void addClickHandler(ClickHandler handler) {
		this.textBox.addClickHandler(handler);
	}

	public void addFocusHandler(FocusHandler handler) {
		this.textBox.addFocusHandler(handler);
	}

	public void addKeyPressHandler(KeyPressHandler handler) {
		this.textBox.addKeyPressHandler(handler);
	}

	public void addKeyUpHandler(KeyUpHandler handler) {
		this.textBox.addKeyUpHandler(handler);
	}

	public void addKeyDownHandler(KeyDownHandler handler) {
		this.textBox.addKeyDownHandler(handler);
	}

	public void addStyleName(String style) {
		this.textBox.addStyleName(style);
	}

	public int getMaxLength() {
		return this.textBox.getMaxLength();
	}

	public String getText() {
		return this.textBox.getText();
	}

	public String getValue() {
		return this.textBox.getValue();
	}

//	public void removeFromParent() {
//		this.textBox.removeFromParent();
//	}

	public void removeStyleName(String style) {
		this.textBox.removeStyleName(style);
	}

	public void setFocus(boolean focused) {
		this.textBox.setFocus(focused);
	}

	public void setHeight(String height) {
		this.textBox.setHeight(height);
	}

	public void setPixelSize(int width, int height) {
		this.textBox.setPixelSize(width, height);
	}

	public void setSize(String width, String height) {
		this.textBox.setSize(width, height);
	}

	public void setMaxLength(int length) {
		this.textBox.setMaxLength(length);
	}

	public void setStyleName(String style) {
		this.textBox.setStyleName(style);
	}

	public void setValue(String value) {
		this.textBox.setValue(value);
	}

	public void setVisible(boolean visible) {
		this.textBox.setVisible(visible);
	}

	public void setWidth(String width) {
		this.textBox.setWidth(width);
	}

	public void setErrorMessage(String message) {
		this.message.setInnerText(message);
	}

	public static ErrorTextBoxResources getResources() {
		return resource;
	}
}
