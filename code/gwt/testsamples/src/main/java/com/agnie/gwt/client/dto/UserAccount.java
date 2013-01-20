package com.agnie.gwt.client.dto;

import com.google.gwt.resources.client.ImageResource;

public class UserAccount {

	private String				title;
	private String				firstName;
	private String				lastName;
	private String				userName;
	private ImageResource		userImg;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public ImageResource getUserImg() {
		return userImg;
	}
	public void setUserImg(ImageResource userImg) {
		this.userImg = userImg;
	}
}
