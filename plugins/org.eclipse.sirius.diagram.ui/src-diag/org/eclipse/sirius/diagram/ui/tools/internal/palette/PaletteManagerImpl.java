/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.palette;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.util.TransactionUtil;
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
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
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
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.palette.PaletteManager;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.palette.ToolFilter;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.description.Environment;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;
import org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription;
import org.eclipse.swt.widgets.Display;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;

/**
 * Default implementation of palette manager.
 * 
 * @author mchauvin
 */
public class PaletteManagerImpl implements PaletteManager {
    private static final String SEVERAL_CANDIDATES_IN_PALETTE_FOUND = "Several {0}s with identical id '{1}' have been found in the palette. Please fix your VSM by whether set a different ID for these Palette entries or ensuring that they cannot be available in the same time.";

    /** The image provider. */
    private static PaletteImageProvider paletteImageProvider = new PaletteImageProvider();

    private EditDomain editDomain;

    private PaletteRoot paletteRoot;

    private Set<ToolFilter> filters = new LinkedHashSet<ToolFilter>();

    private PaletteToolFilterDescriptionListenersManager listenersManager;

    private boolean isDisposed;

    /**
     * Construct a new instance.
     * 
     * @param editDomain
     *            the edit domain
     */
    public PaletteManagerImpl(EditDomain editDomain) {
        this.editDomain = editDomain;
        listenersManager = new PaletteToolFilterDescriptionListenersManager(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.palette.PaletteManager#dispose()
     */
    public void dispose() {
        listenersManager.dispose();
        listenersManager = null;
        filters.clear();
        isDisposed = true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.palette.PaletteManager#addToolFilter(org.eclipse.sirius.diagram.tools.api.graphical.edit.palette.ToolFilter)
     */
    public void addToolFilter(ToolFilter toolFilter) {
        filters.add(toolFilter);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.palette.PaletteManager#removeToolFilter(org.eclipse.sirius.diagram.tools.api.graphical.edit.palette.ToolFilter)
     */
    public void removeToolFilter(ToolFilter toolFilter) {
        filters.remove(toolFilter);
    }

    private boolean isFiltered(final DDiagram diagram, AbstractToolDescription toolDescription) {
        for (final ToolFilter filter : filters) {
            if (filter.filter(diagram, toolDescription)) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.palette.PaletteManager#update()
     */
    public void update(final Diagram diagram) {
        update(diagram, false);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.graphical.edit.palette.PaletteManager#update()
     */
    public void update(final Diagram diagram, final boolean clean) {
        if (diagram != null) {
            initPaletteRoot();
            if (Display.getCurrent() != null) {
                updatePalette(diagram, clean);
            } else {
                // If test is not launched in UI Thread make the changes in UI
                // thread
                Display.getDefault().asyncExec(new Runnable() {
                    public void run() {
                        updatePalette(diagram, clean);
                    }
                });
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see fr.obeo.dsl.viewpoint.diagram.tools.api.graphical.edit.palette.PaletteManager#update()
     */
    private void updatePalette(final Diagram diagram, boolean clean) {
        initPaletteRoot();
        if (paletteRoot != null) {
            clearFilters(diagram);
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
         * Update the palette (according to filters, that are also updated
         * during this method)
         */
        updatePalette(diagram);
        paletteRoot = null;
    }

    /**
     * Set the palette root field with the palette root referenced in the
     * palette viewer and if there is no palette viewer, use reflection to
     * directly get the palette root of the edit domain (protected field).
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
    private void updatePalette(final Diagram diagram) {
        final EObject element = diagram.getElement();
        if (element instanceof DDiagram) {
            final DDiagram dDiagram = (DDiagram) element;
            final DiagramDescription description = dDiagram.getDescription();
            Session session = null;
            if (dDiagram instanceof DSemanticDiagram) {
                session = SessionManager.INSTANCE.getSession(((DSemanticDiagram) dDiagram).getTarget());
            }
            // Step 1: we add the default tools contributed by the environment
            // in the same group as the default GEF tools
            addDefaultTools(diagram);
            // Step 2: Replace the ConnectionCreationTool for
            // DiagramPaletteFactory.TOOL_NOTEATTACHMENT (if needed)
            replaceNoteAttachmentCreationToolIfNeeded();
            if (session != null && description != null && description.eResource() != null && !description.eIsProxy()) {
                updatePalette(description, session, dDiagram);
            }
        }
    }

    /**
     * Update the palette.
     * 
     * @param dDiagram
     *            The {@link DDiagram} representing by the editor which contains
     *            the palette to update.
     * @param description
     *            The {@link DiagramDescription} of the {@link DDiagram}. It
     *            should not be a proxy.
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
     *            The {@link DDiagram} representing by the editor which contains
     *            the palette to update.
     * @param description
     *            The {@link DiagramDescription} of the {@link DDiagram}. It
     *            should not be a proxy.
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
        // Update the filters
        updateFilters(session, description.getAllTools());
        // Update the root of the palette
        updateContainer(session, dDiagram, descGroup.get(), description.getAllTools());
    }

    /**
     * Update the palette for a diagram that has layer(s), at least a default
     * one.
     * 
     * @param dDiagram
     *            The {@link DDiagram} representing by the editor which contains
     *            the palette to update.
     * @param description
     *            The {@link DiagramDescription} of the {@link DDiagram}. It
     *            should not be a proxy.
     * @param session
     *            The {@session} containing the {@link DDiagram}.
     */
    private void updatePaletteForDiagramWithLayer(DiagramDescription description, Session session, DDiagram dDiagram) {
        // Copy of all layers of selected viewpoints
        HashSet<Layer> layersInActivatedViewpoints = new HashSet<Layer>(new DiagramComponentizationManager().getAllLayers(session.getSelectedViewpoints(false), description));
        // Copy of diagram activated layers (in all Viewpoints: activated or
        // not)
        HashSet<Layer> activatedLayers = new HashSet<Layer>(dDiagram.getActivatedLayers());
        // Get the list of activated layers (of selected viewpoints)
        final List<Layer> activatedLayersOfSelectedViewpoints = Lists.newArrayList(Sets.intersection(layersInActivatedViewpoints, activatedLayers));
        // Get the list of deactivated layers (deactivated layers of selected
        // viewpoints and all layers of deselected viewpoints)
        final List<Layer> deactivatedLayersAndAllLayersOfDeselectedViewpoints = Lists.newArrayList(Sets.symmetricDifference(layersInActivatedViewpoints, activatedLayers));
        // Update the filters
        for (final ToolSection section : new DiagramComponentizationManager().getRootPaletteSections(session.getSelectedViewpoints(false), description)) {
            updateFilters(session, new DiagramComponentizationManager().getAllToolEntries(session.getSelectedViewpoints(false), section));
        }
        for (final ToolSection section : new DiagramComponentizationManager().getRootPaletteSections(session.getSelectedViewpoints(false), description)) {
            Option<SectionPaletteDrawer> paletteEntry = getPaletteEntry(paletteRoot, new IdentifiedElementQuery(section).getLabel(), SectionPaletteDrawer.class);
            if (!paletteEntry.some()) {
                final PaletteContainer container = PaletteManagerImpl.createPaletteDrawner(section);
                updateContainer(session, dDiagram, container, new DiagramComponentizationManager().getAllToolEntries(session.getSelectedViewpoints(false), section));
                paletteRoot.add(container);
            } else {
                updateContainer(session, dDiagram, paletteEntry.get(), new DiagramComponentizationManager().getAllToolEntries(session.getSelectedViewpoints(false), section));
            }
        }
        for (final Layer layer : Lists.newArrayList(deactivatedLayersAndAllLayersOfDeselectedViewpoints)) {
            setLayerVisibility(layer, false);
        }
        for (final Layer layer : Lists.newArrayList(activatedLayersOfSelectedViewpoints)) {
            setLayerVisibility(layer, true);
        }
    }

    /**
     * Returns the palette entry contained in the given {@link PaletteContainer}
     * with the given id, of the given type. If none found,
     * {@link Options#newNone()} will be returned. If several found, we will log
     * a warning and return only one of the candidates.
     * 
     * @param <T>
     *            the type of the searched palette entry
     * @param container
     *            the container in which search for this palette entry
     * @param id
     *            the searched id
     * @param type
     *            the expected type
     * @return {@link Options#newNone()} if no matching candidate is found, or
     *         the found candidate (if several found, we will log a warning and
     *         return only one of the candidates).
     */
    private <T extends PaletteEntry> Option<T> getPaletteEntry(PaletteContainer container, final String id, Class<T> type) {
        Option<T> matchingPaletteEntry = Options.newNone();
        UnmodifiableIterator<T> matchingPaletteEntries = Iterators.filter(Iterators.filter(container.getChildren().iterator(), type), new Predicate<T>() {
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
            DiagramPlugin.getDefault().logWarning(MessageFormat.format(SEVERAL_CANDIDATES_IN_PALETTE_FOUND, type.getName(), id));
            // Here no matching candidate has been found, we will return
            // Options.newNone
        }
        return matchingPaletteEntry;
    }

    /**
     * Replace if needed the GMF note attachment tool by a specific one (2
     * clicks for link creation instead of one click with drag).
     */
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
     * Search a palette container by its label in the children of the
     * <code>container</code>.
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
    public void hideLayer(final Layer layer) {
        // final PaletteViewer viewer = editDomain.getPaletteViewer();
        // if (viewer != null) {
        // paletteRoot = viewer.getPaletteRoot();
        initPaletteRoot();
        if (paletteRoot != null) {
            setLayerVisibility(layer, false);
            paletteRoot = null;
        }
    }

    /**
     * Show all tools provided by a layer.
     * 
     * @param layer
     *            the layer
     */
    public void showLayer(final Layer layer) {
        final PaletteViewer viewer = editDomain.getPaletteViewer();
        if (viewer != null) {
            paletteRoot = viewer.getPaletteRoot();
            setLayerVisibility(layer, true);
            paletteRoot = null;
        }
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

    @SuppressWarnings("unchecked")
    private static void setToolsVisibility(final PaletteDrawer drawner, final Layer layer, final ToolSection section, final boolean visibility) {
        for (final PaletteEntry entry : (List<PaletteEntry>) drawner.getChildren()) {
            if (entry instanceof ToolGroupPaletteStack) {
                final ToolGroupPaletteStack stack = (ToolGroupPaletteStack) entry;
                PaletteManagerImpl.setPaletteStackVisibility(stack, layer, section.getOwnedTools(), visibility);
                PaletteManagerImpl.setPaletteStackVisibility(stack, layer, section.getReusedTools(), visibility);
                for (final ToolGroupExtension groupExtension : section.getGroupExtensions()) {
                    if (stack.getId().equals(PaletteManagerImpl.getToolEntryId(groupExtension.getGroup()))) {
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
                if (stack.getId().equals(PaletteManagerImpl.getToolEntryId(toolEntry))) {
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
            if (entry.getId().equals(PaletteManagerImpl.getToolEntryId(toolEntry))) {
                entry.setVisible(visibility);
            }
        }
    }

    private static String getToolSectionId(final ToolSection toolSection) {
        return new IdentifiedElementQuery(toolSection).getLabel();
    }

    private static String getToolEntryId(final ToolEntry entry) {
        return EcoreUtil.getURI(entry).toString();
    }

    /**
     * Adds the default tools contributed by the environment in the same group
     * as the default GEF tools.
     */
    private void addDefaultTools(final Diagram diagram) {
        final PaletteContainer container = paletteRoot.getDefaultEntry().getParent();
        for (Object entry : container.getChildren()) {
            if (entry instanceof PaletteSeparator && "defaultTools".equals(((PaletteSeparator) entry).getId())) { //$NON-NLS-1$
                // Default tools are already there. Nothing to do.
                return;
            }
        }
        final PaletteSeparator marker = new PaletteSeparator("defaultTools"); //$NON-NLS-1$
        marker.setVisible(false);
        container.add(marker);
        for (final ToolEntry defaultEntry : PaletteManagerImpl.getDefaultTools(TransactionUtil.getEditingDomain(diagram).getResourceSet())) {
            addElementToContainer(container, defaultEntry);
        }
    }

    private static List<ToolEntry> getDefaultTools(final ResourceSet context) {
        final Resource coreEnvResource = context.getResource(URI.createURI(SiriusUtil.VIEWPOINT_ENVIRONMENT_RESOURCE_URI, true), true);
        final Environment coreEnv = (Environment) coreEnvResource.getContents().get(0);

        final Resource diagramEnvResource = context.getResource(URI.createURI(SiriusDiagramUtil.DIAGRAM_ENVIRONMENT_RESOURCE_URI, true), true);
        final Environment diagramEnv = (Environment) diagramEnvResource.getContents().get(0);

        List<ToolEntry> defaultTools = Lists.newArrayList();
        defaultTools.addAll(coreEnv.getDefaultTools());
        defaultTools.addAll(diagramEnv.getDefaultTools());
        return defaultTools;
    }

    private static PaletteContainer createPaletteDrawner(final ToolSection section) {
        final String name = new IdentifiedElementQuery(section).getLabel();
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

    private void clearFilters(Diagram diagram) {
        listenersManager.init(diagram);
        Collection<ToolFilter> filtersCopy = Lists.newArrayList(filters);
        for (final ToolFilter filter : filtersCopy) {
            if (filter instanceof ToolFilterFromDescription) {
                removeToolFilter(filter);
            }
        }
    }

    /**
     * Update tool filters. If the session is null, nothing will be done.
     * 
     * @param session
     *            the session
     * @param toolEntries
     *            the list of entry of tools to add.
     */
    protected void updateFilters(final Session session, final List<? extends ToolEntry> toolEntries) {
        if (session != null) {
            final IInterpreter interpreter = session.getInterpreter();
            if (interpreter != null) {
                for (final ToolEntry toolEntry : toolEntries) {
                    if (toolEntry instanceof AbstractToolDescription) {
                        /* create filters from description */
                        for (final ToolFilterDescription filterDescription : ((AbstractToolDescription) toolEntry).getFilters()) {
                            ToolFilter filter = new ToolFilterFromDescription(interpreter, filterDescription);
                            addToolFilter(filter);
                        }
                        /**/
                        listenersManager.addListenersForFilters(interpreter, ((AbstractToolDescription) toolEntry).getFilters());
                    } else if (toolEntry instanceof ToolGroup) {
                        updateFilters(session, new DiagramComponentizationManager().getTools(session.getSelectedViewpoints(false), (ToolGroup) toolEntry));
                    }
                }
            }
        }
    }

    /**
     * Update the container with the list of tool. The tools are created only if
     * needed (not already existing) and not filtered. If a tool exists and
     * should be filtered, it is removed from the container.
     * 
     * @param session
     *            the current session
     * @param diagram
     *            the diagram currently represented
     * @param container
     *            the container to fill
     * @param toolEntries
     *            the list of entry of tools to add.
     */
    protected void updateContainer(final Session session, final DDiagram diagram, final PaletteContainer container, final List<? extends ToolEntry> toolEntries) {
        for (final ToolEntry toolEntry : toolEntries) {
            if (toolEntry instanceof AbstractToolDescription) {
                final AbstractToolDescription toolDescription = (AbstractToolDescription) toolEntry;
                /*
                 * do not create a new entry for the tool if it should not be
                 * displayed
                 */
                Option<PaletteEntry> paletteEntry = getPaletteEntry(container, new IdentifiedElementQuery(toolEntry).getLabel(), PaletteEntry.class);
                if (!paletteEntry.some()) {
                    paletteEntry = getPaletteEntry(container, PaletteManagerImpl.getToolEntryId(toolEntry), PaletteEntry.class);
                }
                if (!isFiltered(diagram, toolDescription)) {
                    addElementToContainer(container, toolEntry, paletteEntry);
                } else {
                    container.remove(paletteEntry.get());
                }
            } else if (toolEntry instanceof ToolGroup) {
                Option<ToolGroupPaletteStack> paletteStack = getPaletteEntry(container, PaletteManagerImpl.getToolEntryId(toolEntry), ToolGroupPaletteStack.class);
                boolean paletteWasCreated = false;
                if (!paletteStack.some()) {
                    paletteStack = Options.newSome(new ToolGroupPaletteStack(((ToolGroup) toolEntry).getName()));
                    paletteWasCreated = true;
                }
                for (final AbstractToolDescription tool : new DiagramComponentizationManager().getTools(session.getSelectedViewpoints(false), (ToolGroup) toolEntry)) {
                    /*
                     * do not create a new entry for the tool if it should not
                     * be displayed
                     */
                    Option<PaletteEntry> paletteEntry = getPaletteEntry(paletteStack.get(), getToolEntryId(tool), PaletteEntry.class);
                    if (!isFiltered(diagram, tool)) {
                        addElementToContainer(paletteStack.get(), tool, paletteEntry);
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
                    paletteStack.get().setId(PaletteManagerImpl.getToolEntryId(toolEntry));
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
     *            the palette entry currently existing with the id of toolEntry,
     *            or {@link Options#newNone()} if it does not currently exists
     */
    protected void addElementToContainer(final PaletteContainer container, final ToolEntry toolEntry, final Option<PaletteEntry> existingPaletteEntry) {
        if (toolEntry instanceof ToolGroup) {
            PaletteStack paletteStack;
            String newName = new IdentifiedElementQuery(toolEntry).getLabel();
            if (!existingPaletteEntry.some()) {
                paletteStack = new ToolGroupPaletteStack(newName);
                paletteStack.setId(PaletteManagerImpl.getToolEntryId(toolEntry));
                container.add(paletteStack);
            } else if (existingPaletteEntry.get() instanceof PaletteStack) {
                paletteStack = (PaletteStack) existingPaletteEntry.get();
            } else {
                throw new IllegalArgumentException("An existing palette entry with name " + newName + " already exists but it is another kind of entry.");
            }
            for (final AbstractToolDescription tool : ((ToolGroup) toolEntry).getTools()) {
                Option<PaletteEntry> paletteEntry = getPaletteEntry(paletteStack, new IdentifiedElementQuery(tool).getLabel(), PaletteEntry.class);
                addElementToContainer(paletteStack, tool, paletteEntry);
            }
        } else if (toolEntry instanceof AbstractToolDescription) {
            if (!existingPaletteEntry.some()) {
                final AbstractToolDescription toolDescription = (AbstractToolDescription) toolEntry;
                final ImageDescriptor imageEntry = paletteImageProvider.getImageDescriptor(toolDescription);
                final String nameEntry = new IdentifiedElementQuery(toolDescription).getLabel();
                final String descriptionEntry = toolDescription.getDocumentation();
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
                    paletteEntry.setId(PaletteManagerImpl.getToolEntryId(toolDescription));
                    container.add(paletteEntry);
                }
            }
        }
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
        public PaletteGroup(final String id, final String label) {
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
        public PaletteToolBasedCreationFactory(AbstractToolDescription toolDescription) {
            this.toolDescription = toolDescription;
        }

        public Object getObjectType() {
            return toolDescription.getClass();
        }

        public Object getNewObject() {
            return toolDescription;
        }
    }
}
