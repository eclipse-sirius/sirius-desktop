/*******************************************************************************
 * Copyright (c) 2008, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.metamodel.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.sirius.diagram.business.api.helper.layers.LayerService;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.EdgeMappingImportWrapper;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.NodeMappingImport;

/**
 * Helpers to navigate the Sirius Diagrams models.
 * 
 * @author cbrun
 */
public final class ContentHelper {

    /**
     * Avoid Instantiation.
     */
    private ContentHelper() {

    }

    /**
     * Get all edge mappings used in a designer diagram description on all
     * layers.
     * 
     * @param diagramDescription
     *            the description of a designer diagram
     * @param withoutOptionalLayers
     *            takes only the default layers if <code>true</code>
     * @return all the edge mappings used
     */
    public static EList<EdgeMapping> getAllEdgeMappings(final DiagramDescription diagramDescription, final boolean withoutOptionalLayers) {
        final Collection<EdgeMapping> result = new ArrayList<EdgeMapping>();

        if (LayerService.withoutLayersMode(diagramDescription)) {
            result.addAll(diagramDescription.getEdgeMappings());
            // Add the wrapper of EdgeMappingImport
            final Iterator<EdgeMappingImport> iterMappingImport = diagramDescription.getEdgeMappingImports().iterator();
            while (iterMappingImport.hasNext()) {
                result.add(EdgeMappingImportWrapper.getWrapper(iterMappingImport.next()));
            }
            // Add the EdgeMapping of the reused mapping
            final Iterator<DiagramElementMapping> it = diagramDescription.getReusedMappings().iterator();
            while (it.hasNext()) {
                final DiagramElementMapping eObj = it.next();
                if (eObj instanceof EdgeMapping) {
                    result.add((EdgeMapping) eObj);
                }
            }
        } else {
            List<Layer> layers;
            if (withoutOptionalLayers) {
                layers = new ArrayList<Layer>(1);
            } else {
                layers = new ArrayList<Layer>(diagramDescription.getAdditionalLayers());
            }

            layers.add(diagramDescription.getDefaultLayer());
            final Iterator<Layer> itLayer = layers.iterator();

            while (itLayer.hasNext()) {
                final Layer layer = itLayer.next();
                result.addAll(ContentHelper.getAllEdgeMappings(layer));
            }
        }
        return new EcoreEList.UnmodifiableEList<EdgeMapping>((InternalEObject) diagramDescription, DescriptionPackage.eINSTANCE.getDiagramDescription_AllEdgeMappings(), result.size(),
                result.toArray());
    }

    /**
     * Get all edge mappings (including wrap EdgeMappingImport) used in a
     * designer diagram description on one particular layer.
     * 
     * @param layer
     *            the layer of a diagram description of a designer diagram
     * @return all the edge mappings used
     */
    public static EList<EdgeMapping> getAllEdgeMappings(final Layer layer) {
        final Collection<EdgeMapping> result = new ArrayList<EdgeMapping>();

        result.addAll(layer.getEdgeMappings());
        // Add the wrapper of EdgeMappingImport
        final Iterator<EdgeMappingImport> iterMappingImport = layer.getEdgeMappingImports().iterator();
        while (iterMappingImport.hasNext()) {
            result.add(EdgeMappingImportWrapper.getWrapper(iterMappingImport.next()));
        }
        // Add the EdgeMapping of the reused mapping
        final Iterator<DiagramElementMapping> it = ContentHelper.getReuseMappings(layer).iterator();
        while (it.hasNext()) {
            final DiagramElementMapping eObj = it.next();
            if (eObj instanceof EdgeMapping) {
                result.add((EdgeMapping) eObj);
            }
        }
        return new EcoreEList.UnmodifiableEList<EdgeMapping>((InternalEObject) layer, DescriptionPackage.eINSTANCE.getLayer_AllEdgeMappings(), result.size(), result.toArray());
    }

    /**
     * DO NOT USE except in property sections or DiagramComponentizationManager.
     * 
     * Get all edge mappings used in a designer diagram extension description on
     * all layers.
     * 
     * @param diagramExtensionDescription
     *            the description of a designer diagram
     * @return all the used edge mappings
     */
    public static Collection<EdgeMapping> getAllEdgeMappings(final DiagramExtensionDescription diagramExtensionDescription) {

        final Collection<EdgeMapping> result = new ArrayList<EdgeMapping>();

        final Iterator<? extends Layer> itLayer = diagramExtensionDescription.getLayers().iterator();

        while (itLayer.hasNext()) {
            final Layer layer = itLayer.next();

            result.addAll(ContentHelper.getAllEdgeMappings(layer));
        }
        return result;
    }

