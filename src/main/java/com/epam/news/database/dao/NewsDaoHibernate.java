package com.epam.news.database.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import com.epam.news.model.News;
import com.epam.news.database.util.HibernateUtil;
import com.epam.news.exception.DataBaseException;

/**
 * News Dao Hibernate.
 * 
 * Dao use Hibernate for access to DB.
 * SessionFactory gets from HibernateUtil class.
 * @author Alexander_Demeshko
 *
 */
public final class NewsDaoHibernate implements NewsDaoI {
	
	private final static String QUERY_NAME = "deleteNews";
	private final static String DELETE_IDS_PARAMETR = "deleteIds";
	private final static String ORDER = "date";
	
	

	/**
	 * Add news.
	 * 
	 * Add news in database.
	 * Return news id;
	 * 
	 * @param News
	 * @return int
	 */
	@Override
	public int addNews(News news) throws DataBaseException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(news);
			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			throw new DataBaseException("Cant save news: " + he.getMessage(),
					he);
		}
		return news.getId();
	}

	/**
	 * Load all news.
	 * 
	 * Load list of all news from database. 
	 * Return list of all news from database in
	 * date order;
	 * 
	 * @return List<News>
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<News> loadAllNews() throws DataBaseException {

		List<News> list = new ArrayList<News>();
		Transaction tx = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(News.class);
			criteria.addOrder(Order.desc(ORDER));
			list = criteria.list();
			tx.commit();
		} catch (HibernateException he) {
			throw new DataBaseException("Cant load news list: " + he.getMessage(),
					he);
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
	public News loadNews(int id) throws DataBaseException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		News news = new News();
		try {			
			tx = session.beginTransaction();
			news = (News) session.get(News.class, id);
			tx.commit();
		} catch (HibernateException he) {
			throw new DataBaseException("Cant load single news: " + he.getMessage(),
					he);
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
	public void editNews(News news) throws DataBaseException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(news);
			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			throw new DataBaseException("Cant save news: " + he.getMessage(),
					he);
		} 
	}

	/**
	 * Delete news.
	 * 
	 * Delete news form database thats id int Integer[] ids param.
	 * Using Named Query.
	 * 
	 * @param Integer[]
	 * 
	 */
	@Override
	public void deleteNews(Integer[] ids) throws DataBaseException {
	
		Transaction tx = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			tx = session.beginTransaction();
			Query query = session.getNamedQuery(QUERY_NAME).setParameterList(
					DELETE_IDS_PARAMETR, ids);
			query.executeUpdate();
			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			throw new DataBaseException("Cant delete news: " + he.getMessage(),
					he);
		}
	}

}
