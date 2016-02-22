/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.common.client.widget.tag;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * @author Pandurang Patil 25-Nov-2014
 *
 */
public class UlHTMLPanel extends HTMLPanel {

	public UlHTMLPanel() {
		super("ul", "");
	}

	/**
	 * @param safeHtml
	 */
	public UlHTMLPanel(SafeHtml safeHtml) {
		this(safeHtml.asString());
	}

	/**
	 * @param html
	 */
	public UlHTMLPanel(String html) {
		super("ul", html);
	}
}
