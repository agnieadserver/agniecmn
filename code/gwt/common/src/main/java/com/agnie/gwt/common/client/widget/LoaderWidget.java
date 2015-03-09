/**
 * 
 */
package com.agnie.gwt.common.client.widget;

import com.agnie.gwt.common.client.mvp.MainView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

/**
 * @author rajgaurav
 *
 */
@Singleton
public class LoaderWidget extends Composite implements MainView {

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
        RootPanel.get().add(this);
        this.setVisible(true);
    }

    @Override
    public boolean shouldWeProceed() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setDefaultFocus() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setBrowserTabActive(boolean state) {

        System.out.println("Alert Value instead" + state);
    }

    @Override
    public void defaultEnterPressed() {
        // TODO Auto-generated method stub

    }

}
