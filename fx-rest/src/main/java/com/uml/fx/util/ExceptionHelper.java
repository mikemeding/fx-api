/*
 * Copyright (c) 2009 Meding Software Technik. All Rights Reserved.
 * $Id: ExceptionHelper.java,v 1.1 2009/03/08 19:16:40 uwe Exp $
 */
package com.uml.fx.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Help handling execptions.
 *
 * @author <a href="mailto:umeding@outsmartinc.com">Uwe B. Meding</a>
 */
public class ExceptionHelper {

	/**
	 *  Get a throwables cause
	 *  @param t is the throwable
	 *  @return a string
	 */
	public static String getThrowableCause(Throwable t) {
		StringBuilder sb = new StringBuilder();
		String delim = "";
		do {
			sb.append(delim);
			sb.append(t.getMessage());
			delim = ", ";
			t = t.getCause();
		} while (t != null);
		return (sb.toString());
	}

	/**
	 *  Get an exceptions cause
	 *  @param e is the execpeiton
	 *  @return a string
	 */
	public static String getCause(Exception e) {
		Throwable t = e.getCause();
		if (t == null) {
			t = e;
		}
		return (getThrowableCause(t));
	}

	/**
	 * Get a stack trace for logging.
	 * @param t is the exception
	 * @return
	 */
	public static String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return (sw.toString());
	}

	/**
	 * Get a stack trace of where we currently are.
	 * @return the stack trace
	 */
	public static String getStackTrace() {
		return (getStackTrace(new Exception("stacktrace")));
	}
}
