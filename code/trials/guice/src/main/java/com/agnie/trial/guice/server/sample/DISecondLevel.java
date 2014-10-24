/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.trial.guice.server.sample;

import java.util.UUID;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author Pandurang Patil 24-Oct-2014
 *
 */
@Singleton
public class DISecondLevel {
	private String			secondLevelId;

	@Inject
	private DIFirstLevel	firstLevel;

	/**
	 * 
	 */
	public DISecondLevel() {
		secondLevelId = "DISecondLevel - " + UUID.randomUUID().toString();
	}

	/**
	 * @return the secondLevelId
	 */
	public String getSecondLevelId() {
		return secondLevelId;
	}

	/**
	 * @param secondLevelId
	 *            the secondLevelId to set
	 */
	public void setSecondLevelId(String secondLevelId) {
		this.secondLevelId = secondLevelId;
	}

	/**
	 * @return the firstLevel
	 */
	public DIFirstLevel getFirstLevel() {
		return firstLevel;
	}

	/**
	 * @param firstLevel
	 *            the firstLevel to set
	 */
	public void setFirstLevel(DIFirstLevel firstLevel) {
		this.firstLevel = firstLevel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DISecondLevel [secondLevelId=" + secondLevelId + ", firstLevel=" + firstLevel + "]";
	}

}
