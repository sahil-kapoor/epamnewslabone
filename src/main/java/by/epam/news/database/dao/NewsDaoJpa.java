package by.epam.news.database.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import by.epam.news.database.SqlScriptMaker;
import by.epam.news.exception.DataBaseException;
import by.epam.news.model.News;

public class NewsDaoJpa implements NewsDaoI {


	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Add news.
	 * 
	 * Save news in database if news not exist, else update. Otherwise clear
	 * persistencecontext via entityManager.clear(); Return news id;
	 * 
	 * @param news
	 * @return int
	 */
	@Override
	@Transactional
	public int addNews(News news) throws DataBaseException {
		try {
			 entityManager.persist(news);
		} catch (PersistenceException e) {
			throw new DataBaseException("Cant save news: " + e.getMessage(), e);
		}
		return news.getId();
	}

	/**
	 * Load all news.
	 * 
	 * Load list of all news from database. Using native query from
	 * SqlScriptMaker.getLoadAll(); Return list of all news from database in
	 * date oerder;
	 * 
	 * @return List<News>
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<News> loadAllNews() throws DataBaseException {
		List<News> list = new ArrayList<News>();
		try {
			Query query = entityManager.createNativeQuery(
					SqlScriptMaker.getLoadAll(), News.class);
			list = query.getResultList();
		} catch (PersistenceException e) {
			throw new DataBaseException("Cant load news list: "
					+ e.getMessage(), e);
		}
		return list;
	}

	/**
	 * Load news.
	 * 
	 * Load news using id;
	 * 
	 * @param int
	 * @return News
	 */
	@Override
	@Transactional
	public News loadNews(int id) throws DataBaseException {
		News news = new News();
		try {
			news = entityManager.find(News.class, id);
		} catch (PersistenceException e) {
			throw new DataBaseException("Cant load single news: "
					+ e.getMessage(), e);
		}
		return news;
	}

	/**
	 * Edit news.
	 * 
	 * Update state of news in database. 
	 * 
	 * @param News
	 */
	@Override
	@Transactional
	public void editNews(News news) throws DataBaseException {
		try {
			news = entityManager.merge(news);
		} catch (PersistenceException e) {
			throw new DataBaseException("Cant save news: " + e.getMessage(), e);
		} 
	}

	/**
	 * Delete news;
	 * 
	 * Delete news from database get HQL query from
	 * SqlScriptMaker.getDelete(ids) that match with standart SQL query.
	 * 
	 * @param Integer
	 *            []
	 */
	@Override
	@Transactional
	public void deleteNews(Integer[] ids) throws DataBaseException {
		try {
			entityManager.createQuery(SqlScriptMaker.getDelete(ids))
					.executeUpdate();
		} catch (PersistenceException e) {
			throw new DataBaseException("Cant delete news : " + e.getMessage(),
					e);
		}
	}

}
