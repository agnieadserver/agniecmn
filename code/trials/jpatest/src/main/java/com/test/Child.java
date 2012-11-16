package com.test;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Version;

/**
 * to define permissions for a domain
 * 
 * 
 */
@Entity
public class Child {
	@Id
	protected String	id;
	@Version
	protected Integer	version;
	@Basic
	private String		name;
	@Basic
	private String		description;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@PrePersist
	protected void prePersist() {
		if (this.getId() == null || "".equals(this.getId().trim())) {
			this.setId(java.util.UUID.randomUUID().toString());
		}
	}
}
