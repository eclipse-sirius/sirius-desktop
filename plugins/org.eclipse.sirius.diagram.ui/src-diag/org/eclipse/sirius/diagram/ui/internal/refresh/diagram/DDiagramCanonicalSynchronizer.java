/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
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
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramUpdater;
import org.eclipse.sirius.diagram.ui.part.SiriusLinkDescriptor;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Synchronizer allowing to synchronize a DSemanticDiagram with its
 * corresponding GMFDiagram.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 * @since 0.9.0
 */
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
     *            the {@link Diagram} to update according to "semantic"
     *            DSemanticDiagram.
     */
    public DDiagramCanonicalSynchronizer(Diagram gmfDiagram) {
        super();
        this.gmfDiagram = gmfDiagram;
        this.connectionsFactory = new ConnectionsFactory(gmfDiagram, viewpointViewProvider);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.refresh.view.refresh.CanonicalSynchronizer#synchronize()
     */
    public void synchronize() {
        refreshSemantic();
    }

    private void refreshSemantic() {
        if (gmfDiagram != null && gmfDiagram.getElement() != null) {
            final Set<View> createdNodeViews = Sets.newLinkedHashSet();
            createdNodeViews.addAll(refreshSemanticChildren(gmfDiagram, gmfDiagram.getElement()));
            for (final Object object : gmfDiagram.getChildren()) {
                createdNodeViews.addAll(refreshSemantic((View) object));
            }

            final Set<Edge> createdConnectionViews = Sets.newLinkedHashSet();
            createdConnectionViews.addAll(refreshConnections(gmfDiagram));

            Set<View> createdViews = Sets.union(createdNodeViews, createdConnectionViews);

            manageCreatedViewsLayout(createdViews);

            manageCollapse(createdNodeViews);

            manageRegions();
        }
    }

    private void manageRegions() {
        for (EObject regionContainer : regionsToLayout) {
            if (regionContainer instanceof Node) {
                new RegionContainerUpdateLayoutOperation((Node) regionContainer).execute();
            }
        }

        regionsToLayout.clear();
    }

    private void manageCreatedViewsLayout(Set<View> createdViews) {
        // get view to layout "normally"
        Set<View> filteredCreatedViewsToLayout = Sets.filter(createdViews, new Predicate<View>() {
            public boolean apply(View input) {
                return input.eAdapters().contains(SiriusLayoutDataManager.INSTANCE.getAdapterMarker());
            }
        });

        // get view to center layout
        Set<View> filteredCreatedViewsWithCenterLayout = Sets.filter(createdViews, new Predicate<View>() {
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
     * center layout must be only done on the super containers : filter the
     * createdViewsWithCenterLayout : only the container(s) of all the new
     * created views must have a center layout. the filtered views must have a
     * "normal" layout.
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

    private Collection<Edge> refreshConnections(final Diagram diagram) {
        final Map<EObject, View> domain2NotationMap = new HashMap<EObject, View>();
        final Collection<SiriusLinkDescriptor> linkDescriptors = collectAllLinks(diagram, domain2NotationMap);
        @SuppressWarnings("unchecked")
        final Collection<Edge> existingLinks = new LinkedList<Edge>(diagram.getEdges());
        existingLinks.addAll(collectAllDanglingEdges(diagram));

        final Collection<Edge> noteAttachments = collectAllNoteAttachments(diagram);
        existingLinks.removeAll(noteAttachments);
        final Collection<Edge> lines = collectAllLines(diagram);
        existingLinks.removeAll(lines);
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
                            Predicate<SiriusLinkDescriptor> existingEdgeSource = new Predicate<SiriusLinkDescriptor>() {

                                public boolean apply(SiriusLinkDescriptor input) {
                                    return input.getModelElement().equals(diagramLinkSrc);
                                }
                            };

                            exist = exist && Iterables.isEmpty(Iterables.filter(linkDescriptors, existingEdgeSource));
                        }
                        if (exist && diagramLinkDst instanceof DEdge) {
                            Predicate<SiriusLinkDescriptor> existingEdgeTarget = new Predicate<SiriusLinkDescriptor>() {

                                public boolean apply(SiriusLinkDescriptor input) {
                                    return input.getModelElement().equals(diagramLinkDst);
                                }
                            };

                            exist = exist && Iterables.isEmpty(Iterables.filter(linkDescriptors, existingEdgeTarget));
                        }
                        if (exist) {
                            linksIterator.remove();
                            linkDescriptorsIterator.remove();
                        }
                    }
                }
            }
        }
        deleteViews(existingLinks);
        return createConnections(linkDescriptors, domain2NotationMap, diagram);
    }

    private Collection<Edge> createConnections(final Collection<SiriusLinkDescriptor> linkDescriptors, final Map<EObject, View> domain2NotationMap, final Diagram diagram) {

        List<Edge> createdEdges = new ArrayList<Edge>();

        for (SiriusLinkDescriptor viewDescriptor : linkDescriptors) {
            Edge createdEdge = connectionsFactory.createEdge(viewDescriptor, domain2NotationMap);
            if (createdEdge != null) {
                createdEdges.add(createdEdge);
            }

            // In case of edges toward edges
            collectAllLinks(gmfDiagram, domain2NotationMap);
        }
        return createdEdges;
    }

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

            @SuppressWarnings("unchecked")
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
            if (!domain2NotationMap.containsKey(child.getElement())) { //$NON-NLS-1$
                domain2NotationMap.put(child.getElement(), child);
            }
        }
        for (Edge edge : Iterables.filter(((Diagram) view).getEdges(), Edge.class)) {
            if (!domain2NotationMap.containsKey(edge.getElement())) { //$NON-NLS-1$
                domain2NotationMap.put(edge.getElement(), edge);
            }
        }
        return result;
    }

    private Collection<Edge> collectAllDanglingEdges(Diagram diagram) {
        final Collection<Edge> result = new LinkedList<Edge>();
        final Iterator<EObject> iterContents = diagram.eAllContents();
        while (iterContents.hasNext()) {
            final EObject next = iterContents.next();
            final Iterator<EReference> iterReferences = next.eClass().getEAllReferences().iterator();
            while (iterReferences.hasNext()) {
                final EReference eReference = iterReferences.next();
                if (eReference.getEType().equals(NotationPackage.eINSTANCE.getEdge()) && !eReference.isContainment() && eReference.isMany()) {
                    @SuppressWarnings("unchecked")
                    Collection<Edge> values = (Collection<Edge>) next.eGet(eReference);
                    final Iterator<Edge> iterValues = values.iterator();
                    while (iterValues.hasNext()) {
                        final Edge edge = iterValues.next();
                        if (edge.eContainer() == null) {
                            result.add(edge);
                        }
                    }
                }
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
        @SuppressWarnings("unchecked")
        final Iterator<Edge> edges = diagram.getEdges().iterator();
        while (edges.hasNext()) {
            final Edge currentEdge = edges.next();
            if ("NoteAttachment".equals(currentEdge.getType())) { //$NON-NLS-1$
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
        @SuppressWarnings("unchecked")
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
