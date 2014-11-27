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
import com.google.gwt.uibinder.client.UiConstructor;

/**
 * @author Pandurang Patil 28-Nov-2014
 *
 */
public class RadioButton extends com.google.gwt.user.client.ui.RadioButton {
	CustomRadioType	lastType;

	/**
	 * Creates a new radio associated with a particular group name. All radio buttons associated with the same group
	 * name belong to a mutually-exclusive set.
	 * 
	 * Radio buttons are grouped by their name attribute, so changing their name using the setName() method will also
	 * change their associated group.
	 * 
	 * @param name
	 *            the group name with which to associate the radio button
	 */
	@UiConstructor
	public RadioButton(String name) {
		super(name);
		setStyleName("radio-custom");
	}

	/**
	 * Creates a new radio associated with a particular group, and initialized with the given HTML label. All radio
	 * buttons associated with the same group name belong to a mutually-exclusive set.
	 * 
	 * Radio buttons are grouped by their name attribute, so changing their name using the setName() method will also
	 * change their associated group.
	 * 
	 * @param name
	 *            the group name with which to associate the radio button
	 * @param label
	 *            this radio button's html label
	 */
	public RadioButton(String name, SafeHtml label) {
		this(name, label.asString(), true);
	}

	/**
	 * @see #RadioButton(String, SafeHtml)
	 * 
	 * @param name
	 *            the group name with which to associate the radio button
	 * @param label
	 *            this radio button's html label
	 * @param dir
	 *            the text's direction. Note that {@code DEFAULT} means direction should be inherited from the widget's
	 *            parent element.
	 */
	public RadioButton(String name, SafeHtml label, Direction dir) {
		this(name);
		setHTML(label, dir);
	}

	/**
	 * @see #RadioButton(String, SafeHtml)
	 * 
	 * @param name
	 *            the group name with which to associate the radio button
	 * @param label
	 *            this radio button's html label
	 * @param directionEstimator
	 *            A DirectionEstimator object used for automatic direction adjustment. For convenience,
	 *            {@link #DEFAULT_DIRECTION_ESTIMATOR} can be used.
	 */
	public RadioButton(String name, SafeHtml label, DirectionEstimator directionEstimator) {
		this(name);
		setDirectionEstimator(directionEstimator);
		setHTML(label.asString());
	}

	/**
	 * Creates a new radio associated with a particular group, and initialized with the given HTML label. All radio
	 * buttons associated with the same group name belong to a mutually-exclusive set.
	 * 
	 * Radio buttons are grouped by their name attribute, so changing their name using the setName() method will also
	 * change their associated group.
	 * 
	 * @param name
	 *            the group name with which to associate the radio button
	 * @param label
	 *            this radio button's label
	 */
	public RadioButton(String name, String label) {
		this(name);
		setText(label);
	}

	/**
	 * @see #RadioButton(String, SafeHtml)
	 * 
	 * @param name
	 *            the group name with which to associate the radio button
	 * @param label
	 *            this radio button's label
	 * @param dir
	 *            the text's direction. Note that {@code DEFAULT} means direction should be inherited from the widget's
	 *            parent element.
	 */
	public RadioButton(String name, String label, Direction dir) {
		this(name);
		setText(label, dir);
	}

	/**
	 * @see #RadioButton(String, SafeHtml)
	 * 
	 * @param name
	 *            the group name with which to associate the radio button
	 * @param label
	 *            this radio button's label
	 * @param directionEstimator
	 *            A DirectionEstimator object used for automatic direction adjustment. For convenience,
	 *            {@link #DEFAULT_DIRECTION_ESTIMATOR} can be used.
	 */
	public RadioButton(String name, String label, DirectionEstimator directionEstimator) {
		this(name);
		setDirectionEstimator(directionEstimator);
		setText(label);
	}

	/**
	 * Creates a new radio button associated with a particular group, and initialized with the given label (optionally
	 * treated as HTML). All radio buttons associated with the same group name belong to a mutually-exclusive set.
	 * 
	 * Radio buttons are grouped by their name attribute, so changing their name using the setName() method will also
	 * change their associated group.
	 * 
	 * @param name
	 *            name the group with which to associate the radio button
	 * @param label
	 *            this radio button's label
	 * @param asHTML
	 *            <code>true</code> to treat the specified label as HTML
	 */
	public RadioButton(String name, String label, boolean asHTML) {
		this(name);
		if (asHTML) {
			setHTML(label);
		} else {
			setText(label);
		}
	}

	public void setType(final CustomRadioType type) {
		if (lastType != null) {
			removeStyleName(lastType.getType());
		}
		addStyleName(type.getType());
		lastType = type;
	}
}
