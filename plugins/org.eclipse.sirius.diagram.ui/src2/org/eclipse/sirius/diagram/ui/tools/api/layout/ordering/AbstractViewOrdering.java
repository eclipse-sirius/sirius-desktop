/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.layout.ordering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.gmf.runtime.notation.View;

/**
 * A simple partially implementation of {@link ViewOrdering}.
 * 
 * @author ymortier
 */
public abstract class AbstractViewOrdering implements ViewOrdering {

    /** The sorted views. */
    protected List<View> sortedViews;

    /**
     * <code>true</code> if the views are sorted, <code>false</code> otherwise.
     */
    protected boolean isSorted;

    /**
     * Set the list of views to sort.
     * 
     * @param views
     *            the list of views to sort.
     * @param <T>
     *            class which extends {@link View}
     */
    public <T extends View> void setViews(final Collection<T> views) {
        this.sortedViews = new ArrayList<View>(views);
        this.isSorted = false;
    }

    /**
     * The result is not modifiable. All attempts to modify the result will
     * throw an {@link UnsupportedOperationException}.
     * 
     * @return the sorted views.
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.ViewOrdering#getSortedViews()
     */
    public List<View> getSortedViews() {
        if (!isSorted) {
            this.sortedViews = this.sortViews(this.sortedViews);
            isSorted = true;
        }
        return this.sortedViews;
    }

    /**
     * Sorts the views.
     * 
     * @param views
     *            the list of views to sort, this parameter can be modified by
     *            the implementation.
     * @return the list of sorted views.
     */
    protected abstract List<View> sortViews(List<View> views);

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.GridViewOrdering#getSortedViewsAsGrid()
     */
    public GridView getSortedViewsAsGrid() {
        final List<View> list = this.getSortedViews();
        final View[][] views = new View[1][];
        views[0] = list.toArray(new View[list.size()]);
        final GridView gridView = GridView.create(views);
        return gridView;
    }
}
