/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.migration;

import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.DiagramCreationUtil;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * A migration participant following the bug #499414 to remove all
 * NoteAttachment without source or target.<br />
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 *
 */
public class NoteAttachmentWithoutSourceOrTargetMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {
    /**
     * The VP version for which this migration is added.
     */
    public static final Version MIGRATION_VERSION = new Version("11.1.0.201608120930"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            for (DView dView : dAnalysis.getOwnedViews()) {
                for (DDiagram dDiagram : Iterables.filter(new DViewQuery(dView).getLoadedRepresentations(), DDiagram.class)) {
                    DiagramCreationUtil diagramCreationUtil = new DiagramCreationUtil(dDiagram);
                    if (diagramCreationUtil.findAssociatedGMFDiagram()) {
                        Diagram gmfDiagram = diagramCreationUtil.getAssociatedGMFDiagram();
                        deleteNoteAttachmentWithoutSourceOrTarget(gmfDiagram);
                    }
                }
            }
            super.postLoad(dAnalysis, loadedVersion);
        }
    }

    private void deleteNoteAttachmentWithoutSourceOrTarget(Diagram gmfDiagram) {
        Iterable<Connector> noteAttachmentsToRemoveIter = Iterables.filter(Iterables.filter(gmfDiagram.getEdges(), Connector.class), new Predicate<Connector>() {
            @Override
            public boolean apply(Connector connector) {
                if (ViewType.NOTEATTACHMENT.equals(connector.getType())) {
                    if (connector.getSource() == null || connector.getTarget() == null) {
                        return true;
                    }
                }
                return false;
            }
        });
        // Make a copy to avoid modification of edges list during the iteration
        // on it.
        Connector[] noteAttachmentsToRemove = Iterables.toArray(noteAttachmentsToRemoveIter, Connector.class);
        // Remove each invalid note attachments
        for (Connector connector : noteAttachmentsToRemove) {
            gmfDiagram.removeEdge(connector);
        }
    }
}
