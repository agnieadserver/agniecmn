package com.agnie.trial.guice.server;

import com.agnie.trial.guice.server.service.BillingService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class SimpleTest {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new BillingModule());
		BillingService service = injector.getInstance(BillingService.class);

		Account purchaserAccount = new SavingAccount(100);
		if(service.chargeOrder("CHICKEN BURGER", purchaserAccount)){
			System.out.println("First \'CHICKEN BURGER\' order placed");
		}else{
			System.out.println("First \'CHICKEN BURGER\' order is not placed");
		}
		
		if(service.chargeOrder("CHICKEN KATHI ROLL", purchaserAccount)){
			System.out.println("First \'CHICKEN KATHI ROLL\' order placed");
		}else{
			System.out.println("First \'CHICKEN KATHI ROLL\' order is not placed");
		}
		
		Account anotherPurchAccount = new SavingAccount(1000);
		if(service.chargeOrder("CHICKEN BURGER", anotherPurchAccount)){
			System.out.println("Second \'CHICKEN BURGER\' order placed");
		}else{
			System.out.println("Second \'CHICKEN BURGER\' order is not placed");
		}
		
		if(service.chargeOrder("CHICKEN KATHI ROLL", anotherPurchAccount)){
			System.out.println("Second \'CHICKEN KATHI ROLL\' order placed");
		}else{
			System.out.println("Second \'CHICKEN KATHI ROLL\' order is not placed");
		}
		
		if(service.chargeOrder("CHICKEN ", anotherPurchAccount)){
			System.out.println("Second \'CHICKEN \' order placed");
		}else{
			System.out.println("Second \'CHICKEN \' order is not placed");
		}
	}
}
