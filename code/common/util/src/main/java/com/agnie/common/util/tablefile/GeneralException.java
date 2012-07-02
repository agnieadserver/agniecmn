/**
 * 
 */
package com.agnie.common.util.tablefile;

/**
 *
 */
public class GeneralException extends RuntimeException {

	private String				errorCode;
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * 
	 */
	public GeneralException() {
	}

	/**
	 * @param message
	 */
	public GeneralException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * @param cause
	 */
	public GeneralException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public GeneralException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

}
