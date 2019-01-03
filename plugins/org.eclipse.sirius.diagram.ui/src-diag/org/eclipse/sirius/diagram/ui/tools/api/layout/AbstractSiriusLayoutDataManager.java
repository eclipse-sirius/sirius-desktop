/*******************************************************************************
 * Copyright (c) 2009, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.api.layout;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
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
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.layoutdata.AbstractLayoutData;
import org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData;
import org.eclipse.sirius.diagram.layoutdata.LayoutdataFactory;
import org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage;
import org.eclipse.sirius.diagram.layoutdata.NodeLayoutData;
import org.eclipse.sirius.diagram.layoutdata.Point;
import org.eclipse.sirius.diagram.ui.business.api.query.NodeQuery;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.operation.CenterEdgeEndModelChangeOperation;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.internal.refresh.borderednode.CanonicalDBorderItemLocator;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;
import org.eclipse.sirius.ext.draw2d.figure.FigureUtilities;
import org.eclipse.sirius.tools.internal.SiriusCopierHelper;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

import com.google.common.collect.Iterables;

/**
 * An abstract implementation for {@link SiriusLayoutDataManager}. <BR>
 * Provide a method to store a layout from a graphicalEditPart and iterates on it's children.
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * @deprecated since Sirius 4.1.0. Use
 *             {@link org.eclipse.sirius.diagram.ui.tools.api.format.AbstractSiriusFormatDataManager} instead.
 *
 */
@Deprecated
public abstract class AbstractSiriusLayoutDataManager implements SiriusLayoutDataManager {

    private static final Class<?>[] CLASS_EXCEPTIONS = new Class[] { DNodeListElement.class };

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager#storeLayoutData(org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart)
     */
    @Override
    public void storeLayoutData(final IGraphicalEditPart rootEditPart) {
        final Collection<LayoutDataKey> discoveredKeys = new HashSet<>();
        final EObject semanticElement = rootEditPart.resolveSemanticElement();
        final View toStoreView = (View) rootEditPart.getModel();
        if (toStoreView instanceof Edge && semanticElement instanceof DEdge) {
            addEdgeLayoutData(null, (DEdge) semanticElement, rootEditPart.getRoot().getViewer());
        } else if (toStoreView instanceof Diagram && semanticElement instanceof DDiagram) {
            addChildLayout((DDiagram) semanticElement, rootEditPart, discoveredKeys);
        } else if (toStoreView instanceof Node) {
            if (semanticElement instanceof DDiagramElement && semanticElement instanceof DSemanticDecorator) {
                addChildLayout(null, (DSemanticDecorator) semanticElement, (Node) toStoreView, rootEditPart, discoveredKeys);
            }
        }
        discoveredKeys.clear();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager#applyLayout(org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart,
     *      org.eclipse.gef.EditPartViewer)
     */
    @Override
    public void applyFormat(final IGraphicalEditPart rootEditPart) {
        applyFormat(rootEditPart, true, true);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager#applyLayout(org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart,
     *      org.eclipse.gef.EditPartViewer)
     */
    @Override
    public void applyLayout(final IGraphicalEditPart rootEditPart) {
        applyFormat(rootEditPart, true, false);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager#applyStyle(org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart,
     *      org.eclipse.gef.EditPartViewer)
     */
    @Override
    public void applyStyle(final IGraphicalEditPart rootEditPart) {
        applyFormat(rootEditPart, false, true);
    }

