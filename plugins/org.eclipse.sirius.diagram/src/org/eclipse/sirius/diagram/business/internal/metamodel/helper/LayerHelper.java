/*******************************************************************************
 * Copyright (c) 2008, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.metamodel.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.model.LayerModelHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.model.MappingHelper;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.description.AbstractMappingImport;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

/**
 * An helper to deal with layers stuffs.
 * 
 * @author mchauvin
 */
public final class LayerHelper {

    private static final String ENABLE_ACTIVE_PARENT_LAYERS_OPTIMIZATION_SYSTEM_PROPERTY = "org.eclipse.sirius.diagram.enableActiveParentLayersOptimization"; //$NON-NLS-1$

    private static final Map<DiagramMappingsManager, Map<DiagramElementMapping, Collection<Layer>>> ACTIVE_PARENT_LAYER_CACHE = new ConcurrentHashMap<>();

    /**
     * Avoid instantiation.
     */
    private LayerHelper() {
    }

    /**
     * return all Layers which use this mapping if available.
     * 
     * @param mapping
     *            any {@link DiagramElementMapping}.
     * @return a containing Layer if available.
     */
    public static Collection<Layer> getParentLayers(final DiagramElementMapping mapping) {

        final Set<Layer> layers = new LinkedHashSet<Layer>();

        /* add containing layer */
        final Layer parentLayer = LayerModelHelper.getContainingLayer(mapping);
        if (parentLayer != null) {
            layers.add(parentLayer);
        }

        /* add layers which reuse this mapping */
        final EObject registryMappingInstance = ViewpointRegistry.getInstance().find(mapping);
        final Collection<Setting> settings = ViewpointRegistry.getInstance().getCrossReferencer().getInverseReferences(registryMappingInstance);
        for (final Setting setting : settings) {
            final EObject eReferencer = setting.getEObject();
            final EStructuralFeature eFeature = setting.getEStructuralFeature();
            if (eReferencer instanceof Layer && eFeature.equals(DescriptionPackage.eINSTANCE.getLayer_ReusedMappings())) {
                layers.add((Layer) eReferencer);
            } else if (eFeature.equals(DescriptionPackage.eINSTANCE.getContainerMapping_ReusedNodeMappings())
                    || eFeature.equals(DescriptionPackage.eINSTANCE.getContainerMapping_ReusedContainerMappings())
                    || eFeature.equals(DescriptionPackage.eINSTANCE.getAbstractNodeMapping_ReusedBorderedNodeMappings())) {
                final Layer eReferencerLayer = LayerModelHelper.getContainingLayer((DiagramElementMapping) eReferencer);
                final Layer mappingSourceLayer = LayerModelHelper.getContainingLayer(mapping);
                // add eReferencerLayer if reusedMapping and its re-user are not
                // in the same diagram.
                if (eReferencerLayer != null && !layers.contains(eReferencerLayer) && !EqualityHelper.areEquals(mappingSourceLayer.eContainer(), eReferencerLayer.eContainer())) {
                    layers.add(eReferencerLayer);
                }
            }
        }
        /* add layers which reuse a container of this mapping */
        if (mapping.eContainer() instanceof ContainerMapping) {
            layers.addAll(LayerHelper.getParentLayers((ContainerMapping) mapping.eContainer()));
        }

        return layers;
    }

    /**
     * Check if a diagram element is in an activated layer or not and visible.
     * 
     * @param mappingsManager
     *            the DiagramMappingsManager of the current diagram.
     * @param element
     *            the diagram element.
     * @return <code>true</code> if it is, <code>false</code> otherwise
     */
    public static boolean isInActivatedLayer(DiagramMappingsManager mappingsManager, final DDiagramElement element) {
        return isInActivatedLayer(mappingsManager, element, element.getParentDiagram());
    }

