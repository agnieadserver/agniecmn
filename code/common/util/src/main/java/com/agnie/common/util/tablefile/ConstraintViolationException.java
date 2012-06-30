/**
 * 
 */
package com.agnie.common.util.tablefile;

import java.util.List;

public class ConstraintViolationException extends RuntimeException {

	private String				header;
	private List<String>		failedConstraints;

	/**
     * 
     */
	private static final long	serialVersionUID	= 1L;

	public ConstraintViolationException(String header, List<String> failedConstraints) {
		this.header = header;
		this.failedConstraints = failedConstraints;
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
	public List<String> getFailedConstraints() {
		return failedConstraints;
	}

	public String getFailedConstraintsStr() {
		StringBuffer resp = new StringBuffer();
		if (failedConstraints != null) {
			boolean first = true;
			for (String ann : failedConstraints) {
				if (!first) {
					resp.append(", ");
				} else {
					first = false;
				}
				resp.append(ann);
			}
		}
		return resp.toString();
	}

}
