package com.epam.news.service.impl;

import java.util.List;

import com.epam.news.database.dao.NewsDaoI;
import com.epam.news.exception.DataBaseException;
import com.epam.news.exception.ServiceException;
import com.epam.news.model.News;
import com.epam.news.service.NewsService;

/**
 * Implementation for NewsService.
 * 
 * @author Alexander_Demeshko
 *
 */
public class NewsServiceImpl implements NewsService {

	private NewsDaoI dao;

	public void setDao(NewsDaoI dao) {
		this.dao = dao;
	}

	/**
	 * Save news.
	 * Create new news or update existent news.
	 * If news id != 0 update news, else create new news in database.
	 */
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
			throw new ServiceException("Can't save news: " + e.getMessage(), e);
		}
		return id;
	}

	@Override
	public List<News> newsList() throws ServiceException {
		try {
			return dao.loadAllNews();
		} catch (DataBaseException e) {
			throw new ServiceException("Can't load  news list: " + e.getMessage(), e);
		}
	}

	@Override
	public News loadNews(int id) throws ServiceException {
		try {
			return dao.loadNews(id);
		} catch (DataBaseException e) {
			throw new ServiceException("Can't load single news: " + e.getMessage(), e);
		}
	}

	@Override
	public void deleteNews(Integer[] ids) throws ServiceException {
		try {
			dao.deleteNews(ids);
		} catch (DataBaseException e) {
			throw new ServiceException("Can't delete news :" + e.getMessage(), e);
		}
	}

}
