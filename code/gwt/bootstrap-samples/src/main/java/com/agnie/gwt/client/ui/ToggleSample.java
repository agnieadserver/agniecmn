/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.client.ui;

import com.agnie.gwt.bootstrap.proto.admin.client.ui.AnchorListItem;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.NavListDropDown;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Pandurang Patil 26-Aug-2014
 *
 */
public class ToggleSample extends Composite {

    @UiField
    NavListDropDown                        sample;

    @UiField
    AnchorListItem                         sa;

    boolean                                state    = false;
    private static CellTableSampleUiBinder uiBinder = GWT.create(CellTableSampleUiBinder.class);

    interface CellTableSampleUiBinder extends UiBinder<Widget, ToggleSample> {
    }

    public ToggleSample() {
        initWidget(uiBinder.createAndBindUi(this));
        sa.setTitle("Moka");

    }
}
