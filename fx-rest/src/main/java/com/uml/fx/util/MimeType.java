/*
 * Copyright (c) 2008 Meding Software Technik -- All Rights Reserved.
 */
package com.uml.fx.util;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;
import javax.activation.MimetypesFileTypeMap;

/**
 * Simplified MIME types
 *
 * @author <a href="umeding@outsmartinc.com">Uwe B. Meding</a>
 */
public class MimeType implements Serializable{
	private static final long serialVersionUID = 8892573979493520527L;

	protected transient static final String[] NONE = new String[0];
	protected transient static HashMap<String, MimeType> instMap = new HashMap<>();
	protected transient static HashMap<String, MimeType> extMap = new HashMap<>();
	protected transient static MimetypesFileTypeMap mtftm =
			(MimetypesFileTypeMap) MimetypesFileTypeMap.getDefaultFileTypeMap();
	// True if this type is compressible
	protected boolean compressible;
	// The MIME type
	protected String name;
	// The default extensions
	protected String[] extensions;

	protected MimeType(String name) {
		this(name, null);
	}
	//------------------------------------------------------------------------
	// DATA FILES
	//------------------------------------------------------------------------
	/**
	 * Plain Text
	 */
	public static final MimeType TXT = new MimeType("text/plain", ".txt", true);
	/**
	 * Text CSV
	 */
	public static final MimeType CSV = new MimeType("text/x-csv", ".csv", true);
	/**
	 * Rich Text
	 */
	public static final MimeType RTX = new MimeType("text/richtext", ".rtx", true);
	/**
	 * Hyper Text
	 */
	public static final MimeType HTML = new MimeType("text/html", ".html .htm .jsp", true);
	/**
	 * XHTML
	 */
	public static final MimeType XHTML = new MimeType("application/xhtml+xml",
			".xhtml .xht", true);
	/**
	 * JSON data
	 */
	public static final MimeType JSON = new MimeType("application/json", ".json");
	/**
	 * JavaScript
	 */
	// NOTE Technically, this should be application/x-javascript, but as the
	// specification itself is incorrect and uses text/javascript, this may need
	// to be changed in the future.
	public static final MimeType JS = new MimeType("application/x-javascript",
			".js", true);
	/**
	 * CSS
	 */
	public static final MimeType CSS = new MimeType("text/css", ".css", true);
	/**
	 * SGML
	 */
	public static final MimeType SGML = new MimeType("text/x-sgml", ".sgm .sgml", true);
	/**
	 * XML
	 */
	public static final MimeType XML = new MimeType("application/xml", ".xml", true);
	/**
	 * Zip Data
	 */
	public static final MimeType ZIP = new MimeType("application/zip", ".zip");
	/**
	 * Gzip Data
	 */
	public static final MimeType GZIP = new MimeType("application/x-gzip", ".gz");
	/**
	 * Tar Data
	 */
	public static final MimeType TAR = new MimeType("application/x-tar", ".tar", true);
	/**
	 * Octet Stream
	 */
	public static final MimeType BINARY = new MimeType("application/octet-stream", ".bin .exe");
	//------------------------------------------------------------------------
	// IMAGES
	//----------------------------------------------------------------------
	/**
	 * Icon
	 */
	public static final MimeType ICO = new MimeType("application/ico", ".ico");
	/**
	 * GIF Image
	 */
	public static final MimeType GIF = new MimeType("image/gif", ".gif");
	/**
	 * Bitmap Image
	 */
	public static final MimeType BMP = new MimeType("image/x-xbitmap", ".bmp", true);
	/**
	 * JPEG Image
	 */
	public static final MimeType JPEG = new MimeType("image/jpeg", ".jpeg .jpe .jpg");
	/**
	 * Progessive JPEG Image
	 */
	public static final MimeType PJPEG = new MimeType("image/pjpeg", ".pjpeg");
	/**
	 * PNG Image
	 */
	public static final MimeType PNG = new MimeType("image/png", ".png");
	/**
	 * SVG Image
	 */
	public static final MimeType SVG = new MimeType("image/svg+xml", ".svg .svgz");
	/**
	 * TIFF Image
	 */
	public static final MimeType TIFF = new MimeType("image/tiff", ".tiff .tif");
	/**
	 * XBM Image
	 */
	public static final MimeType XBM = new MimeType("image/x-xbitmp", ".xbm");
	/**
	 * XPM Image
	 */
	public static final MimeType XPM = new MimeType("image/x-xpixmap", ".xpm");
	//------------------------------------------------------------------------
	// APPLICATION FILES
	//------------------------------------------------------------------------
	/**
	 * Microsoft Excel Workbook
	 */
	public static final MimeType XLS = new MimeType("application/vnd.ms-excel", ".xls");
	/**
	 * Microsoft Word Document
	 */
	public static final MimeType DOC = new MimeType("application/vnd.ms-word", ".doc");
	/**
	 * Microsoft Powerpoint
	 */
	public static final MimeType PPT = new MimeType("application/vnd.ms-powerpoint", ".ppt");
	/**
	 * Adobe PDF
	 */
	public static final MimeType PDF = new MimeType("application/pdf", ".pdf");
	/**
	 * Adobe PostScript
	 */
	public static final MimeType PS = new MimeType("application/postscript", ".eps .ps .ai");
	/**
	 * Java Class
	 */
	public static final MimeType JAVA = new MimeType("application/java-vm", ".class");
	/**
	 * Adobe FrameMaker / MIF
	 */
	public static final MimeType MIF = new MimeType("application/x-mif", ".mif");
	//------------------------------------------------------------------------
	// HTTP
	//------------------------------------------------------------------------
	public static final MimeType WEB_FORM = new MimeType(
			"application/x-www-form-urlencoded", ".wf");
	public static final MimeType MULTIPART_FORM = new MimeType(
			"multipart/form-data", ".mwf");
	//------------------------------------------------------------------------
	// MULTIMEDIA FILES
	//------------------------------------------------------------------------
	/**
	 * MP3
	 */
	public static final MimeType MP3 = new MimeType("audio/mpeg", ".mpga .mp2 .mp3");
	/**
	 * WAV
	 */
	public static final MimeType WAV = new MimeType("audio/x-wav", ".wav", true);
	/**
	 * Real Audio
	 */
	public static final MimeType RAM = new MimeType("audio/x-pn-realaudio", ".ram .rm .ra");
	/**
	 * Shockwave Flash
	 */
	public static final MimeType SWF = new MimeType("application/x-shockwave-flash", ".swf");

