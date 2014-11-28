/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import org.gwtbootstrap3.client.ui.constants.IconFlip;
import org.gwtbootstrap3.client.ui.constants.IconRotate;
import org.gwtbootstrap3.client.ui.constants.IconSize;
import org.gwtbootstrap3.client.ui.constants.IconType;

/**
 * @author Pandurang Patil 28-Nov-2014
 *
 */
public class IconConfig {

	private IconType	type;
	private IconSize	size;
	private IconFlip	flip;
	private IconRotate	rotate;

	public IconConfig(IconType type, IconSize size, IconFlip flip, IconRotate rotate) {
		this.type = type;
		this.size = size;
		this.flip = flip;
		this.rotate = rotate;
	}

	/**
	 * @return the type
	 */
	public IconType getType() {
		return type;
	}

	/**
	 * @return the size
	 */
	public IconSize getSize() {
		return size;
	}

	/**
	 * @return the flip
	 */
	public IconFlip getFlip() {
		return flip;
	}

	/**
	 * @return the rotate
	 */
	public IconRotate getRotate() {
		return rotate;
	}

}
