package by.epam.news.database.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.news.database.ConnectionPool;
import by.epam.news.database.DaoHelp;
import by.epam.news.database.NewsDao;
import by.epam.news.database.SqlScriptMaker;
import by.epam.news.model.News;

public class NewsDaoImpl implements NewsDao {

	private ConnectionPool pool;

	public NewsDaoImpl(ConnectionPool pool) {
		this.pool = pool;
	}

	@Override
	public int addNews(News news) {
		Connection connection = null;
		try {
			connection = pool.getConnection();
			String generatedColumns[] = { "ID" };
			PreparedStatement statement = connection
					.prepareStatement(SqlScriptMaker.getAdd(),  generatedColumns);
			DaoHelp.setOneNews(statement, news);
			statement.executeUpdate();
			ResultSet keys = statement.getGeneratedKeys();  	
			keys.next();  
			int key = keys.getInt(1);
			keys.close();  
			DaoHelp.closeStatementAndConnection(statement, connection, pool);
			return key; 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<News> loadAllNews() {
		Connection connection = null;
		List<News> newsList = new ArrayList<News>();
		try {
			connection = pool.getConnection();
			Statement statement = connection.createStatement();	
			ResultSet result = statement.executeQuery(SqlScriptMaker.getLoadAll());
			while (result.next()) {
				newsList.add(DaoHelp.getOneNews(result));
			}
			DaoHelp.closeStatementAndConnection(statement, connection, pool);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newsList;
	}

	@Override
	public News loadNews(int id) {
		Connection connection = null;
		try {
			connection = pool.getConnection();
			Statement statement = connection.createStatement();		
			ResultSet result = statement.executeQuery(SqlScriptMaker.getLoadOne(id));
			News news = null;
			while (result.next()) {				
				news = DaoHelp.getOneNews(result);
			}
			DaoHelp.closeStatementAndConnection(statement, connection, pool);
			return news;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void editNews(News news) {
		Connection connection = null;
		try {
			connection = pool.getConnection();
			PreparedStatement statement = connection
					.prepareStatement(SqlScriptMaker.getUpdate());		
			DaoHelp.setOneNews(statement, news);
			statement.setInt(5, news.getId());
			statement.executeUpdate();
			DaoHelp.closeStatementAndConnection(statement, connection, pool);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteNews(int[] ids) {
		Connection connection = null;
		try {
			connection = pool.getConnection();
			Statement statement = connection.createStatement();	
			statement.execute(SqlScriptMaker.getDelete(ids));		
			DaoHelp.closeStatementAndConnection(statement, connection, pool);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Test(){
		Connection connection = null;
		try {
			connection = pool.getConnection();
			Statement statement = connection.createStatement();	
			statement.execute(	"CREATE SEQUENCE news_id_seq START WITH 1 INCREMENT BY 1 NOMAXVALUE");
			//statement.execute("CREATE OR REPLACE TRIGGER news_trigger BEFORE INSERT ON News REFERENCING NEW AS NEW FOR EACH ROW BEGIN SELECT news_id_seq.nextval INTO :NEW.ID FROM dual; END;");

			DaoHelp.closeStatementAndConnection(statement, connection, pool);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
