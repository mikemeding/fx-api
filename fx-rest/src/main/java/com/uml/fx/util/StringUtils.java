/*
 * Copyright (c) 2009 Meding Software Technik. All Rights Reserved.
 * $Id$
 */
package com.uml.fx.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

/**
 * Some string utilites.
 *
 * @author <a href="mailto:umeding@outsmartinc.com">Uwe B. Meding</a>
 */
public class StringUtils {

	public final static String EMPTY_STRING = "";

	/**
	 * Replaces all whitespaces from a string with space, removes all redundant
	 * whitespaces.
	 *
	 * @param string string to collapse
	 * @return the collapsed string
	 */
	public static String collapseWhitespace(String string) {
		if (string == null) {
			return (null);
		}

		string = string.trim();
		boolean haveWS = false;
		StringBuilder sb = new StringBuilder();
		char[] ca = string.toCharArray();
		for (char c : ca) {
			if (Character.isWhitespace(c)) {
				if (!haveWS) {
					sb.append(' ');
				}
				haveWS = true;
			} else {
				haveWS = false;
				sb.append(c);
			}
		}
		return (sb.toString());
	}

	/**
	 * Encode a text for db usage etc.
	 *
	 * @param text the text to be encoded
	 * @return the encoded text
	 */
	public static String encode(String text) {
		if (text == null) {
			return (null);
		}
		try {
			return (URLEncoder.encode(text, "UTF-8"));
		} catch (Exception e) {
			return (text);
		}
	}

	/**
	 * Decode a string for db usage etc.
	 *
	 * @param text is hte text
	 * @return the decoded text
	 */
	public static String decode(String text) {
		if (text == null) {
			return (null);
		}
		try {
			return (URLDecoder.decode(text, "UTF-8"));
		} catch (Exception e) {
			return (text);
		}
	}

	/**
	 * Process the escape characters in a string
	 *
	 * @param string the incoming stinrg
	 * @return the resulting string
	 */
	public static String processEscapes(String string) {
		if (string == null) {
			return (null);
		}
		StringBuilder sb = new StringBuilder();
		char[] ca = string.toCharArray();
		for (int i = 0; i < ca.length; i++) {
			switch (ca[i]) {
				case '\\':
					if (i < ca.length - 1) {
						i++;
						// replace the escaped characters
						switch (ca[i]) {
							case 'n':
								sb.append("\n");
								break;
							case 'r':
								sb.append("\r");
								break;
							case 't':
								sb.append("\t");
								break;
							case '\\':
								// ignore
								break;
							default:
								// just take the next one as-is
								sb.append(ca[i]);
								break;
						}
					}
					break;
				default:
					sb.append(ca[i]);
					break;
			}
		}
		return (sb.toString());
	}
//    private static void do_escape(String string) {
//	System.out.println("Before: ["+string+"]");
//	string = processEscapes(string);
//	System.out.println("After: ["+string+"]");
//    }
//    private static void do_collapseWhitespace(String string) {
//
//	System.out.println("Before ["+string+"]");
//	string = collapseWhitespace(string);
//	System.out.println("After ["+string+"]");
//    }
//    public static void main(String[] av) throws Exception {
//
//	do_collapseWhitespace("laber lall");
//	do_collapseWhitespace("\r\nlaber    lall   ");
//	do_collapseWhitespace("\r\nlaber  \r  \n  lall\t\t.   ");
//	do_escape("\\\"scheisse mit reisse\\\"");
//	do_escape("\\\\\"scheisse mit\\n reisse\\\"");
//	System.out.println("done.");
//    }

	/**
	 * Get the base name of a class for instance, java.lang.String returns
	 * 'String'
	 */
	public static String getBaseName(Class c) {
		String className = c.getName();
		String[] parts = className.split("\\.");
		int index = (parts.length) - 1;
		return parts[index];
	}

	/**
	 * Convert a value to an integer
	 *
	 * @param value is the value
	 * @param def is the default value
	 * @return the integer value
	 */
	public static int toInt(String value, int def) {
		try {
			return (Integer.parseInt(value));
		} catch (Exception e) {
			return (def);
		}
	}

	/**
	 * Convert a value to integer, return 0 if it cannot be converted
	 *
	 * @param value is the value
	 * @return the integer value
	 */
	public static int toInt(String value) {
		return (toInt(value, 0));
	}

	/**
	 * See if a string is null
	 */
	public static boolean isNull(String value) {
		if (value == null || value.length() == 0) {
			return (true);
		} else {
			return (false);
		}
	}

	/**
	 * See if a string is not null
	 */
	public static boolean isNotNull(String value) {
		if (value == null || value.length() == 0) {
			return (false);
		} else {
			return (true);
		}
	}
	// Nice format conversions
	private static final DecimalFormat ONE_DECIMAL_PLACE = new DecimalFormat("0.#");
	private static final double KILO = (double) 1024L;
	private static final double MEGA = (double) 1048576L;
	private static final double GIGA = (double) 1073741824L;
	private static final double TERA = (double) 1099511627776L;
	private static final double PETA = (double) 1125899906842624L;
	private static final double EXA = (double) 1152921504606846976L;

