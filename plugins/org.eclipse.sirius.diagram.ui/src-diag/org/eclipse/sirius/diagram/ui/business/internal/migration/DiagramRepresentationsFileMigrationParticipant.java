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
package org.eclipse.sirius.diagram.ui.business.internal.migration;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

/**
 * Migration contribution for diagram part of representations file.
 * 
 * @author lredor
 */
public class DiagramRepresentationsFileMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {
    /**
     * The latest VP version for this participant.
     */
    private static final Version MIGRATION_VERSION = DiagramRepresentationsFileMigrationParticipantV801.MIGRATION_VERSION;

    private static final Version ALREADY_MIGRATED_VERSION = new Version("6.5.3"); //$NON-NLS-1$

    private static final Version NOT_MIGRATED_VERSION = new Version("6.6.0"); //$NON-NLS-1$

    /**
     * {@inheritDoc}
     */
    public void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        super.postLoad(dAnalysis, loadedVersion);
        if (loadedVersion.compareTo(DiagramRepresentationsFileMigrationParticipantV650.MIGRATION_VERSION) < 0) {
            List<Diagram> diagramsToMove = DiagramRepresentationsFileMigrationParticipantV650.getDiagramsAtRoot(dAnalysis);
            if (!diagramsToMove.isEmpty()) {
                // Move GMF diagrams from root of the resource to eAnnotation in
                // corresponding DDiagram, and then launch old migrations that
                // haven't been launched on this diagrams.
                new DiagramRepresentationsFileMigrationParticipantV650().moveGMFDiagramsFromRoot(dAnalysis, diagramsToMove);
            }
        }

        if (loadedVersion.compareTo(DiagramRepresentationsFileMigrationParticipantV670.MIGRATION_VERSION) < 0) {
            // The 6.5.3 maintenance version already contains the migration,
            // migration should be done for versions in
            // [0.0.0, 6.5.3[ U [6.6.0, 6.7.0[.
            if (loadedVersion.compareTo(ALREADY_MIGRATED_VERSION) < 0 || loadedVersion.compareTo(NOT_MIGRATED_VERSION) >= 0) {
                List<Diagram> diagrams = getGMFDiagrams(dAnalysis);
                if (!diagrams.isEmpty()) {
                    DiagramRepresentationsFileMigrationParticipantV670 diagramRepresentationsFileMigrationParticipantV670 = new DiagramRepresentationsFileMigrationParticipantV670();
                    diagramRepresentationsFileMigrationParticipantV670.migrateLabelConstraintFromBoundsToLocation(diagrams);
                    diagramRepresentationsFileMigrationParticipantV670.migrateGMFBoundsOfBorderedNodes(diagrams);
                }
            }
        }
        if (loadedVersion.compareTo(DiagramRepresentationsFileMigrationParticipantV680.MIGRATION_VERSION) < 0) {
            List<Diagram> diagrams = getGMFDiagrams(dAnalysis);
            if (!diagrams.isEmpty()) {
                DiagramRepresentationsFileMigrationParticipantV680 diagramRepresentationsFileMigrationParticipantV680 = new DiagramRepresentationsFileMigrationParticipantV680();
                diagramRepresentationsFileMigrationParticipantV680.migrateCompartmentsWithLayoutConstraints(diagrams);
                diagramRepresentationsFileMigrationParticipantV680.migrateEdgeLabelLocationToBounds(diagrams);
            }
        }
        if (loadedVersion.compareTo(DiagramRepresentationsFileMigrationParticipantV690.MIGRATION_VERSION) < 0) {
            List<Diagram> diagrams = getGMFDiagrams(dAnalysis);
            DiagramRepresentationsFileMigrationParticipantV690 diagramRepresentationsFileMigrationParticipantV690 = new DiagramRepresentationsFileMigrationParticipantV690();
            diagramRepresentationsFileMigrationParticipantV690.migrateEdgeRoutingStyle(getGMFDiagrams(dAnalysis));
            diagramRepresentationsFileMigrationParticipantV690.migrateVisibilityInconsistenciesBetweenGMFNodeAndDDiagramElement(diagrams);
        }

        // This migration has been introduced in Sirius 1.0.0M6 and in several
        // maintenance versions anterior to Sirius. For a complexity reason and
        // the fact that migration can be re-apply safely, we do not verify each
        // version for which this migration has already been applied.
        if (loadedVersion.compareTo(DiagramRepresentationsFileMigrationParticipantV801.MIGRATION_VERSION) < 0) {
            List<Diagram> diagrams = getGMFDiagrams(dAnalysis);
            if (!diagrams.isEmpty()) {
                new DiagramRepresentationsFileMigrationParticipantV801().migrateLabelVisibilityInconsistency(diagrams);
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

    @Override
    public EClassifier getType(EPackage ePackage, String name, String loadedVersion) {

        if (Version.parseVersion(loadedVersion).compareTo(DiagramRepresentationsFileMigrationParticipantV690.MIGRATION_VERSION) < 0) {
            DiagramRepresentationsFileMigrationParticipantV690 representationsFileMigrationParticipantV690 = new DiagramRepresentationsFileMigrationParticipantV690();
            EClassifier classifier = representationsFileMigrationParticipantV690.getType(ePackage, name);
            if (classifier != null) {
                return classifier;
            }
        }

        return super.getType(ePackage, name, loadedVersion);
    }

    @Override
    public Object getValue(EObject object, EStructuralFeature feature, Object value, String loadedVersion) {
        if (Version.parseVersion(loadedVersion).compareTo(DiagramRepresentationsFileMigrationParticipantV700.MIGRATION_VERSION) < 0) {
            DiagramRepresentationsFileMigrationParticipantV700 representationsFileMigrationParticipantV700 = new DiagramRepresentationsFileMigrationParticipantV700();
            Object result = representationsFileMigrationParticipantV700.getValue(object, feature, value);
            if (result != null) {
                return result;
            }
        }
        return super.getValue(object, feature, value, loadedVersion);
    }
}
