package by.epam.news.service;

import java.util.List;

import by.epam.news.model.News;
import by.epam.news.presentation.form.NewsForm;

public interface NewsService {

	public int saveNews (News form);
	
	public List<News> newsList();
}
