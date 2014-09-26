package by.epam.news.service.impl;

import java.util.List;

import by.epam.news.database.DataBaseException;
import by.epam.news.database.NewsDao;
import by.epam.news.model.News;
import by.epam.news.service.NewsService;
import by.epam.news.service.ServiceException;

public class NewsServiceImpl implements NewsService {

	private NewsDao dao;

	public void setDao(NewsDao dao) {
		this.dao = dao;
	}

	@Override
	public int saveNews(News news) throws ServiceException {

		int id = news.getId();
		try {
			if (id == 0) {
				id = dao.addNews(news);
			} else {
				dao.editNews(news);
			}
		} catch (DataBaseException e) {
			throw new ServiceException("Can't save news", e);
		}
		return id;
	}

	@Override
	public List<News> newsList() throws ServiceException {
		try {
			return dao.loadAllNews();
		} catch (DataBaseException e) {
			throw new ServiceException("Can't load  news list", e);
		}
	}

	@Override
	public News loadNews(int id) throws ServiceException {
		try {
			return dao.loadNews(id);
		} catch (DataBaseException e) {
			throw new ServiceException("Can't load single news", e);
		}
	}

	@Override
	public void deleteNews(int[] ids) throws ServiceException {
		try {
			dao.deleteNews(ids);
		} catch (DataBaseException e) {
			throw new ServiceException("Can't delete news", e);
		}
	}

}
