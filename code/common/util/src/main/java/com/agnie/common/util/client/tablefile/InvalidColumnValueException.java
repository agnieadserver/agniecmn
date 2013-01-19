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
