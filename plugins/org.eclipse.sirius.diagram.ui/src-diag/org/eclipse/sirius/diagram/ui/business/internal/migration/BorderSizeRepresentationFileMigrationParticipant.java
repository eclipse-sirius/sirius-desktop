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
package org.eclipse.sirius.diagram.ui.business.internal.migration;

import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;

/**
 * A representation file migration to replace all 0 default values of the
 * borderSize/borderSizeComputationExpression of BorderedStyle of Container by 1
 * to avoid a change of behavior for existing representations. Before Sirius
 * 3.1.0, this replacement was made in the figure configuration and has been
 * stopped because we now support a border of 0 pixel for container.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 *
 */
public class BorderSizeRepresentationFileMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * The VP version for which this migration is added.
     */
    private static final Version MIGRATION_VERSION = new Version("10.1.0.201507101000"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        // Replace all borderSize of Container equals to 0 by 1 and replace all
        // borderSizeComputationExpression of Container equals to "0" by "1". If
        // the expression is not "0" (default value), we let the borderSize
        // unchanged.
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            for (DView dView : dAnalysis.getOwnedViews()) {
                for (DDiagram dDiagram : Iterables.filter(dView.getOwnedRepresentations(), DDiagram.class)) {
                    for (DDiagramElementContainer diagramElement : dDiagram.getContainers()) {
                        if (diagramElement.getStyle() instanceof BorderedStyle) {
                            BorderedStyle containerBorderedStyle = (BorderedStyle) diagramElement.getStyle();
                            if ("0".equals(containerBorderedStyle.getBorderSizeComputationExpression())) { //$NON-NLS-1$
                                containerBorderedStyle.setBorderSizeComputationExpression("1"); //$NON-NLS-1$
                                if (containerBorderedStyle.getBorderSize().intValue() == 0) {
                                    containerBorderedStyle.setBorderSize(1);
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}
