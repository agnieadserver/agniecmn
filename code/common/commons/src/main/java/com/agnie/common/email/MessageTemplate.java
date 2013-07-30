package com.agnie.common.email;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.text.StrSubstitutor;

public class MessageTemplate {

	// template file name
	private String	template;

	// contents of the template file
	private String	messageTemplate;

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
			messageTemplate = IOUtils.toString(getClass().getResource("/" + template));
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
