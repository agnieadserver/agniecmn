/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.entity;

import java.util.List;

/**
 * @author rajgaurav
 *
 */
public class ChartValue {

	List<ChartColumn>	listColumn;

	List<ChartEntity>	listValue;

	public List<ChartEntity> getListValue() {
		return listValue;
	}

	public void setListValue(List<ChartEntity> listValue) {
		this.listValue = listValue;
	}

	public ChartValue(List<ChartColumn> lisColumn, List<ChartEntity> listValue) {
		this.listColumn = lisColumn;
		this.listValue = listValue;
	}

	public List<ChartColumn> getListColumn() {
		return listColumn;
	}

	public void setLisColumn(List<ChartColumn> lisColumn) {
		this.listColumn = lisColumn;
	}

}
