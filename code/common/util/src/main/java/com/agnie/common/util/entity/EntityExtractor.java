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

	/**
	 * @param entityCls
	 * @param connection
	 */
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
		return result;
	}

}
