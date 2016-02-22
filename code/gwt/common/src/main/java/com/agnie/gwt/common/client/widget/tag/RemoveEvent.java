/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.common.client.widget.tag;

import com.google.gwt.event.shared.GwtEvent;

/**
 * 
 * @author Pandurang Patil 26-Nov-2014
 *
 */
public class RemoveEvent extends GwtEvent<RemoveEventHandler> {

	public static Type<RemoveEventHandler>	TYPE	= new Type<RemoveEventHandler>();

	TagElement								element;

	/**
	 * @param element
	 */
	public RemoveEvent(TagElement element) {
		super();
		this.element = element;
	}

	/**
	 * @return the element
	 */
	public TagElement getElement() {
		return element;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<RemoveEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(RemoveEventHandler handler) {
		handler.onRemove(this);
	}

}