	public static String toByteUnits(int bytes) {
		return toByteUnits((double) bytes);
	}

	public static String toByteUnits(long bytes) {
		return toByteUnits((double) bytes);
	}

	/**
	 * Convert a double into a nice byte count
	 *
	 * @param bytes number of bytes
	 * @return a neat string
	 */
	public static String toByteUnits(double bytes) {
		if (bytes < KILO) {
			int intBytes = (int) bytes;
			if (10 * intBytes == 10.0D * bytes) {
				return intBytes + " bytes";
			}
			return bytes + " bytes";
		}
		if (bytes < MEGA) {
			return ONE_DECIMAL_PLACE.format(bytes / KILO) + "KB";
		}
		if (bytes < GIGA) {
			return ONE_DECIMAL_PLACE.format(bytes / MEGA) + "MB";
		}
		if (bytes < TERA) {
			return ONE_DECIMAL_PLACE.format(bytes / GIGA) + "GB";
		}
		if (bytes < PETA) {
			return ONE_DECIMAL_PLACE.format(bytes / TERA) + "TB";
		}
		if (bytes < EXA) {
			return ONE_DECIMAL_PLACE.format(bytes / PETA) + "PB";
		}
		return ONE_DECIMAL_PLACE.format(bytes / EXA) + "EB";
	}

	/**
	 * Builds an MD5-sum from an array of chars as used in password-fields.<br>
	 * Note that this method converts the characters to bytes via
	 * {@link #toBytes(char[])} before applying the md5 hash. By this we achieve
	 * an enhanced security against md5 crackers, which assume ordinary strings.
	 * In order to enhance security even more, however, you should add some
	 * application-specific salt.
	 *
	 * @param salt the "salt", null if plain MD5.
	 * @param input is the input array of chars
	 * @return the md5sum as a string, null if input == null or conversion
	 * failed (error is logged)
	 * @see #md5sum(char[])
	 * @see #MD5SALT
	 */
	public static String md5sum(char[] salt, char[] input) {
		if (input != null) {
			try {
				MessageDigest md5Helper = MessageDigest.getInstance("MD5");
				byte[] inputBytes = ByteHelper.fromCharArray(input);
				if (salt != null && salt.length > 0 && input.length > 0) {
					byte[] saltyBytes = ByteHelper.fromCharArray(salt);
					byte[] saltyInputBytes = new byte[saltyBytes.length + inputBytes.length];
					System.arraycopy(saltyBytes, 0, saltyInputBytes, 0, saltyBytes.length);
					System.arraycopy(inputBytes, 0, saltyInputBytes, saltyBytes.length, inputBytes.length);
					inputBytes = saltyInputBytes;
				}
				return ByteHelper.getHexString(md5Helper.digest(inputBytes), "");
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return null;
	}
	/**
	 * Salt for {@link #md5sum(char[])}.<br>
	 * The default is null, i.e. no salt. Applications should set an individual
	 * salt to enhance security.
	 */
	public static char[] MD5SALT = null;

	/**
	 * Builds an MD5-sum from an array of chars with a default salt given by
	 * {@link #MD5SALT}.
	 *
	 * @param input is the input array of chars
	 * @return the md5sum as a string, null if input == null or conversion
	 * failed (error is logged)
	 */
	public static String md5sum(char[] input) {
		return md5sum(MD5SALT, input);
	}

	public static String md5Hex(String data) {
		return encodeHexString(md5(data));
	}

	public static byte[] md5(String data) {
		return md5(getBytesUtf8(data));
	}

	public static byte[] md5(byte[] data) {
		return getMD5Digest().digest(data);
	}

	private static MessageDigest getMD5Digest() {
		return getDigest("MD5");
	}

	public static String sha512Hex(String data) {
		return encodeHexString(sha512(data));
	}

	public static byte[] sha512(String data) {
		return sha512(getBytesUtf8(data));
	}

	public static byte[] sha512(byte[] data) {
		return getSha512Digest().digest(data);
	}

	private static MessageDigest getSha512Digest() {
		return getDigest("SHA-512");
	}

	static MessageDigest getDigest(String algorithm) {
		try {
			return MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

	public static char[] encodeHex(byte[] data) {
		return encodeHex(data, true);
	}

	public static char[] encodeHex(byte[] data, boolean toLowerCase) {
		return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
	}

	protected static char[] encodeHex(byte[] data, char[] toDigits) {
		int l = data.length;
		char[] out = new char[l << 1];
		// two characters form the hex value.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
			out[j++] = toDigits[0x0F & data[i]];
		}
		return out;
	}

	public static String encodeHexString(byte[] data) {
		return new String(encodeHex(data));
	}

	public static byte[] getBytesUtf8(String string) {
		return string.getBytes(Charset.forName("UTF-8"));
	}
}
