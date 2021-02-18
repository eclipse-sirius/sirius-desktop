/*******************************************************************************
 * Copyright (c) 2009, 2021 THALES GLOBAL SERVICES and others.
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
import org.eclipse.sirius.diagram.formatdata.AbstractFormatData;
import org.eclipse.sirius.diagram.formatdata.EdgeFormatData;
import org.eclipse.sirius.diagram.formatdata.FormatdataFactory;
import org.eclipse.sirius.diagram.formatdata.FormatdataPackage;
import org.eclipse.sirius.diagram.formatdata.NodeFormatData;
import org.eclipse.sirius.diagram.formatdata.Point;
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
        } else if (toStoreView instanceof Node) {
            if (semanticElement instanceof DDiagramElement && semanticElement instanceof DSemanticDecorator) {
                addChildFormat(null, (DDiagramElement) semanticElement, (Node) toStoreView, rootEditPart, discoveredKeys);
            }
        }
        discoveredKeys.clear();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager#applyFormat(org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart,
     *      org.eclipse.gef.EditPartViewer)
     */
    @Override
    public void applyFormat(final IGraphicalEditPart rootEditPart) {
        applyFormat(rootEditPart, true, true);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager#applyFormat(org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart,
     *      org.eclipse.gef.EditPartViewer)
     */
    @Override
    public void applyLayout(final IGraphicalEditPart rootEditPart) {
        applyFormat(rootEditPart, true, false);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager#applyStyle(org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart,
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
     * @param applyFormat
     *            true if the format must be applied, false otherwise
     * @param applyStyle
     *            true if the style must be applied, false otherwise
     */
    protected void applyFormat(final IGraphicalEditPart rootEditPart, boolean applyFormat, boolean applyStyle) {
        final EObject semanticElement = rootEditPart.resolveSemanticElement();
        final View toStoreView = (View) rootEditPart.getModel();
        if (toStoreView instanceof Edge) {
            // Currently not managed...
        } else if (toStoreView instanceof Diagram && semanticElement instanceof DDiagram) {
            applyFormat((DDiagram) semanticElement, (Diagram) toStoreView, rootEditPart.getRoot().getViewer(), applyFormat, applyStyle);
            centerEdgesEnds(toStoreView);
        } else if (toStoreView instanceof Node) {
            if (semanticElement instanceof DDiagramElement && semanticElement instanceof DSemanticDecorator) {
                applyFormat((DDiagramElement) semanticElement, (Node) toStoreView, rootEditPart.getRoot().getViewer(), null, applyFormat, applyStyle);
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
    private void applyFormat(final DDiagram diagram, final Diagram toStoreView, final EditPartViewer editPartViewer, boolean applyFormat, boolean applyStyle) {
        // We don't apply format on diagram but only on its node children (the
        // edge is applied during source node).
        for (final AbstractDNode node : Iterables.filter(diagram.getOwnedDiagramElements(), AbstractDNode.class)) {
            final Node gmfNode = SiriusGMFHelper.getGmfNode(node);
            if (gmfNode != null) {
                applyFormat(node, gmfNode, editPartViewer, null, applyFormat, applyStyle);
            }
        }
    }

    /**
     * @param sourceNode
     * @param editPartViewer
     */
    private void applyFormatToOutgoingEdge(final EdgeTarget sourceNode, final EditPartViewer editPartViewer, boolean applyFormat, boolean applyStyle) {
        for (final DEdge edge : sourceNode.getOutgoingEdges()) {
            final Edge gmfEdge = SiriusGMFHelper.getGmfEdge(edge);
            if (gmfEdge != null) {
                applyFormat(edge, gmfEdge, editPartViewer, applyFormat, applyStyle);
            }
        }
    }

    /**
     * @param edge
     * @param gmfEdge
     * @param editPartViewer
     */
    private void applyFormat(final DEdge edge, final Edge gmfEdge, final EditPartViewer editPartViewer, boolean applyFormat, boolean applyStyle) {
        final EdgeFormatData formatData;
        formatData = (EdgeFormatData) this.getFormatData(createKey(edge), edge.getMapping());

        if (formatData != null) {
            if (applyFormat) {
                applyEdgeFormat(gmfEdge, formatData);
            }
            if (applyStyle) {
                // Apply Sirius style properties
                applySiriusStyle(edge, formatData);
                // Apply GMF style properties
                applyGMFStyle(gmfEdge, formatData);
            }

            applyLabelFormat(gmfEdge, formatData, applyFormat, applyStyle);

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

    private void applyLabelFormat(final View gmfView, final AbstractFormatData parentFormatData, boolean applyFormat, boolean applyStyle) {
        if (parentFormatData != null) {
            final Node labelNode = SiriusGMFHelper.getLabelNode(gmfView);
            if (parentFormatData.getLabel() != null && labelNode != null) {
                if (applyFormat) {
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
     * Search a format corresponding to the semantic decorator and applies it to the node. Then it applies to it's
     * children and outgoing edges.
     *
     * @param semanticDecorator
     *            The semantic decorator to search the corresponding format
     * @param toRestoreView
     *            Node on which to apply the format
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     * @parentFormatData the format of the parent of <code>toRestoreView<code>
     */
    private void applyFormat(final DRepresentationElement semanticDecorator, final Node toRestoreView, final EditPartViewer editPartViewer, final NodeFormatData parentFormatData, boolean applyFormat,
            boolean applyStyle) {
        FormatDataKey key = createKey(semanticDecorator);
        NodeFormatData formatData;
        formatData = (NodeFormatData) this.getFormatData(key, semanticDecorator.getMapping());
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
        if (formatData != null && applyFormat && editPartAsObject != null) {
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
            applySiriusStyle(semanticDecorator, formatData);
            // Apply GMF style properties
            applyGMFStyle(toRestoreView, formatData);
        }
        if (semanticDecorator instanceof DNode) {
            applyFormatToNodeChildren((DNode) semanticDecorator, editPartViewer, formatData, applyFormat, applyStyle);
        } else if (semanticDecorator instanceof DNodeContainer) {
            applyFormatToNodeContainerChildren((DNodeContainer) semanticDecorator, editPartViewer, formatData, applyFormat, applyStyle);
        } else if (semanticDecorator instanceof DNodeList) {
            applyFormatToNodeListChildren((DNodeList) semanticDecorator, editPartViewer, formatData, applyFormat, applyStyle);
        } else {
            logWarnMessage(semanticDecorator);
        }
        // Deal with the outgoing edges
        if (semanticDecorator instanceof EdgeTarget) {
            applyFormatToOutgoingEdge((EdgeTarget) semanticDecorator, editPartViewer, applyFormat, applyStyle);
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
     * Try to apply a format to the children of the {@link DNode}.
     *
     * @param parentNode
     *            The parent containing children to apply format on.
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     * @param formatData
     *            The format data to apply.
     * @param applyFormat
     *            Whether or not to apply format.
     * @param applyStyle
     *            Whether or not to apply style.
     */
    private void applyFormatToNodeChildren(final DNode parentNode, final EditPartViewer editPartViewer, final NodeFormatData formatData, boolean applyFormat, boolean applyStyle) {
        // Restore Bordered nodes
        applyFormatForBorderedNodes(parentNode.getOwnedBorderedNodes(), editPartViewer, formatData, applyFormat, applyStyle);
        // Restore label
        final Node gmfNode = SiriusGMFHelper.getGmfNode(parentNode);
        applyLabelFormat(gmfNode, formatData, applyFormat, applyStyle);
    }

    /**
     * Try to apply a format to the children of the {@link DNodeContainer}.
     *
     * @param container
     *            The parent containing children to apply format on.
     * @param editPartViewer
     *            The viewer responsible for the current editparts lifecycle.
     */
    private void applyFormatToNodeContainerChildren(final DNodeContainer container, final EditPartViewer editPartViewer, final NodeFormatData formatData, boolean applyFormat, boolean applyStyle) {
        // Restore children
        for (final DDiagramElement child : container.getOwnedDiagramElements()) {
            if (child instanceof AbstractDNode) {
                // Search the GMF node corresponding to the child
                final Node gmfNode = SiriusGMFHelper.getGmfNode(child);
                if (gmfNode != null) {
                    applyFormat(child, gmfNode, editPartViewer, formatData, applyFormat, applyStyle);
                }
            }
        }
        // Restore Bordered nodes
        applyFormatForBorderedNodes(container.getOwnedBorderedNodes(), editPartViewer, formatData, applyFormat, applyStyle);
        // Restore label
        final Node gmfNode = SiriusGMFHelper.getGmfNode(container);
        applyLabelFormat(gmfNode, formatData, applyFormat, applyStyle);
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
     * @param applyFormat
     *            Whether or not to apply format.
     * @param applyStyle
     *            Whether or not to apply style.
     */
    private void applyFormatForBorderedNodes(EList<DNode> borderedNodes, EditPartViewer editPartViewer, NodeFormatData parentFormatData, boolean applyFormat, boolean applyStyle) {
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
            applyFormatForBorderedNode(nodesWithCoresspondingDSemanticDecorator.get(node), node, editPartViewer, entry.getValue(), toIgnore, applyFormat, applyStyle);
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
     */
    private void applyFormatForBorderedNode(final DSemanticDecorator semanticDecorator, final Node toRestoreView, final EditPartViewer editPartViewer, final NodeFormatData formatData,
            final Set<Node> portsNodesToIgnore, boolean applyFormat, boolean applyStyle) {
        if (applyFormat) {
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
            applyFormatToNodeChildren((DNode) semanticDecorator, editPartViewer, formatData, applyFormat, applyStyle);
        } else if (semanticDecorator instanceof DNodeContainer) {
            applyFormatToNodeContainerChildren((DNodeContainer) semanticDecorator, editPartViewer, formatData, applyFormat, applyStyle);
        } else if (semanticDecorator instanceof DNodeList) {
            applyFormatToNodeListChildren((DNodeList) semanticDecorator, editPartViewer, formatData, applyFormat, applyStyle);
        } else {
            logWarnMessage(semanticDecorator);
        }
        if (semanticDecorator instanceof EdgeTarget) {
            applyFormatToOutgoingEdge((EdgeTarget) semanticDecorator, editPartViewer, applyFormat, applyStyle);
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
    private void applyFormatToNodeListChildren(final DNodeList nodeList, final EditPartViewer editPartViewer, final NodeFormatData formatData, boolean applyFormat, boolean applyStyle) {
        // Restore Bordered nodes
        applyFormatForBorderedNodes(nodeList.getOwnedBorderedNodes(), editPartViewer, formatData, applyFormat, applyStyle);

        // Restore label
        final Node gmfNode = SiriusGMFHelper.getGmfNode(nodeList);
        applyLabelFormat(gmfNode, formatData, applyFormat, applyStyle);
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
            logWarnMessage(child);
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
