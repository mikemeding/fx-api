/*
 * Copyright (c) 2013 OutSmart Power Systems, Inc. -- All Rights Reserved.
 */
package com.uml.fx.response;

/**
 * Sub tag response
 *
 * @author uwe
 */
public class SubTagReference {

	private String name;
	private String fullname;
	private int subTagCount;

	public SubTagReference(String name, String fullname, int subTagCount) {
		this.name = name;
		this.fullname = fullname;
		this.subTagCount = subTagCount;
	}

	public String getName() {
		return name;
	}

	public String getFullname() {
		return fullname;
	}

	public int getSubTagCount() {
		return subTagCount;
	}
}
