/*******************************************************************************
 * Copyright (c) 2007, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.api.layout.ordering;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

/**
 * This class is able to sort a list of {@link Edge}s.
 * 
 * @author ymortier
 */
public abstract class AbstractEdgeViewOrdering extends AbstractViewOrdering {

    /**
     * The {@link View} that is the source or the target of edges to sort.
     */
    private View connector;

    /**
     * Return The {@link View} that is the source or the target of edges to
     * sort.
     * 
     * @return The {@link View} that is the source or the target of edges to
     *         sort.
     */
    public View getConnector() {
        return this.connector;
    }

    /**
     * Set The {@link View} that is the source or the target of edges to sort.
     * 
     * @param connector
     *            The {@link View} that is the source or the target of edges to
     *            sort.
     */
    public void setConnector(final View connector) {
        this.connector = connector;
        this.isSorted = false;
    }

    /**
     * Compare two {@link Edge}s. It returns a positive number if
     * <code>edge1</code> is greater than <code>edge2</code>, a negative number
     * if <code>edge1</code> is lesser than <code>edge2</code> or <code>0</code>
     * if <code>edge1</code> equals <code>edge2</code>.
     * 
     * 
     * @param edge1
     *            the first edge.
     * @param edge2
     *            the second edge.
     * @return a positive number if <code>node1</code> is greater than
     *         <code>node2</code>, a negative number if <code>node1</code> is
     *         lesser than <code>node2</code> or <code>0</code> if
     *         <code>node1</code> equals <code>node2</code>.
     */
    public abstract int compare(Edge edge1, Edge edge2);

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.AbstractViewOrdering#sortViews(java.util.List)
     */
    @Override
    protected List<View> sortViews(final List<View> views) {
        Collections.sort(views, new EdgeComparator());
        return views;
    }

    /**
     * The comparator of {@link Edge}s.
     * 
     * @author ymortier
     */
    private final class EdgeComparator implements Comparator<View> {

        /**
         * {@inheritDoc}
         * 
         * @see java.util.Comparator#compare(T, T)
         */
        public int compare(final View view0, final View view1) {

            final Edge edge0 = (Edge) view0;
            final Edge edge1 = (Edge) view1;

            int comparison;
            if (AbstractEdgeViewOrdering.this.isAbleToManageEdge(edge0)) {
                if (AbstractEdgeViewOrdering.this.isAbleToManageEdge(edge1)) {
                    comparison = AbstractEdgeViewOrdering.this.compare(edge0, edge1);
                } else {
                    comparison = 1;
                }
            } else {
                if (AbstractEdgeViewOrdering.this.isAbleToManageEdge(edge1)) {
                    comparison = 1;
                } else {
                    comparison = 0;
                }
            }
            return comparison;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.GridViewOrdering#isAbleToManageView(org.eclipse.gmf.runtime.notation.View)
     */
    public final boolean isAbleToManageView(final View view) {
        if (view instanceof Edge) {
            return isAbleToManageEdge((Edge) view);
        }
        return false;
    }

    /**
     * Return <code>true</code> if this {@link ViewOrdering} is able to manage
     * the specified {@link Edge}.
     * 
     * @param edge
     *            the edge to check.
     * @return <code>true</code> if this {@link ViewOrdering} is able to manage
     *         the specified {@link Edge}.
     */
    public abstract boolean isAbleToManageEdge(Edge edge);

}
