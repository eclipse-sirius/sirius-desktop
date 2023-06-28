/*******************************************************************************
 * Copyright (c) 2011, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.refresh.diagram;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.gmf.runtime.common.ui.services.editor.EditorService;
import org.eclipse.gmf.runtime.common.ui.util.DisplayUtils;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.WorkspaceViewerProperties;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.util.MeasurementUnitHelper;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.business.api.diagramtype.ICollapseUpdater;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.api.query.DiagramDescriptionQuery;
import org.eclipse.sirius.diagram.ui.business.api.helper.graphicalfilters.CollapseUpdater;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.business.internal.dialect.SetBestHeightHeaderCommand;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.operation.RegionContainerUpdateLayoutOperation;
import org.eclipse.sirius.diagram.ui.internal.refresh.AbstractCanonicalSynchronizer;
import org.eclipse.sirius.diagram.ui.internal.refresh.CanonicalSynchronizerResult;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramUpdater;
import org.eclipse.sirius.diagram.ui.part.SiriusLinkDescriptor;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Synchronizer allowing to synchronize a DSemanticDiagram with its corresponding GMFDiagram.
 *
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 *
 * @since 0.9.0
 */
@SuppressWarnings("restriction")
public class DDiagramCanonicalSynchronizer extends AbstractCanonicalSynchronizer {

    /**
     * The {@link Diagram} corresponding to the DSemanticDiagram to synchronize.
     */
    private Diagram gmfDiagram;

    /**
     * Specific factory for connections.
     */
    private ConnectionsFactory connectionsFactory;

    /**
     * Default constructor.
     *
     * @param gmfDiagram
     *            the {@link Diagram} to update according to "semantic" DSemanticDiagram.
     */
    public DDiagramCanonicalSynchronizer(Diagram gmfDiagram) {
        super();
        this.gmfDiagram = gmfDiagram;
        this.connectionsFactory = new ConnectionsFactory(gmfDiagram, viewpointViewProvider);
        // Search if it possible to get snapToGrid and gridSpacing values from the store associated to an opened editor.
        loadPreferencesFromOpenedDiagram();
    }

    /**
     * Load the preferences from an opened diagram on the same GMF {@link Diagram}.<BR>
     * Inspired from
     * {@link org.eclipse.sirius.diagram.ui.tools.internal.print.SiriusDiagramPrintPreviewHelper#loadPreferencesFromOpenDiagram(String)}.
     */
    private void loadPreferencesFromOpenedDiagram() {
        List<? extends Object> diagramEditors = EditorService.getInstance().getRegisteredEditorParts();
        @SuppressWarnings("unchecked")
        Optional<DiagramEditor> diagramEditor = (Optional<DiagramEditor>) diagramEditors.stream()
                .filter(de -> de instanceof DiagramEditor && ((DiagramEditor) de).getDiagramEditPart() != null && gmfDiagram.equals(((DiagramEditor) de).getDiagramEditPart().getDiagramView()))
                .findFirst();
        if (diagramEditor.isPresent()) {
            IDiagramGraphicalViewer viewer = diagramEditor.get().getDiagramGraphicalViewer();
            if (viewer instanceof DiagramGraphicalViewer) {
                DiagramGraphicalViewer diagramGraphicalViewer = (DiagramGraphicalViewer) viewer;
                // Load preferences
                IPreferenceStore store = diagramGraphicalViewer.getWorkspaceViewerPreferenceStore();
                setSnapToGrid(store.getBoolean(WorkspaceViewerProperties.SNAPTOGRID));
                setGridSpacing(convertGridSpacingInPixels(store.getDouble(WorkspaceViewerProperties.GRIDSPACING), store.getInt(WorkspaceViewerProperties.RULERUNIT),
                        MeasurementUnitHelper.getMapMode(gmfDiagram.getMeasurementUnit())));
            }
        }
    }

