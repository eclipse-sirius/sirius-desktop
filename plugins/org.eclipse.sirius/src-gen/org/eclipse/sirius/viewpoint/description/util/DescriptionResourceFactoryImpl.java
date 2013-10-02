/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.viewpoint.description.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.business.internal.migration.description.VSMExtendedMetaData;
import org.eclipse.sirius.business.internal.migration.description.VSMMigrationService;
import org.eclipse.sirius.business.internal.migration.description.VSMResourceHandler;
import org.eclipse.sirius.business.internal.migration.description.VSMVersionSAXParser;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> The <b>Resource Factory</b> associated with the
 * package. <!-- end-user-doc -->
 * 
 * @see org.eclipse.sirius.viewpoint.description.util.DescriptionResourceImpl
 * @generated
 */
public class DescriptionResourceFactoryImpl extends ResourceFactoryImpl {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\nContributors:\n   Obeo - initial API and implementation\n";

    private VSMExtendedMetaData extendedMetaData;

    private VSMResourceHandler resourceHandler;

    /**
     * Creates an instance of the resource factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public DescriptionResourceFactoryImpl() {
        super();
    }

    /**
     * Creates an instance of the resource. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public Resource createResource(URI uri) {

        VSMVersionSAXParser parser = new VSMVersionSAXParser(uri);
        String loadedVersion = parser.getVersion(new NullProgressMonitor());
        boolean migrationIsNeeded = true;
        if (loadedVersion != null) {
            migrationIsNeeded = VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(loadedVersion));
        }

        if (migrationIsNeeded) {
            extendedMetaData = new VSMExtendedMetaData(loadedVersion);
            resourceHandler = new VSMResourceHandler(loadedVersion);
        }
        XMIResource resource = new DescriptionResourceImpl(uri, loadedVersion);

        // set load options
        final Map<Object, Object> loadOptions = new HashMap<Object, Object>();
        if (migrationIsNeeded) {
            loadOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
            loadOptions.put(XMLResource.OPTION_RESOURCE_HANDLER, resourceHandler);
        }

        loadOptions.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);

        resource.getDefaultLoadOptions().putAll(loadOptions);

        // set save options
        final Map<Object, Object> saveOptions = new HashMap<Object, Object>();

        if (migrationIsNeeded) {
            saveOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
            saveOptions.put(XMLResource.OPTION_RESOURCE_HANDLER, resourceHandler);
        }

        saveOptions.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);

        resource.getDefaultSaveOptions().putAll(saveOptions);

        return resource;
    }

} // DescriptionResourceFactoryImpl
