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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

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

    private final URI semanticURI;

    private final int hashCode;

    /**
     * Create a new candidate.
     * 
     * @param mapping
     *            the column mapping.
     * @param semanticElement
     *            the target semantic element.
     */
    public DTargetColumnCandidate(final ColumnMapping mapping, final EObject semanticElement) {
        this.mapping = mapping;
        this.semantic = semanticElement;
        this.semanticURI = semantic != null ? EcoreUtil.getURI(semantic) : null;
        this.hashCode = computeHashCode();
    }

    /**
     * Create a new candidate from a diagram element.
     * 
     * @param tableElement
     *            an existing diagram element.
     */
    public DTargetColumnCandidate(final DTargetColumn tableElement) {
        this.mapping = tableElement.getOriginMapping();
        this.semantic = tableElement.getTarget();
        this.element = tableElement;
        this.semanticURI = semantic != null ? EcoreUtil.getURI(semantic) : null;
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
        return KeyCache.DEFAULT.getKey((mapping != null ? mapping.getName() : "") + semanticURI);
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
        if (result == null && mapping == null) {
            if (other.mapping != null)
                result = false;
        } else if (result == null && !mapping.getName().equals(other.mapping.getName()))
            result = false;
        if (result == null && semantic == null) {
            if (other.semantic != null)
                result = false;
        } else if (result == null && !semanticURI.equals(other.semanticURI))
            result = false;
        if (result == null)
            result = true;
        return result;
    }

    public ColumnMapping getMapping() {
        return this.mapping;
    }

    public EObject getSemantic() {
        return this.semantic;
    }
}
