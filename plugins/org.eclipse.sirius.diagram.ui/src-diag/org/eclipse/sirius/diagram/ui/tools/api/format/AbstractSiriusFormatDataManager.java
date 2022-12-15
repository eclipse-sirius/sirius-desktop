/*******************************************************************************
 * Copyright (c) 2009, 2022 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.api.format;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionDimension;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.core.util.ViewRefactorHelper;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Bendpoints;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.JumpLinkStatus;
import org.eclipse.gmf.runtime.notation.JumpLinkType;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.gmf.runtime.notation.Smoothness;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.formatdata.AbstractFormatData;
import org.eclipse.sirius.diagram.formatdata.EdgeFormatData;
import org.eclipse.sirius.diagram.formatdata.FormatdataFactory;
import org.eclipse.sirius.diagram.formatdata.FormatdataPackage;
import org.eclipse.sirius.diagram.formatdata.NodeFormatData;
import org.eclipse.sirius.diagram.formatdata.Point;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.business.api.query.NodeQuery;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.operation.CenterEdgeEndModelChangeOperation;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.internal.refresh.borderednode.CanonicalDBorderItemLocator;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;
import org.eclipse.sirius.ext.draw2d.figure.FigureUtilities;
import org.eclipse.sirius.tools.internal.SiriusCopierHelper;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

import com.google.common.collect.Iterables;

/**
 * An abstract implementation for {@link SiriusFormatDataManager}. <BR>
 * Provide a method to store a format from a graphicalEditPart and iterates on it's children and apply a format.
 * 
 * Regarding the store/apply format functionality, the subclass of this should implements
 * {@link SiriusFormatDataManagerWithMapping} that handles more cases thanks to mapping information.
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 *
 */
public abstract class AbstractSiriusFormatDataManager implements SiriusFormatDataManager {

    private static final Class<?>[] CLASS_EXCEPTIONS = new Class[] { DNodeListElement.class };

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager#storeFormatData(org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart)
     */
    @Override
    public void storeFormatData(final IGraphicalEditPart rootEditPart) {
        final Collection<FormatDataKey> discoveredKeys = new HashSet<>();
        final EObject semanticElement = rootEditPart.resolveSemanticElement();
        final View toStoreView = (View) rootEditPart.getModel();
        if (toStoreView instanceof Edge && semanticElement instanceof DEdge) {
            addEdgeFormatData(null, (DEdge) semanticElement, rootEditPart.getRoot().getViewer());
        } else if (toStoreView instanceof Diagram && semanticElement instanceof DDiagram) {
            addChildFormat((DDiagram) semanticElement, rootEditPart, discoveredKeys);
        } else if (toStoreView instanceof Node && !(rootEditPart instanceof AbstractDiagramNameEditPart)) {
            if (semanticElement instanceof DDiagramElement && semanticElement instanceof DSemanticDecorator) {
                addChildFormat(null, (DDiagramElement) semanticElement, (Node) toStoreView, rootEditPart, discoveredKeys);
            }
        }
        discoveredKeys.clear();
    }

    @Override
    public void applyFormat(IGraphicalEditPart rootEditPart, boolean absoluteCoordinates) {
        applyFormat(rootEditPart, true, true, absoluteCoordinates);
    }

    @Override
    public void applyFormat(IGraphicalEditPart parentEditPart, List<IGraphicalEditPart> childrenSubpart, boolean absoluteCoordinates) {
        applyFormat(parentEditPart, childrenSubpart, true, true, absoluteCoordinates);
    }

    @Override
    public void applyLayout(IGraphicalEditPart rootEditPart, boolean absoluteCoordinates) {
        applyFormat(rootEditPart, true, false, absoluteCoordinates);
    }

    @Override
    public void applyLayout(IGraphicalEditPart parentEditPart, List<IGraphicalEditPart> childrenSubpart, boolean absoluteCoordinates) {
        applyFormat(parentEditPart, childrenSubpart, true, false, absoluteCoordinates);
    }

    @Override
    public void applyStyle(final IGraphicalEditPart rootEditPart) {
        applyFormat(rootEditPart, false, true, true);
    }

    /**
     * Apply the format only to the subpart of the children of the <code>parentEditPart</code>. The current format is
     * not applied on <code>parentEditPart</code>.
     * 
     * @param parentEditPart
     *            the common parent of <code>childrenSubpart</code>
     * @param childrenSubpart
     *            a subpart of the children of parentEditPart
     * @param applyLayout
     *            true if the format must be applied, false otherwise
     * @param applyStyle
     *            true if the style must be applied, false otherwise
     * @param absoluteCoordinates
     *            true if the paste format must apply the layout with absolute coordinates, false if the paste must
     *            apply layout with a conservative origin of the bounding box containing the elements to layout
     */
    protected void applyFormat(IGraphicalEditPart parentEditPart, List<IGraphicalEditPart> childrenSubpart, boolean applyLayout, boolean applyStyle, boolean absoluteCoordinates) {
        if (absoluteCoordinates) {
            // For absolute mode, we apply the layout one by one on the children.
            for (IGraphicalEditPart child : childrenSubpart) {
                applyFormat(child, applyLayout, applyStyle, absoluteCoordinates);
            }
        } else {
            List<AbstractDNode> dNodeChildren = new ArrayList<AbstractDNode>();
            for (IGraphicalEditPart child : childrenSubpart) {
                final View toStoreView = (View) child.getModel();
                final EObject semanticElement = child.resolveSemanticElement();
                if (toStoreView instanceof Node && semanticElement instanceof AbstractDNode && semanticElement instanceof DSemanticDecorator) {
                    dNodeChildren.add((AbstractDNode) semanticElement);
                }
            }
            applyFormatOnChildrenForBoundingBox(dNodeChildren, parentEditPart.getRoot().getViewer(), null, applyLayout, applyStyle, Optional.empty());
        }
    }

    /**
     * Apply the format to the <code>rootEditPart</code>.
     * 
     * @param rootEditPart
     *            The root edit from which we would try to apply the current stored format
     * @param applyLayout
     *            true if the format must be applied, false otherwise
     * @param applyStyle
     *            true if the style must be applied, false otherwise
     * @param absoluteCoordinates
     *            true if the paste format must apply the layout with absolute coordinates, false if the paste must
     *            apply layout with a conservative origin of the bounding box containing the elements to layout
     */
    protected void applyFormat(final IGraphicalEditPart rootEditPart, boolean applyLayout, boolean applyStyle, boolean absoluteCoordinates) {
        final EObject semanticElement = rootEditPart.resolveSemanticElement();
        final View toStoreView = (View) rootEditPart.getModel();
        if (toStoreView instanceof Edge) {
            // Currently not managed...
        } else if (toStoreView instanceof Diagram && semanticElement instanceof DDiagram) {
            if (absoluteCoordinates) {
                applyFormatAbsoluteMode((DDiagram) semanticElement, rootEditPart.getRoot().getViewer(), applyLayout, applyStyle);
            } else {
                applyFormatBoundingBoxMode((DDiagram) semanticElement, rootEditPart.getRoot().getViewer(), applyLayout, applyStyle);
            }
            centerEdgesEnds(toStoreView);
        } else if (toStoreView instanceof Node) {
            if (semanticElement instanceof AbstractDNode && semanticElement instanceof DSemanticDecorator) {
                if (absoluteCoordinates) {
                    applyFormatAbsoluteMode((AbstractDNode) semanticElement, (Node) toStoreView, rootEditPart.getRoot().getViewer(), null, applyLayout, applyStyle);
                } else {
                    applyFormatOnChildrenForBoundingBox(Arrays.asList((AbstractDNode) semanticElement), rootEditPart.getRoot().getViewer(), null, applyLayout, applyStyle, Optional.empty());
                }
            }
            centerEdgesEnds(toStoreView);
        }
    }

