package by.epam.news.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import by.epam.news.util.SystemLogger;

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
	 * Using to store busy connection.
	 */
	private BlockingQueue<Connection> busyConnection;

	/**
	 * Max connection count.
	 * 
	 * Connection pool take this count of connections from jdbc driver.
	 */
	private final static int MAX_CONNECTION_COUNT = 5;

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
			String password) throws DataBaseException {
		freeConnection = new ArrayBlockingQueue<>(MAX_CONNECTION_COUNT);
		busyConnection = new ArrayBlockingQueue<>(MAX_CONNECTION_COUNT);
		try {
			Class.forName(driver);
			for (int i = 0; i < MAX_CONNECTION_COUNT; i++) {
				Connection connection = DriverManager.getConnection(url, user,
						password);
				freeConnection.add(connection);
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
			busyConnection.put(connection);
		} catch (InterruptedException e) {
			throw new DataBaseException("Cant take pool connection", e);
		}
		return connection;
	}

	/**
	 * Return connection.
	 * 
	 * Return connection back to pool. Throws DataBaseException if connection is
	 * null, closed, getting not from this pool or connection cannot be checked for closable(SQLException)
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

		if (busyConnection.contains(connection)) {
			try {
				freeConnection.put(connection);
			} catch (InterruptedException e) {
				throw new DataBaseException("Can't put pool connection back", e);
			}
			busyConnection.remove(connection);
		} else {
			throw new DataBaseException("Try return not pool connection");
		}
	}

	/**
	 * Destroy.
	 * 
	 * Close all connection from from free and busy queues.
	 * @throws SQLException
	 */
	public void destroy() throws SQLException {
		for (Connection connection : freeConnection) {
			connection.close();
		}
		for (Connection connection : busyConnection) {
			connection.close();
		}
	}

}