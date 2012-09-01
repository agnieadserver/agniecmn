package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

/**
 * ErrorTextBox is a TextBox with error message
 * 
 * 
 */
public class TextBox extends Composite {
	private static TextBoxResources	resource	= TextBoxResources.INSTANCE;

	static {
		resource.css().ensureInjected();
	}

	interface MyUiBinder extends UiBinder<Widget, TextBox> {
	}

	private static MyUiBinder				uiBinder		= GWT.create(MyUiBinder.class);

	@UiField
	com.google.gwt.user.client.ui.TextBox	textBox;

	@UiField
	HTMLPanel								errorPan;

	@UiField
	ImageElement							img;

	@UiField
	SpanElement								message;

	@UiField
	Image									close;

	protected HTMLPanel						container;
	public int								messSpanwidth	= 0;

	public TextBox() {

		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		initWidget(container);
		errorPan.setVisible(false);

	}

	public void setErrorMessVisible(boolean visible) {
		errorPan.setVisible(visible);
	}

	public void setErrorPanWidth(int width) {
		errorPan.setWidth(width + "px");
		messSpanwidth = width - 70;
		String messSpanWidthStr = messSpanwidth + "px";
		this.message.setAttribute("style", "width:" + messSpanWidthStr + ";"); 
		
		close.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				errorPan.setVisible(false);
			}
		});
	}

	public void setErrorPanHeight(int height) {
		errorPan.setHeight(height + "px");
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

	public static TextBoxResources getResources() {
		return resource;
	}

}
