/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.requestfactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import com.agnie.common.shutdown.ShutdownProcessor;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * Super Guice context listener to inject Guice injector inside servlet context so that it can be accessed inside jsp.
 * 
 * @author Pandurang Patil 13-Feb-2014
 * 
 */
public abstract class AgnieServletContextListener extends GuiceServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		// No call to super as it also calls getInjector()
		ServletContext sc = servletContextEvent.getServletContext();
		sc.setAttribute(Injector.class.getName(), getInjector());
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		ServletContext sc = servletContextEvent.getServletContext();
		Injector injector = (Injector) sc.getAttribute(Injector.class.getName());
		ShutdownProcessor shutdownProc = injector.getInstance(ShutdownProcessor.class);
		shutdownProc.shutdown(false);
		sc.removeAttribute(Injector.class.getName());
		super.contextDestroyed(servletContextEvent);
	}

}
