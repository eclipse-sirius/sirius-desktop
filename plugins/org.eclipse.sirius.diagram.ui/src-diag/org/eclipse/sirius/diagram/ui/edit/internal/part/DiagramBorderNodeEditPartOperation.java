/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.edit.internal.part;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.api.query.DNodeQuery;
import org.eclipse.sirius.diagram.description.style.Side;
import org.eclipse.sirius.diagram.ui.edit.api.part.DiagramNameEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.ext.draw2d.figure.ICollapseMode;
import org.eclipse.sirius.ext.draw2d.figure.ITransparentFigure;
import org.eclipse.sirius.ext.draw2d.figure.StyledFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.DBorderedNodeFigure;

/**
 * Common operations for nodes that are on the border of other nodes.
 * 
 * @author ymortier
 */
public final class DiagramBorderNodeEditPartOperation {

    /**
     * Avoid instanciation.
     */
    private DiagramBorderNodeEditPartOperation() {

    }

    /**
     * Refreshes the visuals of the specified node.
     * 
     * @param self
     *            the node edit part.
     */
    public static void refreshVisuals(final IDiagramBorderNodeEditPart self) {
        final EObject eObj = self.resolveSemanticElement();
        if (eObj instanceof DNode) {
            if (self.getPrimaryFigure() != null) {
                self.refreshFigure();
            }
            DiagramBorderNodeEditPartOperation.internalRefreshVisuals(self);
            if (self.getPrimaryFigure() != null) {
                self.refreshFigure();
                self.getPrimaryFigure().repaint();
            }
        }
    }

    /**
     * Refreshes the font of the specified node.
     * 
     * @param self
     *            the node edit part.
     */
    public static void refreshFont(final IDiagramBorderNodeEditPart self) {
        if (!self.getChildren().isEmpty()) {
            Object firstChild = self.getChildren().get(0);
            if (firstChild instanceof IDiagramNameEditPart) {
                DiagramNameEditPartOperation.refreshFont((IDiagramNameEditPart) firstChild);
            }
        }
    }

    /**
     * Refreshes the visuals of the specified node.
     * 
     * @param self
     *            the node edit part.
     */
    public static void internalRefreshVisuals(final IDiagramBorderNodeEditPart self) {
        final AbstractDiagramNodeEditPartRefreshVisualsOperation op = new AbstractDiagramNodeEditPartRefreshVisualsOperation(self);
        if (op.canRefresh()) {
            op.refreshSize();
        }
    }

    /**
     * 
     * Convenience method to retreive the value for the supplied feature from
     * the editpart's associated view element. Same as calling
     * <code> ViewUtil.getStructuralFeatureValue(getNotationView(),feature)</code>
     * .
     * 
     * @param editPart
     *            the edit part
     * @param feature
     *            the feature
     * @return value of the feature from the editpart's associated view element
     */
    public static Object getStructuralFeatureValue(final IGraphicalEditPart editPart, final EStructuralFeature feature) {
        if (editPart.getNotationView() != null) {
            return ViewUtil.getPropertyValue((View) editPart.getModel(), feature, feature.getEContainingClass());
        } else {
            return null;
        }
    }

    /**
     * This method is invoked on a semantic change.
     * 
     * @param self
     *            the edit part.
     * @param notification
     *            the notification.
     */
    public static void handleNotificationEvent(final IDiagramBorderNodeEditPart self, final Notification notification) {
        if (DiagramPackage.eINSTANCE.getDDiagramElement_GraphicalFilters().equals(notification.getFeature())) {
            if (notification.getNewValue() instanceof CollapseFilter && notification.getOldValue() == null) {
                DiagramBorderNodeEditPartOperation.updateCollapseMode(self, notification.getNotifier());
            } else if (notification.getOldValue() instanceof CollapseFilter && notification.getNewValue() == null) {
                DiagramBorderNodeEditPartOperation.updateCollapseMode(self, notification.getNotifier());
            }
        } else if (DiagramPackage.eINSTANCE.getDNode_OwnedStyle().equals(notification.getFeature()) || DiagramPackage.eINSTANCE.getDNode_ActualMapping().equals(notification.getFeature())) {
            IFigure nodeFigure = self.getFigure();
            if (nodeFigure instanceof DBorderedNodeFigure) {
                DiagramBorderNodeEditPartOperation.updateAuthorizedSide((DBorderedNodeFigure) nodeFigure, self);
            }
        }
    }

