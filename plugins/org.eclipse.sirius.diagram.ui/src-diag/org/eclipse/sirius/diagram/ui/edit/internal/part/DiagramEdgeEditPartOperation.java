/*******************************************************************************
 * Copyright (c) 2007, 2021, 2023 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *    Vincent Lorenzo (CEA LIST) <vincent.lorenzo@cea.fr> - Bug 581287
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.edit.internal.part;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RelativeBendpoint;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationPreCommitListener;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.JumpLinkStatus;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeArrows;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.DiagramNameEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.EdgeRoutingStyleChangedCommand;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ext.draw2d.figure.CirclePlusDecoration;
import org.eclipse.sirius.ext.draw2d.figure.PolygoneAndPolylineDecoraction;
import org.eclipse.sirius.ext.draw2d.figure.PolygoneAndPolylineWithDotDecoration;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.RGBValues;

/**
 * Implementation of {@link IDiagramEdgeEditPart}.
 * 
 * @author ymortier
 */
public final class DiagramEdgeEditPartOperation {
    /**
     * Basic shape of an inverse triangle tip.
     */
    public static final PointList INVERSE_TRIANGLE_TIP = new PointList();

    /**
     * The diamond figure.
     */
    public static final PointList DIAMOND = new PointList();

    /**
     * A zoom error margin to determine if a bendpoint of a path exist. An error margin is needed because the zoom can
     * modify coordinates.
     */
    private static final int ZOOM_ERROR_MARGIN = 4;

    /**
     * Avoid instanciation
     */
    private DiagramEdgeEditPartOperation() {

    }

    static {
        // INVERSE_TRIANGLE_TIP.addPoint(0, 1);
        // INVERSE_TRIANGLE_TIP.addPoint(-1, 0);
        // INVERSE_TRIANGLE_TIP.addPoint(0, -1);
        INVERSE_TRIANGLE_TIP.addPoint(1, -1);
        INVERSE_TRIANGLE_TIP.addPoint(0, 0);
        INVERSE_TRIANGLE_TIP.addPoint(1, 1);

        DIAMOND.addPoint(0, 0);
        DIAMOND.addPoint(-1, 1);
        DIAMOND.addPoint(-2, 0);
        DIAMOND.addPoint(-1, -1);
    }

    /**
     * Creates the listener of the routing style of the connection.
     * 
     * @param self
     *            the edge edit part.
     * @return the listener of the routing style of the connection.
     */
    public static NotificationPreCommitListener createEAdapterRoutingStyle(final IDiagramEdgeEditPart self) {
        return new NotificationPreCommitListener() {

            @Override
            public Command transactionAboutToCommit(final Notification msg) {
                return new EdgeRoutingStyleChangedCommand(self.getEditingDomain(), self, msg);
            }
        };
    }

    /**
     * Updates the connection according to the new routing style.
     * 
     * @param self
     *            the edge edit part.
     * @param message
     *            the notification.
     */
    public static void routingStyleChanged(final IDiagramEdgeEditPart self, final Notification message) {
        if (self.isActive()) {
            if (message.getFeature() == NotationPackage.eINSTANCE.getRoutingStyle_Routing()) {
                final RoutingStyle rstyle = (RoutingStyle) self.getNotationView().getStyle(NotationPackage.eINSTANCE.getRoutingStyle());
                final EdgeRouting routing = DiagramEdgeEditPartOperation.convertEdgeRoutingStyle(rstyle);
                final DEdge graphicalSemanticEdge = (DEdge) self.resolveSemanticElement();
                if (((EdgeStyle) graphicalSemanticEdge.getStyle()).getRoutingStyle() != routing) {
                    ((EdgeStyle) graphicalSemanticEdge.getStyle()).setRoutingStyle(routing);
                    List<String> customFeatures = graphicalSemanticEdge.getStyle().getCustomFeatures();
                    String featureName = DiagramPackage.Literals.EDGE_STYLE__ROUTING_STYLE.getName();
                    if (!customFeatures.contains(featureName)) {
                        customFeatures.add(featureName);
                    }
                }
                if (routing == EdgeRouting.TREE_LITERAL && rstyle.getJumpLinkStatus() != JumpLinkStatus.NONE_LITERAL) {
                    self.getEditingDomain().getCommandStack()
                            .execute(SetCommand.create(self.getEditingDomain(), rstyle, NotationPackage.eINSTANCE.getRoutingStyle_JumpLinkStatus(), JumpLinkStatus.NONE));
                } else if (routing != EdgeRouting.TREE_LITERAL && rstyle.getJumpLinkStatus() != JumpLinkStatus.ABOVE_LITERAL) {
                    self.getEditingDomain().getCommandStack()
                            .execute(SetCommand.create(self.getEditingDomain(), rstyle, NotationPackage.eINSTANCE.getRoutingStyle_JumpLinkStatus(), JumpLinkStatus.ABOVE));
                }
            }
        }
    }

