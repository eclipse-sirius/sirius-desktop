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
import java.util.Map;

import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.BasicResourceHandler;

/**
 * VSM resource handler implementation.
 * 
 * @author fbarbin
 * 
 */
public class VSMResourceHandler extends BasicResourceHandler {

    private String version;

    /**
     * Constructor.
     * 
     * @param version
     *            the current version of the meta-model to migrate.
     */
    public VSMResourceHandler(String version) {
        this.version = version;
    }

    @Override
    public void postLoad(XMLResource resource, InputStream inputStream, Map<?, ?> options) {
        VSMMigrationService.getInstance().postLoad(resource, version);
        super.postLoad(resource, inputStream, options);
    }

}
