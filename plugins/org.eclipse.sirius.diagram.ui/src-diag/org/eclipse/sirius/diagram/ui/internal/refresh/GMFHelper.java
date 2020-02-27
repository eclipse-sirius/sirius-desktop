/*******************************************************************************
 * Copyright (c) 2011, 2020 THALES GLOBAL SERVICES and others.
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

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.internal.query.DDiagramElementContainerExperimentalQuery;
import org.eclipse.sirius.diagram.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.NodeQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.business.internal.query.DNodeContainerQuery;
import org.eclipse.sirius.diagram.ui.business.internal.query.DNodeQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeList2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.diagram.ui.internal.refresh.borderednode.CanonicalDBorderItemLocator;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.IContainerLabelOffsets;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.LabelBorderStyleIds;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorPart;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

/**
 * GMF Helper.
 * 
 * @author edugueperoux
 */
public final class GMFHelper {

    /**
     * see org.eclipse.sirius.diagram.ui.internal.edit.parts. AbstractDNodeContainerCompartmentEditPart.DEFAULT_MARGIN
     * the Y value is the DEFAULT_MARGIN + the InvisibleResizableCompartmentFigure top Inset (1px)
     */
    private static Point CONTAINER_INSETS = new Point(AbstractDNodeContainerCompartmentEditPart.DEFAULT_MARGIN, IContainerLabelOffsets.LABEL_OFFSET);

    /**
     * The gap in pixels between the Label's icon and its text
     * (org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel.getIconTextGap()).
     */
    private static final int ICON_TEXT_GAP = 3;

    private GMFHelper() {
        // Helper to not instantiate
    }

    /**
     * Get the absolute location relative to the origin (Diagram).
     * 
     * @param node
     *            the GMF Node
     * 
     * @return the absolute location of the node relative to the origin (Diagram)
     */
    public static Point getAbsoluteLocation(Node node) {
        return getAbsoluteLocation(node, false);
    }

    /**
     * Get the absolute location relative to the origin (Diagram).
     * 
     * @param node
     *            the GMF Node
     * @param insetsAware
     *            true to consider the draw2D figures insets. <strong>Warning:</strong> Those insets are based on the
     *            current Sirius editParts and could become wrong if a developer customizes them.
     * 
     * @return the absolute location of the node relative to the origin (Diagram)
     */
    public static Point getAbsoluteLocation(Node node, boolean insetsAware) {
        Node currentNode = node;
        Point absoluteNodeLocation = getLocation(currentNode);
        if (currentNode.eContainer() instanceof Node) {
            currentNode = (Node) currentNode.eContainer();
            Point parentNodeLocation = getAbsoluteLocation(currentNode, insetsAware);
            absoluteNodeLocation.translate(parentNodeLocation);
            if (insetsAware) {
                translateWithInsets(absoluteNodeLocation, node);
            }
        }
        return absoluteNodeLocation;
    }

    /**
     * Return the top-left insets of the container of this <code>node</code>. The insets also considers its border.
     * 
     * @param node
     *            The current node
     * @param searchFirstParentContainer
     *            true to call recursively until finding a Node container, {@link NodeQuery#isContainer()}, false
     *            otherwise
     * @return the top-left insets of the container of this <code>node</code>
     */
    public static Dimension getContainerTopLeftInsets(Node node, boolean searchFirstParentContainer) {
        Dimension result = new Dimension(0, 0);
        EObject nodeContainer = node.eContainer();
        if (nodeContainer instanceof Node) {
            Node parentNode = (Node) nodeContainer;
            NodeQuery nodeQuery = new NodeQuery(parentNode);
            if (nodeQuery.isContainer()) {
                EObject element = parentNode.getElement();
                if (element instanceof DDiagramElementContainer) {
                    DDiagramElementContainer ddec = (DDiagramElementContainer) element;
                    // RegionContainer do not have containers insets
                    if (ddec instanceof DNodeContainer) {
                        if (new DNodeContainerExperimentalQuery((DNodeContainer) ddec).isRegionContainer() || hasFullLabelBorder(ddec)) {
                            result.setHeight(CONTAINER_INSETS.y + getLabelSize(parentNode) + AbstractDiagramElementContainerEditPart.DEFAULT_SPACING);
                        } else {
                            result.setWidth(CONTAINER_INSETS.x);
                            result.setHeight(CONTAINER_INSETS.y);
                        }
                    }
                    Dimension borderSize = getBorderSize(ddec);
                    result.setWidth(result.width() + borderSize.width());
                    result.setHeight(result.height() + borderSize.height());
                }
            } else if (searchFirstParentContainer) {
                result = getContainerTopLeftInsets(parentNode, searchFirstParentContainer);
            }
        }
        return result;
    }

