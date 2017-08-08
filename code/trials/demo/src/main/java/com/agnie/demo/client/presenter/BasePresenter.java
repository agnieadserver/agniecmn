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

import com.agnie.gwt.common.client.mvp.Presenter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;

/**
 * @author Pandurang Patil 26-Jan-2017 - 6:58:36 pm
 *
 */
public abstract class BasePresenter implements Presenter {
    @Inject
    @Named("header")
    RootPanel header;

    public boolean go() {
        header.clear();
        //header.add(new Label("---------------------Demo HEADER ------------------"));
        return true;
    }

}