    /**
     * This conversion method is inspired from
     * {@link org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart#setGridSpacing(double)}.
     * 
     * @param gridSpacing
     *            The current grid spacing respecting the ruler units.
     * @param rulerUnits
     *            The ruler units.
     * @return The grid spacing in pixels.
     */
    private int convertGridSpacingInPixels(double gridSpacing, int rulerUnits, IMapMode mapMode) {
        // Get the Displays DPIs
        final Display display = DisplayUtils.getDisplay();
        final RunnableWithResult<Point> runnable = new RunnableWithResult.Impl<Point>() {
            @Override
            public void run() {
                setResult(display.getDPI());
            }
        };
        display.syncExec(runnable);
        double dotsPerInch = runnable.getResult().x;
        int spacingInPixels = 0;

        // Evaluate the Grid Spacing based on the ruler units
        switch (rulerUnits) {
        case RulerProvider.UNIT_INCHES:
            spacingInPixels = (int) Math.round(dotsPerInch * gridSpacing);
            break;

        case RulerProvider.UNIT_CENTIMETERS:
            spacingInPixels = (int) Math.round(dotsPerInch * gridSpacing / 2.54);
            break;

        default:
            spacingInPixels = (int) gridSpacing;
        }

        int spacing = mapMode.DPtoLP(spacingInPixels);
        return spacing;
    }

    /**
     *
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.diagram.business.api.refresh.view.refresh.CanonicalSynchronizer#synchronize()
     */
    @Override
    public void synchronize() {
        refreshSemantic();
    }

    private void refreshSemantic() {
        if (gmfDiagram != null && gmfDiagram.getElement() != null) {
            CanonicalSynchronizerResult canonicalSynchronizerResult = new CanonicalSynchronizerResult();

            // 1. Refresh nodes
            refreshSemanticChildren(gmfDiagram, gmfDiagram.getElement(), canonicalSynchronizerResult);
            List<View> children = gmfDiagram.getChildren();
            for (final View view : children) {
                refreshSemantic(view, canonicalSynchronizerResult);
            }
            // reconciliation delete/create, compute deletion dependencies and confirm deletion
            canonicalSynchronizerResult.reconciliateOrphanNodes();
            canonicalSynchronizerResult.collectDetachedPGEFromNode();
            canonicalSynchronizerResult.collectAttachedEdgeToNodes();
            canonicalSynchronizerResult.deleteOrphanNodes();

            // 2. Refresh edges
            refreshConnections(gmfDiagram, canonicalSynchronizerResult);
            // reconciliation delete/create, compute deletion dependencies and confirm deletion
            canonicalSynchronizerResult.reconciliateOrphanEdges();
            canonicalSynchronizerResult.collectAttachedEdgeToEdges();
            canonicalSynchronizerResult.collectDetachedPGEFromEdge();
            canonicalSynchronizerResult.deleteOrphanEdges();

            // 3. Delete collected note attached to removed element
            canonicalSynchronizerResult.deleteDetachedPGE();

            // 4. Manage views
            manageCreatedViewsLayout(canonicalSynchronizerResult.getCreatedViews());
            manageCollapse(canonicalSynchronizerResult.getCreatedNodes());
            manageRegions();
        }
    }

    private void manageRegions() {
        if (regionsContainersToLayoutWithImpactStatus.isEmpty()) {
            return;
        }

        // Step 1: update regions layout from the deepest ones.
        List<View> newArrayList = new ArrayList<View>(regionsContainersToLayoutWithImpactStatus.keySet());
        ListIterator<View> regionsContainersToLayoutListIterator = newArrayList.listIterator(newArrayList.size());
        while (regionsContainersToLayoutListIterator.hasPrevious()) {
            View regionsContainer = regionsContainersToLayoutListIterator.previous();
            if (regionsContainer instanceof Node) {
                new RegionContainerUpdateLayoutOperation((Node) regionsContainer, regionsContainersToLayoutWithImpactStatus.get(regionsContainer)).execute();
            }
        }
        regionsContainersToLayoutWithImpactStatus.clear();
    }

