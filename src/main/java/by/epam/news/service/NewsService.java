package by.epam.news.service;

import java.util.List;

import by.epam.news.model.News;


public interface NewsService {

	public int saveNews(News news);
	
	public List<News> newsList();
	
	public News loadNews(int id);
	
}
