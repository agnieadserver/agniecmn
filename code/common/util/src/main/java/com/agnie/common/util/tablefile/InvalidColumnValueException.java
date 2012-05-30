/**
 *
 */
package com.agnie.common.util.tablefile;

/**
 * 
 */
public class InvalidColumnValueException extends RuntimeException {

	/**
     *
     */
	private static final long	serialVersionUID	= 1L;
	private String				header;
	private long				lineNumber;
	private String				invalidValue;
	private String				msg;

	/**
	 * @param header
	 * @param lineNumber
	 * @param invalidValue
	 */
	public InvalidColumnValueException(String header, long lineNumber, String invalidValue) {
		this(header, lineNumber, invalidValue, null);
	}

	/**
	 * @param header
	 * @param lineNumber
	 * @param invalidValue
	 */
	public InvalidColumnValueException(String header, long lineNumber, String invalidValue, String msg) {
		this.header = header;
		this.lineNumber = lineNumber;
		this.invalidValue = invalidValue;
		this.msg = msg;
	}

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * /**
	 * 
	 * @return the lineNumber
	 */
	public long getLineNumber() {
		return lineNumber;
	}

	/**
	 * @return the invalidValue
	 */
	public String getInvalidValue() {
		return invalidValue;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

}
