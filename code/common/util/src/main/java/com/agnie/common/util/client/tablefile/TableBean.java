/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
/**
 * 
 */
package com.agnie.common.util.client.tablefile;

import java.util.List;

/**
 * Abstract interface to define Bean class to read given table contents from given table file
 */
public interface TableBean {

	/**
	 * One can implement this method, which will be called when any validation is failed or converter failed with some
	 * error. In case of single column collection type property, one can apply some constraints on respective add
	 * method. And those constraints will be applicable for every individual tokens of given single column string. So in
	 * case more than one token fails to adhere any constraint or conversion fails for them. insertError method will be
	 * called for each token by passing the same property name. Implementer of insertError method have to take care of
	 * using these error message accordingly.
	 * 
	 * @param property
	 *            Key
	 * @param value
	 *            value
	 * @param errors
	 *            any errors
	 */
	public void insertError(String property, String value, List<String> errors);
}
