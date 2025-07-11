/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.gwt.serverclient.client.dto;

import junit.framework.Assert;

import org.junit.Test;

import com.agnie.common.gwt.serverclient.client.helper.InvalidPermission;

public class AccessControlListTest {

	@Test
	public void create() {
		try {
			AccessControlList acl = new AccessControlList();
			acl.addPermission("perm_domain_create");
			acl.addPermission("perm_domain_edit");
			acl.addPermission("perm_domain_edit_user_view");
			acl.addPermission("perm_domain_create");
			Assert.assertEquals("perm_domain_create" + "\n" + "perm_domain_edit_user_view" + "\n", acl.toString());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void check() {
		try {
			AccessControlList acl = new AccessControlList();
			acl.addPermission("perm_domain_create");
			acl.addPermission("perm_domain_edit");
			acl.addPermission("perm_domain_edit_user_view");

			Assert.assertTrue(acl.check("perm"));
			Assert.assertTrue(acl.check("perm_domain"));
			Assert.assertTrue(acl.check("perm_domain_create"));
			Assert.assertTrue(acl.check("perm_domain_edit"));
			Assert.assertTrue(acl.check("perm_domain_edit_user_view"));
			Assert.assertFalse(acl.check("perm12"));
			Assert.assertFalse(acl.check("perm12_domain"));
			Assert.assertFalse(acl.check("perm12_domain12"));
			Assert.assertFalse(acl.check("perm_domain_delete"));
			Assert.assertFalse(acl.check("perm_domain_edit_permission_create"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void invalidPermission() {
		try {
			AccessControlList acl = new AccessControlList();
			acl.addPermission("perm23_domain_create");
			Assert.assertTrue(false);
		} catch (InvalidPermission e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		try {
			AccessControlList acl = new AccessControlList();
			acl.addPermission("domain_create");
			Assert.assertTrue(false);
		} catch (InvalidPermission e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
