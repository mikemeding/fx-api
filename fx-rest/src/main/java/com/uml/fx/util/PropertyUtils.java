/*
 * Copyright (c) 2008 Meding Software Technik -- All Rights Reserved
 */
package com.uml.fx.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Property utilities
 * <p>
 * @author uwe
 */
public class PropertyUtils {

	private final static Map<String, Boolean> boolNames;

	static {
		boolNames = new HashMap<>();
		boolNames.put("true", Boolean.TRUE);
		boolNames.put("on", Boolean.TRUE);
		boolNames.put("yes", Boolean.TRUE);

		boolNames.put("false", Boolean.FALSE);
		boolNames.put("off", Boolean.FALSE);
		boolNames.put("no", Boolean.FALSE);
	}

	/**
	 * Load properties from files
	 * <p>
	 * @param props the properties holder
	 * @param files the files
	 * @throws IOException
	 */
	public static void loadInto(Properties props, File... files) throws IOException {
		for (File file : files) {
			if (file.exists() && file.canRead()) {
				try (FileInputStream fp = new FileInputStream(file)) {
					props.load(fp);
				}
			}
		}
	}

	/**
	 * Load properties from a properties resource. Example:
	 * <pre>
	 * Properties props = System.getProperties();
	 * PropertyUtils.loadFromResource(props, "resource-name");</pre>
	 * <p>
	 * @param props the properties
	 * @param names the resource names
	 * @throws IOException
	 */
	public static void loadFromResource(Properties props, String... names)
			throws IOException {
		if (names == null) {
			return;
		}
		for (String name : names) {
			if (!name.startsWith("/")) {
				name = "/" + name;
			}
			if (!name.endsWith(".properties")) {
				name = name + ".properties";
			}
			try (InputStream in = PropertyUtils.class.getResourceAsStream(name)) {
				if (in != null) {
					props.load(in);
				}
			}
		}
	}

	/**
	 * Load properties from a properties resources. Example:
	 * <pre>
	 * Properties props = System.getProperties();
	 * PropertyUtils.loadFromResource(props, Main.class, "resource-name");</pre>
	 * <p>
	 * @param props the properties
	 * @param klass the class which this resource is for
	 * @param names the names of the resources
	 * @throws IOException
	 */
	public static void loadFromResource(Properties props, Class<?> klass, String... names)
			throws IOException {
		if (names == null) {
			return;
		}
		for (String name : names) {
			if (!name.endsWith(".properties")) {
				name = name + ".properties";
			}
			try (InputStream in = klass.getResourceAsStream(name)) {
				if (in != null) {
					props.load(in);
				}
			}
		}
	}

	/**
	 * Load properties from a stream.
	 * <p>
	 * @param props the properties
	 * @param fp    the input stream
	 * @throws IOException
	 */
	public static void loadFromStream(Properties props, InputStream fp) throws IOException {
		if (fp != null) {
			props.load(fp);
		}
	}

	/**
	 * See if we have a particular property
	 * <p>
	 * @param props the properties
	 * @param key   the property name
	 * @return true/false
	 */
	public static boolean haveProperty(Properties props, String key) {
		return props.getProperty(key, null) != null;
	}

	/**
	 * Get a string value from the property list
	 * <p>
	 * @param props        are the properties
	 * @param key          is the property key
	 * @param defaultValue is the default value
	 * @return a string
	 */
	public static String getString(Properties props, String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}

	/**
	 * Get a string value from a property list, if property is not defined an
	 * empty string is returned.
	 * <p>
	 * @param props the properties
	 * @param key   the key
	 * @return the string
	 */
	public static String getString(Properties props, String key) {
		return getString(props, key, "");
	}

	/**
	 * Get a boolean value from a property list, if the property is not defined
	 * a default value is returned
	 * <p>
	 * @param props        aree the properties
	 * @param key          is the property key
	 * @param defaultValue is the default value
	 * @return the value
	 */
	public static boolean getBoolean(Properties props, String key, boolean defaultValue) {
		String stringValue = getString(props, key, String.valueOf(defaultValue));
		boolean value;
		try {
			stringValue = stringValue.toLowerCase();
			if (boolNames.containsKey(stringValue)) {
				return boolNames.get(stringValue);
			} else {
				return defaultValue;
			}
		} catch (Exception e) {
			value = defaultValue;
		}
		return value;
	}

	/**
	 * Get a boolean value from a property list. 'false' is returned in case the
	 * property is not defined.
	 * <p>
	 * @param props the property list
	 * @param key   the property key
	 * @return the value
	 */
	public static boolean getBoolean(Properties props, String key) {
		return getBoolean(props, key, false);
	}

