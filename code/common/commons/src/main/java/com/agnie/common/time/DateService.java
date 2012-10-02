package com.agnie.common.time;

import java.util.Date;

/**
 * Common api which will be used at every place to get current date. TODO: We need to implement a mechanism which will
 * sync up the date with Time services and maintain our own clock for current time and date. And regularly synchronise
 * with time date service. As of now we are using default mechanism to get current date and time.
 * 
 */
public class DateService {

	public Date getCurrentDate() {
		return new Date();
	}

}
