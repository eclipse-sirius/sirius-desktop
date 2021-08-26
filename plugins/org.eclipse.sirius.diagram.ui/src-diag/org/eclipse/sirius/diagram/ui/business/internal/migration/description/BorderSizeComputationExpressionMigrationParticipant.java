/*******************************************************************************
 * Copyright (c) 2015, 2021 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.migration.description;

import java.util.List;

import org.eclipse.sirius.business.api.migration.AbstractVSMMigrationParticipant;
import org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.diagram.model.business.internal.helper.LayerModelHelper;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;

/**
 * A VSM migration to replace all 0 default values of the borderSizeComputationExpression of BorderedStyleDescription of
 * ContainerMapping by 1 to avoid a change of behavior for existing VSM.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 *
 */
public class BorderSizeComputationExpressionMigrationParticipant extends AbstractVSMMigrationParticipant {

    /**
     * The version for which this migration is added.
     */
    public static final Version MIGRATION_VERSION = new Version("11.0.0.201511131800"); //$NON-NLS-1$

    /**
     * The initial migration version.
     */
    public static final Version INITIAL_MIGRATION_VERSION = new Version("10.1.0.201507101000"); //$NON-NLS-1$

    /**
     * The 3.1.3 migration correction version.
     */
    public static final Version ALREADY_MIGRATED_VERSION = new Version("10.1.3.201511131800"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    protected void postLoad(Group group, Version loadedVersion) {
        // Set the borderSizeComputationExpression to "1" (to replace 0 default
        // value) for style of container mapping, before the bug 465211 0 is
        // considered as 1.

        // Replace all borderSize of Container equals to 0 by 1 and replace all
        // borderSizeComputationExpression of Container equals to "0" by "1". If
        // the expression is not "0" (default value), we let the borderSize
        // unchanged.
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            // The Sirius 3.1.3 maintenance version already contains the
            // complete migration.
            // Full migration is required for version in [0.0.0, 3.1.0[
            // Manual complementary modification might be required for version
            // in [3.1.0, 3.1.3[

            if (loadedVersion.compareTo(INITIAL_MIGRATION_VERSION) < 0) {
                // Full migration
                migrateBorderSizeComputationExpressions(group);
            } else if (loadedVersion.compareTo(ALREADY_MIGRATED_VERSION) < 0) {
                // Do nothing:
                // first level of container mappings have been migrated.
                // sub container mappings were with the previous version of the
                // migration but in Sirius 3.1.0, specifier might have
                // explicitly added styles with a 0 pix border: the migration
                // cannot take the decision to do the migration or not.
            } else {
                // Files saved with 3.1.3, are already migrated.
            }
        }
    }

    private void migrateBorderSizeComputationExpressions(Group group) {
        for (Viewpoint viewpoint : group.getOwnedViewpoints()) {
            for (DiagramDescription diagramDescription : Iterables.filter(viewpoint.getOwnedRepresentations(), DiagramDescription.class)) {
                // Migrate container mappings from default and additional layers
                for (Layer layer : LayerModelHelper.getAllLayers(diagramDescription)) {
                    // Migrate container mappings contained by the layer (no
                    // need to migrate reused mappings, they will be migrated by
                    // the migration of their containing resource).
                    migrateBorderSizeComputationExpressions(layer.getContainerMappings());
                }

                // Migrate container mappings contained by the diagram
                // description (old without layer mode)
                migrateBorderSizeComputationExpressions(diagramDescription.getContainerMappings());
            }
            for (DiagramExtensionDescription diagramExtensionDescription : Iterables.filter(viewpoint.getOwnedRepresentationExtensions(), DiagramExtensionDescription.class)) {
                for (Layer layer : diagramExtensionDescription.getLayers()) {
                    migrateBorderSizeComputationExpressions(layer.getContainerMappings());
                }
            }
        }
    }

    /**
     * Recursively migrate the given containers and their sub containers.
     * 
     * @param containerMappings
     *            the container mappings to migrate.
     */
    private void migrateBorderSizeComputationExpressions(List<ContainerMapping> containerMappings) {
        for (ContainerMapping containerMapping : containerMappings) {

            // Migrate the styles of the current container mapping.
            migrateBorderSizeComputationExpression(containerMapping.getStyle());
            for (ConditionalContainerStyleDescription conditionalStyleDescription : containerMapping.getConditionnalStyles()) {
                migrateBorderSizeComputationExpression(conditionalStyleDescription.getStyle());
            }

            // Migrate the sub container mappings.
            migrateBorderSizeComputationExpressions(containerMapping.getSubContainerMappings());
        }
    }

    private void migrateBorderSizeComputationExpression(ContainerStyleDescription styleDescription) {
        if (styleDescription != null && "0".equals(styleDescription.getBorderSizeComputationExpression())) { //$NON-NLS-1$
            styleDescription.setBorderSizeComputationExpression("1"); //$NON-NLS-1$
        }
    }
}
