/*******************************************************************************
 * Copyright (c) 2019, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.sequence.business.internal.util;

import org.eclipse.sirius.diagram.sequence.business.api.util.Range;

/**
 * Specific {@link Range} accepting decreasing range (lower is superior to upper).
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class DecreasingRange extends Range {

    private static final String METHOD_NOT_SUPPORTED_MESSAGE = "This range does not support this method."; //$NON-NLS-1$

    /**
     * Initialize the range with given bounds.
     * 
     * @param lower
     *            the lower bound
     * @param upper
     *            the upper bound.
     */
    public DecreasingRange(int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
    }

    @Override
    public Range shifted(int distance) {
        if (isEmpty()) {
            return Range.EMPTY_RANGE;
        } else {
            return new DecreasingRange(lower + distance, upper + distance);
        }
    }

    @Override
    public int clamp(int value) {
        throw new UnsupportedOperationException(METHOD_NOT_SUPPORTED_MESSAGE);
    }

    @Override
    public boolean includes(int value) {
        throw new UnsupportedOperationException(METHOD_NOT_SUPPORTED_MESSAGE);
    }

    @Override
    public boolean includes(int value, boolean excludeLowerBounds, boolean excludeUpperBounds) {
        throw new UnsupportedOperationException(METHOD_NOT_SUPPORTED_MESSAGE);
    }

    @Override
    public int middleValue() {
        throw new UnsupportedOperationException(METHOD_NOT_SUPPORTED_MESSAGE);
    }

    @Override
    public Range union(Range other) {
        throw new UnsupportedOperationException(METHOD_NOT_SUPPORTED_MESSAGE);
    }

    @Override
    public Range intersection(Range other) {
        throw new UnsupportedOperationException(METHOD_NOT_SUPPORTED_MESSAGE);
    }

    @Override
    public boolean intersects(Range other) {
        throw new UnsupportedOperationException(METHOD_NOT_SUPPORTED_MESSAGE);
    }

    @Override
    public boolean includes(Range other) {
        throw new UnsupportedOperationException(METHOD_NOT_SUPPORTED_MESSAGE);
    }

    @Override
    public boolean includesOneBoundOnly(Range other) {
        throw new UnsupportedOperationException(METHOD_NOT_SUPPORTED_MESSAGE);
    }

    @Override
    public boolean includesAtLeastOneBound(Range other) {
        throw new UnsupportedOperationException(METHOD_NOT_SUPPORTED_MESSAGE);
    }

    @Override
    public Range grown(int distance) {
        throw new UnsupportedOperationException(METHOD_NOT_SUPPORTED_MESSAGE);
    }

    @Override
    public Range shrinked(int distance) {
        throw new UnsupportedOperationException(METHOD_NOT_SUPPORTED_MESSAGE);
    }

    @Override
    public double getProportionalLocation(int n) {
        throw new UnsupportedOperationException(METHOD_NOT_SUPPORTED_MESSAGE);
    }

    @Override
    public boolean validatesBoundsAreDifferent(Range other) {
        throw new UnsupportedOperationException(METHOD_NOT_SUPPORTED_MESSAGE);
    }

}
