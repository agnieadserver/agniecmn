/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.common.client.widget.select;

import com.google.gwt.event.shared.GwtEvent;

/**
 * 
 * @author Pandurang Patil 26-Nov-2014
 *
 */
public class RecordSelectEvent<ENTITY extends SelectEntity> extends GwtEvent<RecordSelectEventHandler> {

	public static Type<RecordSelectEventHandler>	TYPE	= new Type<RecordSelectEventHandler>();

	ENTITY											entity;
	Boolean											userAction;

	/**
	 * @param entity
	 * @param userAction
	 */
	public RecordSelectEvent(ENTITY entity, Boolean userAction) {
		super();
		this.entity = entity;
		this.userAction = userAction;
	}

	/**
	 * @return the entity
	 */
	public ENTITY getEntity() {
		return entity;
	}

	/**
	 * @return the userAction
	 */
	public Boolean getUserAction() {
		return userAction;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<RecordSelectEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(RecordSelectEventHandler handler) {
		handler.onSelection(this);
	}

}
