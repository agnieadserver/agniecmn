/**
 * 
 */
package com.agnie.common.util.csv;

import java.util.List;

public class CSVConstraintViolationException extends RuntimeException {

	private String header;
	private long linenumber;
	private List<CSVConstraint> failedConstraints;

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	public CSVConstraintViolationException(String header, long linenumber,
			List<CSVConstraint> failedConstraints) {
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
	public List<CSVConstraint> getFailedConstraints() {
		return failedConstraints;
	}

	public String getFailedConstraintsStr() {
		StringBuffer resp = new StringBuffer();
		if (failedConstraints != null) {
			boolean first = true;
			for (CSVConstraint con : failedConstraints) {
				if (!first) {
					resp.append(", ");
				} else {
					first = false;
				}
				resp.append(con.name());
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
