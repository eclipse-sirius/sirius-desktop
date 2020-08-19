/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.palette;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.PaletteStack;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gmf.runtime.diagram.ui.internal.services.palette.PaletteToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.services.palette.PaletteFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.business.api.helper.SiriusDiagramUtil;
import org.eclipse.sirius.diagram.business.api.helper.layers.LayerService;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.RequestDescription;
import org.eclipse.sirius.diagram.description.tool.ToolGroup;
import org.eclipse.sirius.diagram.description.tool.ToolGroupExtension;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.diagram.tools.api.management.ToolConstants;
import org.eclipse.sirius.diagram.tools.api.management.ToolFilter;
import org.eclipse.sirius.diagram.tools.api.management.ToolManagement;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.palette.PaletteManager;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.ToolGroupInstance;
import org.eclipse.sirius.viewpoint.ToolInstance;
import org.eclipse.sirius.viewpoint.ToolSectionInstance;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;
import org.eclipse.swt.widgets.Display;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;

/**
 * Default implementation of palette manager.
 * 
 * @author mchauvin
 */
public class PaletteManagerImpl implements PaletteManager {

    /** The image provider. */
    private static PaletteImageProvider paletteImageProvider = new PaletteImageProvider();

    private EditDomain editDomain;

    private PaletteRoot paletteRoot;

    private boolean isDisposed;

