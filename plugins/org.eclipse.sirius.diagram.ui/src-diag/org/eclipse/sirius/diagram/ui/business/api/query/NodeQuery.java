/*******************************************************************************
 * Copyright (c) 2013, 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.business.api.query;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.edit.internal.part.PortLayoutHelper;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeList2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.draw2d.figure.ICollapseMode;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * A class aggregating all the queries (read-only!) having a {@link Node} as a starting point.
 * 
 * @author lredor
 */
public class NodeQuery {

    private Node node;

    /**
     * Create a new query.
     * 
     * @param node
     *            the starting point.
     */
    public NodeQuery(Node node) {
        this.node = node;
    }

    /**
     * Return the expected collapsed node dimension according to the preferences.
     * 
     * @return The expected collapsed node dimension.
     */
    public Dimension getCollapsedSize() {
        Dimension expectedDim = ICollapseMode.COLLAPSED_DIMENSION;
        if (!ICollapseMode.DEFAULT) {
            expectedDim = ICollapseMode.MINIMIZED_DIMENSION;
        }
        return expectedDim;
    }

    /**
     * Retrieves the node dimension before collapse from {@link CollapseFilter}. In case of the collapse filter width
     * and height attributes are equal to zero, the default dimension is returned.
     * 
     * @return the original dimension.
     */
    public Dimension getOriginalDimensionBeforeCollapse() {
        EObject element = node.getElement();
        Dimension dim = new Dimension(0, 0);
        if (element instanceof DDiagramElement) {
            CollapseFilter filter = (CollapseFilter) Iterables.getFirst(Iterables.filter(((DDiagramElement) element).getGraphicalFilters(), Predicates.instanceOf(CollapseFilter.class)), null);
            if (filter == null || filter.getWidth() == 0 || filter.getHeight() == 0) {
                dim.setSize(getDefaultDim((DDiagramElement) element));
            } else {
                dim.setSize(new Dimension(filter.getWidth(), filter.getHeight()));
            }
        }
        return dim;
    }

    /**
     * Returns if the node is collapsed or is indirectly collapsed (contains at least one {@link CollapseFilter}) via
     * the {@link DDiagramElementQuery}.
     * 
     * @return true if the node is collapsed, false otherwise.
     */
    public boolean isCollapsed() {
        EObject element = node.getElement();
        if (element instanceof DDiagramElement) {
            DDiagramElementQuery dDiagramElementQuery = new DDiagramElementQuery((DDiagramElement) element);
            return dDiagramElementQuery.isIndirectlyCollapsed();
        }
        return false;
    }

    /**
     * Returns if the node is directly collapsed (contains at least one {@link CollapseFilter} that is not a
     * {@link org.eclipse.sirius.viewpoint.IndirectlyCollapseFilter}) via the {@link DDiagramElementQuery}.
     * 
     * @return true if the node is indirectly collapsed, false otherwise.
     */
    public boolean isDirectlyCollapsed() {
        EObject element = node.getElement();
        if (element instanceof DDiagramElement) {
            return new DDiagramElementQuery((DDiagramElement) element).isCollapsed();
        }
        return false;
    }

    /**
     * Returns if the node is indirectly collapsed (contains at least one
     * {@link org.eclipse.sirius.viewpoint.IndirectlyCollapseFilter}) via the {@link DDiagramElementQuery}.
     * 
     * @return true if the node is indirectly collapsed, false otherwise.
     */
    public boolean isIndirectlyCollapsed() {
        EObject element = node.getElement();
        if (element instanceof DDiagramElement) {
            return new DDiagramElementQuery((DDiagramElement) element).isOnlyIndirectlyCollapsed();
        }
        return false;
    }

    /**
     * Return default dimension of this {@link DDiagramElement}.
     * 
     * @param element
     *            the concerned {@link DDiagramElement}.
     * @return default dimension
     */
    protected Dimension getDefaultDim(DDiagramElement element) {
        Dimension dim;
        if (element instanceof DNode) {
            int originalDNodeWidth = ((DNode) element).getWidth();
            int originalDNodeHeight = ((DNode) element).getHeight();
            dim = new Dimension(originalDNodeWidth * LayoutUtils.SCALE, originalDNodeHeight * LayoutUtils.SCALE);
        } else {
            // TODO FBA: set default VSM dim if filter dim are
            // zero.
            dim = new Dimension(0, 0);
        }
        return dim;
    }

    /**
     * Tests whether the queried Node corresponds to a bordered node.
     * 
     * @return <code>true</code> if the queried View corresponds to a bordered node.
     */
    public boolean isBorderedNode() {
        int type = SiriusVisualIDRegistry.getVisualID(this.node.getType());
        boolean result = type == DNode2EditPart.VISUAL_ID || type == DNode4EditPart.VISUAL_ID;
        return result;
    }

