/*
 * Copyright (c) 2009 Meding Software Technik -- All Rights Reserved.
 */

package com.uml.fx.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Provide a hash map with a limited number of entries.
 * @author uwe
 */
public class LimitedHashMap<K,V> extends LinkedHashMap<K,V> {
	private final int limit;

	/**
	 * Create a new limited hash map
	 * @param limit is the limie
	 */
	public LimitedHashMap(int limit) {
		this.limit = limit;
	}

	/**
	 * See if we need to remove the eldest entry
	 * @param eldest eldest entry
	 * @return true if we have reached the limit
	 */
	@Override
	protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
		return(super.size() > limit);
	}
}
