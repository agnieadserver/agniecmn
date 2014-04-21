/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.util.client.converter;

public class ConversionException extends Exception {

	private static final long	serialVersionUID	= 1L;

	public ConversionException() {
	}

	/**
	 * @param message
	 */
	public ConversionException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ConversionException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ConversionException(String message, Throwable cause) {
		super(message, cause);
	}

}
