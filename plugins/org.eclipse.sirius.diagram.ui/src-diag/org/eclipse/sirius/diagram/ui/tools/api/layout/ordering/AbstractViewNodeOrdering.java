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

import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.AbstractDNode;

/**
 * This class orders a list of {@link AbstractDNode}s.
 * 
 * 
 * @author ymortier
 */
public abstract class AbstractViewNodeOrdering extends AbstractNodeViewOrdering {

    /**
     * Compare two {@link AbstractDNode}s. The return value depends on the
     * relation order of <code>vp1</code> and <code>vp2</code>. It returns a
     * positive number if <code>vp1</code> is greater than <code>vp2</code>, a
     * negative number if <code>vp1</code> is lesser that <code>vp2</code> or
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
    public abstract int compare(AbstractDNode vp1, AbstractDNode vp2);

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.AbstractNodeViewOrdering#compare(org.eclipse.gmf.runtime.notation.Node,
     *      org.eclipse.gmf.runtime.notation.Node)
     */
    @Override
    public final int compare(final Node node1, final Node node2) {
        final AbstractDNode viewNode1 = (AbstractDNode) node1.getElement();
        final AbstractDNode viewNode2 = (AbstractDNode) node2.getElement();
        return compare(viewNode1, viewNode2);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.AbstractNodeViewOrdering#isAbleToManageNode(org.eclipse.gmf.runtime.notation.Node)
     */
    @Override
    public final boolean isAbleToManageNode(final Node node) {
        if (node.getElement() instanceof AbstractDNode) {
            return isAbleToManageAbstractViewNode((AbstractDNode) node.getElement());
        }
        return false;
    }

    /**
     * Return <code>true</code> if this
     * {@link org.eclipse.sirius.diagram.business.api.layout.ordering.ViewOrdering}
     * is able to manage the specified node.
     * 
     * @param node
     *            the node to check.
     * @return <code>true</code> if this
     *         {@link org.eclipse.sirius.diagram.business.api.layout.ordering.ViewOrdering}
     *         is able to manage the specified node.
     */
    public abstract boolean isAbleToManageAbstractViewNode(AbstractDNode node);
}
