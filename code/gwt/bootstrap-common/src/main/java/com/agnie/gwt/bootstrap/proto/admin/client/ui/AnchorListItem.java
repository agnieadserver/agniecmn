/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import org.gwtbootstrap3.client.ui.base.AbstractAnchorListItem;
import org.gwtbootstrap3.client.ui.base.AbstractListItem;

import com.agnie.gwt.bootstrap.proto.admin.client.ui.mixin.NavActiveMixin;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.SpanElement;

/**
 * @author Pandurang Patil 23-Aug-2014
 *
 */
public class AnchorListItem extends AbstractAnchorListItem implements com.google.gwt.user.client.ui.HasText {
	private final NavActiveMixin<AbstractListItem>	navActiveMixin	= new NavActiveMixin<AbstractListItem>(this);

	public AnchorListItem() {
	}

	public AnchorListItem(final String text) {
		setText(text);
	}

	@Override
	public void setText(final String text) {
		SpanElement ele = Document.get().createSpanElement();
		ele.setInnerText(text);
		anchor.getElement().appendChild(ele);
	}

	@Override
	public String getText() {
		return anchor.getText();
	}

	@Override
	public void setActive(final boolean active) {
		navActiveMixin.setActive(active);
	}

	@Override
	public boolean isActive() {
		return navActiveMixin.isActive();
	}
}
