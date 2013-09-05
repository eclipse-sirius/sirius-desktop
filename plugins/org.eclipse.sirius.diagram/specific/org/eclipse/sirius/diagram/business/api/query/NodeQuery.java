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
package org.eclipse.sirius.diagram.business.api.query;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.CollapseFilter;
import org.eclipse.sirius.DDiagramElement;
import org.eclipse.sirius.DNode;
import org.eclipse.sirius.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.description.ContainerMapping;
import org.eclipse.sirius.diagram.edit.internal.part.PortLayoutHelper;
import org.eclipse.sirius.diagram.internal.edit.parts.DNode2EditPart;
import org.eclipse.sirius.diagram.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.ICollapseMode;

/**
 * A class aggregating all the queries (read-only!) having a {@link Node} as a
 * starting point.
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
     * Tests whether the queried Node corresponds to a bordered node.
     * 
     * @return <code>true</code> if the queried View corresponds to a bordered
     *         node.
     */
    public boolean isBorderedNode() {
        int type = SiriusVisualIDRegistry.getVisualID(this.node.getType());
        boolean result = type == DNode2EditPart.VISUAL_ID || type == DNode4EditPart.VISUAL_ID;
        return result;
    }

    /**
     * <p>
     * Provides the new extended bounds for the collapsed GMF Node. The collapse
     * filter attached to graphical filters of the corresponding DDIagramElement
     * should not be delete before to call this method. Indeed, the old bounds
     * before to collapse are kept into this collapsed filters.
     * </p>
     * <p>
     * If the old bounds before to collapse are not saved into the collapse
     * filter, the size specified into {@link DDiagramElement} is used.
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

    /**
     * Return the expected collapsed node dimension according to the
     * preferences.
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
     * Retrieves the node dimension before collapse from {@link CollapseFilter}.
     * In case of the collapse filter width and height attributes are equal to
     * zero, the default dimension is returned.
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
     * Returns if the node is collapsed or is indirectly collapsed (contains at
     * least one {@link CollapseFilter}) via the {@link DDiagramElementQuery}.
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
     * Returns if the node is directly collapsed (contains at least one
     * {@link CollapseFilter} that is not a
     * {@link org.eclipse.sirius.IndirectlyCollapseFilter}) via the
     * {@link DDiagramElementQuery}.
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
     * {@link org.eclipse.sirius.IndirectlyCollapseFilter}) via the
     * {@link DDiagramElementQuery}.
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

    private Dimension getDefaultDim(DDiagramElement element) {
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

    private Option<Rectangle> getParentBorder() {
        EObject container = node.eContainer();
        if (container instanceof Node) {
            NodeQuery nodeQuery = new NodeQuery((Node) container);
            return Options.newSome(nodeQuery.getHandleBounds());
        }
        return Options.newNone();
    }

    /**
     * Returns the Rectangle around which handles are to be placed. The
     * Rectangle should be in the same coordinate system as the bounds itself.
     * 
     * This method returns the same rectangle as the
     * {@link org.eclipse.gef.handles.HandleBounds#getHandleBounds()} and that
     * is used in
     * {@link org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator.BorderItemLocator#getParentBorder()}
     * .
     * 
     * @return The rectangle used for handles
     */
    public Rectangle getHandleBounds() {
        Rectangle bounds = GMFHelper.getAbsoluteBounds(node);
        if (node.getElement() instanceof DDiagramElement) {
            DDiagramElement dDiagramElement = (DDiagramElement) node.getElement();
            // All container styles have a specific insets.
            if (dDiagramElement.getDiagramElementMapping() instanceof ContainerMapping) {
                // This insets corresponds to insets of {@link
                // org.eclipse.sirius.diagram.ui.tools.api.figure.AlphaDropShadowBorder#getTransparentInsets()}.
                Insets insets = new Insets(0, 0, 2, 2);
                return new Rectangle(bounds.x + insets.left, bounds.y + insets.top, bounds.width - (insets.right + insets.left), bounds.height - (insets.bottom + insets.top));
            }
        }
        return bounds;
    }
}
