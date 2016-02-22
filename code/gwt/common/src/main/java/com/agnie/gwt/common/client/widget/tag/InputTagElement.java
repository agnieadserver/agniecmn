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
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.TextBox;

/**
 * @author Pandurang Patil 26-Nov-2014
 *
 */
public class InputTagElement extends LiHTMLPanel implements FocusElement {
	private static TagResources	resource	= TagResources.INSTANCE;
	TextBox						focusinput	= new TextBox();
	private SimpleEventBus		privateEventBus;
	private Editor				editor;

	public InputTagElement() {
		this("");
	}

	public InputTagElement(String html) {
		super(html);
		add(focusinput);
		addStyleName(resource.css().tageditListelement());
		focusinput.setStyleName(resource.css().tageditListPlaceholder());
		focusinput.addKeyPressHandler(Editor.restrictKeyPressHandler);
		focusinput.addFocusHandler(new FocusHandler() {

			@Override
			public void onFocus(FocusEvent event) {
				editor.blurLastElement();
				afterFocusAction();
			}
		});
	}

	public void setEventBus(SimpleEventBus eventBus) {
		this.privateEventBus = eventBus;
	}

	void setEditor(Editor editor) {
		this.editor = editor;
		focusinput.addKeyDownHandler(editor.keyDownHandler);
	}

	public void setWidth(String width) {
		focusinput.setWidth(width);
	}

	private void afterFocusAction() {
		if (InputTagElement.this.editor != null) {
			InputTagElement.this.editor.setFocusElement(InputTagElement.this);
		}
		addStyleName(resource.css().active());
		privateEventBus.fireEvent(new SelectionEvent(this));
	}

	public void setFocus() {
		GWT.log("Inside InputTagElement focus()");
		editor.blurLastElement();
		focusinput.setFocus(true);
		afterFocusAction();
	}

	@Override
	public void setBlur() {
		GWT.log("Inside InputTagElement blurr()");
		focusinput.setFocus(false);
		removeStyleName(resource.css().active());
	}
}
