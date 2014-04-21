/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
/**
 * 
 */
package com.agnie.common.util.client.tablefile;

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
