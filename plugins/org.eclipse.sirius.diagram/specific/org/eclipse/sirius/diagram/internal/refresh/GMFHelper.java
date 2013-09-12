/*******************************************************************************
 * Copyright (c) 2011, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.internal.refresh;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.IEditorPart;

import com.google.common.collect.Iterators;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.AbstractDNode;
import org.eclipse.sirius.DNode;
import org.eclipse.sirius.DNodeContainer;
import org.eclipse.sirius.DNodeList;
import org.eclipse.sirius.diagram.business.api.query.NodeQuery;
import org.eclipse.sirius.diagram.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.business.internal.query.DNodeQuery;
import org.eclipse.sirius.diagram.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.internal.refresh.borderednode.CanonicalDBorderItemLocator;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;

/**
 * GMF Helper.
 * 
 * @author edugueperoux
 */
public final class GMFHelper {

    private GMFHelper() {
        // Helper to not instantiate
    }

    /**
     * Get the absolute location relative to the origin (Diagram).
     * 
     * @param node
     *            the GMF Node
     * 
     * @return the absolute location of the node relative to the origin
     *         (Diagram)
     */
    public static Point getAbsoluteLocation(Node node) {
        Node currentNode = node;
        Point absoluteNodeLocation = getLocation(currentNode);
        if (currentNode.eContainer() instanceof Node) {
            currentNode = (Node) currentNode.eContainer();
            Point parentNodeLocation = getAbsoluteLocation(currentNode);
            absoluteNodeLocation.translate(parentNodeLocation);
        }
        return absoluteNodeLocation;
    }

    /**
     * Get the absolute bounds relative to the origin (Diagram).
     * 
     * @param node
     *            the GMF Node
     * 
     * @return the absolute bounds of the node relative to the origin (Diagram)
     */
    public static Rectangle getAbsoluteBounds(Node node) {
        Node currentNode = node;
        Rectangle absoluteNodeBounds = getBounds(currentNode);
        if (currentNode.eContainer() instanceof Node) {
            currentNode = (Node) currentNode.eContainer();
            Point parentNodeLocation = getAbsoluteLocation(currentNode);
            absoluteNodeBounds = absoluteNodeBounds.getTranslated(parentNodeLocation);
        }
        return absoluteNodeBounds;
    }

    /**
     * Get the absolute bounds relative to the origin (Diagram).
     * 
     * @param edge
     *            the GMF Node
     * 
     * @return the absolute bounds of the edge relative to the origin (Diagram)
     */
    public static Option<Rectangle> getAbsoluteBounds(Edge edge) {
        // Workaround for canonical refresh about edge on edge
        Option<Rectangle> optionalSourceBounds = getAbsoluteBounds(edge.getSource());
        Option<Rectangle> optionalTargetBounds = getAbsoluteBounds(edge.getTarget());
        if (optionalSourceBounds.some() && optionalTargetBounds.some()) {
            return Options.newSome(optionalSourceBounds.get().union(optionalTargetBounds.get()));
        }
        return Options.newNone();
    }

    /**
     * Get the absolute bounds relative to the origin (Diagram).
     * 
     * @param view
     *            the GMF Node or Edge
     * 
     * @return an optional absolute bounds of the node or edge relative to the
     *         origin (Diagram)
     */
    public static Option<Rectangle> getAbsoluteBounds(View view) {
        Option<Rectangle> result = Options.newNone();
        if (view instanceof Node) {
            result = Options.newSome(getAbsoluteBounds((Node) view));
        } else if (view instanceof Edge) {
            result = getAbsoluteBounds((Edge) view);
        }
        return result;
    }

    /**
     * Compute the location of a GMF node.
     * 
     * @param node
     *            the node whose location to compute.
     * @return the location of the node.
     */
    public static Point getLocation(Node node) {
        Point location = new Point(0, 0);
        LayoutConstraint layoutConstraint = node.getLayoutConstraint();
        if (layoutConstraint instanceof Bounds) {
            Bounds gmfBounds = (Bounds) layoutConstraint;
            location.x = gmfBounds.getX();
            location.y = gmfBounds.getY();
            // manage location of bordered node with closest side
            if (node.getElement() instanceof DNode && node.getElement().eContainer() instanceof AbstractDNode) {
                DNode dNode = (DNode) node.getElement();
                AbstractDNode parentAbstractDNode = (AbstractDNode) dNode.eContainer();
                if (parentAbstractDNode.getOwnedBorderedNodes().contains(dNode)) {
                    Node parentNode = (Node) node.eContainer();
                    LayoutConstraint parentLayoutConstraint = parentNode.getLayoutConstraint();
                    if (parentLayoutConstraint instanceof Bounds) {
                        Bounds parentBounds = (Bounds) parentLayoutConstraint;
                        int position = CanonicalDBorderItemLocator.findClosestSideOfParent(new Rectangle(gmfBounds.getX(), gmfBounds.getY(), gmfBounds.getWidth(), gmfBounds.getHeight()),
                                new Rectangle(parentBounds.getX(), parentBounds.getY(), parentBounds.getWidth(), parentBounds.getHeight()));
                        updateLocation(location, position, parentBounds, gmfBounds);
                    }
                }
            }
        }
        return location;
    }

