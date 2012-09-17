package com.agnie.trial.guice.server.persistence;

import java.util.Date;

/**
 * Common generic entity methods are defined in BaseEntity
 * 
 * 
 */
public abstract class BaseEntity {

	/**
	 * @return the createTimeStamp
	 */
	public abstract Date getCreateTimeStamp();

	/**
	 * @param createTimeStamp
	 *            the createTimeStamp to set
	 */
	public abstract void setCreateTimeStamp(Date createTimeStamp);

	/**
	 * @return the lastTimeStamp
	 */
	public abstract Date getLastTimeStamp();

	/**
	 * @param lastTimeStamp
	 *            the lastTimeStamp to set
	 */
	public abstract void setLastTimeStamp(Date lastTimeStamp);

	/**
	 * @return the id
	 */
	public abstract String getId();

	/**
	 * @param id
	 *            the id to set
	 */
	public abstract void setId(String id);

	protected void prePersist() {
		/**
		 * TODO: update the inner entities create and last time stamp
		 */
		if (this.getId() == null || "".equals(this.getId().trim())) {
			this.setId(java.util.UUID.randomUUID().toString());
		}
		Date dt = new Date(); // replace this with servicetime call
		if (this.getCreateTimeStamp() == null) {
			this.setCreateTimeStamp(dt);
		}
		this.setLastTimeStamp(dt);
	}

}
