package by.epam.news.model;

import java.io.Serializable;


public class News implements Serializable {
	

	private static final long serialVersionUID = 6566437990774525688L;
	
	private int id;
	private String title;
	private String date;
	private String brief;
	private String content;
	
	public News(int id, String title, String date, String brief, String content) {
		super();
		this.id = id;
		this.title = title;
		this.date = date;
		this.brief = brief;
		this.content = content;
	}

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
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
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

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", date=" + date
				+ ", brief=" + brief + ", content=" + content + "]";
	}
	 
}
