package by.epam.news.database.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import by.epam.news.database.DataBaseException;
import by.epam.news.database.NewsDao;
import by.epam.news.database.SqlScriptMaker;
import by.epam.news.model.News;

public class NewsDaoJpa implements NewsDao{

	public EntityManager em = Persistence.createEntityManagerFactory("News").createEntityManager();
	
	@Override
	public int addNews(News news) throws DataBaseException {
		em.getTransaction().begin();
		news = em.merge(news);
		em.getTransaction().commit();
		return news.getId();
	}

	@Override
	public List<News> loadAllNews() throws DataBaseException {
		List<News> list = new ArrayList<News>();
		em.getTransaction().begin();
		Query query = em.createQuery(SqlScriptMaker.getLoadAll(), News.class);
		list = query.getResultList();
		em.getTransaction().commit();
		return list;
	}

	@Override
	public News loadNews(int id) throws DataBaseException {
		News news = new News();
		em.getTransaction().begin();
		news = em.find(News.class, id);
		em.getTransaction().commit();
		return news;
	}

	@Override
	public void editNews(News news) throws DataBaseException {
		em.getTransaction().begin();
		em.merge(news);
		em.getTransaction().commit();
	}

	@Override
	public void deleteNews(Integer[] ids) throws DataBaseException {
		em.getTransaction().begin();
		em.createQuery(SqlScriptMaker.getDelete(ids)).executeUpdate();
		em.getTransaction().commit();
	}

}
