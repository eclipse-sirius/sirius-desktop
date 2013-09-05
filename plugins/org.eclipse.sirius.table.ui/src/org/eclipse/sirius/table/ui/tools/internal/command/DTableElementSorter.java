/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.command;

import java.util.Comparator;

import org.eclipse.swt.SWT;

import org.eclipse.sirius.table.metamodel.table.DTableElement;

/**
 * An abstract {@link Comparator} for {@link DTableElement}s.
 * 
 * @param <T>
 *            the {@link DTableElement} type to compare.
 * 
 * @author <a href="mailto:maxime.porhel@obeo.fr">Maxime Porhel</a>
 */
public abstract class DTableElementSorter<T extends DTableElement> implements Comparator<T> {

    protected int sortDirection;

    /**
     * Default constructor.
     * 
     * @param sortDirection
     *            the sort direction
     */
    public DTableElementSorter(int sortDirection) {
        this.sortDirection = sortDirection;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(final T a, final T b) {
        int result = 0;
        final String labelA = getSortLabel(a);
        final String labelB = getSortLabel(b);
        if (labelA == null || labelB == null) {
            if (!(labelA == null && labelB == null)) {
                if (sortDirection == SWT.UP && labelA == null) {
                    result = -1;
                } else if (sortDirection == SWT.DOWN && labelB == null) {
                    result = -1;
                } else {
                    result = 1;
                }
            }
        } else if (!labelA.equals(labelB)) {
            if (sortDirection == SWT.UP) {
                result = labelA.compareTo(labelB);
            } else {
                result = labelB.compareTo(labelA);
            }
        }
        return result;
    }

    protected abstract String getSortLabel(final T element);
}
