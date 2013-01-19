/**
 * 
 */
package com.agnie.common.util.client.tablefile;

/**
 *
 */
public class DevException extends RuntimeException {

	private String				errorCode;
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * 
	 */
	public DevException() {
	}

	/**
	 * @param message
	 */
	public DevException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * @param cause
	 */
	public DevException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DevException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

}
