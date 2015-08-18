/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.experimental.sync;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.util.RefreshIdsHolder;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.EdgeMappingImportWrapper;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.cache.KeyCache;

/**
 * This class represents a candidate for a DEdge, a candidate is a "possible"
 * DEdge which has not been confirmed yet by validation and preconditions.
 * 
 * @author cbrun
 * 
 */
public class DEdgeCandidate {

    private final EdgeTarget sourceView;

    private final EdgeTarget targetView;

    private IEdgeMapping mapping;

    private final EObject semantic;

    private DEdge element;

    private final EdgeMapping rootMapping;

    private final int hashCode;

    private RefreshIdsHolder factory;

    /**
     * Create a new EdgeCandidate from an existing element.
     * 
     * @param element
     *            a DEdge.
     * @param factory
     *            the factory.
     */
    public DEdgeCandidate(final DEdge element, RefreshIdsHolder factory) {
        this.element = element;
        this.semantic = element.getTarget();
        this.mapping = element.getActualMapping();
        this.sourceView = element.getSourceNode();
        this.targetView = element.getTargetNode();
        this.rootMapping = getRootMapping(this.mapping);
        this.factory = factory;
        this.hashCode = computeHashCode();
    }

    /**
     * Create a new EdgeCandidate.
     * 
     * @param mapping
     *            the edge mapping.
     * @param semantic
     *            the target semantic element.
     * @param sourceView
     *            the source view.
     * @param targetView
     *            the target view.
     * @param factory
     *            the factory.
     */
    public DEdgeCandidate(final EdgeMapping mapping, final EObject semantic, final EdgeTarget sourceView, final EdgeTarget targetView, RefreshIdsHolder factory) {
        if (mapping instanceof EdgeMappingImportWrapper) {
            this.mapping = ((EdgeMappingImportWrapper) mapping).getWrappedEdgeMappingImport();
        } else {
            this.mapping = mapping;
        }
        this.semantic = semantic;
        this.sourceView = sourceView;
        this.targetView = targetView;
        this.rootMapping = getRootMapping(this.mapping);
        this.factory = factory;
        this.hashCode = computeHashCode();
    }

    /**
     * Check if this canditate is valid or not.
     * 
     * @return <code>true</code> if it is invalid. <code>false</code> otherwise.
     */
    public boolean isInvalid() {
        Option<DDiagram> sourceDiagram = new EObjectQuery(sourceView).getParentDiagram();
        Option<DDiagram> targetDiagram = new EObjectQuery(targetView).getParentDiagram();
        return !sourceDiagram.some() || !targetDiagram.some();
    }

    /**
     * return the sourceView.
     * 
     * @return the sourceView.
     */
    public EdgeTarget getSourceView() {
        return sourceView;
    }

    /**
     * return the targetView.
     * 
     * @return the targetView
     */
    public EdgeTarget getTargetView() {
        return targetView;
    }

    /**
     * return the mapping.
     * 
     * @return the mapping
     */
    public IEdgeMapping getMapping() {
        return mapping;
    }

    /**
     * return the semantic.
     * 
     * @return the semantic
     */
    public EObject getSemantic() {
        return semantic;
    }

    /**
     * return the source element if the candidate has been created from an
     * element.
     * 
     * @return the source element if the candidate has been created from an
     *         element.
     */
    public DEdge getEdge() {
        return element;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return this.hashCode;
    }

    private int computeHashCode() {
        final int[] parts = new int[4];
        parts[0] = (rootMapping == null) ? 0 : rootMapping.hashCode();
        parts[1] = (semantic == null) ? 0 : getSemanticURI().hashCode();
        parts[2] = (sourceView == null) ? 0 : getSourceViewURI();
        parts[3] = (targetView == null) ? 0 : getTargetViewURI();
        final String sep = "/"; //$NON-NLS-1$
        return KeyCache.DEFAULT.getKey(parts[0] + sep + parts[1] + sep + parts[2] + sep + parts[3]);
    }

    private Integer getTargetViewURI() {
        return this.factory.getOrCreateID(targetView);
    }

    private Integer getSourceViewURI() {
        return this.factory.getOrCreateID(sourceView);
    }

    private Integer getSemanticURI() {
        return this.factory.getOrCreateID(semantic);
    }

    /**
     * {@inheritDoc}
     */
    // CHECKSTYLE:OFF
    @Override
    // Eclipse generated
    // Beware of use of get*URI() methods
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof DEdgeCandidate)) {
            return false;
        }
        DEdgeCandidate other = (DEdgeCandidate) obj;
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
        } else if (!getSemanticURI().equals(other.getSemanticURI())) {
            return false;
        }
        if (sourceView == null) {
            if (other.sourceView != null) {
                return false;
            }
        } else if (!getSourceViewURI().equals(other.getSourceViewURI())) {
            return false;
        }
        if (targetView == null) {
            if (other.targetView != null) {
                return false;
            }
        } else if (!getTargetViewURI().equals(other.getTargetViewURI())) {
            return false;
        }
        return true;
    }

    // CHECKSTYLE:ON

    private EdgeMapping getRootMapping(final IEdgeMapping iEdgeMapping) {
        IEdgeMapping result = iEdgeMapping;
        if (iEdgeMapping != null) {
            while (result instanceof EdgeMappingImport || result instanceof EdgeMappingImportWrapper) {
                if (result instanceof EdgeMappingImport) {
                    result = ((EdgeMappingImport) result).getImportedMapping();
                } else if (result instanceof EdgeMappingImportWrapper) {
                    result = ((EdgeMappingImportWrapper) result).getImportedMapping();
                }
            }
        }
        if (result instanceof EdgeMapping) {
            return (EdgeMapping) result;
        } else {
            return null;
        }
    }
}
