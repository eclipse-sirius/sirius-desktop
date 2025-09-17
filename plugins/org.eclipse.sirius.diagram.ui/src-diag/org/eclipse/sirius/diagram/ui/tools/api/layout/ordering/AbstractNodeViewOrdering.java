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

import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

/**
 * This class is able to sort a list of {@link Node}.
 * 
 * @author ymortier
 */
public abstract class AbstractNodeViewOrdering extends AbstractViewOrdering {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.AbstractViewOrdering#sortViews(java.util.List)
     */
    @Override
    protected final List<View> sortViews(final List<View> views) {
        Collections.sort(views, new NodeComparator());
        return views;
    }

    /**
     * Compare two {@link Node}s. It returns a positive number if
     * <code>node1</code> is greater than <code>node2</code>, a negative number
     * if <code>node1</code> is lesser than <code>node2</code> or <code>0</code>
     * if <code>node1</code> equals <code>node2</code>.
     * 
     * 
     * @param node1
     *            the first node.
     * @param node2
     *            the second node.
     * @return a positive number if <code>node1</code> is greater than
     *         <code>node2</code>, a negative number if <code>node1</code> is
     *         lesser than <code>node2</code> or <code>0</code> if
     *         <code>node1</code> equals <code>node2</code>.
     */
    public abstract int compare(Node node1, Node node2);

    /**
     * The comparator of {@link Node}s.
     * 
     * @author ymortier
     */
    private final class NodeComparator implements Comparator<View> {

        /**
         * {@inheritDoc}
         * 
         * @see java.util.Comparator#compare(T, T)
         */
        public int compare(final View view0, final View view1) {
            int result;

            final Node node0 = (Node) view0;
            final Node node1 = (Node) view1;

            if (AbstractNodeViewOrdering.this.isAbleToManageNode(node0)) {
                if (!AbstractNodeViewOrdering.this.isAbleToManageNode(node1)) {
                    result = -1;
                } else {
                    result = AbstractNodeViewOrdering.this.compare(node0, node1);
                }
            } else {
                if (AbstractNodeViewOrdering.this.isAbleToManageNode(node1)) {
                    result = 1;
                } else {
                    result = 0;
                }
            }
            return result;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.ordering.GridViewOrdering#isAbleToManageView(org.eclipse.gmf.runtime.notation.View)
     */
    public final boolean isAbleToManageView(final View view) {
        if (view instanceof Node) {
            return isAbleToManageNode((Node) view);
        }
        return false;
    }

    /**
     * Return <code>true</code> if this {@link ViewOrdering} is able to manage
     * the specified {@link Node}.
     * 
     * @param node
     *            the node to check.
     * @return <code>true</code> if this {@link ViewOrdering} is able to manage
     *         the specified {@link Node}.
     */
    public abstract boolean isAbleToManageNode(Node node);

}
