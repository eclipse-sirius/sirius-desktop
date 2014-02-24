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
package org.eclipse.sirius.business.api.query;

import org.eclipse.sirius.business.internal.experimental.sync.AbstractDNodeCandidate;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DiagramPackage;

/**
 * A class aggregating all the queries (read-only!) having a
 * {@link AbstractDNode} as a starting point.
 * 
 * @author mporhel
 * 
 */
public class AbstractDNodeQuery {

    private AbstractDNode node;

    /**
     * Create a new query.
     * 
     * @param node
     *            the element to query.
     */
    public AbstractDNodeQuery(AbstractDNode node) {
        this.node = node;
    }

    /**
     * Check if two AbstractDNode are the same.
     * 
     * @param eObj
     *            the second AbstractDNode to compare
     * @return <code>true</code> if they are equals, <code>false</code>
     *         otherwise. If the two objects are both <code>null</code> return
     *         <code>true</code>, otherwise if only one of them is null, return
     *         <code>false</code>
     */
    public boolean equalsTo(final AbstractDNode eObj) {
        boolean result = false;

        if (node == eObj) {
            result = true;
        } else if (node != null && eObj != null) {
            result = new AbstractDNodeCandidate(node).equals(new AbstractDNodeCandidate(eObj));
        }
        return result;
    }

    /**
     * Indicates if the AbtractDNode is a bordered node.
     * 
     * @return true if the AbtractDNode is a bordered node, false otherwise
     */
    public boolean isBorderedNode() {
        return DiagramPackage.eINSTANCE.getAbstractDNode_OwnedBorderedNodes().equals(node.eContainingFeature());
    }

}
