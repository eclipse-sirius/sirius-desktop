/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.helper.refresh;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.tools.internal.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * This class is a filter criteria for edges.
 * 
 * @author ymortier
 */
public class EdgeFilter {

    // FIXED the criteria of this filter is incomplete.
    // To add : - the edgeMapping : In fact, the criteria is used in a
    // mapping context.
    //
    // FIXME : a mapping can produce more than one edge between two semantic
    // elements (we must add a criteria).

    /** The semantic source. */
    private EObject source;

    /** The semantic target. */
    private EObject target;

    /** another criteria. */
    private EObject semTarget;

    /** The source EdgeTarget. */
    private EdgeTarget edgeSource;

    /** The target EdgeTarget. */
    private EdgeTarget edgeTarget;

    /** The mapping (optional). */
    private EdgeMapping mapping;

    /**
     * Create a new EdgeFilter.
     * 
     * @param source
     *            the semantic source.
     * @param target
     *            the semantic target.
     * @param semTarget
     *            : the semantic target.
     * @param edgeTarget
     *            : the target of the edge.
     * @param edgeSource
     *            : the source of the edge.
     * @param edgeMapping
     *            the mapping.
     */
    public EdgeFilter(final EObject source, final EObject target, final EObject semTarget, final EdgeTarget edgeSource, final EdgeTarget edgeTarget, final EdgeMapping edgeMapping) {
        if (source == null || target == null || edgeSource == null || edgeTarget == null) {
            throw new IllegalArgumentException(Messages.EdgeFilter_errorMsg);
        }
        this.source = source;
        this.target = target;
        this.semTarget = semTarget;
        this.edgeSource = edgeSource;
        this.edgeTarget = edgeTarget;
        this.mapping = edgeMapping;
    }

    /**
     * Create a new {@link EdgeFilter} with the specified edge.
     * 
     * @param edge
     *            the edge.
     */
    public EdgeFilter(final DEdge edge) {
        this.source = ((DSemanticDecorator) edge.getSourceNode()).getTarget();
        this.target = ((DSemanticDecorator) edge.getTargetNode()).getTarget();
        this.semTarget = edge.getTarget();
        this.edgeSource = edge.getSourceNode();
        this.edgeTarget = edge.getTargetNode();
        this.mapping = new IEdgeMappingQuery(edge.getActualMapping()).getEdgeMapping().get();
    }

    /**
     * Return the source.
     * 
     * @return the source.
     */
    public EObject getSource() {
        return source;
    }

    /**
     * Return the target.
     * 
     * @return the target.
     */
    public EObject getTarget() {
        return target;
    }

    /**
     * Return the semantic target.
     * 
     * @return the semantic target.
     */
    public EObject getSemTarget() {
        return semTarget;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        boolean result = false;
        if (obj == this) {
            result = true;
        }
        if (!result && obj instanceof EdgeFilter) {
            final EdgeFilter edgeFilter = (EdgeFilter) obj;
            if ((semTarget == null && edgeFilter.semTarget != null) || (semTarget != null && edgeFilter.semTarget == null)) {
                SiriusPlugin.getDefault().error(Messages.EdgeFilter_semanticIsNullErrorMsg, null);
            }
            result = getHashString(this).equals(getHashString(edgeFilter));
        }
        return result;
    }

    private String getHashString(final EdgeFilter obj) {
        String hashString = EcoreUtil.getURI(obj.semTarget).toString();
        hashString += EcoreUtil.getURI(obj.target).toString();
        hashString += EcoreUtil.getURI(obj.source).toString();
        hashString += EcoreUtil.getURI(obj.edgeSource).toString();
        hashString += EcoreUtil.getURI(obj.edgeTarget).toString();
        hashString += EcoreUtil.getURI(obj.mapping).toString();
        return hashString;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return Integer.valueOf(hashCode()).toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return getHashString(this).hashCode();
    }

}