    /**
     * Construct a new instance.
     * 
     * @param editDomain
     *            the edit domain
     */
    public PaletteManagerImpl(EditDomain editDomain) {
        this.editDomain = editDomain;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.palette.PaletteManager#dispose()
     */
    @Override
    public void dispose() {
        isDisposed = true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.palette.PaletteManager#addToolFilter(org.eclipse.sirius.diagram.tools.api.graphical.edit.palette.ToolFilter)
     */
    @Override
    public void addToolFilter(ToolFilter toolFilter) {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.palette.PaletteManager#removeToolFilter(org.eclipse.sirius.diagram.tools.api.graphical.edit.palette.ToolFilter)
     */
    @Override
    public void removeToolFilter(ToolFilter toolFilter) {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.palette.PaletteManager#update()
     */
    @Override
    public void update(final DDiagram dDiagram) {
        update(dDiagram, false);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.palette.PaletteManager#update()
     */
    @Override
    public void update(final DDiagram dDiagram, final boolean clean) {
        if (dDiagram != null) {
            initPaletteRoot();

            if (Display.getCurrent() != null) {
                updatePalette(dDiagram, clean);
            } else {
                // If test is not launched in UI Thread make the changes in UI
                // thread
                Display.getDefault().asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        updatePalette(dDiagram, clean);
                    }
                });
            }
        }
    }

    private void updatePalette(final DDiagram diagram, boolean clean) {
        initPaletteRoot();
        if (paletteRoot != null) {
            if (clean && !paletteRoot.getChildren().isEmpty()) {
                /*
                 * Removes children and avoid concurrent modification exception
                 */
                PaletteContainer defaultTools = paletteRoot.getDefaultEntry().getParent();
                for (final PaletteEntry child : Lists.newArrayList(Iterables.filter(paletteRoot.getChildren(), PaletteEntry.class))) {
                    if (child != defaultTools) {
                        paletteRoot.remove(child);
                    }
                }
            }
        }

        /*
         * Update the palette (according to filters, that are also updated during this method)
         */
        updatePalette(diagram);
        paletteRoot = null;
    }

    /**
     * Set the palette root field with the palette root referenced in the palette viewer and if there is no palette
     * viewer, use reflection to directly get the palette root of the edit domain (protected field).
     * 
     * @param diagram
     */
    private void initPaletteRoot() {
        final PaletteViewer viewer = editDomain.getPaletteViewer();
        if (viewer != null) {
            paletteRoot = viewer.getPaletteRoot();
        } else {
            paletteRoot = (PaletteRoot) ReflectionHelper.getFieldValueWithoutException(editDomain, "paletteRoot").get(); //$NON-NLS-1$
        }
    }

    /**
     * Update the palette.
     * 
     * @param diagram
     *            the diagram.
     */
    private void updatePalette(final DDiagram dDiagram) {
        if (dDiagram != null) {
            final DiagramDescription description = dDiagram.getDescription();
            Session session = null;
            if (dDiagram instanceof DSemanticDiagram) {
                session = SessionManager.INSTANCE.getSession(((DSemanticDiagram) dDiagram).getTarget());
            }

            // Step 1: we add the default tools contributed by the environment
            // in the same group as the default GEF tools
            addDefaultTools(dDiagram);

            // Step 2: Replace the ConnectionCreationTool for
            // DiagramPaletteFactory.TOOL_NOTEATTACHMENT (if needed)
            replaceNoteAttachmentCreationToolIfNeeded();
            if (session != null && description != null && description.eResource() != null && !description.eIsProxy()) {
                updatePalette(description, session, dDiagram);
            }
        }
    }

    @SuppressWarnings("restriction")
    private void addGenericConnectionTool() {
        PaletteToolEntry paletteEntry = new PaletteToolEntry(ToolConstants.TOOL_GENERIC_CONNECTION_CREATION, Messages.GenericConnectionCreationTool_label, new SiriusDiagramPaletteFactory());
        paletteEntry.setToolClass(GenericConnectionCreationTool.class);
        ImageDescriptor descIcon = org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin.Implementation.findImageDescriptor("icons/full/obj16/EdgeCreationDescription.gif"); //$NON-NLS-1$
        paletteEntry.setSmallIcon(descIcon);
        paletteEntry.setLargeIcon(descIcon);
        final PaletteContainer container = paletteRoot.getDefaultEntry().getParent();
        container.add(paletteEntry);
    }

    /**
     * Update the palette.
     * 
     * @param dDiagram
     *            The {@link DDiagram} representing by the editor which contains the palette to update.
     * @param description
     *            The {@link DiagramDescription} of the {@link DDiagram}. It should not be a proxy.
     * @param session
     *            The {@session} containing the {@link DDiagram}.
     */
    private void updatePalette(DiagramDescription description, Session session, DDiagram dDiagram) {
        if (LayerService.withoutLayersMode(description)) {
            updatePaletteForDiagramWithoutLayer(description, session, dDiagram);
        } else {
            updatePaletteForDiagramWithLayer(description, session, dDiagram);
        }
    }

    /**
     * Update the palette for a diagram that has no layer.
     * 
     * @param dDiagram
     *            The {@link DDiagram} representing by the editor which contains the palette to update.
     * @param description
     *            The {@link DiagramDescription} of the {@link DDiagram}. It should not be a proxy.
     * @param session
     *            The {@session} containing the {@link DDiagram}.
     */
    private void updatePaletteForDiagramWithoutLayer(DiagramDescription description, Session session, DDiagram dDiagram) {
        /*
         * if no layers => compatibility mode create a single palette group
         */
        final String name = new IdentifiedElementQuery(description).getLabel();
        Option<PaletteGroup> descGroup = getPaletteEntry(paletteRoot, name, PaletteGroup.class);
        if (!descGroup.some()) {
            descGroup = Options.newSome(new PaletteGroup(name, name));
            paletteRoot.add(descGroup.get());
        }
        // Update the root of the palette with only VSM tools
        updateContainer(session, dDiagram, descGroup.get(),
                dDiagram.getUiState().getToolSections().stream().flatMap(section -> section.getTools().stream()).collect(Collectors.toList()));
    }

    /**
     * Update the palette for a diagram that has layer(s), at least a default one.
     * 
     * @param dDiagram
     *            The {@link DDiagram} representing by the editor which contains the palette to update.
     * @param description
     *            The {@link DiagramDescription} of the {@link DDiagram}. It should not be a proxy.
     * @param session
     *            The {@session} containing the {@link DDiagram}.
     */
    private void updatePaletteForDiagramWithLayer(DiagramDescription description, Session session, DDiagram dDiagram) {
        for (final ToolSectionInstance sectionInstance : dDiagram.getUiState().getToolSections()) {
            if (!ToolConstants.DEFAULT_SECTION_ID.equals(sectionInstance.getId())) {
                Option<SectionPaletteDrawer> paletteEntry = getPaletteEntry(paletteRoot, PaletteManagerImpl.getToolSectionId((ToolSection) sectionInstance.getSection()), SectionPaletteDrawer.class);
                List<ToolInstance> toolInstances = sectionInstance.getTools();
                if (!paletteEntry.some()) {
                    final PaletteContainer container = PaletteManagerImpl.createPaletteDrawner((ToolSection) sectionInstance.getSection());
                    updateContainer(session, dDiagram, container, toolInstances);
                    paletteRoot.add(container);
                } else {
                    updateContainer(session, dDiagram, paletteEntry.get(), toolInstances);
                }
            }
        }
        for (final Layer layer : new ArrayList<>(DiagramPlugin.getPlugin().getToolManagement(dDiagram).getDeactivatedLayersAndAllLayersOfDeselectedViewpoints())) {
            setLayerVisibility(layer, false);
        }
        for (final Layer layer : new ArrayList<>(DiagramPlugin.getPlugin().getToolManagement(dDiagram).getActivatedLayersOfSelectedViewpoints())) {
            setLayerVisibility(layer, true);
        }
    }

    /**
     * Returns the palette entry contained in the given {@link PaletteContainer} with the given id, of the given type.
     * If none found, {@link Options#newNone()} will be returned. If several found, we will log a warning and return
     * only one of the candidates.
     * 
     * @param <T>
     *            the type of the searched palette entry
     * @param container
     *            the container in which search for this palette entry
     * @param id
     *            the searched id
     * @param type
     *            the expected type
     * @return {@link Options#newNone()} if no matching candidate is found, or the found candidate (if several found, we
     *         will log a warning and return only one of the candidates).
     */
    private <T extends PaletteEntry> Option<T> getPaletteEntry(PaletteContainer container, final String id, Class<T> type) {
        Option<T> matchingPaletteEntry = Options.newNone();
        UnmodifiableIterator<T> matchingPaletteEntries = Iterators.filter(Iterators.filter(container.getChildren().iterator(), type), new Predicate<T>() {
            @Override
            public boolean apply(T paletteEntry) {
                return id.equals(paletteEntry.getId());
            }
        });
        try {
            matchingPaletteEntry = Options.newSome(Iterators.getOnlyElement(matchingPaletteEntries));
        } catch (NoSuchElementException e) {
            // Here no matching candidate has been found, we will return
            // Options.newNone
        } catch (IllegalArgumentException e) {
            DiagramPlugin.getDefault().logWarning(MessageFormat.format(Messages.PaletteManagerImpl_severalCandidatesInPalette, type.getName(), id));
            // Here no matching candidate has been found, we will return
            // Options.newNone
        }
        return matchingPaletteEntry;
    }

    /**
     * Replace if needed the GMF note attachment tool by a specific one (2 clicks for link creation instead of one click
     * with drag).
     */
    @SuppressWarnings("restriction")
    private void replaceNoteAttachmentCreationToolIfNeeded() {
        // Get the container of the Note Attachment Creation Tool
        String notesContainerLabel = Platform.getResourceString(org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIPlugin.getInstance().getBundle(), "%NoteStack.Label"); //$NON-NLS-1$
        PaletteContainer notesContainer = getPaletteContainer(paletteRoot, notesContainerLabel);
        if (notesContainer != null) {
            // Get the current noteAttachment tool
            CreationToolEntry noteAttachment = getNoteAttachementToolEntry(notesContainer);
            // If the current noteAttachement tool is not using the
            // SiriusDiagramPaletteFactory
            if (!(noteAttachment.getToolProperty(CreationTool.PROPERTY_CREATION_FACTORY) instanceof SiriusDiagramPaletteFactory)) {
                // We create a new palette entry and replace the existing one
                SiriusDiagramPaletteFactory viewpointDiagramPaletteFactory = new SiriusDiagramPaletteFactory();
                CreationToolEntry paletteEntry = new PaletteToolEntry(noteAttachment.getId(), noteAttachment.getLabel(), viewpointDiagramPaletteFactory);
                paletteEntry.setToolProperty(CreationTool.PROPERTY_CREATION_FACTORY, viewpointDiagramPaletteFactory);
                paletteEntry.setDescription(noteAttachment.getDescription());
                paletteEntry.setLargeIcon(noteAttachment.getLargeIcon());
                paletteEntry.setSmallIcon(noteAttachment.getSmallIcon());
                notesContainer.add(notesContainer.getChildren().indexOf(noteAttachment), paletteEntry);
                notesContainer.remove(noteAttachment);
            }
        }
    }

    @SuppressWarnings("restriction")
    private CreationToolEntry getNoteAttachementToolEntry(final PaletteContainer container) {
        String noteAttachmentToolLabel = Platform.getResourceString(org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIPlugin.getInstance().getBundle(), "%NoteAttachmentTool.Label"); //$NON-NLS-1$
        for (Object child : container.getChildren()) {
            if (child instanceof CreationToolEntry) {
                CreationToolEntry paletteToolEntry = (CreationToolEntry) child;
                if (noteAttachmentToolLabel.equals(paletteToolEntry.getLabel())) {
                    return paletteToolEntry;
                }
            }
        }
        return null;
    }

    /**
     * Search a palette container by its label in the children of the <code>container</code>.
     * 
     * @param container
     *            The container in which searched
     * @param searchedLabel
     *            The searched label
     * @return A palette container if found, false otherwise.
     */
    private PaletteContainer getPaletteContainer(PaletteContainer container, String searchedLabel) {
        PaletteContainer result = null;
        if (container != null) {
            for (Object child : container.getChildren()) {
                if (child instanceof PaletteContainer) {
                    if (searchedLabel.equals(((PaletteContainer) child).getLabel())) {
                        result = (PaletteContainer) child;
                    } else {
                        result = getPaletteContainer((PaletteContainer) child, searchedLabel);
                    }
                }
                if (result != null) {
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Hide all tools provided by a layer.
     * 
     * @param layer
     *            the layer
     */
    @Override
    public void hideLayer(final Layer layer) {
    }

    /**
     * Show all tools provided by a layer.
     * 
     * @param layer
     *            the layer
     */
    @Override
    public void showLayer(final Layer layer) {
    }

    private void setLayerVisibility(final Layer layer, final boolean visibility) {
        final List<ToolSection> sections = layer.getToolSections();
        for (final SectionPaletteDrawer sectionDrawer : Iterables.filter(paletteRoot.getChildren(), SectionPaletteDrawer.class)) {
            setToolSectionsVisibility(layer, sectionDrawer, sections, visibility);
            if (sectionDrawer.getChildren().isEmpty()) {
                sectionDrawer.setVisible(false);
            } else if (!visibility && sectionDrawer.isEmptyOfContributors()) {
                sectionDrawer.setVisible(false);
            } else if (visibility && !sectionDrawer.isEmptyOfContributors()) {
                sectionDrawer.setVisible(true);
            }
        }
    }

    private void setToolSectionsVisibility(final Layer layer, final SectionPaletteDrawer drawer, final Collection<ToolSection> sections, final boolean visibility) {
        for (final ToolSection section : sections) {
            if (drawer.getId().equals(PaletteManagerImpl.getToolSectionId(section))) {
                if (visibility && PaletteManagerImpl.isSectionNotEmpty(section)) {
                    drawer.addLayer(layer);
                } else {
                    drawer.removeLayer(layer);
                }
            }
            PaletteManagerImpl.setToolsVisibility(drawer, layer, section, visibility);
        }
    }

    // A section is not empty when :
    // - it has owned tool
    // - or it has reused tools
    // - or it has not empty subsection
    private static boolean isSectionNotEmpty(ToolSection toolSection) {
        boolean result = !toolSection.getOwnedTools().isEmpty();
        result = result || !toolSection.getReusedTools().isEmpty();
        Iterator<ToolSection> iterator = toolSection.getSubSections().iterator();
        // Do iteration only if previous tests lead to "false" result
        while (!result && iterator.hasNext()) {
            ToolSection subSection = iterator.next();
            result = PaletteManagerImpl.isSectionNotEmpty(subSection);
        }
        return result;
    }

    private static void setToolsVisibility(final PaletteDrawer drawner, final Layer layer, final ToolSection section, final boolean visibility) {
        for (final PaletteEntry entry : (List<PaletteEntry>) drawner.getChildren()) {
            if (entry instanceof ToolGroupPaletteStack) {
                final ToolGroupPaletteStack stack = (ToolGroupPaletteStack) entry;
                PaletteManagerImpl.setPaletteStackVisibility(stack, layer, section.getOwnedTools(), visibility);
                PaletteManagerImpl.setPaletteStackVisibility(stack, layer, section.getReusedTools(), visibility);
                for (final ToolGroupExtension groupExtension : section.getGroupExtensions()) {
                    if (stack.getId().equals(ToolManagement.getId(groupExtension.getGroup()))) {
                        if (visibility) {
                            stack.addLayer(layer);
                        } else {
                            stack.removeLayer(layer);
                        }
                        for (final PaletteEntry subEntry : (List<PaletteEntry>) stack.getChildren()) {
                            PaletteManagerImpl.setPaletteEntryVisibility(subEntry, groupExtension.getTools(), visibility);
                        }
                    }
                }
                if (!visibility && stack.isEmptyOfContributors()) {
                    stack.setVisible(false);
                } else if (visibility && !stack.isEmptyOfContributors()) {
                    stack.setVisible(true);
                }
            } else {
                PaletteManagerImpl.setPaletteEntryVisibility(entry, section.getOwnedTools(), visibility);
                PaletteManagerImpl.setPaletteEntryVisibility(entry, section.getReusedTools(), visibility);
            }
        }
    }

    private static void setPaletteStackVisibility(final ToolGroupPaletteStack stack, final Layer layer, final Collection<? extends ToolEntry> toolEntries, final boolean visibility) {
        for (final ToolEntry toolEntry : toolEntries) {
            if (toolEntry instanceof ToolGroup) {
                if (stack.getId().equals(ToolManagement.getId(toolEntry))) {
                    if (visibility) {
                        stack.addLayer(layer);
                    } else {
                        stack.removeLayer(layer);
                    }
                }
            }
        }
    }

    private static void setPaletteEntryVisibility(final PaletteEntry entry, final Collection<? extends ToolEntry> toolEntries, final boolean visibility) {
        for (final ToolEntry toolEntry : toolEntries) {
            if (entry.getId().equals(ToolManagement.getId(toolEntry))) {
                entry.setVisible(visibility);
            }
        }
    }

    private static String getToolSectionId(final ToolSection toolSection) {
        return new IdentifiedElementQuery(toolSection).getLabel();
    }

    /**
     * Adds the default tools contributed by the environment in the same group as the default GEF tools.
     */
    private void addDefaultTools(final DDiagram diagram) {
        final PaletteContainer container = paletteRoot.getDefaultEntry().getParent();
        for (Object entry : container.getChildren()) {
            if (entry instanceof PaletteSeparator && ToolConstants.TOOL_SEPARATOR_DEFAULT.equals(((PaletteSeparator) entry).getId())) {
                // Default tools are already there. Nothing to do.
                return;
            }
        }
        final PaletteSeparator marker = new PaletteSeparator(ToolConstants.TOOL_SEPARATOR_DEFAULT);
        marker.setVisible(false);
        container.add(marker);

        // We add the generic connection tool.
        if (Platform.getPreferencesService().getBoolean(DiagramPlugin.ID, SiriusDiagramPreferencesKeys.PREF_DISPLAY_GENERIC_EDGE_CREATION_TOOL.name(), false, null)) {
            // if generic present in UIState
            addGenericConnectionTool();
        }
        for (final ToolSectionInstance toolSectionInstance : diagram.getUiState().getToolSections()) {
            if (ToolConstants.DEFAULT_SECTION_ID.equals(toolSectionInstance.getId())) {
                EList<ToolInstance> tools = toolSectionInstance.getTools();
                for (ToolInstance toolInstance : tools) {
                    if (ToolConstants.TOOL_GENERIC_CONNECTION_CREATION.equals(toolInstance.getId()) || ToolConstants.TOOL_PINNING.equals(toolInstance.getId())) {
                        addElementToContainer(container, toolInstance.getToolEntry());
                    }
                }
            }
        }
    }

    private static PaletteContainer createPaletteDrawner(final ToolSection section) {
        final String name = MessageTranslator.INSTANCE.getMessage(section, new IdentifiedElementQuery(section).getLabel());
        String iconPath = section.getIcon();
        final PaletteContainer paletteDrawner = new SectionPaletteDrawer(name);
        paletteDrawner.setId(PaletteManagerImpl.getToolSectionId(section));
        if (StringUtil.isEmpty(iconPath)) {
            iconPath = "icons/obj16/ToolSection.gif"; //$NON-NLS-1$
        }
        final ImageDescriptor descIcon = org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin.Implementation.findImageDescriptor(iconPath);
        if (descIcon != null) {
            paletteDrawner.setSmallIcon(descIcon);
        }
        return paletteDrawner;
    }

    /**
     * Update the container with the list of tool. The tools are created only if needed (not already existing) and not
     * filtered. If a tool exists and should be filtered, it is removed from the container.
     * 
     * @param session
     *            the current session
     * @param dDiagram
     *            the {@link DDiagram} currently represented.
     * @param container
     *            the container to fill
     * @param toolEntries
     *            the list of entry of tools to add.
     */
    protected void updateContainer(final Session session, DDiagram dDiagram, final PaletteContainer container, final List<? extends ToolInstance> toolEntries) {

        for (final ToolInstance toolInstances : toolEntries) {
            ToolEntry toolEntry = toolInstances.getToolEntry();
            if (toolEntry instanceof AbstractToolDescription) {
                /*
                 * do not create a new entry for the tool if it should not be displayed
                 */
                Option<PaletteEntry> paletteEntry = getPaletteEntry(container, toolInstances.getId(), PaletteEntry.class);
                if (!toolInstances.isFiltered()) {
                    addElementToContainer(container, toolEntry, paletteEntry);
                } else {
                    container.remove(paletteEntry.get());
                }
            } else if (toolInstances instanceof ToolGroupInstance) {
                Option<ToolGroupPaletteStack> paletteStack = getPaletteEntry(container, toolInstances.getId(), ToolGroupPaletteStack.class);
                boolean paletteWasCreated = false;
                if (!paletteStack.some()) {
                    paletteStack = Options.newSome(new ToolGroupPaletteStack(((ToolGroup) toolEntry).getName()));
                    paletteWasCreated = true;
                }
                for (ToolInstance siriusToolChild : ((ToolGroupInstance) toolInstances).getTools()) {
                    /*
                     * do not create a new entry for the tool if it should not be displayed
                     */
                    Option<PaletteEntry> paletteEntry = getPaletteEntry(paletteStack.get(), siriusToolChild.getId(), PaletteEntry.class);

                    if (!siriusToolChild.isFiltered()) {
                        addElementToContainer(paletteStack.get(), siriusToolChild.getToolEntry(), paletteEntry);
                    } else {
                        if (paletteEntry.some()) {
                            paletteStack.get().remove(paletteEntry.get());
                            if (paletteStack.get().getChildren().isEmpty()) {
                                // removed if empty to avoid palette stack to be
                                // displayed as first when a diagram palette is
                                // emptied and refilled
                                container.remove(paletteStack.get());
                            }
                        }
                    }
                }

                if (paletteWasCreated && !paletteStack.get().getChildren().isEmpty()) {
                    // avoid creating empty palette stack to avoid being
                    // displayed as first when a diagram palette is emptied and
                    // refilled
                    paletteStack.get().setId(toolInstances.getId());
                    container.add(paletteStack.get());
                }
            }
        }
    }

    /**
     * Fills the group.
     * 
     * @param container
     *            the group.
     * @param toolEntry
     *            the tool to add.
     */
    protected void addElementToContainer(final PaletteContainer container, final ToolEntry toolEntry) {
        addElementToContainer(container, toolEntry, Options.<PaletteEntry> newNone());
    }

    /**
     * Fills the group.
     * 
     * @param container
     *            the group.
     * @param toolEntry
     *            the tool to add.
     * @param existingPaletteEntry
     *            the palette entry currently existing with the id of toolEntry, or {@link Options#newNone()} if it does
     *            not currently exists
     */
    protected void addElementToContainer(final PaletteContainer container, final ToolEntry toolEntry, final Option<PaletteEntry> existingPaletteEntry) {
        if (toolEntry instanceof ToolGroup) {
            PaletteStack paletteStack;
            String newName;
            if (isFromDiagramEnvironment(toolEntry)) {
                newName = MessageTranslator.INSTANCE.getMessage(DiagramPlugin.getPlugin().getBundle(), new IdentifiedElementQuery(toolEntry).getLabel());
            } else {
                newName = MessageTranslator.INSTANCE.getMessage(toolEntry, new IdentifiedElementQuery(toolEntry).getLabel());
            }
            if (!existingPaletteEntry.some()) {
                paletteStack = new ToolGroupPaletteStack(newName);
                paletteStack.setId(ToolManagement.getId(toolEntry));
                container.add(paletteStack);
            } else if (existingPaletteEntry.get() instanceof PaletteStack) {
                paletteStack = (PaletteStack) existingPaletteEntry.get();
            } else {
                throw new IllegalArgumentException(MessageFormat.format(Messages.PaletteManagerImpl_alreadyExistingEntry, newName));
            }
            for (final AbstractToolDescription tool : ((ToolGroup) toolEntry).getTools()) {
                Option<PaletteEntry> paletteEntry = getPaletteEntry(paletteStack, new IdentifiedElementQuery(tool).getLabel(), PaletteEntry.class);
                addElementToContainer(paletteStack, tool, paletteEntry);
            }
        } else if (toolEntry instanceof AbstractToolDescription) {
            if (!existingPaletteEntry.some()) {
                final AbstractToolDescription toolDescription = (AbstractToolDescription) toolEntry;
                final ImageDescriptor imageEntry = paletteImageProvider.getImageDescriptor(toolDescription);
                final String nameEntry;
                final String descriptionEntry;
                if (isFromDiagramEnvironment(toolDescription)) {
                    nameEntry = MessageTranslator.INSTANCE.getMessage(DiagramPlugin.getPlugin().getBundle(), new IdentifiedElementQuery(toolDescription).getLabel());
                    descriptionEntry = MessageTranslator.INSTANCE.getMessage(DiagramPlugin.getPlugin().getBundle(), toolDescription.getDocumentation());
                } else {
                    nameEntry = MessageTranslator.INSTANCE.getMessage(toolDescription, new IdentifiedElementQuery(toolDescription).getLabel());
                    descriptionEntry = MessageTranslator.INSTANCE.getMessage(toolDescription, toolDescription.getDocumentation());
                }
                final CreationFactory creationFactory = new PaletteToolBasedCreationFactory(toolDescription);
                CreationToolEntry paletteEntry = null;
                if (toolDescription instanceof EdgeCreationDescription) {
                    paletteEntry = new ConnectionCreationToolEntry(nameEntry, descriptionEntry, creationFactory, imageEntry, imageEntry);
                    paletteEntry.setToolClass(ConnectionCreationTool.class);
                    paletteEntry.setId(nameEntry);
                } else if (requiresPaletteToolEntry(toolDescription)) {
                    paletteEntry = createPaletteToolEntry(nameEntry, descriptionEntry, creationFactory, imageEntry);
                }
                if (paletteEntry != null) {
                    paletteEntry.setId(ToolManagement.getId(toolDescription));
                    container.add(paletteEntry);
                }
            }
        }
    }

    private boolean isFromDiagramEnvironment(final ToolEntry toolEntry) {
        boolean result = false;
        Resource eResource = toolEntry.eResource();
        if (eResource != null) {
            URI uri = eResource.getURI();
            if (uri != null) {
                result = uri.toString().equals(SiriusDiagramUtil.DIAGRAM_ENVIRONMENT_RESOURCE_URI);
            }
        }
        return result;
    }

    private boolean requiresPaletteToolEntry(AbstractToolDescription toolDescription) {
        boolean result = false;
        if (toolDescription instanceof NodeCreationDescription) {
            result = true;
        } else if (toolDescription instanceof ContainerCreationDescription) {
            result = true;
        } else if (toolDescription instanceof RequestDescription) {
            result = true;
        } else if (toolDescription instanceof SelectionWizardDescription) {
            result = true;
        } else if (toolDescription instanceof PaneBasedSelectionWizardDescription) {
            result = true;
        } else if (toolDescription instanceof ToolDescription) {
            result = true;
        }
        return result;
    }

    @SuppressWarnings("restriction")
    private PaletteToolEntry createPaletteToolEntry(final String nameEntry, final String descriptionEntry, final CreationFactory creationFactory, final ImageDescriptor imageEntry) {
        PaletteFactory paletteFactory = new CreationToolPaletteFactory(creationFactory);
        final PaletteToolEntry creationToolEntry = new PaletteToolEntry(nameEntry, descriptionEntry, paletteFactory);
        creationToolEntry.setLabel(nameEntry);
        creationToolEntry.setDescription(descriptionEntry);
        creationToolEntry.setSmallIcon(imageEntry);
        creationToolEntry.setLargeIcon(imageEntry);
        creationToolEntry.setToolProperty(CreationTool.PROPERTY_CREATION_FACTORY, creationFactory);
        creationToolEntry.setToolClass(CreationTool.class);
        return creationToolEntry;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.palette.PaletteManager#isDisposed()
     */
    @Override
    public boolean isDisposed() {
        return isDisposed;
    }

    /**
     * 
     * @author ymortier
     * 
     */
    private static class PaletteGroup extends org.eclipse.gef.palette.PaletteGroup {
        /**
         * @param id
         * @param label
         */
        PaletteGroup(final String id, final String label) {
            super(label);
            setId(id);
        }
    }

    /**
     * Specific CreationFactory to handle creation through tools.
     * 
     * @author mporhel
     * 
     */
    private static class PaletteToolBasedCreationFactory implements CreationFactory {
        private AbstractToolDescription toolDescription;

        /**
         * Constructor
         * 
         * @param toolDescription
         *            the tool corresponding to the current entry.
         */
        PaletteToolBasedCreationFactory(AbstractToolDescription toolDescription) {
            this.toolDescription = toolDescription;
        }

        @Override
        public Object getObjectType() {
            return toolDescription.getClass();
        }

        @Override
        public Object getNewObject() {
            return toolDescription;
        }
    }
}
