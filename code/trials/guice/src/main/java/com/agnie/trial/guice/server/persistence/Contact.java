/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.trial.guice.server.persistence;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Registered user information
 * 
 * 
 */
@Entity
public class Contact extends BaseEntity {

	@Id
	private String	id;
	@Version
	private Integer	version;
	@Basic
	private String	add1;
	@Basic
	private String	add2;
	@Basic
	private String	city;
	@Temporal(TemporalType.TIMESTAMP)
	private Date	createTimeStamp;
	@Temporal(TemporalType.TIMESTAMP)
	private Date	lastTimeStamp;
	@Temporal(TemporalType.TIMESTAMP)
	private Date	lastLoginTimeStamp;

	@Override
	public int hashCode() {
		if (id != null)
			return id.hashCode();
		else
			return 0;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else {
			if (hashCode() != other.hashCode())
				return false;
			else if (!id.equals(other.id))
				return false;
		}
		return true;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the createTimeStamp
	 */
	public Date getCreateTimeStamp() {
		return createTimeStamp;
	}

	/**
	 * @param createTimeStamp
	 *            the createTimeStamp to set
	 */
	public void setCreateTimeStamp(Date createTimeStamp) {
		this.createTimeStamp = createTimeStamp;
	}

	/**
	 * @return the lastTimeStamp
	 */
	public Date getLastTimeStamp() {
		return lastTimeStamp;
	}

	/**
	 * @param lastTimeStamp
	 *            the lastTimeStamp to set
	 */
	public void setLastTimeStamp(Date lastTimeStamp) {
		this.lastTimeStamp = lastTimeStamp;
	}

	/**
	 * @return the lastLoginTimeStamp
	 */
	public Date getLastLoginTimeStamp() {
		return lastLoginTimeStamp;
	}

	/**
	 * @param lastLoginTimeStamp
	 *            the lastLoginTimeStamp to set
	 */
	public void setLastLoginTimeStamp(Date lastLoginTimeStamp) {
		this.lastLoginTimeStamp = lastLoginTimeStamp;
	}

	/**
	 * @return the add1
	 */
	public String getAdd1() {
		return add1;
	}

	/**
	 * @param add1
	 *            the add1 to set
	 */
	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	/**
	 * @return the add2
	 */
	public String getAdd2() {
		return add2;
	}

	/**
	 * @param add2
	 *            the add2 to set
	 */
	public void setAdd2(String add2) {
		this.add2 = add2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.agnie.useradmin.persistance.server.entity.BaseEntity#prePersist()
	 * 
	 */
	@PrePersist
	@Override
	protected void prePersist() {
		super.prePersist();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Contact [id=" + id + ", version=" + version + ", add1=" + add1 + ", add2=" + add2 + ", city=" + city + "]";
	}

}
