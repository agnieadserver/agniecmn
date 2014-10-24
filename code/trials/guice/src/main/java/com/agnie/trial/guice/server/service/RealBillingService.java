/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.trial.guice.server.service;

import java.util.HashMap;
import java.util.Map;

import com.agnie.trial.guice.server.Account;
import com.agnie.trial.guice.server.PaymentProcessor;
import com.agnie.trial.guice.server.SavingAccount;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RealBillingService implements BillingService {

	private Account				billerAccount;
	private PaymentProcessor	processor;
	private Map<String, Double>	menu	= new HashMap<String, Double>();

	@Inject
	RealBillingService(PaymentProcessor processor) {
		this.processor = processor;
		billerAccount = new SavingAccount(1000);
		menu.put("CHICKEN SIKH KABAB", 100D);
		menu.put("CHICKEN BURGER", 60D);
		menu.put("CHICKEN ROLL", 60D);
		menu.put("CHICKEN KATHI ROLL", 80D);
	}

	@Override
	public boolean chargeOrder(String item, Account account) {
		if (menu.containsKey(item.toUpperCase())) {
			double amount = menu.get(item);
			try {
				processor.transfer(account, billerAccount, amount);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	@Override
	public void printOwnerBalance() {
		System.out.println("Biller balance => " + billerAccount);
	}

}
