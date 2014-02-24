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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * This class orders a list of {@link DSemanticDecorator}s that represent an
 * edge.
 * 
 * @author ymortier
 */
public abstract class AbstractEdgeDecorateSemanticElementOrdering extends AbstractViewEdgeOrdering {

    /**
     * Return the semantic element that is the source or the target of the edges
     * to sort.
     * 
     * @return the semantic element that is the source or the target of the
     *         edges to sort.
     */
    public EObject getSemanticElementConnector() {
        return ((DSemanticDecorator) this.getEdgeTargetConnector()).getTarget();
    }

    /**
     * Compare two {@link EObject}s. The return value depends on the relation
     * order of <code>eObject1</code> and <code>eObject2</code>. It returns a
     * positive number if <code>eObject1</code> is greater than
     * <code>eObject2</code>, a negative number if <code>eObject1</code> is
     * lesser that <code>eObject2</code> or <code>0</code> if
     * <code>eObject1</code> equals <code>eObject2</code>.
     * 
     * @param eObject1
     *            the first element to compare.
     * @param eObject2
     *            the second element to compare.
     * @return a positive number if <code>eObject1</code> is greater than
     *         <code>eObject2</code>, a negative number if <code>eObject1</code>
     *         is lesser that <code>eObject2</code> or <code>0</code> if
     *         <code>eObject1</code> equals <code>eObject2</code>.
     */
    public abstract int compare(EObject eObject1, EObject eObject2);

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.AbstractViewEdgeOrdering#compare(org.eclipse.sirius.diagram.DEdge,
     *      org.eclipse.sirius.diagram.DEdge)
     */
    @Override
    public int compare(final DEdge vp1, final DEdge vp2) {
        final DSemanticDecorator dc1 = vp1;
        final DSemanticDecorator dc2 = vp2;
        return compare(dc1.getTarget(), dc2.getTarget());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.AbstractViewEdgeOrdering#isAbleToManageViewEdge(org.eclipse.sirius.diagram.DEdge)
     */
    @Override
    public final boolean isAbleToManageViewEdge(final DEdge viewEdge) {
        return isAbleToManageSemanticElement(viewEdge.getTarget());
    }

    /**
     * Return <code>true</code> if this
     * {@link org.eclipse.sirius.diagram.business.api.layout.ordering.ViewOrdering}
     * is able to manage the specified semantic element.
     * 
     * @param semanticElement
     *            the semantic element to check.
     * @return <code>true</code> if this
     *         {@link org.eclipse.sirius.diagram.business.api.layout.ordering.ViewOrdering}
     *         is able to manage the specified semantic element.
     */
    public abstract boolean isAbleToManageSemanticElement(EObject semanticElement);

}
