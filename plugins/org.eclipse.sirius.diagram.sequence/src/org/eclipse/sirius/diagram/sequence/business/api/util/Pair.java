/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.sequence.business.api.util;

import java.util.Objects;

/**
 * Generic helper class to hold two elements of the same type. In some
 * situations this is more convenient and more lightweight than using a generic
 * list or array with exactly two elements. Pairs are immutable, and thus
 * thread-safe.
 * 
 * @author pcdavid
 * 
 * @param <T>
 *            the type of the elements of the pair.
 */
public class Pair<T> {
    private final T first;

    private final T second;

    /**
     * Creates a new pair with the specified elements. Any of the elements can
     * be null.
     * 
     * @param first
     *            the first element of the pair.
     * @param second
     *            the second element of the pair.
     */
    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Returns the first element of the pair.
     * 
     * @return the first element of the pair.
     */
    public T first() {
        return first;
    }

    /**
     * Returns the second element of the pair.
     * 
     * @return the second element of the pair.
     */
    public T second() {
        return second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (this == obj) {
            result = true;
        } else if (obj instanceof Pair<?> that) {
            result = Objects.equals(this.first, that.first) && Objects.equals(this.second, that.second);
        }
        return result;
    }

    @Override
    public String toString() {
        return "<" + String.valueOf(first) + ", " + String.valueOf(second) + ">"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

}
