/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.common.client.mvp;

import com.google.gwt.user.client.History;

public class PlaceManager<PLACE extends Enum<PLACE>> {

	private AppController<PLACE>	appController;
	private Class<PLACE>			placeType;

	public PlaceManager(AppController<PLACE> appController, Class<PLACE> placeType) {
		this.appController = appController;
		this.placeType = placeType;
	}

	public void changePlace(Place<PLACE> place) {
		String token = History.getToken();
		Place<PLACE> oldPlace;
		if (token == null || token.isEmpty()) {
			oldPlace = new Place<PLACE>(appController.getDefaultPlace());
		}
		oldPlace = getTokenToPlace(placeType, token);
		appController.setLastPlace(oldPlace);
		History.newItem(place.toString());
	}

	public Place<PLACE> getTokenToPlace(Class<PLACE> placeType, String token) {
		Place<PLACE> place = null;
		try {
			if (token != null && token.contains(Place.HASH_PARAM_SEPERATOR)) {
				String[] frTokens = token.split(Place.HASH_PARAM_SEPERATOR);
				place = new Place<PLACE>(Enum.valueOf(placeType, frTokens[0]));
				place.setParameters(frTokens[1]);
			} else {
				place = new Place<PLACE>(Enum.valueOf(placeType, token));
			}
		} catch (Exception ex) {
			place = new Place<PLACE>(appController.getDefaultPlace());
		}
		return place;
	}
}