    /**
     * Converts a GMF routing-style into the equivalent Sirius {@link EdgeRouting}.
     * 
     * @param rstyle
     *            the GMF routing-style
     * @return the equivalent Sirius {@link EdgeRouting}.
     */
    private static EdgeRouting convertEdgeRoutingStyle(final RoutingStyle rstyle) {
        final EdgeRouting routing;
        switch (rstyle.getRouting().getValue()) {
        case Routing.MANUAL:
            routing = EdgeRouting.STRAIGHT_LITERAL;
            break;
        case Routing.RECTILINEAR:
            routing = EdgeRouting.MANHATTAN_LITERAL;
            break;
        case Routing.TREE:
            routing = EdgeRouting.TREE_LITERAL;
            break;
        default:
            SiriusPlugin.getDefault().warning(MessageFormat.format(Messages.DiagramEdgeEditPartOperation_unknownRountingStyle, rstyle.getRouting().getName()), null);
            routing = EdgeRouting.STRAIGHT_LITERAL;
        }
        return routing;
    }

    /**
     * Refreshes the representation of the edge.
     * 
     * @param self
     *            the edge edit part.
     */
    public static void refreshVisuals(final IDiagramEdgeEditPart self) {
        self.refreshForegroundColor();
        self.refreshSourceDecoration();
        self.refreshTargetDecoration();
        self.refreshLineStyle();
    }

    /**
     * Refreshes the font of the specified node.
     * 
     * @param self
     *            the node edit part.
     */
    public static void refreshFont(final IDiagramEdgeEditPart self) {
        for (Object child : self.getChildren()) {
            if (child instanceof IDiagramNameEditPart) {
                DiagramNameEditPartOperation.refreshFont((IDiagramNameEditPart) child);
            }
        }
    }

    /**
     * Refreshes the foreground color of the edge.
     * 
     * @param self
     *            the edge edit part.
     */
    public static void refreshForegroundColor(final IDiagramEdgeEditPart self) {
        final EObject diagramElement = self.resolveSemanticElement();
        if (diagramElement instanceof DEdge) {
            final DEdge edge = (DEdge) diagramElement;
            if (edge.getOwnedStyle() != null && edge.getOwnedStyle().getStrokeColor() != null) {
                final RGBValues rgb = edge.getOwnedStyle().getStrokeColor();
                self.getPolylineConnectionFigure().setForegroundColor(VisualBindingManager.getDefault().getColorFromRGBValues(rgb));
            }
        }
    }

    /**
     * Refreshes the source decoration of the connection.
     * 
     * @param self
     *            the edge edit part.
     */
    public static void refreshSourceDecoration(final IDiagramEdgeEditPart self) {
        // We don't change the source decoration to keep the selected effect.
        if (!DiagramEdgeEditPartOperation.isSelected(self) && !DiagramEdgeEditPartOperation.isLabelSelected(self)) {
            final EObject diagramElement = self.resolveSemanticElement();
            if (diagramElement instanceof DEdge) {
                final DEdge edge = (DEdge) diagramElement;
                if (edge.getOwnedStyle() != null) {
                    final EdgeStyle style = edge.getOwnedStyle();
                    final EdgeArrows arrowsKind = style.getSourceArrow();
                    self.getPolylineConnectionFigure().setSourceDecoration(DiagramEdgeEditPartOperation.createArrowDecoration(self, arrowsKind));
                }
            }
        }
    }