    /**
     * Return the top-left insets of the container of this <code>node</code> that is after the label. The insets also
     * considers its border.
     * 
     * @param node
     *            The current node
     * @param searchFirstParentContainer
     *            true to call recursively until finding a Node container, {@link NodeQuery#isContainer()}, false
     *            otherwise
     * @return the top-left insets of the container of this <code>node</code>
     */
    public static Dimension getContainerTopLeftInsetsAfterLabel(Node node, boolean searchFirstParentContainer) {
        Dimension result = new Dimension(0, 0);
        EObject nodeContainer = node.eContainer();
        if (nodeContainer instanceof Node) {
            Node parentNode = (Node) nodeContainer;
            NodeQuery nodeQuery = new NodeQuery(parentNode);
            if (nodeQuery.isContainer()) {
                EObject element = parentNode.getElement();
                if (element instanceof DDiagramElementContainer) {
                    result.setWidth(CONTAINER_INSETS.x);
                    result.setHeight(CONTAINER_INSETS.y);

                    Dimension borderSize = getBorderSize((DDiagramElementContainer) element);
                    result.setWidth(result.width() + borderSize.width());
                    result.setHeight(result.height() + borderSize.height());
                }
            } else if (searchFirstParentContainer) {
                result = getContainerTopLeftInsets(parentNode, searchFirstParentContainer);
            }
        }
        return result;
    }

