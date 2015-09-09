/**
 * 
 */
package com.agnie.gwt.bootstrap.proto.admin.client.ui.cellwidgets;

import org.gwtbootstrap3.client.ui.Anchor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * @author rajgaurav
 *
 */
public class AnchorCollapse extends Anchor {

    boolean isCollapsed;

    public AnchorCollapse() {
        this.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                isCollapsed = !isCollapsed;
                setCollapse(isCollapsed);
            }
        });
    }

    public void setCollapse(boolean state) {
        if (state) {
            AnchorCollapse.this.setStyleName("up");
        } else {
            AnchorCollapse.this.setStyleName("down");
        }
    }

    public boolean isCollapseState() {
        return isCollapsed;
    }

}
