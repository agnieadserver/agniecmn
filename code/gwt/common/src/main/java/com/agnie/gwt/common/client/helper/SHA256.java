/*******************************************************************************
 * ? 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.gwt.common.client.helper;

/**
 * To encrypt the given string with SHA256 Base 64 encryption. To make use of this class one need to include util.js in
 * corresponding module's entry point web file (i. e. .html or .jsp etc). Location to include will be the <base path of
 * the entry point module>/js/util.js
 * 
 */
public class SHA256 {
	public static native String getSHA256Base64(String data) /*-{
																var s = $wnd.b64_sha256(data);
																return s;
																}-*/;

}