    private void manageCreatedViewsLayout(Set<View> createdViews) {
        // get view to layout "normally"
        Set<View> filteredCreatedViewsToLayout = Sets.filter(createdViews, new Predicate<View>() {
            @Override
            public boolean apply(View input) {
                return input.eAdapters().contains(SiriusLayoutDataManager.INSTANCE.getAdapterMarker());
            }
        });

        // get view to center layout
        Set<View> filteredCreatedViewsWithCenterLayout = Sets.filter(createdViews, new Predicate<View>() {
            @Override
            public boolean apply(View input) {
                return input.eAdapters().contains(SiriusLayoutDataManager.INSTANCE.getCenterAdapterMarker());
            }
        });

        Set<View> createdViewsWithCenterLayout = Sets.newLinkedHashSet(filteredCreatedViewsWithCenterLayout);
        Set<View> createdViewsToLayout = Sets.newLinkedHashSet(filteredCreatedViewsToLayout);

        // center layout must be only done on the super containers :
        // filter the createdViewsWithCenterLayout : only the container(s)
        // of all the new created views must have a center layout.
        // the filtered views must have a "normal" layout.
        calculateCenterLayout(createdViewsWithCenterLayout, createdViewsToLayout);

        if (!createdViewsWithCenterLayout.isEmpty() && storeViews2Arrange) {
            SiriusLayoutDataManager.INSTANCE.addCreatedViewWithCenterLayout(gmfDiagram, Sets.newLinkedHashSet(createdViewsWithCenterLayout));
            removeAlreadyArrangeMarker(filteredCreatedViewsWithCenterLayout);
        }

        if (!createdViewsToLayout.isEmpty() && storeViews2Arrange) {
            SiriusLayoutDataManager.INSTANCE.addCreatedViewsToLayout(gmfDiagram, Sets.newLinkedHashSet(createdViewsToLayout));
            removeAlreadyArrangeMarker(filteredCreatedViewsToLayout);
        }
    }

    /**
     * center layout must be only done on the super containers : filter the createdViewsWithCenterLayout : only the
     * container(s) of all the new created views must have a center layout. the filtered views must have a "normal"
     * layout.
     *
     * @param createdViewsWithSpecialLayout
     * @param createdViewsToLayout
     */
    private void calculateCenterLayout(Set<View> createdViewsWithSpecialLayout, Set<View> createdViewsToLayout) {
        Set<View> toRemove = new HashSet<View>();
        for (View view : createdViewsWithSpecialLayout) {
            if (hasContainer(view, createdViewsWithSpecialLayout)) {
                toRemove.add(view);
            }
        }
        createdViewsToLayout.addAll(toRemove);
        createdViewsWithSpecialLayout.removeAll(toRemove);
    }

    private boolean hasContainer(View view, Set<View> createdViewsToLayout) {
        for (View aView : createdViewsToLayout) {
            if (!view.equals(aView) && hasContainer(view, aView)) {
                return true;
            }
        }
        return false;
    }

    /**
     * AnnotationEntries contains the GMF diagrams.
     *
     * @param view
     * @param aView
     * @return if view is contained by aView
     */
    private boolean hasContainer(View view, View aView) {
        EObject eContainer = view;
        while (eContainer != null && !(eContainer instanceof AnnotationEntry) && !eContainer.equals(aView)) {
            eContainer = eContainer.eContainer();
        }
        return !(eContainer instanceof AnnotationEntry);
    }

    /**
     * Remove the adapter marker that reveal an already arranged view.
     *
     * @param view
     *            The view to check
     */
    private void removeAlreadyArrangeMarker(Collection<View> createdViews) {
        for (final View view : createdViews) {
            for (Iterator<Adapter> iterator = view.eAdapters().iterator(); iterator.hasNext(); /**/) {
                Adapter adapter = iterator.next();
                if (adapter.isAdapterForType(SiriusLayoutDataManager.INSTANCE)) {
                    iterator.remove();
                    break;
                }
            }
        }
    }

