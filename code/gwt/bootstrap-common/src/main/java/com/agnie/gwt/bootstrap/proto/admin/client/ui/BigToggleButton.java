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

import org.gwtbootstrap3.client.ui.base.HasDataToggle;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.base.mixin.DataToggleMixin;
import org.gwtbootstrap3.client.ui.constants.Styles;
import org.gwtbootstrap3.client.ui.constants.Toggle;
import org.gwtbootstrap3.client.ui.gwt.HTMLPanel;

import com.google.gwt.safehtml.shared.SafeHtml;

/**
 * @author Pandurang Patil 27-Aug-2014
 *
 */
public class BigToggleButton extends HTMLPanel implements HasDataToggle {
	protected DataToggleMixin<BigToggleButton>	dataToggle	= new DataToggleMixin<BigToggleButton>(this);

	/**
	 * @param safeHtml
	 */
	public BigToggleButton(SafeHtml safeHtml) {
		super(safeHtml);
		init();
	}

	/**
	 * @param tag
	 * @param html
	 */
	public BigToggleButton(String tag, String html) {
		super(tag, html);
		init();
	}

	/**
	 * @param html
	 */
	public BigToggleButton(String html) {
		super(html);
		init();
	}

	private void init() {
		setStyleName(ProtoConstants.AG_BIG_BUTTON);
		addStyleName(ProtoConstants.BLUE);
		addStyleName(Styles.BTN);
		addStyleName(Styles.BTN_BLOCK);
	}

	public void setActive(boolean flag) {
		StyleHelper.toggleStyleName(this, flag, Styles.ACTIVE);
	}

	public boolean isActive() {
		return StyleHelper.containsStyle(Styles.ACTIVE, getStyleName());
	}

	@Override
	public void setDataToggle(Toggle toggle) {
		dataToggle.setDataToggle(toggle);
	}

	@Override
	public Toggle getDataToggle() {
		return dataToggle.getDataToggle();
	}

}
