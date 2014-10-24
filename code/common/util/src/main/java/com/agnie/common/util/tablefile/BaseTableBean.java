/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.util.tablefile;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agnie.common.util.client.tablefile.TableBean;
import com.agnie.common.util.client.tablefile.TableType;

/**
 * Abstract Base table bean to use it with TableType
 * 
 */
public abstract class BaseTableBean implements TableBean {
	protected static final Log	logger	= LogFactory.getLog(BaseTableBean.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void insertError(String property, String value, List<String> errors) {
		if (property != null && !("".equals(property))) {
			String prop = Character.toLowerCase(property.charAt(0)) + property.substring(1);
			try {
				Field field = getClass().getDeclaredField(prop);
				field.setAccessible(true);
				Object obj = field.get(this);
				if (obj == null) {
					obj = field.getType().newInstance();
					field.set(this, obj);
				}
				if (obj instanceof TableType) {
					TableType type = (TableType) obj;
					type.insertErrors(value, errors);
				}
			} catch (SecurityException e) {
				logger.error(e);
			} catch (NoSuchFieldException e) {
				logger.debug("No such field/property '" + prop + "'");
			} catch (IllegalArgumentException e) {
				logger.error(e);
			} catch (IllegalAccessException e) {
				logger.error(e);
			} catch (InstantiationException e) {
				logger.error(e);
			}

		}
	}
}