    /**
     * Apply the format to the <code>rootEditPart</code>.
     * 
     * @param rootEditPart
     *            The root edit from which we would try to apply the current stored format
     * @param applyLayout
     *            true if the layout must be applied, false otherwise
     * @param applyStyle
     *            true if the style must be applied, false otherwise
     */
    protected void applyFormat(final IGraphicalEditPart rootEditPart, boolean applyLayout, boolean applyStyle) {
        final EObject semanticElement = rootEditPart.resolveSemanticElement();
        final View toStoreView = (View) rootEditPart.getModel();
        if (toStoreView instanceof Edge) {
            // Currently not managed...
        } else if (toStoreView instanceof Diagram && semanticElement instanceof DDiagram) {
            applyFormat((DDiagram) semanticElement, (Diagram) toStoreView, rootEditPart.getRoot().getViewer(), applyLayout, applyStyle);
            centerEdgesEnds(toStoreView);
        } else if (toStoreView instanceof Node) {
            if (semanticElement instanceof DDiagramElement && semanticElement instanceof DSemanticDecorator) {
                applyFormat((DSemanticDecorator) semanticElement, (Node) toStoreView, rootEditPart.getRoot().getViewer(), null, applyLayout, applyStyle);
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
     * @param semanticDecorator
     * @param toStoreView
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     */
    private void applyFormat(final DDiagram diagram, final Diagram toStoreView, final EditPartViewer editPartViewer, boolean applyLayout, boolean applyStyle) {
        // We don't apply layout on diagram but only on its node children (the
        // edge is applied during source node).
        for (final AbstractDNode node : Iterables.filter(diagram.getOwnedDiagramElements(), AbstractDNode.class)) {
            final Node gmfNode = SiriusGMFHelper.getGmfNode(node);
            if (gmfNode != null) {
                applyFormat(node, gmfNode, editPartViewer, null, applyLayout, applyStyle);
            }
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
        final EdgeLayoutData layoutData = (EdgeLayoutData) getLayoutData(createKey(edge));
        if (layoutData != null) {
            if (applyLayout) {
                final Bendpoints bendpoints = convertPointsToGMFBendpoint(layoutData);
                gmfEdge.setBendpoints(bendpoints);

                if (layoutData.getSourceTerminal() != null) {
                    if (gmfEdge.getSourceAnchor() == null) {
                        gmfEdge.setSourceAnchor(NotationFactory.eINSTANCE.createIdentityAnchor());
                    }
                    if (gmfEdge.getSourceAnchor() instanceof IdentityAnchor) {
                        ((IdentityAnchor) gmfEdge.getSourceAnchor()).setId(layoutData.getSourceTerminal());
                    }
                } else if (gmfEdge.getSourceAnchor() instanceof IdentityAnchor) {
                    gmfEdge.setSourceAnchor(null);
                }
                if (layoutData.getTargetTerminal() != null) {
                    if (gmfEdge.getTargetAnchor() == null) {
                        gmfEdge.setTargetAnchor(NotationFactory.eINSTANCE.createIdentityAnchor());
                    }
                    if (gmfEdge.getTargetAnchor() instanceof IdentityAnchor) {
                        ((IdentityAnchor) gmfEdge.getTargetAnchor()).setId(layoutData.getTargetTerminal());
                    }
                } else if (gmfEdge.getTargetAnchor() instanceof IdentityAnchor) {
                    gmfEdge.setTargetAnchor(null);
                }
                final RoutingStyle routingStyle = (RoutingStyle) gmfEdge.getStyle(NotationPackage.eINSTANCE.getRoutingStyle());
                if (routingStyle != null) {
                    routingStyle.setRouting(Routing.get(layoutData.getRouting()));
                    routingStyle.setJumpLinkStatus(JumpLinkStatus.get(layoutData.getJumpLinkStatus()));
                    routingStyle.setJumpLinkType(JumpLinkType.get(layoutData.getJumpLinkType()));
                    routingStyle.setJumpLinksReverse(layoutData.isReverseJumpLink());
                    routingStyle.setSmoothness(Smoothness.get(layoutData.getSmoothness()));
                }
            }
            if (applyStyle) {
                // Apply Sirius style properties
                applySiriusStyle(edge, layoutData);
                // Apply GMF style properties
                applyGMFStyle(gmfEdge, layoutData);
            }

            applyLabelFormat(gmfEdge, layoutData, applyLayout, applyStyle);

        }
    }

    private void applyLabelFormat(final View gmfView, final AbstractLayoutData parentLayoutData, boolean applyLayout, boolean applyStyle) {
        if (parentLayoutData != null) {
            final Node labelNode = SiriusGMFHelper.getLabelNode(gmfView);
            if (parentLayoutData.getLabel() != null && labelNode != null) {
                if (applyLayout) {
                    if (!parentLayoutData.getLabel().eIsSet(LayoutdataPackage.eINSTANCE.getNodeLayoutData_Width())
                            && !parentLayoutData.getLabel().eIsSet(LayoutdataPackage.eINSTANCE.getNodeLayoutData_Height())) {
                        Location location = NotationFactory.eINSTANCE.createLocation();
                        location.setX(parentLayoutData.getLabel().getLocation().getX());
                        location.setY(parentLayoutData.getLabel().getLocation().getY());
                        labelNode.setLayoutConstraint(location);
                    } else {
                        Bounds bounds = NotationFactory.eINSTANCE.createBounds();
                        bounds.setX(parentLayoutData.getLabel().getLocation().getX());
                        bounds.setY(parentLayoutData.getLabel().getLocation().getY());
                        bounds.setWidth(parentLayoutData.getLabel().getWidth());
                        bounds.setHeight(parentLayoutData.getLabel().getHeight());
                        labelNode.setLayoutConstraint(bounds);
                    }
                }
                if (applyStyle) {
                    // Apply GMF style properties
                    applyGMFStyle(labelNode, parentLayoutData.getLabel());
                }
            }
        }
    }

    /**
     * @param edgeLayoutData
     *            The layout data of the edge
     * @return
     */
    private Bendpoints convertPointsToGMFBendpoint(final EdgeLayoutData edgeLayoutData) {
        final RelativeBendpoints result = NotationFactory.eINSTANCE.createRelativeBendpoints();

        final List<RelativeBendpoint> relativeBendpoints = new LinkedList<RelativeBendpoint>();

        final Point source = edgeLayoutData.getSourceRefPoint();
        final Point target = edgeLayoutData.getTargetRefPoint();

        /* source and target may be null if edit part was not created */
        if (source != null && target != null) {
            final org.eclipse.draw2d.geometry.Point sourceRefPoint = new org.eclipse.draw2d.geometry.Point(source.getX(), source.getY());
            final org.eclipse.draw2d.geometry.Point targetRefPoint = new org.eclipse.draw2d.geometry.Point(target.getX(), target.getY());

            for (final Point point : edgeLayoutData.getPointList()) {
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
     * Search a layout corresponding to the semantic decorator and applies it to the node. Then it applies to it's
     * children and outgoing edges.
     *
     * @param semanticDecorator
     *            The semantic decorator to search the corresponding layout
     * @param toRestoreView
     *            Node on which to apply the layout
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     * @parentLayoutData the layout of the parent of <code>toRestoreView<code>
     */
    private void applyFormat(final DSemanticDecorator semanticDecorator, final Node toRestoreView, final EditPartViewer editPartViewer, final NodeLayoutData parentLayoutData, boolean applyLayout,
            boolean applyStyle) {
        LayoutDataKey key = createKey(semanticDecorator);
        NodeLayoutData layoutData = (NodeLayoutData) getLayoutData(key);

        // If a direct child have the same layout data and key than its parents,
        // look in the parent layout data 's children for a child layout data
        // with the expected id.
        if (parentLayoutData != null && parentLayoutData == layoutData && !StringUtil.isEmpty(key.getId())) {
            layoutData = null;
            for (NodeLayoutData childLayoutData : parentLayoutData.getChildren()) {
                // if many children layout with same id, a choice will not be
                // possible;
                if (key.getId().equals(childLayoutData.getId())) {
                    if (layoutData == null) {
                        layoutData = childLayoutData;
                    } else {
                        layoutData = null;
                        break;
                    }
                }
            }
        }

        if (layoutData != null && applyLayout) {

            final Bounds bounds = NotationFactory.eINSTANCE.createBounds();
            final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) editPartViewer.getEditPartRegistry().get(toRestoreView);
            Point locationToApply;
            boolean isCollapsed = false;
            if (graphicalEditPart instanceof AbstractDiagramBorderNodeEditPart) {
                // Specific treatment for border node
                // Compute absolute location
                locationToApply = LayoutDataHelper.INSTANCE.getAbsoluteLocation(layoutData);
                // Compute the best location according to other existing
                // bordered nodes.
                Node parentNode = (Node) toRestoreView.eContainer();
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
                final Rectangle rect = new Rectangle(locationToApply.getX(), locationToApply.getY(), layoutData.getWidth(), layoutData.getHeight());
                final org.eclipse.draw2d.geometry.Point realLocation = locator.getValidLocation(rect, toRestoreView, new ArrayList<Node>(Arrays.asList(toRestoreView)));
                // Compute the new relative position to the parent
                final org.eclipse.draw2d.geometry.Point parentAbsoluteLocation = ((IGraphicalEditPart) graphicalEditPart.getParent()).getFigure().getBounds().getTopLeft().getCopy();
                FigureUtilities.translateToAbsoluteByIgnoringScrollbar(((IGraphicalEditPart) graphicalEditPart.getParent()).getFigure(), parentAbsoluteLocation);
                locationToApply.setX(realLocation.x);
                locationToApply.setY(realLocation.y);
                locationToApply = LayoutDataHelper.INSTANCE.getTranslated(locationToApply, parentAbsoluteLocation.negate());
            } else {
                locationToApply = LayoutDataHelper.INSTANCE.getRelativeLocation(layoutData, graphicalEditPart);

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
                bounds.setHeight(layoutData.getHeight());
                bounds.setWidth(layoutData.getWidth());
            }
            toRestoreView.setLayoutConstraint(bounds);
        }
        if (layoutData != null && applyStyle) {
            // Apply Sirius style properties
            applySiriusStyle(semanticDecorator, layoutData);
            // Apply GMF style properties
            applyGMFStyle(toRestoreView, layoutData);
        }

        if (semanticDecorator instanceof DNode) {
            applyFormatToNodeChildren((DNode) semanticDecorator, editPartViewer, layoutData, applyLayout, applyStyle);
        } else if (semanticDecorator instanceof DNodeContainer) {
            applyFormatToNodeContainerChildren((DNodeContainer) semanticDecorator, editPartViewer, layoutData, applyLayout, applyStyle);
        } else if (semanticDecorator instanceof DNodeList) {
            applyFormatToNodeListChildren((DNodeList) semanticDecorator, editPartViewer, layoutData, applyLayout, applyStyle);
        } else {
            logWarnMessage(semanticDecorator);
        }
        // Deal with the outgoing edges
        if (semanticDecorator instanceof EdgeTarget) {
            applyFormatToOutgoingEdge((EdgeTarget) semanticDecorator, editPartViewer, applyLayout, applyStyle);
        }
    }

    /**
     * Apply the Sirius style contained in <code>layoutData</code> on the <code>semanticDecorator</code>.
     * 
     * @param semanticDecorator
     *            The {@link DSemanticDecorator} on which to apply the style.
     * @param layoutData
     *            The layout data containing the sirius style
     */
    protected void applySiriusStyle(DSemanticDecorator semanticDecorator, AbstractLayoutData layoutData) {
        // Make a copy of the style to allow several Paste with the same LayoutData.
        Style copyOfSiriusStyle = SiriusCopierHelper.copyWithNoUidDuplication(layoutData.getSiriusStyle());
        if ((semanticDecorator instanceof DNode || semanticDecorator instanceof DNodeListElement) && copyOfSiriusStyle instanceof NodeStyle) {
            if (semanticDecorator instanceof DNode) {
                computeCustomFeatures(((DNode) semanticDecorator).getOwnedStyle(), copyOfSiriusStyle);
                ((DNode) semanticDecorator).setOwnedStyle((NodeStyle) copyOfSiriusStyle);
            } else {
                computeCustomFeatures(((DNodeListElement) semanticDecorator).getOwnedStyle(), copyOfSiriusStyle);
                ((DNodeListElement) semanticDecorator).setOwnedStyle((NodeStyle) copyOfSiriusStyle);
            }
        } else if (semanticDecorator instanceof DDiagramElementContainer && copyOfSiriusStyle instanceof ContainerStyle) {
            computeCustomFeatures(((DDiagramElementContainer) semanticDecorator).getOwnedStyle(), copyOfSiriusStyle);
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
     * @param layoutData
     *            The layout data containing the old view to copy style features from
     */
    @SuppressWarnings("unchecked")
    protected void applyGMFStyle(View newView, AbstractLayoutData layoutData) {
        if (newView != null && layoutData.getGmfView() != null) {
            @SuppressWarnings("rawtypes")
            List excludedStyles = new ArrayList<>();
            if (newView instanceof Edge) {
                // The style of RoutingStyle class is considered as layout
                // properties. So they have already been pasted during paste
                // layout.
                excludedStyles.add(NotationPackage.eINSTANCE.getRoutingStyle());
            }
            new ViewRefactorHelper().copyViewAppearance(layoutData.getGmfView(), newView, excludedStyles);
        }
    }

    private void logWarnMessage(final DSemanticDecorator semanticDecorator) {
        final Class<?> clazz = semanticDecorator.getClass();

        boolean logWarn = true;
        for (final Class<?> exceptionClass : CLASS_EXCEPTIONS) {
            if (exceptionClass.isAssignableFrom(clazz)) {
                logWarn = false;
                break;
            }
        }

        if (logWarn) {
            DiagramPlugin.getDefault().logWarning(MessageFormat.format(Messages.AbstractSiriusLayoutDataManager_unhandledDiagramElementKind, semanticDecorator.getClass().getName()));
        }
    }

    /**
     * Try to apply a layout to the children of the {@link DNode}.
     *
     * @param parentNode
     *            The parent containing children to apply layout on.
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     */
    private void applyFormatToNodeChildren(final DNode parentNode, final EditPartViewer editPartViewer, final NodeLayoutData layoutData, boolean applyLayout, boolean applyStyle) {
        // Restore Bordered nodes
        applyFormatForBorderedNodes(parentNode.getOwnedBorderedNodes(), editPartViewer, layoutData, applyLayout, applyStyle);
        // Restore label
        final Node gmfNode = SiriusGMFHelper.getGmfNode(parentNode);
        applyLabelFormat(gmfNode, layoutData, applyLayout, applyStyle);
    }

    /**
     * Try to apply a layout to the children of the {@link DNodeContainer}.
     *
     * @param container
     *            The parent containing children to apply layout on.
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     */
    private void applyFormatToNodeContainerChildren(final DNodeContainer container, final EditPartViewer editPartViewer, final NodeLayoutData layoutData, boolean applyLayout, boolean applyStyle) {
        // Restore children
        for (final DDiagramElement child : container.getOwnedDiagramElements()) {
            if (child instanceof AbstractDNode) {
                // Search the GMF node corresponding to the child
                final Node gmfNode = SiriusGMFHelper.getGmfNode(child);
                if (gmfNode != null) {
                    applyFormat(child, gmfNode, editPartViewer, layoutData, applyLayout, applyStyle);
                }
            }
        }
        // Restore Bordered nodes
        applyFormatForBorderedNodes(container.getOwnedBorderedNodes(), editPartViewer, layoutData, applyLayout, applyStyle);
        // Restore label
        final Node gmfNode = SiriusGMFHelper.getGmfNode(container);
        applyLabelFormat(gmfNode, layoutData, applyLayout, applyStyle);
    }

    /**
     * Try to apply the layout to the bordered nodes.
     *
     * @param borderedNodes
     *            The list of bordered nodes to deals with
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     * @param parentLayoutData
     *            The layoutData of the parent of the borderedNodes
     */
    private void applyFormatForBorderedNodes(EList<DNode> borderedNodes, EditPartViewer editPartViewer, NodeLayoutData parentLayoutData, boolean applyLayout, boolean applyStyle) {
        HashMap<Node, NodeLayoutData> nodesWithLayoutDataToApply = new HashMap<>();
        HashMap<Node, DSemanticDecorator> nodesWithCoresspondingDSemanticDecorator = new HashMap<>();
        // Search each bordered nodes that have layoutData to apply
        for (final DNode child : borderedNodes) {
            // Search the GMF node corresponding to the child
            final Node gmfNode = SiriusGMFHelper.getGmfNode(child);
            if (gmfNode != null) {
                LayoutDataKey key = createKey(child);
                NodeLayoutData layoutData = (NodeLayoutData) getLayoutData(key);

                // If a direct child have the same layout data and key than its
                // parents, look in the parent layout data 's children for a
                // child layout data with the expected id.
                if (parentLayoutData != null && parentLayoutData == layoutData && !StringUtil.isEmpty(key.getId())) {
                    layoutData = null;
                    for (NodeLayoutData childLayoutData : parentLayoutData.getChildren()) {
                        // if many children layout with same id, a choice will
                        // not be possible
                        if (key.getId().equals(childLayoutData.getId())) {
                            if (layoutData == null) {
                                layoutData = childLayoutData;
                            } else {
                                layoutData = null;
                                break;
                            }
                        }
                    }
                }
                if (layoutData != null) {
                    nodesWithLayoutDataToApply.put(gmfNode, layoutData);
                    nodesWithCoresspondingDSemanticDecorator.put(gmfNode, child);
                }
            }
        }
        // Iterate over each bordered nodes which have layout data to apply to
        Set<Node> toIgnore = nodesWithLayoutDataToApply.keySet();
        for (Entry<Node, NodeLayoutData> entry : nodesWithLayoutDataToApply.entrySet()) {
            Node node = entry.getKey();
            applyFormatForBorderedNode(nodesWithCoresspondingDSemanticDecorator.get(node), node, editPartViewer, entry.getValue(), toIgnore, applyLayout, applyStyle);
        }
    }

    /**
     * Try to apply the layout to a bordered node.
     *
     * @param semanticDecorator
     *            The semantic decorator associated with this Node
     * @param toRestoreView
     *            Node on which to apply the layout
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     * @param layoutData
     *            the layout to apply on <code>toRestoreView<code>
     * @param portsNodesToIgnore
     *            The list of bordered nodes to ignore in the conflict detection
     */
    private void applyFormatForBorderedNode(final DSemanticDecorator semanticDecorator, final Node toRestoreView, final EditPartViewer editPartViewer, final NodeLayoutData layoutData,
            final Set<Node> portsNodesToIgnore, boolean applyLayout, boolean applyStyle) {
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
                locationToApply = LayoutDataHelper.INSTANCE.getAbsoluteLocation(layoutData);
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

                final Rectangle rect = new Rectangle(locationToApply.getX(), locationToApply.getY(), layoutData.getWidth(), layoutData.getHeight());

                final org.eclipse.draw2d.geometry.Point realLocation = locator.getValidLocation(rect, toRestoreView, portsNodesToIgnore);

                // Compute the new relative position to the parent
                final org.eclipse.draw2d.geometry.Point parentAbsoluteLocation = GMFHelper.getAbsoluteBounds(parentNode, true).getTopLeft();
                locationToApply.setX(realLocation.x);
                locationToApply.setY(realLocation.y);
                locationToApply = LayoutDataHelper.INSTANCE.getTranslated(locationToApply, parentAbsoluteLocation.negate());

            } else {
                Object graphicalEditPart = editPartViewer.getEditPartRegistry().get(toRestoreView);
                if (graphicalEditPart instanceof IGraphicalEditPart) {
                    locationToApply = LayoutDataHelper.INSTANCE.getRelativeLocation(layoutData, (IGraphicalEditPart) graphicalEditPart);
                    // Apply the location to the figure to, to correctly compute
                    // the relative location of the children
                    ((GraphicalEditPart) graphicalEditPart).getFigure().setLocation(new org.eclipse.draw2d.geometry.Point(locationToApply.getX(), locationToApply.getY()));
                } else {
                    locationToApply = LayoutdataFactory.eINSTANCE.createPoint();
                }
            }
            bounds.setX(locationToApply.getX());
            bounds.setY(locationToApply.getY());
            if (isCollapsed) {
                Dimension dim = new NodeQuery(toRestoreView).getCollapsedSize();
                bounds.setHeight(dim.height);
                bounds.setWidth(dim.width);
            } else {
                bounds.setHeight(layoutData.getHeight());
                bounds.setWidth(layoutData.getWidth());
            }

            toRestoreView.setLayoutConstraint(bounds);
        }
        if (applyStyle) {
            // Apply Sirius style properties
            applySiriusStyle(semanticDecorator, layoutData);
            // Apply GMF style properties
            applyGMFStyle(toRestoreView, layoutData);
        }

        if (semanticDecorator instanceof DNode) {
            applyFormatToNodeChildren((DNode) semanticDecorator, editPartViewer, layoutData, applyLayout, applyStyle);
        } else if (semanticDecorator instanceof DNodeContainer) {
            applyFormatToNodeContainerChildren((DNodeContainer) semanticDecorator, editPartViewer, layoutData, applyLayout, applyStyle);
        } else if (semanticDecorator instanceof DNodeList) {
            applyFormatToNodeListChildren((DNodeList) semanticDecorator, editPartViewer, layoutData, applyLayout, applyStyle);
        } else {
            logWarnMessage(semanticDecorator);
        }
        if (semanticDecorator instanceof EdgeTarget) {
            applyFormatToOutgoingEdge((EdgeTarget) semanticDecorator, editPartViewer, applyLayout, applyStyle);
        }
    }

    /**
     * Try to apply a layout to the children of the {@link DNodeList}.
     *
     * @param nodeList
     *            The parent containing children to apply layout on.
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     */
    private void applyFormatToNodeListChildren(final DNodeList nodeList, final EditPartViewer editPartViewer, final NodeLayoutData layoutData, boolean applyLayout, boolean applyStyle) {
        // Restore Bordered nodes
        applyFormatForBorderedNodes(nodeList.getOwnedBorderedNodes(), editPartViewer, layoutData, applyLayout, applyStyle);

        // Restore label
        final Node gmfNode = SiriusGMFHelper.getGmfNode(nodeList);
        applyLabelFormat(gmfNode, layoutData, applyLayout, applyStyle);
    }

    /**
     * Add the layout for the children of a node.
     *
     * @param parentNode
     *            The parent of the children
     * @param parentLayoutData
     *            The corresponding layoutData
     * @param parentEditPart
     *            The editPart corresponding to the parent LayoutData
     * @param gmfView
     *            GMF view
     * @param discoveredKeys
     *            The {@link LayoutDataKey} discovered during the current store action.
     */
    protected void addNodeChildren(final DNode parentNode, final NodeLayoutData parentLayoutData, final IGraphicalEditPart parentEditPart, final View gmfView,
            Collection<LayoutDataKey> discoveredKeys) {
        for (final DNode child : parentNode.getOwnedBorderedNodes()) {
            checkDataAndAddChildLayout(parentLayoutData, child, parentEditPart, discoveredKeys);
        }
        // Add the label layout data (if exists).
        addLabelLayoutData(parentLayoutData, gmfView);
    }

    /**
     * Add a layout (if we have enough information : GMF view and editPart).
     *
     * @param parentLayoutData
     *            The parent layout data
     * @param child
     *            The child from which we want to add a new layout
     * @param parentSavedEditPart
     *            The previous saved editPart (corresponds to parentLayoutData)
     * @param discoveredKeys
     *            The {@link LayoutDataKey} discovered during the current store action.
     */
    protected void checkDataAndAddChildLayout(final NodeLayoutData parentLayoutData, final AbstractDNode child, final IGraphicalEditPart parentSavedEditPart,
            Collection<LayoutDataKey> discoveredKeys) {
        // Search the GMF node corresponding to the child
        final Node gmfNode = SiriusGMFHelper.getGmfNode(child);
        if (gmfNode != null) {
            final IGraphicalEditPart editPart = (IGraphicalEditPart) parentSavedEditPart.getRoot().getViewer().getEditPartRegistry().get(gmfNode);
            if (editPart != null) {
                addChildLayout(parentLayoutData, child, gmfNode, editPart, discoveredKeys);
            }
        }
    }

    /**
     * Add children of the node.
     *
     * @param container
     *            The parent of the children
     * @param parentLayoutData
     *            The corresponding layoutData
     * @param parentEditPart
     *            The editPart corresponding to the parent LayoutData
     * @param discoveredKeys
     *            The {@link LayoutDataKey} discovered during the current store action.
     */
    protected void addNodeContainerChildren(final DNodeContainer container, final NodeLayoutData parentLayoutData, final IGraphicalEditPart parentEditPart, Collection<LayoutDataKey> discoveredKeys) {
        for (final DDiagramElement child : container.getOwnedDiagramElements()) {
            if (child instanceof AbstractDNode) {
                checkDataAndAddChildLayout(parentLayoutData, (AbstractDNode) child, parentEditPart, discoveredKeys);
            }
        }
        for (final DNode child : container.getOwnedBorderedNodes()) {
            checkDataAndAddChildLayout(parentLayoutData, child, parentEditPart, discoveredKeys);
        }
    }

    /**
     * Add children of the node.
     *
     * @param nodeList
     *            The parent of the children
     * @param parentLayoutData
     *            The corresponding layoutData
     * @param parentEditPart
     *            The editPart corresponding to the parent LayoutData
     * @param discoveredKeys
     *            The {@link LayoutDataKey} discovered during the current store action.
     */
    protected void addNodeListChildren(final DNodeList nodeList, final NodeLayoutData parentLayoutData, final IGraphicalEditPart parentEditPart, Collection<LayoutDataKey> discoveredKeys) {
        for (final DNode child : nodeList.getOwnedBorderedNodes()) {
            checkDataAndAddChildLayout(parentLayoutData, child, parentEditPart, discoveredKeys);
        }
    }

    /**
     * Add the child layout of the diagram.
     *
     * @param diagram
     *            the diagram
     * @param editPart
     *            The viewer responsible for the current editparts lifecycle
     */
    private void addChildLayout(final DDiagram diagram, final IGraphicalEditPart diagramEditPart, final Collection<LayoutDataKey> discoveredKeys) {

        for (final AbstractDNode child : Iterables.filter(diagram.getOwnedDiagramElements(), AbstractDNode.class)) {
            // Search the GMF node corresponding to the child
            final Node gmfNode = SiriusGMFHelper.getGmfNode(child);
            if (gmfNode != null) {
                final IGraphicalEditPart editPart = (IGraphicalEditPart) diagramEditPart.getRoot().getViewer().getEditPartRegistry().get(gmfNode);
                if (editPart != null) {
                    addChildLayout(null, child, gmfNode, editPart, discoveredKeys);
                }
            }
        }
    }

    /**
     * Add a layout.
     *
     * @param parentLayoutData
     *            The parent layout data
     * @param child
     *            The child from which we want to add a new layout
     * @param gmfNode
     *            The corresponding GMF node.
     * @param editPart
     *            The editPart corresponding to the new layout
     */
    private void addChildLayout(final NodeLayoutData parentLayoutData, final DSemanticDecorator child, final Node gmfNode, final IGraphicalEditPart editPart,
            final Collection<LayoutDataKey> discoveredKeys) {
        final NodeLayoutData childLayoutData = LayoutDataHelper.INSTANCE.createNodeLayoutData(gmfNode, editPart, parentLayoutData);
        if (parentLayoutData != null) {
            parentLayoutData.getChildren().add(childLayoutData);
        }

        LayoutDataKey childKey = createKey(child);
        childLayoutData.setId(childKey.getId());

        // If the current node have the same key than than one of the previously
        // inspected node, the previously computed data might be replaced. It
        // could so replaced one of the initially selected parts.
        if (!discoveredKeys.contains(childKey)) {
            addLayoutData(childKey, childLayoutData);
            discoveredKeys.add(childKey);
        } else if (parentLayoutData == null) {
            // In this case, the same key is used for a root layout data and for
            // an other view (child or border of an other view), the root data
            // should be stored.
            addLayoutData(childKey, childLayoutData);
        }

        if (child instanceof DNode) {
            addNodeChildren((DNode) child, childLayoutData, editPart, gmfNode, discoveredKeys);
        } else if (child instanceof DNodeContainer) {
            addNodeContainerChildren((DNodeContainer) child, childLayoutData, editPart, discoveredKeys);
        } else if (child instanceof DNodeList) {
            addNodeListChildren((DNodeList) child, childLayoutData, editPart, discoveredKeys);
        } else {
            logWarnMessage(child);
        }
        if (child instanceof EdgeTarget) {
            addOutgoingEdge(childLayoutData, (EdgeTarget) child, editPart.getRoot().getViewer());
        }
    }

    /**
     * Add outgoing edge of the edgeTarget.
     *
     * @param parentLayoutData
     *            The parent layout data
     * @param sourceOfEdge
     *            The DDiagramElement that is the source of the edge
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     */
    protected void addOutgoingEdge(final NodeLayoutData parentLayoutData, final EdgeTarget sourceOfEdge, final EditPartViewer editPartViewer) {
        for (final DEdge outgoingEdge : sourceOfEdge.getOutgoingEdges()) {
            addEdgeLayoutData(parentLayoutData, outgoingEdge, editPartViewer);
        }
    }

    /**
     * Add edge layout data.
     *
     * @param parentLayoutData
     *            The parent layout data
     * @param edge
     *            The DEdge
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     */
    protected void addEdgeLayoutData(final NodeLayoutData parentLayoutData, final DEdge edge, final EditPartViewer editPartViewer) {
        // Search the GMF edge corresponding to the child
        final Edge gmfEdge = SiriusGMFHelper.getGmfEdge(edge);
        if (gmfEdge != null) {
            final EdgeLayoutData edgeLayoutData = LayoutDataHelper.INSTANCE.createEdgeLayoutData(gmfEdge, (ConnectionEditPart) editPartViewer.getEditPartRegistry().get(gmfEdge));
            if (parentLayoutData != null) {
                parentLayoutData.getOutgoingEdges().add(edgeLayoutData);
            }

            LayoutDataKey edgeKey = createKey(edge);
            edgeLayoutData.setId(edgeKey.getId());

            // Add the edge layout data.
            addLayoutData(edgeKey, edgeLayoutData);
            // Add the label layout data (if exists).
            addLabelLayoutData(edgeLayoutData, gmfEdge);
        }
    }

    /**
     * Add the layout data of the label of the edge. This layout data sets the <code>edgeLabelLayoutData</code> of the
     * {@link EdgeLayoutData}. It's not added to the layout data with a key in the manager.
     *
     * @param parentLayoutData
     *            The edge layout data
     * @param element
     *            The DEdge
     * @param gmfElement
     *            The edge corresponding view
     */
    private void addLabelLayoutData(final AbstractLayoutData parentLayoutData, final View gmfElement) {
        final Node labelNode = SiriusGMFHelper.getLabelNode(gmfElement);
        if (labelNode != null && parentLayoutData != null) {
            final NodeLayoutData labelLayoutData = LayoutDataHelper.INSTANCE.createLabelLayoutData(labelNode);
            if (labelNode.getElement() instanceof DSemanticDecorator) {
                labelLayoutData.setId(createKey((DSemanticDecorator) labelNode.getElement()).getId());
            }
            parentLayoutData.setLabel(labelLayoutData);
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
