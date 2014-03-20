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
package org.eclipse.sirius.diagram.ui.tools.api.layout.ordering;

import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;

/**
 * This class orders a list of {@link DEdge}s.
 * 
 * @author ymortier
 */
public abstract class AbstractViewEdgeOrdering extends AbstractEdgeViewOrdering {

    /**
     * Return the {@link EdgeTarget} that is the source or the target of
     * {@link DEdge}s to sort.
     * 
     * @return the {@link EdgeTarget} that is the source or the target of
     *         {@link DEdge}s to sort.
     */
    public EdgeTarget getEdgeTargetConnector() {
        return (EdgeTarget) this.getConnector().getElement();
    }

    /**
     * Compare two {@link DEdge}s. The return value depends on the relation
     * order of <code>vp1</code> and <code>vp2</code>. It returns a positive
     * number if <code>vp1</code> is greater than <code>vp2</code>, a negative
     * number if <code>vp1</code> is lesser that <code>vp2</code> or
     * <code>0</code> if <code>vp1</code> equals <code>vp2</code>.
     * 
     * @param vp1
     *            the first element to compare.
     * @param vp2
     *            the second element to compare.
     * @return a positive number if <code>vp1</code> is greater than
     *         <code>vp2</code>, a negative number if <code>vp1</code> is lesser
     *         that <code>vp2</code> or <code>0</code> if <code>vp1</code>
     *         equals <code>vp2</code>.
     */
    public abstract int compare(DEdge vp1, DEdge vp2);

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.AbstractEdgeViewOrdering#compare(org.eclipse.gmf.runtime.notation.Edge,
     *      org.eclipse.gmf.runtime.notation.Edge)
     */
    @Override
    public int compare(final Edge edge1, final Edge edge2) {
        final DEdge viewEdge1 = (DEdge) edge1.getElement();
        final DEdge viewEdge2 = (DEdge) edge2.getElement();
        return AbstractViewEdgeOrdering.this.compare(viewEdge1, viewEdge2);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.AbstractEdgeViewOrdering#isAbleToManageEdge(org.eclipse.gmf.runtime.notation.Edge)
     */
    @Override
    public final boolean isAbleToManageEdge(final Edge edge) {
        if (edge.getElement() instanceof DEdge) {
            return this.isAbleToManageViewEdge((DEdge) edge.getElement());
        }
        return false;
    }

    /**
     * Return <code>true</code> if this
     * {@link org.eclipse.sirius.diagram.business.api.layout.ordering.ViewOrdering}
     * is able to manage the specified {@link DEdge}.
     * 
     * @param viewEdge
     *            the view edge to check.
     * @return <code>true</code> if this
     *         {@link org.eclipse.sirius.diagram.business.api.layout.ordering.ViewOrdering}
     *         is able to manage the specified {@link DEdge}.
     */
    public abstract boolean isAbleToManageViewEdge(DEdge viewEdge);

}
