/*
 * Copyright 2009 Meding Software Technik -- All Rights Reserved.
 */
package com.uml.fx.util;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * Id collections helper
 * @author uwe
 */
public class IdCollection extends TreeMap<String, Long> implements Iterable<Long> {

	private final boolean maintainOrder;

	/**
	 * Create a new id collection
	 */
	public IdCollection() {
		this(false);
	}

	public IdCollection(boolean maintainOrder) {
		this.maintainOrder = maintainOrder;
	}

	/**
	 * Add an id
	 * @param id
	 */
	public void add(long id) {
		if (maintainOrder) {
			put(String.format("%08x", size() + 1), id);
		} else {
			put(String.valueOf(id), id);
		}
	}

	/**
	 * Convert the list of id's to an array
	 * @return an array of id's
	 */
	public long[] toIdArray() {
		long[] la = new long[size()];
		int pos = 0;
		for (Long l : values()) {
			la[pos++] = l;
		}
		return (la);
	}

	/**
	 * Takes the keys and creates an array
	 * @return array of string keys
	 */
	public String[] toStringArray() {
		String[] sa = new String[size()];
		int pos = 0;
		for (String s : keySet()) {
			sa[pos++] = s;
		}
		return (sa);
	}

	
	/**
	 * Provide an easy interface to iterate the ids
	 * @return an iterator
	 */
	@Override
	public Iterator<Long> iterator() {
		return super.values().iterator();
	}


}
