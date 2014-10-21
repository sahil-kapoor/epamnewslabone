package by.epam.news.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import by.epam.news.util.SystemLogger;

/**
 * 
 * Hibernate util
 * 
 * Class build session factory and share it with HibernateDao.
 * Throws  ExceptionInInitializerError when session factory cannot be builded.
 *
 */
public class HibernateUtil {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
	 
	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();
		    configuration.configure();
		    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
		            configuration.getProperties()).build();
		    return configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			SystemLogger.getLogger().error(ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
 
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
 
	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}
}
