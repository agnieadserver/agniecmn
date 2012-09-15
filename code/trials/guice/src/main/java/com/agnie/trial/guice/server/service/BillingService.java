package com.agnie.trial.guice.server.service;

import com.agnie.trial.guice.server.Account;

public interface BillingService {

	boolean chargeOrder(String item, Account account);
}
