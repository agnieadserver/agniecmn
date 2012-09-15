package com.agnie.trial.guice.server;

public interface PaymentProcessor {

	boolean transfer(Account source, Account destination, double amount) throws Exception;
}
