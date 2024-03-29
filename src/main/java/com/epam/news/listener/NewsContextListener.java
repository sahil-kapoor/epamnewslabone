package com.epam.news.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.beanutils.ConvertUtils;

import com.epam.news.util.DateConvert;

/**
 * News Context Listener.
 * Implements ServletContextListener
 * Register DataConverter in ConvertUtils
 * @author Alexander_Demeshko
 *
 */
public final class NewsContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ConvertUtils.register(new DateConvert(), java.util.Date.class);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}

}