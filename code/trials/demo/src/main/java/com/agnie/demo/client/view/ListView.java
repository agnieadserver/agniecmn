/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.demo.client.view;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialListBox;
import gwt.material.design.client.ui.MaterialToast;

import com.agnie.demo.client.injector.DemoInjector;
import com.agnie.demo.client.mvp.DemoPlaceToken;
import com.agnie.gwt.common.client.mvp.MainView;
import com.agnie.gwt.common.client.mvp.Place;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author Pandurang Patil 26-Jan-2017 - 5:07:05 pm
 *
 */
@Singleton
public class ListView extends Composite implements MainView {

    private static ListViewUiBinder uiBinder = GWT.create(ListViewUiBinder.class);

    interface ListViewUiBinder extends UiBinder<Widget, ListView> {
    }

    @UiField
    MaterialButton  create;
    @UiField
    MaterialListBox test;

    @Inject
    DemoInjector    injector;

    public ListView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("create")
    public void createHandler(ClickEvent event) {
        injector.getAppController().go(new Place<DemoPlaceToken>(DemoPlaceToken.CREATE));
    }

    @UiHandler("test")
    public void addTestHandler(ValueChangeEvent<String> event) {
        GWT.log("event is getting fired");
        MaterialToast.fireToast("I Love Material Design - " + event.getValue());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.agnie.gwt.common.client.mvp.MainView#shouldWeProceed()
     */
    public boolean shouldWeProceed() {
        // TODO Auto-generated method stub
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.agnie.gwt.common.client.mvp.MainView#setDefaultFocus()
     */
    public void setDefaultFocus() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.agnie.gwt.common.client.mvp.MainView#setBrowserTabActive(boolean)
     */
    public void setBrowserTabActive(boolean state) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.agnie.gwt.common.client.mvp.MainView#defaultEnterPressed()
     */
    public void defaultEnterPressed() {
        // TODO Auto-generated method stub

    }
}
