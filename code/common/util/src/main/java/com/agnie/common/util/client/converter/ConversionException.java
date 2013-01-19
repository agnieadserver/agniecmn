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
