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

/**
 * @author Pandurang Patil 28-Aug-2014
 *
 */
public enum ToggleSwitchSize {

	SMALL("switch-sm"), DEFAULT("switch-df"), LARGE("switch-lg");

	private final String	size;

	private ToggleSwitchSize(final String size) {
		this.size = size;
	}

	public String getSize() {
		return size;
	}
}
