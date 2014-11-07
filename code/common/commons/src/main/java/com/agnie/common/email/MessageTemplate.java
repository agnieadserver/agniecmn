/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.text.StrSubstitutor;

public class MessageTemplate {

	// template file name
	private String	template;
	// Location of configuration path
	private String	configPath;

	// contents of the template file
	private String	messageTemplate;

	/**
	 * @param template
	 *            template
	 * @param configPath
	 *            config location
	 */
	public MessageTemplate(String template, String configPath) {
		this.template = template;
		this.configPath = configPath;
	}

	public MessageTemplate(String template) {

		this.template = template;
	}

	/**
	 * read the contents of the template on its first use.
	 * 
	 * @throws IOException
	 *             exception
	 */
	private synchronized void init() throws IOException {

		if (messageTemplate == null) {
			if (configPath == null || configPath.isEmpty()) {
				messageTemplate = IOUtils.toString(getClass().getResource("/" + template));
			} else {
				messageTemplate = IOUtils.toString(new FileInputStream(new File(configPath + "/" + template)));
			}
		}
	}

	/**
	 * Generate the message from template by replacing variable values inside template.
	 * 
	 * @param valuesMap
	 *            input variables
	 * @return final message with input variables substituted.
	 * @throws IOException
	 */
	public String getMessage(Map<String, String> valuesMap) throws IOException {

		if (messageTemplate == null) {
			init();
		}
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		return sub.replace(messageTemplate);
	}

}
