/*
 * Copyright (c) 2009 Meding Software Technik. All Rights Reserved.
 * $Id$
 */

package com.uml.fx.util;

/**
 * Implemented by text converters.
 *
 * @author <a href="mailto:umeding@outsmartinc.com">Uwe B. Meding</a>
 */
public interface TextConverter {

    /**
     * Convert text.
     * @param text the source text
     * @return the converted text
     */
    String convert(String text);

}
