package com.agnie.gwt.client.ui;

import com.agnie.gwt.bootstrap.proto.admin.client.ui.cellwidgets.AnchorCollapse;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CollapsePanelWidget extends Composite {

    @UiField
    AnchorCollapse                             anchor;
    private static CollapsePanelWidgetUiBinder uiBinder = GWT.create(CollapsePanelWidgetUiBinder.class);

    interface CollapsePanelWidgetUiBinder extends UiBinder<Widget, CollapsePanelWidget> {
    }

    public CollapsePanelWidget() {
        initWidget(uiBinder.createAndBindUi(this));
        anchor.setCollapse(true);
    }

}