    /**
     * Refresh diagram edges from Sirius to GMF
     * 
     * <ul>
     * <li>Compute differences between Sirius and GMF model</li>
     * <li>Create all missing GMF edges and mark it in `canonicalSynchronizerResult`</li>
     * <li>Mark as orphan (i.e to remove) in `canonicalSynchronizerResult` all GMF edges not present on Sirius side</li>
     * </ul>
     */
    private CanonicalSynchronizerResult refreshConnections(final Diagram diagram, CanonicalSynchronizerResult canonicalSynchronizerResult) {
        // Algorithm description:
        // Take descriptor of all Sirius edges and remove all those that exist in GMF:
        // // -> so, it remains all edges which are to be created (GMF edges to create)
        // Take all edges (except noteAttachments and lines) and remove those that have associated Sirius element:
        // // -> so, it remains all orphan edges (GMF edges to remove)

        final Map<EObject, View> domain2NotationMap = new HashMap<EObject, View>();
        final Collection<SiriusLinkDescriptor> linkDescriptors = collectAllLinks(diagram, domain2NotationMap);
        final List<Edge> existingLinks = new LinkedList<Edge>(diagram.getEdges());

        // Remove NoteAttachments, Lines and already marked edges to remove
        existingLinks.removeAll(collectAllNoteAttachments(diagram));
        existingLinks.removeAll(collectAllLines(diagram));
        existingLinks.removeAll(canonicalSynchronizerResult.getOrphanEdges());

        // for each edge except noteAttachments and lines
        final Iterator<Edge> linksIterator = existingLinks.iterator();
        while (linksIterator.hasNext()) {
            final Edge nextDiagramLink = linksIterator.next();
            final EObject diagramLinkObject = nextDiagramLink.getElement();
            // eContainer can be quite expensive when deep in the model
            final EObject nextDiagramLinkEContainer = nextDiagramLink.eContainer();
            if (diagramLinkObject != null) {
                final EObject diagramLinkEContainer = diagramLinkObject.eContainer();
                if (nextDiagramLink.getSource() != null && nextDiagramLink.getTarget() != null && nextDiagramLink.getSource().eContainer() != null
                        && nextDiagramLink.getTarget().eContainer() != null) {
                    final EObject diagramLinkSrc = nextDiagramLink.getSource().getElement();
                    final EObject diagramLinkDst = nextDiagramLink.getTarget().getElement();
                    final int diagramLinkVisualID = SiriusVisualIDRegistry.getVisualID(nextDiagramLink);
                    final Iterator<SiriusLinkDescriptor> linkDescriptorsIterator = linkDescriptors.iterator();
                    while (linkDescriptorsIterator.hasNext()) {
                        final SiriusLinkDescriptor nextLinkDescriptor = linkDescriptorsIterator.next();
                        boolean exist = nextDiagramLinkEContainer != null && diagramLinkEContainer != null && diagramLinkObject == nextLinkDescriptor.getModelElement();
                        exist = exist && diagramLinkSrc == nextLinkDescriptor.getSource() && diagramLinkDst == nextLinkDescriptor.getDestination()
                                && diagramLinkVisualID == nextLinkDescriptor.getVisualID();
                        if (exist && diagramLinkSrc instanceof DEdge) {
                            exist = linkDescriptors.stream().noneMatch(input -> {
                                return input.getModelElement().equals(diagramLinkSrc);
                            });
                        }
                        if (exist && diagramLinkDst instanceof DEdge) {
                            exist = linkDescriptors.stream().noneMatch(input -> {
                                return input.getModelElement().equals(diagramLinkDst);
                            });
                        }
                        if (exist) {
                            linksIterator.remove();
                            linkDescriptorsIterator.remove();
                        }
                    }
                }
            }
        }

        canonicalSynchronizerResult.addOrphanEdges(existingLinks);
        canonicalSynchronizerResult.addCreatedEdges(createConnections(linkDescriptors, domain2NotationMap, diagram));
        return canonicalSynchronizerResult;
    }

    private List<Edge> createConnections(final Collection<SiriusLinkDescriptor> linkDescriptors, final Map<EObject, View> domain2NotationMap, final Diagram diagram) {

        List<Edge> createdEdges = new ArrayList<Edge>();

        for (SiriusLinkDescriptor viewDescriptor : linkDescriptors) {
            Edge createdEdge = connectionsFactory.createEdge(viewDescriptor, domain2NotationMap);
            if (createdEdge != null) {
                createdEdges.add(createdEdge);
            }
        }
        return createdEdges;
    }