    /**
     * Refreshes the target decoration of the connection.
     * 
     * @param self
     *            the edge edit part.
     */
    public static void refreshTargetDecoration(final IDiagramEdgeEditPart self) {
        // We don't change the source decoration to keep the selected effect.
        if (!DiagramEdgeEditPartOperation.isSelected(self) && !DiagramEdgeEditPartOperation.isLabelSelected(self)) {
            final EObject diagramElement = self.resolveSemanticElement();
            if (diagramElement instanceof DEdge) {
                final DEdge edge = (DEdge) diagramElement;
                if (edge.getOwnedStyle() != null) {
                    final EdgeStyle style = edge.getOwnedStyle();
                    final EdgeArrows arrowsKind = style.getTargetArrow();
                    self.getPolylineConnectionFigure().setTargetDecoration(DiagramEdgeEditPartOperation.createArrowDecoration(self, arrowsKind));
                }
            }
        }
    }

    private static RotatableDecoration createArrowDecoration(final IDiagramEdgeEditPart self, final EdgeArrows arrowKind) {
        final RotatableDecoration result;
        switch (arrowKind) {
        case NO_DECORATION_LITERAL:
            result = null;
            break;
        case DIAMOND_LITERAL:
            result = DiagramEdgeEditPartOperation.createDiamondDecoration(self);
            break;
        case FILL_DIAMOND_LITERAL:
            result = DiagramEdgeEditPartOperation.createFillDiamondDecoration(self);
            break;
        case INPUT_ARROW_LITERAL:
            result = DiagramEdgeEditPartOperation.createArrowIn(self);
            break;
        case INPUT_CLOSED_ARROW_LITERAL:
            result = DiagramEdgeEditPartOperation.createClosedArrowIn(self);
            break;
        case INPUT_FILL_CLOSED_ARROW_LITERAL:
            result = DiagramEdgeEditPartOperation.createFillClosedArrowIn(self);
            break;
        case OUTPUT_ARROW_LITERAL:
            result = DiagramEdgeEditPartOperation.createArrowOut(self);
            break;
        case OUTPUT_CLOSED_ARROW_LITERAL:
            result = DiagramEdgeEditPartOperation.createClosedArrowOut(self);
            break;
        case OUTPUT_FILL_CLOSED_ARROW_LITERAL:
            result = DiagramEdgeEditPartOperation.createFillClosedArrowOut(self);
            break;
        case INPUT_ARROW_WITH_DIAMOND_LITERAL:
            result = DiagramEdgeEditPartOperation.createArrowInWithDiamond(self);
            break;
        case INPUT_ARROW_WITH_FILL_DIAMOND_LITERAL:
            result = DiagramEdgeEditPartOperation.createArrowInWithFillDiamond(self);
            break;
        case CIRCLE_PLUS_LITERAL:
            result = DiagramEdgeEditPartOperation.createCirclePlus(self);
            break;
        case DOT_LITERAL:
            result = DiagramEdgeEditPartOperation.createDot(self);
            break;
        case INPUT_ARROW_WITH_DOT_LITERAL:
            result = DiagramEdgeEditPartOperation.createArrowInWithDot(self);
            break;
        case INPUT_ARROW_WITH_FILL_DIAMOND_AND_DOT_LITERAL:
            result = DiagramEdgeEditPartOperation.createArrowInWithFillDiamondAndDot(self);
            break;
        case INPUT_ARROW_WITH_DIAMOND_AND_DOT_LITERAL:
            result = DiagramEdgeEditPartOperation.createArrowInWithDiamondAndDot(self);
            break;
        case DIAMOND_WITH_DOT_LITERAL:
            result = DiagramEdgeEditPartOperation.createDiamondAndDotDecoration(self);
            break;
        case FILL_DIAMOND_WITH_DOT_LITERAL:
            result = DiagramEdgeEditPartOperation.createFillDiamondAndDotDecoration(self);
            break;
        default:
            assert false;
            result = null;
        }
        return result;
    }