    private static void updateCollapseMode(IDiagramBorderNodeEditPart self, Object notifier) {
        if (notifier != null && notifier.equals(self.resolveDiagramElement())) {
            DiagramBorderNodeEditPartOperation.updateResizeKind(self);

            if (!ICollapseMode.DEFAULT) {
                StyledFigure styledFigure = DiagramElementEditPartOperation.getStyledFigure(self.getFigure());
                if (styledFigure instanceof ITransparentFigure) {
                    DiagramBorderNodeEditPartOperation.updateTransparencyMode(self, (ITransparentFigure) styledFigure);
                }
            }
        }
    }

    /**
     * Updates figure authorized sides.
     * 
     * @param nodeFigure
     *            the current figure.
     * @param diagramBorderNodeEditPart
     *            the edit part.
     */
    public static void updateAuthorizedSide(DBorderedNodeFigure nodeFigure, IDiagramBorderNodeEditPart diagramBorderNodeEditPart) {
        EObject semanticElement = diagramBorderNodeEditPart.resolveSemanticElement();
        if (semanticElement instanceof DNode) {
            DNodeQuery query = new DNodeQuery((DNode) semanticElement);
            List<Side> forbiddenSides = query.getForbiddenSide();
            int[] sides = new int[forbiddenSides.size()];
            int i = 0;
            for (Side side : forbiddenSides) {
                if (Side.WEST.getName().equals(side.getName())) {
                    sides[i++] = PositionConstants.WEST;
                } else if (Side.EAST.getName().equals(side.getName())) {
                    sides[i++] = PositionConstants.EAST;
                } else if (Side.NORTH.getName().equals(side.getName())) {
                    sides[i++] = PositionConstants.NORTH;
                } else if (Side.SOUTH.getName().equals(side.getName())) {
                    sides[i++] = PositionConstants.SOUTH;
                }
            }
            nodeFigure.setForbiddenSides(sides);
        }
    }

    /**
     * Update the transparency of the given figure regarding the collpase mode,
     * and the border item edit part.
     * 
     * @param idbnep
     *            the current border item edit part
     * @param figure
     *            the figur eto update
     */
    public static void updateTransparencyMode(IDiagramBorderNodeEditPart idbnep, ITransparentFigure figure) {
        DDiagramElement dde = idbnep.resolveDiagramElement();
        if (dde != null) {
            figure.setTransparent(!ICollapseMode.DEFAULT && new DDiagramElementQuery(dde).isIndirectlyCollapsed());
        }
    }

    /**
     * Update the resize direction to none if the node is collapsed, otherwise
     * regarding the node's resize kind.
     * 
     * @param self
     *            the current border node edit part
     */
    public static void updateResizeKind(IDiagramBorderNodeEditPart self) {
        DDiagramElement dde = self.resolveDiagramElement();
        if (dde instanceof DNode) {
            DNode node = (DNode) dde;

            EditPolicy ep = self.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
            if (ep instanceof ResizableEditPolicy) {
                DiagramBorderNodeEditPartOperation.updateResizeKind((ResizableEditPolicy) ep, node);
            }
        }
    }

    /**
     * Update the resize direction to none if the node is collapsed, otherwise
     * regarding the node's resize kind.
     * 
     * @param ep
     *            the current resizable edit policy
     * @param node
     *            the current border node
     */
    public static void updateResizeKind(ResizableEditPolicy ep, DNode node) {
        if (new DDiagramElementQuery(node).isIndirectlyCollapsed()) {
            ep.setResizeDirections(PositionConstants.NONE);
        } else {
            DiagramNodeEditPartOperation.updateResizeKind(ep, node);
        }
    }

