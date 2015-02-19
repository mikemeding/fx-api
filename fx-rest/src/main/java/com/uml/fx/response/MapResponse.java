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
public class MapResponse extends AbstractResponse {

    private final Map<String, String> map = new HashMap<>(); // hashmap with objects defined as strings

    public MapResponse() {
    }

    public MapResponse(Map<String,String> m) {
        this.map.clear();
        this.map.putAll(m);
    }

    /**
     * Adds a new value to the key - value set
     *
     * @param index or the set key
     * @param value is the value contained at that key
     */
    public void put(String index, String value) {
        this.map.put(index, value);
    }

    /**
     * Gets a value from the map based on the key given
     *
     * @param index the search key
     * @return the value contained at that key.
     */
    public String get(String index) {
        return this.map.get(index); // cast as a string.        
    }

    /**
     * Gets all the values contained in the map
     *
     * @return all mapped values
     */
    public Map getMap() {
        return this.map;
    }

}