    /**
     * Refreshes the style of the connection.
     * 
     * @param self
     *            the edge edit part.
     */
    public static void refreshLineStyle(final IDiagramEdgeEditPart self) {
        final EObject semanticElement = self.resolveSemanticElement();
        PolylineConnectionEx polylineConnectionFigure = self.getPolylineConnectionFigure();

        if (semanticElement instanceof DEdge && polylineConnectionFigure != null) {
            final DEdge edge = (DEdge) semanticElement;
            if (edge.getOwnedStyle() != null) {
                final EdgeStyle style = edge.getOwnedStyle();
                DiagramElementEditPartOperation.setLineStyle(polylineConnectionFigure, style.getLineStyle(), true);
            }
            // We don't change the line width to keep the selected effect.
            if (!DiagramEdgeEditPartOperation.isSelected(self) && !DiagramEdgeEditPartOperation.isLabelSelected(self)) {
                // Width of the line.
                final int lineWidth = DiagramEdgeEditPartOperation.getLineWidth(edge);
                if (lineWidth != 0 || (lineWidth == 0 && polylineConnectionFigure.getLineWidth() != 1)) {
                    polylineConnectionFigure.setLineWidth(DiagramEdgeEditPartOperation.getLineWidth(edge));
                }
            }
        }
    }

    /**
     * Return the computed line width.
     * 
     * @param self
     *            the edge edit part.
     * @return the line width
     */
    public static int getLineWidth(final IDiagramEdgeEditPart self) {
        final EObject semanticElement = self.resolveSemanticElement();
        if (semanticElement instanceof DEdge) {
            final DEdge edge = (DEdge) semanticElement;
            return DiagramEdgeEditPartOperation.getLineWidth(edge);
        }
        return 0;
    }

    private static int getLineWidth(final DEdge edge) {
        Integer sizeObject = edge.getStyle() != null ? ((EdgeStyle) edge.getStyle()).getSize() : null;
        int size = sizeObject != null ? sizeObject.intValue() : 0;

        // avoid to have only the 1 or 2 pixels shadow lines when the
        // size is 0 or less.
        if (size < 1) {
            size = 1;
        }
        return VisualBindingManager.getDefault().getLineWidthFromValue(size);
    }

    /**
     * Compute the center of edgeTarget in absolute coordinate.
     * 
     * @param edgeTarget
     *            The edgeTarget
     * @param self
     *            An edit part to have access to the editPartRegistry
     * @return The center location of the edgeTarget.
     */
    private static Point getPointFor(final EdgeTarget edgeTarget, final IDiagramEdgeEditPart self) {
        // Retrieve the view.
        View view = null;
        final Map<View, EditPart> editPartRegistry = self.getRoot().getViewer().getEditPartRegistry();

        for (final View currentView : DiagramEdgeEditPartOperation.getViews(edgeTarget, self)) {
            if (currentView.isVisible()) {
                final EditPart editPart = editPartRegistry.get(currentView);
                if (editPart instanceof IAbstractDiagramNodeEditPart) {
                    view = currentView;
                    break;
                }
            }
        }

        final EditPart part = editPartRegistry.get(view);
        if (part instanceof GraphicalEditPart && part.getModel() instanceof Node) {
            final IFigure figure = ((GraphicalEditPart) part).getFigure();
            final Point center = figure.getBounds().getCenter();
            figure.translateToAbsolute(center);
            return center;
        }
        return null;
    }

    private static Collection<View> getViews(final EObject semanticElement, final IDiagramEdgeEditPart self) {
        final EObject rootView = EcoreUtil.getRootContainer(self.getPrimaryView());
        final Collection<View> views = new LinkedList<View>();
        final Iterator<EObject> allView = rootView.eAllContents();
        while (allView.hasNext()) {
            final Object current = allView.next();
            if (current instanceof View) {
                final EObject element = ViewUtil.resolveSemanticElement((View) current);
                if (element != null && element.equals(semanticElement)) {
                    views.add((View) current);
                }
            }
        }
        return views;
    }

    private static DiagramEventBroker getDiagramEventBroker(final IDiagramEdgeEditPart self) {
        final TransactionalEditingDomain theEditingDomain = self.getEditingDomain();
        if (theEditingDomain != null) {
            return DiagramEventBroker.getInstance(theEditingDomain);
        }
        return null;
    }

