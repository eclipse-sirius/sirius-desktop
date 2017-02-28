/**
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.business.internal.migration.description.VSMMigrationService;
import org.eclipse.sirius.business.internal.migration.description.VSMVersionSAXParser;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> The <b>Resource Factory</b> associated with the package. <!-- end-user-doc -->
 * 
 * @see org.eclipse.sirius.viewpoint.description.util.DescriptionResourceImpl
 * @generated
 */
public class DescriptionResourceFactoryImpl extends ResourceFactoryImpl {

    /**
     * Creates an instance of the resource factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DescriptionResourceFactoryImpl() {
        super();
    }

    /**
     * Creates an instance of the resource. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public Resource createResource(URI uri) {
        VSMVersionSAXParser parser = new VSMVersionSAXParser(uri);
        String loadedVersion = parser.getVersion(new NullProgressMonitor());
        boolean migrationIsNeeded = true;
        if (loadedVersion != null) {
            migrationIsNeeded = VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(loadedVersion));
        }

        XMIResource resource = new DescriptionResourceImpl(uri);

        // set load options
        final Map<Object, Object> loadOptions = new HashMap<Object, Object>();
        loadOptions.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);

        // set save options
        final Map<Object, Object> saveOptions = new HashMap<Object, Object>();
        saveOptions.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);

        // handle migration option
        if (migrationIsNeeded) {
            DescriptionResourceImpl.addMigrationOptions(loadedVersion, loadOptions, saveOptions);
        }

        resource.getDefaultLoadOptions().putAll(loadOptions);
        resource.getDefaultSaveOptions().putAll(saveOptions);

        return resource;
    }

} // DescriptionResourceFactoryImpl
