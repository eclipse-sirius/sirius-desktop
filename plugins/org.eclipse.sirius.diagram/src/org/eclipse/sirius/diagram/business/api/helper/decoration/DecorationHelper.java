/*******************************************************************************
 * Copyright (c) 2017, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.helper.decoration;

import java.util.Collection;
import java.util.List;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.internal.helper.decoration.DecorationHelperInternal;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.model.LayerModelHelper;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.viewpoint.description.DecorationDescription;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * An helper for diagram decoration.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class DecorationHelper {

    /**
     * The diagram.
     */
    private DDiagram diagram;

    private DecorationHelperInternal decorationHelperInternal;

    /**
     * Create a new DecorationHelper for the given diagram.
     * 
     * @param diagram
     *            diagram to synchronize.
     */
    public DecorationHelper(DDiagram diagram) {
        this.decorationHelperInternal = new DecorationHelperInternal(diagram);
        this.diagram = diagram;
    }

    /**
     * Update decorations of all {@link DDiagramElement DDiagramElements} contained in the diagram and which
     * {@link DecorationDescription} is contained in provided {@link Layer Layers}. The layer must be activated
     * otherwise this method does nothing.
     * 
     * @param layers
     *            the layers
     */
    public void updateDecorations(List<Layer> layers) {
        Collection<DDiagramElement> dDiagramElements = Lists.newArrayList(Iterators.filter(diagram.eAllContents(), DDiagramElement.class));
        for (Layer layer : layers) {
            boolean transientLayer = LayerModelHelper.isTransientLayer(layer);
            List<Layer> activatedLayers = diagram.getActivatedLayers();
            List<AdditionalLayer> activatedTransientLayers = diagram.getActivatedTransientLayers();
            for (DDiagramElement diagElement : dDiagramElements) {
                if (transientLayer) {
                    decorationHelperInternal.deleteOrResetDecoration(diagElement, diagElement.getTransientDecorations(), activatedTransientLayers);
                } else {
                    decorationHelperInternal.deleteOrResetDecoration(diagElement, diagElement.getDecorations(), activatedLayers);
                }

                // add decoration only if the layer is activated
                if ((transientLayer && activatedTransientLayers.contains(layer)) || (!transientLayer && activatedLayers.contains(layer))) {
                    decorationHelperInternal.updateDecorationToAdd(diagElement, layer);
                }
            }
        }
    }

    /**
     * Update decorations of all {@link DDiagramElement DDiagramElements} contained in the diagram for all activated
     * {@link Layer Layers}.
     */
    public void updateAllDecorations() {
        Collection<DDiagramElement> dDiagramElements = Lists.newArrayList(Iterators.filter(diagram.eAllContents(), DDiagramElement.class));
        List<Layer> activatedLayers = diagram.getActivatedLayers();
        List<AdditionalLayer> activatedTransientLayers = diagram.getActivatedTransientLayers();
        List<Layer> layers = Lists.newArrayList(Iterables.concat(diagram.getActivatedLayers(), diagram.getActivatedTransientLayers()));
        for (DDiagramElement diagElement : dDiagramElements) {
            decorationHelperInternal.deleteOrResetDecoration(diagElement, diagElement.getTransientDecorations(), activatedTransientLayers);
            decorationHelperInternal.deleteOrResetDecoration(diagElement, diagElement.getDecorations(), activatedLayers);

            for (Layer layer : layers) {
                decorationHelperInternal.updateDecorationToAdd(diagElement, layer);
            }
        }
    }

}
