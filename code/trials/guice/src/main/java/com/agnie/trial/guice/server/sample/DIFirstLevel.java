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
public class DIFirstLevel {

	private String			firstLevelId;

	@Inject
	private UniqueIdentity	id;

	/**
	 * 
	 */
	public DIFirstLevel() {
		firstLevelId = "DIFirstLevel - " + UUID.randomUUID().toString();
	}

	/**
	 * @return the firstLevelId
	 */
	public String getFirstLevelId() {
		return firstLevelId;
	}

	/**
	 * @param firstLevelId
	 *            the firstLevelId to set
	 */
	public void setFirstLevelId(String firstLevelId) {
		this.firstLevelId = firstLevelId;
	}

	/**
	 * @return the id
	 */
	public UniqueIdentity getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(UniqueIdentity id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DIFirstLevel [firstLevelId=" + firstLevelId + ", id=" + id + "]";
	}

}
