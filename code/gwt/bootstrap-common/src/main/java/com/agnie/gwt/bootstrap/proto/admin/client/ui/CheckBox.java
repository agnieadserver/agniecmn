/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.safehtml.shared.SafeHtml;

/**
 * @author Pandurang Patil 27-Nov-2014
 *
 */
public class CheckBox extends com.google.gwt.user.client.ui.CheckBox {
	private CustomCheckBoxType	lastType;

	/**
	 * Creates a check box with no label.
	 */
	public CheckBox() {
		super();
		setStyleName("checkbox-custom");
	}

	/**
	 * Creates a check box with the specified text label.
	 * 
	 * @param label
	 *            the check box's label
	 */
	public CheckBox(SafeHtml label) {
		this(label.asString(), true);
	}

	/**
	 * Creates a check box with the specified text label.
	 * 
	 * @param label
	 *            the check box's label
	 * @param dir
	 *            the text's direction. Note that {@code DEFAULT} means direction should be inherited from the widget's
	 *            parent element.
	 */
	public CheckBox(SafeHtml label, Direction dir) {
		this();
		setHTML(label, dir);
	}

	/**
	 * Creates a check box with the specified text label.
	 * 
	 * @param label
	 *            the check box's label
	 * @param directionEstimator
	 *            A DirectionEstimator object used for automatic direction adjustment. For convenience,
	 *            {@link #DEFAULT_DIRECTION_ESTIMATOR} can be used.
	 */
	public CheckBox(SafeHtml label, DirectionEstimator directionEstimator) {
		this();
		setDirectionEstimator(directionEstimator);
		setHTML(label.asString());
	}

	/**
	 * Creates a check box with the specified text label.
	 * 
	 * @param label
	 *            the check box's label
	 */
	public CheckBox(String label) {
		this();
		setText(label);
	}

	/**
	 * Creates a check box with the specified text label.
	 * 
	 * @param label
	 *            the check box's label
	 * @param dir
	 *            the text's direction. Note that {@code DEFAULT} means direction should be inherited from the widget's
	 *            parent element.
	 */
	public CheckBox(String label, Direction dir) {
		this();
		setText(label, dir);
	}

	/**
	 * Creates a label with the specified text and a default direction estimator.
	 * 
	 * @param label
	 *            the check box's label
	 * @param directionEstimator
	 *            A DirectionEstimator object used for automatic direction adjustment. For convenience,
	 *            {@link #DEFAULT_DIRECTION_ESTIMATOR} can be used.
	 */
	public CheckBox(String label, DirectionEstimator directionEstimator) {
		this();
		setDirectionEstimator(directionEstimator);
		setText(label);
	}

	/**
	 * Creates a check box with the specified text label.
	 * 
	 * @param label
	 *            the check box's label
	 * @param asHTML
	 *            <code>true</code> to treat the specified label as html
	 */
	public CheckBox(String label, boolean asHTML) {
		this();
		if (asHTML) {
			setHTML(label);
		} else {
			setText(label);
		}
	}

	public void setType(final CustomCheckBoxType type) {
		if (lastType != null) {
			removeStyleName(lastType.getType());
		}
		addStyleName(type.getType());
		lastType = type;
	}
}
