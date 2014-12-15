/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui.cellwidgets;

import org.gwtbootstrap3.client.ui.constants.ButtonType;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * A {@link Cell} used to render a button.
 * 
 * @author Pandurang Patil 15-Dec-2014
 * 
 */
public class ButtonCell extends com.google.gwt.cell.client.ButtonCell {
	ButtonType		type;

	final String	buttonElement;

	/**
	 * @param type
	 */
	public ButtonCell(ButtonType type) {
		this.type = type;
		buttonElement = "<button type=\"button\" tabindex=\"-1\" class=\"mb-xs mt-xs mr-xs btn " + type.getCssName() + "\">";
	}

	@Override
	public void render(Context context, SafeHtml data, SafeHtmlBuilder sb) {
		sb.appendHtmlConstant(buttonElement);
		if (data != null) {
			sb.append(data);
		}
		sb.appendHtmlConstant("</button>");
	}

}
