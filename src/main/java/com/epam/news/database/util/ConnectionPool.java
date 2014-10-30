package com.epam.news.database.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.epam.news.exception.DataBaseException;
import com.epam.news.util.SystemLogger;

/**
 * Class represent container for connections.
 * 
 * @author Alexander_Demeshko
 *
 */
public class ConnectionPool {

	/**
	 * Free connection.
	 * 
	 * Using to store free connection.
	 */
	private BlockingQueue<Connection> freeConnection;

	/**
	 * Busy connection.
	 * 
	 * Using to store all connection.
	 */
	private List<Connection> allConnection;

	/**
	 * After creating pool get MAX_CONNECTION_COUNT connections from jdbc and
	 * put in freeConnection;
	 * 
	 * @param driver
	 * @param url
	 * @param user
	 * @param password
	 * @throws DataBaseException
	 */
	public ConnectionPool(String driver, String url, String user,
			String password, Integer maxConnectionCount) throws DataBaseException {
		freeConnection = new ArrayBlockingQueue<>(maxConnectionCount);
		allConnection = new ArrayList<>(maxConnectionCount);
		try {
			Class.forName(driver);
			for (int i = 0; i < maxConnectionCount; i++) {
				Connection connection = DriverManager.getConnection(url, user,
						password);
				freeConnection.add(connection);
				allConnection.add(connection);
			}
		} catch (ClassNotFoundException e) {
			throw new DataBaseException("Cant create pool, class not found", e);
		} catch (SQLException e) {
			throw new DataBaseException("Cant create pool, sql error: "
					+ e.getMessage(), e);
		}
	}

	/**
	 * Get connection.
	 * 
	 * When somebody try get connection if freeConnection is empty he wait while
	 * in freeConnection appear a connection. And before return connection to
	 * somebody pool put connection to busy connections.
	 * 
	 * @return Connection
	 * @throws DataBaseException
	 */
	public Connection getConnection() throws DataBaseException {
		Connection connection;
		try {
			connection = freeConnection.take();
			if (null == connection) {
				throw new DataBaseException("null connection in poll");
			}

			try {
				if (connection.isClosed()) {
					throw new DataBaseException("closed connection in pool");
				}
			} catch (SQLException e) {
				SystemLogger.getLogger().error(
						"Error when try check closeble connection in pool", e);
			}
		} catch (InterruptedException e) {
			throw new DataBaseException("Cant take pool connection", e);
		}
		return connection;
	}

	/**
	 * Return connection.
	 * 
	 * Return connection back to pool. Throws DataBaseException if connection is
	 * null, closed, getting not from this pool or connection cannot be checked
	 * for closable(SQLException)
	 * 
	 * @param connection
	 * @throws DataBaseException
	 */

	public void returnConnection(Connection connection)
			throws DataBaseException {

		if (null == connection) {
			throw new DataBaseException("Try return null connection");
		}

		try {
			if (connection.isClosed()) {
				throw new DataBaseException("Try return closed connection");
			}
		} catch (SQLException e) {
			SystemLogger.getLogger().error(
					"Error when try check closeble connection in pool", e);
		}
		freeConnection.add(connection);
	}

	/**
	 * Destroy.
	 * 
	 * Close all connection.
	 * 
	 * @throws SQLException
	 */
	public void destroy() throws SQLException {
		for (Connection connection : allConnection) {
			connection.close();
		}
	}

}