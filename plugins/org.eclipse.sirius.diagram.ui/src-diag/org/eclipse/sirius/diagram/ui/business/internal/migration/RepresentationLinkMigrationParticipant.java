/*******************************************************************************
 * Copyright (c) 2018, 2021 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Felix Dorner <felix.dorner@gmail.com> - initial API and implementation
 */
package org.eclipse.sirius.diagram.ui.business.internal.migration;

import java.text.MessageFormat;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.diagram.ui.internal.view.factories.SiriusNoteViewFactory;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

/**
 * Reuse the sirius 'specific styles' annotation to mark all existing link notes with a special details entry. This
 * marker neccessary to differenciate between link notes whose target representation descriptor has been deleted and
 * normal notes.
 * 
 * @see <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=535648">Bug 535648</a>
 * @author Felix Dorner <felix.dorner@gmail.com>
 */
public class RepresentationLinkMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * The version for which this migration is added.
     */
    public static final Version MIGRATION_VERSION = new Version("14.1.0.201810111800"); //$NON-NLS-1$

    private boolean migrateDiagram(Diagram diag) {
        boolean changed = false;
        Iterator<EObject> it = diag.eAllContents();
        while (it.hasNext()) {
            EObject next = it.next();
            if (next instanceof View && ViewType.NOTE.equals(((View) next).getType()) && ((View) next).getElement() instanceof DRepresentationDescriptor) {
                SiriusNoteViewFactory.markAsLinkNote((View) next);
                changed = true;
            }
        }
        return changed;
    }

    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            StringBuilder builder = new StringBuilder();
            boolean isModified = false;
            for (DView view : dAnalysis.getOwnedViews()) {
                for (DRepresentationDescriptor representationDesc : new DViewQuery(view).getLoadedRepresentationsDescriptors()) {
                    if (representationDesc.getRepresentation() instanceof DDiagram) {
                        DDiagramGraphicalQuery query = new DDiagramGraphicalQuery((DDiagram) representationDesc.getRepresentation());
                        Option<Diagram> option = query.getAssociatedGMFDiagram();
                        if (option.some() && migrateDiagram(option.get())) {
                            builder.append(MessageFormat.format(Messages.RepresentationLinkMigrationParticipant_entry, representationDesc.getName())).append(System.lineSeparator());
                            isModified = true;
                        }
                    }
                }
            }
            if (isModified) {
                builder.insert(0, System.lineSeparator());
                builder.insert(0, Messages.RepresentationLinkMigrationParticipant_title);
                logMigrationInfo(builder.toString());
            }
        }
    }

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

}
