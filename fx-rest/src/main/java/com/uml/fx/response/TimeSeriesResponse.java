/*
 * Copyright (c) 2013 OutSmart Power Systems, Inc. -- All Rights Reserved.
 */
package com.uml.fx.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Time series response
 *
 * @author uwe
 */
public class TimeSeriesResponse {

	private List<List<Object>> data;
	private List<String> columnNames;

	public TimeSeriesResponse() {
		this.data = new ArrayList<List<Object>>();
		this.columnNames = new ArrayList<String>();
	}

	/**
	 * Add a column name
	 *
	 * @param name the column name
	 */
	public void addColumnName(String name) {
		this.columnNames.add(name);
	}

	/**
	 * Add a data row.
	 *
	 * @param dataRow is the data row
	 */
	public void addDataRow(List<Object> dataRow) {
		this.data.add(dataRow);
	}

	/**
	 * Add a data row
	 *
	 * @param dataRow is the data row
	 */
	public void addDataRow(Object... dataRow) {
		List<Object> list = new ArrayList<Object>();
		Collections.addAll(list, dataRow);
		addDataRow(list);
	}

	/**
	 * Get the time series data
	 *
	 * @return the time series data
	 */
	public List<List<Object>> getData() {
		return data;
	}

	/**
	 * Get the column names
	 *
	 * @return the column names
	 */
	public List<String> getColumnNames() {
		return columnNames;
	}
}
