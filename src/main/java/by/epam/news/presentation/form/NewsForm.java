package by.epam.news.presentation.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import by.epam.news.model.News;

@SuppressWarnings("serial")
public class NewsForm extends ActionForm{
	
	private News newsMessage;
	private List<News> newsList;
	private int[] newsToDelete;  
	
	public NewsForm(){
		newsMessage = new News(0, null, null, null, null);
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

	public int[] getNewsToDelete() {
		return newsToDelete;
	}

	public void setNewsToDelete(int[] newsToDelete) {
		this.newsToDelete = newsToDelete;
	}

}
