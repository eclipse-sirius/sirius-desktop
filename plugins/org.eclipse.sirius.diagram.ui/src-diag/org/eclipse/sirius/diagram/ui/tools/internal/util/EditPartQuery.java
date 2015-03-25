/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.ViewportUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.ui.tools.api.figure.locator.DBorderItemLocator;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

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
        for (IBorderItemEditPart borderItemEditPart : getBorderNodeEditParts(expectedSide)) {
            result.add((Node) borderItemEditPart.getModel());
        }
        return result;
    }

    /**
     * Return the list of {@link BorderItemEditPart}s that are on the expected
     * side.
     * 
     * @param expectedSide
     *            The side ({@link org.eclipse.draw2d.PositionConstants}) where
     *            the children must be
     * @return the list of {@link BorderItemEditPart}s that are on the expected
     *         side.
     */
    public List<IBorderItemEditPart> getBorderNodeEditParts(final int expectedSide) {
        if (part instanceof IBorderedShapeEditPart) {
            Iterable<IBorderItemEditPart> bordersItemPart = Iterables.filter(part.getChildren(), Predicates.and(Predicates.instanceOf(IBorderItemEditPart.class), new Predicate<IBorderItemEditPart>() {
                @Override
                public boolean apply(IBorderItemEditPart input) {
                    int currentSide = input.getBorderItemLocator().getCurrentSideOfParent();
                    return expectedSide == currentSide;
                }
            }));
            return Lists.newArrayList(bordersItemPart);
        }
        return new ArrayList<IBorderItemEditPart>();
    }

    /**
     * Return a Map with a move delta for each nodes that must be moved
     * following the resize of the parent. The delta is to applied on GMF
     * location of border nodes (relative to container).
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
     * @param resizedSide
     *            the side that is moved as defined in {@link PositionConstants}
     *            . Possible values are
     *            <ul>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#EAST}, if
     *            parent is resized from East</li>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#WEST}, if
     *            parent is resized from West</li>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#NORTH}, if
     *            parent is resized from North</li>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#SOUTH}, if
     *            parent is resized from South</li>
     *            </ul>
     * @param parentResizeSize
     *            the resize size of the parent
     * @return A Map with a move delta for each nodes that must be moved
     */
    public Map<Node, Dimension> getBorderedNodesToMoveWithDelta(int expectedSide, int resizedSide, int parentResizeSize) {
        Map<Node, Dimension> result = new HashMap<Node, Dimension>();
        if (parentResizeSize < 0 && part instanceof IBorderedShapeEditPart) {
            // The container is reduced
            List<IBorderItemEditPart> expectedSideEditParts = getBorderNodeEditParts(expectedSide);
            if (expectedSideEditParts.size() > 0) {
                // Calculate the parent bounds with consideration for the handle
                // bounds insets.
                IFigure parentFigure = ((IBorderedShapeEditPart) part).getBorderedFigure();
                Rectangle parentBounds = parentFigure.getBounds();
                if (parentFigure instanceof NodeFigure) {
                    parentBounds = ((NodeFigure) parentFigure).getHandleBounds().getCopy();
                }
                // Sort edit parts according to the resized side (the shortest
                // border nodes should be in first).
                Set<IBorderItemEditPart> sortedEditParts = sortEditParts(expectedSideEditParts, resizedSide);
                if (expectedSide == PositionConstants.EAST || expectedSide == PositionConstants.WEST) {
                    // If the parent is reduced from the north (or from the
                    // south and the new height is too small, the bordered node
                    // must be moved to be near the bottom of parent.
                    result = getBorderedNodesToMoveVerticallyWithDelta(sortedEditParts, parentBounds, resizedSide, parentResizeSize);
                } else if (expectedSide == PositionConstants.NORTH || expectedSide == PositionConstants.SOUTH) {
                    // If the parent is reduced from the east (or from the west
                    // and the new width is too small, the bordered node must be
                    // moved to be near the right of parent.
                    result = getBorderedNodesToMoveHorizontallyWithDelta(sortedEditParts, parentBounds, resizedSide, parentResizeSize);
                }
            }

        }
        return result;
    }

    /**
     * Return true if the figure of the current part is currently visible (by
     * the end-user), false otherwise.
     * 
     * @return true if the figure of the current part is currently visible (by
     *         the end-user), false otherwise.
     */
    @SuppressWarnings("unchecked")
    public boolean isVisibleOnViewport() {
        // Traverse the viewport path of the figure (and reduce clipRect
        // to what is actually visible); process all viewports up to the
        // root viewport
        Viewport topViewport = ((FigureCanvas) part.getViewer().getControl()).getViewport();
        IFigure figure = part.getFigure();
        Viewport nearestEnclosingViewport = ViewportUtilities.getNearestEnclosingViewport(figure);
        List<Viewport> enclosingViewportsPath;
        if (topViewport.equals(nearestEnclosingViewport)) {
            enclosingViewportsPath = Lists.newArrayList(topViewport);
        } else {
            enclosingViewportsPath = ViewportUtilities.getViewportsPath(nearestEnclosingViewport, topViewport, true);
        }
        Rectangle clipRect = getAbsoluteBoundsAsCopy(figure);
        clipAtViewports(clipRect, enclosingViewportsPath);
        return !clipRect.isEmpty();
    }

    /**
     * Clips the given clipRect at all given viewports. Method copied from
     * {@link org.eclipse.draw2d.ViewportAwareConnectionLayerClippingStrategy}.
     * 
     * @param clipRect
     *            Rectangle to clip
     * @param enclosingViewportsPath
     *            All viewports to use to clip
     */
    protected void clipAtViewports(Rectangle clipRect, List<Viewport> enclosingViewportsPath) {
        for (Viewport viewport : enclosingViewportsPath) {
            clipRect.intersect(getAbsoluteViewportAreaAsCopy(viewport));
        }
    }

    /**
     * Returns the area covered by the viewport in absolute coordinates. Method
     * copied from
     * {@link org.eclipse.draw2d.ViewportAwareConnectionLayerClippingStrategy}.
     * 
     * @param viewport
     *            Concerned Viewport
     * @return the area covered by the viewport in absolute coordinates.
     */
    protected Rectangle getAbsoluteViewportAreaAsCopy(Viewport viewport) {
        return getAbsoluteClientAreaAsCopy(viewport);
    }

    /**
     * Returns the viewport's client area in absolute coordinates. Method copied
     * from
     * {@link org.eclipse.draw2d.ViewportAwareConnectionLayerClippingStrategy}.
     * 
     * @param figure
     *            Concerned figure
     * @return a copy of the client area of this figure
     */
    protected Rectangle getAbsoluteClientAreaAsCopy(IFigure figure) {
        Rectangle absoluteClientArea = figure.getClientArea();
        figure.translateToParent(absoluteClientArea);
        figure.translateToAbsolute(absoluteClientArea);
        return absoluteClientArea;
    }

    /**
     * Returns the figure's bounds in absolute coordinates. Method copied from
     * {@link org.eclipse.draw2d.ViewportAwareConnectionLayerClippingStrategy}.
     * 
     * @param figure
     *            Concerned figure
     * @return the figure's bounds in absolute coordinates
     */
    protected Rectangle getAbsoluteBoundsAsCopy(IFigure figure) {
        Rectangle absoluteFigureBounds = figure.getBounds().getCopy();
        figure.translateToAbsolute(absoluteFigureBounds);
        return absoluteFigureBounds;
    }

    /**
     * Check that the container of the <code>part</code> is layouted with
     * "FreeForm" style.
     * 
     * @return true if the the container of the <code>part</code> is layouted
     *         with "FreeForm" style, false otherwise.
     */
    public boolean isFreeFormContainerChildrenPresentation() {
        boolean isFreeForm = true;
        EObject eContainer = part.resolveSemanticElement().eContainer();
        if (eContainer instanceof DNodeList) {
            isFreeForm = false;
        } else if (eContainer instanceof DNodeContainer) {
            DNodeContainer container = (DNodeContainer) eContainer;
            ContainerLayout childrenPresentation = container.getChildrenPresentation();
            if (!childrenPresentation.equals(ContainerLayout.FREE_FORM)) {
                isFreeForm = false;
            }
        }
        return isFreeForm;
    }

    /**
     * @param editParts
     *            Edit parts potentially concerned by this resize.
     * @param parentBounds
     *            The available bounds to consider for the parent
     * @param resizedSide
     *            the side that is moved as defined in {@link PositionConstants}
     *            . Possible values are
     *            <ul>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#NORTH}, if
     *            parent is resized from North</li>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#SOUTH}, if
     *            parent is resized from South</li>
     *            </ul>
     * @param parentResizeSize
     *            the resize size of the parent
     */
    private Map<Node, Dimension> getBorderedNodesToMoveVerticallyWithDelta(Set<IBorderItemEditPart> editParts, Rectangle parentBounds, int resizedSide, int parentResizeSize) {
        Map<IBorderItemEditPart, Dimension> defaultComputedShifting = new LinkedHashMap<IBorderItemEditPart, Dimension>();
        Map<Node, Dimension> shiftingAccordingToBorderItemLocator = new LinkedHashMap<Node, Dimension>();
        for (IBorderItemEditPart editPart : editParts) {
            Node node = (Node) editPart.getModel();
            if (node.getLayoutConstraint() instanceof Bounds) {
                Bounds borderedNodeBounds = (Bounds) node.getLayoutConstraint();
                if (PositionConstants.NORTH == resizedSide) {
                    // Check if the distance from top will be enough after
                    // resize. If not, this border node must be shift to 0 for y
                    // axis. If there are several nodes at 0, they will be fixed
                    // just after with the BorderItemLocator.
                    if (borderedNodeBounds.getY() < -parentResizeSize) {
                        defaultComputedShifting.put(editPart, new Dimension(0, -borderedNodeBounds.getY()));
                    }
                } else {
                    // Compute the distance between the bottom of the
                    // container and the bottom of the bordered node (we
                    // add the default_offset because it is used for
                    // our DBorderItemLocator).
                    int distanceFromBottom = parentBounds.height - IBorderItemOffsets.DEFAULT_OFFSET.height - (borderedNodeBounds.getY() + borderedNodeBounds.getHeight());
                    // Check if the distance from bottom will be enough after
                    // resize. If not, this border node must be shift to the
                    // bottom of the parent. If there are several nodes at the
                    // bottom of the parent, they will be fixed just after with
                    // the BorderItemLocator.
                    if (distanceFromBottom < -parentResizeSize) {
                        defaultComputedShifting.put(editPart, new Dimension(0, distanceFromBottom + parentResizeSize));
                    }
                }
            }
        }
        // Fix the expected shifting according to the BorderItemLocator (to
        // avoid conflicts)
        HashMap<IGraphicalEditPart, IFigure> partToFigureToIgnore = Maps.newHashMap();
        for (IBorderItemEditPart editPart : defaultComputedShifting.keySet()) {
            partToFigureToIgnore.put(editPart, editPart.getFigure());
        }
        for (IBorderItemEditPart borderItemEditPart : defaultComputedShifting.keySet()) {
            // We must check that there is no conflicts with
            // existing border nodes
            Rectangle currentBounds = borderItemEditPart.getFigure().getBounds();
            Rectangle expectedNewBounds = currentBounds.getCopy();
            expectedNewBounds.setX(expectedNewBounds.x + defaultComputedShifting.get(borderItemEditPart).width);
            expectedNewBounds.setY(expectedNewBounds.y + defaultComputedShifting.get(borderItemEditPart).height);
            IBorderItemLocator borderItemLocator = borderItemEditPart.getBorderItemLocator();
            if (borderItemLocator instanceof DBorderItemLocator) {
                // Temporary resize the parent figure for valid location
                // computation
                Rectangle borderItemLocatorParentBounds = ((DBorderItemLocator) borderItemLocator).getParentFigure().getBounds();
                if (PositionConstants.NORTH == resizedSide) {
                    borderItemLocatorParentBounds.y = borderItemLocatorParentBounds.y - parentResizeSize;
                    expectedNewBounds.y = expectedNewBounds.y - parentResizeSize;
                }
                borderItemLocatorParentBounds.height = borderItemLocatorParentBounds.height + parentResizeSize;
                expectedNewBounds = ((DBorderItemLocator) borderItemLocator).getValidLocation(expectedNewBounds, borderItemEditPart.getFigure(), partToFigureToIgnore.values(),
                        new ArrayList<IFigure>());
                // Reset the temporary size of the parent figure
                if (PositionConstants.NORTH == resizedSide) {
                    borderItemLocatorParentBounds.y = borderItemLocatorParentBounds.y + parentResizeSize;
                }
                borderItemLocatorParentBounds.height = borderItemLocatorParentBounds.height - parentResizeSize;

            } else {
                expectedNewBounds = borderItemLocator.getValidLocation(expectedNewBounds, borderItemEditPart.getFigure());
            }
            if (PositionConstants.NORTH == resizedSide) {
                shiftingAccordingToBorderItemLocator.put((Node) borderItemEditPart.getModel(),
                        new Dimension(expectedNewBounds.x - currentBounds.x, expectedNewBounds.y - currentBounds.y + parentResizeSize));
            } else {
                shiftingAccordingToBorderItemLocator.put((Node) borderItemEditPart.getModel(), new Dimension(expectedNewBounds.x - currentBounds.x, expectedNewBounds.y - currentBounds.y));
            }
            // Directly set the figure to be considered by next border item edit
            // part (if any)
            borderItemEditPart.getFigure().getBounds().x = expectedNewBounds.x;
            borderItemEditPart.getFigure().getBounds().y = expectedNewBounds.y;
            // Remove the located figure
            partToFigureToIgnore.remove(borderItemEditPart);
        }
        return shiftingAccordingToBorderItemLocator;
    }

    /**
     * @param editParts
     *            Edit parts potentially concerned by this resize.
     * @param parentBounds
     *            The available bounds to consider for the parent
     * @param resizedSide
     *            the side that is moved as defined in {@link PositionConstants}
     *            . Possible values are
     *            <ul>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#EAST}, if
     *            parent is resized from East</li>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#WEST}, if
     *            parent is resized from West</li>
     *            </ul>
     * @param parentResizeSize
     *            the resize size of the parent
     */
    private Map<Node, Dimension> getBorderedNodesToMoveHorizontallyWithDelta(Set<IBorderItemEditPart> editParts, Rectangle parentBounds, int resizedSide, int parentResizeSize) {
        Map<IBorderItemEditPart, Dimension> defaultComputedShifting = new LinkedHashMap<IBorderItemEditPart, Dimension>();
        Map<Node, Dimension> shiftingAccordingToBorderItemLocator = new LinkedHashMap<Node, Dimension>();
        for (IBorderItemEditPart editPart : editParts) {
            Node node = (Node) editPart.getModel();
            if (node.getLayoutConstraint() instanceof Bounds) {
                Bounds borderedNodeBounds = (Bounds) node.getLayoutConstraint();
                if (PositionConstants.WEST == resizedSide) {
                    // Check if the distance from left will be enough after
                    // resize. If not, this border node must be shift to 0 for x
                    // axis. If there are several nodes at 0, they will be fixed
                    // just after with the BorderItemLocator.
                    if (borderedNodeBounds.getX() < -parentResizeSize) {
                        defaultComputedShifting.put(editPart, new Dimension(-borderedNodeBounds.getX(), 0));
                    }
                } else {
                    // Compute the distance between the right of the
                    // container and the right of the bordered node (we
                    // add the default_offset because it is used for
                    // our DBorderItemLocator).
                    int distanceFromRight = parentBounds.width - IBorderItemOffsets.DEFAULT_OFFSET.height - (borderedNodeBounds.getX() + borderedNodeBounds.getWidth());
                    // Check if the distance from right will be enough after
                    // resize. If not, this border node must be shift to the
                    // right of the parent. If there are several nodes at the
                    // right of the parent, they will be fixed just after with
                    // the BorderItemLocator.
                    if (distanceFromRight < -parentResizeSize) {
                        defaultComputedShifting.put(editPart, new Dimension(distanceFromRight + parentResizeSize, 0));
                    }
                }
            }
        }
        // Fix the expected shifting according to the borderItemLocator (to
        // avoid conflicts)
        HashMap<IGraphicalEditPart, IFigure> partToFigureToIgnore = Maps.newHashMap();
        for (IBorderItemEditPart editPart : defaultComputedShifting.keySet()) {
            partToFigureToIgnore.put(editPart, editPart.getFigure());
        }
        for (IBorderItemEditPart borderItemEditPart : defaultComputedShifting.keySet()) {
            // We must check that there is no conflicts with
            // existing border nodes
            Rectangle currentBounds = borderItemEditPart.getFigure().getBounds();
            Rectangle expectedNewBounds = currentBounds.getCopy();
            expectedNewBounds.setX(expectedNewBounds.x + defaultComputedShifting.get(borderItemEditPart).width);
            expectedNewBounds.setY(expectedNewBounds.y + defaultComputedShifting.get(borderItemEditPart).height);
            IBorderItemLocator borderItemLocator = borderItemEditPart.getBorderItemLocator();
            if (borderItemLocator instanceof DBorderItemLocator) {
                // Temporary resize the parent figure for valid location
                // computation
                Rectangle borderItemLocatorParentBounds = ((DBorderItemLocator) borderItemLocator).getParentFigure().getBounds();
                if (PositionConstants.WEST == resizedSide) {
                    borderItemLocatorParentBounds.x = borderItemLocatorParentBounds.x - parentResizeSize;
                    expectedNewBounds.x = expectedNewBounds.x - parentResizeSize;
                }
                borderItemLocatorParentBounds.width = borderItemLocatorParentBounds.width + parentResizeSize;
                expectedNewBounds = ((DBorderItemLocator) borderItemLocator).getValidLocation(expectedNewBounds, borderItemEditPart.getFigure(), partToFigureToIgnore.values(),
                        new ArrayList<IFigure>());
                // Reset the temporary size of the parent figure
                if (PositionConstants.WEST == resizedSide) {
                    borderItemLocatorParentBounds.x = borderItemLocatorParentBounds.x + parentResizeSize;
                }
                borderItemLocatorParentBounds.width = borderItemLocatorParentBounds.width - parentResizeSize;
            } else {
                expectedNewBounds = borderItemLocator.getValidLocation(expectedNewBounds, borderItemEditPart.getFigure());
            }
            if (PositionConstants.WEST == resizedSide) {
                shiftingAccordingToBorderItemLocator.put((Node) borderItemEditPart.getModel(),
                        new Dimension(expectedNewBounds.x - currentBounds.x + parentResizeSize, expectedNewBounds.y - currentBounds.y));
            } else {
                shiftingAccordingToBorderItemLocator.put((Node) borderItemEditPart.getModel(), new Dimension(expectedNewBounds.x - currentBounds.x, expectedNewBounds.y - currentBounds.y));
            }
            // Directly set the figure to be considered by next border item edit
            // part (if any)
            borderItemEditPart.getFigure().getBounds().x = expectedNewBounds.x;
            borderItemEditPart.getFigure().getBounds().y = expectedNewBounds.y;
            // Remove the located figure
            partToFigureToIgnore.remove(borderItemEditPart);
        }
        return shiftingAccordingToBorderItemLocator;
    }

    /**
     * Sort the {@link IBorderItemEditPart} according to the resized side.
     * 
     * @param nodes
     *            List of {@link IBorderItemEditPart} to sort
     * @param resizedSide
     *            the side that is moved as defined in {@link PositionConstants}
     *            . Possible values are
     *            <ul>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#EAST}, if
     *            parent is resized from East</li>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#WEST}, if
     *            parent is resized from West</li>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#NORTH}, if
     *            parent is resized from North</li>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#SOUTH}, if
     *            parent is resized from South</li>
     *            </ul>
     * @return A new sorted list
     */
    private Set<IBorderItemEditPart> sortEditParts(List<IBorderItemEditPart> nodes, int resizedSide) {
        Function<IBorderItemEditPart, Integer> getValueToCompareFunction;
        if (PositionConstants.NORTH == resizedSide) {
            // Smaller y in first
            getValueToCompareFunction = new Function<IBorderItemEditPart, Integer>() {
                @Override
                public Integer apply(IBorderItemEditPart from) {
                    Node node = (Node) from.getModel();
                    if (node.getLayoutConstraint() instanceof Bounds) {
                        Bounds nodeBounds = (Bounds) node.getLayoutConstraint();
                        return nodeBounds.getY();
                    }
                    return 0;
                }
            };
        } else if (PositionConstants.SOUTH == resizedSide) {
            // Greater (y+height) in first
            getValueToCompareFunction = new Function<IBorderItemEditPart, Integer>() {
                @Override
                public Integer apply(IBorderItemEditPart from) {
                    Node node = (Node) from.getModel();
                    if (node.getLayoutConstraint() instanceof Bounds) {
                        Bounds nodeBounds = (Bounds) node.getLayoutConstraint();
                        return -(nodeBounds.getY() + nodeBounds.getHeight());
                    }
                    return 0;
                }
            };
        } else if (PositionConstants.EAST == resizedSide) {
            // Greater (x+width) in first
            getValueToCompareFunction = new Function<IBorderItemEditPart, Integer>() {
                @Override
                public Integer apply(IBorderItemEditPart from) {
                    Node node = (Node) from.getModel();
                    if (node.getLayoutConstraint() instanceof Bounds) {
                        Bounds nodeBounds = (Bounds) node.getLayoutConstraint();
                        return -(nodeBounds.getX() + nodeBounds.getWidth());
                    }
                    return 0;
                }
            };
        } else {
            // Smaller x in first
            getValueToCompareFunction = new Function<IBorderItemEditPart, Integer>() {
                @Override
                public Integer apply(IBorderItemEditPart from) {
                    Node node = (Node) from.getModel();
                    if (node.getLayoutConstraint() instanceof Bounds) {
                        Bounds nodeBounds = (Bounds) node.getLayoutConstraint();
                        return nodeBounds.getX();
                    }
                    return 0;
                }
            };
        }
        Ordering<IBorderItemEditPart> ordering = Ordering.natural().onResultOf(getValueToCompareFunction);
        return ImmutableSortedSet.orderedBy(ordering).addAll(nodes).build();
    }
}
