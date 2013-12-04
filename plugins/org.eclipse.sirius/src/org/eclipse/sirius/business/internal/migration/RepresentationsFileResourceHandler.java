/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.migration;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.BasicResourceHandler;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.osgi.framework.Version;

/**
 * Implementation for viewpoint migration post-treatment. Delegates to
 * {@link RepresentationsFileMigrationService}
 * 
 * @author fbarbin
 * 
 */
public class RepresentationsFileResourceHandler extends BasicResourceHandler {

    private String loadedVersion;

    /**
     * Constructor.
     * 
     * @param loadedVersion
     *            the loaded version of the model to migrate.
     */
    public RepresentationsFileResourceHandler(String loadedVersion) {
        this.loadedVersion = loadedVersion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void postLoad(XMLResource resource, InputStream inputStream, Map<?, ?> options) {
        RepresentationsFileMigrationService.getInstance().postLoad(resource, loadedVersion);
        super.postLoad(resource, inputStream, options);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void preSave(XMLResource resource, OutputStream outputStream, Map<?, ?> options) {
        super.preSave(resource, outputStream, options);

        if (!resource.getContents().isEmpty() && resource.getContents().get(0) instanceof DAnalysis) {
            DAnalysis analysis = (DAnalysis) resource.getContents().get(0);
            Version lastMigrationVersion = RepresentationsFileMigrationService.getInstance().getLastMigrationVersion();

            boolean previousDeliver = analysis.eDeliver();
            analysis.eSetDeliver(false);

            analysis.setVersion(lastMigrationVersion.toString());

            analysis.eSetDeliver(previousDeliver);
        }
    }
}