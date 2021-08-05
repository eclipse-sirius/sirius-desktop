/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.model.tools.internal.util;

/**
 * This class provides some java 1.6 String features.
 * 
 * Copied from {@link org.eclipse.sirius.common.tools.api.uti.StringUtil}
 * 
 * @author mchauvin
 */
public final class StringUtil {

    /**
     * Avoid instantiation.
     */
    private StringUtil() {

    }

    /**
     * Check if a {@link String} is empty. Please use it instead of s.equals("").
     * 
     * @param s
     *            the string to test.
     * @return <code>True</code> if this string is empty or <code>null</code>, <code>False</code> otherwise.
     */
    public static boolean isEmpty(final String s) {
        return s == null || s.trim().length() == 0;
    }

}
