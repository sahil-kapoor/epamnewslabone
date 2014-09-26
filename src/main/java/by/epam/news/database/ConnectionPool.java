package by.epam.news.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import by.epam.news.util.SystemLogger;

public class ConnectionPool {

    private BlockingQueue<Connection> freeConnection;
    private BlockingQueue<Connection> busyConnection;

    private final static int MAX_CONNECTION_COUNT = 5;


    public ConnectionPool(String driver, String url, String user, String password) throws DataBaseException{
        freeConnection = new ArrayBlockingQueue<>(MAX_CONNECTION_COUNT);
        busyConnection = new ArrayBlockingQueue<>(MAX_CONNECTION_COUNT);
        try {
            Class.forName(driver);
            for(int i = 0; i < MAX_CONNECTION_COUNT; i++){
                Connection connection = DriverManager.getConnection(
                        url, user, password);
                freeConnection.add(connection);
            }
        } catch (ClassNotFoundException e) {
            throw new DataBaseException("Cant create pool, class not found", e);
        } catch (SQLException e) {
            throw new DataBaseException("Cant create pool, sql error: " + e.getMessage(), e);
        }
    }

    public Connection getConnection() throws DataBaseException{
        Connection connection;
        try {
            connection = freeConnection.take();
            busyConnection.put(connection);
        } catch (InterruptedException e) {
            throw new DataBaseException("Cant take pool connection", e);
        }
        return connection;
    }

    public void returnConnection(Connection connection) throws DataBaseException{
    	
    	if (null == connection){
    		throw new DataBaseException("Try return null connection");
    	}
    	
    	try {
			if (connection.isClosed()){
				throw new DataBaseException("Try return closed connection");
			}
		} catch (SQLException e) {
			SystemLogger.getLogger().error("Error when try check closeble connection in pool", e); 
		}
    	
        if (busyConnection.contains(connection)){
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


    public void destroy() throws SQLException{
        for(Connection connection : freeConnection){
            connection.close();
        }
        for(Connection connection : busyConnection){
            connection.close();
        }
    }

}