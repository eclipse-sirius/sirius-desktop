/**
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.viewpoint.description.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.model.business.internal.migration.IMigrationHandler;
import org.eclipse.sirius.model.tools.internal.SiriusModelPlugin;

/**
 * <!-- begin-user-doc --> The <b>Resource Factory</b> associated with the package. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.viewpoint.description.util.DescriptionResourceImpl
 * @generated
 */
public class DescriptionResourceFactoryImpl extends ResourceFactoryImpl {

    /** Handler used to configure migration process */
    private Optional<IMigrationHandler> migrationHandler;

    /**
     * Creates an instance of the resource factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    public DescriptionResourceFactoryImpl() {
        super();
        migrationHandler = Optional.ofNullable(SiriusModelPlugin.getDefault().getMigrationHandler());
    }

    /**
     * Creates an instance of the resource. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public Resource createResource(URI uri) {
        XMIResource resource = new DescriptionResourceImpl(uri);

        // set load options
        final Map<Object, Object> loadOptions = new HashMap<>();
        loadOptions.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);

        // set save options
        final Map<Object, Object> saveOptions = new HashMap<>();
        saveOptions.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);

        // handle migration option

        if (migrationHandler.isPresent()) {
            migrationHandler.get().addMigrationOptionIfNeeded(uri, loadOptions, saveOptions);
        }

        resource.getDefaultLoadOptions().putAll(loadOptions);
        resource.getDefaultSaveOptions().putAll(saveOptions);

        return resource;
    }

} // DescriptionResourceFactoryImpl
