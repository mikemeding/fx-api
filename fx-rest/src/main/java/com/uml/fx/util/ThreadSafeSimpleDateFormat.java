/*
 * Copyright (c) 2010 Meding Software Technik -- All Rights Reserved.
 */
package com.uml.fx.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

/**
 * Dooh! SimpleDateFormat is not thread-safe, this implements a wrapper for
 * this. This should probably be in the utilities somewhere
 *
 * @author uwe
 */
public class ThreadSafeSimpleDateFormat {

	private final DateFormat dateFormat;

	public ThreadSafeSimpleDateFormat(String format) {
		this.dateFormat = new SimpleDateFormat(format);
	}

	public ThreadSafeSimpleDateFormat(String format, Locale locale) {
		this.dateFormat = new SimpleDateFormat(format, locale);
	}

	public synchronized String format(Date date) {
		return dateFormat.format(date);
	}

	public synchronized String format(long date) {
		return dateFormat.format(new Date(date));
	}

	public synchronized Date parse(String string) throws ParseException {
		return dateFormat.parse(string);
	}

	public void setTimeZone(TimeZone tz) {
		dateFormat.setTimeZone(tz);
	}
}
