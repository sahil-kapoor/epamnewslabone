package com.epam.news.util;

import org.apache.log4j.Logger;

/**
 * System logger.
 * Share apach log4j logger to low layer class.
 * @author Alexander_Demeshko
 *
 */
public final class SystemLogger {
	
	private SystemLogger(){
		
	}
	
	private static final Logger Log = Logger.getLogger(SystemLogger.class);
	
	public static Logger getLogger(){
		return Log;
	}
	
}
