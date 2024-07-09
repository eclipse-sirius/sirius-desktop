/*******************************************************************************
 * Copyright (c) 2011, 2024 THALES GLOBAL SERVICES and others.
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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
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
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.model.business.internal.query.DDiagramElementContainerExperimentalQuery;
import org.eclipse.sirius.diagram.model.business.internal.query.DNodeContainerExperimentalQuery;
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
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AlphaDropShadowBorder;
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
     * the top value is the DEFAULT_MARGIN + the InvisibleResizableCompartmentFigure top Inset (1px)
     */
    private static Insets CONTAINER_INSETS = new Insets(IContainerLabelOffsets.LABEL_OFFSET, AbstractDNodeContainerCompartmentEditPart.DEFAULT_MARGIN,
            AbstractDNodeContainerCompartmentEditPart.DEFAULT_MARGIN, AbstractDNodeContainerCompartmentEditPart.DEFAULT_MARGIN);

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
     * Return the top-left insets of this <code>container</code>. The insets also considers its border.
     * 
     * @param container
     *            The container for which we wish to have the insets. This {@link Node} must correspond to a container,
     *            otherwise, {0,0} is returned
     * @return the top-left insets of this <code>container</code>
     */
    public static Dimension getTopLeftInsets(Node container) {
        Dimension result = new Dimension(0, 0);
        NodeQuery nodeQuery = new NodeQuery(container);
        if (nodeQuery.isContainer()) {
            EObject element = container.getElement();
            if (element instanceof DDiagramElementContainer) {
                DDiagramElementContainer ddec = (DDiagramElementContainer) element;
                // RegionContainer do not have containers insets
                if (ddec instanceof DNodeContainer) {
                    if (new DNodeContainerExperimentalQuery((DNodeContainer) ddec).isRegionContainer() || hasFullLabelBorder(ddec)) {
                        result.setHeight(CONTAINER_INSETS.top + getLabelSize(container) + AbstractDiagramElementContainerEditPart.DEFAULT_SPACING);
                    } else {
                        result.setWidth(CONTAINER_INSETS.left);
                        result.setHeight(CONTAINER_INSETS.top);
                    }
                }
                Dimension borderSize = getBorderSize(ddec);
                result.setWidth(result.width() + borderSize.width());
                result.setHeight(result.height() + borderSize.height());
            }
        }
        return result;
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
                result = getTopLeftInsets(parentNode);
            } else if (searchFirstParentContainer) {
                result = getContainerTopLeftInsets(parentNode, searchFirstParentContainer);
            }
        }
        return result;
    }

    /**
     * Return the bottom-right insets of the container of this <code>node</code>. The insets also considers its border.
     * 
     * @param container
     *            The container for which we wish to have the insets. This {@link Node} must correspond to a container,
     *            otherwise, {0,0} is returned
     * @return the bottom-right insets of this <code>container</code>
     */
    private static Dimension getBottomRightInsets(Node container) {
        Dimension result = new Dimension(0, 0);
        NodeQuery nodeQuery = new NodeQuery(container);
        if (nodeQuery.isContainer()) {
            EObject element = container.getElement();
            if (element instanceof DDiagramElementContainer) {
                DDiagramElementContainer ddec = (DDiagramElementContainer) element;
                // RegionContainer do not have containers insets
                if (ddec instanceof DNodeContainer) {
                    if (new DNodeContainerExperimentalQuery((DNodeContainer) ddec).isRegionContainer() || hasFullLabelBorder(ddec)) {
                        // TODO : Not sure about that, to verify
                        result.setHeight(CONTAINER_INSETS.bottom);
                    } else {
                        result.setWidth(CONTAINER_INSETS.right);
                        result.setHeight(CONTAINER_INSETS.bottom);
                    }
                }
                Dimension borderSize = getBorderSize(ddec);
                // Added twice as this insets is used to compute the "global" size including the border
                result.setWidth(result.width() + (borderSize.width() * 2));
                result.setHeight(result.height() + (borderSize.height() * 2));
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
                    result.setWidth(CONTAINER_INSETS.left);
                    result.setHeight(CONTAINER_INSETS.top);

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
        int borderSize = 0;
        ContainerStyle containerStyle = ddec.getOwnedStyle();
        if (containerStyle != null && containerStyle.getBorderSize() != null) {
            borderSize = containerStyle.getBorderSize().intValue();
        }
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
        // Border nodes are not concerned by those insets.
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
            location.setX(gmfBounds.getX());
            location.setY(gmfBounds.getY());
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
                location.setX(location.x + (parentBounds.getWidth() - gmfBounds.getWidth()) / 2);
            }
            break;
        case PositionConstants.WEST:
        case PositionConstants.EAST:
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
     * 
     * @return the absolute bounds of the node relative to the origin (Diagram)
     */
    public static Rectangle getAbsoluteBounds(Node node) {
        return getAbsoluteBounds(node, false, false);
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
        return getAbsoluteBounds(node, insetsAware, false);
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
     * @return the absolute bounds of the node relative to the origin (Diagram)
     */
    public static Rectangle getAbsoluteBounds(Node node, boolean insetsAware, boolean boxForConnection) {
        Node currentNode = node;
        Rectangle absoluteNodeBounds = getBounds(currentNode, false, false, boxForConnection, false);
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
    public static Option<Rectangle> getAbsoluteBounds(Edge edge, boolean insetsAware, boolean boxForConnection) {
        // Workaround for canonical refresh about edge on edge
        Option<Rectangle> optionalSourceBounds = getAbsoluteBounds(edge.getSource(), insetsAware, boxForConnection);
        Option<Rectangle> optionalTargetBounds = getAbsoluteBounds(edge.getTarget(), insetsAware, boxForConnection);
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
    public static Option<Rectangle> getAbsoluteBounds(View view, boolean insetsAware, boolean boxForConnection) {
        Option<Rectangle> result = Options.newNone();
        if (view instanceof Node) {
            result = Options.newSome(getAbsoluteBounds((Node) view, insetsAware, boxForConnection));
        } else if (view instanceof Edge) {
            result = getAbsoluteBounds((Edge) view, insetsAware, boxForConnection);
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
        return getBounds(node, useFigureForAutoSizeConstraint, forceFigureAutoSize, false, false);
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
     *            true if this method is called from a parent "getBounds" call, false otherwise.
     * 
     * @return the bounds of the node.
     */
    public static Rectangle getBounds(Node node, boolean useFigureForAutoSizeConstraint, boolean forceFigureAutoSize, boolean boxForConnection, boolean recursiveGetBounds) {
        PrecisionRectangle bounds = new PrecisionRectangle(0, 0, 0, 0);
        LayoutConstraint layoutConstraint = node.getLayoutConstraint();
        EObject element = node.getElement();
        if (element instanceof AbstractDNode) {
            AbstractDNode abstractDNode = (AbstractDNode) element;
            if (layoutConstraint instanceof Location) {
                bounds.setX(((Location) layoutConstraint).getX());
                bounds.setY(((Location) layoutConstraint).getY());
            }
            if (layoutConstraint instanceof Size) {
                bounds.setWidth(((Size) layoutConstraint).getWidth());
                bounds.setHeight(((Size) layoutConstraint).getHeight());
            } else {
                bounds.setWidth(-1);
                bounds.setHeight(-1);
            }

            if (new ViewQuery(node).isForNameEditPart()) {
                if (abstractDNode.getName() == null || abstractDNode.getName().length() == 0) {
                    if (bounds.width == -1) {
                        bounds.setWidth(0);
                    }
                    if (bounds.height == -1) {
                        bounds.setHeight(0);
                    }
                } else {

                    // Make a default size for label (this size is purely an average estimate)
                    replaceAutoSize(node, bounds, useFigureForAutoSizeConstraint, getLabelDimension(node, new Dimension(50, 20)), recursiveGetBounds);
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
                double shadowBorderSize = getShadowBorderSize(node);
                bounds.setWidth(bounds.width - shadowBorderSize);
                bounds.setHeight(bounds.height - shadowBorderSize);
            }
        }
        return bounds;
    }

    /**
     * If the editPart is a container and is not a workspace image or a regions, the default shadow border size is
     * returned. Otherwise, 0 is returned. See
     * {@link AbstractDiagramElementContainerEditPart#addDropShadow(NodeFigure,IFigure)}.
     * 
     * @param editPart
     *            an edit part
     * @return The shadow border size of the edit part
     */
    public static double getShadowBorderSize(Node node) {
        double shadowBorderSize = 0;
        if (isShadowBorderNeeded(node)) {
            shadowBorderSize = AlphaDropShadowBorder.getDefaultShadowSize();
        }
        return shadowBorderSize;
    }

    /**
     * Shadow border is needed for all container except for regions or workspace image styles. These can have a
     * non-rectangular contour and transparent zones which should be kept as is.
     * 
     * @return false for regions and workspace images, true otherwise.
     */
    public static boolean isShadowBorderNeeded(Node node) {
        boolean needShadowBorder = false;
        EObject element = node.getElement();
        if (element instanceof DDiagramElementContainer) {
            DDiagramElementContainer ddec = (DDiagramElementContainer) element;
            needShadowBorder = !(new DDiagramElementContainerExperimentalQuery(ddec).isRegion() || ddec.getOwnedStyle() instanceof WorkspaceImage);
        }
        return needShadowBorder;
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
    private static void replaceAutoSize(Node node, Rectangle bounds, boolean useFigureForAutoSizeConstraint, Dimension providedDefaultSize, boolean recursive) {
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
                Point bottomRight = getBottomRight(node, recursive);
                double shadowBorderSize = getShadowBorderSize(node);
                Dimension topLeftInsets = getTopLeftInsets(node);
                Dimension bottomRightInsets = getBottomRightInsets(node);
                if (!recursive) {
                    bottomRight.setX(bottomRight.x + Double.valueOf(shadowBorderSize).intValue() + topLeftInsets.width() + bottomRightInsets.width());
                    bottomRight.setY(bottomRight.y + Double.valueOf(shadowBorderSize).intValue() + topLeftInsets.height() + bottomRightInsets.height());
                }
                if (bounds.width == -1) {
                    if (bottomRight.x > defaultSize.width) {
                        bounds.setWidth(bottomRight.x);
                    } else {
                        bounds.setWidth(defaultSize.width);
                    }
                }
                if (bounds.height == -1) {
                    if (bottomRight.y > defaultSize.height) {
                        bounds.setHeight(bottomRight.y);
                    } else {
                        bounds.setHeight(defaultSize.height);
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
     * @param node
     *            the node whose bottom right corner is to compute.
     * @param considerBorderNode
     *            true to consider border nodes when computing the bottom right corner point, false otherwise.
     * 
     * @return Point at the bottom right of the rectangle
     */
    private static Point getBottomRight(Node node, boolean considerBorderNodes) {
        int right = 0;
        int bottom = 0;
        for (Iterator<Node> children = Iterators.filter(node.getChildren().iterator(), Node.class); children.hasNext(); /* */) {
            Node child = children.next();
            // The border nodes are ignored, except if it is expected to consider it (auto-size of a container with
            // children having border nodes)
            if (considerBorderNodes || !(new NodeQuery(child).isBorderedNode())) {
                Rectangle bounds = getBounds(child, false, false, false, true);
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

    /**
     * Get all incoming and outgoing edges of this <code>view</code> or of all of its children.
     * 
     * @param view
     *            the concern view
     * @return list of edges
     */
    private static List<Edge> getIncomingOutgoingEdges(View view) {
        List<Edge> edgesToDelete = new ArrayList<>();
        edgesToDelete.addAll(view.getSourceEdges());
        edgesToDelete.addAll(view.getTargetEdges());

        List<View> children = view.getChildren();
        for (View child : children) {
            edgesToDelete.addAll(getIncomingOutgoingEdges(child));
        }
        return edgesToDelete;
    }

    /**
     * Get all incoming and outgoing edges of all <code>views</code> or of all of their children.
     * 
     * @param view
     *            the concern views
     * @return list of edges
     */
    public static List<Edge> getAttachedEdges(Collection<View> views) {
        return views.stream().flatMap(view -> getIncomingOutgoingEdges(view).stream()).toList();
    }

    /**
     * Return all incoming and outgoing edges of all edges recursively
     * 
     * @param edges
     *            collection of edges for which we want all incoming and outgoing edges
     * @return all incoming and outgoing edges of all edges recursively
     */
    public static List<Edge> getAttachedEdgesRecursively(Collection<? extends View> edges) {
        var resursivlyAttachedEdges = new ArrayList<Edge>();
        var remainingEdges = new ArrayList<View>(edges);
        while (!remainingEdges.isEmpty()) {
            // Here we take view (take = remove from list), we put it aside (in removed).
            // Then, if the view has not already been processed:
            // we collect incoming and outgoing edges and we add these edges to the orphan edges list.
            View view = remainingEdges.stream().findAny().orElseThrow(); // get an element

            // Before removing this view, we must identify incoming or outgoing
            // edges of this view or of one of its children to delete them just
            // after. Indeed, an Edge without source (or target) must not exist.
            List<Edge> attachedEdges = getIncomingOutgoingEdges(view);
            // remove edges already present
            attachedEdges.removeAll(resursivlyAttachedEdges);
            attachedEdges.removeAll(remainingEdges);

            remainingEdges.addAll(attachedEdges);
            resursivlyAttachedEdges.addAll(attachedEdges);

            remainingEdges.removeIf(v -> view == v);
        }
        return resursivlyAttachedEdges;
    }

    /**
     * Return all attached notes/texts/representation links (i.e. PGE: pure graphical elements) of a view.
     * 
     * @return The list of attached PGE
     */
    public static List<Node> getAttachedPGE(View view) {
        // get all attached notes
        List<Edge> noteAttachments = getIncomingOutgoingEdges(view).stream() //
                .filter(GMFNotationHelper::isNoteAttachment).toList();

        return noteAttachments.stream().flatMap(edge -> {
            return Stream.of(edge.getSource(), edge.getTarget());
        }).filter(attachedView -> { // all nodes linked to note attachment: filter notes/texts
            if (attachedView instanceof Node attachedNode) {
                return GMFNotationHelper.isNote(attachedNode) || GMFNotationHelper.isTextNote(attachedNode);
            } else {
                return false;
            }
        }).map(Node.class::cast).toList();
    }

    /**
     * Return all attached notes/texts/representation links (i.e. PGE: pure graphical elements) of all views.
     * 
     * @return The list of attached PGE
     */
    public static List<Node> getAttachedPGE(Collection<? extends View> views) {
        return views.stream().flatMap(view -> GMFHelper.getAttachedPGE(view).stream()).toList();
    }

    /**
     * Remove all notes/texts/representation links (i.e. PGE: pure graphical elements) attached to removed nodes and
     * without other attachment.
     * 
     * The PGE is removed if all attached element are removed. The PGE is hidden if all visible attached element are
     * removed. If the PGE has at least one remaining attached element, the PGE is not removed and note hidden.
     * 
     * @param candidatesPGE
     *            Collection of all candidate PGE
     */
    public static void deleteDetachedPGE(Collection<Node> candidatesPGE) {
        for (Node pureGraphicalElement : candidatesPGE) {
            List<Edge> sourceEdges = pureGraphicalElement.getSourceEdges();
            List<Edge> targetEdges = pureGraphicalElement.getTargetEdges();

            List<Edge> validEdges = Stream.concat(sourceEdges.stream(), targetEdges.stream()) //
                    .filter(edge -> edge.eContainer() != null).toList();

            // remove unattached notes/texts
            if (validEdges.size() == 0) {
                EcoreUtil.remove(pureGraphicalElement);
            } else {
                Stream<Edge> visibleEdges = validEdges.stream().filter(edge -> edge.isVisible());

                // hide notes/texts attached to invisible element
                if (visibleEdges.count() == 0) {
                    pureGraphicalElement.setVisible(false);
                }
            }
        }
    }

    /**
     * Return the list of elements filtered by visible connection.
     * 
     * This method take the collection of node <code>elements</code> and return a filtered list this collection. The
     * condition to keep an element are as follows:
     * <ul>
     * <li>there are no edges attached to element,</li>
     * <li>or all edges attached to element are hidden or blacklisted (in <code>excludedEdges</code>)</li>
     * </ul>
     * 
     * @param elements
     *            the initial collection to be filtered
     * @param excludedEdges
     *            the black list for edges we want to exclude
     * @return the filtered list
     */
    public static List<Node> getElementWithoutVisibleConnection(Collection<Node> elements, Collection<Edge> excludedEdges) {
        return elements.stream().filter(element -> {
            List<Edge> sourceEdges = element.getSourceEdges();
            List<Edge> targetEdges = element.getTargetEdges();

            // All incoming and outgoing edges of element
            Stream<Edge> edges = Stream.concat(sourceEdges.stream(), targetEdges.stream());

            // Filter on edges by visibility attribute and excluded edges
            // Return node with none attached edges (except excluded edges or hidden edges)
            return edges.noneMatch(edge -> {
                return edge.isVisible() && !excludedEdges.contains(edge);
            });
        }).toList();
    }
}
