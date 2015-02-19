/*
 * Copyright (c) 2013 OutSmart Power Systems, Inc. -- All Rights Reserved.
 */
package com.uml.fx.response;

import java.util.Map;
import java.util.TreeMap;
import javax.ejb.EJBException;
import javax.ejb.TransactionRolledbackLocalException;
import javax.xml.bind.annotation.XmlRootElement;
import os.util.ExceptionHelper;

/**
 * A message response
 * <p>
 * @author uwe
 */
@XmlRootElement
public class MessageResponse extends AbstractResponse {

	public static final int OK = 0;
	public static final int SUCCESS = 1;
	public static final int INFO = 2;
	public static final int ERROR = 3;
	private int errorCode;
	private String errorMsg;
	private Map<String, Object> fieldErrors;

	public MessageResponse() {
	}

	/**
	 * Construct a message response.
	 * <p>
	 * @param errorCode is the error code
	 * @param errorMsg  the error message
	 */
	public MessageResponse(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		switch (errorCode) {
			case ERROR:
				super.status = FAIL_STATUS;
				break;
		}
	}

	/**
	 * Create on OK message.
	 * <p>
	 * @param message the message text
	 * @return the response message
	 */
	public static MessageResponse ok(String message) {
		return new MessageResponse(OK, message);
	}

	/**
	 * Create an info message.
	 * <p>
	 * @param message the message text
	 * @return the response message
	 */
	public static MessageResponse info(String message) {
		return new MessageResponse(INFO, message);
	}

	/**
	 * Create a success message.
	 * <p>
	 * @param message the message text
	 * @return the response message
	 */
	public static MessageResponse success(String message) {
		return new MessageResponse(SUCCESS, message);
	}

	/**
	 * Create a warning message.
	 * <p>
	 * @param message the message text
	 * @return the response message
	 */
	public static MessageResponse warn(String message) {
		return new MessageResponse(ERROR, message);
	}

	/**
	 * Create an error message.
	 * <p>
	 * @param message the message text
	 * @return the response message
	 */
	public static MessageResponse error(String message) {
		return error(null, message);
	}

	/**
	 * Create an error message.
	 * <p>
	 * @param prefix  some prefix for the message
	 * @param message the message itself
	 * @return the response message
	 */
	public static MessageResponse error(String prefix, String message) {
		if (prefix != null) {
			message = String.format("%s%n%s", prefix, message);
		}
		return new MessageResponse(ERROR, message);
	}

	/**
	 * Create an error message with an exception.
	 * <p>
	 * @param e is the exception
	 * @return the response message
	 */
	public static MessageResponse error(Exception e) {
		return error(null, e);
	}

	/**
	 * Create a message response with an exception.
	 * <p>
	 * @param text some additional text
	 * @param e    the exception
	 * @return the response message
	 */
	public static MessageResponse error(String text, Exception e) {
		try {
			if (e instanceof EJBException) {
				e = (Exception) e.getCause();
				if (e instanceof TransactionRolledbackLocalException) {
					e = (Exception) e.getCause();
				}
				return error(text, e.getMessage());
			}
			return error(text, e.getMessage());
		} catch (Exception ie) {
			// ignore any internal exception and just return the message
			return error(text, e.getMessage());
		}
	}

	public static MessageResponse errorTrace(Exception e) {
		if (e instanceof EJBException) {
			e = (Exception) e.getCause();
		}
		return error(null, ExceptionHelper.getStackTrace(e));
	}

	/**
	 * Get the error code
	 * <p>
	 * @return the error code
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * Set the error code
	 * <p>
	 * @param errorCode is the error code
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Get the error message
	 * <p>
	 * @return the error message
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * The error message
	 * <p>
	 * @param errorMsg the error message
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * Get the field errors
	 * <p>
	 * @return the field errors
	 */
	public Map<String, Object> getFieldErrors() {
		return fieldErrors;
	}

	/**
	 * Set the field errors
	 * <p>
	 * @param fieldErrors are the field errors
	 */
	public void setFieldErrors(Map<String, Object> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	/**
	 * Add a field error
	 * <p>
	 * @param name  is the name of the field
	 * @param error is the error
	 */
	public void addFieldError(String name, Object error) {
		if (fieldErrors == null) {
			fieldErrors = new TreeMap<>();
		}
		fieldErrors.put(name, error);
	}
}