    private static void updateLocation(Point location, int position, Bounds parentBounds, Bounds gmfBounds) {
        switch (position) {
        case PositionConstants.NORTH:
        case PositionConstants.SOUTH:
            if (location.x == 0) {
                location.x += (parentBounds.getWidth() - gmfBounds.getWidth()) / 2;
            }
            break;
        case PositionConstants.WEST:
        case PositionConstants.EAST:
            if (location.y == 0) {
                location.y += (parentBounds.getHeight() - gmfBounds.getHeight()) / 2;
            }
            break;
        default:
            break;
        }
    }

    /**
     * Compute the bounds of a GMF node.
     * 
     * @param node
     *            the node whose bounds to compute.
     * @return the bounds of the node.
     */
    public static Rectangle getBounds(Node node) {
        return getBounds(node, false);
    }

    /**
     * Compute the bounds of a GMF node.
     * 
     * @param node
     *            the node whose bounds to compute.
     * @param useFigureForAutoSizeConstraint
     *            true to use figure for auto size constraint
     * @return the bounds of the node.
     */
    public static Rectangle getBounds(Node node, boolean useFigureForAutoSizeConstraint) {
        return getBounds(node, useFigureForAutoSizeConstraint, false);
    }

    /**
     * Compute the bounds of a GMF node.
     * 
     * @param node
     *            the node whose bounds to compute.
     * @param useFigureForAutoSizeConstraint
     *            true to use figure for auto size constraint
     * @param forceFigureAutoSize
     *            if useFigureForAutoSizeConstraint and if the found edit part
     *            supports it, force auto size and validate the parent to get
     *            the auto-sized dimension (during auto-size for exemple)
     * @return the bounds of the node.
     */
    public static Rectangle getBounds(Node node, boolean useFigureForAutoSizeConstraint, boolean forceFigureAutoSize) {
        Rectangle bounds = new Rectangle(0, 0, 0, 0);
        LayoutConstraint layoutConstraint = node.getLayoutConstraint();
        EObject element = node.getElement();
        if (element instanceof AbstractDNode) {
            AbstractDNode abstractDNode = (AbstractDNode) element;
            if (layoutConstraint instanceof Location) {
                bounds.x = ((Location) layoutConstraint).getX();
                bounds.y = ((Location) layoutConstraint).getY();
            }
            if (layoutConstraint instanceof Size) {
                bounds.width = ((Size) layoutConstraint).getWidth();
                bounds.height = ((Size) layoutConstraint).getHeight();
            } else {
                bounds.width = -1;
                bounds.height = -1;
            }

            if (new ViewQuery(node).isForNameEditPart()) {
                if (abstractDNode.getName() == null || abstractDNode.getName().length() == 0) {
                    if (bounds.width == -1) {
                        bounds.width = 0;
                    }
                    if (bounds.height == -1) {
                        bounds.height = 0;
                    }
                } else {

                    // Make a default size for label (this size is purely an
                    // average estimate)
                    replaceAutoSize(node, bounds, useFigureForAutoSizeConstraint, new Dimension(50, 20));
                }
            } else {
                replaceAutoSize(node, bounds, useFigureForAutoSizeConstraint, null);
            }
        }
        return bounds;
    }

