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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

/**
 * A simple composite of {@link AbstractEdgeViewOrdering}s.
 * 
 * @author ymortier
 */
public class SimpleCompositeEdgeViewOrdering extends AbstractEdgeViewOrdering {

    /** The orderings. */
    private List<AbstractEdgeViewOrdering> edgeViewOrderings = new LinkedList<AbstractEdgeViewOrdering>();

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.AbstractEdgeViewOrdering#setConnector(org.eclipse.gmf.runtime.notation.View)
     */
    @Override
    public void setConnector(final View connector) {
        super.setConnector(connector);
        final Iterator<AbstractEdgeViewOrdering> iterEdgeViewOrderings = this.edgeViewOrderings.iterator();
        while (iterEdgeViewOrderings.hasNext()) {
            final AbstractEdgeViewOrdering current = iterEdgeViewOrderings.next();
            current.setConnector(connector);
        }
    }

    /**
     * Add a new {@link AbstractEdgeViewOrdering}.
     * 
     * @param edgeViewOrdering
     *            the {@link AbstractEdgeViewOrdering} to add.
     */
    public void addEdgeViewOrdering(final AbstractEdgeViewOrdering edgeViewOrdering) {
        this.edgeViewOrderings.add(edgeViewOrdering);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.AbstractEdgeViewOrdering#compare(org.eclipse.gmf.runtime.notation.Edge,
     *      org.eclipse.gmf.runtime.notation.Edge)
     */
    @Override
    public int compare(final Edge edge1, final Edge edge2) {
        final AbstractEdgeViewOrdering viewOrdering1 = this.getViewOrderingFor(edge1);
        final AbstractEdgeViewOrdering viewOrdering2 = this.getViewOrderingFor(edge2);

        int comparison;

        if (viewOrdering1 == null) {
            if (viewOrdering2 == null) {
                comparison = 0;
            } else {
                comparison = 1;
            }
        } else if (viewOrdering2 == null) {
            comparison = -1;
        } else {
            if (viewOrdering1 == viewOrdering2) {
                comparison = viewOrdering1.compare(edge1, edge2);
            } else {
                comparison = viewOrdering1.hashCode() - viewOrdering2.hashCode();
            }
        }
        return comparison;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.AbstractEdgeViewOrdering#isAbleToManageEdge(org.eclipse.gmf.runtime.notation.Edge)
     */
    @Override
    public boolean isAbleToManageEdge(final Edge edge) {
        return this.getViewOrderingFor(edge) != null;
    }

    /**
     * Return the first {@link ViewOrdering} that is able to manage the
     * specified view or <code>null</code> if no {@link ViewOrdering} is
     * availble for the view.
     * 
     * @param view
     *            the view to manage.
     * @return the first {@link ViewOrdering} that is able to manage the
     *         specified view or <code>null</code> if no {@link ViewOrdering} is
     *         availble for the view.
     */
    protected AbstractEdgeViewOrdering getViewOrderingFor(final Edge view) {
        final Iterator<AbstractEdgeViewOrdering> iterViewOrderings = this.edgeViewOrderings.iterator();
        while (iterViewOrderings.hasNext()) {
            final AbstractEdgeViewOrdering currentViewOrdering = iterViewOrderings.next();
            if (currentViewOrdering.isAbleToManageView(view)) {
                return currentViewOrdering;
            }
        }
        return null;
    }

}
