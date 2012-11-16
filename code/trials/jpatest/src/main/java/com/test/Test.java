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
