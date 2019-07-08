/*******************************************************************************
 * Copyright (c) 2008, 2018 THALES GLOBAL SERVICES.
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
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
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.EdgeMappingImportWrapper;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.description.AbstractMappingImport;
import org.eclipse.sirius.viewpoint.description.DecorationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

/**
 * An helper to deal with layers stuffs.
 * 
 * @author mchauvin
 */
public final class LayerHelper {

    /**
     * Avoid instantiation.
     */
    private LayerHelper() {
    }

    /**
     * Get all the layers of a diagram description.
     * 
     * @param description
     *            the diagram description
     * @return all the layers
     */
    public static EList<Layer> getAllLayers(final DiagramDescription description) {

        final Collection<Layer> layers = new ArrayList<Layer>();
        if (description.getDefaultLayer() != null) {
            layers.add(description.getDefaultLayer());
        }
        layers.addAll(description.getAdditionalLayers());
        return new BasicEList<>(layers);
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
        final Layer parentLayer = LayerHelper.getContainingLayer(mapping);
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
                final Layer eReferencerLayer = LayerHelper.getContainingLayer((DiagramElementMapping) eReferencer);
                final Layer mappingSourceLayer = LayerHelper.getContainingLayer(mapping);
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
     * return the layer which contains this mapping if available.
     * 
     * @param mapping
     *            the diagram element mapping
     * @return the layer containing if there is one, <code>null</code> otherwise
     */
    public static Layer getContainingLayer(final DiagramElementMapping mapping) {
        EObject current = mapping;
        while (current != null) {
            current = current.eContainer();
            if (current instanceof Layer) {
                return (Layer) current;
            }
        }
        return null;
    }

    /**
     * return a containing Layer if available.
     * 
     * @param decorationDescription
     *            any {@link DecorationDescription}.
     * @return a containing Layer if available.
     */
    public static Layer getParentLayer(final DecorationDescription decorationDescription) {
        EObject current = decorationDescription;
        while (current != null) {
            current = current.eContainer();
            if (current instanceof Layer) {
                return (Layer) current;
            }
        }
        return null;
    }

    /**
     * Check if a diagram element is in an activated layer or not and visible.
     * 
     * @param session
     *            the current session.
     * @param element
     *            the diagram element.
     * @return <code>true</code> if it is, <code>false</code> otherwise
     */
    public static boolean isInActivatedLayer(DiagramMappingsManager session, final DDiagramElement element) {
        return isInActivatedLayer(session, element, element.getParentDiagram());
    }

    /**
     * Check if a diagram element is in an activated layer or not and visible.
     * 
     * @param session
     *            the current session.
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
    public static boolean isInActivatedLayer(DiagramMappingsManager session, final DDiagramElement element, final DDiagram parentDiagram) {
        final DiagramElementMapping mapping = element.getDiagramElementMapping();

        if (!LayerHelper.withoutLayersMode(mapping)) {
            final DDiagram diagram;
            if (parentDiagram != null) {
                diagram = parentDiagram;
            } else {
                diagram = element.getParentDiagram();
            }

            boolean visible = false;
            if (diagram != null && session.getActiveParentLayers(mapping).size() > 0) {
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

                if (!LayerHelper.hideSubMappingsInImporters(session, diagram, settings, mapping)) {
                    final EObject container = element.eContainer();

                    /*
                     * Case 2 The mapping should be imported by another mapping owned by a visible element.
                     */
                    if (container instanceof DDiagramElement && LayerHelper.isInActivatedLayer(session, (DDiagramElement) container, parentDiagram)) {
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
            if (EqualityHelper.contains(LayerHelper.getAllLayerMappings(activatedLayer), mapping)) {
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

    private static Collection<DiagramElementMapping> getAllLayerMappings(final Layer layer) {
        final Collection<DiagramElementMapping> result = new HashSet<DiagramElementMapping>();
        result.addAll(layer.getNodeMappings());
        result.addAll(layer.getContainerMappings());
        result.addAll(layer.getEdgeMappings());
        // Add the wrapper of EdgeMappingImport
        final Iterator<EdgeMappingImport> iterMappingImport = layer.getEdgeMappingImports().iterator();
        while (iterMappingImport.hasNext()) {
            result.add(EdgeMappingImportWrapper.getWrapper(iterMappingImport.next()));
        }
        result.addAll(ContentLayerHelper.getReuseMappings(layer));
        return result;
    }

    /**
     * Check if a diagram element mapping is in an activated layer or not.
     * 
     * @param session
     *            the current session.
     * @param mapping
     *            the diagram element mapping.
     * @param diagram
     *            the diagram.
     * @return <code>true</code> if it is, <code>false</code> otherwise
     */
    public static boolean isInActivatedLayer(DiagramMappingsManager session, final DDiagram diagram, final DiagramElementMapping mapping) {
        if (!LayerHelper.withoutLayersMode(mapping)) {

            boolean visible = false;

            final Collection<Layer> layers = session.getActiveParentLayers(mapping);
            for (final Layer layer : layers) {
                if (EqualityHelper.contains(diagram.getActivatedLayers(), layer)) {

                    final EObject registryMappingInstance = ViewpointRegistry.getInstance().find(mapping);
                    final Collection<Setting> settings = ViewpointRegistry.getInstance().getCrossReferencer().getInverseReferences(registryMappingInstance);

                    if (!LayerHelper.hideSubMappingsInImporters(session, diagram, settings, mapping)) {
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
     * Check if a diagram element mapping is in an activated layer or not.
     * 
     * @param mapping
     *            the diagram element mapping.
     * @param diagram
     *            the diagram.
     * @return <code>true</code> if it is, <code>false</code> otherwise
     */
    public static boolean isInActivatedLayer(final DDiagram diagram, final IEdgeMapping mapping) {
        boolean found = false;
        if (!found && (diagram.getDescription().getEdgeMappings().contains(mapping) || diagram.getDescription().getEdgeMappingImports().contains(mapping)
                || LayerHelper.containsWrapped(diagram.getDescription().getEdgeMappingImports(), mapping))) {
            found = true;
        }
        for (Layer layer : diagram.getActivatedLayers()) {
            if (layer.getEdgeMappings().contains(mapping) || layer.getEdgeMappingImports().contains(mapping) || LayerHelper.containsWrapped(layer.getEdgeMappingImports(), mapping)) {
                found = true;
                break;
            }
        }
        return found;
    }

    /**
     * @param edgeMappingImports
     * @param mapping
     * @return
     */
    private static boolean containsWrapped(final EList<EdgeMappingImport> edgeMappingImports, final IEdgeMapping mapping) {
        for (EdgeMappingImport edgeMappingImport : edgeMappingImports) {
            if (mapping.equals(EdgeMappingImportWrapper.getWrapper(edgeMappingImport))) {
                return true;
            }
        }
        return false;
    }

    /*
     * mch : may be useful do not delete elements
     */
    /*
     * public static boolean areOnSameLayers(final DiagramElementMapping mapping1, final DiagramElementMapping mapping2)
     * { if (!LayerHelper.withoutLayersMode(mapping1)) { final Layer layer1 = LayerHelper.getParentLayer(mapping1);
     * final Layer layer2 = LayerHelper.getParentLayer(mapping1); } return true; }
     */

    /**
     * Check if are in the mode without layers.
     * 
     * @param mapping
     *            any {@link DiagramElementMapping}
     * @return <code>true</code> if we are in the without layer mode, <code>false</code> otherwise
     */
    public static boolean withoutLayersMode(final DiagramElementMapping mapping) {
        return LayerHelper.getContainingLayer(mapping) == null;
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
     * Search in this diagram (or in activated layer of this diagram) if there is one EdgeMappingImport which import
     * edgeMapping.
     * 
     * @param edgeMapping
     *            the edgeMapping to possibly refine (with importMapping)
     * @param diagram
     *            the diagram in which search the list of EdgeMappingImport to check
     * @return the best EdgeMapping
     */
    public static EdgeMapping getBestMapping(final EdgeMapping edgeMapping, final DDiagram diagram) {
        final EList<EdgeMappingImportWrapper> edgeMappingImportWrappers = new BasicEList<EdgeMappingImportWrapper>();
        // Add wrapper form EdgeMappingImport of DiagramDescription
        for (EdgeMappingImport edgeMappingImport : diagram.getDescription().getEdgeMappingImports()) {
            edgeMappingImportWrappers.add(EdgeMappingImportWrapper.getWrapper(edgeMappingImport));
        }
        // Add wrapper from EdgeMappingImport of activated layers

        for (final Layer layer : diagram.getActivatedLayers()) {
            for (final EdgeMapping otherEdgeMapping : ContentLayerHelper.getAllEdgeMappings(layer)) {
                if (otherEdgeMapping instanceof EdgeMappingImportWrapper) {
                    edgeMappingImportWrappers.add((EdgeMappingImportWrapper) otherEdgeMapping);
                }
            }
        }

        // If the edgeMapping is a EdgeMappingImportWrapper, verify if it's
        // available.
        EdgeMapping result;
        if (edgeMapping instanceof EdgeMappingImportWrapper && !edgeMappingImportWrappers.contains(edgeMapping)) {
            result = null;
        } else {
            result = LayerHelper.getBestMapping(edgeMapping, edgeMappingImportWrappers);
        }
        return result;
    }

    /**
     * Search in the list of EdgeMappingImportWrapper if there is one which import edgeMapping.
     * 
     * @param edgeMapping
     *            the edgeMapping to possibly refine (with importMapping)
     * @param edgeMappingImportWrappers
     *            List of EdgeMappingImport to check
     * @return the best EdgeMapping
     */
    public static EdgeMapping getBestMapping(final EdgeMapping edgeMapping, final EList<EdgeMappingImportWrapper> edgeMappingImportWrappers) {
        EdgeMapping result = edgeMapping;
        for (EdgeMappingImportWrapper edgeMappingImportWrapper : edgeMappingImportWrappers) {
            if (LayerHelper.isImported(edgeMapping, edgeMappingImportWrapper.getWrappedEdgeMappingImport())) {
                result = LayerHelper.getBestMapping(edgeMappingImportWrapper, edgeMappingImportWrappers);
                break;
            }
        }
        return result;
    }

    /**
     * Check if an edgeMappingImport imports another or not.
     * 
     * @param searchImportedMapping
     *            The potential imported edge mapping.
     * @param edgeMappingImport
     *            The importer
     * @return true is the EdgeMappingImport imports the searchImportedMapping.
     */
    public static boolean isImported(final IEdgeMapping searchImportedMapping, final EdgeMappingImport edgeMappingImport) {
        boolean result = false;
        final IEdgeMapping importedMapping = edgeMappingImport.getImportedMapping();

        IEdgeMapping mappingToCompare = searchImportedMapping;
        if (searchImportedMapping instanceof EdgeMappingImportWrapper) {
            mappingToCompare = ((EdgeMappingImportWrapper) searchImportedMapping).getWrappedEdgeMappingImport();
        }

        if (importedMapping == null) {
            result = false;
        } else if (importedMapping.equals(mappingToCompare)) {
            result = true;
        } else if (importedMapping instanceof EdgeMappingImport) {
            result = LayerHelper.isImported(searchImportedMapping, (EdgeMappingImport) importedMapping);
        } else {
            result = importedMapping.equals(searchImportedMapping);
        }
        return result;
    }

    /**
     * Check if this layer contains only tools or not.
     * 
     * @param layer
     *            the layer to check
     * @return <code>true</code> if it contains only tools, <code>false</code> otherwise
     */
    public static boolean containsOnlyTools(final Layer layer) {
        final boolean containsMappings = containsMappings(layer);
        final boolean isNoDecorationDescritionSet = layer.getDecorationDescriptionsSet() == null || layer.getDecorationDescriptionsSet().getDecorationDescriptions().isEmpty();
        return !containsMappings && isNoDecorationDescritionSet;
    }

    private static boolean containsMappings(final Layer layer) {
        final boolean isNoMapping = layer.getContainerMappings().isEmpty() && layer.getEdgeMappings().isEmpty() && layer.getNodeMappings().isEmpty();
        final boolean isNoImportOrReusedMapping = layer.getEdgeMappingImports().isEmpty() && layer.getReusedMappings().isEmpty();
        return !(isNoMapping && isNoImportOrReusedMapping);
    }

    /**
     * Check if this layer is considered as Transient. A transient layer is an additional layer that contains at most
     * tools or decorationDescription.
     * 
     * @param layer
     *            the layer to check
     * @return <code>true</code> if it is transient, <code>false</code> otherwise
     */
    public static boolean isTransientLayer(final Layer layer) {
        if (layer instanceof AdditionalLayer) {
            boolean containsMappings = containsMappings(layer);
            boolean containsCusto = layer.getCustomization() != null && !layer.getCustomization().getVsmElementCustomizations().isEmpty();
            return !containsMappings && !containsCusto;
        }
        return false;
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
            if (LayerHelper.isTransientLayer(layer)) {
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
