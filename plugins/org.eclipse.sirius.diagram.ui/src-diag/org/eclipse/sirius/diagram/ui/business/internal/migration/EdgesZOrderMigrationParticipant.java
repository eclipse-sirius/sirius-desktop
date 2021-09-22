/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.business.internal.migration;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * This migration participant changes the GMF edges order according to connections source/target order. The goal is to
 * keep the same visual aspect after the z-order feature (bugzilla 574273). Indeed, the display order now consider GMF
 * edges order (changes in
 * {@link org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDDiagramEditPart#refreshChildren()}).
 * 
 * @author Laurent Redor
 *
 */
public class EdgesZOrderMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * Migration version.
     */
    public static final Version MIGRATION_VERSION = new Version("14.6.0.202109231100"); //$NON-NLS-1$


    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    /**
     * This method is overridden to change GMF edges order if necessary.
     */
    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {

            boolean isModified = false;
            StringBuilder sb = new StringBuilder(Messages.EdgesZOrderMigrationParticipant_title);

            for (DView dView : dAnalysis.getOwnedViews()) {
                List<DRepresentationDescriptor> loadedRepresentationsDescriptors = new DViewQuery(dView).getLoadedRepresentationsDescriptors();
                for (DRepresentationDescriptor descriptor : loadedRepresentationsDescriptors) {
                    DRepresentation rep = descriptor.getRepresentation();
                    if (rep instanceof DDiagram) {
                        DDiagram dDiagram = (DDiagram) rep;

                        DDiagramGraphicalQuery query = new DDiagramGraphicalQuery(dDiagram);
                        Option<Diagram> gmfDiagram = query.getAssociatedGMFDiagram();
                        if (gmfDiagram.some()) {
                            // Get edges sorted from connection source/target order (as it was displayed before)
                            Collection<Edge> edgesFromSourceTargetOrder = getEdgesFromSourceAndTargetOrder(gmfDiagram.get());
                            if (sortEdges(gmfDiagram.get(), edgesFromSourceTargetOrder)) {
                                isModified = true;
                                // Add information to be logged
                                sb.append(MessageFormat.format(Messages.EdgesZOrderMigrationParticipant_edgesOrderChanged, descriptor.getName()));
                            }
                        }
                    }
                }
            }

            if (isModified) {
                DiagramPlugin.getDefault().logInfo(sb.toString());
            }
        }
    }

    /**
     * Sort edges of the given diagram according to the given order.
     * 
     * @param gmfDiagram
     *            The diagram containing edges to sort
     * @param edgesFromSourceTargetOrder
     *            The edges in the expected order
     * @return true if the order of edges in GMF diagram has been changed, false otherwise.
     */
    private boolean sortEdges(Diagram gmfDiagram, Collection<Edge> edgesFromSourceTargetOrder) {
        boolean orderHasChanged = false;
        int currentIndex = 0;
        for (Iterator<Edge> iterator = edgesFromSourceTargetOrder.iterator(); iterator.hasNext(); /* */) {
            Edge edge = iterator.next();
            List<Edge> edges = getEdges(gmfDiagram);
            if (edge != edges.get(currentIndex)) {
                gmfDiagram.removeEdge(edge);
                gmfDiagram.insertEdgeAt(edge, currentIndex);
                orderHasChanged = true;
            }
            currentIndex++;
        }
        return orderHasChanged;
    }

    /**
     * Get edges of this view and of its descendants. The order of the list is the same than in the refresh of the
     * figures threw {@link org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDDiagramEditPart#refreshChildren()}
     * before the z-order feature implementation.
     * 
     * @param view
     *            The starting point to get edges
     * @return A list of edges
     */
    private Collection<Edge> getEdgesFromSourceAndTargetOrder(View view) {
        Collection<Edge> result = new LinkedHashSet<Edge>();
        for (Object child : view.getChildren()) {
            if (child instanceof View) {
                View childView = (View) child;
                result.addAll(getEdgesFromSourceAndTargetOrder(childView));
                result.addAll(childView.getSourceEdges());
                result.addAll(childView.getTargetEdges());
            }
        }
        return result;
    }

    /**
     * Get the {@link Edge}s contained in the given {@link Diagram} .
     * 
     * @param gmfDiagram
     *            the given {@link Diagram}.
     * @return the {@link Edge}s contained in the given {@link Diagram}.
     */
    private List<Edge> getEdges(Diagram gmfDiagram) {
        return Lists.newArrayList(Iterables.filter(gmfDiagram.getEdges(), Edge.class));
    }

    protected String getMessageMigrationParticipantTitle() {
        return Messages.RepairGMFbendpointsMigrationParticipant_title;
    }
}
