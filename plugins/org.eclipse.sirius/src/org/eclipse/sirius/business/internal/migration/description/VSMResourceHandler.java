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
package org.eclipse.sirius.business.internal.migration.description;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.BasicResourceHandler;
import org.eclipse.sirius.viewpoint.description.Group;
import org.osgi.framework.Version;

/**
 * VSM resource handler implementation.
 * 
 * @author fbarbin
 * 
 */
public class VSMResourceHandler extends BasicResourceHandler {

    private String loadedVersion;

    /**
     * Constructor.
     * 
     * @param loadedVersion
     *            the current version of the meta-model to migrate.
     */
    public VSMResourceHandler(String loadedVersion) {
        this.loadedVersion = loadedVersion;
    }

    @Override
    public void postLoad(XMLResource resource, InputStream inputStream, Map<?, ?> options) {
        VSMMigrationService.getInstance().postLoad(resource, loadedVersion);
        super.postLoad(resource, inputStream, options);
    }

    @Override
    public void preSave(XMLResource resource, OutputStream outputStream, Map<?, ?> options) {
        super.preSave(resource, outputStream, options);

        if (!resource.getContents().isEmpty() && resource.getContents().get(0) instanceof Group) {
            Group group = (Group) resource.getContents().get(0);
            Version lastMigrationVersion = VSMMigrationService.getInstance().getLastMigrationVersion();
          
            boolean previousDeliver = group.eDeliver();
            group.eSetDeliver(false);
            
            group.setVersion(lastMigrationVersion.toString());
            
            group.eSetDeliver(previousDeliver);
        }
    }
}