    /**
     * Tests whether the queried Node corresponds to a container (list or not).
     * 
     * @return <code>true</code> if the queried View corresponds to a container node.
     */
    public boolean isContainer() {
        int type = SiriusVisualIDRegistry.getVisualID(this.node.getType());
        boolean result = type == DNodeContainer2EditPart.VISUAL_ID || type == DNodeContainerEditPart.VISUAL_ID || type == DNodeList2EditPart.VISUAL_ID || type == DNodeListEditPart.VISUAL_ID;
        return result;
    }

    /**
     * <p>
     * Provides the new extended bounds for the collapsed GMF Node. The collapse filter attached to graphical filters of
     * the corresponding DDIagramElement should not be delete before to call this method. Indeed, the old bounds before
     * to collapse are kept into this collapsed filters.
     * </p>
     * <p>
     * If the old bounds before to collapse are not saved into the collapse filter, the size specified into
     * {@link DDiagramElement} is used.
     * </p>
     * 
     * @return the new extended bounds.
     */
    public Option<Bounds> getExtendedBounds() {
        return getNewGMFBounds(false);
    }

    /**
     * Provides the new collapsed bounds for the extended GMF Node.
     * 
     * @return the new collapsed bounds.
     */
    public Option<Bounds> getCollapsedBounds() {
        return getNewGMFBounds(true);
    }

    private Option<Bounds> getNewGMFBounds(boolean isCollapsed) {
        EObject element = node.getElement();
        LayoutConstraint layoutConstraint = node.getLayoutConstraint();

        if (element instanceof DDiagramElement && layoutConstraint instanceof Bounds) {
            Bounds bounds = (Bounds) layoutConstraint;
            Bounds newBounds = NotationFactory.eINSTANCE.createBounds();

            Dimension dim;
            if (isCollapsed) {
                dim = getCollapsedSize();
            } else {
                CollapseFilter filter = (CollapseFilter) Iterables.getFirst(Iterables.filter(((DDiagramElement) element).getGraphicalFilters(), Predicates.instanceOf(CollapseFilter.class)), null);

                if (filter == null || (filter.getWidth() == 0 || filter.getHeight() == 0)) {
                    dim = getDefaultDim((DDiagramElement) element);
                } else {
                    dim = new Dimension(filter.getWidth(), filter.getHeight());
                }
            }
            Rectangle rect = null;
            Rectangle constraint = new Rectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
            Option<Rectangle> parentBorder = getParentBorder();
            if (parentBorder.some()) {
                // When the node is on diagram, there is no need to translate to
                // absolute.
                constraint.translate(parentBorder.get().x, parentBorder.get().y);
            }
            if (isCollapsed) {
                rect = PortLayoutHelper.getCollapseCandidateLocation(dim, constraint, parentBorder.get());
            } else {
                rect = PortLayoutHelper.getUncollapseCandidateLocation(dim, constraint, parentBorder.get());
            }
            if (parentBorder.some()) {
                // we translate to relative if there is a parent node.
                Dimension d = rect.getLocation().getDifference(parentBorder.get().getLocation());
                rect.setLocation(new Point(d.width, d.height));
            } else {
                rect.setLocation(rect.getLocation().getCopy());
            }
            newBounds.setHeight(rect.height);
            newBounds.setWidth(rect.width);
            newBounds.setX(rect.x);
            newBounds.setY(rect.y);

            return Options.newSome(newBounds);
        }
        return Options.newNone();
    }

    private Option<Rectangle> getParentBorder() {
        EObject container = node.eContainer();
        if (container instanceof Node) {
            NodeQuery nodeQuery = new NodeQuery((Node) container);
            return Options.newSome(nodeQuery.getHandleBounds());
        }
        return Options.newNone();
    }

    /**
     * Returns the Rectangle around which handles are to be placed. The Rectangle should be in the same coordinate
     * system as the bounds itself.
     * 
     * This method returns the same rectangle as the {@link org.eclipse.gef.handles.HandleBounds#getHandleBounds()} and
     * that is used in
     * {@link org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator.BorderItemLocator#getParentBorder()} .
     * 
     * @return The rectangle used for handles
     */
    public Rectangle getHandleBounds() {
        return GMFHelper.getAbsoluteBounds(node, true, true);
    }

    /**
     * Tests whether the queried Node is a descendant of <code>container</code>.
     * 
     * @param container
     *            The container
     * @return true if the queried Node is a descendant of <code>container</code>, false otherwise.
     */
    public boolean isDescendantOf(View container) {
        boolean result = false;
        if (node.eContainer() instanceof Node) {
            if (container instanceof Node && new NodeQuery((Node) container).isContainer()) {
                if (container.equals(node.eContainer())) {
                    result = true;
                } else {
                    result = new NodeQuery((Node) node.eContainer()).isDescendantOf(container);
                }
            }
        }
        return result;
    }

}
