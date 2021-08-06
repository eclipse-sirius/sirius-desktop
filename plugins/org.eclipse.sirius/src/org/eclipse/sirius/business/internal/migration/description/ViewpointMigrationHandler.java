/**
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
 *
 */
package org.eclipse.sirius.business.internal.migration.description;

import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.business.api.migration.ResourceVersionMismatchDiagnostic;
import org.eclipse.sirius.business.internal.migration.AbstractSiriusMigrationService;
import org.eclipse.sirius.business.internal.migration.IMigrationHandler;
import org.eclipse.sirius.ext.base.Option;
import org.osgi.framework.Version;

/**
 * MigrationHandler used for viewpoint metamodel.
 * 
 * @author jmallet
 */
public class ViewpointMigrationHandler implements IMigrationHandler {

    /**
     * {@inheritDoc}
     *
     * @see IMigrationHandler#isMigrationNeeded(URI)
     */
    @Override
    public void addMigrationOptionIfNeeded(URI newResourceUri, Map<Object, Object> loadOptions, Map<Object, Object> saveOptions) {
        boolean migrationIsNeeded = true;
        VSMVersionSAXParser parser = new VSMVersionSAXParser(newResourceUri);
        String loadedVersion = parser.getVersion(new NullProgressMonitor());
        if (loadedVersion != null) {
            migrationIsNeeded = VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(loadedVersion));
        }
        // handle migration option
        if (migrationIsNeeded) {
            addMigrationOptions(loadedVersion, loadOptions, saveOptions);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see IMigrationHandler#createXMLHelper()
     */
    @Override
    public XMLHelper createXMLHelper(XMLResource descriptionResource) {
        return new VSMXMIHelper(descriptionResource);
    }

    /**
     * {@inheritDoc}
     *
     * @see IMigrationHandler#getOptionalRewrittenFragment(String)
     */
    @Override
    public Option<String> getOptionalRewrittenFragment(String uriFragment) {
        return VSMMigrationService.getInstance().getNewFragment(uriFragment);
    }

    /**
     * {@inheritDoc}
     *
     * @see IMigrationHandler#createXMLLoad(Map)
     */
    @Override
    public XMLLoad createXMLLoad(Map<?, ?> options, XMLResource descriptionResource) {
        if (options != null && options.containsKey(AbstractSiriusMigrationService.OPTION_RESOURCE_MIGRATION_LOADEDVERSION)) {
            // LoadedVersion can be null for old aird files.
            String loadedVersion = null;
            Object mapVersion = options.get(AbstractSiriusMigrationService.OPTION_RESOURCE_MIGRATION_LOADEDVERSION);
            if (mapVersion instanceof String) {
                loadedVersion = (String) mapVersion;
            }
            return new VSMResourceXMILoad(loadedVersion, createXMLHelper(descriptionResource));
        }
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see IMigrationHandler#handleMigrationOptions(Map, Map)
     */
    @Override
    public Diagnostic handleMigrationOptions(URI uri, Map<Object, Object> defaultLoadOptions, Map<Object, Object> defaultSaveOptions) {
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
        Object versionOption = defaultLoadOptions.get(AbstractSiriusMigrationService.OPTION_RESOURCE_MIGRATION_LOADEDVERSION);

        // If the migration options have been installed and we do not need to
        // migrate anymore (a reload following a save for instance), we remove
        // them.
        if (!migrationIsNeeded && versionOption != null) {
            removeMigrationMechanism(defaultLoadOptions, defaultSaveOptions);
        }
        // If we need to migrate we install the mechanism. If the mechanism
        // was already installed and the loaded version is different, we
        // update it.
        else if (migrationIsNeeded && (versionOption == null || !versionOption.equals(loadedVersion))) {
            addMigrationOptions(loadedVersion, defaultLoadOptions, defaultSaveOptions);
        }
        return migrationMismatchDiagnostic;
    }

    /**
     * Remove migration option.
     * 
     * @param defaultLoadOptions
     *            option map used to to control load behavior.
     * @param defaultSaveOptions
     *            option map used to control save behavior.
     */
    private void removeMigrationMechanism(Map<Object, Object> defaultLoadOptions, Map<Object, Object> defaultSaveOptions) {
        defaultLoadOptions.remove(XMLResource.OPTION_EXTENDED_META_DATA);
        defaultLoadOptions.remove(XMLResource.OPTION_RESOURCE_HANDLER);
        defaultLoadOptions.remove(AbstractSiriusMigrationService.OPTION_RESOURCE_MIGRATION_LOADEDVERSION);
        defaultSaveOptions.remove(XMLResource.OPTION_EXTENDED_META_DATA);
        defaultSaveOptions.remove(XMLResource.OPTION_RESOURCE_HANDLER);
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
         * This option is passed so that the resource can decide to adapt the load mechanism depending on the loaded
         * version.
         */
        loadOptions.put(AbstractSiriusMigrationService.OPTION_RESOURCE_MIGRATION_LOADEDVERSION, loadedVersion);

        saveOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
        saveOptions.put(XMLResource.OPTION_RESOURCE_HANDLER, resourceHandler);
    }

}
