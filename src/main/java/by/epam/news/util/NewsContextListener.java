package by.epam.news.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.beanutils.ConvertUtils;

public class NewsContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ConvertUtils.register(new DateConvert(), java.util.Date.class);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}

}