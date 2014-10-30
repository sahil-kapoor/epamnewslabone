package com.epam.news.database.impl;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.epam.news.database.dao.NewsDaoI;
import com.epam.news.exception.DataBaseException;
import com.epam.news.model.News;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:SpringBeansTest.xml" })
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
@TransactionConfiguration(defaultRollback = true)
@DatabaseSetup("classpath:testData.xml")
public class NewsDaoTest {
	
	
	@Autowired
	@Qualifier("NewsDaoHibernate")
	private NewsDaoI dao;
	
	@SuppressWarnings("deprecation")
	@Test
	public void addNewsAndLoadTest() throws DataBaseException{
		News newsToAdd = new News(0,"random title", new Date(), "random brief", "random content");
		int id = dao.addNews(newsToAdd);
		News loadedNews = dao.loadNews(id);
		newsToAdd.setId(id);
		Assert.assertEquals(newsToAdd.getId(), loadedNews.getId());
		Assert.assertEquals(newsToAdd.getTitle(), loadedNews.getTitle());
		Assert.assertEquals(newsToAdd.getContent(), loadedNews.getContent());
		Assert.assertEquals(newsToAdd.getBrief(), loadedNews.getBrief());
		Assert.assertEquals(newsToAdd.getDate().getDay(), loadedNews.getDate().getDay());
		Assert.assertEquals(newsToAdd.getDate().getMonth(), loadedNews.getDate().getMonth());
		Assert.assertEquals(newsToAdd.getDate().getYear(), loadedNews.getDate().getYear());
	}
	
	@Test
	public void loadAll() throws DataBaseException{
		Assert.assertEquals(3, dao.loadAllNews().size());
	}
	
	@Test 
	public void deleteNews() throws DataBaseException{
		Integer idToDelete[] = {1, 2};	
		dao.deleteNews(idToDelete);
		Assert.assertEquals(1, dao.loadAllNews().size());
	}
	
	@Test
	public void updateNews() throws DataBaseException{
		News news = dao.loadNews(1);
		news.setTitle("haha");
		dao.editNews(news);
		news = dao.loadNews(1);
		Assert.assertEquals(news.getId(), 1);
		Assert.assertEquals(news.getTitle(), "haha");
		Assert.assertEquals(news.getContent(), "CONTENT TEST 1");
		Assert.assertEquals(news.getBrief(), "test brief 1");
	}
	
	@Test 
	public void loadOne() throws DataBaseException{
		News news = dao.loadNews(1);
		Assert.assertEquals(news.getId(), 1);
		Assert.assertEquals(news.getTitle(), "test title 1");
		Assert.assertEquals(news.getContent(), "CONTENT TEST 1");
		Assert.assertEquals(news.getBrief(), "test brief 1");
	}
	


	
	
}
