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
