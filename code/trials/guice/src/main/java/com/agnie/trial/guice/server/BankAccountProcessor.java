package com.agnie.trial.guice.server;

import com.google.inject.Singleton;

@Singleton
public class BankAccountProcessor implements PaymentProcessor {

	@Override
	public boolean transfer(Account source, Account destination, double amount) throws Exception {
		source.debit(amount);
		destination.credit(amount);
		return true;
	}

}
