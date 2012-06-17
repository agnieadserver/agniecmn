package com.agnie.gwt.common.client.helper;

/**
 * to encrypt the given string with SHA256 Base 64 encryption
 * 
 */
public class SHA256 {
	public static native String getSHA256Base64(String data) /*-{
																var s = $wnd.b64_sha256(data);
																return s;
																}-*/;

}
