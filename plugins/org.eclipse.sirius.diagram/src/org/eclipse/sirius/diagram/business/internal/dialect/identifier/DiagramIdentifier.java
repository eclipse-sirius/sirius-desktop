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
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;

/**
 * Identifies a diagram.
 * 
 * @author ymortier
 */
public class DiagramIdentifier extends AbstractRepresentationElementIdentifier implements NodeContainerIdentifier {

    /** The semantic element. */
    private EObject semantic;

    /** The name of the description. */
    private String descriptionName;

    /** Index of the diagram. */
    private int index;

    /**
     * Construct a new diagram identifier.
     * 
     * @param semantic
     *            the semantic target element
     * @param descriptionName
     *            the description name
     * @param index
     *            an index identifier
     */
    public DiagramIdentifier(final EObject semantic, final String descriptionName, final int index) {
        if (semantic == null || descriptionName == null) {
            throw new IllegalArgumentException("semantic & descriptionName are mandatories");
        }
        this.semantic = semantic;
        this.descriptionName = descriptionName;
        this.index = index;
    }

    /**
     * Construct a new diagram identifier from a semantic diagram.
     * 
     * @param diagram
     *            the reference semantic diagram
     */
    public DiagramIdentifier(final DSemanticDiagram diagram) {
        this(diagram.getTarget(), diagram.getDescription().getName(), DiagramIdentifier.getId(diagram));
    }

    /**
     * If you need to override the default generated index.
     * 
     * @param index
     *            the index
     */
    public void setIndex(final int index) {
        this.index = index;
    }

    private static int getId(final DDiagram diagram) {
        return diagram.getName() == null ? -1 : diagram.getName().hashCode();
    }

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
        result = prime * result + descriptionName.hashCode();
        result = prime * result + index;
        return result;
    }

    /*
     * hash code is defined in super class
     */
    // CHECKSTYLE:OFF
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DiagramIdentifier other = (DiagramIdentifier) obj;
        if (descriptionName == null) {
            if (other.descriptionName != null) {
                return false;
            }
        } else if (!descriptionName.equals(other.descriptionName)) {
            return false;
        }
        if (semantic == null) {
            if (other.semantic != null) {
                return false;
            }
        } else if (!EqualityHelper.areEquals(semantic, other.semantic)) {
            return false;
        }
        if (index != other.index) {
            return false;
        }
        return true;
    }
    // CHECKSTYLE:ON
}
