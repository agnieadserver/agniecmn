/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import org.gwtbootstrap3.client.ui.base.AbstractListItem;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Pandurang Patil 23-Aug-2014
 *
 */
public class NavListDropDown extends AbstractListItem {

    boolean state = false;

    public NavListDropDown() {
        setStyleName(ProtoStyles.NAV_PARENT);
    }

    @Override
    public void add(final Widget child) {

        add(child, (Element) getElement());
    }

    public void onClickHandleEvent() {
        state = !state;
        if (state) {
            getElement().addClassName("nav-parent nav-expanded");

        } else {
            getElement().removeClassName("nav-parent nav-expanded");

        }
    }
}
