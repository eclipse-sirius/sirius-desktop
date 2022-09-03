/*******************************************************************************
 * Copyright (c) 2007, 2022 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *    IBM Corporation and others - The code for default load/save options was lifted
 *      from GMF's org.eclipse.gmf.runtime.emf.core.resources.GMFResourceFactory.
 *******************************************************************************/
package org.eclipse.sirius.business.internal.resource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileVersionSAXParser;
import org.eclipse.sirius.common.tools.api.resource.ResourceMigrationMarker;
import org.osgi.framework.Version;

/**
 * A resource factory decorator to set XMI encodings.
 * 
 * @author ymortier
 */
public class AirDResourceFactory extends SiriusResourceFactory {

    /**
     * Git schemes available in org.eclipse.emf.diffmerge.connector.git.ext.GitHelper
     */
    private static final String[] GIT_SCHEME = new String[] { "commit", "index", "remote" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.emf.core.resources.GMFResourceFactory#createResource(org.eclipse.emf.common.util.URI)
     */
    @Override
    public Resource createResource(final URI uri) {
        RepresentationsFileVersionSAXParser parser = new RepresentationsFileVersionSAXParser(uri);
        boolean migrationIsNeeded = true;
        String loadedVersion = parser.getVersion(new NullProgressMonitor());
        if (loadedVersion != null) {
            migrationIsNeeded = RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(loadedVersion));
        }

        final XMIResource resource = doCreateAirdResourceImpl(uri);
        if (uri != null && uri.scheme() != null && Arrays.stream(GIT_SCHEME).anyMatch(uri.scheme()::equals)) {
            setOptions(resource, false, loadedVersion);
        } else {
            setOptions(resource, migrationIsNeeded, loadedVersion);
        }

        if (!resource.getEncoding().equals(XMI_ENCODING)) {
            resource.setEncoding(XMI_ENCODING);
        }

        if (migrationIsNeeded) {
            ResourceMigrationMarker.addMigrationMarker(resource);
        }
        return resource;
    }

    /**
     * Returns the implementation of the AirdResourceImpl to use.
     * 
     * @param uri
     *            the uri of the AirdResource
     * @return the implementation of the AirdResourceImpl to use
     */
    protected XMIResource doCreateAirdResourceImpl(URI uri) {
        return new AirDResourceImpl(uri);
    }

    /**
     * Sets the options to associate to the AirDResource.
     * 
     * @param resource
     *            the resource being loaded
     * @param migrationIsNeeded
     *            if a migration is needed.
     * @param loadedVersion
     *            the loaded version.
     */
    private void setOptions(XMIResource resource, boolean migrationIsNeeded, String loadedVersion) {

        final Map<Object, Object> loadOptions = new HashMap<Object, Object>();
        final Map<Object, Object> saveOptions = new HashMap<Object, Object>();
        /* default load options. */
        loadOptions.putAll(getDefaultLoadOptions());
        /* default save options. */
        saveOptions.putAll(getDefaultSaveOptions());

        loadOptions.put(XMLResource.OPTION_DEFER_ATTACHMENT, true);
        loadOptions.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, true);
        loadOptions.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, false);
        loadOptions.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl(true));
        loadOptions.put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, new HashMap<>());

        // extendedMetaData and resourceHandler

        if (migrationIsNeeded) {
            AirDResourceImpl.addMigrationOptions(loadedVersion, loadOptions, saveOptions);
        }

        loadOptions.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
        saveOptions.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);

        resource.getDefaultSaveOptions().putAll(saveOptions);
        resource.getDefaultLoadOptions().putAll(loadOptions);
    }

}
