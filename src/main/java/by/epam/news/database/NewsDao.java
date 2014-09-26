package by.epam.news.database;


import java.util.List;

import by.epam.news.model.News;

public interface NewsDao {
	
	int addNews(News news) throws DataBaseException;
	
	List<News> loadAllNews() throws DataBaseException;
	
	News loadNews(int id) throws DataBaseException;
	
	void editNews(News news) throws DataBaseException;
	
	void deleteNews(int[] ids) throws DataBaseException;
	
}
