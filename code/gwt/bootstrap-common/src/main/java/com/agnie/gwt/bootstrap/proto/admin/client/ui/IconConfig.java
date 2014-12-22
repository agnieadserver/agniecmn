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
import org.gwtbootstrap3.client.ui.constants.Styles;

/**
 * @author Pandurang Patil 28-Nov-2014
 *
 */
public class IconConfig {

	private IconType	type;
	private IconSize	size;
	private IconFlip	flip;
	private IconRotate	rotate;
	private boolean		spin	= false;
	private String		color;

	/**
	 * @return the type
	 */
	public IconType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(IconType type) {
		this.type = type;
	}

	/**
	 * @return the size
	 */
	public IconSize getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(IconSize size) {
		this.size = size;
	}

	/**
	 * @return the flip
	 */
	public IconFlip getFlip() {
		return flip;
	}

	/**
	 * @param flip
	 *            the flip to set
	 */
	public void setFlip(IconFlip flip) {
		this.flip = flip;
	}

	/**
	 * @return the rotate
	 */
	public IconRotate getRotate() {
		return rotate;
	}

	/**
	 * @param rotate
	 *            the rotate to set
	 */
	public void setRotate(IconRotate rotate) {
		this.rotate = rotate;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the spin
	 */
	public boolean isSpin() {
		return spin;
	}

	public String spinClass() {
		return (spin ? Styles.ICON_SPIN : "");
	}

	/**
	 * @param spin
	 *            the spin to set
	 */
	public void setSpin(boolean spin) {
		this.spin = spin;
	}

}