    /**
     * Return the node plate of the edit part.
     * 
     * @param viewNodeEditPart
     *            the edit part
     * @return the node plate of the edit part.
     */
    protected static DefaultSizeNodeFigure getNodePlate(final IDiagramBorderNodeEditPart viewNodeEditPart) {
        return AbstractDiagramNodeEditPartOperation.getNodePlate(viewNodeEditPart);
    }

    /**
     * Returns the command to resize a bordered node.
     * 
     * @param part
     *            the edit part corresponding to the bordered node.
     * @param request
     *            the request for a resize.
     * @return the command to resize a bordered node.
     */
    public static Command getResizeBorderItemCommand(IGraphicalEditPart part, ChangeBoundsRequest request) {
        return getResizeBorderItemCommand(part, request, true);
    }

    /**
     * Returns the command to resize a bordered node.
     * 
     * @param part
     *            the edit part corresponding to the bordered node.
     * @param request
     *            the request for a resize.
     * @param forbidResizeIfCollapse
     *            indicates how to handle collapsed nodes.
     * @return the command to resize a bordered node.
     */
    public static Command getResizeBorderItemCommand(IGraphicalEditPart part, ChangeBoundsRequest request, boolean forbidResizeIfCollapse) {
        final Command result;
        final EObject semantic = part.resolveSemanticElement();
        if (semantic instanceof DNode) {
            final double zoom = ((ZoomManager) part.getViewer().getProperty(ZoomManager.class.toString())).getZoom();
            final Dimension dimension = DiagramBorderNodeEditPartOperation.getDimensionFromView(part);
            final Point position = DiagramBorderNodeEditPartOperation.getPositionFromView(part);
            final DNode viewNode = (DNode) semantic;
            boolean canResize = !(new DDiagramElementQuery(viewNode).isIndirectlyCollapsed() && forbidResizeIfCollapse);
            if (canResize && new DNodeQuery(viewNode).allowsVerticalResize()) {
                dimension.setHeight(dimension.height + (int) (request.getSizeDelta().height / zoom));
                switch (request.getResizeDirection()) {
                case PositionConstants.NORTH:
                case PositionConstants.NORTH_WEST:
                case PositionConstants.NORTH_EAST:
                    position.setY(position.y - (int) (request.getSizeDelta().height / zoom));
                    break;
                default:
                    break;
                }
            }
            if (canResize && new DNodeQuery(viewNode).allowsHorizontalResize()) {
                dimension.setWidth(dimension.width + (int) (request.getSizeDelta().width / zoom));
                switch (request.getResizeDirection()) {
                case PositionConstants.WEST:
                case PositionConstants.NORTH_WEST:
                case PositionConstants.SOUTH_WEST:
                    position.setX(position.x - (int) (request.getSizeDelta().width / zoom));
                    break;
                default:
                    break;
                }
            }
            SetBoundsCommand setBoundsCommand = new SetBoundsCommand(part.getEditingDomain(), Messages.IAbstractDiagramNodeEditPart_resizeCommandLabel, new EObjectAdapter(part.getNotationView()),
                    new Rectangle(position, dimension));
            result = new ICommandProxy(setBoundsCommand);
        } else {
            result = null;
        }
        return result;
    }

    private static Point getPositionFromView(IGraphicalEditPart part) {
        final Point position = new Point();
        if (part.getNotationView() instanceof Node && ((Node) part.getNotationView()).getLayoutConstraint() instanceof Location) {
            final Location location = (Location) ((Node) part.getNotationView()).getLayoutConstraint();
            position.setX(location.getX());
            position.setY(location.getY());
        }
        return position;
    }

    private static Dimension getDimensionFromView(IGraphicalEditPart part) {
        final Dimension dimension = new Dimension();
        if (part.getNotationView() instanceof Node && ((Node) part.getNotationView()).getLayoutConstraint() instanceof Size) {
            final Size size = (Size) ((Node) part.getNotationView()).getLayoutConstraint();
            dimension.setWidth(size.getWidth());
            dimension.setHeight(size.getHeight());
        }
        return dimension;
    }
}