	/**
	 * Creates a new MimeType object.
	 *
	 * @param name 	Mime Type declaration
	 * @param name 	Space delimited extensions with optional dot
	 */
	protected MimeType(String name, String extensions) {
		this(name, extensions, false);
	}

	/**
	 * Creates a new MimeType object.
	 *
	 * @param name 	Mime Type declaration
	 * @param name 	Space delimited extensions with optional dot
	 */
	protected MimeType(String name, String extensions, boolean compressible) {

		// Check Instance Map for duplicate Name
		if (instMap.containsKey(name)) {
			throw new IllegalArgumentException("Duplicate MIME Type: " + name);
		}

		this.name = name;
		this.compressible = compressible;

		if (null == extensions || extensions.trim().equals("")) {
			this.extensions = NONE;
		} else {
			String[] parts = extensions.trim().split(" ");
			this.extensions = new String[parts.length];
			for (int i = 0; i < parts.length; i++) {
				char c = parts[i].charAt(0);
				if (c == '.') {
					this.extensions[i] = parts[i].substring(1);
				} else {
					this.extensions[i] = parts[i];
				}
			}
		}

		// register
		registerInstance();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final MimeType other = (MimeType) obj;
		if (!Objects.equals(this.name, other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 43 * hash + Objects.hashCode(this.name);
		return hash;
	}


	/**
	 * Returns true if compressible
	 */
	public boolean isCompressible() {
		return compressible;
	}

	/**
	 * Prints Class Name
	 */
	@Override
	public String toString() {
		return getName();
	}

	/**
	 * Get the name of this class
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the file extensions associated with this MIME type.
	 */
	public String[] getExtensions() {
		return extensions;
	}

	public void addExtension(String ext) {
		String[] exts = new String[extensions.length + 1];
		System.arraycopy(extensions, 0, exts, 0, extensions.length);
		exts[extensions.length] = ext;
		extensions = exts;
	}

	/**
	 *  see if a particular extensions pressent
	 *  @param ext is the extension we are looking for
	 *  @return true or false
	 */
	public boolean haveExtension(String name) {
		String[] exts = getExtensions();
		for (int i = 0; i < exts.length; i++) {
			if (name.equals(exts[i])) {
				return (true);
			}
		}
		return (false);
	}

	/**
	 * Keep track of instances
	 */
	private void registerInstance() {
		instMap.put(name, this);
		String[] exts = getExtensions();
		for (String extension : exts) {
			if (extMap.containsKey(extension)) {
				throw new IllegalStateException(
						"Duplicate file extension: " + extension);
			}

			extMap.put(extension, this);
		}
	}

	/**
	 * Return the MimeType with the given value.
	 *
	 * @param name DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public static MimeType get(String name) {
		name = trimName(name);
		MimeType mt = (MimeType) instMap.get(name);
		if (null == mt) {
			throw new IllegalArgumentException("No MIME type found for '" + name + "'");
		}
		return mt;
	}

	/**
	 *  See if a particular type is defined.
	 *  @param name is the mime type name
	 *  @return true or false
	 */
	public static boolean have(String name) {
		name = trimName(name);
		MimeType mt = (MimeType) instMap.get(name);
		return (mt != null);
	}

	/**
	 *  Trim the name to something usable. Sometimes we get additional
	 *  crap at the end.
	 *  @param name is the unfiltered name
	 *  @return the beautified name
	 */
	private static String trimName(String name) {
		int semi = name.indexOf(';');
		if (semi < 0) {
			return (name.trim());
		} else {
			// discard anything after the semicolon
			return (name.substring(0, semi));
		}
	}

	/**
	 *  Define a mime type for a filename
	 *  @param mime is the mime type
	 *  @param filename is the filename we defining this for
	 */
	public static void defineForFilename(String mime, String filename) {
		String ext;
		int dot = filename.lastIndexOf('.');
		if (dot < 0) {
			ext = filename;
		} else {
			ext = filename.substring(dot + 1);
		}

		MimeType mt;
		if (have(mime)) {
			mt = get(mime);
			if (false == mt.haveExtension(ext)) {
				mt.addExtension(ext);
			}
		} else {
			mt = new MimeType(mime, ext);
		}

		// Update the system mime table
		String type = mtftm.getContentType(filename);
		if (false == type.equals(mime)) {
			mtftm.addMimeTypes(mime + " " + ext);
		}

	}

	/**
	 * Get the mime type for a filename
	 * @param name is filename
	 * @return the mime type
	 */
	public static MimeType forName(String name) {
		int pos = name.lastIndexOf('.');
		if (pos < 0) {
			pos = 0;
		}
		return (forExtension(name.substring(pos), false));
	}

	/**
	 * Get the mime type for a file
	 * @param file is the file
	 * @return the mime type
	 */
	public static MimeType forName(File file) {
		return (forName(file.getName()));
	}

	/**
	 * Return the MimeType for the given extension.
	 *
	 * @param name DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public static MimeType forExtension(String name) {
		return (forExtension(name, true));
	}

	private static MimeType forExtension(String name, boolean bomb) {
		String key = name;
		char c = key.charAt(0);
		if (c == '.') {
			key = key.substring(1);
		}

		MimeType mt = (MimeType) extMap.get(key);
		if (null == mt) {
			if (bomb) {
				throw new IllegalArgumentException("No MIME type found for extension '." + key + "'");
			} else {
				mt = BINARY;
			}
		}
		return mt;
	}
}
