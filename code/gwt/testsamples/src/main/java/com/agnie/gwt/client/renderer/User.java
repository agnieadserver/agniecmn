/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.gwt.client.renderer;

public class User {
	String name;
	String wingName;
	String flatNo;
	String type; 
	String status; 
	String socImgUrl;
	String takeAction;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWingName() {
		return wingName;
	}
	public void setWingName(String wingName) {
		this.wingName = wingName;
	}
	public String getFlatNo() {
		return flatNo;
	}
	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSocImgUrl() {
		return socImgUrl;
	}
	public void setSocImgUrl(String socImgUrl) {
		this.socImgUrl = socImgUrl;
	}
	public String getTakeAction() {
		return takeAction;
	}
	public void setTakeAction(String takeAction) {
		this.takeAction = takeAction;
	}

}
