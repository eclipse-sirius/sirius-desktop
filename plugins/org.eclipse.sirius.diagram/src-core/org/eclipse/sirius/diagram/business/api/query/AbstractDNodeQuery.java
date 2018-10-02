/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.query;

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
     * Indicates if the AbtractDNode is a bordered node.
     * 
     * @return true if the AbtractDNode is a bordered node, false otherwise
     */
    public boolean isBorderedNode() {
        return DiagramPackage.eINSTANCE.getAbstractDNode_OwnedBorderedNodes().equals(node.eContainingFeature());
    }

}
