package by.epam.news.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.commons.beanutils.ConvertUtils;

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
		java.util.Date newsDate = ((Date) ConvertUtils.convert(
				result.getDate(3), java.util.Date.class));
		String brief = result.getString(4);
		String content = result.getString(5);
		return new News(id, title, newsDate, brief, content);
	}

	public static void setOneNews(PreparedStatement statement, News news)
			throws SQLException {
		statement.setString(1, news.getTitle());
		statement.setDate(2, (java.sql.Date) ConvertUtils.convert(
				news.getDate(), java.sql.Date.class));
		statement.setString(3, news.getBrief());
		statement.setString(4, news.getContent());
	}

}
