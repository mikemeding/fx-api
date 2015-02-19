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
 * Names reponse
 *
 * @author uwe
 */
@XmlRootElement
public class NamesResponse extends AbstractResponse {

	private List<String> names;

	public NamesResponse(String... names) {
		super("OK");
		
		this.names = new ArrayList<String>();
		Collections.addAll(this.names, names);
	}

	/**
	 * Add a name to the response
	 *
	 * @param name is the name
	 */
	public void add(String... names) {
		Collections.addAll(this.names, names);
	}

	/**
	 * Add another collection to our names list
	 *
	 * @param other the other collection
	 */
	public void add(Collection<String> other) {
		this.names.addAll(other);
	}

	/**
	 * Get the names
	 *
	 * @return the names
	 */
	public List<String> getNames() {
		return names;
	}
}
