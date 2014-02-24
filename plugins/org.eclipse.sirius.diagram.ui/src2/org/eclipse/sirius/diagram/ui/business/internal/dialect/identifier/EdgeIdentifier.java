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
import org.eclipse.sirius.diagram.DEdge;

/**
 * An edge identifier.
 * 
 * @author mchauvin
 */
public class EdgeIdentifier extends AbstractRepresentationElementIdentifier {

    private EObject semantic;

    private String mappingName;

    private NodeIdentifier source;

    private NodeIdentifier target;

    /**
     * Construct a new edge identifier from an edge.
     * 
     * @param edge
     *            the reference edge
     * @param source
     *            the source identifier
     * @param target
     *            the target identifier
     */
    public EdgeIdentifier(final DEdge edge, final NodeIdentifier source, final NodeIdentifier target) {
        this(edge.getTarget(), edge.getMapping().getName(), source, target);
    }

    /**
     * Construct a new edge identifier.
     * 
     * @param semantic
     *            the semantic element
     * @param mappingName
     *            the mapping name
     * @param source
     *            the source
     * @param target
     *            the target
     */
    public EdgeIdentifier(final EObject semantic, final String mappingName, final NodeIdentifier source, final NodeIdentifier target) {
        if (semantic == null || mappingName == null || source == null || target == null) {
            throw new IllegalArgumentException("semantic, mappingName, source and target are mandatories");
        }
        this.semantic = semantic;
        this.mappingName = mappingName;
        this.source = source;
        this.target = target;
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
        result = prime * result + ((mappingName == null) ? 0 : mappingName.hashCode());
        result = prime * result + ((semantic == null) ? 0 : semantic.hashCode());
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((target == null) ? 0 : target.hashCode());

        return result;
    }

    /*
     * hash code is defined in super class
     */
    // CHECKSTYLE:OFF
    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EdgeIdentifier other = (EdgeIdentifier) obj;
        if (mappingName == null) {
            if (other.mappingName != null) {
                return false;
            }
        } else if (!mappingName.equals(other.mappingName)) {
            return false;
        }
        if (semantic == null) {
            if (other.semantic != null) {
                return false;
            }
        } else if (!EqualityHelper.areEquals(semantic, other.semantic)) {
            return false;
        }
        if (source == null) {
            if (other.source != null) {
                return false;
            }
        } else if (!source.equals(other.source)) {
            return false;
        }
        if (target == null) {
            if (other.target != null) {
                return false;
            }
        } else if (!target.equals(other.target)) {
            return false;
        }
        return true;
    }
    // CHECKSTYLE:ON
}
