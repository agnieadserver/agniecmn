/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.email;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.mail.Address;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Recipient {

	private Map<RecipientType, Set<Address>>	recipients	= new HashMap<RecipientType, Set<Address>>();

	public Recipient(String recipients) throws AddressException {

		put(RecipientType.TO, recipients);
	}

	/**
	 * Add the comma separated list of recipients list converted into InternetAddress attached TO, CC, BCC
	 * 
	 * @param type
	 *            TO,CC, BCC
	 * @param recipients
	 *            comma separated list of email ids
	 * @throws AddressException
	 *             exception
	 */

	public void put(RecipientType type, String recipients) throws AddressException {

		Address[] adds = InternetAddress.parse(recipients);
		if (this.recipients.get(type) == null) {
			this.recipients.put(type, new HashSet<Address>());
		}
		Set<Address> recipientSet = this.recipients.get(type);
		for (Address address : adds) {
			recipientSet.add(address);
		}
	}

	/**
	 * Get array of InternetAddress for Recipients for given type (TO,CC,BCC)
	 * 
	 * @param type
	 *            TO,CC, BCC
	 * @return list of address
	 */
	public Address[] get(RecipientType type) {

		Set<Address> rec = recipients.get(type);
		Address[] resp = null;
		if (rec != null) {
			resp = new Address[rec.size()];
			int index = 0;
			for (Address address : rec) {
				resp[index] = address;
			}
		}
		return resp;
	}
}
