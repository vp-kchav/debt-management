package com.mcm.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 
 * @author kchav
 */
@Slf4j
public enum DateTimeUtils {

	INSTANCE;
	/**
	 * This method is to generateDate base on the string and format provided
	 * @param date
	 * @param format
	 * @return
	 */
	public Date getDateFromString(String date, String format) {
			try {
				DateFormat dateFormat = new SimpleDateFormat(format,Locale.US);
				return dateFormat.parse(date);
			} catch (ParseException e) {
				log.error("Error while passing string to date, stringDate {}", date);
			}
			return null;
	}

	/**
	 *
	 * @param date
	 * @param format
	 * @return
	 */
	public String getStringDateFromDate(Date date, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format,Locale.US);
		return dateFormat.format(date);
	}

	/**
	 *
	 * @param format
	 * @return
	 */
	public String getDateAsString(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}

}
