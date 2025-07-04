/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.common.client.timezone;

import com.google.gwt.event.shared.GwtEvent;

/**
 * 
 * @author Pandurang Patil 14-Jan-2015
 *
 */
public class SecondsChangeEvent extends GwtEvent<SecondsChangeHandler> {

	public static Type<SecondsChangeHandler>	TYPE	= new Type<SecondsChangeHandler>();

	AgnieDate										date;

	/**
	 * @param element
	 */
	public SecondsChangeEvent(AgnieDate date) {
		this.date = date;
	}

	/**
	 * @return the date
	 */
	public AgnieDate getDate() {
		return date;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<SecondsChangeHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SecondsChangeHandler handler) {
		handler.onChange(this);
	}

}
