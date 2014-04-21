/*******************************************************************************
 * ? 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.gwt.common.client.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;

/**
 * @author Pandurang Patil 11-Mar-2014
 * 
 */
public class NewTabAnchor extends Anchor {
	String	href;

	public NewTabAnchor() {
		// As we are going to keep href internally and open it new window on click.
		super(true);
		getElement().setAttribute("style", "cursor: pointer;");
		addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Window.open(href, "_blank", "");
			}
		});
	}

	@Override
	public void setHref(String href) {
		this.href = href;
	}

}
