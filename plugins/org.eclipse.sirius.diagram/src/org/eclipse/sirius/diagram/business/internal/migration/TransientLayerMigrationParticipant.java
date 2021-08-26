/*******************************************************************************
 * Copyright (c) 2017, 2021 THALES GLOBAL SERVICES..
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
package org.eclipse.sirius.diagram.business.internal.migration;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.model.business.internal.helper.LayerModelHelper;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.Decoration;
import org.osgi.framework.Version;

/**
 * Remove layers from DDiagram.activatedLayers that are now considered as transient and remove
 * DDiagramElement.decorations if there descriptions come from a layer that is now considered as transient.
 * 
 * @author lfasani
 *
 */
public class TransientLayerMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * Migration version.
     */
    public static final Version MIGRATION_VERSION = new Version("12.0.0.201704271200"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            TreeIterator<EObject> allContents = dAnalysis.eResource().getAllContents();
            while (allContents.hasNext()) {
                EObject eObject = allContents.next();
                if (eObject instanceof DDiagram) {
                    DDiagram diagram = (DDiagram) eObject;
                    List<Layer> activatedLayersToRemove = diagram.getActivatedLayers().stream().filter(LayerModelHelper::isTransientLayer).collect(Collectors.toList());
                    if (!activatedLayersToRemove.isEmpty()) {

                        diagram.getDiagramElements().stream().forEach(element -> {
                            Iterator<Decoration> decosIterator = element.getDecorations().iterator();
                            while (decosIterator.hasNext()) {
                                Decoration deco = decosIterator.next();
                                Layer parentLayer = LayerModelHelper.getParentLayer(deco.getDescription());
                                if (activatedLayersToRemove.contains(parentLayer)) {
                                    decosIterator.remove();
                                }
                            }
                        });
                        diagram.getActivatedLayers().removeAll(activatedLayersToRemove);
                        allContents.prune();
                    }
                }
            }
        }
    }
}
