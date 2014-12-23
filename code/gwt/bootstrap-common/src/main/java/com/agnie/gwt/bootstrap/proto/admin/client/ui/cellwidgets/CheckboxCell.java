/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui.cellwidgets;

import com.agnie.gwt.bootstrap.proto.admin.client.ui.CheckBoxType;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

/**
 * @author Pandurang Patil 28-Nov-2014
 *
 */
public class CheckboxCell extends com.google.gwt.cell.client.CheckboxCell {

	CheckBoxType				type;
	/**
	 * An html string representation of a checked input box.
	 */
	protected final SafeHtml	INPUT_CHECKED;

	/**
	 * An html string representation of an unchecked input box.
	 */
	protected final SafeHtml	INPUT_UNCHECKED;

	/**
	 * 
	 */
	public CheckboxCell() {
		this(false, false);
	}

	public CheckboxCell(CheckBoxType type) {
		this(false, false, type);
	}

	public CheckboxCell(boolean dependsOnSelection, boolean handlesSelection) {
		this(dependsOnSelection, handlesSelection, CheckBoxType.DEFAULT);
	}

	/**
	 * @param dependsOnSelection
	 * @param handlesSelection
	 */
	public CheckboxCell(boolean dependsOnSelection, boolean handlesSelection, CheckBoxType type) {
		super(dependsOnSelection, handlesSelection);
		this.type = type;
		INPUT_CHECKED = SafeHtmlUtils.fromSafeConstant("<span class=\"checkbox-custom " + type.getType() + "\"><input type=\"checkbox\" tabindex=\"-1\" checked/><label></label></span>");
		INPUT_UNCHECKED = SafeHtmlUtils.fromSafeConstant("<span class=\"checkbox-custom " + type.getType() + "\"><input type=\"checkbox\" tabindex=\"-1\"/><label></label></span>");
	}

	@Override
	public void onBrowserEvent(Context context, Element parent, Boolean value, NativeEvent event, ValueUpdater<Boolean> valueUpdater) {
		String type = event.getType();

		boolean enterPressed = BrowserEvents.KEYDOWN.equals(type) && event.getKeyCode() == KeyCodes.KEY_ENTER;
		if (BrowserEvents.CHANGE.equals(type) || enterPressed) {
			InputElement input = parent.getFirstChild().getFirstChild().cast();
			Boolean isChecked = input.isChecked();

			/*
			 * Toggle the value if the enter key was pressed and the cell handles selection or doesn't depend on
			 * selection. If the cell depends on selection but doesn't handle selection, then ignore the enter key and
			 * let the SelectionEventManager determine which keys will trigger a change.
			 */
			if (enterPressed && (handlesSelection() || !dependsOnSelection())) {
				isChecked = !isChecked;
				input.setChecked(isChecked);
			}

			/*
			 * Save the new value. However, if the cell depends on the selection, then do not save the value because we
			 * can get into an inconsistent state.
			 */
			if (value != isChecked && !dependsOnSelection()) {
				setViewData(context.getKey(), isChecked);
			} else {
				clearViewData(context.getKey());
			}

			if (valueUpdater != null) {
				valueUpdater.update(isChecked);
			}
		}
	}

	@Override
	public void render(Context context, Boolean value, SafeHtmlBuilder sb) {
		// Get the view data.
		Object key = context.getKey();
		Boolean viewData = getViewData(key);
		if (viewData != null && viewData.equals(value)) {
			clearViewData(key);
			viewData = null;
		}

		if (value != null && ((viewData != null) ? viewData : value)) {
			sb.append(INPUT_CHECKED);
		} else {
			sb.append(INPUT_UNCHECKED);
		}
	}
}
