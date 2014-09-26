package by.epam.news.service;

import java.util.List;

import by.epam.news.model.News;


public interface NewsService {

	public int saveNews(News news) throws ServiceException;
	
	public List<News> newsList() throws ServiceException;
	
	public News loadNews(int id) throws ServiceException;
	
	public void deleteNews(int[] ids) throws ServiceException;
	
}
