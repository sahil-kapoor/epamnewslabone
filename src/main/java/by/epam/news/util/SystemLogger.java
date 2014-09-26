package by.epam.news.util;

import org.apache.log4j.Logger;


public final class SystemLogger {
	
	private SystemLogger(){};
	
	private static final Logger Log = Logger.getLogger(SystemLogger.class);
	
	public static Logger getLogger(){
		return Log;
	}
	
}
