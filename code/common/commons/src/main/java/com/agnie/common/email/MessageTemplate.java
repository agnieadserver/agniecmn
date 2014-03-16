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
	 * @param configPath
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
	 * @return
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