    /**
     * Activates the edit part.
     * 
     * @param self
     *            the edge edit part.
     */
    public static void activate(final AbstractDiagramEdgeEditPart self) {
        final DiagramEventBroker broker = DiagramEdgeEditPartOperation.getDiagramEventBroker(self);
        final RoutingStyle rstyle = (RoutingStyle) self.getNotationView().getStyle(NotationPackage.eINSTANCE.getRoutingStyle());
        broker.addNotificationListener(rstyle, self.getEAdapterRoutingStyle());
        // Store the broker data to ensure to be able to remove the added listener (not possible during deactivation in
        // example of connection lost to a server)
        self.setRoutingStyle(rstyle);
        self.setRoutingStyleNotificationPreCommitListener(self.getEAdapterRoutingStyle());
        final IPermissionAuthority auth = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(self.getEditingDomain().getResourceSet());
        auth.addAuthorityListener(self.getEditPartAuthorityListener());
        self.getEditPartAuthorityListener().refreshEditMode();
    }

    /**
     * Deactivates the edit part.
     * 
     * @param self
     *            the edge edit part.
     */
    public static void deactivate(final AbstractDiagramEdgeEditPart self) {
        // Remove the broker listener (if added in activation)
        if (self.getRoutingStyle() != null && self.getRoutingStyleNotificationPreCommitListener() != null) {
            final DiagramEventBroker broker = DiagramEdgeEditPartOperation.getDiagramEventBroker(self);
            broker.removeNotificationListener(self.getRoutingStyle(), self.getRoutingStyleNotificationPreCommitListener());
        }
        final IPermissionAuthority auth = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(self.getEditingDomain().getResourceSet());
        auth.removeAuthorityListener(self.getEditPartAuthorityListener());
    }

    /**
     * Creates a fill diamond decoration.
     * 
     * @param self
     *            the edge edit part
     * @return the new decoration
     */
    public static PolygonDecoration createFillDiamondDecoration(final IDiagramEdgeEditPart self) {
        final PolygonDecoration decoration = new PolygonDecoration();
        decoration.setTemplate(DIAMOND);
        decoration.setScale(6, 3);
        DiagramEdgeEditPartOperation.applyLineWidth(decoration, self);
        return decoration;
    }

    /**
     * Creates a diamond decoration.
     * 
     * @param self
     *            the edge edit part
     * @return the new decoration
     */
    public static PolygonDecoration createDiamondDecoration(final IDiagramEdgeEditPart self) {
        final PolygonDecoration decoration = DiagramEdgeEditPartOperation.createFillDiamondDecoration(self);
        decoration.setBackgroundColor(ColorConstants.white);
        return decoration;
    }

    /**
     * Creates an input closed arrow decoration.
     * 
     * @param self
     *            the edge edit part
     * @return the new decoration
     */
    public static PolygonDecoration createClosedArrowIn(final IDiagramEdgeEditPart self) {
        final PolygonDecoration decoration = DiagramEdgeEditPartOperation.createFillClosedArrowIn(self);
        decoration.setBackgroundColor(ColorConstants.white);
        return decoration;
    }

    /**
     * Creates a fill input closed arrow decoration.
     * 
     * @param self
     *            the edge edit part
     * @return the new decoration
     */
    public static PolygonDecoration createFillClosedArrowIn(final IDiagramEdgeEditPart self) {
        final PolygonDecoration decoration = new PolygonDecoration();
        decoration.setScale(8, 4);
        DiagramEdgeEditPartOperation.applyLineWidth(decoration, self);
        return decoration;
    }

    /**
     * Creates an output closed arrow decoration.
     * 
     * @param self
     *            the edge edit part
     * @return the new decoration
     */
    public static PolygonDecoration createClosedArrowOut(final IDiagramEdgeEditPart self) {
        final PolygonDecoration decoration = DiagramEdgeEditPartOperation.createClosedArrowIn(self);
        decoration.setTemplate(PolygonDecoration.INVERTED_TRIANGLE_TIP);
        return decoration;
    }

    /**
     * Creates a fill output closed arrow decoration.
     * 
     * @param self
     *            the edge edit part
     * @return the new decoration
     */
    public static PolygonDecoration createFillClosedArrowOut(final IDiagramEdgeEditPart self) {
        final PolygonDecoration decoration = DiagramEdgeEditPartOperation.createFillClosedArrowIn(self);
        decoration.setTemplate(PolygonDecoration.INVERTED_TRIANGLE_TIP);
        return decoration;
    }

