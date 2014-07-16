/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.dialect.identifier;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.identifier.AbstractRepresentationElementIdentifier;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.diagram.AbstractDNode;

/**
 * Type that identifies one node.
 * 
 * @author ymortier
 */
public class NodeIdentifier extends AbstractRepresentationElementIdentifier implements NodeContainerIdentifier {

    /** The semantic element. */
    private EObject semantic;

    /** The name of the mapping. */
    private String mappingName;

    /** The parent node. */
    private NodeContainerIdentifier parent;

    /**
     * Creates a new node identifier from node.
     * 
     * @param node
     *            the reference node
     * @param parent
     *            the parent identifier
     */
    public NodeIdentifier(final AbstractDNode node, final NodeContainerIdentifier parent) {
        this(node.getTarget(), node.getDiagramElementMapping().getName(), parent);
    }

    /**
     * Creates a new node identifier.
     * 
     * @param semantic
     *            the semantic object.
     * @param mappingName
     *            the name of the mapping.
     * @param parent
     *            the parent identifier.
     */
    public NodeIdentifier(final EObject semantic, final String mappingName, final NodeContainerIdentifier parent) {
        if (semantic == null || mappingName == null) {
            throw new IllegalArgumentException("semantic & mappingName are mandatories");
        }
        this.semantic = semantic;
        this.mappingName = mappingName;
        this.parent = parent;
    }

    // CHECKSTYLE:OFF
    /**
     * {@inheritDoc}
     * 
     * @see com.thalesgroup.mde.dae.advance.specific.diagram.ThalesElementState.StateIdentifier#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        boolean result = true;
        result = result && obj instanceof NodeIdentifier;
        result = result && EqualityHelper.areEquals(((NodeIdentifier) obj).semantic, this.semantic);
        result = result && ((NodeIdentifier) obj).mappingName.equals(this.mappingName);
        if (result) {
            final NodeContainerIdentifier parent1 = this.parent;
            final NodeContainerIdentifier parent2 = ((NodeIdentifier) obj).parent;
            result = result && (parent1 == null && parent2 == null) || (parent2 != null && parent1.equals(parent2));
        }
        return result;
    }

    // CHECKSTYLE:ON

    /**
     * {@inheritDoc}
     * 
     * @see com.thalesgroup.mde.dae.advance.specific.diagram.ThalesElementState.StateIdentifier#uniqueID()
     */
    @Override
    public int uniqueID() {
        final int prime = 31;
        int result = 1;
        result = prime * result + semantic.hashCode();
        result = prime * result + mappingName.hashCode();
        result = prime * result + (parent == null ? 0 : parent.hashCode());
        return result;
    }
}
