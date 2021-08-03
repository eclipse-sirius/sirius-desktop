/*******************************************************************************
 * Copyright (c) 2009, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.componentization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.diagram.business.api.query.DiagramDescriptionQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.DiagramComponentizationHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.model.ContentHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.model.ContentLayerHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.model.LayerModelHelper;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.tool.ToolGroup;
import org.eclipse.sirius.diagram.description.tool.ToolGroupExtension;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;

/**
 * This class handles componentization for diagram elements. This class will move in diagram plug-in when refresh
 * refactoring will occurs.
 * 
 * @author mchauvin
 */
public class DiagramComponentizationManager {

    private static boolean isInSelectedViewpoints(final Collection<Viewpoint> selectedViewpoints, final DiagramDescription diagramDescription) {
        for (final Viewpoint viewpoint : selectedViewpoints) {
            for (final RepresentationDescription representationDescription : new ViewpointQuery(viewpoint).getAllRepresentationDescriptions()) {
                if (EqualityHelper.areEquals(diagramDescription, representationDescription)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get all the layers of a diagram description.
     * 
     * @param enabledViewpoints
     *            the list of viewpoints to consider
     * @param diagramDescription
     *            the diagram description
     * @return all the available layers
     */
    public EList<Layer> getAllLayers(final Collection<Viewpoint> enabledViewpoints, final DiagramDescription diagramDescription) {
        final Collection<Layer> layers = new ArrayList<Layer>(LayerModelHelper.getAllLayers(diagramDescription));

        if (enabledViewpoints != null) {
            if (!DiagramComponentizationManager.isInSelectedViewpoints(enabledViewpoints, diagramDescription)) {
                return new BasicEList<>();
            }
            layers.addAll(DiagramComponentizationHelper.getContributedLayers(diagramDescription, enabledViewpoints));
        }
        return new BasicEList<>(layers);
    }

    /**
     * Get all the edge mappings available for a diagram description.
     * 
     * @param enabledViewpoints
     *            the list of viewpoint to consider
     * @param diagramDescription
     *            the diagram description
     * @return all the available edge mappings
     */
    public EList<EdgeMapping> getAllEdgeMappings(final Collection<Viewpoint> enabledViewpoints, final DiagramDescription diagramDescription) {
        final Collection<EdgeMapping> edgeMappings = new ArrayList<EdgeMapping>(ContentHelper.getAllEdgeMappings(diagramDescription, false));
        if (enabledViewpoints != null) {
            for (final Layer layer : DiagramComponentizationHelper.getContributedLayers(diagramDescription, enabledViewpoints)) {
                edgeMappings.addAll(ContentLayerHelper.getAllEdgeMappings(layer));
            }
        }
        return new BasicEList.UnmodifiableEList<EdgeMapping>(edgeMappings.size(), edgeMappings.toArray());
    }

    /**
     * Get all the node mappings available for a diagram description.
     * 
     * @param enabledViewpoints
     *            the list of viewpoints to consider
     * @param diagramDescription
     *            the diagram description
     * @return all the available node mappings
     */
    public EList<NodeMapping> getAllNodeMappings(final Collection<Viewpoint> enabledViewpoints, final DiagramDescription diagramDescription) {
        final Collection<NodeMapping> nodeMappings = new ArrayList<NodeMapping>(ContentHelper.getAllNodeMappings(diagramDescription, false));
        if (enabledViewpoints != null) {
            for (final Layer layer : DiagramComponentizationHelper.getContributedLayers(diagramDescription, enabledViewpoints)) {
                nodeMappings.addAll(layer.getNodeMappings());
            }
        }
        return new BasicEList<>(nodeMappings);
    }

    /**
     * Get all the container mappings available for a diagram description.
     * 
     * @param enabledViewpoints
     *            the list of viewpoints to consider
     * @param diagramDescription
     *            the diagram description
     * @return all the available container mappings
     */
    public EList<ContainerMapping> getAllContainerMappings(final Collection<Viewpoint> enabledViewpoints, final DiagramDescription diagramDescription) {
        final Collection<ContainerMapping> containerMappings = new ArrayList<ContainerMapping>(ContentHelper.getAllContainerMappings(diagramDescription, false));
        if (enabledViewpoints != null) {
            for (final Layer layer : DiagramComponentizationHelper.getContributedLayers(diagramDescription, enabledViewpoints)) {
                containerMappings.addAll(layer.getContainerMappings());
            }
        }
        return new BasicEList<>(containerMappings);
    }

    /**
     * Get all the sections available for a diagram description.
     * 
     * @param enabledViewpoints
     *            the list of viewpoints to consider
     * @param diagramDescription
     *            the diagram description
     * @return all the available sections
     */
    public EList<ToolSection> getRootPaletteSections(final Collection<Viewpoint> enabledViewpoints, final DiagramDescription diagramDescription) {
        final Map<String, ToolSection> nameToSection = new LinkedHashMap<String, ToolSection>();
        for (final Layer layer : getAllLayers(enabledViewpoints, diagramDescription)) {
            for (ToolSection currentSection : layer.getToolSections()) {
                if (!nameToSection.containsKey(currentSection.getName())) {
                    nameToSection.put(currentSection.getName(), currentSection);
                }
            }
        }
        return new BasicEList<ToolSection>(nameToSection.values());
    }

    /**
     * Get all the tools available for a diagram description. The function will check direct and indirect children.
     * 
     * @param enabledViewpoints
     *            the viewpoints to consider.
     * @param diagramDescription
     *            the diagram description
     * @return all the available tools
     */
    public EList<AbstractToolDescription> getAllTools(final Collection<Viewpoint> enabledViewpoints, final DiagramDescription diagramDescription) {
        final Collection<AbstractToolDescription> tools = new ArrayList<AbstractToolDescription>(new DiagramDescriptionQuery(diagramDescription).getAllTools());
        if (enabledViewpoints != null) {
            for (final Layer layer : DiagramComponentizationHelper.getContributedLayers(diagramDescription, enabledViewpoints)) {
                tools.addAll(layer.getAllTools());
            }
        }
        return new BasicEList.UnmodifiableEList<>(tools.size(), tools.toArray());
    }

    /**
     * Get the tool entries available for a section.
     * 
     * @param enabledViewpoints
     *            the viewpoints to consider.
     * @param section
     *            the section
     * @return all the available tools
     */
    public EList<ToolEntry> getToolEntries(final Collection<Viewpoint> enabledViewpoints, final ToolSection section) {
        return getAllToolEntries(enabledViewpoints, section, false);
    }

    /**
     * Get all the tool entries available for a section. The function will check direct and indirect children.
     * 
     * @param enabledViewpoints
     *            the viewpoints to consider.
     * @param section
     *            the section
     * @return all the available tools
     */
    public EList<ToolEntry> getAllToolEntries(final Collection<Viewpoint> enabledViewpoints, final ToolSection section) {
        return getAllToolEntries(enabledViewpoints, section, true);
    }

    private EList<ToolEntry> getAllToolEntries(final Collection<Viewpoint> enabledViewpoints, final ToolSection section, boolean recursive) {
        final EList<ToolEntry> toolEntries = new BasicEList<ToolEntry>();

        final EObject container = section.eContainer();

        if (container instanceof Layer) {
            if (section.getName() != null && enabledViewpoints != null) {
                final Layer parentLayer = (Layer) container;
                final DiagramDescription diagramDescription = getDiagramDescription(enabledViewpoints, parentLayer);

                for (final Layer layer : getAllLayers(enabledViewpoints, diagramDescription)) {

                    for (final ToolSection toolSection : layer.getToolSections()) {

                        if (section.getName().equals(toolSection.getName())) {
                            toolEntries.addAll(getTools(toolSection, recursive));
                        }
                    }
                }
            }
        } else {
            toolEntries.addAll(getTools(section, recursive));
        }
        return toolEntries;
    }

    private EList<ToolEntry> getTools(final ToolSection toolSection, boolean recursive) {
        final EList<ToolEntry> entries = new BasicEList<ToolEntry>();
        entries.addAll(toolSection.getOwnedTools());
        entries.addAll(toolSection.getReusedTools());
        if (recursive) {
            for (ToolSection subSection : toolSection.getSubSections()) {
                entries.addAll(getTools(subSection, recursive));
            }
        }
        return entries;
    }

    private DiagramDescription getDiagramDescription(final Collection<Viewpoint> enabledViewpoints, final Layer layer) {
        DiagramDescription diagramDescription = null;
        final EObject layerContainer = layer.eContainer();
        if (layerContainer instanceof DiagramDescription) {
            diagramDescription = (DiagramDescription) layerContainer;
        } else if (layerContainer instanceof DiagramExtensionDescription) {
            diagramDescription = DiagramComponentizationHelper.getDiagramDescription((DiagramExtensionDescription) layerContainer, enabledViewpoints);
        }
        return diagramDescription;
    }

    /**
     * Get the tools available for a tool group.
     * 
     * @param enabledViewpoints
     *            the viewpoints to consider.
     * @param toolGroup
     *            the group of tools
     * @return the available tools
     */
    public EList<AbstractToolDescription> getTools(final Collection<Viewpoint> enabledViewpoints, final ToolGroup toolGroup) {

        final DiagramDescription diagramDescription = getDiagramDescription(toolGroup);
        final EList<AbstractToolDescription> tools = new BasicEList<AbstractToolDescription>();
        tools.addAll(toolGroup.getTools());
        if (diagramDescription != null) {
            for (final Layer layer : getAllLayers(enabledViewpoints, diagramDescription)) {
                tools.addAll(getToolsFromToolSection(layer.getToolSections(), toolGroup));
            }
        }
        return tools;
    }

    private EList<AbstractToolDescription> getToolsFromToolSection(final Collection<ToolSection> sections, final ToolGroup toolGroup) {
        final EList<AbstractToolDescription> tools = new BasicEList<AbstractToolDescription>();
        for (final ToolSection section : sections) {
            for (final ToolGroupExtension extension : section.getGroupExtensions()) {
                if (EqualityHelper.areEquals(toolGroup, extension.getGroup())) {
                    tools.addAll(extension.getTools());
                }
            }
            tools.addAll(getToolsFromToolSection(section.getSubSections(), toolGroup));
        }
        return tools;
    }

    private DiagramDescription getDiagramDescription(final ToolGroup toolGroup) {
        EObject eObject = toolGroup.eContainer();
        while (!(eObject instanceof DiagramDescription || eObject instanceof DiagramExtensionDescription)) {
            eObject = eObject.eContainer();
            if (eObject == null) {
                return null;
            }
        }
        DiagramDescription diagramDescription;

        if (eObject instanceof DiagramExtensionDescription) {
            diagramDescription = DiagramComponentizationHelper.getDiagramDescription((DiagramExtensionDescription) eObject, ViewpointRegistry.getInstance().getViewpoints());
        } else {
            diagramDescription = (DiagramDescription) eObject;
        }
        return diagramDescription;
    }

}
