package by.epam.news.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import by.epam.news.model.News;

public class DaoHelp {

	public static void closeStatementAndConnection(Statement statement,
			Connection connection, ConnectionPool pool) throws Exception {
		if (null != statement) {
			try {
				statement.close();
			} catch (SQLException e) {
				// logging
			}
		}
		try {
			pool.returnConnection(connection);
		} catch (Exception e) {
			try {
				connection.close();
			} catch (SQLException e1) {
				// logging
			}
		}
	}

	public static News getOneNews(ResultSet result) throws SQLException {
		int id = result.getInt(1);
		String title = result.getString(2);
		Date newsDate = result.getDate(3);
		String brief = result.getString(4);
		String content = result.getString(5);
		return new News(id, title, newsDate.toString(), brief, content);
	}
	
	
	public static void setOneNews(PreparedStatement statement, News news) throws SQLException {
		java.sql.Date date = java.sql.Date.valueOf(news.getDate());
		statement.setString(1, news.getTitle());
		statement.setDate(2, date);
		statement.setString(3, news.getBrief());
		statement.setString(4, news.getContent());
	}

}
