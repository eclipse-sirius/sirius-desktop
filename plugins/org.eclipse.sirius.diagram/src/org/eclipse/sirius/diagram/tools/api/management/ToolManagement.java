/*******************************************************************************
 * Copyright (c) 2019 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.api.management;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.helper.ViewpointUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.diagram.business.api.helper.SiriusDiagramUtil;
import org.eclipse.sirius.diagram.business.api.helper.layers.LayerService;
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.tool.ToolGroup;
import org.eclipse.sirius.diagram.description.tool.ToolGroupExtension;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.diagram.tools.internal.management.ToolFilterDescriptionListenersManager;
import org.eclipse.sirius.diagram.tools.internal.management.ToolManagementRegistry;
import org.eclipse.sirius.viewpoint.ToolGroupInstance;
import org.eclipse.sirius.viewpoint.ToolInstance;
import org.eclipse.sirius.viewpoint.ToolSectionInstance;
import org.eclipse.sirius.viewpoint.UIState;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.description.Environment;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;
import org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * This component maintains the {@link ToolInstance} available in the {@link UIState} of a given {@link DDiagram}
 * regarding layers activated, tool filters status.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class ToolManagement {

    /**
     * Tool filters installed for the specific diagram managed by this instance.
     */
    private Set<ToolFilter> filters = new LinkedHashSet<ToolFilter>();

    /**
     * The currently activated layers.
     */
    private List<Layer> activatedLayersOfSelectedViewpoints;

    /**
     * The currently deactivated layers.
     */
    private List<Layer> deactivatedLayersAndAllLayersOfDeselectedViewpoints;

    /**
     * The diagram from which this component handles available tools.
     */
    private DDiagram dDiagram;

    /**
     * The GMF diagram from which this component handles available tools.
     */

    /**
     * Listener manager to update toolS wHen their filter condition changes.
     */
    private ToolFilterDescriptionListenersManager listenersManager;

    /**
     * A map of {@link DDiagram} to their registered {@link ToolChangeListener}.
     */
    private Set<ToolChangeListener> toolChangeListeners;

    /**
     * Initialize this tool management from given diagram.
     * 
     * @param dDiagram
     *            the diagram from which tool will be handled by this component.
     */
    public ToolManagement(DDiagram dDiagram) {
        this.dDiagram = dDiagram;
        listenersManager = new ToolFilterDescriptionListenersManager();
        listenersManager.init(dDiagram);
        toolChangeListeners = new HashSet<>();
    }

    /**
     * Add a listener to be aware of any tool change}.
     * 
     * @param toolChangeListener
     *            the {@link ToolChangeListener} to add.
     */
    public void addToolChangeListener(ToolChangeListener toolChangeListener) {
        toolChangeListeners.add(toolChangeListener);
    }

    /**
     * Returns the listeners interested by tool changes.
     * 
     * @return the listeners interested by tool changes.
     */
    public Set<ToolChangeListener> getToolListeners() {
        return toolChangeListeners;
    }

    /**
     * Remove the given {@link ToolChangeListener}.
     * 
     * @param toolChangeListener
     *            the {@link ToolChangeListener} to remove.
     * @return true if the given {@link ToolChangeListener} has been removed. False otherwise.
     */
    public boolean removeToolChangeListener(ToolChangeListener toolChangeListener) {
        return toolChangeListeners.remove(toolChangeListener);
    }

    /**
     * Returns the currently activated layers of selected viewpoints.
     * 
     * @return the currently activated layers of selected viewpoints.
     */
    public List<Layer> getActivatedLayersOfSelectedViewpoints() {
        return activatedLayersOfSelectedViewpoints;
    }

    /**
     * Notifies diagram listeners that related tools have been updated because a reload of the VSM has been done.
     * 
     */
    public void notifyToolChangeAfterVSMReload() {
        Set<ToolChangeListener> toolListeners = getToolListeners();
        for (ToolChangeListener toolChangeListener : toolListeners) {
            toolChangeListener.notifyToolChange(ToolChangeListener.ChangeKind.VSM_UPDATE);
        }
    }

    /**
     * Notifies diagram listeners that related tools have been updated.
     * 
     */
    public void notifyToolChange() {
        Set<ToolChangeListener> toolListeners = getToolListeners();
        for (ToolChangeListener toolChangeListener : toolListeners) {
            toolChangeListener.notifyToolChange(ToolChangeListener.ChangeKind.OTHER_UPDATE);
        }
    }

    /**
     * Returns the currently deactivated layers and all layers of deselected viewpoints.
     * 
     * @return the currently deactivated layers and all layers of deselected viewpoints.
     */
    public List<Layer> getDeactivatedLayersAndAllLayersOfDeselectedViewpoints() {
        return deactivatedLayersAndAllLayersOfDeselectedViewpoints;
    }

    /**
     * This method update the tools available for the current diagram state and returns those. Tools are filtered by
     * filters that are installed specifically for the diagram managed by this instance, i.e. those added via
     * {@link #addToolFilter(ToolFilter)}, and by filters that are installed globally via the toolManagement extension
     * point.
     * 
     * @param updateFilters
     *            true if filters should be updated.
     * 
     * @return the updated tools associated to the attached diagram.
     */
    public List<ToolSectionInstance> updateTools(boolean updateFilters) {
        Session session = null;
        if (updateFilters) {
            clearFilters();
        }
        if (dDiagram instanceof DSemanticDiagram) {
            session = SessionManager.INSTANCE.getSession(((DSemanticDiagram) dDiagram).getTarget());
        }

        return computeAvailableTools(session, updateFilters);

    }

    /**
     * Clear diagrams filters.
     * 
     */
    public void clearFilters() {
        listenersManager.init(dDiagram);
        Collection<ToolFilter> filtersCopy = Lists.newArrayList(filters);
        for (final ToolFilter filter : filtersCopy) {
            if (filter instanceof ToolFilterFromDescription) {
                removeToolFilter(filter);
            }
        }
    }

    /**
     * Remove a filter to hide a tool.
     * 
     * @param toolFilter
     *            the tool filter to remove.
     */
    public void removeToolFilter(ToolFilter toolFilter) {
        filters.remove(toolFilter);
    }

    /**
     * Compute all tools available for the attached {@link DDiagram}.
     * 
     * @param session
     *            used to get viewpoint information and to execute update command.
     * @param updateFilters
     * 
     * @return all tools available for the attached {@link DDiagram}.
     */
    private List<ToolSectionInstance> computeAvailableTools(Session session, boolean updateFilters) {
        dDiagram.getUiState().getToolSections().clear();

        ToolSectionInstance defaultToolSection = ViewpointFactory.eINSTANCE.createToolSectionInstance();
        dDiagram.getUiState().getToolSections().add(defaultToolSection);
        defaultToolSection.setId(ToolConstants.DEFAULT_SECTION_ID);

        // add gef tools manually for the moment.
        addToolById("selectionTool", defaultToolSection); //$NON-NLS-1$
        addToolById("zoomInTool", defaultToolSection); //$NON-NLS-1$
        addToolById("zoomOutTool", defaultToolSection); //$NON-NLS-1$
        addToolById("noteTool", defaultToolSection); //$NON-NLS-1$
        addToolById("textTool", defaultToolSection); //$NON-NLS-1$
        addToolById(ToolConstants.TOOL_NOTEATTACHMENT, defaultToolSection);

        // add generic tool
        addToolById(ToolConstants.TOOL_GENERIC_CONNECTION_CREATION, defaultToolSection);

        // add VSM tools
        List<ToolEntry> defaultTools = getDefaultTools(TransactionUtil.getEditingDomain(dDiagram).getResourceSet());
        for (ToolEntry toolEntry : defaultTools) {
            addNewTool(defaultToolSection, toolEntry, true);
        }

        addVSMTools(session, updateFilters);

        return dDiagram.getUiState().getToolSections();
    }

    private void addToolById(String id, ToolSectionInstance defaultToolSection) {
        ToolInstance tool = ViewpointFactory.eINSTANCE.createToolInstance();
        tool.setId(id);
        tool.setVisible(true);
        tool.setEnabled(true);
        defaultToolSection.getTools().add(tool);
    }

    /**
     * Adds a new {@link ToolInstance} referencing the given {@link ToolEntry} to the list of available tools for
     * attached diagram.
     * 
     * @param toolSectionParent
     *            the {@link ToolSectionInstance} where to put the new {@link ToolInstance}.
     * 
     * @param toolEntry
     *            the {@link ToolEntry} associated to the new {@link ToolInstance}
     * @param useNameAsId
     *            true if the name of the {@link ToolEntry} should be used as id. False if the id should be based on its
     *            URI.
     * @return Returns the newly created ToolInstance.
     */
    private ToolInstance addNewTool(ToolSectionInstance toolSectionParent, ToolEntry toolEntry, boolean useNameAsId) {
        ToolInstance newToolInstance = createNewTool(toolEntry, useNameAsId);
        toolSectionParent.getTools().add(newToolInstance);
        return newToolInstance;
    }

    /**
     * Adds a new {@link ToolInstance} referencing the given {@link ToolEntry} to the list of available tools for
     * attached diagram.
     * 
     * @param toolSectionParent
     *            the {@link ToolSectionInstance} where to put the new {@link ToolInstance}.
     * 
     * @param toolEntry
     *            the {@link ToolEntry} associated to the new {@link ToolInstance}
     * @param useNameAsId
     *            true if the name of the {@link ToolEntry} should be used as id. False if the id should be based on its
     *            URI.
     * @return Returns the newly created ToolInstance.
     */
    private ToolInstance createNewTool(ToolEntry toolEntry, boolean useNameAsId) {
        ToolInstance newToolInstance = ViewpointFactory.eINSTANCE.createToolInstance();
        if (useNameAsId) {
            newToolInstance.setId(toolEntry.getName());
        } else {
            newToolInstance.setId(getId(toolEntry));
        }

        newToolInstance.setVisible(true);
        newToolInstance.setEnabled(true);
        newToolInstance.setToolEntry(toolEntry);

        if (toolEntry instanceof AbstractToolDescription && isFiltered((AbstractToolDescription) toolEntry)) {
            newToolInstance.setFiltered(true);
        }

        return newToolInstance;
    }

    /**
     * Add a tool filter.
     * 
     * @param toolFilter
     *            the tool filter to add.
     */
    public void addToolFilter(ToolFilter toolFilter) {
        filters.add(toolFilter);
    }

    /**
     * Returns the id of a {@link ToolEntry}.
     * 
     * @param entry
     *            the entry from which the id should be returned.
     * @return the id of a {@link ToolEntry}.
     */
    public static String getId(final EObject entry) {
        return EcoreUtil.getURI(entry).toString();
    }

    /**
     * Adds VSM tools for a diagram that has no layer.
     * 
     * @param session
     *            The {@session} containing the {@link DDiagram}.
     * @param updateFilters
     */
    private void addVSMToolsForDiagramWithoutLayer(Session session, boolean updateFilters) {
        // Update the filters
        DiagramDescription diagramDescription = dDiagram.getDescription();
        if (updateFilters) {
            updateFilters(session, diagramDescription.getAllTools());
        }

        // Owned tools
        ToolSection toolSection = diagramDescription.getToolSection();
        if (toolSection != null) {
            initToolSection(toolSection);
        }
    }

    private void initToolSection(ToolSection toolSection) {
        ToolSectionInstance newToolSectionInstance = ViewpointFactory.eINSTANCE.createToolSectionInstance();
        newToolSectionInstance.setId(ToolManagement.getId(toolSection));
        newToolSectionInstance.setSection(newToolSectionInstance);
        dDiagram.getUiState().getToolSections().add(newToolSectionInstance);
        newToolSectionInstance.getTools().addAll(toolSection.getOwnedTools().stream().map(ts -> {
            return createNewTool(ts, false);
        }).collect(Collectors.toSet()));
        newToolSectionInstance.getTools().addAll(toolSection.getReusedTools().stream().map(ts -> {
            return createNewTool(ts, false);
        }).collect(Collectors.toSet()));
        EList<ToolGroupExtension> groupExtensions = toolSection.getGroupExtensions();
        for (ToolGroupExtension toolGroupExtension : groupExtensions) {
            ToolGroup group = toolGroupExtension.getGroup();
            ToolGroupInstance newToolGroupInstance = ViewpointFactory.eINSTANCE.createToolGroupInstance();
            newToolGroupInstance.setId(group.getName());
            newToolGroupInstance.setGroup(newToolGroupInstance);
            newToolGroupInstance.getTools().addAll(group.getTools().stream().map(ts -> {
                return createNewTool(ts, false);
            }).collect(Collectors.toSet()));
            newToolSectionInstance.getTools().add(newToolGroupInstance);
        }
        EList<ToolSection> subSections = toolSection.getSubSections();
        for (ToolSection subToolSection : subSections) {
            initToolSection(subToolSection);
        }
    }

    /**
     * Adds VSM tools for a diagram that has layer(s), at least a default one.
     * 
     * @param session
     *            The {@session} containing the {@link DDiagram}.
     * @param updateFilters
     */
    private void addVSMToolsForDiagramWithLayer(Session session, boolean updateFilters) {
        Map<String, List<ToolInstance>> idToTool = new HashMap<>();
        // Copy of all layers of selected viewpoints
        HashSet<Layer> layersInActivatedViewpoints = new HashSet<Layer>(new DiagramComponentizationManager().getAllLayers(session.getSelectedViewpoints(false), dDiagram.getDescription()));
        // Copy of diagram activated layers (in all Viewpoints: activated or
        // not)
        Set<Layer> activatedLayers = new HashSet<Layer>(new DDiagramQuery(dDiagram).getAllActivatedLayers());
        // Get the list of activated layers (of selected viewpoints)
        activatedLayersOfSelectedViewpoints = Lists.newArrayList(Sets.intersection(layersInActivatedViewpoints, activatedLayers));
        // Get the list of deactivated layers (deactivated layers of selected
        // viewpoints and all layers of deselected viewpoints)
        deactivatedLayersAndAllLayersOfDeselectedViewpoints = Lists.newArrayList(Sets.symmetricDifference(layersInActivatedViewpoints, activatedLayers));
        Map<String, ToolSectionInstance> idToToolSection = new HashMap<>();
        // Update the filters
        for (final ToolSection section : new DiagramComponentizationManager().getRootPaletteSections(session.getSelectedViewpoints(false), dDiagram.getDescription())) {
            if (updateFilters) {
                updateFilters(session, new DiagramComponentizationManager().getAllToolEntries(session.getSelectedViewpoints(false), section));
            }
            ToolSectionInstance newToolSectionInstance = ViewpointFactory.eINSTANCE.createToolSectionInstance();
            String id = ToolManagement.getId(section);
            newToolSectionInstance.setId(id);
            idToToolSection.put(id, newToolSectionInstance);
            newToolSectionInstance.setSection(section);
            dDiagram.getUiState().getToolSections().add(newToolSectionInstance);
            List<ToolEntry> allToolEntries = new DiagramComponentizationManager().getAllToolEntries(session.getSelectedViewpoints(false), section);
            for (ToolEntry toolEntry : allToolEntries) {
                if (toolEntry instanceof ToolGroup) {
                    ToolGroupInstance newToolGroupInstance = ViewpointFactory.eINSTANCE.createToolGroupInstance();
                    newToolGroupInstance.setId(getId(toolEntry));
                    newToolGroupInstance.setGroup(newToolGroupInstance);
                    newToolGroupInstance.setToolEntry(toolEntry);

                    // get extension tools for a group
                    newToolGroupInstance.getTools().addAll(new DiagramComponentizationManager().getTools(session.getSelectedViewpoints(false), (ToolGroup) toolEntry).stream().map(ts -> {
                        ToolInstance tool = createNewTool(ts, false);
                        return tool;
                    }).collect(Collectors.toCollection(LinkedHashSet::new)));
                    newToolSectionInstance.getTools().add(newToolGroupInstance);
                } else {
                    ToolInstance newToolInstance = createNewTool(toolEntry, false);
                    newToolSectionInstance.getTools().add(newToolInstance);
                    List<ToolInstance> tools = idToTool.get(newToolInstance.getId());
                    if (tools == null) {
                        tools = new ArrayList<>();
                        idToTool.put(newToolInstance.getId(), tools);
                    }
                    tools.add(newToolInstance);
                }

            }
        }
        for (final Layer layer : Lists.newArrayList(deactivatedLayersAndAllLayersOfDeselectedViewpoints)) {
            EList<ToolSection> toolSections = layer.getToolSections();
            for (ToolSection toolSection : toolSections) {
                ToolSectionInstance toolSectionInstance = idToToolSection.get(ToolManagement.getId(toolSection));
                if (toolSectionInstance != null) {
                    toolSectionInstance.setVisible(false);
                }
            }
        }
    }

    /**
     * Adds VSM tools to the given list of {@link ToolInstance}.
     * 
     * @param session
     *            The {@session} containing the {@link DDiagram}.
     * @param updateFilters
     */
    private void addVSMTools(Session session, boolean updateFilters) {
        if (LayerService.withoutLayersMode(dDiagram.getDescription())) {
            addVSMToolsForDiagramWithoutLayer(session, updateFilters);
        } else {
            addVSMToolsForDiagramWithLayer(session, updateFilters);
        }
    }

    private Iterator<ToolFilter> getAllToolFilters() {
        return Iterators.concat(filters.iterator(), ToolManagementRegistry.getInstance().getProvidedToolFilters().iterator());
    }

    private boolean isFiltered(AbstractToolDescription toolDescription) {
        for (Iterator<ToolFilter> it = getAllToolFilters(); it.hasNext();) {
            if (it.next().filter(dDiagram, toolDescription)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Update tool filters. If the session is null, nothing will be done.
     * 
     * @param session
     *            the session
     * @param toolEntries
     *            the list of entry of tools to add.
     */
    private void updateFilters(final Session session, final List<? extends ToolEntry> toolEntries) {
        if (session != null) {
            final IInterpreter interpreter = session.getInterpreter();
            if (interpreter != null) {
                for (final ToolEntry toolEntry : toolEntries) {
                    if (toolEntry instanceof AbstractToolDescription) {
                        /* create filters from description */
                        for (final ToolFilterDescription filterDescription : ((AbstractToolDescription) toolEntry).getFilters()) {
                            ToolFilter filter = new ToolFilterFromDescription(interpreter, filterDescription);
                            filters.add(filter);
                        }
                        listenersManager.addListenersForFilters(interpreter, ((AbstractToolDescription) toolEntry).getFilters());
                    } else if (toolEntry instanceof ToolGroup) {
                        updateFilters(session, new DiagramComponentizationManager().getTools(session.getSelectedViewpoints(false), (ToolGroup) toolEntry));
                    }
                }
            }
        }
    }

    private List<ToolEntry> getDefaultTools(final ResourceSet context) {
        final Resource coreEnvResource = context.getResource(URI.createURI(ViewpointUtil.VIEWPOINT_ENVIRONMENT_RESOURCE_URI, true), true);
        final Environment coreEnv = (Environment) coreEnvResource.getContents().get(0);

        final Resource diagramEnvResource = context.getResource(URI.createURI(SiriusDiagramUtil.DIAGRAM_ENVIRONMENT_RESOURCE_URI, true), true);
        final Environment diagramEnv = (Environment) diagramEnvResource.getContents().get(0);

        List<ToolEntry> defaultTools = new ArrayList<>();
        defaultTools.addAll(coreEnv.getDefaultTools());
        defaultTools.addAll(diagramEnv.getDefaultTools());
        return defaultTools;
    }

}
