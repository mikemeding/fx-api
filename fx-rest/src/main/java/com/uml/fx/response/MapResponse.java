/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uml.fx.response;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mike
 */
public class MapResponse<K, V> extends AbstractResponse {

	private final Map<K, V> map = new HashMap<>(); // hashmap with objects defined as strings

	public MapResponse() {
	}

	public MapResponse(Map<K, V> m) {
		this.map.clear();
		this.map.putAll(m);
	}

	/**
	 * Adds a new value to the key - value set
	 * <p>
	 * @param index or the set key
	 * @param value is the value contained at that key
	 */
	public void put(K index, V value) {
		this.map.put(index, value);
	}

	/**
	 * Gets a value from the map based on the key given
	 * <p>
	 * @param index the search key
	 * @return the value contained at that key.
	 */
	public V get(K index) {
		return this.map.get(index); // cast as a string.        
	}

	/**
	 * Gets all the values contained in the map
	 * <p>
	 * @return all mapped values
	 */
	public Map getMap() {
		return this.map;
	}

}
