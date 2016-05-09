/**
 * Copyright (c) 2007, 2016 THALES GLOBAL SERVICES and others.
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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.sirius.business.api.migration.ResourceVersionMismatchDiagnostic;
import org.eclipse.sirius.business.internal.migration.AbstractSiriusMigrationService;
import org.eclipse.sirius.business.internal.migration.description.VSMExtendedMetaData;
import org.eclipse.sirius.business.internal.migration.description.VSMMigrationService;
import org.eclipse.sirius.business.internal.migration.description.VSMResourceHandler;
import org.eclipse.sirius.business.internal.migration.description.VSMResourceXMILoad;
import org.eclipse.sirius.business.internal.migration.description.VSMVersionSAXParser;
import org.eclipse.sirius.business.internal.migration.description.VSMXMIHelper;
import org.eclipse.sirius.ext.base.Option;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.viewpoint.description.util.
 *      DescriptionResourceFactoryImpl
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
        if (!isLoaded) {
            useURIFragmentAsId = Boolean.TRUE.equals(options.get(DescriptionResourceImpl.OPTION_USE_URI_FRAGMENT_AS_ID)) && getURI().isPlatformPlugin();
            Diagnostic migrationMismatchDiagnostic = handleMigrationOptions();
            super.load(options);
            if (migrationMismatchDiagnostic != null) {
                getErrors().add(migrationMismatchDiagnostic);
            }
        }
    }

    /**
     * Handle migration options and return an error diagnostic in case of
     * migration version mismatch
     */
    private Diagnostic handleMigrationOptions() {
        Diagnostic migrationMismatchDiagnostic = null;
        VSMVersionSAXParser parser = new VSMVersionSAXParser(uri);
        String loadedVersion = parser.getVersion(new NullProgressMonitor());
        boolean migrationIsNeeded = true;
        if (loadedVersion != null) {
            Version parsedLoadedVersion = Version.parseVersion(loadedVersion);
            Version lastMigrationVersion = VSMMigrationService.getInstance().getLastMigrationVersion();
            boolean attemptToLoadMoreRecentVSM = lastMigrationVersion.compareTo(parsedLoadedVersion) < 0;
            if (attemptToLoadMoreRecentVSM) {
                migrationMismatchDiagnostic = new ResourceVersionMismatchDiagnostic(uri, parsedLoadedVersion, lastMigrationVersion);
            }
            migrationIsNeeded = VSMMigrationService.getInstance().isMigrationNeeded(parsedLoadedVersion);
        }
        Object versionOption = this.getDefaultLoadOptions().get(AbstractSiriusMigrationService.OPTION_RESOURCE_MIGRATION_LOADEDVERSION);

        // If the migration options have been installed and we do not need to
        // migrate anymore (a reload following a save for instance), we remove
        // them.
        if (!migrationIsNeeded && versionOption != null) {
            removeMigrationMechanism();
        }
        // If we need to migrate we install the mechanism. If the mechanism
        // was already installed and the loaded version is different, we
        // update it.
        else if (migrationIsNeeded && (versionOption == null || !versionOption.equals(loadedVersion))) {
            DescriptionResourceImpl.addMigrationOptions(loadedVersion, this.getDefaultLoadOptions(), this.getDefaultSaveOptions());
        }
        return migrationMismatchDiagnostic;
    }

    private void removeMigrationMechanism() {
        this.getDefaultLoadOptions().remove(XMLResource.OPTION_EXTENDED_META_DATA);
        this.getDefaultLoadOptions().remove(XMLResource.OPTION_RESOURCE_HANDLER);
        this.getDefaultLoadOptions().remove(AbstractSiriusMigrationService.OPTION_RESOURCE_MIGRATION_LOADEDVERSION);
        this.getDefaultSaveOptions().remove(XMLResource.OPTION_EXTENDED_META_DATA);
        this.getDefaultSaveOptions().remove(XMLResource.OPTION_RESOURCE_HANDLER);
    }

    /**
     * Add the migration options in the given loadOptions and saveOptions maps.
     *
     * @param loadedVersion
     *            the loadedVersion.
     * @param loadOptions
     *            the loadOptions map.
     * @param saveOptions
     *            the saveOptions map.
     */
    public static void addMigrationOptions(String loadedVersion, final Map<Object, Object> loadOptions, final Map<Object, Object> saveOptions) {
        VSMExtendedMetaData extendedMetaData = new VSMExtendedMetaData(loadedVersion);
        VSMResourceHandler resourceHandler = new VSMResourceHandler(loadedVersion);

        loadOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
        loadOptions.put(XMLResource.OPTION_RESOURCE_HANDLER, resourceHandler);
        /**
         * This option is passed so that the resource can decide to adapt the
         * load mechanism depending on the loaded version.
         */
        loadOptions.put(AbstractSiriusMigrationService.OPTION_RESOURCE_MIGRATION_LOADEDVERSION, loadedVersion);

        saveOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
        saveOptions.put(XMLResource.OPTION_RESOURCE_HANDLER, resourceHandler);
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
