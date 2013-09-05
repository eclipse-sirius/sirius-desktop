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

import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.LineContainer;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;

/**
 * This class represente a candidate for a DLine, a candidate is a "possible"
 * DLine which has not been confirmed yet by validation and preconditions.
 * 
 * @author cbrun
 * 
 */
public class DLineCandidate {
    private final LineContainer viewContainer;

    private final EObject semantic;

    private final LineMapping mapping;

    /**
     * The original element from which the candidate has been created. May be
     * null if no element has been used.
     */
    private DLine element;

	private final URI semanticURI;

	private final URI viewContainerURI;

	private int hashCode;

    /**
     * Create a new candidate.
     * 
     * @param mapping
     *            the line mapping.
     * @param semanticElement
     *            the target semantic element.
     * @param viewContainer
     *            the view container.
     */
    public DLineCandidate(final LineMapping mapping, final EObject semanticElement, final LineContainer viewContainer) {
        this.mapping = mapping;
        this.semantic = semanticElement;
        this.viewContainer = viewContainer;
        this.semanticURI = semantic != null ? EcoreUtil.getURI(semantic) : null;
        this.viewContainerURI = viewContainer != null ? EcoreUtil.getURI(viewContainer) : null;
        this.hashCode = computeHashCode();
    }

    /**
     * Create a new candidate from a diagram element.
     * 
     * @param tableElement
     *            an existing diagram element.
     */
    public DLineCandidate(final DLine tableElement) {
    	this(tableElement.getOriginMapping(), tableElement.getTarget(), (LineContainer) tableElement.eContainer());
        this.element = tableElement;
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
    public DLine getOriginalElement() {
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
	    return KeyCache.DEFAULT.getKey((mapping != null ? mapping.getName() : "") + semanticURI + viewContainerURI);
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
        if (result == null && !(obj instanceof DLineCandidate))
            result = false;
        final DLineCandidate other = (DLineCandidate) obj;
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
        if (result == null && viewContainer == null) {
            if (other.viewContainer != null)
                result = false;
        } else if (result == null && !viewContainerURI.equals(other.viewContainerURI))
            result = false;
        if (result == null)
            result = true;
        return result;
    }

    public LineMapping getMapping() {
        return this.mapping;
    }

    public EObject getSemantic() {
        return this.semantic;
    }

    public LineContainer getViewContainer() {
        return this.viewContainer;
    }
}