    /**
     * This method replace the -1x-1 size with a more realistic size. This size
     * is probably not exactly the same as in draw2d but much closer that -1x-1.
     * 
     * @param node
     *            The GMF node with the auto-size
     * @param bounds
     *            The bounds with auto-size to replace.
     * @param useFigureForAutoSizeConstraint
     *            true to use draw2d figure to get size
     * @param providedDefaultSize
     *            The size used for creation for this kind of <code>node</code>.
     *            It is the minimum size.
     */
    private static void replaceAutoSize(Node node, Rectangle bounds, boolean useFigureForAutoSizeConstraint, Dimension providedDefaultSize) {
        if (bounds.width == -1 || bounds.height == -1) {
            Dimension defaultSize = providedDefaultSize;
            if (providedDefaultSize == null) {
                // if there is no default size, we compute it from the given
                // node.
                EObject element = node.getElement();
                if (element instanceof AbstractDNode) {
                    defaultSize = getDefaultSize((AbstractDNode) element);
                }
            }
            if (useFigureForAutoSizeConstraint) {
                // Use the figure (if founded) to set width and height
                // instead of (-1, -1)
                Option<GraphicalEditPart> optionalTargetEditPart = getGraphicalEditPart(node);
                // CHECKSTYLE:OFF
                if (optionalTargetEditPart.some()) {
                    GraphicalEditPart graphicalEditPart = optionalTargetEditPart.get();
                    if (graphicalEditPart instanceof AbstractDiagramElementContainerEditPart) {
                        ((AbstractDiagramElementContainerEditPart) graphicalEditPart).forceFigureAutosize();
                        ((GraphicalEditPart) graphicalEditPart.getParent()).getFigure().validate();
                    }

                    Rectangle figureBounds = graphicalEditPart.getFigure().getBounds();
                    if (bounds.width == -1) {
                        bounds.width = figureBounds.width;
                    }
                    if (bounds.height == -1) {
                        bounds.height = figureBounds.height;
                    }
                }
                // CHECKSTYLE:ON
            } else {
                // Compute the bounds of all children and use the lowest
                // one (y+height) for height and the rightmost one
                // (x+width) for width.
                Point bottomRight = getBottomRight(node);
                if (bounds.width == -1) {
                    if (bottomRight.x > defaultSize.width) {
                        bounds.width = bottomRight.x;
                    } else {
                        bounds.width = defaultSize.width;
                    }
                }
                if (bounds.height == -1) {
                    if (bottomRight.y > defaultSize.height) {
                        bounds.height = bottomRight.y;
                    } else {
                        bounds.height = defaultSize.height;
                    }
                }
            }

            if (bounds.width == -1) {
                bounds.width = defaultSize.width;
            }
            if (bounds.height == -1) {
                bounds.height = defaultSize.height;
            }
        }
    }

    /**
     * Returns a new Point representing the bottom right point of all bounds of
     * children of this Node. Useful for Node with size of -1x-1 to be more
     * accurate (but it is still not necessarily the same size that draw2d).
     * 
     * @param node
     *            the node whose bottom right corner is to compute.
     * 
     * @return Point at the bottom right of the rectangle
     */
    public static Point getBottomRight(Node node) {
        int right = 0;
        int bottom = 0;
        for (Iterator<Node> children = Iterators.filter(node.getChildren().iterator(), Node.class); children.hasNext(); /* */) {
            Node child = children.next();
            // The bordered nodes is ignored
            if (!(new NodeQuery(node).isBorderedNode())) {
                Rectangle bounds = getBounds(child);
                Point bottomRight = bounds.getBottomRight();
                if (bottomRight.x > right) {
                    right = bottomRight.x;
                }
                if (bottomRight.y > bottom) {
                    bottom = bottomRight.y;
                }
            }
        }
        return new Point(right, bottom);
    }

    private static Dimension getDefaultSize(AbstractDNode abstractDNode) {
        Dimension defaultSize = new Dimension(-1, -1);
        if (abstractDNode instanceof DNode) {
            defaultSize = new DNodeQuery((DNode) abstractDNode).getDefaultDimension();
        } else if (abstractDNode instanceof DNodeContainer || abstractDNode instanceof DNodeList) {
            defaultSize = LayoutUtils.DEFAULT_CONTAINER_DIMENSION;
        }
        return defaultSize;
    }

    /**
     * Return an option with the editPart corresponding to the <code>view</code>
     * in the current diagram or an empty Option if there is no corresponding
     * editPart.
     * 
     * @param view
     *            The view element that is searched
     * @return The optional corresponding edit part.
     */
    private static Option<GraphicalEditPart> getGraphicalEditPart(View view) {
        Option<GraphicalEditPart> result = Options.newNone();
        final IEditorPart editor = EclipseUIUtil.getActiveEditor();
        if (view != null && editor instanceof DiagramEditor) {
            final Map<?, ?> editPartRegistry = ((DiagramEditor) editor).getDiagramGraphicalViewer().getEditPartRegistry();
            final EditPart targetEditPart = (EditPart) editPartRegistry.get(view);
            if (targetEditPart instanceof GraphicalEditPart) {
                result = Options.newSome((GraphicalEditPart) targetEditPart);
            }
        }
        return result;
    }
}
