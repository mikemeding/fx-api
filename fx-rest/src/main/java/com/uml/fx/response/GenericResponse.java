/*
 * Copyright (c) 2013 OutSmart Power Systems -- All Rights Reserved.
 */
package com.uml.fx.response;

import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Simple JSON response for the API
 *
 * @author uwe
 */
@XmlRootElement
public class GenericResponse extends AbstractResponse {

	public final static GenericResponse OK = new GenericResponse("OK", 0);
	private int errorCode;
	private String errorMsg;
	private Map<String, Object> fieldErrors;
	private Object data;

	public GenericResponse() {
		this("OK");
	}

	public GenericResponse(String status) {
		this(status, 0);
	}

	public GenericResponse(String status, int errorCode) {
		this.status = status;
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public Map<String, Object> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(Map<String, Object> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}