/*******************************************************************************
 * Copyright (c) 2018, 2021 Obeo.
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
package org.eclipse.sirius.business.internal.migration;

import java.text.MessageFormat;

import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.IdentifiedElement;
import org.osgi.framework.Version;

/**
 * This migration participant will update IdentifiedElement.uid to be the same value than the xmiid.</br>
 * DRepresentationDescriptor.repPath is updated consequently to the DRepresentation.uid change
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class TechnicalUidMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * The VP version for which this migration is added.
     */
    public static final Version MIGRATION_VERSION = new Version("14.1.0.201808300808"); //$NON-NLS-1$

    private boolean migrationOccured;

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    public void postLoad(XMLResource resource, String loadedVersion) {
        if (Version.parseVersion(loadedVersion).compareTo(MIGRATION_VERSION) < 0) {
            resource.getAllContents().forEachRemaining(eObject -> {
                if (eObject instanceof IdentifiedElement) {
                    migrationOccured = true;
                    IdentifiedElement idElem = (IdentifiedElement) eObject;
                    String xmiid = resource.getID(eObject);
                    idElem.setUid(xmiid);

                    // Special case for DRepresentationDescriptor that must update the repPath
                    if (eObject instanceof DRepresentationDescriptor) {
                        DRepresentation rep = ((DRepresentationDescriptor) eObject).getRepresentation();
                        if (rep != null) {
                            rep.setUid(resource.getID(rep));
                            ((DRepresentationDescriptor) eObject).updateRepresentation(rep);
                        }
                    }

                }
            });
            if (migrationOccured) {
                SiriusPlugin.getDefault().info(MessageFormat.format(Messages.TechnicalUidMigrationParticipant_message, resource.getURI().toPlatformString(true)), null);
                migrationOccured = false;
            }
        }
        super.postLoad(resource, loadedVersion);
    }
}