    /**
     * Get the border size of the <code>ddec</code> ({@link DDiagramElementContainer}).
     * 
     * @param ddec
     *            The {@link DDiagramElementContainer}
     * @return the border size of the diagram element container.
     */
    public static Dimension getBorderSize(DDiagramElementContainer ddec) {
        Dimension result = new Dimension(0, 0);
        ContainerStyle containerStyle = ddec.getOwnedStyle();
        int borderSize = containerStyle.getBorderSize().intValue();
        DDiagramElementContainerExperimentalQuery regionQuery = new DDiagramElementContainerExperimentalQuery(ddec);
        if (regionQuery.isRegionInHorizontalStack()) {
            result.setWidth(isFirstRegion(ddec) ? 0 : borderSize);
            result.setHeight(1);
        } else if (regionQuery.isRegionInVerticalStack()) {
            result.setWidth(1);
            result.setHeight(isFirstRegion(ddec) ? 1 : borderSize);
        } else {
            result.setWidth(borderSize);
            result.setHeight(borderSize);
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
    private static void translateWithInsets(Point locationToTranslate, Node currentNode) {
        NodeQuery nodeQuery = new NodeQuery(currentNode);
        // bordered node are not concerned by those insets.
        if (!nodeQuery.isBorderedNode()) {
            locationToTranslate.translate(getContainerTopLeftInsets(currentNode, false));
        }
    }

    private static boolean hasFullLabelBorder(DDiagramElementContainer ddec) {
        Option<LabelBorderStyleDescription> labelBorderStyle = new DDiagramElementContainerExperimentalQuery(ddec).getLabelBorderStyle();
        return labelBorderStyle.some() && LabelBorderStyleIds.LABEL_FULL_BORDER_STYLE_FOR_CONTAINER_ID.equals(labelBorderStyle.get().getId());
    }

    private static int getLabelSize(Node parentNode) {
        int labelSize = 0;
        for (Node child : Iterables.filter(parentNode.getVisibleChildren(), Node.class)) {
            if (new ViewQuery(child).isForNameEditPart()) {
                // TODO Compute the real label height
                // It depends on the font size
                // It might require to set the layout constraint of the label
                // GMF node which will not be used by the
                // ConstrainedToolbarLayout to locate the label but might be
                // usefull to store the value in the model.
                labelSize = 16;
                break;
            }
        }
        return labelSize;
    }

    private static boolean isFirstRegion(DDiagramElementContainer ddec) {
        EObject potentialRegionContainer = ddec.eContainer();
        if (potentialRegionContainer instanceof DNodeContainer) {
            Iterable<DDiagramElementContainer> regions = Iterables.filter(((DNodeContainer) potentialRegionContainer).getOwnedDiagramElements(), DDiagramElementContainer.class);
            return !Iterables.isEmpty(regions) && ddec == Iterables.getFirst(regions, null);
        }
        return false;
    }

    /**
     * Shift the current node absolute bounds location by the container insets.
     * 
     * @param boundsToTranslate
     *            the current computed bounds that will be translated by the container insets.
     * @param currentNode
     *            the current node for which we translate bounds. We do not change the currentNode bounds.
     */
    private static void translateWithInsets(Rectangle boundsToTranslate, Node currentNode) {
        Point location = boundsToTranslate.getLocation();
        translateWithInsets(location, currentNode);
        boundsToTranslate.setLocation(location);
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
     * Get the absolute bounds relative to the origin (Diagram).
     * 
     * @param node
     *            the GMF Node
     * 
     * @return the absolute bounds of the node relative to the origin (Diagram)
     */
    public static Rectangle getAbsoluteBounds(Node node) {
        return getAbsoluteBounds(node, false);
    }

    /**
     * Get the absolute bounds relative to the origin (Diagram).
     * 
     * @param node
     *            the GMF Node
     * @param insetsAware
     *            true to consider the draw2D figures insets. <strong>Warning:</strong> Those insets are based on the
     *            current Sirius editParts and could become wrong if a developer customizes them.
     * 
     * @return the absolute bounds of the node relative to the origin (Diagram)
     */
    public static Rectangle getAbsoluteBounds(Node node, boolean insetsAware) {
        Node currentNode = node;
        Rectangle absoluteNodeBounds = getBounds(currentNode);
        if (currentNode.eContainer() instanceof Node) {
            currentNode = (Node) currentNode.eContainer();
            Point parentNodeLocation = getAbsoluteLocation(currentNode, insetsAware);
            absoluteNodeBounds = absoluteNodeBounds.getTranslated(parentNodeLocation);
            if (insetsAware) {
                translateWithInsets(absoluteNodeBounds, node);
            }
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
        return getAbsoluteBounds(edge, false);
    }

    /**
     * Get the absolute bounds relative to the origin (Diagram).
     * 
     * @param edge
     *            the GMF Node
     * @param insetsAware
     *            true to consider the draw2D figures insets. <strong>Warning:</strong> Those insets are based on the
     *            current Sirius editParts and could become wrong if a developer customizes them.
     * 
     * @return the absolute bounds of the edge relative to the origin (Diagram)
     */
    public static Option<Rectangle> getAbsoluteBounds(Edge edge, boolean insetsAware) {
        // Workaround for canonical refresh about edge on edge
        Option<Rectangle> optionalSourceBounds = getAbsoluteBounds(edge.getSource(), insetsAware);
        Option<Rectangle> optionalTargetBounds = getAbsoluteBounds(edge.getTarget(), insetsAware);
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
     * @return an optional absolute bounds of the node or edge relative to the origin (Diagram)
     */
    public static Option<Rectangle> getAbsoluteBounds(View view) {
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
    public static Option<Rectangle> getAbsoluteBounds(View view, boolean insetsAware) {
        Option<Rectangle> result = Options.newNone();
        if (view instanceof Node) {
            result = Options.newSome(getAbsoluteBounds((Node) view, insetsAware));
        } else if (view instanceof Edge) {
            result = getAbsoluteBounds((Edge) view, insetsAware);
        }
        return result;
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
     *            if useFigureForAutoSizeConstraint and if the found edit part supports it, force auto size and validate
     *            the parent to get the auto-sized dimension (during auto-size for example)
     * @return the bounds of the node.
     */
    public static Rectangle getBounds(Node node, boolean useFigureForAutoSizeConstraint, boolean forceFigureAutoSize) {
        PrecisionRectangle bounds = new PrecisionRectangle(0, 0, 0, 0);
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

                    // Make a default size for label (this size is purely an average estimate)
                    replaceAutoSize(node, bounds, useFigureForAutoSizeConstraint, getLabelDimension(node, new Dimension(50, 20)));
                }
            } else {
                replaceAutoSize(node, bounds, useFigureForAutoSizeConstraint, null);
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

    private static void lookForNextRegionLocation(Rectangle bounds, Node node) {
        EObject element = node.getElement();
        if (element instanceof DDiagramElementContainer && node.eContainer() instanceof Node) {
            DDiagramElementContainer ddec = (DDiagramElementContainer) element;
            DDiagramElementContainerExperimentalQuery query = new DDiagramElementContainerExperimentalQuery(ddec);
            boolean isRegion = query.isRegion();
            EList children = ((Node) node.eContainer()).getChildren();
            int currentIndex = children.indexOf(node);
            if (!(currentIndex != 0 && bounds.equals(new Rectangle(0, 0, -1, -1)))) {
                // We are not in the case of a new region insertion (in this
                // case, we use the default size)
                int nextIndex = currentIndex + 1;
                if (isRegion && nextIndex != 0 && nextIndex < children.size() && children.get(nextIndex) instanceof Node) {
                    Node nextNode = (Node) children.get(nextIndex);
                    int visualID = SiriusVisualIDRegistry.getVisualID(nextNode.getType());
                    if (DNodeContainer2EditPart.VISUAL_ID == visualID || DNodeListEditPart.VISUAL_ID == visualID || DNodeList2EditPart.VISUAL_ID == visualID) {
                        // DNodeContainerEditPart.VISUAL_ID == visualID is not
                        // checked as a region cannot be a
                        // DNodeContainerEditPart as it is directly contained by
                        // the diagram part.
                        LayoutConstraint layoutConstraint = nextNode.getLayoutConstraint();
                        if (layoutConstraint instanceof Location) {
                            Location nextLocation = (Location) layoutConstraint;
                            // Update only the parent stack direction if some
                            // layout has already been done.
                            if (bounds.width == -1 && query.isRegionInHorizontalStack() && nextLocation.getX() != 0) {
                                bounds.width = nextLocation.getX() - bounds.x;
                            }
                            if (bounds.height == -1 && query.isRegionInVerticalStack() && nextLocation.getY() != 0) {
                                bounds.height = nextLocation.getY() - bounds.y;
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
        } else if (abstractDNode instanceof DNodeContainer) {
            defaultSize = new DNodeContainerQuery((DNodeContainer) abstractDNode).getDefaultDimension();
        } else if (abstractDNode instanceof DNodeList) {
            defaultSize = LayoutUtils.NEW_DEFAULT_CONTAINER_DIMENSION;
        }
        return defaultSize;
    }

    /**
     * Return an option with the editPart corresponding to the <code>view</code> in the current diagram or an empty
     * Option if there is no corresponding editPart.
     * 
     * @param view
     *            The view element that is searched
     * @return The optional corresponding edit part.
     */
    public static Option<GraphicalEditPart> getGraphicalEditPart(View view) {
        if (view != null) {
            Diagram gmfDiagram = view.getDiagram();
            // Try the active editor first (most likely case in practice)
            IEditorPart editor = EclipseUIUtil.getActiveEditor();
            if (isEditorFor(editor, gmfDiagram)) {
                return getGraphicalEditPart(view, (DiagramEditor) editor);
            } else if (gmfDiagram.getElement() instanceof DDiagram) {
                // Otherwise check all active Sirius editors
                for (IEditingSession uiSession : SessionUIManager.INSTANCE.getUISessions()) {
                    DialectEditor dialectEditor = uiSession.getEditor((DDiagram) gmfDiagram.getElement());
                    if (isEditorFor(dialectEditor, gmfDiagram)) {
                        return getGraphicalEditPart(view, (DiagramEditor) dialectEditor);
                    }
                }
            }
        }
        return Options.<GraphicalEditPart> newNone();
    }

    private static boolean isEditorFor(IEditorPart editor, Diagram diagram) {
        return editor instanceof DiagramEditor && ((DiagramEditor) editor).getDiagram() == diagram;
    }

    /**
     * Return an option with the editPart corresponding to the <code>view</code> in the current diagram or an empty
     * Option if there is no corresponding editPart.
     * 
     * @param view
     *            The view element that is searched
     * @param editor
     *            the editor where looking for the edit part.
     * @return The optional corresponding edit part.
     */
    public static Option<GraphicalEditPart> getGraphicalEditPart(View view, DiagramEditor editor) {
        Option<GraphicalEditPart> result = Options.newNone();
        final Map<?, ?> editPartRegistry = editor.getDiagramGraphicalViewer().getEditPartRegistry();
        final EditPart targetEditPart = (EditPart) editPartRegistry.get(view);
        if (targetEditPart instanceof GraphicalEditPart) {
            result = Options.newSome((GraphicalEditPart) targetEditPart);
        }
        return result;
    }

    /**
     * Get the points list computed from GMF bendpoints according to source side for the <code>edgeEditPart</code>.
     * 
     * @param edgeEditPart
     *            The concerned edge edit part.
     * @return Points list
     * @throws IllegalArgumentException
     *             when the edgeEditPart is not as expected
     */
    public static List<Point> getPointsFromSource(ConnectionEditPart edgeEditPart) throws IllegalArgumentException {
        if (edgeEditPart.getModel() instanceof Edge && edgeEditPart.getFigure() instanceof Connection) {
            List<Point> result = new ArrayList<>();
            Edge gmfEdge = (Edge) edgeEditPart.getModel();
            Connection connectionFigure = (Connection) edgeEditPart.getFigure();
            Point srcAnchorLoc = connectionFigure.getSourceAnchor().getReferencePoint();
            connectionFigure.translateToRelative(srcAnchorLoc);

            RelativeBendpoints bp = (RelativeBendpoints) gmfEdge.getBendpoints();
            for (int i = 0; i < bp.getPoints().size(); i++) {
                RelativeBendpoint rbp = (RelativeBendpoint) bp.getPoints().get(i);
                Point fromSrc = srcAnchorLoc.getTranslated(rbp.getSourceX(), rbp.getSourceY());
                result.add(fromSrc);
            }
            return result;
        }
        throw new IllegalArgumentException(Messages.GMFHelper_invalidEdgeModelAndFigure);
    }

    /**
     * Get the points list computed from GMF bendpoints according to target side for the <code>edgeEditPart</code>.
     * 
     * @param edgeEditPart
     *            The concerned edge edit part.
     * @return Points list
     * @throws IllegalArgumentException
     *             when the edgeEditPart is not as expected
     */
    public static List<Point> getPointsFromTarget(ConnectionEditPart edgeEditPart) throws IllegalArgumentException {
        if (edgeEditPart.getModel() instanceof Edge && edgeEditPart.getFigure() instanceof Connection) {
            List<Point> result = new ArrayList<>();
            Edge gmfEdge = (Edge) edgeEditPart.getModel();
            Connection connectionFigure = (Connection) edgeEditPart.getFigure();
            Point tgtAnchorLoc = connectionFigure.getTargetAnchor().getReferencePoint();
            connectionFigure.translateToRelative(tgtAnchorLoc);

            RelativeBendpoints bp = (RelativeBendpoints) gmfEdge.getBendpoints();
            for (int i = 0; i < bp.getPoints().size(); i++) {
                RelativeBendpoint rbp = (RelativeBendpoint) bp.getPoints().get(i);
                Point fromTgt = tgtAnchorLoc.getTranslated(rbp.getTargetX(), rbp.getTargetY());
                result.add(fromTgt);
            }
            return result;
        }
        throw new IllegalArgumentException(Messages.GMFHelper_invalidEdgeModelAndFigure);
    }

    /**
     * Compute the size of a label. This method uses "UI data" to retrieve the label size. It must be called in UI
     * thread to have right result, otherwise <code>defaultDimension</code> will be used.
     * 
     * @param node
     *            the node, corresponding to a DNodeNameEditPart, whose size to compute.
     * @param defaultDimension
     *            the default dimension to use if it is not possible to get a "real" size
     * @return the size of the label.
     */
    public static Dimension getLabelDimension(Node node, Dimension defaultDimension) {
        Dimension labelSize = defaultDimension;
        ViewQuery viewQuery = new ViewQuery(node);
        EObject element = node.getElement();
        if (element instanceof DDiagramElement) {
            DDiagramElement dDiagramElement = (DDiagramElement) element;
            org.eclipse.sirius.viewpoint.Style siriusStyle = dDiagramElement.getStyle();
            if (!new DDiagramElementQuery(dDiagramElement).isLabelHidden()) {
                if (siriusStyle instanceof BasicLabelStyle) {
                    BasicLabelStyle bls = (BasicLabelStyle) siriusStyle;
                    Font defaultFont = VisualBindingManager.getDefault().getFontFromLabelStyle(bls, (String) viewQuery.getDefaultValue(NotationPackage.Literals.FONT_STYLE__FONT_NAME));
                    try {
                        labelSize = FigureUtilities.getStringExtents(dDiagramElement.getName(), defaultFont);
                        if (bls.isShowIcon()) {
                            // Also consider the icon size
                            Dimension iconDimension = getIconDimension(dDiagramElement, bls);
                            labelSize.setHeight(Math.max(labelSize.height(), iconDimension.height));
                            labelSize.setWidth(labelSize.width() + ICON_TEXT_GAP + iconDimension.width);
                        }
                    } catch (SWTException e) {
                        // Probably an "Invalid thread access" (FigureUtilities
                        // creates a new Shell to compute the label size). So in
                        // this case, we use the default size.
                    }
                }
            }
        }
        return labelSize;
    }

    private static Dimension getIconDimension(DSemanticDecorator dSemanticDecorator, BasicLabelStyle bls) {
        ImageDescriptor descriptor = null;
        EObject target = dSemanticDecorator.getTarget();
        if (!StringUtil.isEmpty(bls.getIconPath())) {
            String iconPath = bls.getIconPath();
            final File imageFile = FileProvider.getDefault().getFile(new Path(iconPath));
            if (imageFile != null && imageFile.exists() && imageFile.canRead()) {
                try {
                    descriptor = DiagramUIPlugin.Implementation.findImageDescriptor(imageFile.toURI().toURL());
                } catch (MalformedURLException e) {
                    // Do nothing here
                }
            }
        } else if (target != null) {
            final IItemLabelProvider labelProvider = (IItemLabelProvider) DiagramUIPlugin.getPlugin().getItemProvidersAdapterFactory().adapt(target, IItemLabelProvider.class);
            if (labelProvider != null) {
                descriptor = ExtendedImageRegistry.getInstance().getImageDescriptor(labelProvider.getImage(target));
            }
        }

        if (descriptor == null) {
            descriptor = ImageDescriptor.getMissingImageDescriptor();
        }
        Image icon = DiagramUIPlugin.getPlugin().getImage(descriptor);
        return new Dimension(icon.getBounds().width, icon.getBounds().height);
    }
}
