/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.gwt.serverclient.client.enums;

public enum QueryString {
	SOURCE("source"), SESSION("sessionid"), GWT_DEV_MODE("gwt.codesvr"), LOCALE("locale"), QUESTION_MARK("?"), AMPERSAND("&"), HASH("#"), USER_NAME("user"), DOMAIN("domain"), SELECTED_DOMAIN(
			"sel-domain"), HISTORY_HASH("history-token"), SELECTED_CONTEXT("sel-context"), CONTEXT("context"), API_ACCESS_KEY("access-key"), REFERER("Referer");

	private String	key;

	private QueryString(String key) {
		this.key = key;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

}
