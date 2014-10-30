package com.epam.news.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.ResourceBundle;

import org.apache.commons.beanutils.Converter;

/**
 * Data convert.
 * 
 * Convert String and java.sql.Date to java.util.Date. Allows to convert string
 * in only format MM/dd/yyyy. If conversion fails return current date.
 * 
 * @author Alexander_Demeshko
 *
 */
public final class DateConvert implements Converter {

	private static ResourceBundle bundle = ResourceBundle
			.getBundle("com.epam.news.properties.news.News");
	
	private final static String pattern = bundle.getString("news.date.format");
	

	@Override
	public Object convert(@SuppressWarnings("rawtypes") Class type, Object value) {

		if (value == null) {
			return (null);

		}

		if (value.getClass() == java.sql.Date.class) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime((java.sql.Date) value);
			return calendar.getTime();
		}

		Date dateObj = null;
		if (value.getClass() == java.lang.String.class) {
			try {
				return new SimpleDateFormat(pattern).parse((String) value);
			} catch (ParseException pe) {
				
				SystemLogger.getLogger().error(
						"invalid date detected, returned null", pe);
				return null;
			}
		}

		return dateObj;
	}

}