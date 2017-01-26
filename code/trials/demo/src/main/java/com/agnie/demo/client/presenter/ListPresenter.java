/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.demo.client.presenter;

import javax.inject.Named;

import com.agnie.demo.client.view.ListView;
import com.agnie.gwt.common.client.mvp.MainView;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author Pandurang Patil 26-Jan-2017 - 5:13:18 pm
 *
 */
@Singleton
public class ListPresenter extends BasePresenter {
    @Inject
    ListView  view;
    @Inject
    @Named("container")
    RootPanel container;

    /*
     * (non-Javadoc)
     * 
     * @see com.agnie.gwt.common.client.mvp.Presenter#go()
     */
    public boolean go() {
        if (super.go()) {
            container.clear();
            container.add(view);
            return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.agnie.gwt.common.client.mvp.Presenter#postRender()
     */
    public void postRender() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.agnie.gwt.common.client.mvp.Presenter#checkPermission(java.lang.String)
     */
    public boolean checkPermission(String permission) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.agnie.gwt.common.client.mvp.Presenter#getMainView()
     */
    public MainView getMainView() {
        return view;
    }

}
