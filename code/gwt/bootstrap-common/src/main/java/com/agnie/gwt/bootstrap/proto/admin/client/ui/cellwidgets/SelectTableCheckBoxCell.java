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
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

/**
 * @author Pandurang Patil 23-Dec-2014
 *
 */
public class SelectTableCheckBoxCell extends CheckboxCell {

	/**
	 * An html string representation of a checked input box.
	 */
	protected final SafeHtml						INPUT_CHECKED_DISABLED;

	/**
	 * An html string representation of an unchecked input box.
	 */
	protected final SafeHtml						INPUT_UNCHECKED_DISABLED;
	protected SelectTable<? extends SelectEntity>	stable;

	/**
	 * 
	 */
	public SelectTableCheckBoxCell(SelectTable<? extends SelectEntity> stable) {
		this(false, false, stable);
	}

	public SelectTableCheckBoxCell(CheckBoxType type, SelectTable<? extends SelectEntity> stable) {
		this(false, false, type, stable);
	}

	public SelectTableCheckBoxCell(boolean dependsOnSelection, boolean handlesSelection, SelectTable<? extends SelectEntity> stable) {
		this(dependsOnSelection, handlesSelection, CheckBoxType.DEFAULT, stable);
	}

	/**
	 * @param dependsOnSelection
	 * @param handlesSelection
	 */
	public SelectTableCheckBoxCell(boolean dependsOnSelection, boolean handlesSelection, CheckBoxType type, SelectTable<? extends SelectEntity> stable) {
		super(dependsOnSelection, handlesSelection, type);
		this.type = type;
		this.stable = stable;
		INPUT_CHECKED_DISABLED = SafeHtmlUtils.fromSafeConstant("<span class=\"checkbox-custom " + type.getType()
				+ "\"><input type=\"checkbox\" tabindex=\"-1\" checked disabled/><label></label></span>");
		INPUT_UNCHECKED_DISABLED = SafeHtmlUtils.fromSafeConstant("<span class=\"checkbox-custom " + type.getType() + "\"><input type=\"checkbox\" tabindex=\"-1\" disabled/><label></label></span>");
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
			if (stable.isDisabled())
				sb.append(INPUT_CHECKED_DISABLED);
			else
				sb.append(INPUT_CHECKED);
		} else {
			if (stable.isDisabled())
				sb.append(INPUT_UNCHECKED_DISABLED);
			else
				sb.append(INPUT_UNCHECKED);
		}
	}
}
