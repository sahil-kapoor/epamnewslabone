package com.epam.news.database.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.beanutils.ConvertUtils;

import com.epam.news.exception.DataBaseException;
import com.epam.news.model.News;
import com.epam.news.util.SystemLogger;

/**
 * Help class for dao.
 * 
 * Using to remove copy paste from NewsDaoJdbc.
 * Cannot using like object, only static methods.
 * @author Alexander_Demeshko
 *
 */
public final class JdbcUtil {

	private JdbcUtil(){
		
	}
	
	
	/**
	 * Close statement and connection.
	 * 
	 * Close statement and return connection to pool.
	 * @param statement
	 * @param connection
	 * @param pool
	 */
	
	public static void closeStatementAndConnection(Statement statement,
			Connection connection, ConnectionPool pool)  {
		if (null != statement) {
			try {
				statement.close();
			} catch (SQLException e) {
				SystemLogger.getLogger().error("Can't close statment", e); 
			}
		}
		try {
			pool.returnConnection(connection);
		} catch (DataBaseException e) {
			try {
				connection.close();
				SystemLogger.getLogger().error("Can't return connection to pool", e); 
			} catch (SQLException e1) {
				SystemLogger.getLogger().error("Can't close connection", e); 
			}
		}
	}
	
	/**
	 * Load one news.
	 * 
	 * Create news bean from ResultSet
	 * @param result
	 * @return
	 * @throws SQLException
	 */
	public static News getOneNews(ResultSet result) throws SQLException {
		int id = result.getInt(1);
		String title = result.getString(2);
		java.util.Date newsDate = result.getDate(3);
		String brief = result.getString(4);
		String content = result.getString(5);
		return new News(id, title, newsDate, brief, content);
	}

	/**
	 * Set one news.
	 * 
	 * Put news bean to PreparedStatement
	 * @param result
	 * @return
	 * @throws SQLException
	 */
	public static void setOneNews(PreparedStatement statement, News news)
			throws SQLException {
		statement.setString(1, news.getTitle());
		statement.setDate(2, (java.sql.Date) ConvertUtils.convert(
				news.getDate(), java.sql.Date.class));
		statement.setString(3, news.getBrief());
		statement.setString(4, news.getContent());
	}
	
	
	/**
	 * Close ResultSet.
	 * @param result
	 */
	public static void closeResultSet(ResultSet result){
		if ( null != result){
			try {
				result.close();
			} catch (SQLException e) {
				SystemLogger.getLogger().error("Can't close result set", e); 
				e.printStackTrace();
			}
		}
	}

}
