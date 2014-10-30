package com.epam.news.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="DEMESHKO.NEWS")
public class News implements Serializable {
	

	private static final long serialVersionUID = 6566437990774525688L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_generator")
	@SequenceGenerator(name = "id_generator", sequenceName = "NEWS_SEQ")
	@Column(name = "ID")
	private int id;
	@Column(name = "TITLE")
	private String title;
	@Column(name="NEWS_DATE")
	private Date date;
	@Column(name = "BRIEF")
	private String brief;
	@Column(name = "CONTENT")
	private String content;
	
	public News(int id, String title, Date date, String brief, String content) {
		this.id = id;
		this.title = title;
		this.date = date;
		this.brief = brief;
		this.content = content;
	}
	
	public News(){
		this.id = 0;
		this.title = "";
		this.date = new Date();
		this.brief = "";
		this.content = "";
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

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", date=" + date
				+ ", brief=" + brief + ", content=" + content + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brief == null) ? 0 : brief.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		if (brief == null) {
			if (other.brief != null)
				return false;
		} else if (!brief.equals(other.brief))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	 
}
