/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.business.api.provider;

import java.text.MessageFormat;
import java.util.HashSet;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.business.api.query.DEdgeQuery;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;

/**
 * A custom ItemProvider to add the label of Edge. This ItemProvider "simulates"
 * a new child for Edge that have label.
 *
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 *
 */
public class DEdgeLabelItemProvider extends AbstractDDiagramElementLabelItemProvider {

    /**
     * Default constructor.
     *
     * @param adapterFactory
     *            The factory is used as a key so that we always know which factory created this adapter.
     * @param parentDEdge
     *            The parent of the label
     */
    public DEdgeLabelItemProvider(AdapterFactory adapterFactory, DEdge parentDEdge) {
        super(adapterFactory, parentDEdge);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
     */
    @Override
    public String getText(Object object) {
        String label = ((DEdge) target).getName();
        if (label == null || label.length() == 0) {
            label = EMPTY_DDIAGRAMELEMENT_LABEL_LABEL;
        } else {
            label = MessageFormat.format(Messages.AbstractDDiagramElementLabelItemProvider_label, label);
        }
        return label;
    }

    /**
     * <p>
     * Indicates if the given candidateDNode should have a DEdgeLabelItem has
     * children.
     * </p>
     * <p>
     * The given candidateDEdge should have a DEdgeLabelItem has children if all
     * following predicates are verified:
     * <ul>
     * <li>It is a bordered node</li>
     * <li>It has a non-null and non-empty name</li>
     * </ul>
     * </p>
     *
     * @param edge
     *            the DEdge to determine if it can have a DEdgeLabelItem has
     *            children
     * @return true if the given candidate DEdge should have a DEdgeLabelItem
     *         has children
     */
    private static boolean hasRelevantDEdgelabelItem(DEdge edge) {
        boolean isRelevant = false;
        if (edge != null && edge.getOwnedStyle() != null) {
            DEdgeQuery candidateEdgeQuery = new DEdgeQuery(edge);
            isRelevant = candidateEdgeQuery.getCenterLabelStyle().some() && candidateEdgeQuery.hasNonEmptyNameDefinition();
        }
        return isRelevant;
    }

    /**
     * Tests whether a diagram element should have a label item as children.
     *
     * @param dDiagramElement
     *            he element to test.
     * @return <code>true</code> if the diagram element should have a label item
     *         as children.
     */
    public static boolean hasRelevantLabelItem(DDiagramElement dDiagramElement) {
        if (dDiagramElement instanceof DEdge) {
            return DEdgeLabelItemProvider.hasRelevantDEdgelabelItem((DEdge) dDiagramElement);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (getTarget() != null && obj instanceof DEdgeLabelItemProvider) {
            return getTarget().equals(((DEdgeLabelItemProvider) obj).getTarget());
        } else {
            return super.equals(obj);
        }
    }

    /**
     * Addition of the VISUAL_ID field so in a {@link HashSet} this can be distinguished from the
     * {@link DEdgeBeginLabelItemProvider} and the {@link DEdgeEndLabelItemProvider} of the same edge.
     * 
     * {@inheritDoc}
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return super.hashCode() + DEdgeNameEditPart.VISUAL_ID;
    }
}
