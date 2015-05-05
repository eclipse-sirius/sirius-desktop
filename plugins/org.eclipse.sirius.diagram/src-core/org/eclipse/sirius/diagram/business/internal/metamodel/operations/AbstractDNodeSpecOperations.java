/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.metamodel.operations;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.sirius.common.tools.api.util.EObjectCouple;
import org.eclipse.sirius.common.tools.api.util.RefreshIdsHolder;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.AbstractNodeMappingSpecOperations;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * Common operations for {@link AbstractDNode} spec classes.
 * 
 * @author <a href="maxime.porhel@obeo.fr">Maxime Porhel</a>
 */
public final class AbstractDNodeSpecOperations {

    /**
     * Avoid instantiation.
     */
    private AbstractDNodeSpecOperations() {
        // empty.
    }

    /**
     * Refresh the border nodes of the given {@link AbstractDNode}.
     * 
     * @param adn
     *            the {@link AbstractDNode} whose bordered nodes need to be
     *            refreshed.
     */
    public static void refreshBorderNodes(AbstractDNode adn) {
        /*
         * Update bordering nodes
         */
        final Collection<EObjectCouple> managedBorderingNodes = new HashSet<EObjectCouple>();
        for (DNode n : adn.getOwnedBorderedNodes()) {
            n.refresh();
            managedBorderingNodes.add(new EObjectCouple(n.getTarget(), n.getActualMapping(), RefreshIdsHolder.getOrCreateHolder(adn.getParentDiagram())));
        }
        /*
         * create the non managed bordering nodes
         */
        RepresentationElementMapping mapping = adn.getMapping();
        if (mapping instanceof AbstractNodeMapping) {
            AbstractNodeMappingSpecOperations.createBorderingNodes((AbstractNodeMapping) mapping, adn.getTarget(), adn, managedBorderingNodes, adn.getParentDiagram());
        }
    }
}
