/**
 * 
 */
package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author rajgaurav
 *
 */
public class LoaderWidget extends Composite {

    static {
        LoaderResources.INSTANCE.css().ensureInjected();
    }

    @UiField(provided = true)
    Image                               image;

    private static LoaderWidgetUiBinder uiBinder = GWT.create(LoaderWidgetUiBinder.class);

    interface LoaderWidgetUiBinder extends UiBinder<Widget, LoaderWidget> {
    }

    public LoaderWidget() {

        image = new Image(LoaderResources.INSTANCE.communicating());
        initWidget(uiBinder.createAndBindUi(this));
    }

}
