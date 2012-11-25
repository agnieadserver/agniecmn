package com.agnie.common.gwt.serverclient.client.helper;

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
