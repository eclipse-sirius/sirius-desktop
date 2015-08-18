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
package org.eclipse.sirius.diagram.ui.business.internal.migration;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.GraphicalFilter;
import org.eclipse.sirius.diagram.IndirectlyCollapseFilter;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.ui.business.api.query.NodeQuery;
import org.eclipse.sirius.diagram.ui.edit.internal.part.PortLayoutHelper;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode3EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeList2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.NotationViewIDs;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.internal.refresh.borderednode.CanonicalDBorderItemLocator;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;
import org.osgi.framework.Version;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * The migration code of Sirius 6.7.0.
 * 
 * @author lredor
 */
public class DiagramRepresentationsFileMigrationParticipantV670 {
    /**
     * The VP version for this migration.
     */
    public static final Version MIGRATION_VERSION = new Version("6.7.0.201302181200"); //$NON-NLS-1$

    private Predicate<Node> isBorderedNode = new IsBorderedNodePredicate();

    private Predicate<Node> isDirectlyCollapsedNode = new IsDirectlyCollapsedNodePredicate();

    /**
     * Migration of Bounds type to Location type. In some case, label might have
     * a wrong constraint type in GMF Annotation model (a Bounds instead of a
     * Location).
     * 
     * @param diagrams
     *            list of GMF Diagram to migrate.
     */
    public void migrateLabelConstraintFromBoundsToLocation(List<Diagram> diagrams) {
        for (Diagram diagram : diagrams) {
            Iterator<EObject> iterator = diagram.eAllContents();
            while (iterator.hasNext()) {
                EObject element = iterator.next();
                if (element instanceof Node) {
                    Node node = (Node) element;
                    int typeInt = SiriusVisualIDRegistry.getVisualID(node.getType());
                    // whatever the label position (border or node), the layout
                    // constraint should always be a location and not a bounds.
                    // 5001: a label of a Bordered node on a node.
                    // 5002: a node label
                    // 5003: a node label (where the node is in a container)
                    // 5010: a bordered node label (where the bordered node is
                    // in a container)
                    if (typeInt == NotationViewIDs.DNODE_NAME_EDIT_PART_VISUAL_ID || typeInt == NotationViewIDs.DNODE_NAME_2_EDIT_PART_VISUAL_ID
                            || typeInt == NotationViewIDs.DNODE_NAME_3_EDIT_PART_VISUAL_ID || typeInt == NotationViewIDs.DNODE_NAME_4_EDIT_PART_VISUAL_ID) {
                        LayoutConstraint layoutConstraint = node.getLayoutConstraint();
                        if (layoutConstraint instanceof Bounds) {
                            Location location = NotationFactory.eINSTANCE.createLocation();
                            location.setX(((Bounds) layoutConstraint).getX());
                            location.setY(((Bounds) layoutConstraint).getY());
                            node.setLayoutConstraint(location);
                        }
                    }
                }
            }
        }
    }

    /**
     * 1-Add new IndirectlyCollapseFilter to node (bordered or not) that is
     * contained in collapsed element.<BR>
     * 2-Set width and height of graphical filter of collapsed bordered nodes
     * according to current GMF size and the set the GMF bounds according to
     * there collapsed size and location. 3-Migrate the GMF bounds of the
     * bordered nodes to ensure that they do not overlap each other. Before, the
     * GMF bounds can be inconsistent (with draw2d) and overlap can exists.
     * 
     * @param diagrams
     *            list of GMF Diagram to migrate.
     */
    public void migrateGMFBoundsOfBorderedNodes(List<Diagram> diagrams) {
        for (Diagram diagram : Iterables.filter(diagrams, new IsStandardDiagramPredicate())) {
            // 1-Add new IndirectlyCollapseFilter
            migrateChildrenOfCollapsedNode(diagram);
            // 2-Set width and height of graphical filters of collapsed nodes
            // (directly or not) with GMF size and set GMF bounds.
            migrateGraphicalFiltersAndGMFBounds(diagram);
            // 3-Move bordered node to avoid overlaps
            avoidOverlaps(diagram);
        }
    }

