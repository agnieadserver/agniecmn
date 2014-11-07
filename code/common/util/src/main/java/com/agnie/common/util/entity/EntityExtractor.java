/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.util.entity;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agnie.common.util.tablefile.SimpleJDBCTableIterator;

public class EntityExtractor<E> {

	protected static final Log	logger	= LogFactory.getLog(EntityExtractor.class);
	private Class<E>			entityCls;
	private Connection			connection;

	public EntityExtractor(Class<E> entityCls, Connection connection) {
		super();
		this.entityCls = entityCls;
		this.connection = connection;
	}

	public E getEntity(String sql) throws IOException, SQLException, MultipleRecordsExcpetion {
		SimpleJDBCTableIterator<E> itr = new SimpleJDBCTableIterator<E>(entityCls, connection, sql);
		E entity = itr.next();
		if (itr.hasNext()) {
			throw new MultipleRecordsExcpetion();
		}
		return entity;
	}

	public List<E> getList(String sql) throws IOException, SQLException {
		List<E> result = new ArrayList<E>();
		SimpleJDBCTableIterator<E> itr = new SimpleJDBCTableIterator<E>(entityCls, connection, sql);
		while (itr.hasNext()) {
			E entity = itr.next();
			result.add(entity);
		}
		itr.releaseResources();
		return result;
	}

}
