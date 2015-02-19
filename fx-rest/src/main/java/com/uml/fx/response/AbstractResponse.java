/*
 * Copyright (c) 2013 OutSmart Power Systems, Inc. -- All Rights Reserved.
 */
package com.uml.fx.response;

/**
 * An abstract response
 * @author uwe
 */
public abstract class AbstractResponse {

	public final static String DEFAULT_STATUS = "OK";
	public final static String OK_STATUS = DEFAULT_STATUS;
	public final static String FAIL_STATUS = "FAIL";
	public final static float DEFAULT_VERSION = 1.0f;
	protected String status;
	protected float version;
	public AbstractResponse() {
		this(DEFAULT_STATUS);
	}

	public AbstractResponse(String status) {
		this(status, DEFAULT_VERSION);
	}

	public AbstractResponse(String status, float version) {
		this.status = status;
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getVersion() {
		return version;
	}

	public void setVersion(float version) {
		this.version = version;
	}
	
}
