/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.common.client.widget.tag;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author Pandurang Patil 21-Dec-2014
 *
 */
public class TagEditor extends Composite {
	private static TagResources			resource	= TagResources.INSTANCE;
	private static TagEditorUiBinder	uiBinder	= GWT.create(TagEditorUiBinder.class);
	static {
		resource.css().ensureInjected();
	}

	interface TagEditorUiBinder extends UiBinder<Widget, TagEditor> {
	}

	@UiField
	Editor		editor;
	@UiField
	DivElement	displayreadonly;

	boolean		readOnlyMode	= false;

	public TagEditor() {
		initWidget(uiBinder.createAndBindUi(this));
		setReadOnlyVisible(readOnlyMode);
	}

	public void setDisable(boolean disable) {
		readOnlyMode = disable;
		if (disable) {
			editor.setVisible(false);
		} else {
			editor.setVisible(true);
		}
		setReadOnlyVisible(readOnlyMode);
	}

	private void setReadOnlyVisible(boolean flag) {
		if (flag) {
			displayreadonly.removeClassName(resource.css().hide());
		} else {
			displayreadonly.addClassName(resource.css().hide());
		}
	}

	public void addTagElement(TagElement element, boolean setfocus) {
		editor.addTagElement(element, setfocus);
		displayreadonly.setInnerText(displayreadonly.getInnerText() + " " + element.getLabel());
	}

	public List<TagElement> getTagElementList() {
		return editor.getTagElementList();
	}

	public TagElement getSelectedTagElement() {
		return editor.getSelectedTagElement();
	}

	public void removeElement(TagElement element) {
		editor.removeElement(element);
	}

	public HandlerRegistration addSelectionHandler(SelectionEventHandler handler) {
		return editor.addSelectionHandler(handler);
	}

	public void clear() {
		editor.clear();
		displayreadonly.setInnerHTML("");
	}
}
