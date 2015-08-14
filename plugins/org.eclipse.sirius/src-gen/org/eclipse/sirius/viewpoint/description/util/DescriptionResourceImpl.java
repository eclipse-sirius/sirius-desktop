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

import java.io.IOException;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.sirius.business.internal.migration.AbstractSiriusMigrationService;
import org.eclipse.sirius.business.internal.migration.description.VSMMigrationService;
import org.eclipse.sirius.business.internal.migration.description.VSMResourceXMILoad;
import org.eclipse.sirius.business.internal.migration.description.VSMXMIHelper;
import org.eclipse.sirius.ext.base.Option;

/**
 * <!-- begin-user-doc --> The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.sirius.viewpoint.description.util.DescriptionResourceFactoryImpl
 * @not-generated
 */
public class DescriptionResourceImpl extends XMIResourceImpl {

    /**
     * Option to specify if we use uri fragment as id in
     * {@link XMLResourceImpl#getIDToEObjectMap()} map to enhance inter
     * resources proxy resolution. Default is false. This option is considered
     * only for odesign in plugin.
     */
    public static final String OPTION_USE_URI_FRAGMENT_AS_ID = "SIRIUS_USE_URI_FRAGMENT_AS_ID"; //$NON-NLS-1$

    private boolean useURIFragmentAsId;

    /**
     * Creates an instance of the resource. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param uri
     *            the URI of the new resource.
     * @not-generated
     */
    protected DescriptionResourceImpl(URI uri) {
        super(uri);
    }

    @Override
    protected XMLHelper createXMLHelper() {
        return new VSMXMIHelper(this);
    }

    @Override
    protected XMLLoad createXMLLoad(Map<?, ?> options) {
        if (options != null && options.containsKey(AbstractSiriusMigrationService.OPTION_RESOURCE_MIGRATION_LOADEDVERSION)) {
            // LoadedVersion can be null for old aird files.
            String loadedVersion = null;
            Object mapVersion = options.get(AbstractSiriusMigrationService.OPTION_RESOURCE_MIGRATION_LOADEDVERSION);
            if (mapVersion instanceof String) {
                loadedVersion = (String) mapVersion;
            }
            return new VSMResourceXMILoad(loadedVersion, createXMLHelper());
        }
        return super.createXMLLoad(options);
    }

    @Override
    public void setModified(boolean isModified) {
        super.setModified(isModified);
        if (isModified) {
            getIDToEObjectMap().clear();
        }
    }

    @Override
    public void load(Map<?, ?> options) throws IOException {
        useURIFragmentAsId = Boolean.TRUE.equals(options.get(OPTION_USE_URI_FRAGMENT_AS_ID)) && getURI().isPlatformPlugin();
        super.load(options);
    }

    /**
     * Override to migrate fragment if necessary (when a reference has been
     * renamed) before getting the EObject.
     */
    @Override
    public EObject getEObject(String uriFragment) {
        Option<String> optionalRewrittenFragment = VSMMigrationService.getInstance().getNewFragment(uriFragment);
        if (optionalRewrittenFragment.some()) {
            return getEObject(optionalRewrittenFragment.get());
        } else if (useURIFragmentAsId) {
            return getEObjectUsingURIFragmentAsId(uriFragment);
        } else {
            return super.getEObject(uriFragment);
        }
    }

    private EObject getEObjectUsingURIFragmentAsId(String uriFragment) {
        EObject eObject = null;
        if (isLoading) {
            eObject = getEObjectAndUpdateIDMap(uriFragment);
        } else {
            eObject = getEObjectByID(uriFragment);
            if (eObject == null) {
                eObject = getEObjectAndUpdateIDMap(uriFragment);
            }
        }
        return eObject;
    }

    private EObject getEObjectAndUpdateIDMap(String uriFragment) {
        EObject eObject = super.getEObject(uriFragment);
        if (eObject != null) {
            setID(eObject, uriFragment);
        }
        return eObject;
    }

} // DescriptionResourceImpl
