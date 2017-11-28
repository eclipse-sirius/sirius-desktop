/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.api.helper.graphicalfilters;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.IndirectlyCollapseFilter;
import org.eclipse.sirius.diagram.business.api.diagramtype.DiagramTypeDescriptorRegistry;
import org.eclipse.sirius.diagram.business.api.diagramtype.ICollapseUpdater;
import org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramTypeDescriptor;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.NodeQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A default {@link ICollapseUpdater} to modify the graphical filters of
 * {@link DDiagramElement} and the bounds of the {@link Node} according to the
 * collapse changes.
 * 
 * @author lredor
 */
public class CollapseUpdater implements ICollapseUpdater {
    /**
     * Default constructor.
     */
    public CollapseUpdater() {
    }

    /**
     * Get a collapse updater which corresponds to the given DDiagram. Look for
     * {@link ICollapseUpdater} provided by a {@link IDiagramTypeDescriptor}, if
     * no updater is provided, return a default new {@link CollapseUpdater}.
     * 
     * @param diagram
     *            the current DDiagram which needs collapse application.
     * @return an ICollapseUpdater.
     */
    public static ICollapseUpdater getICollapseUpdater(DDiagram diagram) {
        ICollapseUpdater collapseUpdater = null;
        // If diagram is not null, we search for a possible
        // DiagramDescriptionProvider handling this type of diagram and getting
        // a specific ICollapseUpdater
        for (final IDiagramTypeDescriptor diagramTypeDescriptor : DiagramTypeDescriptorRegistry.getInstance().getAllDiagramTypeDescriptors()) {
            Option<? extends ICollapseUpdater> optionalCollapseUpdater = diagramTypeDescriptor.getDiagramDescriptionProvider().getCollapseUpdater(diagram);
            if (optionalCollapseUpdater.some()) {
                collapseUpdater = optionalCollapseUpdater.get();
            }
        }

        if (collapseUpdater == null) {
            // Use the default one
            collapseUpdater = new CollapseUpdater();
        }
        return collapseUpdater;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.diagramtype.ICollapseUpdater
     *      #synchronizeCollapseFiltersAndGMFBounds(DDiagramElement, boolean,
     *      Class)
     */
    public void synchronizeCollapseFiltersAndGMFBounds(DDiagramElement element, boolean add, Class<? extends CollapseFilter> kindOfFilter) {
        EObjectQuery eObjectQuery = new EObjectQuery(element);
        Iterable<Node> nodes = Iterables.filter(eObjectQuery.getInverseReferences(NotationPackage.eINSTANCE.getView_Element()), Node.class);
        if (nodes.iterator().hasNext()) {
            Node node = nodes.iterator().next();
            synchronizeCollapseFiltersAndGMFBounds(element, Options.newSome(node), add, kindOfFilter);
        } else {
            // this case can happens in repair context where DDIagramElement
            // have not corresponding GMF Node yet.
            synchronizeCollapseFiltersAndGMFBounds(element, Options.<Node> newNone(), add, kindOfFilter);
        }
    }

    /**
     * Adds or removes the graphical filter ({@link CollapseFilter} or
     * {@link IndirectlyCollapseFilter} of <code>element</code> and update (if
     * needed) the bounds of the GMF Node according to the collapse changes.
     * 
     * @param element
     *            The @link {@link DDiagramElement} to synchronized.
     * @param optionalNode
     *            The optional {@link Node} to synchronized. The GMF bounds are
     *            not update if there is no node (it is the case during the
     *            repair process).
     * @param add
     *            true if a new filter must be added to element, false if all
     *            filters of kind <code>kindOfFilter</code> must be removed.
     * @param kindOfFilter
     *            the kind of filter to add or remove ( {@link CollapseFilter}
     *            or {@link IndirectlyCollapseFilter}
     */
    protected void synchronizeCollapseFiltersAndGMFBounds(DDiagramElement element, Option<Node> optionalNode, boolean add, Class<? extends CollapseFilter> kindOfFilter) {
        if (add) {
            addNewGraphicalFilter(element, optionalNode, kindOfFilter);
        } else {
            removeGraphicalFilter(element, optionalNode, kindOfFilter);
        }
    }

