package com.agnie.trial.guice.server;

public class SavingAccount implements Account {

	private double	balance	= 0;

	public SavingAccount(double balance) {
		this.balance = balance;
	}

	@Override
	public void debit(double amount) throws Exception {
		if (this.balance > amount) {
			this.balance = this.balance - amount;
		} else {
			throw new Exception("Insufficient balance");
		}
	}

	@Override
	public void credit(double amount) throws Exception {
		this.balance += amount;
	}

}
