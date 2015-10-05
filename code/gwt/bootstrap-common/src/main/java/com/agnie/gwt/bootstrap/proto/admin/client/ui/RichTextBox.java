/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/

package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.DragEndHandler;
import com.google.gwt.event.dom.client.DragEnterHandler;
import com.google.gwt.event.dom.client.DragHandler;
import com.google.gwt.event.dom.client.DragLeaveHandler;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.GestureChangeHandler;
import com.google.gwt.event.dom.client.GestureEndHandler;
import com.google.gwt.event.dom.client.GestureStartHandler;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.dom.client.TouchCancelHandler;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ganesh Dawar
 *
 *         2:04:22 pm
 */
public class RichTextBox extends Composite {
	@UiField()
	protected TextBox	input;
	@UiField
	LabelElement		label;
	@UiField
	LabelElement		errorMessage;
	@UiField
	MyStyle				style;

	interface MyStyle extends CssResource {
		String notempty();

		String success();

		String error();
	}

	private static RichTextBoxUiBinder	uiBinder	= GWT.create(RichTextBoxUiBinder.class);

	interface RichTextBoxUiBinder extends UiBinder<Widget, RichTextBox> {
	}

	public RichTextBox() {
		this("");
	}

	public RichTextBox(String label) {
		initWidget(uiBinder.createAndBindUi(this));
		input.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				valueChanged();
			}
		});
		setLabel(label);
	}

	private void valueChanged() {
		if (input.getValue() != "") {
			RichTextBox.this.label.addClassName(style.notempty());
		} else {
			RichTextBox.this.label.removeClassName(style.notempty());
		}
	}

	public void setLabel(String label) {
		this.label.setInnerText(label);
	}

	public void setErrorMessage(String message) {
		errorMessage.setInnerText(message);
		input.removeStyleName(style.success());
		input.addStyleName(style.error());
	}

	public void clearErrorMessage() {
		errorMessage.setInnerText("");
		input.addStyleName(style.success());
		input.removeStyleName(style.error());
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.Widget#addAttachHandler(com.google.gwt.event.logical.shared.AttachEvent.Handler)
	 */
	public HandlerRegistration addAttachHandler(Handler handler) {
		return input.addAttachHandler(handler);
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.TextBox#getMaxLength()
	 */
	public int getMaxLength() {
		return input.getMaxLength();
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.TextBoxBase#getValue()
	 */
	public String getValue() {
		return input.getValue();
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.TextBox#getVisibleLength()
	 */
	public int getVisibleLength() {
		return input.getVisibleLength();
	}

	/**
	 * @param length
	 * @see com.google.gwt.user.client.ui.TextBox#setMaxLength(int)
	 */
	public void setMaxLength(int length) {
		input.setMaxLength(length);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.ValueBoxBase#addChangeHandler(com.google.gwt.event.dom.client.ChangeHandler)
	 */
	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return input.addChangeHandler(handler);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.ValueBoxBase#addValueChangeHandler(com.google.gwt.event.logical.shared.ValueChangeHandler)
	 */
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
		return input.addValueChangeHandler(handler);
	}

	/**
	 * 
	 * @see com.google.gwt.user.client.ui.ValueBoxBase#cancelKey()
	 */
	public void cancelKey() {
		input.cancelKey();
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.ValueBoxBase#getCursorPos()
	 */
	public int getCursorPos() {
		return input.getCursorPos();
	}

	/**
	 * @param event
	 * @see com.google.gwt.user.client.ui.Widget#fireEvent(com.google.gwt.event.shared.GwtEvent)
	 */
	public void fireEvent(GwtEvent<?> event) {
		input.fireEvent(event);
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.ValueBoxBase#getDirection()
	 */
	public Direction getDirection() {
		return input.getDirection();
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.ValueBoxBase#getDirectionEstimator()
	 */
	public DirectionEstimator getDirectionEstimator() {
		return input.getDirectionEstimator();
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.ValueBoxBase#getName()
	 */
	public String getName() {
		return input.getName();
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.ValueBoxBase#getSelectedText()
	 */
	public String getSelectedText() {
		return input.getSelectedText();
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addBlurHandler(com.google.gwt.event.dom.client.BlurHandler)
	 */
	public HandlerRegistration addBlurHandler(BlurHandler handler) {
		return input.addBlurHandler(handler);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addClickHandler(com.google.gwt.event.dom.client.ClickHandler)
	 */
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return input.addClickHandler(handler);
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.ValueBoxBase#getSelectionLength()
	 */
	public int getSelectionLength() {
		return input.getSelectionLength();
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addDoubleClickHandler(com.google.gwt.event.dom.client.DoubleClickHandler)
	 */
	public HandlerRegistration addDoubleClickHandler(DoubleClickHandler handler) {
		return input.addDoubleClickHandler(handler);
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.ValueBoxBase#getText()
	 */
	public String getText() {
		return input.getText();
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addDragEndHandler(com.google.gwt.event.dom.client.DragEndHandler)
	 */
	public HandlerRegistration addDragEndHandler(DragEndHandler handler) {
		return input.addDragEndHandler(handler);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addDragEnterHandler(com.google.gwt.event.dom.client.DragEnterHandler)
	 */
	public HandlerRegistration addDragEnterHandler(DragEnterHandler handler) {
		return input.addDragEnterHandler(handler);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addDragHandler(com.google.gwt.event.dom.client.DragHandler)
	 */
	public HandlerRegistration addDragHandler(DragHandler handler) {
		return input.addDragHandler(handler);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addDragLeaveHandler(com.google.gwt.event.dom.client.DragLeaveHandler)
	 */
	public HandlerRegistration addDragLeaveHandler(DragLeaveHandler handler) {
		return input.addDragLeaveHandler(handler);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addDragOverHandler(com.google.gwt.event.dom.client.DragOverHandler)
	 */
	public HandlerRegistration addDragOverHandler(DragOverHandler handler) {
		return input.addDragOverHandler(handler);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addDragStartHandler(com.google.gwt.event.dom.client.DragStartHandler)
	 */
	public HandlerRegistration addDragStartHandler(DragStartHandler handler) {
		return input.addDragStartHandler(handler);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addDropHandler(com.google.gwt.event.dom.client.DropHandler)
	 */
	public HandlerRegistration addDropHandler(DropHandler handler) {
		return input.addDropHandler(handler);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addFocusHandler(com.google.gwt.event.dom.client.FocusHandler)
	 */
	public HandlerRegistration addFocusHandler(FocusHandler handler) {
		return input.addFocusHandler(handler);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addGestureChangeHandler(com.google.gwt.event.dom.client.GestureChangeHandler)
	 */
	public HandlerRegistration addGestureChangeHandler(GestureChangeHandler handler) {
		return input.addGestureChangeHandler(handler);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addGestureEndHandler(com.google.gwt.event.dom.client.GestureEndHandler)
	 */
	public HandlerRegistration addGestureEndHandler(GestureEndHandler handler) {
		return input.addGestureEndHandler(handler);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addGestureStartHandler(com.google.gwt.event.dom.client.GestureStartHandler)
	 */
	public HandlerRegistration addGestureStartHandler(GestureStartHandler handler) {
		return input.addGestureStartHandler(handler);
	}

	/**
	 * 
	 * @see com.google.gwt.user.client.ui.ValueBoxBase#selectAll()
	 */
	public void selectAll() {
		input.selectAll();
	}

	/**
	 * @param align
	 * @see com.google.gwt.user.client.ui.ValueBoxBase#setAlignment(com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment)
	 */
	public void setAlignment(TextAlignment align) {
		input.setAlignment(align);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addKeyDownHandler(com.google.gwt.event.dom.client.KeyDownHandler)
	 */
	public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
		return input.addKeyDownHandler(handler);
	}

	/**
	 * @param pos
	 * @see com.google.gwt.user.client.ui.ValueBoxBase#setCursorPos(int)
	 */
	public void setCursorPos(int pos) {
		input.setCursorPos(pos);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addKeyPressHandler(com.google.gwt.event.dom.client.KeyPressHandler)
	 */
	public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
		return input.addKeyPressHandler(handler);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addKeyUpHandler(com.google.gwt.event.dom.client.KeyUpHandler)
	 */
	public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		return input.addKeyUpHandler(handler);
	}

	/**
	 * @param direction
	 * @see com.google.gwt.user.client.ui.ValueBoxBase#setDirection(com.google.gwt.i18n.client.HasDirection.Direction)
	 */
	public void setDirection(Direction direction) {
		input.setDirection(direction);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addMouseDownHandler(com.google.gwt.event.dom.client.MouseDownHandler)
	 */
	public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
		return input.addMouseDownHandler(handler);
	}

	/**
	 * @param enabled
	 * @see com.google.gwt.user.client.ui.ValueBoxBase#setDirectionEstimator(boolean)
	 */
	public void setDirectionEstimator(boolean enabled) {
		input.setDirectionEstimator(enabled);
	}

	/**
	 * @param directionEstimator
	 * @see com.google.gwt.user.client.ui.ValueBoxBase#setDirectionEstimator(com.google.gwt.i18n.shared.DirectionEstimator)
	 */
	public void setDirectionEstimator(DirectionEstimator directionEstimator) {
		input.setDirectionEstimator(directionEstimator);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addMouseMoveHandler(com.google.gwt.event.dom.client.MouseMoveHandler)
	 */
	public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
		return input.addMouseMoveHandler(handler);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addMouseOutHandler(com.google.gwt.event.dom.client.MouseOutHandler)
	 */
	public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
		return input.addMouseOutHandler(handler);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addMouseOverHandler(com.google.gwt.event.dom.client.MouseOverHandler)
	 */
	public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
		return input.addMouseOverHandler(handler);
	}

	/**
	 * @param name
	 * @see com.google.gwt.user.client.ui.ValueBoxBase#setName(java.lang.String)
	 */
	public void setName(String name) {
		input.setName(name);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addMouseUpHandler(com.google.gwt.event.dom.client.MouseUpHandler)
	 */
	public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
		return input.addMouseUpHandler(handler);
	}

	/**
	 * @param readOnly
	 * @see com.google.gwt.user.client.ui.ValueBoxBase#setReadOnly(boolean)
	 */
	public void setReadOnly(boolean readOnly) {
		input.setReadOnly(readOnly);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addMouseWheelHandler(com.google.gwt.event.dom.client.MouseWheelHandler)
	 */
	public HandlerRegistration addMouseWheelHandler(MouseWheelHandler handler) {
		return input.addMouseWheelHandler(handler);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addTouchCancelHandler(com.google.gwt.event.dom.client.TouchCancelHandler)
	 */
	public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler) {
		return input.addTouchCancelHandler(handler);
	}

	/**
	 * @param pos
	 * @param length
	 * @see com.google.gwt.user.client.ui.ValueBoxBase#setSelectionRange(int, int)
	 */
	public void setSelectionRange(int pos, int length) {
		input.setSelectionRange(pos, length);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addTouchEndHandler(com.google.gwt.event.dom.client.TouchEndHandler)
	 */
	public HandlerRegistration addTouchEndHandler(TouchEndHandler handler) {
		return input.addTouchEndHandler(handler);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addTouchMoveHandler(com.google.gwt.event.dom.client.TouchMoveHandler)
	 */
	public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler) {
		return input.addTouchMoveHandler(handler);
	}

	/**
	 * @param handler
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#addTouchStartHandler(com.google.gwt.event.dom.client.TouchStartHandler)
	 */
	public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
		return input.addTouchStartHandler(handler);
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.FocusWidget#getTabIndex()
	 */
	public int getTabIndex() {
		return input.getTabIndex();
	}

	/**
	 * @param text
	 * @see com.google.gwt.user.client.ui.ValueBoxBase#setText(java.lang.String)
	 */
	public void setText(String text) {
		input.setText(text);
		valueChanged();
	}

	/**
	 * @param value
	 * @see com.google.gwt.user.client.ui.ValueBoxBase#setValue(java.lang.Object)
	 */
	public void setValue(String value) {
		input.setValue(value);
		valueChanged();
	}

	/**
	 * @param value
	 * @param fireEvents
	 * @see com.google.gwt.user.client.ui.ValueBoxBase#setValue(java.lang.Object, boolean)
	 */
	public void setValue(String value, boolean fireEvents) {
		input.setValue(value, fireEvents);
		valueChanged();
	}

	/**
	 * @param key
	 * @see com.google.gwt.user.client.ui.FocusWidget#setAccessKey(char)
	 */
	public void setAccessKey(char key) {
		input.setAccessKey(key);
	}

	/**
	 * @param focused
	 * @see com.google.gwt.user.client.ui.FocusWidget#setFocus(boolean)
	 */
	public void setFocus(boolean focused) {
		input.setFocus(focused);
	}

	/**
	 * @param index
	 * @see com.google.gwt.user.client.ui.FocusWidget#setTabIndex(int)
	 */
	public void setTabIndex(int index) {
		input.setTabIndex(index);
	}

	/**
	 * @param styleSuffix
	 * @see com.google.gwt.user.client.ui.UIObject#addStyleDependentName(java.lang.String)
	 */
	public void addStyleDependentName(String styleSuffix) {
		input.addStyleDependentName(styleSuffix);
	}

	/**
	 * @param style
	 * @see com.google.gwt.user.client.ui.UIObject#addStyleName(java.lang.String)
	 */
	public void addStyleName(String style) {
		input.addStyleName(style);
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.UIObject#getTitle()
	 */
	public String getTitle() {
		return input.getTitle();
	}

	/**
	 * @param width
	 * @param height
	 * @see com.google.gwt.user.client.ui.UIObject#setPixelSize(int, int)
	 */
	public void setPixelSize(int width, int height) {
		input.setPixelSize(width, height);
	}

	/**
	 * @param width
	 * @param height
	 * @see com.google.gwt.user.client.ui.UIObject#setSize(java.lang.String, java.lang.String)
	 */
	public void setSize(String width, String height) {
		input.setSize(width, height);
	}

	/**
	 * @param title
	 * @see com.google.gwt.user.client.ui.UIObject#setTitle(java.lang.String)
	 */
	public void setTitle(String title) {
		input.setTitle(title);
	}

	public void setEnabled(boolean enabled) {
		input.setEnabled(enabled);
	}
}
