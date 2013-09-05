/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.migration;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.osgi.framework.Version;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.DAnalysis;
import org.eclipse.sirius.DDiagram;
import org.eclipse.sirius.DRepresentation;
import org.eclipse.sirius.DView;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.diagram.business.api.query.DDiagramGraphicalQuery;

/**
 * Migration contribution for diagram part of representations file.
 * 
 * @author lredor
 */
public class DiagramRepresentationsFileMigrationParticiant extends AbstractRepresentationsFileMigrationParticipant {
    /**
     * The latest VP version for this participant.
     */
    private static final Version MIGRATION_VERSION = DiagramRepresentationsFileMigrationParticipantV670.MIGRATION_VERSION;

    private static final Version ALREADY_MIGRATED_VERSION = new Version("6.5.3");

    /**
     * {@inheritDoc}
     */
    public void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        super.postLoad(dAnalysis, loadedVersion);
        if (loadedVersion.compareTo(DiagramRepresentationsFileMigrationParticiantV650.MIGRATION_VERSION) < 0) {
            List<Diagram> diagramsToMove = DiagramRepresentationsFileMigrationParticiantV650.getDiagramsAtRoot(dAnalysis);
            if (!diagramsToMove.isEmpty()) {
                // Move GMF diagrams from root of the resource to eAnnotation in
                // corresponding DDiagram, and then launch old migrations that
                // haven't been launched on this diagrams.
                new DiagramRepresentationsFileMigrationParticiantV650().moveGMFDiagramsFromRoot(dAnalysis, diagramsToMove);
            }
        }
        if (loadedVersion.compareTo(ALREADY_MIGRATED_VERSION) < 0) {
            List<Diagram> diagrams = getGMFDiagrams(dAnalysis);
            if (!diagrams.isEmpty()) {
                DiagramRepresentationsFileMigrationParticipantV670 diagramRepresentationsFileMigrationParticipantV670 = new DiagramRepresentationsFileMigrationParticipantV670();
                diagramRepresentationsFileMigrationParticipantV670.migrateLabelConstraintFromBoundsToLocation(diagrams);
                diagramRepresentationsFileMigrationParticipantV670.migrateGMFBoundsOfBorderedNodes(diagrams);
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.migration.IMigrationParticipant#getMigrationVersion()
     */
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    /**
     * Retrieve all GMF diagrams of the {@link DAnalysis}.
     * 
     * @param dAnalysis
     *            The analysis of the resource to migrate
     * @return GMF diagrams of this {@link DAnalysis}
     */
    public List<Diagram> getGMFDiagrams(DAnalysis dAnalysis) {
        List<Diagram> diagrams = new ArrayList<Diagram>();

        for (DView view : dAnalysis.getOwnedViews()) {
            for (DRepresentation representation : view.getOwnedRepresentations()) {
                if (representation instanceof DDiagram) {
                    DDiagramGraphicalQuery query = new DDiagramGraphicalQuery((DDiagram) representation);
                    Option<Diagram> option = query.getAssociatedGMFDiagram();
                    if (option.some()) {
                        diagrams.add(option.get());
                    }
                }
            }
        }

        return diagrams;
    }
}
