/*
 * Copyright (c) 2013 OutSmart Power Systems, Inc. -- All Rights Reserved.
 */
package com.uml.fx.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * A generic data response
 * @author uwe
 * @param <T> single response type
 */
@XmlRootElement
public class SingleResponse<T> extends AbstractResponse {

	private T data;

	public SingleResponse() {
	}

	public SingleResponse(String status) {
		super(status);
	}

	public SingleResponse(String status, float version) {
		super(status, version);
	}

	public SingleResponse(T data, String status, float version) {
		super(status, version);
		this.data = data;
	}

	public SingleResponse(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