    /**
     * Add a new filter to <code>element</code> and update (if needed) the
     * bounds of the GMF Node.
     * 
     * @param element
     *            The @link {@link DDiagramElement} to synchronized.
     * @param optionalNode
     *            The optional {@link Node} to synchronized. The GMF bounds are
     *            not update if there is no node (it is the case during the
     *            repair process).
     * @param kindOfFilter
     *            the kind of filter to add or remove ( {@link CollapseFilter}
     *            or {@link IndirectlyCollapseFilter}
     */
    protected void addNewGraphicalFilter(DDiagramElement element, Option<Node> optionalNode, Class<? extends CollapseFilter> kindOfFilter) {
        DDiagramElementQuery query = new DDiagramElementQuery(element);
        // Store if the element is directly or indirectly collapsed before
        // application.
        boolean isAlreayCollapsed = query.isIndirectlyCollapsed();

        if (kindOfFilter.equals(CollapseFilter.class)) {
            if (!query.isCollapsed()) {
                CollapseFilter collapseApplication = DiagramFactory.eINSTANCE.createCollapseFilter();
                element.getGraphicalFilters().add(collapseApplication);
            }
            addIndirectlyCollapseFilterToChildren(element);
        } else if (kindOfFilter.equals(IndirectlyCollapseFilter.class)) {
            if (!query.isOnlyIndirectlyCollapsed()) {
                IndirectlyCollapseFilter indirectlyCollapseFilter = DiagramFactory.eINSTANCE.createIndirectlyCollapseFilter();
                element.getGraphicalFilters().add(indirectlyCollapseFilter);
            }
        }

        storeInFilterAndCollapseBounds(element, optionalNode, isAlreayCollapsed);
    }

    /**
     * If the optionalNode exists, get its size and store it into the
     * {@link DDiagramElement}'s CollapseFilter, then if required collapse the
     * layout constraint of the GMF node.
     * 
     * @param element
     *            the current {@link DDiagramElement}.
     * @param optionalNode
     *            an option referencing the GMF {@link Node} corresponding to
     *            the given {@link DDiagramElement}.
     * @param isAlreayCollapsed
     *            indicates if the node is known has already collapsed and do
     *            not require to have its layout constraint collapsed again.
     */
    public void storeInFilterAndCollapseBounds(DDiagramElement element, Option<Node> optionalNode, boolean isAlreayCollapsed) {
        if (optionalNode.some()) {
            saveBoundsInFilter(optionalNode.get(), element);

            NodeQuery nodeQuery = new NodeQuery(optionalNode.get());
            boolean isBorderedNode = nodeQuery.isBorderedNode();

            // we don't collapse the node bounds if it was already collapsed or
            // if it's not a bordered node
            if (!isAlreayCollapsed && isBorderedNode) {
                collapseBounds(optionalNode.get(), element);
            }
        }
    }

    /**
     * Remove filter of <code>element</code> and update (if needed) the bounds
     * of the GMF Node.
     * 
     * @param element
     *            The @link {@link DDiagramElement} to synchronized.
     * @param optionalNode
     *            The optional {@link Node} to synchronized. The GMF bounds are
     *            not update if there is no node (it is the case during the
     *            repair process).
     * @param kindOfFilter
     *            the kind of filter to add or remove ( {@link CollapseFilter}
     *            or {@link IndirectlyCollapseFilter}
     */
    protected void removeGraphicalFilter(DDiagramElement element, Option<Node> optionalNode, Class<? extends CollapseFilter> kindOfFilter) {
        // Compute the expanded Bounds that will be used at the end if the node
        // is no more collapsed. This expanded bounds is computed now because
        // after removing all graphical filters is too late.
        Option<Bounds> optionalExpandedBounds;
        if (optionalNode.some()) {
            optionalExpandedBounds = getExpandedBounds(optionalNode.get(), element);
        } else {
            optionalExpandedBounds = Options.newNone();

        }
        if (kindOfFilter.equals(CollapseFilter.class)) {
            // Iterate on all CollapseFilter that are not
            // IndirectlyCollapseFilter
            for (CollapseFilter collapseApplication : Lists.newArrayList(Iterables.filter(Iterables.filter(element.getGraphicalFilters(), CollapseFilter.class),
                    Predicates.not(Predicates.instanceOf(IndirectlyCollapseFilter.class))))) {
                element.getGraphicalFilters().remove(collapseApplication);
            }
            removeFromChildrenIndirectlyCollapsedFilter(element);
        } else if (kindOfFilter.equals(IndirectlyCollapseFilter.class)) {
            for (IndirectlyCollapseFilter collapseApplication : Lists.newArrayList(Iterables.filter(element.getGraphicalFilters(), IndirectlyCollapseFilter.class))) {
                element.getGraphicalFilters().remove(collapseApplication);
            }
        }

        if (optionalNode.some()) {
            NodeQuery nodeQuery = new NodeQuery(optionalNode.get());
            // if the node have no more collapse filter after having remove the
            // last one, we expand it (if it's a bordered node only)
            if (!nodeQuery.isCollapsed() && nodeQuery.isBorderedNode()) {
                if (optionalExpandedBounds.some()) {
                    optionalNode.get().setLayoutConstraint(optionalExpandedBounds.get());
                }
            }
        }
    }

