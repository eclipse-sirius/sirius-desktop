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

    private int hashcode;

    private RefreshIdsHolder ids;

    /**
     * Create a new candidate.
     * 
     * @param mapping
     *            the line mapping.
     * @param semanticElement
     *            the target semantic element.
     * @param viewContainer
     *            the view container.
     * @param ids
     *            the holder of the refresh ids.
     */
    public DLineCandidate(final LineMapping mapping, final EObject semanticElement, final LineContainer viewContainer, RefreshIdsHolder ids) {
        this.mapping = mapping;
        this.semantic = semanticElement;
        this.viewContainer = viewContainer;
        this.ids = ids;
        this.hashcode = computeHashCode();
    }

    /**
     * Create a new candidate from a diagram element.
     * 
     * @param tableElement
     *            an existing diagram element.
     * @param ids
     *            the holder of the refresh ids.
     */
    public DLineCandidate(final DLine tableElement, RefreshIdsHolder ids) {
        this(tableElement.getOriginMapping(), tableElement.getTarget(), (LineContainer) tableElement.eContainer(), ids);
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
        return this.hashcode;
    }

    private int computeHashCode() {
        final int[] parts = new int[3];
        parts[0] = (mapping == null) ? 0 : getMappingID();
        parts[1] = (semantic == null) ? 0 : getSemanticID();
        parts[2] = (viewContainer == null) ? 0 : getViewContainerID();
        final String sep = "/"; //$NON-NLS-1$
        return KeyCache.DEFAULT.getKey(parts[0] + sep + parts[1] + sep + parts[2]);
    }

    // CHECKSTYLE:OFF
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof DLineCandidate)) {
            return false;
        }
        DLineCandidate other = (DLineCandidate) obj;
        /*
         * The semantic element is less likely to be the same as the mapping, we
         * should check for this first.
         */
        if (semantic == null) {
            if (other.semantic != null) {
                return false;
            }
        } else if (!getSemanticID().equals(other.getSemanticID())) {
            return false;
        }
        if (mapping == null) {
            if (other.mapping != null) {
                return false;
            }
        } else if (!mapping.equals(other.mapping)) {
            return false;
        }
        if (viewContainer == null) {
            if (other.viewContainer != null) {
                return false;
            }
        } else if (!getViewContainerID().equals(other.getViewContainerID())) {
            return false;
        }
        return true;
    }

    // CHECKSTYLE:ON

    /**
     * return the view container URI.
     * 
     * @return the view container URI.
     */
    private Integer getViewContainerID() {
        if (viewContainer == null) {
            return -1;
        }
        return this.ids.getOrCreateID(viewContainer);
    }

    /**
     * return the semantic id.
     * 
     * @return the semantic ID
     */
    private Integer getSemanticID() {
        if (semantic == null) {
            return -1;
        }
        return this.ids.getOrCreateID(semantic);
    }

    private Integer getMappingID() {
        return this.ids.getOrCreateID(mapping);
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
