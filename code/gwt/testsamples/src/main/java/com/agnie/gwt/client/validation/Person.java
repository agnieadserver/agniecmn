/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.client.validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class Person {
	/**
	 * 
	 */
	public Person(){
		
	}
	@NotNull
	@Size(min = 4,max=10, message = "Name must be at least 4 characters long.")
	private String	name;
	
	@NotNull
	/*@Pattern(regexp="[A-Za-z0-9 \t~!#$%&()@^*_+?><]+[.]?[A-Za-z0-9 \t~!#$%&()@^*_+?><]+@+[A-Za-z0-9 \t~!#$%&()@^*_+?><]+[.]+(com|(co+[.]+in))", message="Invalid email id types allowed are{(xyz@xyz.com)/(xyz.xyz@xyz.com)}or{(xyz@qyz.co.in)/(xyz.xyz@xyz.co.in)}space tab allowed")*/
	@Pattern(regexp="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="Invalid email id types allowed are{(xyz@xyz.com)/(xyz.xyz@xyz.com)}or{(xyz@qyz.co.in)/(xyz.xyz@xyz.co.in)}space tab allowed")
	private String email;
	
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