    /**
     * Move bordered nodes to avoid overlaps.
     * 
     * @param diagram
     *            GMF Diagram to migrate.
     */
    private void avoidOverlaps(Diagram diagram) {
        // Get an iterator of bordered nodes that probably move
        Iterator<Node> viewIterator = Iterators.filter(Iterators.filter(diagram.eAllContents(), Node.class), isBorderedNode);
        // Get a list of bordered nodes that probably move (to ignore it
        // during the move of other bordered nodes. After a bordered node
        // has been moved, it is removed from this list.
        List<Node> otherBorderedNodesToIgnore = Lists.newArrayList(Iterators.filter(Iterators.filter(diagram.eAllContents(), Node.class), isBorderedNode));
        while (viewIterator.hasNext()) {
            Node node = viewIterator.next();
            Node parentNode = (Node) node.eContainer();
            // Create a canonicalDBorderItemLocator to locate this bordered
            // nodes according to current location and conflicts with other
            // bordered nodes.
            CanonicalDBorderItemLocator borderItemLocator = new CanonicalDBorderItemLocator(parentNode, PositionConstants.NSEW);
            borderItemLocator.setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);
            LayoutConstraint layoutConstraint = node.getLayoutConstraint();
            // The bordered node without layoutConstraint are ignored (it
            // should not have)
            if (layoutConstraint instanceof Location) {
                Rectangle constraint;
                if (layoutConstraint instanceof Bounds) {
                    Bounds bounds = (Bounds) layoutConstraint;
                    constraint = new Rectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
                } else {
                    Location location = (Location) layoutConstraint;
                    constraint = new Rectangle(location.getX(), location.getY(), -1, -1);
                }
                Point parentAbsoluteLocation = GMFHelper.getAbsoluteLocation(parentNode);
                constraint.translate(parentAbsoluteLocation.x, parentAbsoluteLocation.y);
                final Point realLocation = borderItemLocator.getValidLocation(constraint, node, otherBorderedNodesToIgnore);
                final Dimension d = realLocation.getDifference(parentAbsoluteLocation);
                realLocation.setLocation(new Point(d.width, d.height));

                // Set the new location for Location
                Location location = (Location) layoutConstraint;
                location.setX(realLocation.x);
                location.setY(realLocation.y);

            }
            // Remove this node from the list of bordered node to ignore.
            // Indeed, this port has now at its right location, so it should
            // no longer be ignored during conflict detection.
            otherBorderedNodesToIgnore.remove(node);
        }
    }

    /**
     * Set width and height of graphical filter of collapsed bordered nodes
     * according to current GMF size and the set the GMF bounds according to
     * there collapsed size and location.
     * 
     * @param diagram
     *            GMF Diagram to migrate.
     */
    private void migrateGraphicalFiltersAndGMFBounds(Diagram diagram) {
        Iterator<Node> viewIterator = Iterators.filter(Iterators.filter(diagram.eAllContents(), Node.class), isBorderedNode);
        while (viewIterator.hasNext()) {
            Node node = viewIterator.next();
            if (node.getElement() instanceof DNode && node.eContainer() instanceof Node) {
                // The element of a bordered node should be a DNode and the
                // parent of a bordered node should be a Node.
                DNode dNode = (DNode) node.getElement();
                if (new DDiagramElementQuery(dNode).isIndirectlyCollapsed()) {
                    // This node is collapsed.
                    Dimension expectedCollapsedSize = new NodeQuery(node).getCollapsedSize();
                    LayoutConstraint layoutConstraint = node.getLayoutConstraint();
                    if (layoutConstraint instanceof Bounds) {
                        Bounds bounds = (Bounds) layoutConstraint;
                        Rectangle rectBounds = GMFHelper.getAbsoluteBounds(node);
                        // The GMF node size must be stored in collapse
                        // filter (to can set this size
                        // when this node is expanded).
                        for (GraphicalFilter graphicalFilter : dNode.getGraphicalFilters()) {
                            if (graphicalFilter instanceof CollapseFilter) {
                                ((CollapseFilter) graphicalFilter).setWidth(bounds.getWidth());
                                ((CollapseFilter) graphicalFilter).setHeight(bounds.getHeight());
                            }
                        }
                        // Compute the parent border rectangle
                        Rectangle parentBorder = new NodeQuery((Node) node.eContainer()).getHandleBounds();
                        // Compute the new collapsed bounds
                        Rectangle newCollapsedBounds = PortLayoutHelper.getCollapseCandidateLocation(expectedCollapsedSize, rectBounds, parentBorder);
                        // Translate to relative
                        newCollapsedBounds.translate(parentBorder.getLocation().negate());
                        // // Set new GMF bounds
                        Bounds newBounds = NotationFactory.eINSTANCE.createBounds();
                        newBounds.setX(newCollapsedBounds.x);
                        newBounds.setY(newCollapsedBounds.y);
                        newBounds.setWidth(newCollapsedBounds.width);
                        newBounds.setHeight(newCollapsedBounds.height);
                        node.setLayoutConstraint(newBounds);
                    }
                }
            }
        }
    }

    /**
     * Add a {@link IndirectlyCollapsedFilter} to the children of CollapsedNode
     * (to retrieve the same behavior as before). The migration of GMF bounds of
     * this indirectly collapsed nodes, if they are bordered nodes, are deal
     * later in method {{@link #migrateGMFBoundsOfBorderedNodes(List)}.
     * 
     * @param diagram
     *            GMF Diagram to migrate.
     */
    private void migrateChildrenOfCollapsedNode(Diagram diagram) {
        List<DDiagramElement> indirectlyCollaspedDDEs = Lists.newArrayList();
        Iterator<Node> viewIterator = Iterators.filter(Iterators.filter(diagram.eAllContents(), Node.class), isDirectlyCollapsedNode);
        while (viewIterator.hasNext()) {
            final Node node = viewIterator.next();
            if (node.getElement() instanceof AbstractDNode) {
                AbstractDNode abstractDNode = (AbstractDNode) node.getElement();
                indirectlyCollaspedDDEs.addAll(abstractDNode.getOwnedBorderedNodes());
                if (abstractDNode instanceof DNodeContainer) {
                    DNodeContainer dDiagramElementContainer = (DNodeContainer) abstractDNode;
                    indirectlyCollaspedDDEs.addAll(dDiagramElementContainer.getOwnedDiagramElements());
                } else if (abstractDNode instanceof DNodeList) {
                    DNodeList dNodeList = (DNodeList) abstractDNode;
                    indirectlyCollaspedDDEs.addAll(dNodeList.getOwnedElements());
                }
            }
        }
        for (DDiagramElement indirectlyCollaspedDDE : indirectlyCollaspedDDEs) {
            if (!Iterables.any(indirectlyCollaspedDDE.getGraphicalFilters(), Predicates.instanceOf(IndirectlyCollapseFilter.class))) {
                IndirectlyCollapseFilter indirectlyCollapseFilter = DiagramFactory.eINSTANCE.createIndirectlyCollapseFilter();
                indirectlyCollaspedDDE.getGraphicalFilters().add(indirectlyCollapseFilter);
            }
        }
    }

    /**
     * Predicate that checks that :
     * <UL>
     * <LI>The Diagram input reference a DDiagram,</LI>
     * <LI>and this DDiagram is defined by the ViewpointPackage,</LI>
     * <LI>and its description is defined by the DescriptionPackage,</LI>
     * </UL>
     */
    private static class IsStandardDiagramPredicate implements Predicate<Diagram> {
        public boolean apply(Diagram input) {
            boolean apply = false;
            if (input.getElement() instanceof DDiagram) {
                DDiagram diag = (DDiagram) input.getElement();
                EPackage diagPackage = diag.eClass().getEPackage();
                apply = DiagramPackage.eINSTANCE.equals(diagPackage);
                if (apply && diag.getDescription() != null) {
                    EPackage descriptionPackage = diag.getDescription().eClass().getEPackage();
                    apply = DescriptionPackage.eINSTANCE.equals(descriptionPackage);
                }
            }
            return apply;
        }
    }

    /**
     * Predicate that checks that :
     * <UL>
     * <LI>The input is a GMF Node,</LI>
     * <LI>and this Node is a bordered node.</LI>
     * </UL>
     */
    private static class IsBorderedNodePredicate implements Predicate<Node> {

        public boolean apply(Node input) {
            // Is this node the main view of a DNode and a border
            // node ?
            return new NodeQuery(input).isBorderedNode();
        }
    }

    /**
     * Predicate that checks that :
     * <UL>
     * <LI>The input corresponds to something that can be collapsed,</LI>
     * <LI>and the input is collapsed.</LI>
     * </UL>
     */

    private static class IsDirectlyCollapsedNodePredicate implements Predicate<Node> {
        public boolean apply(Node input) {
            boolean apply = false;

            int type = SiriusVisualIDRegistry.getVisualID(input.getType());
            boolean result = type == DNode2EditPart.VISUAL_ID || type == DNode4EditPart.VISUAL_ID;
            result = result || type == DNodeEditPart.VISUAL_ID || type == DNode3EditPart.VISUAL_ID;
            result = result || type == DNodeContainerEditPart.VISUAL_ID || type == DNodeContainer2EditPart.VISUAL_ID;
            result = result || type == DNodeListEditPart.VISUAL_ID || type == DNodeList2EditPart.VISUAL_ID;

            if (result) {
                return new NodeQuery(input).isDirectlyCollapsed();
            }
            return apply;
        }
    }
}
