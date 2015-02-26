/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uml.fx.json;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author uwe
 */
public class Test {

	private final static boolean JO_EMPTY = false;
	private final static boolean JO_STRING = true;
	private final static boolean JO_LONG = false;
	private final static boolean JO_INT = false;
	private final static boolean JO_SHORT = false;
	private final static boolean JO_DOUBLE = false;
	private final static boolean JO_COMPOSITE = true;
	private final static boolean JO_PARSE = true;
	private final static boolean JO_PARSE_FAIL = false;

	private final static boolean JA_EMPTY = true;
	private final static boolean JA_STRING = true;
	private final static boolean JA_LONG = true;
	private final static boolean JA_INT = true;
	private final static boolean JA_SHORT = true;
	private final static boolean JA_DOUBLE = true;
	private final static boolean JA_COMPOSITE = true;
	private final static boolean JA_PARSE = true;
	private final static boolean JA_PARSE_FAIL = true;



	private void execute() throws Exception {

		JSONObject jo;

		if (JO_EMPTY) {
			jo = new JSONObject();
			System.out.println("Empty object: " + jo);
		}

		if (JO_STRING) {
			jo = new JSONObject();
			jo.put("string1", "laber");
			jo.put("string2", "laber/lall");
			jo.put("string3", "laber lall \"scheisse\"");
			jo.put("string4", "laber 'lall'");
			jo.put("string5", "laber\n'lall'\n");
			System.out.println("Strings: " + jo);
		}
		
		if(JO_LONG) {
			jo = new JSONObject();
			jo.put("lmin", Long.MIN_VALUE);
			jo.put("lmax", Long.MAX_VALUE);
			jo.put("number", 1234L);
			System.out.println("Longs: " + jo);
		}

		if(JO_INT) {
			jo = new JSONObject();
			jo.put("imin", Integer.MIN_VALUE);
			jo.put("imax", Integer.MAX_VALUE);
			jo.put("number", 1234);
			System.out.println("Ints: " + jo);
		}

		if(JO_SHORT) {
			jo = new JSONObject();
			jo.put("smin", Short.MIN_VALUE);
			jo.put("smax", Short.MAX_VALUE);
			jo.put("number", (short)1234);
			System.out.println("shorts: " + jo);
		}

		if(JO_DOUBLE) {
			jo = new JSONObject();
            jo.put("dmin", Double.MIN_VALUE);
            jo.put("dmax", Double.MAX_VALUE);
            jo.put("number", (double)12.34);
			System.out.println("shorts: " + jo);
		}

		if(JO_COMPOSITE) {
			jo = new JSONObject();
			jo.put("number", 42);
			jo.put("string", "ha ha ha");
			jo.put("real", 1.23);
			jo.put("bool", true);

			JSONObject child = new JSONObject();
			child.put("n", 42);

			jo.put("child", child);
			System.out.println("composite: " + jo);
		}
		
		DefaultJSONFactory jf = new DefaultJSONFactory();
		if(JO_PARSE) {
			String js = jo.toString();
			JSONObject parsed = jf.jsonObject(js);
			System.out.println("Parsed: "+parsed);
		}

		if(JO_PARSE_FAIL) {
			jo = jf.jsonObject("{\"scheisse\":42");
			System.out.println("fucked: " + jo);
		}

		JSONArray ja;

		if(JA_EMPTY) {
			ja = new JSONArray();
			System.out.println("empty array: " + ja);
		}

		if(JA_STRING) {
			ja = new JSONArray();
			ja.put("laber");
			ja.put("laber lall");
			System.out.println("string array: " + ja);
		}

		if(JA_COMPOSITE) {
			ja = new JSONArray();
			ja.put(jo);
			ja.put(jo);
			System.out.println("composite array: " + ja);
		}

		if(JA_PARSE) {
			String jaString = ja.toString();
			JSONArray japarse = jf.jsonArray(jaString);
			System.out.println("parsed: "+japarse);
		}
	}

	public static void main(String[] av) throws Exception {
		new Test().execute();
	}
}
