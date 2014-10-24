/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *  
 *  NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 *  any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 *  and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 *  law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 *  permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.trial.guice.server.sample;

import java.util.UUID;

import com.google.inject.Inject;

/**
 * @author Pandurang Patil 24-Oct-2014
 *
 */
public class UniqueIdentity {

	private String	uniqeId;

	@Inject
	DISecondLevel	secLevel;

	/**
	 * 
	 */
	public UniqueIdentity() {
		uniqeId = UUID.randomUUID().toString();
	}

	/**
	 * @return the uniqeId
	 */
	public String getUniqeId() {
		return uniqeId;
	}

	/**
	 * @param uniqeId
	 *            the uniqeId to set
	 */
	public void setUniqeId(String uniqeId) {
		this.uniqeId = uniqeId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UniqueIdentity [uniqeId=" + uniqeId + ", seconLevel =" + (secLevel != null ? " 'present'" : " 'not present'") + "]";
	}
}
