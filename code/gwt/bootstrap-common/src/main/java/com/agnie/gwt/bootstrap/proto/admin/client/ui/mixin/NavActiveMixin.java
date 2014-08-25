/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *  
 *  NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 *  any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 *  and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 *  law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 *  permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui.mixin;

import org.gwtbootstrap3.client.ui.base.HasActive;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;

import com.agnie.gwt.bootstrap.proto.admin.client.ui.ProtoConstants;
import com.google.gwt.user.client.ui.UIObject;

/**
 * @author Pandurang Patil 23-Aug-2014
 *
 */
public class NavActiveMixin<T extends UIObject & HasActive> implements HasActive {
	UIObject	uiObject;

	public NavActiveMixin(final T uiObject) {
		this.uiObject = uiObject;
	}

	@Override
	public void setActive(final boolean active) {
		if (active) {
			uiObject.addStyleName(ProtoConstants.NAV_ACTIVE);
		} else {
			uiObject.removeStyleName(ProtoConstants.NAV_ACTIVE);
		}
	}

	@Override
	public boolean isActive() {
		return StyleHelper.containsStyle(uiObject.getStyleName(), ProtoConstants.NAV_ACTIVE);
	}
}