    /**
     * Check if a diagram element is in an activated layer or not and visible.
     * 
     * @param mappingsManager
     *            the DiagramMappingsManager of the current diagram.
     * @param element
     *            the diagram element.
     * @param parentDiagram
     *            the parent diagram of the diagram element. This information can be retrieved from the diagram element
     *            but sometimes it is already known by the caller or it can be null (during drag'n'drop of element with
     *            bordered nodes for example : PortLocationAfterDragAndDropTest.
     *            testPortLocationFromParentDnDFromModelExplorerView()) this method is called before setting all parents
     *            hierarchy of diagram element.
     * @return <code>true</code> if it is, <code>false</code> otherwise
     */
    public static boolean isInActivatedLayer(DiagramMappingsManager mappingsManager, final DDiagramElement element, final DDiagram parentDiagram) {
        final DiagramElementMapping mapping = element.getDiagramElementMapping();

        if (!LayerModelHelper.withoutLayersMode(mapping)) {
            final DDiagram diagram;
            if (parentDiagram != null) {
                diagram = parentDiagram;
            } else {
                diagram = element.getParentDiagram();
            }

            boolean visible = false;
            if (diagram != null && getActiveParentLayers(mappingsManager, mapping).size() > 0) {
                /*
                 * We are visible in the following cases: 1. the mapping is in active layer and not hidden by owner
                 * mapping in an active layer and container is diagram 2- the mapping is in active layer and not hidden
                 * by owner mapping in an active layer and container is element and element.mapping contains mapping and
                 * element is visible
                 */

                /*
                 * Check that mapping is not hidden by an importer mapping
                 */

                final EObject registryMappingInstance = ViewpointRegistry.getInstance().find(mapping);
                final Collection<Setting> settings = ViewpointRegistry.getInstance().getCrossReferencer().getInverseReferences(registryMappingInstance, true);

                if (!LayerHelper.hideSubMappingsInImporters(mappingsManager, diagram, settings, mapping)) {
                    final EObject container = element.eContainer();

                    /*
                     * Case 2 The mapping should be imported by another mapping owned by a visible element.
                     */
                    if (container instanceof DDiagramElement && LayerHelper.isInActivatedLayer(mappingsManager, (DDiagramElement) container, parentDiagram)) {
                        visible = LayerHelper.caseDiagramElementContainer((DDiagramElement) container, mapping);
                    }
                    /*
                     * Case 1 The mapping should be in visible mappings set
                     */
                    else if (container instanceof DDiagram) {
                        visible = LayerHelper.caseDiagramContainer(diagram, mapping);
                    } else if (container == null) {
                        // We consider the diagram as future container.
                        visible = LayerHelper.caseDiagramContainer(parentDiagram, mapping);
                    }
                }
            }

            return visible;
        }
        return true;
    }

    private static boolean caseDiagramElementContainer(final DDiagramElement container, final DiagramElementMapping mapping) {
        final DiagramElementMapping containerMapping = container.getDiagramElementMapping();
        if (MappingHelper.getAllMappings(containerMapping).contains(mapping)) {
            return true;
        }
        return false;
    }

