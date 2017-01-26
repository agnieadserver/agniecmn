/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.demo.client.mvp;

import com.agnie.demo.client.injector.DemoInjector;
import com.agnie.demo.client.presenter.CreatePresenter;
import com.agnie.demo.client.presenter.ListPresenter;
import com.agnie.gwt.common.client.mvp.AppController;
import com.agnie.gwt.common.client.mvp.Place;
import com.agnie.gwt.common.client.mvp.Presenter;
import com.google.gwt.user.client.History;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * 
 * @author Pandurang Patil 12-Feb-2016 - 6:30:51 pm
 *
 */
@Singleton
public class DemoAppController extends AppController<DemoPlaceToken> {
    @Inject
    DemoInjector    injector;
    @Inject
    CurrentPlace    currentPlace;
    @Inject
    ListPresenter   listPresenter;
    @Inject
    CreatePresenter createPresenter;

    /**
     * @param placeType
     */
    public DemoAppController() {
        super(DemoPlaceToken.class);
    }

    @Override
    protected DemoPlaceToken getDefaultPlace() {
        return DemoPlaceToken.LIST;
    }

    public void go(Place<DemoPlaceToken> place) {
        String token = History.getToken();
        if (token != null && token.equals(place.toString())) {
            History.fireCurrentHistoryState();
        } else {
            History.newItem(place.toString());
        }
    }

    @Override
    protected Presenter getPresenterForPlace(Place<DemoPlaceToken> place) {

        Presenter presenter = null;
        currentPlace.setPlace(place);
        if (place != null) {

            switch (place.getPlace()) {
            case LIST:
                presenter = listPresenter;
                break;
            case CREATE:
                presenter = createPresenter;
                break;
            default:
                break;
            }
        }
        return presenter;
    }
}
