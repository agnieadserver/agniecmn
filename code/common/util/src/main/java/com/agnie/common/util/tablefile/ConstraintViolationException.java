/**
 * 
 */
package com.agnie.common.util.tablefile;

import java.lang.annotation.Annotation;
import java.util.List;

public class ConstraintViolationException extends RuntimeException {

	private String				header;
	private long				linenumber;
	private List<Annotation>	failedConstraints;

	/**
     * 
     */
	private static final long	serialVersionUID	= 1L;

	public ConstraintViolationException(String header, long linenumber, List<Annotation> failedConstraints) {
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
	public List<Annotation> getFailedConstraints() {
		return failedConstraints;
	}

	public String getFailedConstraintsStr() {
		StringBuffer resp = new StringBuffer();
		if (failedConstraints != null) {
			boolean first = true;
			for (Annotation ann : failedConstraints) {
				if (!first) {
					resp.append(", ");
				} else {
					first = false;
				}
				resp.append(ann.annotationType().getSimpleName());
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
