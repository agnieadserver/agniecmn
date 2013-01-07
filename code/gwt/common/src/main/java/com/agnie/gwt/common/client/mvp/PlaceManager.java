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
		if (token != null && token.contains(":")) {
			String[] frTokens = token.split(":");

			place = new Place<PLACE>(Enum.valueOf(placeType, frTokens[0]));
			place.setParameters(frTokens[1]);
		} else {
			place = new Place<PLACE>(Enum.valueOf(placeType, token));
		}
		return place;
	}
}
