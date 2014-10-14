package by.epam.news.database.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import by.epam.news.database.DataBaseException;
import by.epam.news.database.NewsDao;
import by.epam.news.database.SqlScriptMaker;
import by.epam.news.model.News;

public class NewsDaoJpa implements NewsDao {

	private final static String MODEL_NAME = "News";
	public EntityManager em = Persistence.createEntityManagerFactory(MODEL_NAME)
			.createEntityManager();

	@Override
	public int addNews(News news) throws DataBaseException {
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			news = em.merge(news);
			transaction.commit();
		} catch (PersistenceException e) {
			if (null != transaction) {
				transaction.rollback();
			}
			throw new DataBaseException("Cant save news: "
					+ e.getMessage(), e);
		} finally {
			em.clear();
		}
		return news.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> loadAllNews() throws DataBaseException {
		List<News> list = new ArrayList<News>();
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			Query query = em.createNativeQuery(SqlScriptMaker.getLoadAll(),
					News.class);
			list = query.getResultList();
			transaction.commit();
		} catch (PersistenceException e) {
			throw new DataBaseException("Cant load news list: "
					+ e.getMessage(), e);
		}
		return list;
	}

	@Override
	public News loadNews(int id) throws DataBaseException {
		News news = new News();
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			news = em.find(News.class, id);
			transaction.commit();
		} catch (PersistenceException e) {			
			throw new DataBaseException("Cant load single news: "
					+ e.getMessage(), e);
		} 
		return news;
	}

	@Override
	public void editNews(News news) throws DataBaseException {
		addNews(news);
	}

	@Override
	public void deleteNews(Integer[] ids) throws DataBaseException {
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
				em.createQuery(SqlScriptMaker.getDelete(ids)).executeUpdate();
			transaction.commit();
		} catch (PersistenceException e) {
			if (null != transaction) {
				transaction.rollback();
			}
			throw new DataBaseException("Cant delete news : "
					+ e.getMessage(), e);
		} 
	}

}
