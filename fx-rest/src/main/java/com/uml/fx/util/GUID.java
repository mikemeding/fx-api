/*
 * Copyright (c) 2008 Meding Software Technik - All Rights Reserved.
 */
package com.uml.fx.util;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Manage a guid.
 *
 * @author <a href="mailto:umeding@outsmartinc.com">Uwe B. Meding</a>
 */
public class GUID {

	private static long last = 0L;
	private static final AtomicLong startId = new AtomicLong(System.currentTimeMillis());

	/**
	 * Create a unique guid
	 *
	 * @return the guid
	 */
	public static String createGuid() {
		long time = new Date().getTime();
		while (last == time) {
			try {
				Thread.sleep(1L);
			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
			} catch (Exception e) {
				// fuck it
			}
			time = new Date().getTime();
		}
		last = time;
		return (Long.toString(time, 36));
	}

	public static String createGuid(String prefix) {
		StringBuilder sb = new StringBuilder();
		sb.append(prefix).append("-").append(createGuid());
		return (sb.toString());
	}

	public static String trivialGuid(String prefix) {
		StringBuilder sb = new StringBuilder();
		if (prefix != null) {
			sb.append(prefix).append("-");
		}
		sb.append(Long.toString(startId.getAndIncrement(), 36));
		return sb.toString();
	}

	public static String trivialGuid() {
		return trivialGuid(null);
	}
}