    private void centerEdgesEnds(View view) {
        Set<Edge> edges = new HashSet<Edge>();
        if (view instanceof Diagram) {
            edges.addAll(((Diagram) view).getEdges());
        } else {
            ViewUtil.getAllRelatedEdgesForView(view, edges);
        }
        for (Edge edge : edges) {
            CenterEdgeEndModelChangeOperation centerEdgeEndModelChangeOperation = new CenterEdgeEndModelChangeOperation(edge, false);
            centerEdgeEndModelChangeOperation.execute();
        }

    }

    /**
     * Apply the format to the selected <code>diagram</code> in absolute mode.
     * 
     * @param diagram
     *            The diagram used as root element to apply the current stored format
     * @param semanticDecorator
     * @param toStoreView
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     * @param applyLayout
     *            true if the format must be applied, false otherwise
     * @param applyStyle
     *            true if the style must be applied, false otherwise
     */
    private void applyFormatAbsoluteMode(final DDiagram diagram, final EditPartViewer editPartViewer, boolean applyLayout, boolean applyStyle) {
        // We don't apply format on diagram but only on its node children (the
        // edge is applied during source node).
        for (final AbstractDNode node : Iterables.filter(diagram.getOwnedDiagramElements(), AbstractDNode.class)) {
            final Node gmfNode = SiriusGMFHelper.getGmfNode(node);
            if (gmfNode != null) {
                applyFormatAbsoluteMode(node, gmfNode, editPartViewer, null, applyLayout, applyStyle);
            }
        }
    }

    /**
     * Apply the format to the selected <code>diagram</code> in bounding box mode.
     * 
     * @param diagram
     *            The diagram used as root element to apply the current stored format
     * @param semanticDecorator
     * @param toStoreView
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     * @param applyLayout
     *            true if the format must be applied, false otherwise
     * @param applyStyle
     *            true if the style must be applied, false otherwise
     */
    private void applyFormatBoundingBoxMode(final DDiagram diagram, final EditPartViewer editPartViewer, boolean applyLayout, boolean applyStyle) {
        // We don't apply format on diagram but only on its node children (the edge layout application is done during
        // the layout application of its source node).
        applyFormatOnChildrenForBoundingBox(Iterables.filter(diagram.getOwnedDiagramElements(), AbstractDNode.class), editPartViewer, null, applyLayout, applyStyle, Optional.empty());
    }