    /**
     * Collect all links in Sirius model and return description of these links and create map between existing Sirius
     * elements and GMF View
     * 
     * @param view
     *            The view associated to DDiagram Sirius
     * @param domain2NotationMap
     *            The map between Sirius elements and GMF views
     * @return List of Sirius links summary (some information about source, target, type...)
     */
    private Collection<SiriusLinkDescriptor> collectAllLinks(final View view, final Map<EObject, View> domain2NotationMap) {
        if (!DDiagramEditPart.MODEL_ID.equals(SiriusVisualIDRegistry.getModelID(view))) {
            return Collections.<SiriusLinkDescriptor> emptyList();
        }
        /*
         * Let's start with the diagram.
         */
        final Collection<SiriusLinkDescriptor> result = new LinkedList<SiriusLinkDescriptor>();
        result.addAll(SiriusDiagramUpdater.getDDiagram_1000ContainedLinks(view));

        @SuppressWarnings("serial")
        TreeIterator<View> it = new AbstractTreeIterator<View>(view) {

            @Override
            protected Iterator<? extends View> getChildren(Object object) {
                if (object instanceof View) {
                    View view = (View) object;
                    List<? extends View> children = view.getChildren();
                    return children.iterator();
                }
                return Collections.EMPTY_LIST.iterator();
            }
        };
        while (it.hasNext()) {
            View child = it.next();
            EObject element = child.getElement();
            if (!domain2NotationMap.containsKey(element)) {
                domain2NotationMap.put(element, child);
            }
        }
        List<Edge> edges = ((Diagram) view).getEdges();
        for (Edge edge : edges) {
            EObject element = edge.getElement();
            if (!domain2NotationMap.containsKey(element)) {
                domain2NotationMap.put(element, edge);
            }
        }
        return result;
    }

    /**
     * Collect all Note Attachments.
     *
     * @param diagram
     *            the diagram.
     * @return all note attachments.
     */
    private Collection<Edge> collectAllNoteAttachments(final Diagram diagram) {
        final Collection<Edge> result = new LinkedList<Edge>();
        final Iterator<Edge> edges = diagram.getEdges().iterator();
        while (edges.hasNext()) {
            final Edge currentEdge = edges.next();
            if (ViewType.NOTEATTACHMENT.equals(currentEdge.getType())) {
                result.add(currentEdge);
            }
        }
        return result;
    }

    /**
     * Collect all Line (GMF figure).
     *
     * @param diagram
     *            the diagram.
     * @return all lines.
     */
    private Collection<Edge> collectAllLines(final Diagram diagram) {
        final Collection<Edge> result = new LinkedList<Edge>();
        final Iterator<Edge> edges = diagram.getEdges().iterator();
        while (edges.hasNext()) {
            final Edge currentEdge = edges.next();
            if ("line".equals(currentEdge.getType())) { //$NON-NLS-1$
                result.add(currentEdge);
            }
        }
        return result;
    }

    private void manageCollapse(Set<View> createdNodeViews) {
        if (createdNodeViews.isEmpty() && !(gmfDiagram.getElement() instanceof DDiagram)) {
            return;
        }
        DDiagram dDiagram = (DDiagram) gmfDiagram.getElement();
        ICollapseUpdater cu = CollapseUpdater.getICollapseUpdater(dDiagram);
        for (Node node : Iterables.filter(createdNodeViews, Node.class)) {
            EObject element = node.getElement();
            if (element instanceof DDiagramElement && cu instanceof CollapseUpdater) {
                DDiagramElement dde = (DDiagramElement) element;
                if (new DDiagramElementQuery(dde).isIndirectlyCollapsed()) {
                    CollapseFilter filter = (CollapseFilter) Iterables.getFirst(Iterables.filter(dde.getGraphicalFilters(), Predicates.instanceOf(CollapseFilter.class)), null);

                    if (filter != null && filter.getWidth() == 0 && filter.getHeight() == 0) {
                        ((CollapseUpdater) cu).storeInFilterAndCollapseBounds(dde, Options.newSome(node), false);
                    }
                }
            }
        }

    }

    @Override
    public void postCreation() {
        if (gmfDiagram != null && gmfDiagram.getElement() instanceof DDiagram && ((DDiagram) gmfDiagram.getElement()).getDescription() != null) {
            if (new DiagramDescriptionQuery(((DDiagram) gmfDiagram.getElement()).getDescription()).isHeaderSectionEnabled()) {
                TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(gmfDiagram.getElement());
                if (domain != null) {
                    Command setBestHeightHeaderCommand = new SetBestHeightHeaderCommand(domain, gmfDiagram);
                    setBestHeightHeaderCommand.execute();
                }
            }
        }

    }

}
