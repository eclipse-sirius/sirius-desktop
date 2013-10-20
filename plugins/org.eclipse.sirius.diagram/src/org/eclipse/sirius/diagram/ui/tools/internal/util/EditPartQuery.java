/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.IBorderItemOffsets;

/**
 * Queries on GMF edit parts.
 * <p>
 * The class is named <code>EditPartQuery</code> instead of the longer but more
 * accurate <code>IGraphicalEditPartQuery</code> only to avoid an overly long
 * and cumbersome name.
 * 
 * @author pcdavid
 */
public class EditPartQuery {
    private final IGraphicalEditPart part;

    /**
     * Constructor.
     * 
     * @param part
     *            the graphical edit part to query.
     */
    public EditPartQuery(IGraphicalEditPart part) {
        this.part = Preconditions.checkNotNull(part);
    }

    /**
     * Returns the first ancestor (in the hierarchy of edit parts) of this part
     * which is of the specified type.
     * 
     * @param <T>
     *            the type.
     * @param type
     *            the type of the ancestor to look for.
     * @return the closest ancestor of the specified part which is compatible
     *         with <code>type</code>, or <code>null</code> if there is none.
     */
    public <T> T getFirstAncestorOfType(Class<T> type) {
        if (part == null) {
            return null;
        }
        EditPart current = part.getParent();
        while (current != null && !type.isInstance(current)) {
            current = current.getParent();
        }
        return type.cast(current);
    }

    /**
     * Returns all ancestors (in the hierarchy of edit parts) of this part which
     * are of the specified type.
     * 
     * @param <T>
     *            the type.
     * @param type
     *            the type of the ancestor to look for.
     * @return all the ancestors of this part which are compatible with
     *         <code>type</code> from the closest to the farthest.
     */
    public <T> List<T> getAllAncestorsOfType(Class<T> type) {
        if (part == null) {
            return null;
        }
        ArrayList<T> result = Lists.newArrayList();
        EditPart current = part.getParent();
        while (current != null) {
            if (type.isInstance(current)) {
                result.add(type.cast(current));
            }
            current = current.getParent();
        }
        return result;
    }

    /**
     * Return the list of Node corresponding to the BorderItemEditPart that is
     * on the expected side.
     * 
     * @param expectedSide
     *            The side ({@link org.eclipse.draw2d.PositionConstants}) where
     *            the children must be
     * @return the list of Node corresponding to the BorderItemEditPart that is
     *         on the expected side.
     */
    public List<Node> getBorderedNodes(final int expectedSide) {
        List<Node> result = new ArrayList<Node>();
        if (part instanceof IBorderedShapeEditPart) {
            Iterable<IBorderItemEditPart> bordersItemPart = (Iterable<IBorderItemEditPart>) Iterables.filter(part.getChildren(),
                    Predicates.and(Predicates.instanceOf(IBorderItemEditPart.class), new Predicate<IBorderItemEditPart>() {
                        public boolean apply(IBorderItemEditPart input) {
                            int currentSide = input.getBorderItemLocator().getCurrentSideOfParent();
                            return expectedSide == currentSide;
                        }
                    }));
            for (IBorderItemEditPart borderItemEditPart : bordersItemPart) {
                result.add((Node) borderItemEditPart.getModel());
            }
        }
        return result;
    }

