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

import java.util.List;

import org.gwtbootstrap3.client.ui.Alert;
import org.gwtbootstrap3.client.ui.base.ComplexWidget;
import org.gwtbootstrap3.client.ui.base.HasDataParent;
import org.gwtbootstrap3.client.ui.base.HasDataTarget;
import org.gwtbootstrap3.client.ui.base.HasDataToggle;
import org.gwtbootstrap3.client.ui.base.HasHref;
import org.gwtbootstrap3.client.ui.base.HasPull;
import org.gwtbootstrap3.client.ui.base.HasTarget;
import org.gwtbootstrap3.client.ui.base.HasTargetHistoryToken;
import org.gwtbootstrap3.client.ui.base.mixin.AttributeMixin;
import org.gwtbootstrap3.client.ui.base.mixin.DataParentMixin;
import org.gwtbootstrap3.client.ui.base.mixin.DataTargetMixin;
import org.gwtbootstrap3.client.ui.base.mixin.DataToggleMixin;
import org.gwtbootstrap3.client.ui.base.mixin.FocusableMixin;
import org.gwtbootstrap3.client.ui.base.mixin.PullMixin;
import org.gwtbootstrap3.client.ui.constants.Attributes;
import org.gwtbootstrap3.client.ui.constants.Pull;
import org.gwtbootstrap3.client.ui.constants.Styles;
import org.gwtbootstrap3.client.ui.constants.Toggle;

import com.agnie.gwt.bootstrap.proto.admin.client.ui.mixin.NumberTextMixin;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasDoubleClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Pandurang Patil 25-Aug-2014
 *
 */
public class WizardAnchor extends ComplexWidget implements HasClickHandlers, HasDoubleClickHandlers, HasHref, HasDataToggle, HasDataParent, HasTargetHistoryToken, HasHTML, Focusable, HasDataTarget,
		HasTarget, HasPull {

	private final PullMixin<WizardAnchor>		pullMixin		= new PullMixin<WizardAnchor>(this);
	private final DataToggleMixin<WizardAnchor>	toggleMixin		= new DataToggleMixin<WizardAnchor>(this);
	private final DataParentMixin<WizardAnchor>	parentMixin		= new DataParentMixin<WizardAnchor>(this);
	private final NumberTextMixin<WizardAnchor>	numberTextMixin	= new NumberTextMixin<WizardAnchor>(this);
	private final DataTargetMixin<WizardAnchor>	targetMixin		= new DataTargetMixin<WizardAnchor>(this);
	private final AttributeMixin<WizardAnchor>	attributeMixin	= new AttributeMixin<WizardAnchor>(this);
	private final FocusableMixin<WizardAnchor>	focusableMixin;
	private String								targetHistoryToken;

	/**
	 * Creates an anchor widget with the desired HREF
	 *
	 * @param href
	 *            href for the anchor
	 */
	public WizardAnchor(final String href) {
		setElement(Document.get().createAnchorElement());
		setHref(href);
		focusableMixin = new FocusableMixin<WizardAnchor>(this);
		numberTextMixin.addTextWidgetToParent();
	}

	/**
	 * Creates an achor widget with the desired HREF and text
	 *
	 * @param text
	 *            text for the anchor
	 * @param href
	 *            href for the anchor
	 */
	public WizardAnchor(final String text, final String href) {
		this(href);
		setText(text);
	}

	/**
	 * Creates a default anchor with a default href
	 */
	public WizardAnchor() {
		this(EMPTY_HREF);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HandlerRegistration addClickHandler(final ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HandlerRegistration addDoubleClickHandler(final DoubleClickHandler handler) {
		return addDomHandler(handler, DoubleClickEvent.getType());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setText(final String text) {
		numberTextMixin.setText(text);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getText() {
		return numberTextMixin.getText();
	}

	public void setNumber(final Integer number) {
		numberTextMixin.setNumber(number);
	}

	public Integer getNumber() {
		return numberTextMixin.getNumber();
	}

	public void setProgress(boolean flag) {
		numberTextMixin.setProgress(flag);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setHref(final String href) {
		AnchorElement.as(getElement()).setHref(href);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getHref() {
		return AnchorElement.as(getElement()).getHref();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTargetHistoryToken(final String targetHistoryToken) {
		this.targetHistoryToken = targetHistoryToken;
		final String hash = History.encodeHistoryToken(targetHistoryToken);
		setHref("#" + hash);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTargetHistoryToken() {
		return targetHistoryToken;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDataParent(final String dataParent) {
		parentMixin.setDataParent(dataParent);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDataParent() {
		return parentMixin.getDataParent();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDataToggle(final Toggle toggle) {
		toggleMixin.setDataToggle(toggle);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Toggle getDataToggle() {
		return toggleMixin.getDataToggle();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getTabIndex() {
		return focusableMixin.getTabIndex();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTabIndex(final int index) {
		focusableMixin.setTabIndex(index);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAccessKey(final char key) {
		focusableMixin.setAccessKey(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFocus(final boolean focused) {
		focusableMixin.setFocus(focused);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getHTML() {
		return getElement().getInnerHTML();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setHTML(final String html) {
		getElement().setInnerHTML(html);
	}

	@Override
	public void setDataTargetWidgets(final List<Widget> widgets) {
		targetMixin.setDataTargetWidgets(widgets);
	}

	@Override
	public void setDataTargetWidget(final Widget widget) {
		targetMixin.setDataTargetWidget(widget);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDataTarget(final String dataTarget) {
		targetMixin.setDataTarget(dataTarget);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDataTarget() {
		return targetMixin.getDataTarget();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTarget(final String target) {
		attributeMixin.setAttribute(Attributes.TARGET, target);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTarget() {
		return attributeMixin.getAttribute(Attributes.TARGET);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPull(final Pull pull) {
		pullMixin.setPull(pull);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Pull getPull() {
		return pullMixin.getPull();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onAttach() {
		super.onAttach();

		// Adding styles to the heading depending on the parent
		if (getParent() != null) {
			if (getParent() instanceof Alert) {
				addStyleName(Styles.ALERT_LINK);
			}
		}
	}
}
