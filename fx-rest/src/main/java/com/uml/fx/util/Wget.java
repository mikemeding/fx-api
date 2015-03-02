/*
 * Copyright (c) 2009 Meding Software Technik. All Rights Reserved.
 * $Id$
 */
package com.uml.fx.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Simple wget utility.
 * @author <a href="mailto:umeding@outsmartinc.com">Uwe B. Meding</a>
 */
public class Wget {

    // number of bytes to read at once
    private final static int READ_BUFFER_SIZE = 2048;
    // number od retries
    public final static int MAX_RETRIES = 42;

    /**
     * Same as wget(d, u), but verbosity is off.
     *
     * @return true if the downloading actually happened
     */
    public static boolean wget(final String downloadDir, final String sUrl)
	    throws Exception {
	return wget(downloadDir, sUrl, false, false);
    }

    /**
     * This method is public so that applications may use it (as a library).
     *
     * @return true if the downloading actually happened
     */
    public static boolean wget(String downloadDir,
	    final String sUrl,
	    final boolean head,
	    final boolean verbose)
	    throws
	    Exception {
	if (!downloadDir.endsWith(File.separator)) {
	    downloadDir += File.separator;
	}

	int i = sUrl.lastIndexOf("/");
	String fileName = sUrl.substring(i + 1);
	fileName = downloadDir + fileName;

	final File targetFile = new File(fileName);

	final URL url = new URL(sUrl);

	final URLConnection con = url.openConnection();

	final long remoteLastModified = con.getLastModified();

	if (head && targetFile.exists() && verbose) {
	    System.out.println("..wget() local   " + targetFile.lastModified());
	    System.out.println("..wget() remote  " + remoteLastModified);
	}

	if (head &&
		targetFile.exists() &&
		remoteLastModified != 0 &&
		targetFile.lastModified() >= remoteLastModified) {
	    if (verbose) {
		System.out.println("..wget() local file fresher than web version. " +
			"Not downloading.");
	    }

	    return false;
	}

	if (verbose) {
	    System.out.println("..wget() will save to " + fileName);
	}

	int contentLength = con.getContentLength();

	if (verbose) {
	    System.out.println("..wget() contentLength is " + contentLength);
	}

	if (contentLength < 0) {
	    contentLength = Integer.MAX_VALUE;
	}

	final InputStream is = url.openStream();

	final FileOutputStream fos =
		new FileOutputStream(fileName);

	byte[] buffer = new byte[READ_BUFFER_SIZE];

	int totalRead = 0;
	int retries = 0;

	while (true) {
	    int read = is.read(buffer);

	    totalRead += read;

	    if (verbose) {
		System.out.println("..wget() read " + read +
			" bytes   (" + totalRead + "/" + contentLength + ") r" + retries);
	    }

	    if (read > 0) {
		fos.write(buffer, 0, read);
		fos.flush();

		retries = 0;
	    }

	    if (totalRead >= contentLength) {
		break;
	    }

	    if (read < READ_BUFFER_SIZE) {
		if (retries >= MAX_RETRIES) {
		    if (verbose) {
			System.out.println("..wget() giving up.");
		    }

		    break;
		}

		if (read < 1) {
		    //Thread.sleep(14);
		    Thread.yield();
		    retries++;
		}
	    }
	}

	//fos.flush();
	fos.close();
	is.close();

	return true;
    }

    private static void printUsage() {
	final String cmd = "java " + Wget.class.getName();

	System.out.println("USAGE :");
	System.out.println();
	System.out.print(cmd);
	System.out.println(" [-d {downloadDir}] [-v] [-H] {URL}*");
	System.out.println(" [-v] [-H]");
	System.out.println();
	System.out.println("Wget is a java 'wget', with not much features.");
	System.out.println();
	System.out.println("  -v : verbose");
	System.out.println("  -h : prints this usage and exits");
	System.out.println("  -H : HEAD, will not download if local resource fresher than web resource");
	System.out.println();

	System.exit(-1);
    }

    public static void main(final String[] args)
	    throws Exception {
	if (args.length < 1) {
	    printUsage();
	}

	boolean head = false;
	boolean verbose = false;

	int index = 0;

	String downloadDir = ".";

	while (index < args.length && args[index].startsWith("-")) {
	    if (args[index].equals("-d")) {
		if (args.length - index < 2) {
		    printUsage();
		}

		downloadDir = args[index + 1];
		index += 2;

	    } else if (args[index].equals("-v")) {
		verbose = true;
		index++;

	    //System.out.println("...verbose");
	    } else if (args[index].equals("-H")) {
		head = true;
		index++;
	    } else if (args[index].equals("-h")) {
		printUsage();
	    }
	}

	//
	// do download

	for (int i = index; i < args.length; i++) {
	    //System.out.println("args["+i+"] is >"+args[i]+"<");

	    wget(downloadDir, args[i], head, verbose);
	    System.out.println("...got " + args[i]);
	}
    }
}
