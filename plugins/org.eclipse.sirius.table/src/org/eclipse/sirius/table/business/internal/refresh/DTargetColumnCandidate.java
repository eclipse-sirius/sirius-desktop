/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.business.internal.refresh;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.util.RefreshIdsHolder;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;

/**
 * This class represente a candidate for a DColumn, a candidate is a "possible"
 * DColumn which has not been confirmed yet by validation and preconditions.
 * 
 * @author cbrun
 * 
 */
public class DTargetColumnCandidate {

    private final EObject semantic;

    private final ColumnMapping mapping;

    /**
     * The original element from which the candidate has been created. May be
     * null if no element has been used.
     */
    private DTargetColumn element;

    private final int hashCode;

    private RefreshIdsHolder ids;

    /**
     * Create a new candidate.
     * 
     * @param mapping
     *            the column mapping.
     * @param semanticElement
     *            the target semantic element.
     * @param ids
     *            the holder of the refresh ids.
     */
    public DTargetColumnCandidate(final ColumnMapping mapping, final EObject semanticElement, RefreshIdsHolder ids) {
        this.mapping = mapping;
        this.semantic = semanticElement;
        this.ids = ids;
        this.hashCode = computeHashCode();
    }

    /**
     * Create a new candidate from a diagram element.
     * 
     * @param tableElement
     *            an existing diagram element.
     * @param ids
     *            the holder of the refresh ids.
     */
    public DTargetColumnCandidate(final DTargetColumn tableElement, RefreshIdsHolder ids) {
        this.mapping = tableElement.getOriginMapping();
        this.semantic = tableElement.getTarget();
        this.element = tableElement;
        this.ids = ids;
        this.hashCode = computeHashCode();
    }

    /**
     * Tells wether this candidate has been created from an existing element or
     * not.
     * 
     * @return true if the candidate has been created from an existing element.
     */
    public boolean comesFromTableElement() {
        return getOriginalElement() != null;
    }

    /**
     * Return the original element which has been used for the candidate
     * creation.
     * 
     * @return the original element which has been used for the candidate
     *         creation, null if no element has been used.
     */
    public DTargetColumn getOriginalElement() {
        return element;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return hashCode;
    }

    private int computeHashCode() {
        final int[] parts = new int[2];
        parts[0] = (mapping == null) ? 0 : getMappingID();
        parts[1] = (semantic == null) ? 0 : getSemanticID();
        final String sep = "/"; //$NON-NLS-1$
        return KeyCache.DEFAULT.getKey(parts[0] + sep + parts[1]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        Boolean result = null;
        if (this == obj)
            result = true;
        if (result == null && obj == null)
            result = false;
        if (result == null && !(obj instanceof DTargetColumnCandidate))
            result = false;
        final DTargetColumnCandidate other = (DTargetColumnCandidate) obj;
        if (result == null && semantic == null) {
            if (other.semantic != null)
                result = false;
        } else if (result == null && !getSemanticID().equals(other.getSemanticID()))
            result = false;
        if (result == null && mapping == null) {
            if (other.mapping != null)
                result = false;
        } else if (result == null && !mapping.equals(other.mapping))
            result = false;
        if (result == null)
            result = true;
        return result;
    }

    /**
     * return the semantic id.
     * 
     * @return the semantic ID
     */
    private Integer getSemanticID() {
        return this.ids.getOrCreateID(semantic);
    }

    private Integer getMappingID() {
        return this.ids.getOrCreateID(mapping);
    }

    public ColumnMapping getMapping() {
        return this.mapping;
    }

    public EObject getSemantic() {
        return this.semantic;
    }
}
