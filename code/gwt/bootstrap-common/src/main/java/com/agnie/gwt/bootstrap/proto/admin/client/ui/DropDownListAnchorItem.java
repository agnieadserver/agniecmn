package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import org.gwtbootstrap3.client.ui.base.AbstractAnchorListItem;
import org.gwtbootstrap3.client.ui.base.AbstractListItem;

import com.agnie.gwt.bootstrap.proto.admin.client.ui.mixin.NavActiveMixin;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.SpanElement;

public class DropDownListAnchorItem extends AbstractAnchorListItem implements com.google.gwt.user.client.ui.HasText {
    private final NavActiveMixin<AbstractListItem> navActiveMixin = new NavActiveMixin<AbstractListItem>(this);

    public DropDownListAnchorItem() {
    }

    public DropDownListAnchorItem(final String text) {
        setText(text);
    }

    @Override
    public void setText(final String text) {
        SpanElement ele = Document.get().createSpanElement();
        ele.setInnerText(text);
        anchor.getElement().appendChild(ele);
    }

    @Override
    public String getText() {
        return anchor.getText();
    }

    @Override
    public void setActive(final boolean active) {
        navActiveMixin.setActive(active);
    }

    @Override
    public boolean isActive() {
        return navActiveMixin.isActive();
    }
}