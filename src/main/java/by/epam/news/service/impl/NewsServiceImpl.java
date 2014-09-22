package by.epam.news.service.impl;

import java.util.List;

import by.epam.news.database.NewsDao;
import by.epam.news.model.News;
import by.epam.news.service.NewsService;
import by.epam.news.util.SpringApplicationContext;

public class NewsServiceImpl implements NewsService{

	@Override
	public int saveNews(News news) {
		NewsDao dao = (NewsDao) SpringApplicationContext.getBean("NewsDao");
		int id = dao.addNews(news);
		System.out.println("id="+id);
		return id;
	}

	@Override
	public List<News> newsList() {
		NewsDao dao = (NewsDao) SpringApplicationContext.getBean("NewsDao");		
		return dao.loadAllNews();
	}

	@Override
	public News loadNews(int id) {
		NewsDao dao = (NewsDao) SpringApplicationContext.getBean("NewsDao");		
		return dao.loadNews(id);
	}

}
