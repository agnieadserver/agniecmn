/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
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
