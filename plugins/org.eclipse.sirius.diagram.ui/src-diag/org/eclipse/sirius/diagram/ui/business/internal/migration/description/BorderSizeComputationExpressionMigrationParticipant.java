/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;

/**
 * A VSM migration to replace all 0 default values of the
 * borderSizeComputationExpression of BorderedStyleDescription of
 * ContainerMapping by 1 to avoid a change of behavior for existing VSM.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 *
 */
public class BorderSizeComputationExpressionMigrationParticipant extends AbstractVSMMigrationParticipant {

    /**
     * The version for which this migration is added.
     */
    private static final Version MIGRATION_VERSION = new Version("10.1.0.201507101000"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    protected void postLoad(Group group, Version loadedVersion) {
        // Set the borderSizeComputationExpression to "1" (to replace 0 default
        // value) for style of container mapping, before the bug 465211 0 is
        // considered as 1.
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            for (Viewpoint viewpoint : group.getOwnedViewpoints()) {
                for (DiagramDescription diagramDescription : Iterables.filter(viewpoint.getOwnedRepresentations(), DiagramDescription.class)) {
                    migrateStyleOfContainerMappingInLayer(diagramDescription.getAllLayers());
                    migrateStyleOfContainerMapping(diagramDescription.getAllContainerMappings());
                }
                for (DiagramExtensionDescription diagramExtensionDescription : Iterables.filter(viewpoint.getOwnedRepresentationExtensions(), DiagramExtensionDescription.class)) {
                    migrateStyleOfContainerMappingInLayer(diagramExtensionDescription.getLayers());
                }
            }
        }
    }

    private void migrateStyleOfContainerMappingInLayer(List<? extends Layer> layers) {
        for (Layer layer : layers) {
            migrateStyleOfContainerMapping(layer.getContainerMappings());
        }
    }

    private void migrateStyleOfContainerMapping(List<ContainerMapping> containerMappings) {
        for (ContainerMapping containerMapping : containerMappings) {
            migrateStyleOfContainerMapping(containerMapping.getStyle());
            for (ConditionalContainerStyleDescription conditionalStyleDescription : containerMapping.getConditionnalStyles()) {
                migrateStyleOfContainerMapping(conditionalStyleDescription.getStyle());
            }
        }
    }

    private void migrateStyleOfContainerMapping(ContainerStyleDescription styleDescription) {
        if (styleDescription != null && "0".equals(styleDescription.getBorderSizeComputationExpression())) { //$NON-NLS-1$
            styleDescription.setBorderSizeComputationExpression("1"); //$NON-NLS-1$
        }
    }
}
