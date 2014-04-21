/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.util.converter;

import com.agnie.common.util.client.converter.ConversionException;

/**
 * Basic interface for converting Single column tokens into required data type
 * 
 */
public abstract class AbstractSingleColumnConverter {

	public abstract Object convert(String token, Class<?> cls) throws ConversionException;

}
