/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ecore.extender.tool.internal;

/**
 * This class provides some string util feature.
 * 
 * @author mchauvin
 */
public final class StringUtil {

    /** "" . */
    public static final String EMPTY_STRING = ""; // $NON-NLS$

    /**
     * Avoid instantiation.
     */
    private StringUtil() {

    }

    /**
     * Check if a {@link String} is empty. Please use it instead of
     * s.equals("").
     * 
     * @param s
     *            the string to test.
     * @return <code>True</code> if this string is empty, <code>False</code>
     *         otherwise.
     */
    public static boolean isEmpty(final String s) {
        return s.length() == 0;
    }
}