	/**
	 * Get an integer value
	 * <p>
	 * @param props        the properties
	 * @param key          the key
	 * @param defaultValue the default value
	 * @return the integer value
	 */
	public static int getInt(Properties props, String key, int defaultValue) {
		String stringValue = getString(props, key, String.valueOf(defaultValue));
		int value;
		try {
			value = Integer.parseInt(stringValue);
		} catch (Exception e) {
			value = defaultValue;
		}
		return value;
	}

	/**
	 * Get an integer value
	 * <p>
	 * @param props the properties
	 * @param key   the key
	 * @return the value
	 */
	public static int getInt(Properties props, String key) {
		return getInt(props, key, 0);
	}

	/**
	 * Get a long value
	 * <p>
	 * @param props        the properties
	 * @param key          the key
	 * @param defaultValue the default value
	 * @return the integer value
	 */
	public static long getLong(Properties props, String key, long defaultValue) {
		String stringValue = getString(props, key, String.valueOf(defaultValue));
		long value;
		try {
			value = Long.parseLong(stringValue);
		} catch (Exception e) {
			value = defaultValue;
		}
		return value;
	}

	/**
	 * Get a long value
	 * <p>
	 * @param props the properties
	 * @param key   the key
	 * @return the value
	 */
	public static long getLong(Properties props, String key) {
		return getLong(props, key, 0);
	}

	/**
	 * Get a double value
	 * <p>
	 * @param props        the properties
	 * @param key          the key
	 * @param defaultValue the default value
	 * @return the integer value
	 */
	public static double getDouble(Properties props, String key, double defaultValue) {
		String stringValue = getString(props, key, String.valueOf(defaultValue));
		double value;
		try {
			value = Double.parseDouble(stringValue);
		} catch (Exception e) {
			value = defaultValue;
		}
		return value;
	}

	/**
	 * Get an integer value
	 * <p>
	 * @param props the properties
	 * @param key   the key
	 * @return the value
	 */
	public static double getDouble(Properties props, String key) {
		return getDouble(props, key, 0.0);
	}

	/**
	 * Extract a inet socket address from the properties list, expected is
	 * something in the following form.
	 * <pre>
	 * google.com:80
	 * xbs3.meding.local:1234
	 * 192.168.19.199:80
	 * [2001:db8:85a3:8d3:1319:8a2e:370:7348]:443
	 * 2001:db8:85a3:8d3:1319:8a2e:370:7348:443
	 * </pre>
	 * <p>
	 * @param props are the properties
	 * @param key   is the key
	 * @return the socket address
	 */
	public static InetSocketAddress getInetSocketAddress(Properties props, String key) {
		if (!haveProperty(props, key)) {
			throw new NullPointerException(key + ": property not found");
		}

		String stringAddress = getString(props, key);
		int colon = stringAddress.lastIndexOf(':');
		if (colon < 0) {
			throw new IllegalArgumentException(key + ": port specification not found");
		}

		String portString = stringAddress.substring(colon + 1);
		int port = Integer.parseInt(portString);

		String address = stringAddress.substring(0, colon);
		if (address.startsWith("[") && address.endsWith("]")) {
			address = address.substring(1, address.length() - 1);
		}

		try {
			InetAddress inetAddress = InetAddress.getByName(address);
			return new InetSocketAddress(inetAddress, port);
		} catch (UnknownHostException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * Get the inet address from the property list
	 * <p>
	 * @param props are the properties
	 * @param key   is hte property key
	 * @return the inet address
	 */
	public static InetAddress getInetAddress(Properties props, String key)
			throws UnknownHostException {
		if (!haveProperty(props, key)) {
			throw new NullPointerException(key + ": property not found");
		}

		String stringAddress = getString(props, key);
		return InetAddress.getByName(stringAddress);
	}

	/**
	 * Get the inet address from the property list
	 * <p>
	 * @param props are the properties
	 * @param key   is the property key
	 * @return the inet address
	 */
	public static InetAddress[] getAllInetAddresses(Properties props, String key)
			throws UnknownHostException {
		if (!haveProperty(props, key)) {
			throw new NullPointerException(key + ": property not found");
		}

		String stringAddress = getString(props, key);
		return InetAddress.getAllByName(stringAddress);
	}

	/**
	 * Get a URI from the property list
	 * <p>
	 * @param props are the properties
	 * @param key   is the key
	 * @return the uri
	 */
	public static URI getURI(Properties props, String key) throws URISyntaxException {
		String uriString = getString(props, key);
		return new URI(uriString);
	}

	/**
	 * Get the URL form the properties list
	 * <p>
	 * @param props are the properties
	 * @param key   is the property key
	 * @return the URL
	 */
	public static URL getURL(Properties props, String key) throws URISyntaxException, MalformedURLException {
		return getURI(props, key).toURL();
	}
}
