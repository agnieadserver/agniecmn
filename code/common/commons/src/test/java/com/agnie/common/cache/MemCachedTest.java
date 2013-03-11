package com.agnie.common.cache;

import junit.framework.Assert;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.junit.Test;

public class MemCachedTest {

	@Test
	public void firstTest() {
		CacheService service = new MemCacheService("127.0.0.1:11211");
		UserBean user = new UserBean();
		user.setFname("fname");
		user.setLname("lname");
		Phone ph = new Phone();
		ph.setType(Type.HOME);
		ph.setPhone("87089980");
		user.setPhone(ph);
		service.put("sample", user);

		UserBean actual = (UserBean) service.get("sample");
		System.out.println("expected =>" + user);
		System.out.println("actual =>" + actual);
		Assert.assertEquals(user, actual);
	}
}

class UserBean {
	private String	fname;
	private String	lname;
	private Phone	phone;

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

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBean other = (UserBean) obj;
		if (fname == null) {
			if (other.fname != null)
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		if (lname == null) {
			if (other.lname != null)
				return false;
		} else if (!lname.equals(other.lname))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserBean [fname=" + fname + ", lname=" + lname + ", phone=" + phone + "]";
	}

}

class Phone {
	private Type	type;
	private String	phone;

	@JsonSerialize(using = TypeSerializer.class)
	public Type getType() {
		return type;
	}

	@JsonDeserialize(using = TypeDeserializer.class)
	public void setType(Type type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phone other = (Phone) obj;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Phone [type=" + type + ", phone=" + phone + "]";
	}

}

enum Type {
	HOME, MOBILE;
}