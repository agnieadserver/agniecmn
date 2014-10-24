/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.trial.guice.server.persistence;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;

public class UserTest {

	private static Injector	injector;

	@BeforeClass
	public static void init() {
		injector = Guice.createInjector(new JpaPersistModule("guice-test"));
	}

	@Test
	public void inittest() {
		injector.getInstance(MyInitializer.class);

		UserManager manager = injector.getInstance(UserManager.class);
		Contact cont = new Contact();
		cont.setAdd1("Address 1");
		cont.setAdd2("Address 2");
		cont.setCity("Pune");
		cont = manager.saveContact(cont);
		User user = new User();
		user.setFirstName("Pranoti");
		user.setLastName("Patil");
		user.setUserName("pranoti.patil");
		List<Contact> list = new ArrayList<Contact>();
		list.add(cont);
		user.setContacts(list);
		user = manager.saveUser(user);
		System.out.println("Contact => " + cont);
		System.out.println("User => " + user);
	}
}
