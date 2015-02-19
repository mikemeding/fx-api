/*
 * Copyright (c) 2013 OutSmart Power Systems, Inc. -- All Rights Reserved.
 */
package com.uml.fx.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Sub tag reference response
 *
 * @author uwe
 */
@XmlRootElement
public class SubTagReferenceResponse {

	private final static float version = 1.0f;
	private List<SubTagReference> tags;

	public SubTagReferenceResponse(SubTagReference... str) {
		this.tags = new ArrayList<SubTagReference>();
		Collections.addAll(this.tags, str);
	}

	/**
	 * Add some sub tag references
	 *
	 * @param str is a sub tag reference
	 */
	public void add(SubTagReference... str) {
		Collections.addAll(this.tags, str);
	}

	/**
	 * Get the version
	 *
	 * @return the version
	 */
	public static float getVersion() {
		return version;
	}

	/**
	 * Get the sub tag references.
	 *
	 * @return the sub tag references
	 */
	public List<SubTagReference> getTags() {
		return tags;
	}
}
