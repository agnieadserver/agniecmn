package com.agnie.trial.guice.server;

import com.agnie.trial.guice.server.service.BillingService;
import com.agnie.trial.guice.server.service.RealBillingService;
import com.google.inject.AbstractModule;

public class BillingModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(PaymentProcessor.class).to(BankAccountProcessor.class);
		bind(BillingService.class).to(RealBillingService.class);
	}

}
