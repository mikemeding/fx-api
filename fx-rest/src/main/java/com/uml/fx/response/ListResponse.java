/*
 * Copyright (c) 2013 OutSmart Power Systems, Inc. -- All Rights Reserved.
 */
package com.uml.fx.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A generic list response
 * <p>
 * @author uwe
 * @param <T> list content type
 */
@XmlRootElement
public class ListResponse<T> extends AbstractResponse {

	private List<T> list;

	public ListResponse() {
		this(null, DEFAULT_STATUS, DEFAULT_VERSION);
	}

	public ListResponse(String status) {
		this(null, status, DEFAULT_VERSION);
	}

	public ListResponse(String status, float version) {
		this(null, status, version);
	}

	public ListResponse(Collection<T> list) {
		this(list, DEFAULT_STATUS, DEFAULT_VERSION);
	}

	public ListResponse(Collection<T> list, String status, float version) {
		super(status, version);
		this.list = new ArrayList<>();
		if (list != null) {
			this.list.addAll(list);
		}
	}

	/**
	 * Add another collection to our response list
	 * <p>
	 * @param elements are the additional elements
	 */
	@SafeVarargs
	@SuppressWarnings("varargs")
	public final void add(T... elements) {
		Collections.addAll(this.list, elements);
	}

	/**
	 * Get the list of elements
	 * <p>
	 * @return the list of elements
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * Set the list of elements
	 * <p>
	 * @param list the list of elements
	 */
	public void setList(List<T> list) {
		this.list = list;
	}
}