    /**
     * Apply the line width of the editPart to the decoration.
     * 
     * @param decoration
     *            the decoration
     * @param self
     *            the edge edit part.
     * @return the created decoration.
     */
    private static void applyLineWidth(final Polyline decoration, final IDiagramEdgeEditPart self) {
        final DEdge edge = (DEdge) self.resolveSemanticElement();
        int size = DiagramEdgeEditPartOperation.getLineWidth(edge);
        if (size != 0) {
            decoration.setLineWidth(size);
        }
    }

    /**
     * Creates an input arrow decoration.
     * 
     * @param self
     *            the edge edit part
     * @return the new decoration
     */
    public static PolylineDecoration createArrowIn(final IDiagramEdgeEditPart self) {
        final PolylineDecoration decoration = new PolylineDecoration();
        DiagramEdgeEditPartOperation.applyLineWidth(decoration, self);
        return decoration;
    }

    /**
     * Creates an output arrow decoration.
     * 
     * @param self
     *            the edge edit part
     * @return the new decoration
     */
    public static PolylineDecoration createArrowOut(final IDiagramEdgeEditPart self) {
        final PolylineDecoration decoration = new PolylineDecoration();
        DiagramEdgeEditPartOperation.applyLineWidth(decoration, self);
        decoration.setTemplate(INVERSE_TRIANGLE_TIP);
        return decoration;
    }

    /**
     * Creates an input arrow with diamond decoration.
     * 
     * @param self
     *            the edge edit part
     * @return the new decoration
     */
    private static RotatableDecoration createArrowInWithDiamond(IDiagramEdgeEditPart self) {
        PolygoneAndPolylineDecoraction decoration = (PolygoneAndPolylineDecoraction) DiagramEdgeEditPartOperation.createArrowInWithFillDiamond(self);
        decoration.setBackgroundColor(ColorConstants.white);
        return decoration;
    }

    /**
     * Creates an input arrow with fill diamond decoration.
     * 
     * @param self
     *            the edge edit part
     * @return the new decoration
     */
    private static RotatableDecoration createArrowInWithFillDiamond(IDiagramEdgeEditPart self) {
        final PolygoneAndPolylineDecoraction decoration = new PolygoneAndPolylineDecoraction();
        decoration.setTemplate(DIAMOND);
        decoration.setScale(6, 3);
        DiagramEdgeEditPartOperation.applyLineWidth(decoration, self);
        return decoration;
    }

    /**
     * Creates a circle with a plus decoration.
     * 
     * @param self
     *            the edge edit part
     * @return the new decoration
     */
    private static RotatableDecoration createCirclePlus(final IDiagramEdgeEditPart self) {
        final CirclePlusDecoration decoration = new CirclePlusDecoration();
        return decoration;
    }

    /**
     * Creates a dot decoration.
     * 
     * @param self
     *            the edge edit part
     * @return the new decoration
     */
    private static RotatableDecoration createDot(final IDiagramEdgeEditPart self) {
        final PolygoneAndPolylineWithDotDecoration decoration = new PolygoneAndPolylineWithDotDecoration();
        decoration.setPolylineTemplate(new PointList()); // remove the arrow
        decoration.setTemplate(new PointList()); // remove the diamond
        DiagramEdgeEditPartOperation.applyLineWidth(decoration, self);
        return decoration;
    }

    /**
     * Creates an input arrow with a dot decoration.
     * 
     * @param self
     *            the edge edit part
     * @return the new decoration
     */
    private static RotatableDecoration createArrowInWithDot(IDiagramEdgeEditPart self) {
        PolygoneAndPolylineWithDotDecoration decoration = new PolygoneAndPolylineWithDotDecoration();
        decoration.setScale(6, 3);
        DiagramEdgeEditPartOperation.applyLineWidth(decoration, self);
        return decoration;

    }

    /**
     * Creates an input arrow with diamond and dot decoration.
     * 
     * @param self
     *            the edge edit part
     * @return the new decoration
     */
    private static RotatableDecoration createArrowInWithDiamondAndDot(IDiagramEdgeEditPart self) {
        PolygoneAndPolylineWithDotDecoration decoration = (PolygoneAndPolylineWithDotDecoration) createArrowInWithFillDiamondAndDot(self);
        decoration.setBackgroundColor(ColorConstants.white);
        DiagramEdgeEditPartOperation.applyLineWidth(decoration, self);
        return decoration;
    }
    
