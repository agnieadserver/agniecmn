package com.agnie.gwt.helper.requestfactory.marker;

import com.google.web.bindery.requestfactory.shared.ServiceLocator;

/**
 * Marker interface to mark Service class for which Request Factory Service proxy interface need to be generated
 * 
 */
public @interface RFService {
	Class<? extends ServiceLocator> value();
}
