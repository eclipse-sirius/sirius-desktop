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
package org.eclipse.sirius.diagram.business.internal.metamodel.helper.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.EdgeMappingImportWrapper;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.viewpoint.description.DecorationDescription;

/**
 * An helper to deal with layers stuffs.
 * 
 * @author mchauvin
 */
public final class LayerModelHelper {

    /**
     * Avoid instantiation.
     */
    private LayerModelHelper() {
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
                || LayerModelHelper.containsWrapped(diagram.getDescription().getEdgeMappingImports(), mapping))) {
            found = true;
        }
        for (Layer layer : diagram.getActivatedLayers()) {
            if (layer.getEdgeMappings().contains(mapping) || layer.getEdgeMappingImports().contains(mapping) || LayerModelHelper.containsWrapped(layer.getEdgeMappingImports(), mapping)) {
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
        return LayerModelHelper.getContainingLayer(mapping) == null;
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
            result = LayerModelHelper.getBestMapping(edgeMapping, edgeMappingImportWrappers);
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
            if (LayerModelHelper.isImported(edgeMapping, edgeMappingImportWrapper.getWrappedEdgeMappingImport())) {
                result = LayerModelHelper.getBestMapping(edgeMappingImportWrapper, edgeMappingImportWrappers);
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
            result = LayerModelHelper.isImported(searchImportedMapping, (EdgeMappingImport) importedMapping);
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
     * TODO Return mappings of the current layer.
     * 
     * @param layer
     *            the current layer.
     * @return mappigns of the current layer.
     */
    public static Collection<DiagramElementMapping> getAllLayerMappings(final Layer layer) {
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
     * Check if are in the mode without layers.
     * 
     * @param diagramDescription
     *            the diagram description to check
     * @return <code>true</code> if we are in the without layer mode, <code>false</code> otherwise
     */
    public static boolean withoutLayersMode(final DiagramDescription diagramDescription) {
        return diagramDescription.getDefaultLayer() == null;
    }

}
