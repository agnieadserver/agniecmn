/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.helper;

import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * server side implementation for encrypting given string with SHA 256
 * 
 * 
 */
public class SHA256 {
	public static String getHashedString(String passtoken) {

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] encryptedpass = md.digest(passtoken.getBytes());
			return Base64Utils.toBase64(encryptedpass);

		} catch (Exception ex) {
			Logger.getLogger("SHA256").log(Level.SEVERE, "Exception in getHashString", ex);

		}

		return "";
	}
}