    /**
     * Collapse the GMF bounds of this node (the bound of this node must be in
     * expanded state before calling this method).
     * 
     * @param node
     *            The node to collapse.
     * @param element
     *            The ddiagramElement corresponding to the node to expand.
     */
    public void collapseBounds(Node node, DDiagramElement element) {
        // Get the collapsed bounds
        Option<Bounds> option = new NodeQuery(node).getCollapsedBounds();

        if (option.some()) {
            node.setLayoutConstraint(option.get());
        }
    }

    /**
     * Get the expanded bounds of this node (the bound of this node must be in
     * collapsed state before calling this method).
     * 
     * @param node
     *            The node to expand.
     * @param element
     *            The ddiagramElement corresponding to the node to expand
     *            (useful to make specific query directly on element without
     *            using a cross-referencer from node, for other kind of
     *            CollapseUptaer).
     * @return An optional bounds. The bounds can be null. For example, if the
     *         current layout constraint of the <code>node</code> is not a
     *         Bounds.
     */
    public Option<Bounds> getExpandedBounds(Node node, DDiagramElement element) {
        return new NodeQuery(node).getExtendedBounds();
    }

    /**
     * Save the current GMF Node bounds into collapsed filters. If the node is
     * already collapsed, we set width and height on each unset filters with
     * already set filter.
     * 
     * @param node
     *            the GMF Node.
     * @param diagramElement
     *            the corresponding viewpoint element.
     */
    protected void saveBoundsInFilter(Node node, DDiagramElement diagramElement) {
        LayoutConstraint layoutConstraint = node.getLayoutConstraint();
        if (layoutConstraint instanceof Bounds) {
            Bounds bounds = (Bounds) layoutConstraint;
            // Save size constraint into Collapse filter
            // before collapsing
            int referenceWidth = bounds.getWidth();
            int referenceHeight = bounds.getHeight();
            for (CollapseFilter filter : Iterables.filter(diagramElement.getGraphicalFilters(), CollapseFilter.class)) {
                if (filter.eIsSet(DiagramPackage.eINSTANCE.getCollapseFilter_Width()) && filter.eIsSet(DiagramPackage.eINSTANCE.getCollapseFilter_Height())) {
                    referenceWidth = filter.getWidth();
                    referenceHeight = filter.getHeight();
                } else {
                    filter.setWidth(referenceWidth);
                    filter.setHeight(referenceHeight);
                }
            }
        }
    }

    /**
     * Retrieves all direct children of given element.
     * 
     * @param element
     *            the element to retrieve his children.
     * @return the direct children list.
     */
    private List<DDiagramElement> getAllChildren(DDiagramElement element) {
        List<DDiagramElement> allChildren = new ArrayList<>();
        if (element instanceof AbstractDNode) {
            AbstractDNode abstractDNode = (AbstractDNode) element;
            allChildren.addAll(abstractDNode.getOwnedBorderedNodes());
            if (abstractDNode instanceof DNodeContainer) {
                DNodeContainer dDiagramElementContainer = (DNodeContainer) abstractDNode;
                allChildren.addAll(dDiagramElementContainer.getOwnedDiagramElements());
            } else if (abstractDNode instanceof DNodeList) {
                DNodeList dNodeList = (DNodeList) abstractDNode;
                allChildren.addAll(dNodeList.getOwnedElements());
            }
        }
        return allChildren;
    }

    /**
     * Adds indirectlyCollpasedFilter to all children.
     * 
     * @param element
     *            the ddiagramelement that is collapsed.
     */
    protected void addIndirectlyCollapseFilterToChildren(DDiagramElement element) {
        List<DDiagramElement> children = getAllChildren(element);
        for (DDiagramElement child : children) {
            synchronizeCollapseFiltersAndGMFBounds(child, true, IndirectlyCollapseFilter.class);
        }
    }

    /**
     * Removes indirectlyCollpasedFilter from all children.
     * 
     * @param element
     *            the ddiagramelement that was collapsed.
     */
    protected void removeFromChildrenIndirectlyCollapsedFilter(DDiagramElement element) {
        List<DDiagramElement> children = getAllChildren(element);
        for (DDiagramElement child : children) {
            synchronizeCollapseFiltersAndGMFBounds(child, false, IndirectlyCollapseFilter.class);
        }

    }
}
