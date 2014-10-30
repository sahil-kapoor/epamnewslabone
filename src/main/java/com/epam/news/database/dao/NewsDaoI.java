package com.epam.news.database.dao;


import java.util.List;

import com.epam.news.exception.DataBaseException;
import com.epam.news.model.News;

/**
 * News Dao Interface.
 * 
 * CRUD+ operations for news.
 * @author Alexander_Demeshko
 *
 */

public interface NewsDaoI {
	
	/**
	 * Add News.
	 * 
	 * Add new news in DB. Return new id;
	 * @param news
	 * @return
	 * @throws DataBaseException
	 */
	int addNews(News news) throws DataBaseException;
	
	/**
	 * Load all news.
	 * 
	 * Load all news from db.
	 * @return
	 * @throws DataBaseException
	 */
	List<News> loadAllNews() throws DataBaseException;
	
	/**
	 * Load News.
	 * 
	 * Load news from db by Id.
	 * @param id
	 * @return
	 * @throws DataBaseException
	 */
	News loadNews(int id) throws DataBaseException;
	
	/**
	 * Edit News.
	 * @param news
	 * @throws DataBaseException
	 */
	void editNews(News news) throws DataBaseException;
	
	/**
	 * Delete news.
	 * 
	 * Delete news from db that id in ids.
	 * @param ids
	 * @throws DataBaseException
	 */
	void deleteNews(Integer[] ids) throws DataBaseException;
	
}
