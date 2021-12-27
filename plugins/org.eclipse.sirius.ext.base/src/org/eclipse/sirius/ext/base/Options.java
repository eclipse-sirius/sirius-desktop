/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ext.base;


/**
 * Factory class to create new instances of Options.
 * 
 * @author cbrun
 */
@Deprecated(forRemoval = true)
public final class Options {

    /*
     * utility class.
     */
    private Options() {

    }
    
    /**
     * create a new option representing a None value.
     * 
     * @param <T>
     *            the type expected by the option.
     * @return a newly created none option.
     */
    public static <T> Option<T> newNone() {
        return new Option<T>(null);
    }

    /**
     * create a new option representing Some value.
     * 
     * @param <T>
     *            the type expected by the option.
     * @param value
     *            the value wrapped by the option.
     * @return a newly created Some option.
     */
    public static <T> Option<T> newSome(T value) {
        return new Option<T>(value);
    }

    /**
     * Returns an option wrapping the specified value if it is not null, or an
     * {@linkplain Options#newNone() empty} option otherwise.
     * 
     * @param <T>
     *            the type expected by the option.
     * @param value
     *            the value, which may be null.
     * @return an option wrapping the specified value if it is not null, or an
     *         {@linkplain Options#newNone() empty} option otherwise.
     */
    public static <T> Option<T> fromNullable(T value) {
        if (value == null) {
            return newNone();
        } else {
            return newSome(value);
        }
    }
}