    /**
     * Return a Map with a move delta for each nodes that must be moved
     * following the resize of the parent.
     * 
     * @param expectedSide
     *            the side on which the border item appears as defined in
     *            {@link PositionConstants}. Possible values are
     *            <ul>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#EAST}, if
     *            parent is resized from North or South</li>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#WEST}, if
     *            parent is resized from North or South</li>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#NORTH}, if
     *            parent is resized from East or West</li>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#SOUTH}, if
     *            parent is resized from East or West</li>
     *            </ul>
     * @param parentResizeSize
     *            the resize size of the parent
     * @return A Map with a move delta for each nodes that must be moved
     */
    public Map<Node, Integer> getBorderedNodesToMoveWithDelta(int expectedSide, int parentResizeSize) {
        Map<Node, Integer> result = new HashMap<Node, Integer>();
        if (part instanceof IBorderedShapeEditPart) {
            // calculate the parent bounds with consideration for the handle
            // bounds inset.
            IFigure parentFigure = ((IBorderedShapeEditPart) part).getMainFigure();
            Rectangle parentBounds = parentFigure.getBounds();
            if (parentFigure instanceof NodeFigure) {
                parentBounds = ((NodeFigure) parentFigure).getHandleBounds().getCopy();
            }

            List<Node> expectedSideNodes = getBorderedNodes(expectedSide);
            if (parentResizeSize < 0) {
                // The container is reduced
                if (expectedSide == PositionConstants.EAST || expectedSide == PositionConstants.WEST) {
                    // If the parent is reduced from the north (or from the
                    // south and the new height is too small, the bordered node
                    // must be moved to be near the bottom of parent.
                    result = getBorderedNodesToMoveVerticallyWithDelta(expectedSideNodes, parentBounds, parentResizeSize);
                } else if (expectedSide == PositionConstants.NORTH || expectedSide == PositionConstants.SOUTH) {
                    // If the parent is reduced from the east (or from the west
                    // and the new width is too small, the bordered node must be
                    // moved to be near the right of parent.
                    result = getBorderedNodesToMoveHorizontallyWithDelta(expectedSideNodes, parentBounds, parentResizeSize);
                }
            }
        }
        return result;
    }

    /**
     * @param nodes
     *            Nodes potentially concerned by this resize.
     * @param parentBounds
     *            The available bounds to consider for the parent
     * @param parentResizeSize
     *            the resize size of the parent
     */
    private Map<Node, Integer> getBorderedNodesToMoveVerticallyWithDelta(List<Node> nodes, Rectangle parentBounds, int parentResizeSize) {
        Map<Node, Integer> result = new HashMap<Node, Integer>();
        for (Node node : nodes) {
            if (node.getLayoutConstraint() instanceof Bounds) {
                Bounds borderedNodeBounds = (Bounds) node.getLayoutConstraint();
                // Compute the distance between the bottom of the
                // container and the bottom of the bordered node (we
                // add the default_offset because it is used for
                // our DBorderItemLocator).
                int distanceFromBottom = parentBounds.height - IBorderItemOffsets.DEFAULT_OFFSET.height - (borderedNodeBounds.getY() + borderedNodeBounds.getHeight());
                if (distanceFromBottom < -parentResizeSize) {
                    result.put(node, distanceFromBottom + parentResizeSize);
                }
            }
        }
        return result;
    }

    /**
     * @param nodes
     *            Nodes potentially concerned by this resize.
     * @param parentBounds
     *            The available bounds to consider for the parent
     * @param parentResizeSize
     *            the resize size of the parent
     */
    private Map<Node, Integer> getBorderedNodesToMoveHorizontallyWithDelta(List<Node> nodes, Rectangle parentBounds, int parentResizeSize) {
        Map<Node, Integer> result = new HashMap<Node, Integer>();
        for (Node node : nodes) {
            if (node.getLayoutConstraint() instanceof Bounds) {
                Bounds borderedNodeBounds = (Bounds) node.getLayoutConstraint();
                // Compute the distance between the right of the
                // container and the right of the bordered node (we
                // add the default_offset because it is used for
                // our DBorderItemLocator).
                int distanceFromRight = parentBounds.width - IBorderItemOffsets.DEFAULT_OFFSET.height - (borderedNodeBounds.getX() + borderedNodeBounds.getWidth());
                if (distanceFromRight < -parentResizeSize) {
                    result.put(node, distanceFromRight + parentResizeSize);
                }
            }
        }
        return result;
    }
}
