/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
/**
 * 
 */
package com.agnie.common.util.entity;

/**
 * 
 */
public class MultipleRecordsExcpetion extends Exception {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * 
	 */
	public MultipleRecordsExcpetion() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public MultipleRecordsExcpetion(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public MultipleRecordsExcpetion(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MultipleRecordsExcpetion(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
