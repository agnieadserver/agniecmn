/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.UnitOfWork;

public class Test {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new PersistModule());
		UnitOfWork uow = injector.getInstance(UnitOfWork.class);
		uow.begin();
		Service service = injector.getInstance(Service.class);
		String id = service.save();
		uow.end();

		uow = injector.getInstance(UnitOfWork.class);
		uow.begin();
		service = injector.getInstance(Service.class);
		service.remove(id);
		uow.end();
	}
}