    /**
     * Creates an input arrow with fill diamond and dot decoration.
     * 
     * @param self
     *            the edge edit part
     * @return the new decoration
     */
    private static RotatableDecoration createArrowInWithFillDiamondAndDot(IDiagramEdgeEditPart self) {
        PolygoneAndPolylineWithDotDecoration decoration = new PolygoneAndPolylineWithDotDecoration();
        final PointList decorationPointList = DIAMOND.getCopy();
        // translate the diamond to be able to display the dot
        decorationPointList.translate(-1, 0);
        decoration.setTemplate(decorationPointList);

        PointList arrow = PolygoneAndPolylineDecoraction.TRIANGLE_TIP.getCopy();
        arrow.translate(-1, 0);
        decoration.setPolylineTemplate(arrow);

        DiagramEdgeEditPartOperation.applyLineWidth(decoration, self);
        return decoration;

    }

    /**
     * Creates a diamond with dot decoration.
     * 
     * @param self
     *            the edge edit part
     * @return the new decoration
     */
    private static RotatableDecoration createDiamondAndDotDecoration(IDiagramEdgeEditPart self) {
        PolygoneAndPolylineWithDotDecoration decoration = (PolygoneAndPolylineWithDotDecoration) createFillDiamondAndDotDecoration(self);
        decoration.setBackgroundColor(ColorConstants.white);
        DiagramEdgeEditPartOperation.applyLineWidth(decoration, self);
        return decoration;
    }

    /**
     * Creates a fill diamond with dot decoration.
     * 
     * @param self
     *            the edge edit part
     * @return the new decoration
     */
    private static RotatableDecoration createFillDiamondAndDotDecoration(IDiagramEdgeEditPart self) {
        PolygoneAndPolylineWithDotDecoration decoration = new PolygoneAndPolylineWithDotDecoration();
        decoration.setPolylineTemplate(new PointList()); // remove the arrow

        final PointList decorationPointList = DIAMOND.getCopy();
        // translate the diamond to be able to display the dot
        decorationPointList.translate(-1, 0);

        decoration.setTemplate(decorationPointList);
        DiagramEdgeEditPartOperation.applyLineWidth(decoration, self);
        return decoration;
    }

    /**
     * Removes the node label from model children.
     * 
     * @param self
     *            the edit part.
     * @param modelChildren
     *            the children of the node.
     */
    public static void removeLabel(final IDiagramEdgeEditPart self, final List<?> modelChildren) {
        EObject label = null;
        final Iterator<?> iterChildren = modelChildren.iterator();
        while (iterChildren.hasNext() && label == null) {
            final Object current = iterChildren.next();
            if (current instanceof View) {
                final int visualId = SiriusVisualIDRegistry.getVisualID(((View) current).getType());
                if (visualId == DEdgeNameEditPart.VISUAL_ID) {
                    label = (EObject) current;
                }
            }
        }
        if (label != null) {
            modelChildren.remove(label);
        }
    }

    /**
     * Get the label edit part associated with the edge.
     * 
     * @param self
     *            the edit part.
     * @return the label of the edge or null if any
     */
    protected static DEdgeNameEditPart getLabel(final IDiagramEdgeEditPart self) {
        DEdgeNameEditPart label = null;
        final Iterator<?> iterChildren = self.getChildren().iterator();
        while (iterChildren.hasNext() && label == null) {
            final Object current = iterChildren.next();
            if (current instanceof DEdgeNameEditPart) {
                label = (DEdgeNameEditPart) current;
            }
        }
        return label;
    }

    /**
     * Test if the label of an edge edit part is selected (primary or not).
     * 
     * @param self
     *            the edge edit part.
     * @return true is the label edit part is selected false otherwise.
     */
    public static boolean isLabelSelected(final IDiagramEdgeEditPart self) {
        final DEdgeNameEditPart label = DiagramEdgeEditPartOperation.getLabel(self);
        if (label == null) {
            return false;
        } else {
            return DiagramEdgeEditPartOperation.isSelected(label);
        }
    }

