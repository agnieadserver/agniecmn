/**
 * 
 */
package com.agnie.common.util.csv;

import java.util.List;

import com.agnie.common.util.validator.Validator;

public class ConstraintViolationException extends RuntimeException {

	private String				header;
	private long				linenumber;
	private List<Validator>		failedConstraints;

	/**
     * 
     */
	private static final long	serialVersionUID	= 1L;

	public ConstraintViolationException(String header, long linenumber, List<Validator> failedConstraints) {
		this.header = header;
		this.failedConstraints = failedConstraints;
		this.linenumber = linenumber;
	}

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @return the failedConstraints
	 */
	public List<Validator> getFailedConstraints() {
		return failedConstraints;
	}

	public String getFailedConstraintsStr() {
		StringBuffer resp = new StringBuffer();
		if (failedConstraints != null) {
			boolean first = true;
			for (Validator con : failedConstraints) {
				if (!first) {
					resp.append(", ");
				} else {
					first = false;
				}
				resp.append(con.getConstraint().annotationType().getSimpleName());
			}
		}
		return resp.toString();
	}

	/**
	 * @return the linenumber
	 */
	public long getLinenumber() {
		return linenumber;
	}

}
