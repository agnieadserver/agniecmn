/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.cache;

import junit.framework.Assert;
import java.net.InetSocketAddress;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.junit.Test;

import com.agnie.common.shutdown.ShutdownProcessor;
import com.thimbleware.jmemcached.CacheImpl;
import com.thimbleware.jmemcached.Key;
import com.thimbleware.jmemcached.LocalCacheElement;
import com.thimbleware.jmemcached.MemCacheDaemon;
import com.thimbleware.jmemcached.storage.CacheStorage;
import com.thimbleware.jmemcached.storage.hash.ConcurrentLinkedHashMap;

public class MemCachedTest {

    private static MemCacheDaemon<LocalCacheElement> daemon;
    private static final String MEMCACHED_HOST = "127.0.0.1";
    private static final int MEMCACHED_PORT = 11211; // Standard Memcached port

    @BeforeClass
    public static void setUpClass() {
        daemon = new MemCacheDaemon<LocalCacheElement>();

        // Use ConcurrentLinkedHashMap as the cache storage
        CacheStorage<Key, LocalCacheElement> storage = ConcurrentLinkedHashMap.create(
            ConcurrentLinkedHashMap.EvictionPolicy.LRU,
            1000, // Max items
            1024 * 1024 // Max bytes (1MB)
        );
        daemon.setCache(new CacheImpl(storage));
        daemon.setAddr(new InetSocketAddress(MEMCACHED_HOST, MEMCACHED_PORT));
        daemon.setBinary(false); // spymemcached by default uses text protocol
        daemon.start();
    }

    @AfterClass
    public static void tearDownClass() {
        if (daemon != null && daemon.isRunning()) {
            daemon.stop();
        }
    }

	@Test
	public void firstTest() {
		CacheService service = new MemCacheService(MEMCACHED_HOST + ":" + MEMCACHED_PORT, new ShutdownProcessor());
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

		user = new UserBean();
		user.setFname("fname");
		user.setLname("lname");
		ph = new Phone();
		ph.setType(Type.HOME);
		ph.setPhone(null);
		user.setPhone(ph);
		service.put("sample-other", user);

		actual = (UserBean) service.get("sample-other");
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

	@JsonSerialize(using = EnumSerializer.class)
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