    /**
     * Get all node mappings used in a designer diagram description on all
     * layers.
     * 
     * @param diagramDescription
     *            the description of a designer diagram
     * @param withoutOptionalLayers
     *            takes only the default layers if <code>true</code>
     * @return all the node mappings used
     */
    public static EList<NodeMapping> getAllNodeMappings(final DiagramDescription diagramDescription, final boolean withoutOptionalLayers) {
        final Collection<NodeMapping> result = new ArrayList<NodeMapping>();

        if (LayerService.withoutLayersMode(diagramDescription)) {
            result.addAll(diagramDescription.getNodeMappings());
            final Iterator<DiagramElementMapping> it = diagramDescription.getReusedMappings().iterator();
            while (it.hasNext()) {
                final DiagramElementMapping mapping = it.next();
                if (mapping instanceof NodeMapping) {
                    result.add((NodeMapping) mapping);
                }
            }
        } else {
            List<Layer> layers;
            if (withoutOptionalLayers) {
                layers = new ArrayList<Layer>(1);
            } else {
                layers = new ArrayList<Layer>(diagramDescription.getAdditionalLayers());
            }

            layers.add(diagramDescription.getDefaultLayer());
            final Iterator<Layer> itLayer = layers.iterator();

            while (itLayer.hasNext()) {
                final Layer layer = itLayer.next();

                result.addAll(layer.getNodeMappings());
                final Iterator<DiagramElementMapping> it = ContentHelper.getReuseMappings(layer).iterator();
                while (it.hasNext()) {
                    final DiagramElementMapping mapping = it.next();
                    if (mapping instanceof NodeMapping) {
                        result.add((NodeMapping) mapping);
                    }
                }
            }
        }
        return new EcoreEList.UnmodifiableEList<NodeMapping>((InternalEObject) diagramDescription, DescriptionPackage.eINSTANCE.getDiagramDescription_AllNodeMappings(), result.size(),
                result.toArray());
    }

    /**
     * DO NOT USE except in property sections or DiagramComponentizationManager.
     * 
     * Get all node mappings used in a designer diagram extension description on
     * all layers.
     * 
     * @param diagramExtensionDescription
     *            the description of a designer diagram
     * @return all the used node mappings
     */
    public static Collection<NodeMapping> getAllNodeMappings(final DiagramExtensionDescription diagramExtensionDescription) {

        final Collection<NodeMapping> result = new ArrayList<NodeMapping>();

        final Iterator<? extends Layer> itLayer = diagramExtensionDescription.getLayers().iterator();

        while (itLayer.hasNext()) {
            final Layer layer = itLayer.next();

            result.addAll(layer.getNodeMappings());
            final Iterator<DiagramElementMapping> it = ContentHelper.getReuseMappings(layer).iterator();
            while (it.hasNext()) {
                final EObject eObj = it.next();
                if (eObj instanceof NodeMapping) {
                    result.add((NodeMapping) eObj);
                }
            }
        }
        return result;
    }

    /**
     * Get all containers mappings used in a designer diagram description on all
     * layers.
     * 
     * @param diagramDescription
     *            the description of a designer diagram
     * @param withoutOptionalLayers
     *            takes only the default layers if <code>true</code>
     * @return all the container mappings used
     */
    public static EList<ContainerMapping> getAllContainerMappings(final DiagramDescription diagramDescription, final boolean withoutOptionalLayers) {
        final Collection<ContainerMapping> result = new ArrayList<ContainerMapping>();

        if (LayerService.withoutLayersMode(diagramDescription)) {
            result.addAll(diagramDescription.getContainerMappings());
            final Iterator<DiagramElementMapping> it = diagramDescription.getReusedMappings().iterator();
            while (it.hasNext()) {
                final EObject eObj = it.next();
                if (eObj instanceof ContainerMapping) {
                    result.add((ContainerMapping) eObj);
                }
            }
        } else {
            List<Layer> layers;
            if (withoutOptionalLayers) {
                layers = new ArrayList<Layer>(1);
            } else {
                layers = new ArrayList<Layer>(diagramDescription.getAdditionalLayers());
            }

            layers.add(diagramDescription.getDefaultLayer());
            final Iterator<Layer> itLayer = layers.iterator();

            while (itLayer.hasNext()) {
                final Layer layer = itLayer.next();

                result.addAll(layer.getContainerMappings());
                final Iterator<DiagramElementMapping> it = ContentHelper.getReuseMappings(layer).iterator();
                while (it.hasNext()) {
                    final EObject eObj = it.next();
                    if (eObj instanceof ContainerMapping) {
                        result.add((ContainerMapping) eObj);
                    }
                }
            }
        }
        return new EcoreEList.UnmodifiableEList<ContainerMapping>((InternalEObject) diagramDescription, DescriptionPackage.eINSTANCE.getDiagramDescription_AllContainerMappings(), result.size(),
                result.toArray());
    }

