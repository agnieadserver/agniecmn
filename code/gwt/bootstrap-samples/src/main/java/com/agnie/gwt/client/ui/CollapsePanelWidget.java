package com.agnie.gwt.client.ui;

import org.gwtbootstrap3.client.ui.Anchor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CollapsePanelWidget extends Composite {

    @UiField
    Anchor                                     anchor;

    boolean                                    isCollapsed;

    private static CollapsePanelWidgetUiBinder uiBinder = GWT.create(CollapsePanelWidgetUiBinder.class);

    interface CollapsePanelWidgetUiBinder extends UiBinder<Widget, CollapsePanelWidget> {
    }

    public CollapsePanelWidget() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("anchor")
    public void clickAnchor(ClickEvent event) {
        isCollapsed = !isCollapsed;
        if (isCollapsed) {
            anchor.setStyleName("up");
        } else {
            anchor.setStyleName("down");
        }
    }

}
