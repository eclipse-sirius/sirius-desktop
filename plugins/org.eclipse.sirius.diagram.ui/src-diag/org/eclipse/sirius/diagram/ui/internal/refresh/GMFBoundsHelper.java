/*******************************************************************************
 * Copyright (c) 2026 Obeo.
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
package org.eclipse.sirius.diagram.ui.internal.refresh;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.model.business.internal.query.DDiagramElementContainerExperimentalQuery;
import org.eclipse.sirius.diagram.model.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.NodeQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeList2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.diagram.ui.internal.refresh.borderednode.CanonicalDBorderItemLocator;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.IContainerLabelOffsets;

public class GMFBoundsHelper {

    /**
     * see org.eclipse.sirius.diagram.ui.internal.edit.parts. AbstractDNodeContainerCompartmentEditPart.DEFAULT_MARGIN
     * the top value is the DEFAULT_MARGIN + the InvisibleResizableCompartmentFigure top Inset (1px), also corresponding
     * to IContainerLabelOffsets.LABEL_OFFSET.
     */
    private static final Insets FREEFORM_CONTAINER_INSETS = new Insets(IContainerLabelOffsets.LABEL_OFFSET, AbstractDNodeContainerCompartmentEditPart.DEFAULT_MARGIN,
            AbstractDNodeContainerCompartmentEditPart.DEFAULT_MARGIN, AbstractDNodeContainerCompartmentEditPart.DEFAULT_MARGIN);

    private static final Insets LIST_CONTAINER_INSETS = new Insets(4, 0, 0, 0);

    private Map<Node, Rectangle> boundsCache = new HashMap<>();

    /**
     * Get the absolute location relative to the origin (Diagram).
     * 
     * @param node
     *            the GMF Node
     * @param insetsAware
     *            true to consider the draw2D figures insets. <strong>Warning:</strong> Those insets are based on the
     *            current Sirius editParts and could become wrong if a developer customizes them.
     * @param adaptBorderNodeLocation
     *            Useful for specific border nodes, like in sequence diagrams, to center the border nodes if the
     *            coordinate is 0 (x for EAST or WEST side, y for NORTH or SOUTH side)
     * 
     * @return the absolute location of the node relative to the origin (Diagram)
     */
    public Point getAbsoluteLocation(Node node, boolean insetsAware, boolean adaptBorderNodeLocation) {
        // TODO: Location of title "DNode*NameEditPart", x coordinate, can be wrong according to label alignment. This
        // problem is not yet handled.

        Point location = new Point(0, 0);
        LayoutConstraint layoutConstraint = node.getLayoutConstraint();
        if (layoutConstraint instanceof Bounds gmfBounds && !(new ViewQuery(node).isRegion())) {
            // The bounds is computed for horizontal or vertical regions, even if it is stored in GMF data
            location.setX(gmfBounds.getX());
            location.setY(gmfBounds.getY());
        }
        ViewQuery viewQuery = new ViewQuery(node);
        if (adaptBorderNodeLocation && viewQuery.isBorderedNode() && layoutConstraint instanceof Bounds gmfBounds) {
            // manage location of bordered node with closest side
            if (node.getElement() instanceof DNode dNode && dNode.eContainer() instanceof AbstractDNode parentAbstractDNode && parentAbstractDNode.getOwnedBorderedNodes().contains(dNode)) {
                Node parentNode = (Node) node.eContainer();
                if (parentNode.getLayoutConstraint() instanceof Bounds parentBounds) {
                    int position = CanonicalDBorderItemLocator.findClosestSideOfParent(new Rectangle(gmfBounds.getX(), gmfBounds.getY(), gmfBounds.getWidth(), gmfBounds.getHeight()),
                            new Rectangle(parentBounds.getX(), parentBounds.getY(), parentBounds.getWidth(), parentBounds.getHeight()));
                    centerLocationIfZero(location, position, parentBounds, gmfBounds);
                }
            }
        }
        if (viewQuery.isListCompartment()) {
            // Translate the compartment to be just below the the title, the x coordinate is also the same (same parent
            // insets)
            Rectangle titleBounds = getAbsoluteBounds(getPreviousChild(node), true, false, false, false);
            location.translate(titleBounds.preciseX(), titleBounds.preciseY() + titleBounds.preciseHeight());
            // Translate from the spacing (5 pixels)
            location.translate(0, IContainerLabelOffsets.LABEL_OFFSET);
        } else if (viewQuery.isListItem()) {
            if (node.eContainer() instanceof Node container) {
                if (container.getChildren().get(0) == node) {
                    Point parentNodeLocation = getAbsoluteLocation(container, insetsAware, false);
                    location.translate(parentNodeLocation);
                    if (insetsAware) {
                        translateWithInsets(location, node);
                    }
                } else {
                    // Translate from the previous children
                    Rectangle previousChildBounds = getAbsoluteBounds(getPreviousChild(node), true, false, false, false);
                    location.translate(previousChildBounds.preciseX(), previousChildBounds.preciseY() + previousChildBounds.preciseHeight());
                }
            }
        } else if (viewQuery.isRegionContainerCompartment()) {
            // Translate the compartment to be just below the the title, the x coordinate is also the same (same parent
            // insets)
            Rectangle titleBounds = getAbsoluteBounds(getPreviousChild(node), true, false, false, false);
            location.translate(titleBounds.preciseX(), titleBounds.preciseY() + titleBounds.preciseHeight());
            // Translate from the spacing (5 pixels)
            location.translate(0, IContainerLabelOffsets.LABEL_OFFSET);
        } else if (viewQuery.isVerticalRegion()) {
            if (node.eContainer() instanceof Node container) {
                if (container.getChildren().get(0) == node) {
                    Point parentNodeLocation = getAbsoluteLocation(container, insetsAware, false);
                    location.translate(parentNodeLocation);
                    if (insetsAware) {
                        translateWithInsets(location, node);
                    }
                } else {
                    // Translate from the previous children
                    Rectangle previousChildBounds = getAbsoluteBounds(getPreviousChild(node), true, false, false, false);
                    location.translate(previousChildBounds.preciseX(), previousChildBounds.preciseY() + previousChildBounds.preciseHeight());
                }
            }
        } else if (viewQuery.isHorizontalRegion()) {
            if (node.eContainer() instanceof Node container) {
                if (container.getChildren().get(0) == node) {
                    Point parentNodeLocation = getAbsoluteLocation(container, insetsAware, false);
                    location.translate(parentNodeLocation);
                    if (insetsAware) {
                        translateWithInsets(location, node);
                    }
                } else {
                    // Translate from the previous children
                    Rectangle previousChildBounds = getAbsoluteBounds(getPreviousChild(node), true, false, false, false);
                    location.translate(previousChildBounds.preciseX() + previousChildBounds.preciseWidth(), previousChildBounds.preciseY());
                }
            }
        } else if (node.eContainer() instanceof Node container) {
            Point parentNodeLocation = getAbsoluteLocation(container, insetsAware, true);
            location.translate(parentNodeLocation);
            if (insetsAware) {
                translateWithInsets(location, node);
            }
        }
        return location;
    }

    private Node getPreviousChild(Node searchedNode) {
        Node previousChild = null;
        boolean found = false;
        if (searchedNode.eContainer() instanceof Node container) {
            for (Iterator<View> children = container.getChildren().iterator(); children.hasNext() && !found; /* */) {
                View child = children.next();
                if (child instanceof Node nodeChild) {
                    if (searchedNode == nodeChild) {
                        found = true;
                    } else {
                        previousChild = nodeChild;
                    }
                }
            }
        }
        if (found) {
            return previousChild;
        } else {
            return null;
        }
    }

    /**
     * Return the bottom-right insets of the container of this <code>node</code>. The insets also considers its border.
     * 
     * @param container
     *            The container for which we wish to have the insets. This {@link Node} must correspond to a container,
     *            otherwise, {0,0} is returned
     * @return the bottom-right insets of this <code>container</code>
     */
    private Dimension getBottomRightInsets(Node container) {
        Dimension result = new Dimension(0, 0);
        NodeQuery nodeQuery = new NodeQuery(container);
        if (nodeQuery.isContainer()) {
            EObject element = container.getElement();
            if (element instanceof DDiagramElementContainer ddec) {
                // RegionContainer do not have containers insets
                if (ddec instanceof DNodeContainer dNodeContainer) {
                    if (new DNodeContainerExperimentalQuery(dNodeContainer).isRegionContainer()) {
                        result.setWidth(LIST_CONTAINER_INSETS.right);
                        result.setHeight(LIST_CONTAINER_INSETS.bottom);
                        // TODO: to verify
                        Dimension borderSize = GMFHelper.getBorderSize(ddec);
                        result.setWidth(result.width() + borderSize.width());
                        result.setHeight(result.height() + borderSize.height());
                    } else if (new DDiagramElementContainerExperimentalQuery(ddec).isRegion()) {
                        // No margin, except the border size
                        Dimension borderSize = GMFHelper.getBorderSize(ddec);
                        result.setWidth(result.width() + borderSize.width());
                        result.setHeight(result.height() + borderSize.height());
                    } else {
                        if (GMFHelper.hasFullLabelBorder(ddec)) {
                            // TODO : Not sure about that, to verify
                            result.setHeight(FREEFORM_CONTAINER_INSETS.bottom);
                        } else {
                            result.setWidth(FREEFORM_CONTAINER_INSETS.right);
                            result.setHeight(FREEFORM_CONTAINER_INSETS.bottom);
                        }
                        Dimension borderSize = GMFHelper.getBorderSize(ddec);
                        // Added twice as this insets is used to compute the "global" size including the border
                        result.setWidth(result.width() + (borderSize.width() * 2));
                        result.setHeight(result.height() + (borderSize.height() * 2));
                    }
                } else if (ddec instanceof DNodeList) {
                    result.setWidth(LIST_CONTAINER_INSETS.right);
                    result.setHeight(LIST_CONTAINER_INSETS.bottom);
                    // TODO: to verify
                    Dimension borderSize = GMFHelper.getBorderSize(ddec);
                    result.setWidth(result.width() + borderSize.width());
                    result.setHeight(result.height() + borderSize.height());
                }
            }
        } else if (nodeQuery.isListCompartment()) {
            // Add the corresponding margin of {1, 4, 0, 4} of
            // org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeListCompartmentEditPart.createFigure(),
            // more precisely at the end of the method, the margin is the evaluation of
            // "result.getContentPane().getBorder()".
            result.setWidth(4);
            result.setHeight(0);
        }
        return result;
    }

    /**
     * Shift the current node absolute location by the container insets.
     * 
     * @param locationToTranslate
     *            the current computed location that will be translated by the container insets.
     * @param currentNode
     *            the current node for which we translate location. We do not change the currentNode bounds.
     */
    private void translateWithInsets(Point locationToTranslate, Node currentNode) {
        NodeQuery nodeQuery = new NodeQuery(currentNode);
        // Border nodes are not concerned by those insets.
        if (!nodeQuery.isBorderedNode()) {
            locationToTranslate.translate(GMFHelper.getContainerTopLeftInsets(currentNode, false));
            if (currentNode.eContainer() instanceof Node container && new ViewQuery(currentNode).isListItem() && container.getChildren().get(0) == currentNode) {
                // This is the first list item, add a one margin border over it.
                locationToTranslate.translate(0, 1);
            }
        }
    }

    private void centerLocationIfZero(Point location, int position, Bounds parentBounds, Bounds gmfBounds) {
        switch (position) {
        case PositionConstants.NORTH, PositionConstants.SOUTH:
            if (location.x == 0) {
                location.setX(location.x + (parentBounds.getWidth() - gmfBounds.getWidth()) / 2);
            }
            break;
        case PositionConstants.WEST, PositionConstants.EAST:
            if (location.y == 0) {
                location.setY(location.y + (parentBounds.getHeight() - gmfBounds.getHeight()) / 2);
            }
            break;
        default:
            break;
        }
    }
    
    /**
     * Get the absolute bounds relative to the origin (Diagram).
     * 
     * @param node
     *            the GMF Node
     * @param insetsAware
     *            true to consider the draw2D figures insets. <strong>Warning:</strong> Those insets are based on the
     *            current Sirius editParts and could become wrong if a developer customizes them.
     * @param boxForConnection
     *            true if we want to have the bounds used to compute connection anchor from source or target, false
     *            otherwise
     * @param recursiveGetBounds
     *            true if this method is called from a parent "getBounds" call, false otherwise.
     * @param adaptBorderNodeLocation
     *            Useful for specific border nodes, like in sequence diagrams, to center the border nodes if the
     *            coordinate is 0 (x for EAST or WEST side, y for NORTH or SOUTH side)
     * 
     * @return the absolute bounds of the node relative to the origin (Diagram)
     */
    public Rectangle getAbsoluteBounds(Node node, boolean insetsAware, boolean boxForConnection, boolean recursiveGetBounds, boolean adaptBorderNodeLocation) {
        Rectangle result;
        if (boundsCache.containsKey(node)) {
            result = boundsCache.get(node);
        } else {
            Point location = getAbsoluteLocation(node, insetsAware, adaptBorderNodeLocation);
            Rectangle bounds = getBounds(node, false, false, boxForConnection, recursiveGetBounds, location);
            result = new PrecisionRectangle(location.preciseX(), location.preciseY(), bounds.preciseWidth(), bounds.preciseHeight());
            if (recursiveGetBounds) {
                boundsCache.put(node, result);
            }
        }
        return result.getCopy();
    }

    /**
     * Get the absolute bounds relative to the origin (Diagram).
     * 
     * @param edge
     *            the GMF Node
     * 
     * @return the absolute bounds of the edge relative to the origin (Diagram)
     */
    public Optional<Rectangle> getAbsoluteBounds(Edge edge) {
        return getAbsoluteBounds(edge, false, false);
    }

    /**
     * Get the absolute bounds relative to the origin (Diagram).
     * 
     * @param edge
     *            the GMF Node
     * @param insetsAware
     *            true to consider the draw2D figures insets. <strong>Warning:</strong> Those insets are based on the
     *            current Sirius editParts and could become wrong if a developer customizes them.
     * @param boxForConnection
     *            true if we want to have the bounds used to compute connection anchor from source or target, false
     *            otherwise
     * 
     * @return the absolute bounds of the edge relative to the origin (Diagram)
     */
    public Optional<Rectangle> getAbsoluteBounds(Edge edge, boolean insetsAware, boolean boxForConnection) {
        // Workaround for canonical refresh about edge on edge
        Optional<Rectangle> optionalSourceBounds = getAbsoluteBounds(edge.getSource(), insetsAware, boxForConnection);
        Optional<Rectangle> optionalTargetBounds = getAbsoluteBounds(edge.getTarget(), insetsAware, boxForConnection);
        if (optionalSourceBounds.isPresent() && optionalTargetBounds.isPresent()) {
            return Optional.ofNullable(optionalSourceBounds.get().union(optionalTargetBounds.get()));
        }
        return Optional.empty();
    }

    /**
     * Get the absolute bounds relative to the origin (Diagram).
     * 
     * @param view
     *            the GMF Node or Edge
     * 
     * @return an optional absolute bounds of the node or edge relative to the origin (Diagram)
     */
    public Optional<Rectangle> getAbsoluteBounds(View view) {
        return getAbsoluteBounds(view, false);
    }

    /**
     * Get the absolute bounds relative to the origin (Diagram).
     * 
     * @param view
     *            the GMF Node or Edge
     * @param insetsAware
     *            true to consider the draw2D figures insets. <strong>Warning:</strong> Those insets are based on the
     *            current Sirius editParts and could become wrong if a developer customizes them.
     * 
     * @return an optional absolute bounds of the node or edge relative to the origin (Diagram)
     */
    public Optional<Rectangle> getAbsoluteBounds(View view, boolean insetsAware) {
        return getAbsoluteBounds(view, insetsAware, false);
    }

    /**
     * Get the absolute bounds relative to the origin (Diagram).
     * 
     * @param view
     *            the GMF Node or Edge
     * @param insetsAware
     *            true to consider the draw2D figures insets. <strong>Warning:</strong> Those insets are based on the
     *            current Sirius editParts and could become wrong if a developer customizes them.
     * @param boxForConnection
     *            true if we want to have the bounds used to compute connection anchor from source or target, false
     *            otherwise
     * 
     * @return an optional absolute bounds of the node or edge relative to the origin (Diagram)
     */
    public Optional<Rectangle> getAbsoluteBounds(View view, boolean insetsAware, boolean boxForConnection) {
        Optional<Rectangle> result = Optional.empty();
        if (view instanceof Node node) {
            result = Optional.ofNullable(getAbsoluteBounds(node, insetsAware, boxForConnection, false, true));
        } else if (view instanceof Edge edge) {
            result = getAbsoluteBounds(edge, insetsAware, boxForConnection);
        }
        return result;
    }

    /**
     * Compute the bounds of a GMF node.
     * 
     * @param node
     *            the node whose bounds to compute.
     * @param useFigureForAutoSizeConstraint
     *            true to use figure for auto size constraint
     * @param forceFigureAutoSize
     *            if useFigureForAutoSizeConstraint and if the found edit part supports it, force auto size and validate
     *            the parent to get the auto-sized dimension (during auto-size for example)
     * @param boxForConnection
     *            true if we want to have the bounds used to compute connection anchor from source or target, false
     *            otherwise
     * @param recursiveGetBounds
     *            true if this method is called from a parent "getBounds" call, false otherwise
     * @param computedAbsoluteLocation
     *            The absolute location of the <code>node</code>
     * 
     * @return the bounds of the node.
     */
    public Rectangle getBounds(Node node, boolean useFigureForAutoSizeConstraint, boolean forceFigureAutoSize, boolean boxForConnection, boolean recursiveGetBounds,
            Point computedAbsoluteLocation) {
        PrecisionRectangle bounds = new PrecisionRectangle(computedAbsoluteLocation.preciseX(), computedAbsoluteLocation.preciseY(), 0, 0);
        LayoutConstraint layoutConstraint = node.getLayoutConstraint();
        EObject element = node.getElement();
        if (element instanceof AbstractDNode abstractDNode) {
            if (layoutConstraint instanceof Size size) {
                bounds.setWidth(size.getWidth());
                bounds.setHeight(size.getHeight());
            } else {
                bounds.setWidth(-1);
                bounds.setHeight(-1);
            }
            ViewQuery viewQuery = new ViewQuery(node);
            if (viewQuery.isForNameEditPart() || viewQuery.isListItem()) {
                if (abstractDNode.getName() == null || abstractDNode.getName().isEmpty()) {
                    if (bounds.width == -1) {
                        bounds.setWidth(0);
                    }
                    if (bounds.height == -1) {
                        bounds.setHeight(0);
                    }
                } else {

                    // Make a default size for label (this size is purely an average estimate)
                    replaceAutoSize(node, bounds, useFigureForAutoSizeConstraint, GMFHelper.getLabelDimension(node, new Dimension(50, 20)), recursiveGetBounds);
                }
            } else {
                replaceAutoSize(node, bounds, useFigureForAutoSizeConstraint, null, recursiveGetBounds);
            }

            if (boxForConnection) {
                // Remove the shadow border size (as it is done in SlidableAnchor.getBox() that calls
                // HandleBounds.getHandleBounds() (for example
                // org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure.getHandleBounds())
                // This insets corresponds to insets of {@link
                // org.eclipse.sirius.diagram.ui.tools.api.figure.AlphaDropShadowBorder#getTransparentInsets()}.
                double shadowBorderSize = GMFHelper.getShadowBorderSize(node);
                bounds.setPreciseWidth(bounds.preciseWidth() - shadowBorderSize);
                bounds.setPreciseHeight(bounds.preciseHeight() - shadowBorderSize);
            }
        }
        return bounds;
    }

    /**
     * This method replace the -1x-1 size with a more realistic size. This size is probably not exactly the same as in
     * draw2d but much closer that -1x-1.
     * 
     * @param node
     *            The GMF node with the auto-size
     * @param bounds
     *            The bounds with auto-size to replace.
     * @param useFigureForAutoSizeConstraint
     *            true to use draw2d figure to get size
     * @param providedDefaultSize
     *            The size used for creation for this kind of <code>node</code>. It is the minimum size.
     * @param recursive
     *            true if this method is called from a "parent" call, false otherwise.
     */
    private void replaceAutoSize(Node node, PrecisionRectangle bounds, boolean useFigureForAutoSizeConstraint, Dimension providedDefaultSize, boolean recursive) {
        if (bounds.width == -1 || bounds.height == -1) {
            Dimension defaultSize = providedDefaultSize;
            if (providedDefaultSize == null) {
                // if there is no default size, we compute it from the given
                // node.
                EObject element = node.getElement();
                ViewQuery nodeQuery = new ViewQuery(node);
                if (nodeQuery.isFreeFormCompartment() || nodeQuery.isListCompartment()) {
                    defaultSize = new Dimension(ResizableCompartmentFigure.MIN_CLIENT_DP, ResizableCompartmentFigure.MIN_CLIENT_DP);
                    if (node.getChildren().isEmpty() && (nodeQuery.isListCompartment() || nodeQuery.isVerticalRegionContainerCompartment() || nodeQuery.isHorizontalRegionContainerCompartment())) {
                        // Add one margin border (even if empty)
                        defaultSize.expand(0, 1);
                    }
                } else if (element instanceof AbstractDNode abstractDNode) {
                    defaultSize = GMFHelper.getDefaultSize(abstractDNode);
                }
            }
            if (useFigureForAutoSizeConstraint) {
                // Use the figure (if founded) to set width and height
                // instead of (-1, -1)
                Optional<GraphicalEditPart> optionalTargetEditPart = GMFHelper.getGraphicalEditPart(node);
                // CHECKSTYLE:OFF
                if (optionalTargetEditPart.isPresent()) {
                    GraphicalEditPart graphicalEditPart = optionalTargetEditPart.get();
                    if (graphicalEditPart instanceof AbstractDiagramElementContainerEditPart abstractDiagramElementContainerEditPart) {
                        abstractDiagramElementContainerEditPart.forceFigureAutosize();
                        ((GraphicalEditPart) graphicalEditPart.getParent()).getFigure().validate();
                    }

                    Rectangle figureBounds = graphicalEditPart.getFigure().getBounds();
                    if (bounds.width == -1) {
                        bounds.setWidth(figureBounds.width);
                    }
                    if (bounds.height == -1) {
                        bounds.setHeight(figureBounds.height);
                    }
                } else {
                    // Diagram editor might be initializing and there is no edit
                    // part yet. For regions we might retrieve the previous
                    // known constraints in the GMF model by looking into the
                    // GMF location of the next region as they were computed
                    // from the location and size of the current region.
                    lookForNextRegionLocation(bounds, node);
                }
                // CHECKSTYLE:ON
            } else {
                // Compute the bounds of all children and use the lowest
                // one (y+height) for height and the rightmost one
                // (x+width) for width plus the margin.
                Rectangle childrenBounds = getChildrenBounds(node, recursive);
                // Add the potential shadow border size and the bottom right insets of the node (i.e. container)
                double shadowBorderSize = GMFHelper.getShadowBorderSize(node);
                Dimension bottomRightInsets = getBottomRightInsets(node);
                // Do not add bottom right insets and shadow if there is at least one border node on the corresponding
                // side
                int borderNodesSides = PositionConstants.NONE;
                if (recursive) {
                    borderNodesSides = getBorderNodesSides(node, childrenBounds);
                }
                boolean isBorderNodeOnRightSide = (PositionConstants.RIGHT & borderNodesSides) == PositionConstants.RIGHT;
                boolean isBorderNodeOnBottomSide = (PositionConstants.BOTTOM & borderNodesSides) == PositionConstants.BOTTOM;
                childrenBounds.resize(isBorderNodeOnRightSide ? 0 : bottomRightInsets.width() + shadowBorderSize, isBorderNodeOnBottomSide ? 0 : bottomRightInsets.height() + shadowBorderSize);
                // Replace -1 by the new computed values
                if (bounds.width == -1) {
                    bounds.setPreciseWidth(defaultSize.preciseWidth());
                    double deltaWidth = childrenBounds.getRight().preciseX() - bounds.getRight().preciseX();
                    if (deltaWidth > 0) {
                        bounds.resize(deltaWidth, 0);
                    }
                }
                if (bounds.height == -1) {
                    bounds.setPreciseHeight(defaultSize.preciseHeight());
                    double deltaHeight = childrenBounds.getBottom().preciseY() - bounds.getBottom().preciseY();
                    if (deltaHeight > 0) {
                        bounds.resize(0, deltaHeight);
                    }
                }
            }

            if (bounds.width == -1) {
                bounds.setWidth(defaultSize.width);
            }
            if (bounds.height == -1) {
                bounds.setHeight(defaultSize.height);
            }
        }
    }

    private int getBorderNodesSides(Node container, Rectangle containerChildrenBounds) {
        int result = PositionConstants.NONE;
        for (ListIterator<View> children = container.getChildren().listIterator(); children.hasNext(); /* */) {
            View child = children.next();
            if (child instanceof Node nodeChild && new NodeQuery(nodeChild).isBorderedNode()) {
                Rectangle borderNodeBounds = getAbsoluteBounds(nodeChild, true, false, false, false);
                if (borderNodeBounds.preciseX() == containerChildrenBounds.preciseX()) {
                    result = result | PositionConstants.LEFT;
                }
                if (borderNodeBounds.preciseY() == containerChildrenBounds.preciseY()) {
                    result = result | PositionConstants.TOP;
                }
                if (borderNodeBounds.preciseX() + borderNodeBounds.preciseWidth() == containerChildrenBounds.preciseX() + containerChildrenBounds.preciseWidth()) {
                    result = result | PositionConstants.RIGHT;
                }
                if (borderNodeBounds.preciseY() + borderNodeBounds.preciseHeight() == containerChildrenBounds.preciseY() + containerChildrenBounds.preciseHeight()) {
                    result = result | PositionConstants.BOTTOM;
                }
            }
        }
        return result;
    }

    private void lookForNextRegionLocation(Rectangle bounds, Node node) {
        EObject element = node.getElement();
        if (element instanceof DDiagramElementContainer ddec && node.eContainer() instanceof Node nodeContainer) {
            DDiagramElementContainerExperimentalQuery query = new DDiagramElementContainerExperimentalQuery(ddec);
            boolean isRegion = query.isRegion();
            EList<?> children = nodeContainer.getChildren();
            int currentIndex = children.indexOf(node);
            if (!(currentIndex != 0 && bounds.equals(new Rectangle(0, 0, -1, -1)))) {
                // We are not in the case of a new region insertion (in this
                // case, we use the default size)
                int nextIndex = currentIndex + 1;
                if (isRegion && nextIndex != 0 && nextIndex < children.size() && children.get(nextIndex) instanceof Node nextNode) {
                    int visualID = SiriusVisualIDRegistry.getVisualID(nextNode.getType());
                    if (DNodeContainer2EditPart.VISUAL_ID == visualID || DNodeListEditPart.VISUAL_ID == visualID || DNodeList2EditPart.VISUAL_ID == visualID) {
                        // DNodeContainerEditPart.VISUAL_ID == visualID is not
                        // checked as a region cannot be a
                        // DNodeContainerEditPart as it is directly contained by
                        // the diagram part.
                        LayoutConstraint layoutConstraint = nextNode.getLayoutConstraint();
                        if (layoutConstraint instanceof Location nextLocation) {
                            // Update only the parent stack direction if some
                            // layout has already been done.
                            if (bounds.width == -1 && query.isRegionInHorizontalStack() && nextLocation.getX() != 0) {
                                bounds.setWidth(nextLocation.getX() - bounds.x);
                            }
                            if (bounds.height == -1 && query.isRegionInVerticalStack() && nextLocation.getY() != 0) {
                                bounds.setHeight(nextLocation.getY() - bounds.y);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Returns a new Point representing the bottom right point of all bounds of children of this Node. Useful for Node
     * with size of -1x-1 to be more accurate (but it is still not necessarily the same size that draw2d).
     * 
     * @param container
     *            the node whose bottom right corner is to compute.
     * @param considerBorderNode
     *            true to consider border nodes when computing the bottom right corner point, false otherwise.
     * 
     * @return Point at the bottom right of the rectangle
     */
    private Rectangle getChildrenBounds(Node container, boolean considerBorderNodes) {
        Rectangle result = null;
        if (container.getChildren().isEmpty()) {
            result = new PrecisionRectangle();
        }
        ViewQuery containerViewQuery = new ViewQuery(container);
        if (containerViewQuery.isListContainer() || containerViewQuery.isListCompartment() || containerViewQuery.isVerticalRegionContainerCompartment()) {
            if (!container.getChildren().isEmpty()) {
                Node lastChild = getLastChild(container, considerBorderNodes);
                result = getAbsoluteBounds(lastChild, true, false, true, false);
            }
        } else {
            for (ListIterator<View> children = container.getChildren().listIterator(); children.hasNext(); /* */) {
                View child = children.next();
                // The border nodes are ignored, except if it is expected to consider it (auto-size of a container with
                // children having border nodes)
                if (child instanceof Node nodeChild && (considerBorderNodes || !(new NodeQuery(nodeChild).isBorderedNode()))) {
                    Rectangle childAbsoluteBounds = getAbsoluteBounds(nodeChild, true, false, true, false);
                    if (result == null) {
                        result = childAbsoluteBounds.getCopy();
                    } else {
                        // Make union of the child bounds and its parent bounds
                        result.union(childAbsoluteBounds);
                    }
                }
            }
        }
        return result;
    }

    private Node getLastChild(Node container, boolean considerBorderNodes) {
        for (int i = container.getChildren().size() - 1; i >= 0; i--) {
            Node currentNode = (Node) container.getChildren().get(i);
            if (considerBorderNodes || !new NodeQuery(currentNode).isBorderedNode()) {
                return currentNode;
            }
        }
        return null;
    }
}