    /**
     * Return the list of reuse mappings for the layer removing any import of
     * the layers mappings itself (which might be considered as a "useless
     * import").
     * 
     * @param layer
     *            the layer.
     * @return the collection of reused mappings.
     */
    public static Collection<DiagramElementMapping> getReuseMappings(final Layer layer) {
        final Set<DiagramElementMapping> mappings = new LinkedHashSet<DiagramElementMapping>(layer.getReusedMappings().size());
        mappings.addAll(layer.getReusedMappings());
        mappings.removeAll(layer.getContainerMappings());
        mappings.removeAll(layer.getNodeMappings());
        mappings.removeAll(layer.getEdgeMappings());
        // Remove the wrapper of EdgeMappingImport
        final Iterator<EdgeMappingImport> iterMappingImport = layer.getEdgeMappingImports().iterator();
        while (iterMappingImport.hasNext()) {
            mappings.remove(EdgeMappingImportWrapper.getWrapper(iterMappingImport.next()));
        }
        return mappings;
    }

    /**
     * DO NOT USE except in property sections or DiagramComponentizationManager.
     * 
     * Get all containers mappings used in a designer diagram extension
     * description on all layers.
     * 
     * @param diagramExtensionDescription
     *            the description of a designer diagram
     * @return all the used container mappings
     */
    public static Collection<ContainerMapping> getAllContainerMappings(final DiagramExtensionDescription diagramExtensionDescription) {

        final Collection<ContainerMapping> result = new ArrayList<ContainerMapping>();

        final Iterator<? extends Layer> itLayer = diagramExtensionDescription.getLayers().iterator();

        while (itLayer.hasNext()) {
            final Layer layer = itLayer.next();

            result.addAll(layer.getContainerMappings());
            final Iterator<DiagramElementMapping> it = ContentHelper.getReuseMappings(layer).iterator();
            while (it.hasNext()) {
                final EObject eObj = it.next();
                if (eObj instanceof ContainerMapping) {
                    result.add((ContainerMapping) eObj);
                }
            }
        }
        return result;
    }

    /**
     * Implementation of
     * {@link AbstractNodeMapping#getAllBorderedNodeMappings()}.
     * 
     * @param nodeMapping
     *            the mapping.
     * @return all bordered node mappings.
     */
    public static EList<NodeMapping> getAllAvalaibleBorderedNodeMappings(final AbstractNodeMapping nodeMapping) {
        List<NodeMapping> result = new ArrayList<NodeMapping>();
        result.addAll(nodeMapping.getBorderedNodeMappings());
        result.addAll(nodeMapping.getReusedBorderedNodeMappings());
        if (!LayerHelper.withoutLayersMode(nodeMapping)) {
            result = ContentHelper.developMappingsInheritance(result);
        }
        return new BasicEList<NodeMapping>(result);
    }

    private static List<NodeMapping> developMappingsInheritance(final List<NodeMapping> mappings) {
        final List<NodeMapping> result = new ArrayList<NodeMapping>(mappings);
        for (final NodeMapping mapping : mappings) {
            if (mapping instanceof NodeMappingImport) {
                result.addAll(ContentHelper.developMappingInheritance((NodeMappingImport) mapping));
            }
        }
        return result;
    }

    private static List<NodeMapping> developMappingInheritance(final NodeMappingImport mappingImport) {
        final List<NodeMapping> result = new ArrayList<NodeMapping>();
        NodeMapping importedMapping = mappingImport.getImportedMapping();
        while (importedMapping != null) {
            result.add(importedMapping);
            if (importedMapping instanceof NodeMappingImport) {
                importedMapping = ((NodeMappingImport) importedMapping).getImportedMapping();
            } else {
                importedMapping = null;
            }
        }
        return result;
    }

    /**
     * Returns the parent diagram description of the given mapping.
     * 
     * @param mapping
     *            the mapping.
     * @return the parent diagram description of the given mapping.
     */
    public static DiagramDescription getParentDescription(final DiagramElementMapping mapping) {
        DiagramDescription result = null;
        EObject current = mapping.eContainer();
        while (current != null && result == null) {
            if (current instanceof DiagramDescription) {
                result = (DiagramDescription) current;
            } else {
                current = current.eContainer();
            }
        }
        return result;
    }
}
