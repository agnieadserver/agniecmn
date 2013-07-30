package com.agnie.common.exception;

public class UnexpectedException extends RuntimeException {

	private static final long	serialVersionUID	= 1L;

	public UnexpectedException() {
	}

	/**
	 * @param message
	 */
	public UnexpectedException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public UnexpectedException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UnexpectedException(String message, Throwable cause) {
		super(message, cause);
	}

}
