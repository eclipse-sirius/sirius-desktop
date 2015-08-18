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

    private static final String SIRIUS_DESCRIPTION_CONCERN_1_1_0 = "http://www.eclipse.org/sirius/description/concern/1.1.0"; //$NON-NLS-1$

    private static final String SIRIUS_DIAGRAM_DESCRIPTION_CONCERN_1_1_0 = "http://www.eclipse.org/sirius/diagram/description/concern/1.1.0"; //$NON-NLS-1$

    private static final String SIRIUS_DESCRIPTION_FILTER_1_1_0 = "http://www.eclipse.org/sirius/description/filter/1.1.0"; //$NON-NLS-1$

    private static final String SIRIUS_DIAGRAM_DESCRIPTION_FILTER_1_1_0 = "http://www.eclipse.org/sirius/diagram/description/filter/1.1.0"; //$NON-NLS-1$

    private static final String SIRIUS_DESCRIPTION_VALIDATION_1_1_0 = "http://www.eclipse.org/sirius/description/validation/1.1.0"; //$NON-NLS-1$

    private static final String SIRIUS_DIAGRAM_DESCRIPTION_VALIDATION_1_1_0 = "http://www.eclipse.org/sirius/diagram/description/validation/1.1.0"; //$NON-NLS-1$

    /**
     * The version 7.0.0 corresponds to the file format of Sirius 0.9 (more
     * recent than the 6.9.0 file format released under the name "Viewpoint").
     */
    private static final Version SIRIUS_0_9_VERSION = new Version("7.0.0"); //$NON-NLS-1$

    /**
     * The version 8.0.0 corresponds to the file format of Sirius 1.0.0 M5.
     */
    private static final Version SIRIUS_1_0_0_M5_VERSION = new Version("8.0.0"); //$NON-NLS-1$

    /**
     * The version 8.1.0 corresponds to the file format of Sirius 1.0.0 M6.
     */
    private static final Version SIRIUS_1_0_0_M6_VERSION = new Version("8.1.0"); //$NON-NLS-1$

    private static final Map<String, String> FROM_VIEWPOINT_NS_URI_MAPPINGS = ImmutableMap.<String, String> builder()
            .put("http://www.obeo.fr/dsl/viewpoint/description/contribution/1.0.0", "http://www.eclipse.org/sirius/description/contribution/1.0.0") //$NON-NLS-1$ //$NON-NLS-2$
            .put("http://www.obeo.fr/dsl/viewpoint/1.1.0", "http://www.eclipse.org/sirius/1.1.0") //$NON-NLS-1$ //$NON-NLS-2$
            .put("http://www.obeo.fr/dsl/viewpoint/description/1.1.0", "http://www.eclipse.org/sirius/description/1.1.0") //$NON-NLS-1$ //$NON-NLS-2$
            .put("http://www.obeo.fr/dsl/viewpoint/description/style/1.1.0", "http://www.eclipse.org/sirius/description/style/1.1.0") //$NON-NLS-1$ //$NON-NLS-2$
            .put("http://www.obeo.fr/dsl/viewpoint/description/tool/1.1.0", "http://www.eclipse.org/sirius/description/tool/1.1.0") //$NON-NLS-1$ //$NON-NLS-2$
            .put("http://www.obeo.fr/dsl/viewpoint/description/audit/1.1.0", "http://www.eclipse.org/sirius/description/audit/1.1.0") //$NON-NLS-1$ //$NON-NLS-2$
            .put("http://www.obeo.fr/dsl/layoutdata/1.1.0", "http://www.eclipse.org/sirius/dsl/layoutdata/1.1.0") //$NON-NLS-1$ //$NON-NLS-2$
            .put("http://www.obeo.fr/dsl/viewpoint/diagram/sequence/2.0.0", "http://www.eclipse.org/sirius/diagram/sequence/2.0.0") //$NON-NLS-1$ //$NON-NLS-2$
            .put("http://www.obeo.fr/dsl/viewpoint/diagram/sequence/description/2.0.0", "http://www.eclipse.org/sirius/diagram/sequence/description/2.0.0") //$NON-NLS-1$ //$NON-NLS-2$
            .put("http://www.obeo.fr/dsl/viewpoint/diagram/sequence/description/tool/2.0.0", "http://www.eclipse.org/sirius/diagram/sequence/description/tool/2.0.0") //$NON-NLS-1$ //$NON-NLS-2$
            .put("http://www.obeo.fr/dsl/viewpoint/diagram/sequence/ordering/2.0.0", "http://www.eclipse.org/sirius/diagram/sequence/ordering/2.0.0") //$NON-NLS-1$ //$NON-NLS-2$
            .put("http://www.obeo.fr/dsl/viewpoint/diagram/sequence/template/2.0.0", "http://www.eclipse.org/sirius/diagram/sequence/template/2.0.0") //$NON-NLS-1$ //$NON-NLS-2$
            .put("http://www.obeo.fr/dsl/viewpoint/table/1.1.0", "http://www.eclipse.org/sirius/table/1.1.0") //$NON-NLS-1$ //$NON-NLS-2$
            .put("http://www.obeo.fr/dsl/viewpoint/table/description/1.1.0", "http://www.eclipse.org/sirius/table/description/1.1.0") //$NON-NLS-1$ //$NON-NLS-2$
            .put("http://www.obeo.fr/dsl/viewpoint/tree/1.0.0", "http://www.eclipse.org/sirius/tree/1.0.0") //$NON-NLS-1$ //$NON-NLS-2$
            .put("http://www.obeo.fr/dsl/viewpoint/tree/description/1.0.0", "http://www.eclipse.org/sirius/tree/description/1.0.0") //$NON-NLS-1$ //$NON-NLS-2$
            .put("http://www.obeo.fr/dsl/viewpoint/description/concern/1.1.0", SIRIUS_DIAGRAM_DESCRIPTION_CONCERN_1_1_0) //$NON-NLS-1$
            .put("http://www.obeo.fr/dsl/viewpoint/description/filter/1.1.0", SIRIUS_DIAGRAM_DESCRIPTION_FILTER_1_1_0) //$NON-NLS-1$
            .put("http://www.obeo.fr/dsl/viewpoint/description/validation/1.1.0", SIRIUS_DESCRIPTION_VALIDATION_1_1_0).build(); //$NON-NLS-1$

    private static final Map<String, String> FROM_SIRIUS_0_9_NS_URI_MAPPINGS = ImmutableMap.<String, String> builder().put(SIRIUS_DESCRIPTION_CONCERN_1_1_0, SIRIUS_DIAGRAM_DESCRIPTION_CONCERN_1_1_0)
            .put(SIRIUS_DESCRIPTION_FILTER_1_1_0, SIRIUS_DIAGRAM_DESCRIPTION_FILTER_1_1_0).build();

    private static final Map<String, String> FROM_SIRIUS_1_0_0_M5_NS_URI_MAPPINGS = ImmutableMap.<String, String> builder()
            .put(SIRIUS_DIAGRAM_DESCRIPTION_VALIDATION_1_1_0, SIRIUS_DESCRIPTION_VALIDATION_1_1_0).build();

    public Version getMigrationVersion() {
        return SIRIUS_1_0_0_M6_VERSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EPackage getPackage(String namespace, String loadedVersion) {
        if (namespace != null) {
            String mapTo = null;
            if (Version.parseVersion(loadedVersion).compareTo(SIRIUS_0_9_VERSION) < 0) {
                mapTo = FROM_VIEWPOINT_NS_URI_MAPPINGS.get(namespace);
            } else if (Version.parseVersion(loadedVersion).compareTo(SIRIUS_1_0_0_M5_VERSION) < 0) {
                mapTo = FROM_SIRIUS_0_9_NS_URI_MAPPINGS.get(namespace);
            } else if (Version.parseVersion(loadedVersion).compareTo(SIRIUS_1_0_0_M6_VERSION) < 0) {
                mapTo = FROM_SIRIUS_1_0_0_M5_NS_URI_MAPPINGS.get(namespace);
            }

            if (mapTo != null) {
                EPackage found = EPackage.Registry.INSTANCE.getEPackage(mapTo);
                if (found != null) {
                    return found;
                }
            }
        }
        return super.getPackage(namespace, loadedVersion);
    }
}
