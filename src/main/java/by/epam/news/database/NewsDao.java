package by.epam.news.database;


import java.util.List;

import by.epam.news.model.News;

public interface NewsDao {
	
	int addNews(News news);
	
	List<News> loadAllNews();
	
	News loadNews(int id);
	
	void editNews(News news);
	
	void deleteNews(List<Integer> idList);
	
}
