/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.migration;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.business.api.migration.AbstractMigrationParticipant;
import org.osgi.framework.Version;

import com.google.common.collect.ImmutableMap;

/**
 * The VSM and representations file migration participant for the migration from
 * Viewpoint nsURIs to Sirius nsURIs.
 * 
 * @author cbrun
 * 
 */
public class NsURIMigrationParticipant extends AbstractMigrationParticipant {

    /**
     * The version 7.0.0 corresponds to the file format of Sirius 0.9 (more
     * recent than the 6.9.0 file format released under the name "Viewpoint").
     */
    private static final Version MIGRATION_VERSION = new Version("7.0.0");

    private static final Map<String, String> NS_URI_MAPPINGS = ImmutableMap.<String, String> builder()
            .put("http://www.obeo.fr/dsl/viewpoint/description/contribution/1.0.0", "http://www.eclipse.org/sirius/description/contribution/1.0.0")
            .put("http://www.obeo.fr/dsl/viewpoint/1.1.0", "http://www.eclipse.org/sirius/1.1.0")
            .put("http://www.obeo.fr/dsl/viewpoint/description/1.1.0", "http://www.eclipse.org/sirius/description/1.1.0")
            .put("http://www.obeo.fr/dsl/viewpoint/description/style/1.1.0", "http://www.eclipse.org/sirius/description/style/1.1.0")
            .put("http://www.obeo.fr/dsl/viewpoint/description/tool/1.1.0", "http://www.eclipse.org/sirius/description/tool/1.1.0")
            .put("http://www.obeo.fr/dsl/viewpoint/description/filter/1.1.0", "http://www.eclipse.org/sirius/diagram/description/filter/1.1.0")
            .put("http://www.obeo.fr/dsl/viewpoint/description/validation/1.1.0", "http://www.eclipse.org/sirius/description/validation/1.1.0")
            .put("http://www.obeo.fr/dsl/viewpoint/description/audit/1.1.0", "http://www.eclipse.org/sirius/description/audit/1.1.0")
            .put("http://www.obeo.fr/dsl/viewpoint/description/concern/1.1.0", "http://www.eclipse.org/sirius/description/concern/1.1.0")
            .put("http://www.obeo.fr/dsl/layoutdata/1.1.0", "http://www.eclipse.org/sirius/dsl/layoutdata/1.1.0")
            .put("http://www.obeo.fr/dsl/viewpoint/diagram/sequence/2.0.0", "http://www.eclipse.org/sirius/diagram/sequence/2.0.0")
            .put("http://www.obeo.fr/dsl/viewpoint/diagram/sequence/description/2.0.0", "http://www.eclipse.org/sirius/diagram/sequence/description/2.0.0")
            .put("http://www.obeo.fr/dsl/viewpoint/diagram/sequence/description/tool/2.0.0", "http://www.eclipse.org/sirius/diagram/sequence/description/tool/2.0.0")
            .put("http://www.obeo.fr/dsl/viewpoint/diagram/sequence/ordering/2.0.0", "http://www.eclipse.org/sirius/diagram/sequence/ordering/2.0.0")
            .put("http://www.obeo.fr/dsl/viewpoint/diagram/sequence/template/2.0.0", "http://www.eclipse.org/sirius/diagram/sequence/template/2.0.0")
            .put("http://www.obeo.fr/dsl/viewpoint/table/1.1.0", "http://www.eclipse.org/sirius/table/1.1.0")
            .put("http://www.obeo.fr/dsl/viewpoint/table/description/1.1.0", "http://www.eclipse.org/sirius/table/description/1.1.0")
            .put("http://www.obeo.fr/dsl/viewpoint/tree/1.0.0", "http://www.eclipse.org/sirius/tree/1.0.0")
            .put("http://www.obeo.fr/dsl/viewpoint/tree/description/1.0.0", "http://www.eclipse.org/sirius/tree/description/1.0.0").build();

    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EPackage getPackage(String namespace, String loadedVersion) {
        if (Version.parseVersion(loadedVersion).compareTo(MIGRATION_VERSION) < 0) {
            if (namespace != null) {
                String mapTo = NS_URI_MAPPINGS.get(namespace);
                if (mapTo != null) {
                    EPackage found = EPackage.Registry.INSTANCE.getEPackage(mapTo);
                    if (found != null) {
                        return found;
                    }
                }
            }
        }
        return super.getPackage(namespace, loadedVersion);
    }

}
