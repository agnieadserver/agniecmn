/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.common.client.widget.tag;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author Pandurang Patil 25-Nov-2014
 *
 */
public class TagElement extends Composite implements FocusElement {
	private static TagResources			resource	= TagResources.INSTANCE;
	private static TagElementUiBinder	uiBinder	= GWT.create(TagElementUiBinder.class);

	interface TagElementUiBinder extends UiBinder<Widget, TagElement> {
	}

	@UiField
	Anchor					label;
	@UiField
	TextBox					focusinput;
	@UiField
	Anchor					remove;
	@UiField
	SpanElement				display;
	LiHTMLPanel				root;
	private SimpleEventBus	privateEventBus;
	private Editor			editor;

	public TagElement() {
		this("");
	}

	@UiConstructor
	public TagElement(String html) {
		root = (LiHTMLPanel) uiBinder.createAndBindUi(this);
		initWidget(root);
		this.label.setText(html);
	}

	/**
	 * Set event bus to propagate events.
	 * 
	 * @param eventBus
	 */
	void setEventBus(SimpleEventBus eventBus) {
		this.privateEventBus = eventBus;
	}

	/**
	 * Set Tag Editor so that it can callback editor
	 * 
	 * @param editor
	 */
	void setEditor(Editor editor) {
		this.editor = editor;
		focusinput.addKeyDownHandler(editor.keyDownHandler);
	}

	/**
	 * Get label value
	 * 
	 * @return
	 */
	public String getLabel() {
		return label.getText();
	}

	public void setLabel(String label) {
		this.label.setText(label);
	}

	/**
	 * Label selection click handler.
	 * 
	 * @param event
	 */
	@UiHandler("label")
	public void clickHandler(ClickEvent event) {
		setFocus();
	}

	/**
	 * Remove link click handler
	 * 
	 * @param event
	 */
	@UiHandler("remove")
	public void removeClickHandler(ClickEvent event) {
		GWT.log("Remove is getting called.");
		editor.removeElement(this);
	}

	/**
	 * Set focus to this element.
	 */
	public void setFocus() {
		GWT.log("Inside TagElement focus()");
		editor.blurLastElement();
		editor.setFocusElement(this);
		focusinput.setFocus(true);
		setActive(true);
	}

	/**
	 * Remove focus form this element.
	 */
	public void setBlur() {
		GWT.log("Inside TagElement Blurr()");
		focusinput.setFocus(false);
		setActive(false);
	}

	/**
	 * if flag is true the set given element as active element. Or if flag is false set given element as inactive
	 * element.
	 * 
	 * @param flag
	 *            flag which indicates whether to set given element active or set it as inactive element.
	 */
	public void setActive(boolean flag) {
		GWT.log("Inside TagElement setActive flag ->" + flag);
		if (flag) {
			root.addStyleName(resource.css().active());
			privateEventBus.fireEvent(new SelectionEvent(this));
		} else {
			root.removeStyleName(resource.css().active());
		}
	}

	/**
	 * When it is identified there is error around this tag, this tag will be highlighted with error display along with
	 * message.
	 * 
	 * @param html
	 *            Error contents it can have some html.
	 */
	public void setError() {
		setFocus();
		display.addClassName(resource.css().error());
		privateEventBus.fireEvent(new SelectionEvent(this));
	}

	/**
	 * Fix the error case.
	 * 
	 * @param actual
	 */
	public void fixError() {
		display.removeClassName(resource.css().error());
		setBlur();
	}

	/**
	 * Register handler to receive remove click event.
	 * 
	 * @param handler
	 *            handler to register for remove events.
	 * @return handler using which handler registered can unregister its handler registration.
	 */
	public HandlerRegistration addRemoveHandler(RemoveEventHandler handler) {
		return privateEventBus.addHandler(RemoveEvent.TYPE, handler);
	}

}
