package by.epam.news.service;

import java.util.List;

import by.epam.news.exception.ServiceException;
import by.epam.news.model.News;

/**
 * News service
 * Using to connect Datavase with actions.
 * @author Alexander_Demeshko
 *
 */

public interface NewsService {

	/**
	 * Save news.
	 * 
	 * Update exist or create new if news id = 0.
	 * @param news
	 * @return
	 * @throws ServiceException
	 */
	public int saveNews(News news) throws ServiceException;
	
	/**
	 * Load All News.
	 * @return
	 * @throws ServiceException
	 */
	public List<News> newsList() throws ServiceException;
	
	/**
	 * Load news by id.
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public News loadNews(int id) throws ServiceException;
	
	/**
	 * Delete news.
	 * 
	 * Delete news from db that id in ids array.
	 * @param ids
	 * @throws ServiceException
	 */
	public void deleteNews(Integer[] ids) throws ServiceException;
	
}
