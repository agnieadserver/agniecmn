package com.agnie.trial.guice.server;

public interface Account {

	void debit(double amount) throws Exception;

	void credit(double amount) throws Exception;
}
