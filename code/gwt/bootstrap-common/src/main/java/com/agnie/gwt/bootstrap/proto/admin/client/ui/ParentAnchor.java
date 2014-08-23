/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *  
 *  NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 *  any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 *  and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 *  law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 *  permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import org.gwtbootstrap3.client.ui.Anchor;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.SpanElement;

/**
 * @author Pandurang Patil 23-Aug-2014
 *
 */
public class ParentAnchor extends Anchor {

	public ParentAnchor() {
		super();
	}

	/**
	 * Creates an achor widget with the desired HREF and text
	 *
	 * @param text
	 *            text for the anchor
	 * @param href
	 *            href for the anchor
	 */
	public ParentAnchor(String text, String href) {
		super(href);
		setText(text);
	}

	/**
	 * Creates an anchor widget with the desired HREF
	 *
	 * @param href
	 *            href for the anchor
	 */
	public ParentAnchor(String href) {
		super(href);
	}

	@Override
	public void setText(final String text) {
		SpanElement ele = Document.get().createSpanElement();
		ele.setInnerText(text);
		this.getElement().appendChild(ele);
	}

}
