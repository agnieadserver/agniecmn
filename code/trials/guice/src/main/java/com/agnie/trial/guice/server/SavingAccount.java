/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SavingAccount [balance=" + balance + "]";
	}

}
