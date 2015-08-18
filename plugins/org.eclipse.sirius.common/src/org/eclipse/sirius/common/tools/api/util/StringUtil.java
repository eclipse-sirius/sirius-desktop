/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.util;

/**
 * This class provides some java 1.6 String features.
 * 
 * @author mchauvin
 */
public final class StringUtil {

    /** "". */
    public static final String EMPTY_STRING = ""; //$NON-NLS-1$

    /** "*". */
    public static final String JOKER_STRING = "*"; //$NON-NLS-1$

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
     * @return <code>True</code> if this string is empty or <code>null</code>,
     *         <code>False</code> otherwise.
     */
    public static boolean isEmpty(final String s) {
        return s == null || s.trim().length() == 0;
    }

    /**
     * <p>
     * Compares two CharSequences, returning <code>true</code> if they are
     * equal.
     * </p>
     * 
     * <p>
     * <code>null</code>s are handled without exceptions. Two <code>null</code>
     * references are considered to be equal. The comparison is case sensitive.
     * </p>
     * 
     * <pre>
     * StringUtils.equals(null, null) = true
     * StringUtils.equals(null, "abc") = false
     * StringUtils.equals("abc", null) = false
     * StringUtils.equals("abc", "abc") = true
     * StringUtils.equals("abc", "ABC") = false
     * </pre>
     * 
     * @see java.lang.String#equals(Object)
     * @param cs1
     *            the first CharSequence, may be null
     * @param cs2
     *            the second CharSequence, may be null
     * @return <code>true</code> if the CharSequences are equal, case sensitive,
     *         or both <code>null</code>
     */
    public static boolean equals(CharSequence cs1, CharSequence cs2) {
        return cs1 == null ? cs2 == null : cs1.equals(cs2);
    }

}
