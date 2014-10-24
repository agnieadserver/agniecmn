/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.trial.guice.server;

import com.agnie.trial.guice.server.service.BillingService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class SimpleTest {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new BillingModule());
		BillingService service = injector.getInstance(BillingService.class);
		Account purchaserAccount = new SavingAccount(100);
		System.out.println("Balances before first person palce the order");
		System.out.println(" First person balance => " + purchaserAccount);
		service.printOwnerBalance();
		if (service.chargeOrder("CHICKEN BURGER", purchaserAccount)) {
			System.out.println("First \'CHICKEN BURGER\' order placed");
		} else {
			System.out.println("First \'CHICKEN BURGER\' order is not placed");
		}

		if (service.chargeOrder("CHICKEN KATHI ROLL", purchaserAccount)) {
			System.out.println("First \'CHICKEN KATHI ROLL\' order placed");
		} else {
			System.out.println("First \'CHICKEN KATHI ROLL\' order is not placed");
		}

		System.out.println("Balances after first person done with the order");
		System.out.println(" First person balance => " + purchaserAccount);
		service.printOwnerBalance();
		Account anotherPurchAccount = new SavingAccount(1000);
		System.out.println("Balances before second person place the order");
		System.out.println(" Second person balance => " + anotherPurchAccount);
		service.printOwnerBalance();
		if (service.chargeOrder("CHICKEN BURGER", anotherPurchAccount)) {
			System.out.println("Second \'CHICKEN BURGER\' order placed");
		} else {
			System.out.println("Second \'CHICKEN BURGER\' order is not placed");
		}

		if (service.chargeOrder("CHICKEN SIKH KABAB", anotherPurchAccount)) {
			System.out.println("Second \'CHICKEN SIKH KABAB\' order placed");
		} else {
			System.out.println("Second \'CHICKEN SIKH KABAB\' order is not placed");
		}

		if (service.chargeOrder("CHICKEN ", anotherPurchAccount)) {
			System.out.println("Second \'CHICKEN \' order placed");
		} else {
			System.out.println("Second \'CHICKEN \' order is not placed");
		}

		System.out.println("Balances after second person done with the order");
		System.out.println(" Second person balance => " + anotherPurchAccount);
		service.printOwnerBalance();
	}
}
