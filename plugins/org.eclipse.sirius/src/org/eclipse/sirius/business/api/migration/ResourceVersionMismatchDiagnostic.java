/*******************************************************************************
 * Copyright (c) 2016, 2019 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.sirius.business.api.migration;

import java.text.MessageFormat;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.viewpoint.Messages;
import org.osgi.framework.Version;

/**
 * A {@link Diagnostic} to help Sirius to manage the case when the model being loaded is newer than the Sirius release.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class ResourceVersionMismatchDiagnostic implements Diagnostic {

    private final URI resourceToLoadURI;

    private Version lastMigrationVersion;

    private Version parsedLoadedVersion;

    /**
     * Default constructor.
     * 
     * @param resourceToLoadURI
     *            to {@link URI} of the resource to load
     * @param parsedLoadedVersion
     *            the parsed loaded version
     * @param lastMigrationVersion
     *            the last migration version
     */
    public ResourceVersionMismatchDiagnostic(URI resourceToLoadURI, Version parsedLoadedVersion, Version lastMigrationVersion) {
        this.resourceToLoadURI = resourceToLoadURI;
        this.lastMigrationVersion = lastMigrationVersion;
        this.parsedLoadedVersion = parsedLoadedVersion;

    }

    public Version getLastMigrationVersion() {
        return lastMigrationVersion;
    }

    public Version getParsedLoadedVersion() {
        return parsedLoadedVersion;
    }

    public URI getResourceToLoadURI() {
        return resourceToLoadURI;
    }

    @Override
    public String getLocation() {
        if (resourceToLoadURI != null) {
            return resourceToLoadURI.toString();
        }
        return null;
    }

    @Override
    public int getLine() {
        return 0;
    }

    @Override
    public int getColumn() {
        return 0;
    }

    @Override
    public String getMessage() {
        String message = null;
        String fileExtension = resourceToLoadURI.fileExtension();
        if (SiriusUtil.SESSION_RESOURCE_EXTENSION.equals(fileExtension)) {
            message = MessageFormat.format(Messages.ResourceVersionMismatchDiagnostic_airdMessage, resourceToLoadURI, parsedLoadedVersion, lastMigrationVersion);
        } else if (SiriusUtil.DESCRIPTION_MODEL_EXTENSION.equals(fileExtension)) {
            message = MessageFormat.format(Messages.ResourceVersionMismatchDiagnostic_vsmMessage, resourceToLoadURI, parsedLoadedVersion, lastMigrationVersion);
        }
        return message;
    }
}
