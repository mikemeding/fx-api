/*
 * Copyright (c) 2009 Meding Software Technik. All Rights Reserved.
 * $Id: MSTConfig.java,v 1.1 2009/03/08 19:16:40 uwe Exp $
 */
package com.uml.fx.util;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

/**
 * MST Config helper functions.
 *
 * @author <a href="mailto:umeding@outsmartinc.com">Uwe B. Meding</a>
 */
public class MSTConfig {

    /**
     * Get the base directory. This could be defined on the command line
     * as "os.home" or in the environment as "MST_HOME".
     * os.home has priority.
     * @return the path
     */
    public static File getBaseDir() {
	String dir = System.getProperty("os.home");
	if (null == dir) {
	    dir = System.getenv("MST_HOME");
	}
	if (null == dir) {
	    throw new IllegalStateException("base directory not defined (os.home or MST_HOME)");
	}
	File path = new File(dir);
	if (!(path.exists() && path.isDirectory())) {
	    throw new IllegalStateException("base directory not a directory");
	}
	return (path);
    }

    /**
     * Get a subdirectory to the base dir.
     * @param subdir is the subdirectory
     * @return the path
     */
    public static File getBaseDir(String subdir) {

	File baseDir = getBaseDir();
	File path = new File(baseDir, subdir);
	if (!(path.exists() && path.isDirectory())) {
	    throw new IllegalStateException(path + ": not a directory");
	}
	return (path);
    }

    /**
     * Read some config properties. Properties are expected to be located in
     * &lt;base dir&gt;/etc/&lt;name&gt;.properties
     * NOTE: no exceptions are thrown if the properties cannot be found.
     * @param props are properties to attach to
     * @param name are the properties to load.
     */
    public static Properties getConfigProperties(Properties props, String name) {
	File etc = getBaseDir("etc");
	try {
	    File propFile = new File(etc, name + ".properties");
	    if (propFile.exists() && propFile.isFile() && propFile.canRead()) {
		FileReader fp = new FileReader(propFile);
		props.load(fp);
		fp.close();
	    }
	} catch (Exception e) {
	    // ignored
	}
	return (props);
    }

    /**
     * Read some config properties. Properties are expected to be located in
     * &lt;base dir&gt;/etc/&lt;name&gt;.properties
     * NOTE: no exceptions are thrown if the properties cannot be found.
     * @param name are the properties to load.
     */
    public static Properties getConfigProperties(String name) {
	Properties props = new Properties();
	return (getConfigProperties(props, name));
    }
}
