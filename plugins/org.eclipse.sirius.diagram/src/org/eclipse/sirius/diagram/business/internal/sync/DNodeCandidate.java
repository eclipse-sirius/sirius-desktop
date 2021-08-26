/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.business.internal.sync;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.util.RefreshIdsHolder;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.NodeMappingImport;
import org.eclipse.sirius.viewpoint.description.AbstractMappingImport;

/**
 * This class represents a candidate for a AbstractDNode, a candidate is a
 * "possible" AbstractDNode which has not been confirmed yet by validation and
 * preconditions.
 * 
 * @author cbrun
 * 
 */
public class DNodeCandidate {
    /**
     * Special value to indicate an invalid id.
     */
    private static final int INVALID_ID = -1;
    
    private DragAndDropTarget viewContainer;

    private EObject semantic;

    private AbstractNodeMapping mapping;

    private AbstractNodeMapping rootMapping;

    /**
     * The original element from which the candidate has been created. May be
     * null if no element has been used.
     */
    private AbstractDNode element;

    private RefreshIdsHolder ids;

    /**
     * Create a new candidate.
     * 
     * @param mapping
     *            the node mapping.
     * @param semanticElement
     *            the target semantic element.
     * @param viewContainer
     *            the view container.
     * @param ids
     *            the refresh ids holder.
     */
    public DNodeCandidate(final AbstractNodeMapping mapping, final EObject semanticElement, final DragAndDropTarget viewContainer, RefreshIdsHolder ids) {
        this.ids = ids;
        this.mapping = mapping;
        this.semantic = semanticElement;
        this.viewContainer = viewContainer;
        this.rootMapping = getRootMapping(mapping);
    }

    /**
     * Create a new candidate from a diagram element.
     * 
     * @param diagElement
     *            an existing diagram element.
     * @param ids
     *            the refresh ids holder.
     */
    public DNodeCandidate(final AbstractDNode diagElement, RefreshIdsHolder ids) {
        this.ids = ids;
        this.mapping = (AbstractNodeMapping) diagElement.getMapping();
        this.semantic = diagElement.getTarget();
        this.viewContainer = (DragAndDropTarget) diagElement.eContainer();
        this.element = diagElement;
        this.rootMapping = getRootMapping(this.mapping);
    }

    /**
     * Tells whether this candidate has been created from an existing element or
     * not.
     * 
     * @return true if the candidate has been created from an existing element.
     */
    public boolean comesFromDiagramElement() {
        return getOriginalElement() != null;
    }

    /**
     * Return the original element which has been used for the candidate
     * creation.
     * 
     * @return the original element which has been used for the candidate
     *         creation, null if no element has been used.
     */
    public AbstractDNode getOriginalElement() {
        return element;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((rootMapping == null) ? 0 : rootMapping.hashCode());
        result = prime * result + ((semantic == null) ? 0 : getSemanticID());
        result = prime * result + ((viewContainer == null) ? 0 : getViewContainerID());
        return result;
    }

    /**
     * return the view container URI.
     * 
     * @return the view container URI.
     */
    private int getViewContainerID() {
        if (semantic == null) {
            return INVALID_ID;
        }
        return this.ids.getOrCreateID(viewContainer);
    }

    /**
     * return the semantic id.
     * 
     * @return the semantic ID
     */
    private int getSemanticID() {
        if (semantic == null) {
            return INVALID_ID;
        }
        return this.ids.getOrCreateID(semantic);
    }

    // CHECKSTYLE:OFF
    @Override
    // Eclipse generated
    // Beware of us of get*URI() methods
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof DNodeCandidate)) {
            return false;
        }
        DNodeCandidate other = (DNodeCandidate) obj;
        if (rootMapping == null) {
            if (other.rootMapping != null) {
                return false;
            }
        } else if (!rootMapping.equals(other.rootMapping)) {
            return false;
        }
        if (semantic == null) {
            if (other.semantic != null) {
                return false;
            }
        } else if (getSemanticID() != other.getSemanticID()) {
            return false;
        }
        if (viewContainer == null) {
            if (other.viewContainer != null) {
                return false;
            }
        } else if (getViewContainerID() != other.getViewContainerID()) {
            return false;
        }
        return true;
    }

    // CHECKSTYLE:ON

    public AbstractNodeMapping getMapping() {
        return this.mapping;
    }

    public EObject getSemantic() {
        return this.semantic;
    }

    public DragAndDropTarget getViewContainer() {
        return this.viewContainer;
    }

    private AbstractNodeMapping getRootMapping(final AbstractNodeMapping mappingImport) {
        if (mappingImport == null) {
            return null;
        }
        AbstractNodeMapping result = mappingImport;
        while (result instanceof AbstractMappingImport) {
            if (result instanceof ContainerMappingImport) {
                result = ((ContainerMappingImport) result).getImportedMapping();
            } else if (result instanceof NodeMappingImport) {
                result = ((NodeMappingImport) result).getImportedMapping();
            }
        }
        return result;
    }
}
