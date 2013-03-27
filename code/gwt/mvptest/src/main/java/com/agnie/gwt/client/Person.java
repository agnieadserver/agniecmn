package com.agnie.gwt.client;

public class Person {

	private String	fname;
	private String	lname;
	private String	age;
	private String	emailid;

	public Person() {
		super();
	}

	public Person(String fname, String lname, String age, String emailid) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.age = age;
		this.emailid = emailid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

}
