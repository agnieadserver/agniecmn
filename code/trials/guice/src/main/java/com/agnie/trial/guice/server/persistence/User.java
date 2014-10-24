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
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

/**
 * Registered user information
 * 
 * 
 */
@Entity
public class User extends BaseEntity {

	@Id
	private String			id;
	@Version
	private Integer			version;
	@Basic
	private String			firstName;
	@Basic
	private String			lastName;
	@NotNull
	private String			userName;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Contact>	contacts;
	@Temporal(TemporalType.TIMESTAMP)
	private Date			createTimeStamp;
	@Temporal(TemporalType.TIMESTAMP)
	private Date			lastTimeStamp;
	@Temporal(TemporalType.TIMESTAMP)
	private Date			lastLoginTimeStamp;

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
		User other = (User) obj;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * @return the contacts
	 */
	public List<Contact> getContacts() {
		return contacts;
	}

	/**
	 * @param contacts
	 *            the contacts to set
	 */
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
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
		return "User [id=" + id + ", version=" + version + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", contacts=" + contacts + "]";
	}

}
