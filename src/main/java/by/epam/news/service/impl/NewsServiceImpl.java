package by.epam.news.service.impl;

import java.util.List;

import by.epam.news.database.NewsDao;
import by.epam.news.model.News;
import by.epam.news.service.NewsService;

public class NewsServiceImpl implements NewsService{

	private NewsDao dao;
	
	public void setDao(NewsDao dao){
		this.dao = dao;
	}
	
	@Override
	public int saveNews(News news) {
				
		int id = news.getId();
		if (id == 0){
			id = dao.addNews(news);
		} else {
			dao.editNews(news);
		}
		return id;
	}

	@Override
	public List<News> newsList() {
			
		return dao.loadAllNews();
	}

	@Override
	public News loadNews(int id) {
			
		return dao.loadNews(id);
	}

	@Override
	public void deleteNews(int[] ids) {
		dao.deleteNews(ids);
	}

}
