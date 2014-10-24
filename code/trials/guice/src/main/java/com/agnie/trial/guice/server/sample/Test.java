/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.trial.guice.server.sample;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * @author Pandurang Patil 24-Oct-2014
 *
 */
public class Test {

	@Inject
	private DISecondLevel	secondLevel;

	public void process() {
		System.out.println(secondLevel);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("using Guice");
		Injector injector = Guice.createInjector(new SampleModule());
		Test t = injector.getInstance(Test.class);
		t.process();
		// DIFirstLevel firstLevel1 = injector.getInstance(DIFirstLevel.class);
		//
		// System.out.println(firstLevel1);
		//
		// DIFirstLevel firstLevel2 = injector.getInstance(DIFirstLevel.class);
		// System.out.println(firstLevel2);
		//
		// DISecondLevel secLevel = injector.getInstance(DISecondLevel.class);
		// System.out.println(secLevel);
	}

}