    /**
     * Test if an edit part is selected (primary or not).
     * 
     * @param self
     *            the edit part to check.
     * @return true is the edit part is selected false otherwise.
     */
    public static boolean isSelected(final EditPart self) {
        return self.getSelected() == EditPart.SELECTED_PRIMARY || self.getSelected() == EditPart.SELECTED;
    }

    /**
     * Refresh the bendpoints of the figure from the bendpoints of the GMF Edge (for edge with path). This method use
     * code from GMF.
     * 
     * @See {@link org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart#refreshBendpoints}
     * @param self
     *            the edge edit part.
     * @param edge
     *            the corresponding semantic element
     */
    public static void refreshBendpointsWithPath(AbstractDiagramEdgeEditPart self, DEdge edge) {
        RelativeBendpoints bendpoints = (RelativeBendpoints) ((Edge) self.getModel()).getBendpoints();
        List<?> modelConstraint = bendpoints.getPoints();
        List<RelativeBendpoint> defaultFigureConstraint = new ArrayList<RelativeBendpoint>();
        for (int i = 0; i < modelConstraint.size(); i++) {
            org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint wbp = (org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint) modelConstraint.get(i);
            RelativeBendpoint rbp = new RelativeBendpoint(self.getConnectionFigure());
            rbp.setRelativeDimensions(new Dimension(wbp.getSourceX(), wbp.getSourceY()), new Dimension(wbp.getTargetX(), wbp.getTargetY()));
            rbp.setWeight((i + 1) / ((float) modelConstraint.size() + 1));
            defaultFigureConstraint.add(rbp);
        }
        List<Point> defaultConstraintPoints = new ArrayList<Point>();
        for (RelativeBendpoint relativeBendpointFigureConstraint : defaultFigureConstraint) {
            Point location = relativeBendpointFigureConstraint.getLocation().getCopy();
            defaultConstraintPoints.add(location);
        }

        // Compute the points by which the edge must pass and check if
        // the edge passes by all this mandatory points
        final List<Bendpoint> mandatoryPoints = new LinkedList<Bendpoint>();
        boolean allMandatoryPointsAlreadyExist = true;
        for (final EdgeTarget current : edge.getPath()) {
            final Point point = DiagramEdgeEditPartOperation.getPointFor(current, self);
            // Point can be null if the center of this path element is not found
            // (in cause probably an editPart not yet created)
            if (point != null) {
                self.getPolylineConnectionFigure().translateToRelative(point);
                AbsoluteBendpoint mandatoryPoint = new AbsoluteBendpoint(point);
                mandatoryPoints.add(mandatoryPoint);
                allMandatoryPointsAlreadyExist = allMandatoryPointsAlreadyExist && DiagramEdgeEditPartOperation.isContainsBy(mandatoryPoint.getLocation(), defaultConstraintPoints, ZOOM_ERROR_MARGIN);
            }
        }
        if (!allMandatoryPointsAlreadyExist) {
            // Force the edge to pass by all the mandatory points
            // The constraint must contains the start and the end of the
            // edge (that are not in the path : mandatoryPoints)
            final List<Bendpoint> figureConstraintWithPath = new LinkedList<Bendpoint>(mandatoryPoints);
            figureConstraintWithPath.add(0, new AbsoluteBendpoint(defaultConstraintPoints.get(0)));
            figureConstraintWithPath.add(new AbsoluteBendpoint(defaultConstraintPoints.get(defaultConstraintPoints.size() - 1)));
            self.getConnectionFigure().setRoutingConstraint(figureConstraintWithPath);
        } else {
            self.getConnectionFigure().setRoutingConstraint(defaultFigureConstraint);
        }
    }

    /**
     * Check if the point is contains is the list with an margin of error. The getDistance method of Point if used for
     * checking the error margin.
     * 
     * @param point
     *            The point to check
     * @param points
     *            The list of points
     * @param errorMargin
     *            The error margin
     * @return true if this point is in the list.
     */
    private static boolean isContainsBy(Point point, List<Point> points, int errorMargin) {
        for (int i = 0; i < points.size(); i++) {
            if (point.getDistance(points.get(i)) < errorMargin) {
                return true;
            }
        }
        return false;
    }
}
