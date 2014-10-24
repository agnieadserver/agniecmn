/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *  
 *  NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 *  any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 *  and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 *  law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 *  permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.trial.guice.server.sample;

import com.google.inject.AbstractModule;

/**
 * @author Pandurang Patil 24-Oct-2014
 *
 */
public class SampleModule extends AbstractModule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(UniqueIdentity.class).asEagerSingleton();
	}

}
