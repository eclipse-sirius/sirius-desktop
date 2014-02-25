/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.api.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.api.query.DNodeQuery;

/**
 * A custom ItemProvider to add the label of Node. This ItemProvider "simulates"
 * a new child for Node that have label on border.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class DNodeLabelItemProvider extends AbstractDDiagramElementLabelItemProvider {

    /**
     * Default constructor.
     * 
     * @param adapterFactory
     *            The factory is used as a key so that we always know which
     *            factory created this adapter.
     * @param parentDNode
     *            The parent of the label
     */
    public DNodeLabelItemProvider(AdapterFactory adapterFactory, DNode parentDNode) {
        super(adapterFactory, parentDNode);
    }

    /**
     * <p>
     * Indicates if the given candidateDNode should have a DNodeLabelItem has
     * children.
     * </p>
     * <p>
     * The given candidateDNode should have a DNodeLabelItem has children if all
     * following predicates are verified:
     * <ul>
     * <li>It is a bordered node</li>
     * <li>It has a non-null and non-empty name</li>
     * </ul>
     * </p>
     * 
     * @param node
     *            the DNode to determine if it can have a DNodeLabelItem has
     *            children
     * @return true if the given candidate DNode should have a DNodeLabelItem
     *         has children
     */
    private static boolean hasRelevantDNodelabelItem(AbstractDNode node) {
        boolean isRelevant = false;
        if (node instanceof DNode) {
            DNodeQuery candidateNodeQuery = new DNodeQuery((DNode) node);
            // First of all, it must have a label on border
            isRelevant = candidateNodeQuery.hasLabelOnBorder();
            if (isRelevant) {
                // And a non-null name
                isRelevant = candidateNodeQuery.hasNonEmptyNameDefinition();
            }
        }
        return isRelevant;
    }

    /**
     * Tests whether a diagram element should have a label item as children.
     * 
     * @param dDiagramElement
     *            he element to test.
     * @return <code>true</code> if the diagram element should have a label item
     *         as children.
     */
    public static boolean hasRelevantLabelItem(DDiagramElement dDiagramElement) {
        if (dDiagramElement instanceof AbstractDNode) {
            return DNodeLabelItemProvider.hasRelevantDNodelabelItem((AbstractDNode) dDiagramElement);
        }
        return false;
    }
}
