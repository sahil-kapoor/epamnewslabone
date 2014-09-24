package by.epam.news.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by alex on 09.07.14.
 */
public class ConnectionPool {

    private BlockingQueue<Connection> freeConnection;
    private BlockingQueue<Connection> busyConnection;

    private final static int MAX_CONNECTION_COUNT = 5;


    public ConnectionPool(String driver, String url, String user, String password) throws Exception{
    	System.out.println("pool create");
        freeConnection = new LinkedBlockingQueue<>(MAX_CONNECTION_COUNT);
        busyConnection = new ArrayBlockingQueue<>(MAX_CONNECTION_COUNT);
        try {
            Class.forName(driver);
            for(int i = 0; i < MAX_CONNECTION_COUNT; i++){
                Connection connection = DriverManager.getConnection(
                        url, user, password);
                freeConnection.add(connection);
            }
        } catch (ClassNotFoundException e) {
            throw new Exception("Cant create pool, class not found", e);
        } catch (SQLException e) {
            throw new Exception("Cant create pool, sql error: " + e.getMessage(), e);
        }
    }

    public Connection getConnection() throws Exception{
        Connection connection;
        try {
            connection = freeConnection.take();
            busyConnection.put(connection);
        } catch (InterruptedException e) {
            throw new Exception("Cant take pool connection", e);
        }
        return connection;
    }

    public void returnConnection(Connection connection) throws Exception{
        if (busyConnection.contains(connection)){
            try {
                freeConnection.put(connection);
            } catch (InterruptedException e) {
                throw new Exception("Can't put pool connection back", e);
            }
            busyConnection.remove(connection);
        } else {
            throw new Exception("Try return not pool connection");
        }
    }


    public void destroy() throws SQLException{
    	System.out.println("pool destroy");
            for(Connection connection : freeConnection){
                connection.close();
            }
            for(Connection connection : busyConnection){
                connection.close();
            }
    }

}