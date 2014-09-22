package by.epam.news.presentation.form;

import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

import by.epam.news.model.News;

public class NewsForm extends ActionForm{
	
	
	public NewsForm(){
		newsMessage = new News(0, null, null, null, null);
	}
	
	private News newsMessage;
	private List<News> newsList;
	
	public News getNewsMessage() {
		return newsMessage;
	}
	
	public void setNewsMessage(News newsMessage) {
		this.newsMessage = newsMessage;
	}
	
	public List<News> getNewsList() {
		return newsList;
	}
	
	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	
	
	
}
