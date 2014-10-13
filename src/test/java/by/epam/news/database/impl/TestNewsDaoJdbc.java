package by.epam.news.database.impl;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import by.epam.news.database.DataBaseException;
import by.epam.news.database.NewsDao;
import by.epam.news.model.News;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:SpringBeansTest.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    DbUnitTestExecutionListener.class })

public class TestNewsDaoJdbc {
	
	private NewsDao dao;

	@Autowired
	public void setDao(NewsDao dao) {
		this.dao = dao;
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@Rollback(true)  
	@DatabaseSetup("classpath:testData.xml")
	public void addNewsAndLoadTest() throws DataBaseException{
		News newsToAdd = new News(0,"random title", new Date(), "random brief", "random content");
		int id = dao.addNews(newsToAdd);
		News loadedNews = dao.loadNews(id);
		newsToAdd.setId(id);
		System.out.println(id);
		Assert.assertEquals(newsToAdd.getId(), loadedNews.getId());
		Assert.assertEquals(newsToAdd.getTitle(), loadedNews.getTitle());
		Assert.assertEquals(newsToAdd.getContent(), loadedNews.getContent());
		Assert.assertEquals(newsToAdd.getBrief(), loadedNews.getBrief());
		Assert.assertEquals(newsToAdd.getDate().getDay(), loadedNews.getDate().getDay());
		Assert.assertEquals(newsToAdd.getDate().getMonth(), loadedNews.getDate().getMonth());
		Assert.assertEquals(newsToAdd.getDate().getYear(), loadedNews.getDate().getYear());
	}
	
	
}
