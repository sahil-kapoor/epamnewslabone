package by.epam.news.presentation.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class NewsForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	
	private int id;

	private String title;
	private Date date;
	private String brief;
	private String content;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getBrief() {
		return brief;
	}
	
	public void setBrief(String brief) {
		this.brief = brief;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
}