    private static boolean caseDiagramContainer(final DDiagram diagram, final DiagramElementMapping mapping) {
        for (Layer activatedLayer : diagram.getActivatedLayers()) {
            if (EqualityHelper.contains(LayerModelHelper.getAllLayerMappings(activatedLayer), mapping)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hideSubMappingsInImporters(final DiagramMappingsManager session, final DDiagram diagram, final Collection<Setting> settings, final DiagramElementMapping mapping) {

        boolean hide = false;

        for (final Setting setting : settings) {
            final EObject eObject = setting.getEObject();
            final EStructuralFeature eFeature = setting.getEStructuralFeature();
            if (eObject instanceof AbstractMappingImport && eObject instanceof DiagramElementMapping) {
                if (eFeature == DescriptionPackage.eINSTANCE.getContainerMappingImport_ImportedMapping() || eFeature == DescriptionPackage.eINSTANCE.getNodeMappingImport_ImportedMapping()) {

                    final DiagramElementMapping importerMapping = (DiagramElementMapping) eObject;

                    if (((AbstractMappingImport) importerMapping).isHideSubMappings() && LayerHelper.isInActivatedLayer(session, diagram, importerMapping)) {
                        hide = true;
                        break;

                    } else {
                        final Collection<Setting> importerSettings = ViewpointRegistry.getInstance().getCrossReferencer().getInverseReferences(importerMapping);
                        if (LayerHelper.hideSubMappingsInImporters(session, diagram, importerSettings, importerMapping)) {
                            hide = true;
                            break;
                        }
                    }
                }
            }
        }
        return hide;
    }

    /**
     * Check if a diagram element mapping is in an activated layer or not.
     * 
     * @param mappingsManager
     *            the DiagramMappingsManager of the current diagram.
     * @param mapping
     *            the diagram element mapping.
     * @param diagram
     *            the diagram.
     * @return <code>true</code> if it is, <code>false</code> otherwise
     */
    public static boolean isInActivatedLayer(DiagramMappingsManager mappingsManager, final DDiagram diagram, final DiagramElementMapping mapping) {
        if (!LayerModelHelper.withoutLayersMode(mapping)) {

            boolean visible = false;

            final Collection<Layer> layers = getActiveParentLayers(mappingsManager, mapping);
            for (final Layer layer : layers) {
                if (EqualityHelper.contains(diagram.getActivatedLayers(), layer)) {

                    final EObject registryMappingInstance = ViewpointRegistry.getInstance().find(mapping);
                    final Collection<Setting> settings = ViewpointRegistry.getInstance().getCrossReferencer().getInverseReferences(registryMappingInstance);

                    if (!LayerHelper.hideSubMappingsInImporters(mappingsManager, diagram, settings, mapping)) {
                        visible = true;
                        break;
                    }
                }
            }
            return visible;
        }
        return true;
    }

    /**
     * Enable or disable the ability to cache the computed parent layers for the given DiagramMappingManager. The cache
     * is cleared when this method is called to disable the cache.
     * 
     * This method does nothing if the optimization has been disabled with the system property
     * "org.eclipse.sirius.diagram.enableActiveParentLayersOptimization" set to false.
     * 
     * @param mappingsManager
     *            DiagramMappingsManager of the current diagram.
     * @param enable
     *            <code>true</code> to allow this helper to put the computed active parent layers in a cache,
     *            <code>false</code> otherwise.
     */
    public static synchronized void setActiveParentLayersCacheEnabled(DiagramMappingsManager mappingsManager, boolean enable) {
        if (!Boolean.valueOf(System.getProperty(ENABLE_ACTIVE_PARENT_LAYERS_OPTIMIZATION_SYSTEM_PROPERTY, "true"))) { //$NON-NLS-1$
            return;
        }

        if (enable && !ACTIVE_PARENT_LAYER_CACHE.containsKey(mappingsManager)) {
            ACTIVE_PARENT_LAYER_CACHE.put(mappingsManager, new ConcurrentHashMap<>());
        }

        if (!enable) {
            ACTIVE_PARENT_LAYER_CACHE.remove(mappingsManager);
        }
    }

    private static Collection<Layer> getActiveParentLayers(DiagramMappingsManager mappingsManager, final DiagramElementMapping mapping) {
        Map<DiagramElementMapping, Collection<Layer>> managerCache = ACTIVE_PARENT_LAYER_CACHE.get(mappingsManager);
        if (managerCache != null) {
            Collection<Layer> cachedValue = managerCache.get(mapping);
            if (cachedValue == null) {
                cachedValue = mappingsManager.getActiveParentLayers(mapping);
                managerCache.put(mapping, cachedValue);
            }
            return cachedValue;
        }

        return mappingsManager.getActiveParentLayers(mapping);
    }

    /**
     * Update the actual mapping of the element with mapping given as parameter.
     * 
     * @param element
     *            the diagram element to update
     * @param mapping
     *            the mapping to use
     */
    public static void updateActualMapping(final DDiagramElement element, final DiagramElementMapping mapping) {
        if (element instanceof DNode && mapping instanceof NodeMapping) {
            DNode node = (DNode) element;
            if (node.getActualMapping() != mapping) {
                node.setActualMapping((NodeMapping) mapping);
            }
        } else if (element instanceof DDiagramElementContainer && mapping instanceof ContainerMapping) {
            DDiagramElementContainer ddec = (DDiagramElementContainer) element;
            if (ddec.getActualMapping() != mapping) {
                ddec.setActualMapping((ContainerMapping) mapping);
            }
        }
    }

    /**
     * Get the transient or non transient layers to activate.
     * 
     * @param diagDescription
     *            the {@link DiagramDescription} to get the layers from
     * @param viewpointsFilter
     *            viewpoints to consider
     * @param layersToActivate
     *            returned non transient layers to activate
     * @param transientLayersToActivate
     *            returned transient layers to activate
     */
    public static void getInitialActiveLayers(DiagramDescription diagDescription, Collection<Viewpoint> viewpointsFilter, List<Layer> layersToActivate,
            List<AdditionalLayer> transientLayersToActivate) {
        final Predicate<Layer> isActiveByDefault = new Predicate<Layer>() {
            @Override
            public boolean apply(final Layer layer) {
                boolean result = true;
                if (layer instanceof AdditionalLayer) {
                    AdditionalLayer additionalLayer = (AdditionalLayer) layer;
                    result = additionalLayer.isActiveByDefault() || !additionalLayer.isOptional();
                }
                return result;
            }
        };

        getFilteredLayers(diagDescription, viewpointsFilter, layersToActivate, transientLayersToActivate, isActiveByDefault);
    }

    private static void getFilteredLayers(DiagramDescription diagDescription, Collection<Viewpoint> viewpointsFilter, List<Layer> layers, List<AdditionalLayer> transientLayers,
            Predicate<Layer> predicate) {
        Collection<Layer> allLayers = new ArrayList<Layer>(new DiagramComponentizationManager().getAllLayers(viewpointsFilter, diagDescription));

        Collection<Layer> allActivatedLayers = Collections2.filter(allLayers, predicate);
        allActivatedLayers.addAll(Collections2.filter(DiagramComponentizationHelper.getContributedLayers(diagDescription, viewpointsFilter), predicate));

        for (Layer layer : allActivatedLayers) {
            if (LayerModelHelper.isTransientLayer(layer)) {
                transientLayers.add((AdditionalLayer) layer);
            } else {
                layers.add(layer);
            }
        }
    }

    /**
     * Get, from descriptions, the list of mandatories layers.
     * 
     * @param diagDescription
     *            the {@link DiagramDescription} to get the layers from
     * @param viewpointsFilter
     *            viewpoints to consider
     * @param layers
     *            returned non transient layers to activate
     * @param transientLayers
     *            returned transient layers to activate
     */
    public static void getMandatoriesAdditionalLayers(DiagramDescription diagDescription, Collection<Viewpoint> viewpointsFilter, List<Layer> layers, List<AdditionalLayer> transientLayers) {
        final Predicate<Layer> isMandatory = new Predicate<Layer>() {
            @Override
            public boolean apply(final Layer layer) {
                return (layer instanceof AdditionalLayer) && !((AdditionalLayer) layer).isOptional();
            }
        };
        getFilteredLayers(diagDescription, viewpointsFilter, layers, transientLayers, isMandatory);
    }

}
