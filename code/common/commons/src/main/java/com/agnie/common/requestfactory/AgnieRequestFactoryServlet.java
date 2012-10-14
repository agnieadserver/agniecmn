/**
 * 
 */
package com.agnie.common.requestfactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.web.bindery.requestfactory.server.ExceptionHandler;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.server.ServiceLayerDecorator;

/**
 *
 */
@Singleton
public class AgnieRequestFactoryServlet extends RequestFactoryServlet {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Inject
	public AgnieRequestFactoryServlet(ExceptionHandler expHandler, ServiceLayerDecorator decorator) {
		super(expHandler, decorator);
	}
}
