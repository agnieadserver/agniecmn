/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
/**
 *
 */
package com.agnie.common.util.client.tablefile;

/**
 * 
 */
public class InvalidColumnValueException extends RuntimeException {

	/**
     *
     */
	private static final long	serialVersionUID	= 1L;
	private String				header;
	private String				invalidValue;

	public InvalidColumnValueException(String header, String invalidValue, Throwable cause) {
		this(header, invalidValue, null, cause);
	}

	public InvalidColumnValueException(String header, String invalidValue) {
		this(header, invalidValue, null, null);
	}

	/**
	 * @param header
	 * @param invalidValue
	 * @param msg
	 */
	public InvalidColumnValueException(String header, String invalidValue, String msg) {
		this(header, invalidValue, msg, null);
	}

	/**
	 * @param header
	 * @param lineNumber
	 * @param invalidValue
	 */
	public InvalidColumnValueException(String header, String invalidValue, String msg, Throwable cause) {
		super(msg, cause);
		this.header = header;
		this.invalidValue = invalidValue;
	}

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @return the invalidValue
	 */
	public String getInvalidValue() {
		return invalidValue;
	}

}
