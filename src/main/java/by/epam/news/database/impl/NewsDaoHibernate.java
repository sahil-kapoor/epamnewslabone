package by.epam.news.database.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.catalina.authenticator.SavedRequest;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import by.epam.news.database.DataBaseException;
import by.epam.news.database.NewsDao;
import by.epam.news.model.News;
import by.epam.news.util.HibernateUtil;

public class NewsDaoHibernate implements NewsDao {

	@Override
	public int addNews(News news) throws DataBaseException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(news);
		session.getTransaction().commit();
		return news.getId();
	}

	@Override
	public List<News> loadAllNews() throws DataBaseException {
		List<News> list = new ArrayList<News>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(News.class);
		criteria.addOrder(Order.desc("date"));
		list = criteria.list();
		session.getTransaction().commit();
		return list;
	}

	@Override
	public News loadNews(int id) throws DataBaseException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		News news = new News();
		session.beginTransaction();
		news = (News) session.get(News.class, id);
		session.getTransaction().commit();
		return news;
	}

	@Override
	public void editNews(News news) throws DataBaseException {
		addNews(news);
	}

	@Override
	public void deleteNews(Integer[] ids) throws DataBaseException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("deleteNews").setParameterList("deleteIds", ids);
		query.executeUpdate();
		session.getTransaction().commit();
	}

}
