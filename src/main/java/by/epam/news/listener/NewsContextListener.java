package by.epam.news.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.beanutils.ConvertUtils;

import by.epam.news.util.DateConvert;

public class NewsContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ConvertUtils.register(new DateConvert(), java.util.Date.class);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}

}