    private void applyFormatOnChildrenForBoundingBox(final Iterable<AbstractDNode> children, final EditPartViewer editPartViewer, NodeFormatData parentFormatData, boolean applyLayout,
            boolean applyStyle, Optional<PrecisionDimension> parentDeltaBoundingBox) {
        // List children having a corresponding stored format (to consider them in bounding box computing). Common
        // parent is an IdentifiedElement: DDiagram or
        // DDiagramElement
        HashMap<AbstractDNode, Optional<NodeFormatData>> childrenWithFormatMap = new HashMap<>();
        for (final AbstractDNode node : children) {
            final Node gmfNode = SiriusGMFHelper.getGmfNode(node);
            if (gmfNode != null) {
                Optional<NodeFormatData> optionalFormat = getFormatData(node, gmfNode, editPartViewer, null);
                childrenWithFormatMap.put(node, optionalFormat);
            }
        }

        // CHECKSTYLE:OFF
        // The delta between the bounding box of the elements to paste and the bounding box of the copied
        // elements.
        PrecisionDimension deltaBewteenBoundingBoxes = new PrecisionDimension();
        if (applyLayout && parentDeltaBoundingBox.isEmpty()) {
            // Absolute origin of bounding box elements on which to apply
            org.eclipse.draw2d.geometry.PrecisionPoint absoluteOriginOfToPasteElements = new org.eclipse.draw2d.geometry.PrecisionPoint(Double.MAX_VALUE, Double.MAX_VALUE);
            // Absolute origin of bounding box of layout to apply
            org.eclipse.draw2d.geometry.PrecisionPoint absoluteOriginOfCopiedLayout = new org.eclipse.draw2d.geometry.PrecisionPoint(Double.MAX_VALUE, Double.MAX_VALUE);
            childrenWithFormatMap.forEach((dNode, formatData) -> {
                if (formatData.isPresent()) {
                    final Node gmfNode = SiriusGMFHelper.getGmfNode(dNode);
                    Object editPartAsObject = editPartViewer.getEditPartRegistry().get(gmfNode);
                    // we can have a null edit part when pasting format on root diagram
                    // element of a diagram with hidden part that are not hidden in source
                    // diagram.
                    if (editPartAsObject instanceof IGraphicalEditPart) {
                        final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) editPartAsObject;
                        org.eclipse.draw2d.geometry.Point absoluteLocation = graphicalEditPart.getFigure().getBounds().getTopLeft().getCopy();
                        FigureUtilities.translateToAbsoluteByIgnoringScrollbar(graphicalEditPart.getFigure(), absoluteLocation);
                        computeBoundingBoxTopLeftCorner(absoluteOriginOfToPasteElements, absoluteLocation);
                        Point absoluteCopiedLocationToApply = FormatDataHelper.INSTANCE.getAbsoluteLocation(formatData.get());
                        computeBoundingBoxTopLeftCorner(absoluteOriginOfCopiedLayout, new PrecisionPoint(absoluteCopiedLocationToApply.getX(), absoluteCopiedLocationToApply.getY()));
                    }
                }
            });
            deltaBewteenBoundingBoxes.expand(absoluteOriginOfToPasteElements.preciseX(), absoluteOriginOfToPasteElements.preciseY()).shrink(absoluteOriginOfCopiedLayout.preciseX(),
                    absoluteOriginOfCopiedLayout.preciseY());
        }
        childrenWithFormatMap.forEach((dNode, formatData) -> {
            final Node gmfNode = SiriusGMFHelper.getGmfNode(dNode);
            // Apply layout
            if (applyLayout && formatData.isPresent()) {
                if (parentDeltaBoundingBox.isPresent()) {
                    applyLayout(dNode, gmfNode, editPartViewer, formatData.get(), parentDeltaBoundingBox);
                } else {
                    applyLayout(dNode, gmfNode, editPartViewer, formatData.get(), Optional.of(deltaBewteenBoundingBoxes));
                }
            }
            // Apply style
            if (applyStyle && formatData.isPresent()) {
                applyStyle(dNode, gmfNode, formatData.get());
            }
            // Apply format on children
            if (formatData.isPresent()) {
                if (parentDeltaBoundingBox.isPresent()) {
                    applyFormatOnChildrenAndOutgoingEdgesBoundingBoxMode(dNode, editPartViewer, formatData.get(), applyLayout, applyStyle, parentDeltaBoundingBox);
                } else {
                    applyFormatOnChildrenAndOutgoingEdgesBoundingBoxMode(dNode, editPartViewer, formatData.get(), applyLayout, applyStyle, Optional.of(deltaBewteenBoundingBoxes));
                }
            } else {
                applyFormatOnChildrenAndOutgoingEdgesBoundingBoxMode(dNode, editPartViewer, null, applyLayout, applyStyle, Optional.empty());
            }
        });
        // CHECKSTYLE:ON
    }

    private void computeBoundingBoxTopLeftCorner(PrecisionPoint boundingBoxTopLeftCorner, final org.eclipse.draw2d.geometry.Point topLeftCornerOfAnElement) {
        if (boundingBoxTopLeftCorner.preciseX() > topLeftCornerOfAnElement.preciseX()) {
            boundingBoxTopLeftCorner.setPreciseX(topLeftCornerOfAnElement.preciseX());
        }
        if (boundingBoxTopLeftCorner.preciseY() > topLeftCornerOfAnElement.preciseY()) {
            boundingBoxTopLeftCorner.setPreciseY(topLeftCornerOfAnElement.preciseY());
        }
    }

    private void applyLayout(final DRepresentationElement dRepresentationElement, final Node toRestoreView, final EditPartViewer editPartViewer, final NodeFormatData formatData,
            Optional<PrecisionDimension> deltaBewteenBoundingBoxes) {
        Object editPartAsObject = editPartViewer.getEditPartRegistry().get(toRestoreView);
        // we can have a null edit part when pasting format on root diagram
        // element of a diagram with hidden part that are not hidden in source
        // diagram.
        if (formatData != null && editPartAsObject != null) {
            final Bounds bounds = NotationFactory.eINSTANCE.createBounds();
            final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) editPartAsObject;
            Point locationToApply;
            boolean isCollapsed = false;
            if (graphicalEditPart instanceof AbstractDiagramBorderNodeEditPart) {
                // Specific treatment for border node
                // Compute absolute location
                locationToApply = FormatDataHelper.INSTANCE.getAbsoluteLocation(formatData);
                // Compute the best location according to other existing
                // bordered nodes.
                Node parentNode = (Node) toRestoreView.eContainer();
                CanonicalDBorderItemLocator locator = new CanonicalDBorderItemLocator(parentNode, PositionConstants.NSEW);
                if (dRepresentationElement instanceof DDiagramElement) {
                    if (new DDiagramElementQuery((DDiagramElement) dRepresentationElement).isIndirectlyCollapsed()) {
                        isCollapsed = true;
                        locator.setBorderItemOffset(IBorderItemOffsets.COLLAPSE_FILTER_OFFSET);
                    } else {
                        locator.setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);
                    }
                } else {
                    locator.setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);
                }
                final Rectangle rect = new Rectangle(locationToApply.getX(), locationToApply.getY(), formatData.getWidth(), formatData.getHeight());
                final org.eclipse.draw2d.geometry.Point realLocation = locator.getValidLocation(rect, toRestoreView, new ArrayList<Node>(Arrays.asList(toRestoreView)));
                // Compute the new relative position to the parent
                final org.eclipse.draw2d.geometry.Point parentAbsoluteLocation = ((IGraphicalEditPart) graphicalEditPart.getParent()).getFigure().getBounds().getTopLeft().getCopy();
                FigureUtilities.translateToAbsoluteByIgnoringScrollbar(((IGraphicalEditPart) graphicalEditPart.getParent()).getFigure(), parentAbsoluteLocation);
                locationToApply.setX(realLocation.x);
                locationToApply.setY(realLocation.y);
                locationToApply = FormatDataHelper.INSTANCE.getTranslated(locationToApply, parentAbsoluteLocation.negate());
            } else {
                // locationToApply = FormatDataHelper.INSTANCE.getRelativeLocation(formatData, graphicalEditPart);
                locationToApply = FormatDataHelper.INSTANCE.getAbsoluteLocation(formatData);
                PrecisionPoint newLoc = new PrecisionPoint(locationToApply.getX(), locationToApply.getY());
                // Adapt the point according to the delta between each bounding box
                if (deltaBewteenBoundingBoxes.isPresent()) {
                    newLoc.translate(deltaBewteenBoundingBoxes.get());
                }
                // Translate the location to be relative to its parent
                FigureUtilities.translateToRelativeByIgnoringScrollbar(graphicalEditPart.getFigure(), newLoc);
                // Reset the location to apply used to set GMF bounds
                locationToApply.setX(newLoc.x());
                locationToApply.setY(newLoc.y());
                // Apply the location to the figure to, to correctly compute the relative location of the children
                graphicalEditPart.getFigure().setLocation(newLoc);
            }
            bounds.setX(locationToApply.getX());
            bounds.setY(locationToApply.getY());
            if (isCollapsed) {
                Dimension dim = new NodeQuery(toRestoreView).getCollapsedSize();
                bounds.setHeight(dim.height);
                bounds.setWidth(dim.width);
            } else {
                bounds.setHeight(formatData.getHeight());
                bounds.setWidth(formatData.getWidth());
            }
            toRestoreView.setLayoutConstraint(bounds);
        }
    }

    private void applyStyle(final DRepresentationElement dRepresentationElement, final Node toRestoreView, final NodeFormatData formatData) {
        if (formatData != null) {
            // Apply Sirius style properties
            applySiriusStyle(dRepresentationElement, formatData);
            // Apply GMF style properties
            applyGMFStyle(toRestoreView, formatData);
        }
    }

    private void applyFormatOnChildrenAndOutgoingEdgesBoundingBoxMode(final DRepresentationElement dRepresentationElement, final EditPartViewer editPartViewer, final NodeFormatData formatData,
            boolean applyLayout, boolean applyStyle, Optional<PrecisionDimension> parentDeltaBoundingBox) {
        if (dRepresentationElement instanceof DNode) {
            applyFormatToNodeChildren((DNode) dRepresentationElement, editPartViewer, formatData, applyLayout, applyStyle, parentDeltaBoundingBox);
        } else if (dRepresentationElement instanceof DNodeContainer) {
            applyFormatToNodeContainerChildrenBoundingBoxMode((DNodeContainer) dRepresentationElement, editPartViewer, formatData, applyLayout, applyStyle, parentDeltaBoundingBox);
        } else if (dRepresentationElement instanceof DNodeList) {
            applyFormatToNodeListChildren((DNodeList) dRepresentationElement, editPartViewer, formatData, applyLayout, applyStyle);
        } else {
            logUnhandledDiagramElementKindMessage(dRepresentationElement);
        }
        // Deal with the outgoing edges
        if (dRepresentationElement instanceof EdgeTarget) {
            applyFormatToOutgoingEdge((EdgeTarget) dRepresentationElement, editPartViewer, applyLayout, applyStyle);
        }
    }

    /**
     * @param sourceNode
     * @param editPartViewer
     */
    private void applyFormatToOutgoingEdge(final EdgeTarget sourceNode, final EditPartViewer editPartViewer, boolean applyLayout, boolean applyStyle) {
        for (final DEdge edge : sourceNode.getOutgoingEdges()) {
            final Edge gmfEdge = SiriusGMFHelper.getGmfEdge(edge);
            if (gmfEdge != null) {
                applyFormat(edge, gmfEdge, editPartViewer, applyLayout, applyStyle);
            }
        }
    }

    /**
     * @param edge
     * @param gmfEdge
     * @param editPartViewer
     */
    private void applyFormat(final DEdge edge, final Edge gmfEdge, final EditPartViewer editPartViewer, boolean applyLayout, boolean applyStyle) {
        final Optional<EdgeFormatData> formatData = getFormatData(edge);

        if (formatData.isPresent()) {
            if (applyLayout) {
                applyEdgeFormat(gmfEdge, formatData.get());
            }
            if (applyStyle) {
                // Apply Sirius style properties
                applySiriusStyle(edge, formatData.get());
                // Apply GMF style properties
                applyGMFStyle(gmfEdge, formatData.get());
            }

            applyLabelFormat(gmfEdge, formatData.get(), applyLayout, applyStyle);

        }
    }

    /**
     * Apply format stored in {@code formatData} to {@code gmfEdge}.
     * 
     * @param gmfEdge
     * @param formatData
     */
    private void applyEdgeFormat(final Edge gmfEdge, final EdgeFormatData formatData) {
        final Bendpoints newBendpoints = convertPointsToGMFBendpoint(formatData);
        Bendpoints oldBendpoints = gmfEdge.getBendpoints();
        if (oldBendpoints instanceof RelativeBendpoints && newBendpoints instanceof RelativeBendpoints) {
            // Use this method to allow correct notification handle in
            // org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart.handleNotificationEvent(Notification) and
            // induce a refresh of the edge figure.
            ((RelativeBendpoints) oldBendpoints).setPoints(((RelativeBendpoints) newBendpoints).getPoints());
        } else {
            // Fallback but seems not necessary
            gmfEdge.setBendpoints(newBendpoints);
        }

        if (formatData.getSourceTerminal() != null) {
            if (gmfEdge.getSourceAnchor() == null) {
                gmfEdge.setSourceAnchor(NotationFactory.eINSTANCE.createIdentityAnchor());
            }
            if (gmfEdge.getSourceAnchor() instanceof IdentityAnchor) {
                ((IdentityAnchor) gmfEdge.getSourceAnchor()).setId(formatData.getSourceTerminal());
            }
        } else if (gmfEdge.getSourceAnchor() instanceof IdentityAnchor) {
            gmfEdge.setSourceAnchor(null);
        }
        if (formatData.getTargetTerminal() != null) {
            if (gmfEdge.getTargetAnchor() == null) {
                gmfEdge.setTargetAnchor(NotationFactory.eINSTANCE.createIdentityAnchor());
            }
            if (gmfEdge.getTargetAnchor() instanceof IdentityAnchor) {
                ((IdentityAnchor) gmfEdge.getTargetAnchor()).setId(formatData.getTargetTerminal());
            }
        } else if (gmfEdge.getTargetAnchor() instanceof IdentityAnchor) {
            gmfEdge.setTargetAnchor(null);
        }
        final RoutingStyle routingStyle = (RoutingStyle) gmfEdge.getStyle(NotationPackage.eINSTANCE.getRoutingStyle());
        if (routingStyle != null) {
            routingStyle.setRouting(Routing.get(formatData.getRouting()));
            routingStyle.setJumpLinkStatus(JumpLinkStatus.get(formatData.getJumpLinkStatus()));
            routingStyle.setJumpLinkType(JumpLinkType.get(formatData.getJumpLinkType()));
            routingStyle.setJumpLinksReverse(formatData.isReverseJumpLink());
            routingStyle.setSmoothness(Smoothness.get(formatData.getSmoothness()));
        }
    }

    private void applyLabelFormat(final View gmfView, final AbstractFormatData parentFormatData, boolean applyLayout, boolean applyStyle) {
        if (parentFormatData != null) {
            final Node labelNode = SiriusGMFHelper.getLabelNode(gmfView);
            if (parentFormatData.getLabel() != null && labelNode != null) {
                if (applyLayout) {
                    if (!parentFormatData.getLabel().eIsSet(FormatdataPackage.eINSTANCE.getNodeFormatData_Width())
                            && !parentFormatData.getLabel().eIsSet(FormatdataPackage.eINSTANCE.getNodeFormatData_Height())) {
                        Location location = NotationFactory.eINSTANCE.createLocation();
                        location.setX(parentFormatData.getLabel().getLocation().getX());
                        location.setY(parentFormatData.getLabel().getLocation().getY());
                        labelNode.setLayoutConstraint(location);
                    } else {
                        Bounds bounds = NotationFactory.eINSTANCE.createBounds();
                        bounds.setX(parentFormatData.getLabel().getLocation().getX());
                        bounds.setY(parentFormatData.getLabel().getLocation().getY());
                        bounds.setWidth(parentFormatData.getLabel().getWidth());
                        bounds.setHeight(parentFormatData.getLabel().getHeight());
                        labelNode.setLayoutConstraint(bounds);
                    }
                }
                if (applyStyle) {
                    // Apply GMF style properties
                    applyGMFStyle(labelNode, parentFormatData.getLabel());
                }
            }
        }
    }

    /**
     * @param edgeFormatData
     *            The format data of the edge
     * @return
     */
    private Bendpoints convertPointsToGMFBendpoint(final EdgeFormatData edgeFormatData) {
        final RelativeBendpoints result = NotationFactory.eINSTANCE.createRelativeBendpoints();

        final List<RelativeBendpoint> relativeBendpoints = new LinkedList<RelativeBendpoint>();

        final Point source = edgeFormatData.getSourceRefPoint();
        final Point target = edgeFormatData.getTargetRefPoint();

        /* source and target may be null if edit part was not created */
        if (source != null && target != null) {
            final org.eclipse.draw2d.geometry.Point sourceRefPoint = new org.eclipse.draw2d.geometry.Point(source.getX(), source.getY());
            final org.eclipse.draw2d.geometry.Point targetRefPoint = new org.eclipse.draw2d.geometry.Point(target.getX(), target.getY());

            for (final Point point : edgeFormatData.getPointList()) {
                final org.eclipse.draw2d.geometry.Point tempPoint = new org.eclipse.draw2d.geometry.Point(point.getX(), point.getY());
                final Dimension s = tempPoint.getDifference(sourceRefPoint);
                final Dimension t = tempPoint.getDifference(targetRefPoint);
                relativeBendpoints.add(new RelativeBendpoint(s.width, s.height, t.width, t.height));
            }
        }
        result.setPoints(relativeBendpoints);

        return result;
    }

    /**
     * 
     * Search a format corresponding to the semantic decorator and applies it to the node. Then it applies to it's
     * children and outgoing edges.
     *
     * @param dRepresentationElement
     *            The representation element used as root element to apply the current stored format. Is is used to
     *            search the corresponding format.
     * @param toRestoreView
     *            Node on which to apply the format
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     * @parentFormatData the format of the parent of <code>toRestoreView<code>
     * @param applyLayout
     *            true if the format must be applied, false otherwise
     * @param applyStyle
     *            true if the style must be applied, false otherwise
     */
    private Optional<NodeFormatData> getFormatData(final DRepresentationElement dRepresentationElement, final Node toRestoreView, final EditPartViewer editPartViewer,
            final NodeFormatData parentFormatData) {
        FormatDataKey key = createKey(dRepresentationElement);
        NodeFormatData formatData;
        formatData = (NodeFormatData) this.getFormatData(key, dRepresentationElement.getMapping());
        // If a direct child have the same format data and key than its parents,
        // look in the parent format data 's children for a child format data
        // with the expected id.
        if (parentFormatData != null && parentFormatData == formatData && !StringUtil.isEmpty(key.getId())) {
            formatData = null;
            for (NodeFormatData childFormatData : parentFormatData.getChildren()) {
                // if many children format with same id, a choice will not be
                // possible;
                if (key.getId().equals(childFormatData.getId())) {
                    if (formatData == null) {
                        formatData = childFormatData;
                    } else {
                        formatData = null;
                        break;
                    }
                }
            }
        }
        return Optional.ofNullable(formatData);
    }

    private Optional<EdgeFormatData> getFormatData(final DEdge edge) {
        return Optional.ofNullable((EdgeFormatData) this.getFormatData(createKey(edge), edge.getMapping()));
    }

    /**
     * 
     * Search a format corresponding to the semantic decorator and applies it to the node. Then it applies to it's
     * children and outgoing edges.
     *
     * @param dRepresentationElement
     *            The representation element used as root element to apply the current stored format. Is is used to
     *            search the corresponding format.
     * @param toRestoreView
     *            Node on which to apply the format
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     * @param parentFormatData
     *            the format of the parent of <code>toRestoreView<code>
     * @param applyLayout
     *            true if the format must be applied, false otherwise
     * @param applyStyle
     *            true if the style must be applied, false otherwise
     */
    private void applyFormatAbsoluteMode(final DRepresentationElement dRepresentationElement, final Node toRestoreView, final EditPartViewer editPartViewer, final NodeFormatData parentFormatData,
            boolean applyLayout, boolean applyStyle) {
        FormatDataKey key = createKey(dRepresentationElement);
        NodeFormatData formatData;
        formatData = (NodeFormatData) this.getFormatData(key, dRepresentationElement.getMapping());
        // If a direct child have the same format data and key than its parents,
        // look in the parent format data 's children for a child format data
        // with the expected id.
        if (parentFormatData != null && parentFormatData == formatData && !StringUtil.isEmpty(key.getId())) {
            formatData = null;
            for (NodeFormatData childFormatData : parentFormatData.getChildren()) {
                // if many children format with same id, a choice will not be
                // possible;
                if (key.getId().equals(childFormatData.getId())) {
                    if (formatData == null) {
                        formatData = childFormatData;
                    } else {
                        formatData = null;
                        break;
                    }
                }
            }
        }
        Object editPartAsObject = editPartViewer.getEditPartRegistry().get(toRestoreView);
        // we can have a null edit part when pasting format on root diagram
        // element of a diagram with hidden part that are not hidden in source
        // diagram.
        if (formatData != null && applyLayout && editPartAsObject != null) {
            final Bounds bounds = NotationFactory.eINSTANCE.createBounds();
            final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) editPartAsObject;
            Point locationToApply;
            boolean isCollapsed = false;
            if (graphicalEditPart instanceof AbstractDiagramBorderNodeEditPart) {
                // Specific treatment for border node
                // Compute absolute location
                locationToApply = FormatDataHelper.INSTANCE.getAbsoluteLocation(formatData);
                // Compute the best location according to other existing
                // bordered nodes.
                Node parentNode = (Node) toRestoreView.eContainer();
                CanonicalDBorderItemLocator locator = new CanonicalDBorderItemLocator(parentNode, PositionConstants.NSEW);
                if (dRepresentationElement instanceof DDiagramElement) {
                    if (new DDiagramElementQuery((DDiagramElement) dRepresentationElement).isIndirectlyCollapsed()) {
                        isCollapsed = true;
                        locator.setBorderItemOffset(IBorderItemOffsets.COLLAPSE_FILTER_OFFSET);
                    } else {
                        locator.setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);
                    }
                } else {
                    locator.setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);
                }
                final Rectangle rect = new Rectangle(locationToApply.getX(), locationToApply.getY(), formatData.getWidth(), formatData.getHeight());
                final org.eclipse.draw2d.geometry.Point realLocation = locator.getValidLocation(rect, toRestoreView, new ArrayList<Node>(Arrays.asList(toRestoreView)));
                // Compute the new relative position to the parent
                final org.eclipse.draw2d.geometry.Point parentAbsoluteLocation = ((IGraphicalEditPart) graphicalEditPart.getParent()).getFigure().getBounds().getTopLeft().getCopy();
                FigureUtilities.translateToAbsoluteByIgnoringScrollbar(((IGraphicalEditPart) graphicalEditPart.getParent()).getFigure(), parentAbsoluteLocation);
                locationToApply.setX(realLocation.x);
                locationToApply.setY(realLocation.y);
                locationToApply = FormatDataHelper.INSTANCE.getTranslated(locationToApply, parentAbsoluteLocation.negate());
            } else {
                locationToApply = FormatDataHelper.INSTANCE.getRelativeLocation(formatData, graphicalEditPart);
                // Apply the location to the figure to, to correctly compute
                // the relative location of the children
                graphicalEditPart.getFigure().setLocation(new org.eclipse.draw2d.geometry.Point(locationToApply.getX(), locationToApply.getY()));
            }
            bounds.setX(locationToApply.getX());
            bounds.setY(locationToApply.getY());
            if (isCollapsed) {
                Dimension dim = new NodeQuery(toRestoreView).getCollapsedSize();
                bounds.setHeight(dim.height);
                bounds.setWidth(dim.width);
            } else {
                bounds.setHeight(formatData.getHeight());
                bounds.setWidth(formatData.getWidth());
            }
            toRestoreView.setLayoutConstraint(bounds);
        }
        if (formatData != null && applyStyle) {
            // Apply Sirius style properties
            applySiriusStyle(dRepresentationElement, formatData);
            // Apply GMF style properties
            applyGMFStyle(toRestoreView, formatData);
        }
        if (dRepresentationElement instanceof DNode) {
            applyFormatToNodeChildren((DNode) dRepresentationElement, editPartViewer, formatData, applyLayout, applyStyle, Optional.empty());
        } else if (dRepresentationElement instanceof DNodeContainer) {
            applyFormatToNodeContainerChildrenAbsoluteMode((DNodeContainer) dRepresentationElement, editPartViewer, formatData, applyLayout, applyStyle);
        } else if (dRepresentationElement instanceof DNodeList) {
            applyFormatToNodeListChildren((DNodeList) dRepresentationElement, editPartViewer, formatData, applyLayout, applyStyle);
        } else {
            logUnhandledDiagramElementKindMessage(dRepresentationElement);
        }
        // Deal with the outgoing edges
        if (dRepresentationElement instanceof EdgeTarget) {
            applyFormatToOutgoingEdge((EdgeTarget) dRepresentationElement, editPartViewer, applyLayout, applyStyle);
        }
    }

    /**
     * Apply the Sirius style contained in <code>formatData</code> on the <code>semanticDecorator</code>.
     * 
     * @param semanticDecorator
     *            The {@link DSemanticDecorator} on which to apply the style.
     * @param formatData
     *            The format data containing the sirius style
     */
    protected void applySiriusStyle(DSemanticDecorator semanticDecorator, AbstractFormatData formatData) {
        // Make a copy of the style to allow several Paste with the same FormatData.
        Style copyOfSiriusStyle = SiriusCopierHelper.copyWithNoUidDuplication(formatData.getSiriusStyle());
        if ((semanticDecorator instanceof DNode || semanticDecorator instanceof DNodeListElement) && copyOfSiriusStyle instanceof NodeStyle) {
            if (semanticDecorator instanceof DNode) {
                computeCustomFeatures(((DNode) semanticDecorator).getOwnedStyle(), copyOfSiriusStyle);
                ((DNode) semanticDecorator).setOwnedStyle((NodeStyle) copyOfSiriusStyle);
            } else {
                computeCustomFeatures(((DNodeListElement) semanticDecorator).getOwnedStyle(), copyOfSiriusStyle);
                ((DNodeListElement) semanticDecorator).setOwnedStyle((NodeStyle) copyOfSiriusStyle);
            }
        } else if (semanticDecorator instanceof DDiagramElementContainer && copyOfSiriusStyle instanceof ContainerStyle) {
            if (((DDiagramElementContainer) semanticDecorator).getOwnedStyle() != null) {
                computeCustomFeatures(((DDiagramElementContainer) semanticDecorator).getOwnedStyle(), copyOfSiriusStyle);
            }
            ((DDiagramElementContainer) semanticDecorator).setOwnedStyle((ContainerStyle) copyOfSiriusStyle);
        } else if (semanticDecorator instanceof DEdge && copyOfSiriusStyle instanceof EdgeStyle) {
            computeCustomFeatures(((DEdge) semanticDecorator).getOwnedStyle(), copyOfSiriusStyle);
            ((DEdge) semanticDecorator).setOwnedStyle((EdgeStyle) copyOfSiriusStyle);
        }
    }

    /**
     * Copies the appearance of the old view to the new view. Typically this means copying the visibility and the styles
     * of the root and it's children.
     * 
     * @param newView
     *            The new view to copy style features to
     * @param formatData
     *            The format data containing the old view to copy style features from
     */
    @SuppressWarnings("unchecked")
    protected void applyGMFStyle(View newView, AbstractFormatData formatData) {
        if (newView != null && formatData.getGmfView() != null) {
            @SuppressWarnings("rawtypes")
            List excludedStyles = new ArrayList<>();
            if (newView instanceof Edge) {
                // The style of RoutingStyle class is considered as format
                // properties. So they have already been pasted during paste
                // format.
                excludedStyles.add(NotationPackage.eINSTANCE.getRoutingStyle());
            }
            new ViewRefactorHelper().copyViewAppearance(formatData.getGmfView(), newView, excludedStyles);
        }
    }

    /**
     * Log a warning to explain that this kind of element is not managed by the LayoutDataManager. By default, some
     * elements are excluded, no message is displayed for them. Currently, only {@link DNodeListElement} are excluded.
     * Indeed, it is expected that nothing is stored in LayoutDataManager for {@link DNodeListElement} as their location
     * and size are constraint by their parents.
     * 
     * @param notManagedObject
     *            A not managed {@link Object}, usually a {@link DSemanticDecorator}.
     */
    public static void logUnhandledDiagramElementKindMessage(final Object notManagedObject) {
        final Class<?> clazz = notManagedObject.getClass();

        boolean logWarn = true;
        for (final Class<?> exceptionClass : CLASS_EXCEPTIONS) {
            if (exceptionClass.isAssignableFrom(clazz)) {
                logWarn = false;
                break;
            }
        }

        if (logWarn) {
            DiagramPlugin.getDefault().logWarning(MessageFormat.format(Messages.AbstractSiriusLayoutDataManager_unhandledDiagramElementKind, clazz.getName()));
        }
    }

    /**
     * Try to apply a format to the children of the {@link DNode}.
     *
     * @param parentNode
     *            The parent containing children to apply format on.
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     * @param formatData
     *            The format data to apply.
     * @param applyLayout
     *            Whether or not to apply format.
     * @param applyStyle
     *            Whether or not to apply style.
     */
    private void applyFormatToNodeChildren(final DNode parentNode, final EditPartViewer editPartViewer, final NodeFormatData formatData, boolean applyLayout, boolean applyStyle,
            Optional<PrecisionDimension> parentDeltaBoundingBox) {
        // Restore Bordered nodes
        applyFormatForBorderedNodes(parentNode.getOwnedBorderedNodes(), editPartViewer, formatData, applyLayout, applyStyle, parentDeltaBoundingBox);
        // Restore label
        final Node gmfNode = SiriusGMFHelper.getGmfNode(parentNode);
        applyLabelFormat(gmfNode, formatData, applyLayout, applyStyle);
    }

    /**
     * Try to apply a format to the children of the {@link DNodeContainer}.
     *
     * @param container
     *            The parent containing children to apply format on.
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     */
    private void applyFormatToNodeContainerChildrenAbsoluteMode(final DNodeContainer container, final EditPartViewer editPartViewer, final NodeFormatData formatData, boolean applyLayout,
            boolean applyStyle) {
        // Restore children
        for (final DDiagramElement child : container.getOwnedDiagramElements()) {
            if (child instanceof AbstractDNode) {
                // Search the GMF node corresponding to the child
                final Node gmfNode = SiriusGMFHelper.getGmfNode(child);
                if (gmfNode != null) {
                    applyFormatAbsoluteMode(child, gmfNode, editPartViewer, formatData, applyLayout, applyStyle);
                }
            }
        }
        // Restore Bordered nodes
        applyFormatForBorderedNodes(container.getOwnedBorderedNodes(), editPartViewer, formatData, applyLayout, applyStyle, Optional.empty());
        // Restore label
        final Node gmfNode = SiriusGMFHelper.getGmfNode(container);
        applyLabelFormat(gmfNode, formatData, applyLayout, applyStyle);
    }

    /**
     * Try to apply a format to the children of the {@link DNodeContainer}.
     *
     * @param container
     *            The parent containing children to apply format on.
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     */
    private void applyFormatToNodeContainerChildrenBoundingBoxMode(final DNodeContainer container, final EditPartViewer editPartViewer, final NodeFormatData formatData, boolean applyLayout,
            boolean applyStyle, Optional<PrecisionDimension> parentDeltaBoundingBox) {
        // Restore children
        applyFormatOnChildrenForBoundingBox(Iterables.filter(container.getOwnedDiagramElements(), AbstractDNode.class), editPartViewer, formatData, applyLayout, applyStyle, parentDeltaBoundingBox);
        // Restore Bordered nodes
        applyFormatForBorderedNodes(container.getOwnedBorderedNodes(), editPartViewer, formatData, applyLayout, applyStyle, parentDeltaBoundingBox);
        // Restore label
        final Node gmfNode = SiriusGMFHelper.getGmfNode(container);
        applyLabelFormat(gmfNode, formatData, applyLayout, applyStyle);
    }

    /**
     * Try to apply the format to the bordered nodes.
     *
     * @param borderedNodes
     *            The list of bordered nodes to deal with.
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     * @param parentFormatData
     *            The formatData of the parent of the borderedNodes.
     * @param applyLayout
     *            Whether or not to apply format.
     * @param applyStyle
     *            Whether or not to apply style.
     */
    private void applyFormatForBorderedNodes(EList<DNode> borderedNodes, EditPartViewer editPartViewer, NodeFormatData parentFormatData, boolean applyLayout, boolean applyStyle,
            Optional<PrecisionDimension> parentShift) {
        HashMap<Node, NodeFormatData> nodesWithFormatDataToApply = new HashMap<>();
        HashMap<Node, DSemanticDecorator> nodesWithCoresspondingDSemanticDecorator = new HashMap<>();
        // Search each bordered nodes that have formatData to apply
        for (final DNode child : borderedNodes) {
            // Search the GMF node corresponding to the child
            final Node gmfNode = SiriusGMFHelper.getGmfNode(child);
            if (gmfNode != null) {
                FormatDataKey key = createKey(child);
                NodeFormatData formatData;
                formatData = (NodeFormatData) this.getFormatData(key, child.getMapping());

                // If a direct child have the same format data and key than its
                // parents, look in the parent format data 's children for a
                // child format data with the expected id.
                if (parentFormatData != null && parentFormatData == formatData && !StringUtil.isEmpty(key.getId())) {
                    formatData = null;
                    for (NodeFormatData childFormatData : parentFormatData.getChildren()) {
                        // if many children format with same id, a choice will
                        // not be possible
                        if (key.getId().equals(childFormatData.getId())) {
                            if (formatData == null) {
                                formatData = childFormatData;
                            } else {
                                formatData = null;
                                break;
                            }
                        }
                    }
                }
                if (formatData != null) {
                    nodesWithFormatDataToApply.put(gmfNode, formatData);
                    nodesWithCoresspondingDSemanticDecorator.put(gmfNode, child);
                }
            }
        }
        // Iterate over each bordered nodes which have format data to apply to
        Set<Node> toIgnore = nodesWithFormatDataToApply.keySet();
        for (Entry<Node, NodeFormatData> entry : nodesWithFormatDataToApply.entrySet()) {
            Node node = entry.getKey();
            applyFormatForBorderedNode(nodesWithCoresspondingDSemanticDecorator.get(node), node, editPartViewer, entry.getValue(), toIgnore, applyLayout, applyStyle, parentShift);
        }
    }

    /**
     * Try to apply the format to a bordered node.
     *
     * @param semanticDecorator
     *            The semantic decorator associated with this Node
     * @param toRestoreView
     *            Node on which to apply the format
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     * @param formatData
     *            the format to apply on <code>toRestoreView<code>
     * @param portsNodesToIgnore
     *            The list of bordered nodes to ignore in the conflict detection
     * @param applyLayout
     *            Whether or not to apply format.
     * @param applyStyle
     *            Whether or not to apply style.
     * @param parentShift
     *            If the parent has been moved because of a previous "Paste Format in bounding box mode", the border
     *            nodes are also shift with the same delta.
     */
    // CHECKSTYLE:OFF
    private void applyFormatForBorderedNode(final DSemanticDecorator semanticDecorator, final Node toRestoreView, final EditPartViewer editPartViewer, final NodeFormatData formatData,
            final Set<Node> portsNodesToIgnore, boolean applyLayout, boolean applyStyle, Optional<PrecisionDimension> parentShift) {
        // CHECKSTYLE:ON
        if (applyLayout) {
            final Bounds bounds = NotationFactory.eINSTANCE.createBounds();
            Point locationToApply;
            boolean isCollapsed = false;
            if (!(toRestoreView.eContainer() instanceof Node)) {
                return;
            }
            Node parentNode = (Node) toRestoreView.eContainer();

            Object parentGraphicalEditPart = editPartViewer.getEditPartRegistry().get(parentNode);
            NodeQuery nodeQuery = new NodeQuery(toRestoreView);

            if (nodeQuery.isBorderedNode() && parentGraphicalEditPart instanceof IGraphicalEditPart) {
                // Specific treatment for border node
                // Compute absolute location
                locationToApply = FormatDataHelper.INSTANCE.getAbsoluteLocation(formatData);

                // Compute the best location according to other existing
                // bordered nodes.

                CanonicalDBorderItemLocator locator = new CanonicalDBorderItemLocator(parentNode, PositionConstants.NSEW);
                if (semanticDecorator instanceof DDiagramElement) {
                    if (new DDiagramElementQuery((DDiagramElement) semanticDecorator).isIndirectlyCollapsed()) {
                        isCollapsed = true;
                        locator.setBorderItemOffset(IBorderItemOffsets.COLLAPSE_FILTER_OFFSET);
                    } else {
                        locator.setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);
                    }
                } else {
                    locator.setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);
                }

                final Rectangle rect = new Rectangle(locationToApply.getX(), locationToApply.getY(), formatData.getWidth(), formatData.getHeight());
                // Adapt with the parent shift
                if (parentShift.isPresent()) {
                    rect.translate(parentShift.get().preciseWidth(), parentShift.get().preciseHeight());
                }
                final org.eclipse.draw2d.geometry.Point realLocation = locator.getValidLocation(rect, toRestoreView, portsNodesToIgnore);

                // Compute the new relative position to the parent
                final org.eclipse.draw2d.geometry.Point parentAbsoluteLocation = GMFHelper.getAbsoluteBounds(parentNode, true).getTopLeft();
                locationToApply.setX(realLocation.x);
                locationToApply.setY(realLocation.y);
                locationToApply = FormatDataHelper.INSTANCE.getTranslated(locationToApply, parentAbsoluteLocation.negate());

            } else {
                Object graphicalEditPart = editPartViewer.getEditPartRegistry().get(toRestoreView);
                if (graphicalEditPart instanceof IGraphicalEditPart) {
                    locationToApply = FormatDataHelper.INSTANCE.getRelativeLocation(formatData, (IGraphicalEditPart) graphicalEditPart);
                    // Apply the location to the figure to, to correctly compute
                    // the relative location of the children
                    ((GraphicalEditPart) graphicalEditPart).getFigure().setLocation(new org.eclipse.draw2d.geometry.Point(locationToApply.getX(), locationToApply.getY()));
                } else {
                    locationToApply = FormatdataFactory.eINSTANCE.createPoint();
                }
            }
            bounds.setX(locationToApply.getX());
            bounds.setY(locationToApply.getY());
            if (isCollapsed) {
                Dimension dim = new NodeQuery(toRestoreView).getCollapsedSize();
                bounds.setHeight(dim.height);
                bounds.setWidth(dim.width);
            } else {
                bounds.setHeight(formatData.getHeight());
                bounds.setWidth(formatData.getWidth());
            }

            toRestoreView.setLayoutConstraint(bounds);
        }
        if (applyStyle) {
            // Apply Sirius style properties
            applySiriusStyle(semanticDecorator, formatData);
            // Apply GMF style properties
            applyGMFStyle(toRestoreView, formatData);
        }

        if (semanticDecorator instanceof DNode) {
            applyFormatToNodeChildren((DNode) semanticDecorator, editPartViewer, formatData, applyLayout, applyStyle, Optional.empty());
        } else {
            logUnhandledDiagramElementKindMessage(semanticDecorator);
        }
        if (semanticDecorator instanceof EdgeTarget) {
            applyFormatToOutgoingEdge((EdgeTarget) semanticDecorator, editPartViewer, applyLayout, applyStyle);
        }
    }

    /**
     * Try to apply a format to the children of the {@link DNodeList}.
     *
     * @param nodeList
     *            The parent containing children to apply format on.
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     */
    private void applyFormatToNodeListChildren(final DNodeList nodeList, final EditPartViewer editPartViewer, final NodeFormatData formatData, boolean applyLayout, boolean applyStyle) {
        // Restore Bordered nodes
        applyFormatForBorderedNodes(nodeList.getOwnedBorderedNodes(), editPartViewer, formatData, applyLayout, applyStyle, Optional.empty());

        // Restore label
        final Node gmfNode = SiriusGMFHelper.getGmfNode(nodeList);
        applyLabelFormat(gmfNode, formatData, applyLayout, applyStyle);
    }

    /**
     * Add the format for the children of a node.
     *
     * @param parentNode
     *            The parent of the children
     * @param parentFormatData
     *            The corresponding formatData
     * @param parentEditPart
     *            The editPart corresponding to the parent FormatData
     * @param gmfView
     *            GMF view
     * @param discoveredKeys
     *            The {@link FormatDataKey} discovered during the current store action.
     */
    protected void addNodeChildren(final DNode parentNode, final NodeFormatData parentFormatData, final IGraphicalEditPart parentEditPart, final View gmfView,
            Collection<FormatDataKey> discoveredKeys) {
        for (final DNode child : parentNode.getOwnedBorderedNodes()) {
            checkDataAndAddChildFormat(parentFormatData, child, parentEditPart, discoveredKeys);
        }
        // Add the label format data (if exists).
        addLabelFormatData(parentFormatData, gmfView);
    }

    /**
     * Add a format (if we have enough information : GMF view and editPart).
     *
     * @param parentFormatData
     *            The parent format data
     * @param child
     *            The child from which we want to add a new format
     * @param parentSavedEditPart
     *            The previous saved editPart (corresponds to parentFormatData)
     * @param discoveredKeys
     *            The {@link FormatDataKey} discovered during the current store action.
     */
    protected void checkDataAndAddChildFormat(final NodeFormatData parentFormatData, final AbstractDNode child, final IGraphicalEditPart parentSavedEditPart,
            Collection<FormatDataKey> discoveredKeys) {
        // Search the GMF node corresponding to the child
        final Node gmfNode = SiriusGMFHelper.getGmfNode(child);
        if (gmfNode != null) {
            final IGraphicalEditPart editPart = (IGraphicalEditPart) parentSavedEditPart.getRoot().getViewer().getEditPartRegistry().get(gmfNode);
            if (editPart != null) {
                addChildFormat(parentFormatData, child, gmfNode, editPart, discoveredKeys);
            }
        }
    }

    /**
     * Add children of the node.
     *
     * @param container
     *            The parent of the children
     * @param parentFormatData
     *            The corresponding formatData
     * @param parentEditPart
     *            The editPart corresponding to the parent FormatData
     * @param discoveredKeys
     *            The {@link FormatDataKey} discovered during the current store action.
     */
    protected void addNodeContainerChildren(final DNodeContainer container, final NodeFormatData parentFormatData, final IGraphicalEditPart parentEditPart, Collection<FormatDataKey> discoveredKeys) {
        for (final DDiagramElement child : container.getOwnedDiagramElements()) {
            if (child instanceof AbstractDNode) {
                checkDataAndAddChildFormat(parentFormatData, (AbstractDNode) child, parentEditPart, discoveredKeys);
            }
        }
        for (final DNode child : container.getOwnedBorderedNodes()) {
            checkDataAndAddChildFormat(parentFormatData, child, parentEditPart, discoveredKeys);
        }
    }

    /**
     * Add children of the node.
     *
     * @param nodeList
     *            The parent of the children
     * @param parentFormatData
     *            The corresponding formatData
     * @param parentEditPart
     *            The editPart corresponding to the parent FormatData
     * @param discoveredKeys
     *            The {@link FormatDataKey} discovered during the current store action.
     */
    protected void addNodeListChildren(final DNodeList nodeList, final NodeFormatData parentFormatData, final IGraphicalEditPart parentEditPart, Collection<FormatDataKey> discoveredKeys) {
        for (final DNode child : nodeList.getOwnedBorderedNodes()) {
            checkDataAndAddChildFormat(parentFormatData, child, parentEditPart, discoveredKeys);
        }
    }

    /**
     * Add the child format of the diagram.
     *
     * @param diagram
     *            the diagram
     * @param editPart
     *            The viewer responsible for the current editparts lifecycle
     */
    private void addChildFormat(final DDiagram diagram, final IGraphicalEditPart diagramEditPart, final Collection<FormatDataKey> discoveredKeys) {

        for (final AbstractDNode child : Iterables.filter(diagram.getOwnedDiagramElements(), AbstractDNode.class)) {
            // Search the GMF node corresponding to the child
            final Node gmfNode = SiriusGMFHelper.getGmfNode(child);
            if (gmfNode != null) {
                final IGraphicalEditPart editPart = (IGraphicalEditPart) diagramEditPart.getRoot().getViewer().getEditPartRegistry().get(gmfNode);
                if (editPart != null) {
                    addChildFormat(null, child, gmfNode, editPart, discoveredKeys);
                }
            }
        }
    }

    /**
     * Add a format.
     *
     * @param parentFormatData
     *            The parent format data
     * @param child
     *            The child from which we want to add a new format
     * @param gmfNode
     *            The corresponding GMF node.
     * @param editPart
     *            The editPart corresponding to the new format
     */
    private void addChildFormat(final NodeFormatData parentFormatData, final DRepresentationElement child, final Node gmfNode, final IGraphicalEditPart editPart,
            final Collection<FormatDataKey> discoveredKeys) {
        final NodeFormatData childFormatData = FormatDataHelper.INSTANCE.createNodeFormatData(gmfNode, editPart, parentFormatData);
        if (parentFormatData != null) {
            parentFormatData.getChildren().add(childFormatData);
        }

        FormatDataKey childKey = createKey(child);
        childFormatData.setId(childKey.getId());

        // If the current node have the same key than than one of the previously
        // inspected node, the previously computed data might be replaced. It
        // could so replaced one of the initially selected parts.
        if (!discoveredKeys.contains(childKey)) {
            this.addFormatData(childKey, child.getMapping(), childFormatData);
            discoveredKeys.add(childKey);
        } else if (parentFormatData == null) {
            // In this case, the same key is used for a root format data and for
            // an other view (child or border of an other view), the root data
            // should be stored.
            this.addFormatData(childKey, child.getMapping(), childFormatData);
        }

        if (child instanceof DNode) {
            addNodeChildren((DNode) child, childFormatData, editPart, gmfNode, discoveredKeys);
        } else if (child instanceof DNodeContainer) {
            addNodeContainerChildren((DNodeContainer) child, childFormatData, editPart, discoveredKeys);
        } else if (child instanceof DNodeList) {
            addNodeListChildren((DNodeList) child, childFormatData, editPart, discoveredKeys);
        } else {
            logUnhandledDiagramElementKindMessage(child);
        }
        if (child instanceof EdgeTarget) {
            addOutgoingEdge(childFormatData, (EdgeTarget) child, editPart.getRoot().getViewer());
        }
    }

    /**
     * Add outgoing edge of the edgeTarget.
     *
     * @param parentFormatData
     *            The parent format data
     * @param sourceOfEdge
     *            The DDiagramElement that is the source of the edge
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     */
    protected void addOutgoingEdge(final NodeFormatData parentFormatData, final EdgeTarget sourceOfEdge, final EditPartViewer editPartViewer) {
        for (final DEdge outgoingEdge : sourceOfEdge.getOutgoingEdges()) {
            addEdgeFormatData(parentFormatData, outgoingEdge, editPartViewer);
        }
    }

    /**
     * Add edge format data.
     *
     * @param parentFormatData
     *            The parent format data
     * @param edge
     *            The DEdge
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     */
    protected void addEdgeFormatData(final NodeFormatData parentFormatData, final DEdge edge, final EditPartViewer editPartViewer) {
        // Search the GMF edge corresponding to the child
        final Edge gmfEdge = SiriusGMFHelper.getGmfEdge(edge);
        if (gmfEdge != null) {
            final EdgeFormatData edgeFormatData = FormatDataHelper.INSTANCE.createEdgeFormatData(gmfEdge, (ConnectionEditPart) editPartViewer.getEditPartRegistry().get(gmfEdge));
            if (parentFormatData != null) {
                parentFormatData.getOutgoingEdges().add(edgeFormatData);
            }

            FormatDataKey edgeKey = createKey(edge);
            edgeFormatData.setId(edgeKey.getId());

            // Add the edge format data.
            this.addFormatData(edgeKey, edge.getMapping(), edgeFormatData);
            // Add the label format data (if exists).
            addLabelFormatData(edgeFormatData, gmfEdge);
        }
    }

    /**
     * Add the format data of the label of the edge. This format data sets the <code>edgeLabelFormatData</code> of the
     * {@link EdgeFormatData}. It's not added to the format data with a key in the manager.
     *
     * @param parentFormatData
     *            The edge format data
     * @param element
     *            The DEdge
     * @param gmfElement
     *            The edge corresponding view
     */
    private void addLabelFormatData(final AbstractFormatData parentFormatData, final View gmfElement) {
        final Node labelNode = SiriusGMFHelper.getLabelNode(gmfElement);
        if (labelNode != null && parentFormatData != null) {
            final NodeFormatData labelFormatData = FormatDataHelper.INSTANCE.createLabelFormatData(labelNode);
            if (labelNode.getElement() instanceof DSemanticDecorator) {
                labelFormatData.setId(createKey((DSemanticDecorator) labelNode.getElement()).getId());
            }
            parentFormatData.setLabel(labelFormatData);
        }
    }

    /**
     * Check for each attribute of newStyle if it is the same in oldStyle. On the other hand, this attribute is added to
     * the custom features of the newStyle.
     * 
     * @param oldStyle
     *            The old style to compare with
     * @param newStyle
     *            The new style in which to add custom features.
     */
    protected void computeCustomFeatures(Style oldStyle, Style newStyle) {
        for (EAttribute attribute : newStyle.eClass().getEAllAttributes()) {
            if (!ViewpointPackage.Literals.CUSTOMIZABLE__CUSTOM_FEATURES.equals(attribute)) {
                EAttribute attributeOfOldStyle = getCorrespondingEAttribute(attribute, oldStyle);
                if (attributeOfOldStyle != null) {
                    if (newStyle.eIsSet(attribute)) {
                        if (!newStyle.eGet(attribute).equals(oldStyle.eGet(attributeOfOldStyle))) {
                            newStyle.getCustomFeatures().add(attributeOfOldStyle.getName());
                        }
                    } else if (oldStyle.eIsSet(attributeOfOldStyle)) {
                        newStyle.getCustomFeatures().add(attributeOfOldStyle.getName());
                    }
                }
            }
        }
    }

    private EAttribute getCorrespondingEAttribute(EAttribute attribute, Style style) {
        EAttribute result = null;
        if (style.eClass().getFeatureID(attribute) != -1) {
            result = attribute;
        } else {
            // This attribute does not exist in the style. Check specific
            // mapping cases.
            EStructuralFeature structuralFeature = style.eClass().getEStructuralFeature(attribute.getName());
            if (structuralFeature instanceof EAttribute) {
                result = (EAttribute) structuralFeature;
            } else if ("color".equals(attribute.getName())) { //$NON-NLS-1$
                structuralFeature = style.eClass().getEStructuralFeature("backgroundColor"); //$NON-NLS-1$
                if (structuralFeature instanceof EAttribute) {
                    result = (EAttribute) structuralFeature;
                }
            } else if ("backgroundColor".equals(attribute.getName())) { //$NON-NLS-1$
                structuralFeature = style.eClass().getEStructuralFeature("color"); //$NON-NLS-1$
                if (structuralFeature instanceof EAttribute) {
                    result = (EAttribute) structuralFeature;
                }
            } else if ("width".equals(attribute.getName())) { //$NON-NLS-1$
                structuralFeature = style.eClass().getEStructuralFeature("horizontalDiameter"); //$NON-NLS-1$
                if (structuralFeature instanceof EAttribute) {
                    result = (EAttribute) structuralFeature;
                }
            } else if ("horizontalDiameter".equals(attribute.getName())) { //$NON-NLS-1$
                structuralFeature = style.eClass().getEStructuralFeature("width"); //$NON-NLS-1$
                if (structuralFeature instanceof EAttribute) {
                    result = (EAttribute) structuralFeature;
                }
            } else if ("height".equals(attribute.getName())) { //$NON-NLS-1$
                structuralFeature = style.eClass().getEStructuralFeature("verticalDiameter"); //$NON-NLS-1$
                if (structuralFeature instanceof EAttribute) {
                    result = (EAttribute) structuralFeature;
                }
            } else if ("verticalDiameter".equals(attribute.getName())) { //$NON-NLS-1$
                structuralFeature = style.eClass().getEStructuralFeature("height"); //$NON-NLS-1$
                if (structuralFeature instanceof EAttribute) {
                    result = (EAttribute) structuralFeature;
                }
            }
        }
        return result;
    }
}
