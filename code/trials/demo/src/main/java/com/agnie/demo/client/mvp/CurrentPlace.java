/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.demo.client.mvp;

import com.agnie.gwt.common.client.mvp.Place;
import com.google.inject.Singleton;

/**
 * 
 * @author Pandurang Patil 12-Feb-2016 - 5:55:22 pm
 *
 */
@Singleton
public class CurrentPlace {
    private Place<DemoPlaceToken> place;

    /**
     * @return the place
     */
    public Place<DemoPlaceToken> getPlace() {
        return place;
    }

    /**
     * @param place
     *            the place to set
     */
    public void setPlace(Place<DemoPlaceToken> place) {
        this.place = place;
    }

}
