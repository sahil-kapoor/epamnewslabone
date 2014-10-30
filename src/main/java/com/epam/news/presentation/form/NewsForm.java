package com.epam.news.presentation.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import com.epam.news.model.News;

@SuppressWarnings("serial")
public class NewsForm extends ValidatorForm{
	
	private News newsMessage;
	private List<News> newsList;
	private Integer[] newsToDelete;  
	
	public NewsForm(){
		newsMessage = new News();
		newsList = new ArrayList<News>();
	}

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

	public Integer[] getNewsToDelete() {
		return newsToDelete;
	}

	public void setNewsToDelete(Integer[] newsToDelete) {
		this.newsToDelete = newsToDelete;
	}

}
