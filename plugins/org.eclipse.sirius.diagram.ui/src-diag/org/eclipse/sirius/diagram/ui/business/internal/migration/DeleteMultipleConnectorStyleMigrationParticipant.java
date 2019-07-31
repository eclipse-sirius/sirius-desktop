/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmf.runtime.notation.ConnectorStyle;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.business.api.refresh.DiagramCreationUtil;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

/**
 * A representation file migration to delete multiple connectorStyle for each edge of diagrams.
 * 
 * @author jmallet
 */
public class DeleteMultipleConnectorStyleMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * The Sirius version for which this migration is added.
     */
    public static final Version MIGRATION_VERSION = new Version("14.1.0.201810161215"); //$NON-NLS-1$

    private boolean migrationOccured;

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            StringBuilder sb = new StringBuilder(Messages.DeleteMultipleConnectorMigrationParticipant_title);
            EList<DView> ownedViews = dAnalysis.getOwnedViews();
            for (DView view : ownedViews) {

                List<DRepresentationDescriptor> loadedRepresentationsDescriptors = new DViewQuery(view).getLoadedRepresentationsDescriptors();

                for (DRepresentationDescriptor descriptor : loadedRepresentationsDescriptors) {

                    DRepresentation rep = descriptor.getRepresentation();
                    if (rep instanceof DSemanticDiagram) {
                        DSemanticDiagram dDiagram = (DSemanticDiagram) rep;
                        boolean isEdgeModified = false;
                        List<Edge> edgeList = getEdgeList(dDiagram);
                        for (Edge edge : edgeList) {
                            isEdgeModified = checkAndDeleteMultipleConnectorStyle(edge) || isEdgeModified;
                        }
                        if (isEdgeModified) {
                            migrationOccured = true;
                            sb.append(MessageFormat.format(Messages.DeleteMultipleConnectorMigrationParticipant_edgesModified, descriptor.getName()));
                        }

                    }
                }
                if (migrationOccured) {
                    DiagramPlugin.getDefault().logInfo(sb.toString());
                    migrationOccured = false;
                }
            }
        }
    }

    /**
     * Check and delete multiple connectorStyle for a given edge.
     * 
     * @param edge
     *            the edge which contains connectors Style to check
     * @return true if some connector Style of the given edge have been deleted, false otherwise
     */
    private boolean checkAndDeleteMultipleConnectorStyle(Edge edge) {
        boolean isEdgeModified = false;
        List<?> connectorStyles = (List<?>) edge.getStyles().stream().filter(ConnectorStyle.class::isInstance).collect(Collectors.toList());
        if (connectorStyles.size() > 1) {
            for (int i = 1; i < connectorStyles.size(); i++) {
                edge.getStyles().remove(connectorStyles.get(i));
            }
            isEdgeModified = true;
        }
        return isEdgeModified;
    }

    /**
     * Return a list of the edges contained in a representation.
     * 
     * @param representation
     *            the representation
     * @return a list of edges
     */
    protected List<Edge> getEdgeList(DDiagram representation) {
        List<Edge> edges = new ArrayList<Edge>();
        DiagramCreationUtil diagramCreationUtil = new DiagramCreationUtil(representation);
        if (diagramCreationUtil.findAssociatedGMFDiagram()) {
            Diagram gmfDiagram = diagramCreationUtil.getAssociatedGMFDiagram();
            edges = gmfDiagram.getEdges();
        }
        return edges;
    }

}
