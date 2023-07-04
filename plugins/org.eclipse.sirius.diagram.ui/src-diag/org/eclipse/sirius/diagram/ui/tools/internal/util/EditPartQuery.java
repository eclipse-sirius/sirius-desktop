/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.util;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.ViewportUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ResizableCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.WorkspaceViewerProperties;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.DrawerStyle;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.figure.locator.DBorderItemLocator;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

/**
 * Queries on GMF edit parts.
 * <p>
 * The class is named <code>EditPartQuery</code> instead of the longer but more accurate
 * <code>IGraphicalEditPartQuery</code> only to avoid an overly long and cumbersome name.
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
        this.part = Objects.requireNonNull(part);
    }

    /**
     * Returns the first ancestor (in the hierarchy of edit parts) of this part which is of the specified type.
     * 
     * @param <T>
     *            the type.
     * @param type
     *            the type of the ancestor to look for.
     * @return the closest ancestor of the specified part which is compatible with <code>type</code>, or
     *         <code>null</code> if there is none.
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
     * Returns all ancestors (in the hierarchy of edit parts) of this part which are of the specified type.
     * 
     * @param <T>
     *            the type.
     * @param type
     *            the type of the ancestor to look for.
     * @return all the ancestors of this part which are compatible with <code>type</code> from the closest to the
     *         farthest.
     */
    public <T> List<T> getAllAncestorsOfType(Class<T> type) {
        if (part == null) {
            return null;
        }
        ArrayList<T> result = new ArrayList<>();
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
     * Return the list of Node corresponding to the BorderItemEditPart that is on the expected side.
     * 
     * @param expectedSide
     *            The side ({@link org.eclipse.draw2d.PositionConstants}) where the children must be
     * @return the list of Node corresponding to the BorderItemEditPart that is on the expected side.
     */
    public List<Node> getBorderedNodes(final int expectedSide) {
        List<Node> result = new ArrayList<Node>();
        for (IBorderItemEditPart borderItemEditPart : getBorderNodeEditParts(expectedSide)) {
            result.add((Node) borderItemEditPart.getModel());
        }
        return result;
    }

    /**
     * Return the list of {@link BorderItemEditPart}s that are on the expected side.
     * 
     * @param expectedSide
     *            The side ({@link org.eclipse.draw2d.PositionConstants}) where the children must be
     * @return the list of {@link BorderItemEditPart}s that are on the expected side.
     */
    public List<IBorderItemEditPart> getBorderNodeEditParts(final int expectedSide) {
        if (part instanceof IBorderedShapeEditPart) {
            return part.getChildren().stream()
                    .filter(child -> child instanceof IBorderItemEditPart borderItem &&  borderItem.getBorderItemLocator().getCurrentSideOfParent() == expectedSide)
                    .filter(IBorderItemEditPart.class::isInstance)
                    .map(IBorderItemEditPart.class::cast)
                    .toList();
        }
        return new ArrayList<>();
    }

    /**
     * Return a Map with a move delta for each nodes that must be moved following the resize of the parent. The delta is
     * to applied on GMF location of border nodes (relative to container).
     * 
     * @param expectedSide
     *            the side on which the border item appears as defined in {@link PositionConstants}. Possible values are
     *            <ul>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#EAST}, if parent is resized from North or South</li>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#WEST}, if parent is resized from North or South</li>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#NORTH}, if parent is resized from East or West</li>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#SOUTH}, if parent is resized from East or West</li>
     *            </ul>
     * @param resizedSide
     *            the side that is moved as defined in {@link PositionConstants} . Possible values are
     *            <ul>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#EAST}, if parent is resized from East</li>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#WEST}, if parent is resized from West</li>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#NORTH}, if parent is resized from North</li>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#SOUTH}, if parent is resized from South</li>
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
     * Return true if the figure of the current part is currently visible (by the end-user), false otherwise.
     * 
     * @return true if the figure of the current part is currently visible (by the end-user), false otherwise.
     */
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
     * Returns the area covered by the viewport in absolute coordinates. Method copied from
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
     * Returns the viewport's client area in absolute coordinates. Method copied from
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
     * Check that the container of the <code>part</code> is layouted with "FreeForm" style.
     * 
     * @return true if the the container of the <code>part</code> is layouted with "FreeForm" style, false otherwise.
     */
    public boolean isFreeFormContainerChildrenPresentation() {
        boolean isFreeForm = true;
        EObject semElt = part.resolveSemanticElement();
        if (semElt == null) {
            isFreeForm = false;
        } else {
            EObject eContainer = semElt.eContainer();
            if (eContainer instanceof DNodeList) {
                isFreeForm = false;
            } else if (eContainer instanceof DNodeContainer) {
                DNodeContainer container = (DNodeContainer) eContainer;
                ContainerLayout childrenPresentation = container.getChildrenPresentation();
                if (!childrenPresentation.equals(ContainerLayout.FREE_FORM)) {
                    isFreeForm = false;
                }
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
     *            the side that is moved as defined in {@link PositionConstants} . Possible values are
     *            <ul>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#NORTH}, if parent is resized from North</li>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#SOUTH}, if parent is resized from South</li>
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
        HashMap<IGraphicalEditPart, IFigure> partToFigureToIgnore = new HashMap<>();
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
                    borderItemLocatorParentBounds.setY(borderItemLocatorParentBounds.y - parentResizeSize);
                    expectedNewBounds.setY(expectedNewBounds.y - parentResizeSize);
                }
                borderItemLocatorParentBounds.setHeight(borderItemLocatorParentBounds.height + parentResizeSize);
                expectedNewBounds = ((DBorderItemLocator) borderItemLocator).getValidLocation(expectedNewBounds, borderItemEditPart.getFigure(), partToFigureToIgnore.values(),
                        new ArrayList<IFigure>());
                // Reset the temporary size of the parent figure
                if (PositionConstants.NORTH == resizedSide) {
                    borderItemLocatorParentBounds.setY(borderItemLocatorParentBounds.y + parentResizeSize);
                }
                borderItemLocatorParentBounds.setHeight(borderItemLocatorParentBounds.height - parentResizeSize);

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
            borderItemEditPart.getFigure().getBounds().setX(expectedNewBounds.x);
            borderItemEditPart.getFigure().getBounds().setY(expectedNewBounds.y);
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
     *            the side that is moved as defined in {@link PositionConstants} . Possible values are
     *            <ul>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#EAST}, if parent is resized from East</li>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#WEST}, if parent is resized from West</li>
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
        HashMap<IGraphicalEditPart, IFigure> partToFigureToIgnore = new HashMap<>();
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
                    borderItemLocatorParentBounds.setX(borderItemLocatorParentBounds.x - parentResizeSize);
                    expectedNewBounds.setX(expectedNewBounds.x - parentResizeSize);
                }
                borderItemLocatorParentBounds.setWidth(borderItemLocatorParentBounds.width + parentResizeSize);
                expectedNewBounds = ((DBorderItemLocator) borderItemLocator).getValidLocation(expectedNewBounds, borderItemEditPart.getFigure(), partToFigureToIgnore.values(),
                        new ArrayList<IFigure>());
                // Reset the temporary size of the parent figure
                if (PositionConstants.WEST == resizedSide) {
                    borderItemLocatorParentBounds.setX(borderItemLocatorParentBounds.x + parentResizeSize);
                }
                borderItemLocatorParentBounds.setWidth(borderItemLocatorParentBounds.width - parentResizeSize);
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
            borderItemEditPart.getFigure().getBounds().setX(expectedNewBounds.x);
            borderItemEditPart.getFigure().getBounds().setY(expectedNewBounds.y);
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
     *            the side that is moved as defined in {@link PositionConstants} . Possible values are
     *            <ul>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#EAST}, if parent is resized from East</li>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#WEST}, if parent is resized from West</li>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#NORTH}, if parent is resized from North</li>
     *            <li>{@link org.eclipse.draw2d.PositionConstants#SOUTH}, if parent is resized from South</li>
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

    /**
     * Test if the current is collapsed. Collapse is enabled only on List Regions in vertical stacks.
     * 
     * @return true if the part is collapsed.
     */
    public boolean isCollapsed() {
        DrawerStyle drawerStyle = getDrawerStyle();
        return drawerStyle != null && drawerStyle.isCollapsed();
    }

    /**
     * Return the drawer style when collapse is enabled on the current part. Collapse is enabled only on List Regions in
     * vertical stacks.
     * 
     * @return the drawer style when collapse is enabled on the current part.
     */
    public DrawerStyle getDrawerStyle() {
        if (part instanceof AbstractDiagramElementContainerEditPart && part.getNotationView() != null && ((AbstractDiagramElementContainerEditPart) part).isRegion()) {
            for (Node child : Iterables.filter(part.getNotationView().getChildren(), Node.class)) {
                DrawerStyle drawerStyle = (DrawerStyle) child.getStyle(NotationPackage.eINSTANCE.getDrawerStyle());
                if (drawerStyle != null) {
                    return drawerStyle;
                }
            }
        }
        return null;
    }

    /**
     * Check if the given part is used in a diagram in layouting mode.
     * 
     * @return true if the given part is used in a diagram in layouting mode, false otherwise.
     */
    public boolean isInLayoutingMode() {
        View notationView = part.getNotationView();
        if (notationView != null) {
            Diagram diagram = notationView.getDiagram();
            if (diagram != null) {
                EObject element = diagram.getElement();
                return element instanceof DDiagram && ((DDiagram) element).isIsInLayoutingMode();
            }
        }
        return false;
    }

    /**
     * Check if the given part is used in a diagram in show/hide mode.
     * 
     * @return true if the given part is used in a diagram in show/hide mode, false otherwise.
     */
    public boolean isInShowingMode() {
        View notationView = part.getNotationView();
        if (notationView != null) {
            Diagram diagram = notationView.getDiagram();
            if (diagram != null) {
                EObject element = diagram.getElement();
                return element instanceof DDiagram && ((DDiagram) element).isIsInShowingMode();
            }
        }
        return false;
    }

    /**
     * Provides the corrected location according to the snap.
     * 
     * @param request
     *            the request to update location.
     * @param proposedLocation
     *            the proposed location.
     * @return the corrected location.
     */
    public Point getSnapLocation(Request request, Point proposedLocation) {
        if (isSnapToGridEnabled()) {
            Object helper = part.getAdapter(SnapToHelper.class);
            if (helper instanceof SnapToHelper) {
                PrecisionPoint preciseLocation = new PrecisionPoint(proposedLocation);
                PrecisionPoint result = new PrecisionPoint(proposedLocation);
                ((SnapToHelper) helper).snapPoint(request, PositionConstants.HORIZONTAL | PositionConstants.VERTICAL, preciseLocation, result);
                return result.getCopy();
            }
        }
        return proposedLocation;
    }

    /**
     * Return true if snapToGrid is enabled for the diagram containing the current part.
     * 
     * @return true if snapToGrid is enabled for the diagram containing the current part.
     */
    public boolean isSnapToGridEnabled() {
        EditPartViewer editPartViewer = part.getViewer();
        if (editPartViewer instanceof DiagramGraphicalViewer) {
            IPreferenceStore preferenceStore = ((DiagramGraphicalViewer) editPartViewer).getWorkspaceViewerPreferenceStore();
            if (preferenceStore != null) {
                return preferenceStore.getBoolean(WorkspaceViewerProperties.SNAPTOGRID);
            }
        }
        return false;
    }

    /**
     * Given a {@link Point}, this method computes on which border it is. If it is not on a border it returns
     * PositionConstants.NONE.
     * 
     * @param point
     *            the point to locate
     * @return the side where this point is located
     */
    public int getSideOfLocation(Point point) {
        Rectangle bounds = part.getFigure().getBounds();
        int side;
        if (bounds.getTop().y == point.y) {
            side = PositionConstants.NORTH;
        } else if (bounds.getRight().x == point.x) {
            side = PositionConstants.EAST;
        } else if (bounds.getBottom().y == point.y) {
            side = PositionConstants.SOUTH;
        } else if (bounds.getLeft().x == point.x) {
            side = PositionConstants.WEST;
        } else {
            side = PositionConstants.NONE;
        }
        return side;
    }

    /**
     * This methods returns the location of the center of a side.
     * 
     * @param positionConstant
     *            asked side
     * @return centered location
     */
    public Point getCenterOfSide(int positionConstant) {
        Rectangle bounds = part.getFigure().getBounds();
        Point center;
        switch (positionConstant) {
        case PositionConstants.NORTH:
            center = bounds.getTop();
            break;
        case PositionConstants.EAST:
            center = bounds.getRight();
            break;
        case PositionConstants.SOUTH:
            center = bounds.getBottom();
            break;
        case PositionConstants.WEST:
            center = bounds.getLeft();
            break;
        default:
            center = bounds.getCenter();
        }
        return center;
    }

    /**
     * Verify if this edit part is auto-sized.
     * 
     * @param checkWidth
     *            true if this method must verify that width is auto-sized, false otherwise.
     * @param checkHeight
     *            true if this method must verify that height is auto-sized, false otherwise.
     * @return true if the width and/or the height are auto-sized (according to the parameter value)
     */
    public boolean isAutoSized(final boolean checkWidth, final boolean checkHeight) {
        boolean isAutoSized = false;
        if (part instanceof AbstractDiagramContainerEditPart && ((AbstractDiagramContainerEditPart) part).isRegionContainer()) {
            // Edit part is a region container: We collect compartment children.
            // The region container is considered as auto-sized if all children is auto-sized.
            isAutoSized = ((AbstractDiagramContainerEditPart) part).getResizableCompartments().stream()
                    .allMatch(resizableCompartment -> new EditPartQuery((IGraphicalEditPart) resizableCompartment).isAutoSized(checkWidth, checkHeight));
        } else if (part instanceof ResizableCompartmentEditPart && part.getParent() instanceof AbstractDiagramContainerEditPart
                && ((AbstractDiagramContainerEditPart) part.getParent()).isRegionContainer()) {
            // Edit part is the ResizableCompartmentEditPart of a region container
            isAutoSized = isAutoSized((ResizableCompartmentEditPart) part, checkWidth, checkHeight);
        } else {
            Integer width = (Integer) part.getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Width());
            Integer height = (Integer) part.getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height());
            isAutoSized = (!checkWidth || width == -1) && (!checkHeight || height == -1);
        }
        return isAutoSized;
    }

    private boolean isAutoSized(ResizableCompartmentEditPart resizableCompartmentEditPart, boolean checkWidth, boolean checkHeight) {
        return resizableCompartmentEditPart.getChildren().stream().filter(AbstractDiagramElementContainerEditPart.class::isInstance).allMatch(el -> {
            Integer containerWidth = (Integer) ((AbstractDiagramElementContainerEditPart) el).getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Width());
            Integer containerHeight = (Integer) ((AbstractDiagramElementContainerEditPart) el).getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height());
            return (!checkWidth || containerWidth.intValue() == -1) && (!checkHeight || containerHeight.intValue() == -1);
        });
    }

    /**
     * Verify if this edit part is auto-sized.
     * 
     * @return true if the width and the height are auto-sized
     */
    public boolean isAutoSized() {
        return isAutoSized(true, true);
    }

    /**
     * Looks for the VISUAL_ID filed of a {@link IGraphicalEditPart}. This is used for variables typed as an abstract
     * edit part type but handling {@link IGraphicalEditPart} with visual IDs.
     * 
     * @return the content of the VISUAL_ID field if there is one, -1 otherwise.
     */
    public int getVisualID() {
        try {
            Field visualIDfield = part.getClass().getField("VISUAL_ID"); //$NON-NLS-1$
            return (Integer) visualIDfield.get(part);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            DiagramUIPlugin.getPlugin().error(MessageFormat.format(Messages.AbstractDEdgeNameEditPart_VisualID_Error, part), e);
        }
        return -1;
    }
}
