/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
/**
 * 
 */
package com.agnie.common.server.auth;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.jexl2.JexlContext;

import com.agnie.common.gwt.serverclient.client.dto.AccessControlList;

/**
 * Implementation of JexlContext which will help in evaluating permission expression. Every individual permission from
 * logical expression of the permission will be checked and evaluated if that permission is present in ACL. If present
 * it will return true.
 */
public class ACLContext implements JexlContext {

	private AccessControlList	acl;

	private List<String>		checkPerms	= new ArrayList<String>();

	public ACLContext() {

	}

	/**
	 * @param acl
	 */
	public ACLContext(AccessControlList acl) {
		this.acl = acl;
	}

	/**
	 * @return the acl
	 */
	public AccessControlList getAcl() {
		return acl;
	}

	public void clear() {
		checkPerms.clear();
	}

	/**
	 * @param acl
	 *            the acl to set
	 */
	public void setAcl(AccessControlList acl) {
		this.acl = acl;
	}

	/**
	 * /** If you call this method it will return you list of permissions that were checked while evaluating the
	 * permissions expression. You need to call this method immediately after evaluatePermissionExp() other wise you
	 * will lose the information for given permission expression if evaluatePermissionExp() called once again to
	 * evaluate other expression.
	 * 
	 * @return the checkPerms
	 */
	public List<String> getCheckPerms() {
		return checkPerms;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.jexl2.JexlContext#get(java.lang.String)
	 */
	public Object get(String perm) {
		checkPerms.add(perm);
		if (acl != null)
			return acl.check(perm);
		else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.jexl2.JexlContext#set(java.lang.String, java.lang.Object)
	 */
	public void set(String name, Object value) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.jexl2.JexlContext#has(java.lang.String)
	 */
	public boolean has(String name) {
		// TODO Auto-generated method stub
		return false;
	}

}
