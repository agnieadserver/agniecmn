/**
 * 
 */
package com.agnie.common.util.tablefile;

/**
 *
 */
public class GeneralException extends RuntimeException {

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
	public GeneralException(String message) {
		super(message);
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

}
