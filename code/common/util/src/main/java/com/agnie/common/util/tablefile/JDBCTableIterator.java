/**
 * 
 */
package com.agnie.common.util.tablefile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 */
public class JDBCTableIterator<T> extends AbstractTableFileIterator<T> {
	protected static final Log	logger				= LogFactory.getLog(JDBCTableIterator.class);

	private String				sql;
	private ResultSet			resultSet;
	private ArrayList<String>	indexMappedHeaders	= new ArrayList<String>();
	private Connection			connection;

	/**
	 * It is the responsibility of caller of this constructor to close the passed connection. After the given work is
	 * done.
	 * 
	 * @param cls
	 * @param connection
	 * @param sql
	 * @throws IOException
	 * @throws SQLException
	 */
	public JDBCTableIterator(Class<T> cls, Connection connection, String sql) throws IOException, SQLException {
		super(cls);
		this.sql = sql;
		this.connection = connection;
		init();
	}

	private void init() throws SQLException {
		Statement stmt = connection.createStatement();
		resultSet = stmt.executeQuery(sql);
		ResultSetMetaData meta = resultSet.getMetaData();
		for (int index = 1; index <= meta.getColumnCount(); index++) {
			String columnName = meta.getColumnName(index);
			if (columnName.contains("_")) {
				// replace "_" with empty space " " with assumption that developer when uses this iterator will pass on
				// column name with _ if it has to map with given header of property of the bean. This would be useful
				// in case same bean is used for different types of AbstractTableFileIterator implementations. In case
				// of other Iterator it supports Header name with space then in case of JDBCTableIterator it has to
				// support header with space.
				columnName = columnName.replace("_", " ");
			}
			indexMappedHeaders.add(columnName);
		}
	}

	@Override
	protected Map<String, String> readTokens() throws IOException {
		Map<String, String> tokens = new HashMap<String, String>();
		try {
			if (resultSet.next()) {
				for (int index = 1; index <= resultSet.getMetaData().getColumnCount(); index++) {
					String token = resultSet.getString(index);
					if (token != null) {
						tokens.put(indexMappedHeaders.get(index - 1), token);
					}
				}
				return tokens;
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return null;
	}
}
