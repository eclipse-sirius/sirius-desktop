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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.sirius.business.internal.migration.description.VSMMigrationService;
import org.eclipse.sirius.business.internal.migration.description.VSMVersionSAXParser;
import org.eclipse.sirius.business.internal.migration.description.VSMXMIHelper;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.sirius.viewpoint.description.util.DescriptionResourceFactoryImpl
 * @generated NOT
 */
public class DescriptionResourceImpl extends XMIResourceImpl {

    private String loadedVersion;

    private boolean migrationIsNeeded = false;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * Creates an instance of the resource. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param uri
     *            the URI of the new resource.
     * @param loadedVersion
     *            the last migration version of the representations file.
     * @generated NOT
     */
    public DescriptionResourceImpl(URI uri, String loadedVersion) {
        super(uri);
        this.loadedVersion = loadedVersion;
        this.migrationIsNeeded = VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(loadedVersion));

    }

    @Override
    protected XMLHelper createXMLHelper() {
        if (loadedVersion == null) {
            VSMVersionSAXParser parser = new VSMVersionSAXParser(this.getURI());
            loadedVersion = parser.getVersion(new NullProgressMonitor());
            this.migrationIsNeeded = VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(loadedVersion));

        }
        return new VSMXMIHelper(loadedVersion, this);
    }

    /**
     * Override to migrate fragment if necessary (when a reference has been
     * renamed) before getting the EObject.
     */
    @Override
    public EObject getEObject(String uriFragment) {
        if (migrationIsNeeded) {
            Option<String> optionalRewrittenFragment = VSMMigrationService.getInstance().getNewFragment(uriFragment, loadedVersion);
            if (optionalRewrittenFragment.some()) {
                return getEObject(optionalRewrittenFragment.get());
            }
        }
        return super.getEObject(uriFragment);

    }

} // DescriptionResourceImpl
