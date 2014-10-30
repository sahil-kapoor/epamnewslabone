package com.epam.news.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.news.database.util.ConnectionPool;
import com.epam.news.database.util.JdbcNewsUtil;
import com.epam.news.database.util.SqlScriptMaker;
import com.epam.news.exception.DataBaseException;
import com.epam.news.model.News;

/**
 * Class implements NewsDao @see by.epam.news.database.NewsDao. Based on JDBC. 
 * 
 * Goal of class is work with Oracle data base.
 * 
 * @author Alexander_Demeshko
 *
 */
public final class NewsDaoJdbc implements NewsDaoI {
 
	/**
	 * Connection pool
	 * 
	 * Pool used to getting connections to execute operations with Database.
	 * @see by.epam.news.database.ConnectionPoolTest
	 */
	private ConnectionPool pool;

	/**
	 * Set pool. Spring injection used.
	 * @param pool
	 */
	public NewsDaoJdbc(ConnectionPool pool) {
		this.pool = pool;
	}

	/**
	 * Add News
	 * 
	 * Insert new news to database.
	 * 
	 * @param news
	 * @throws DataBaseException 
	 * Throw DataBaseException when SQLException is result of executing insert. 
	 */
	@Override
	public int addNews(News news) throws DataBaseException{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {		
			connection = pool.getConnection();
			String RETURN_ID = "ID";
			String generatedColumns[] = { RETURN_ID };
			statement = connection
					.prepareStatement(SqlScriptMaker.getAdd(),  generatedColumns);
			JdbcNewsUtil.setOneNews(statement, news);
			statement.executeUpdate();
			result = statement.getGeneratedKeys();  	
			result.next();  
			int key = result.getInt(1);			 			
			return key; 
		} catch (SQLException e) {
			throw new DataBaseException("Can't execute sql when add news", e);
		} finally {
			JdbcNewsUtil.closeResultSet(result);		 
			JdbcNewsUtil.closeStatementAndConnection(statement, connection, pool);
		}
	}

	/**
	 * Load all News
	 * 
	 * Load All news from database.
	 * 
	 * @throws DataBaseException 
	 * 
	 * Throw DataBaseException when SQLException is result of executing query. 
	 */
	@Override
	public List<News> loadAllNews() throws DataBaseException{
		Connection connection = null;
		List<News> newsList = new ArrayList<News>();
		Statement statement = null;
		ResultSet result = null;
		try {
			connection = pool.getConnection();
			statement = connection.createStatement();	
			result = statement.executeQuery(SqlScriptMaker.getLoadAll());
			while (result.next()) {
				newsList.add(JdbcNewsUtil.getOneNews(result));
			}
		} catch (SQLException e) {
			throw new DataBaseException("Can't execute sql when load all news", e);
		} finally {
			JdbcNewsUtil.closeResultSet(result);		
			JdbcNewsUtil.closeStatementAndConnection(statement, connection, pool);
		}
		return newsList;
	}

	/**
	 * Load single News
	 *
	 * @param int
	 * @throws DataBaseException 
	 * 
	 * Load news by id from database.
	 * Throw DataBaseException when SQLException is result of executing query. 
	 */
	@Override
	public News loadNews(int id) throws DataBaseException{
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			connection = pool.getConnection();
			statement = connection.createStatement();		
			result = statement.executeQuery(SqlScriptMaker.getLoadOne(id));
			News news = null;
			while (result.next()) {				
				news = JdbcNewsUtil.getOneNews(result);
			}
			result.close();
			return news;
		} catch (SQLException e) {
			throw new DataBaseException("Can't execute sql when load all news", e);
		} finally {
			JdbcNewsUtil.closeResultSet(result);		
			JdbcNewsUtil.closeStatementAndConnection(statement, connection, pool);
		}
	}

	/**
	 * Edit news
	 *
	 * @param news
	 * @throws DataBaseException 
	 * 
	 * Edit news.
	 * Throw DataBaseException when SQLException is result of executing update. 
	 */
	@Override
	public void editNews(News news) throws DataBaseException{
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = pool.getConnection();
			statement = connection
					.prepareStatement(SqlScriptMaker.getUpdate());		
			JdbcNewsUtil.setOneNews(statement, news);
			statement.setInt(5, news.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DataBaseException("Can't execute sql when edit news", e);
		} finally {		
			JdbcNewsUtil.closeStatementAndConnection(statement, connection, pool);
		}
	}

	/**
	 * Delete News
	 *
	 * @param Integer[]
	 * @throws DataBaseException 
	 * 
	 * Delete news using int array of news's id.
	 * Throw DataBaseException when SQLException is result of executing query. 
	 */
	
	@Override
	public void deleteNews(Integer[] ids) throws DataBaseException{
		Connection connection = null;
		Statement statement = null;
		try {
			connection = pool.getConnection();
			statement = connection.createStatement();	
			statement.execute(SqlScriptMaker.getDelete(ids));		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException("Can't execute sql when delete news", e);
		} finally {
			JdbcNewsUtil.closeStatementAndConnection(statement, connection, pool);
		}
	}
	

}
