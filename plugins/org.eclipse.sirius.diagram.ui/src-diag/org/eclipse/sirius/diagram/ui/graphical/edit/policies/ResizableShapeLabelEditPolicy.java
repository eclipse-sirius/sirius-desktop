/*******************************************************************************
 * Copyright (c) 2011, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.requests.AlignmentRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableShapeEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.internal.figures.LabelHelper;
import org.eclipse.gmf.runtime.diagram.ui.internal.util.LabelViewConstants;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.locator.EdgeLabelQuery;

/**
 * Edit policy which supports TARGET and SOURCE label connections for feedback on the edge.
 *
 * @author nlepine
 */
public class ResizableShapeLabelEditPolicy extends ResizableShapeEditPolicy {

    private Polyline tether;

    /**
     * Get the referenced point used as target point of tether feedback.
     *
     * @return the referenced point used as target point of tether feedback
     */
    protected Point getReferencePoint() {
        if (getHost().getParent() instanceof AbstractConnectionEditPart) {
            PointList ptList = ((AbstractConnectionEditPart) getHost().getParent()).getConnectionFigure().getPoints();
            return PointListUtilities.calculatePointRelativeToLine(ptList, 0, getLocation(), true);
        } else {
            return ((GraphicalEditPart) getHost().getParent()).getFigure().getBounds().getLocation();
        }

    }

    /**
     * Get the location among {@link LabelViewConstants} constants where to relocate the label figure.
     *
     * @return the location among {@link LabelViewConstants} constants
     */
    private int getLocation() {
        int location = LabelViewConstants.MIDDLE_LOCATION;
        switch (((AbstractDEdgeNameEditPart) getHost()).getKeyPoint()) {
        case ConnectionLocator.SOURCE:
            location = LabelViewConstants.TARGET_LOCATION;
            break;
        case ConnectionLocator.TARGET:
            location = LabelViewConstants.SOURCE_LOCATION;
            break;
        case ConnectionLocator.MIDDLE:
            location = LabelViewConstants.MIDDLE_LOCATION;
            break;
        default:
            location = LabelViewConstants.MIDDLE_LOCATION;
            break;
        }
        return location;
    }

    @Override
    protected void eraseChangeBoundsFeedback(ChangeBoundsRequest request) {
        super.eraseChangeBoundsFeedback(request);
        if (tether != null) {
            removeFeedback(tether);
        }
        tether = null;
    }

    @Override
    protected IFigure createDragSourceFeedbackFigure() {
        IFigure feedback = super.createDragSourceFeedbackFigure();
        tether = new Polyline();
        tether.setLineStyle(Graphics.LINE_DASHDOT);
        tether.setForegroundColor(((IGraphicalEditPart) getHost().getParent()).getFigure().getForegroundColor());
        addFeedback(tether);
        return feedback;
    }

    @Override
    protected Command getResizeCommand(ChangeBoundsRequest request) {
        PrecisionRectangle rect = new PrecisionRectangle(getInitialFeedbackBounds().getCopy());
        getHostFigure().translateToAbsolute(rect);
        rect.translate(request.getMoveDelta());
        rect.resize(request.getSizeDelta());
        getHostFigure().translateToRelative(rect);
        View shapeView = (View) getHost().getModel();

        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();

        Point refPoint = getReferencePoint();
        Point normalPoint;
        if (getHost().getParent() instanceof AbstractConnectionEditPart) {
            // In case of a label of an edge, there is specific computation according to the "location" of the label
            // (begin, center, end). Indeed, in GMF, the location is stored according to the reference point (begin,
            // center, end).
            PointList ptList = ((AbstractConnectionEditPart) getHost().getParent()).getConnectionFigure().getPoints();
            normalPoint = EdgeLabelQuery.offsetFromRelativeCoordinate(getHostFigure().getBounds().getCenter().getCopy(), ptList, refPoint);
        } else {
            // For a label of a shape, the location is "just" relative to the shape origin.
            normalPoint = LabelHelper.offsetFromRelativeCoordinate(getHostFigure(), rect, refPoint);
        }

        ICommand resizeCommand = new org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand(editingDomain, DiagramUIMessages.SetLocationCommand_Label_Resize, new EObjectAdapter(shapeView),
                new Rectangle(normalPoint, rect.getSize()));
        return new ICommandProxy(resizeCommand);
    }

    @Override
    protected Command getMoveCommand(ChangeBoundsRequest request) {
        Point refPoint = getReferencePoint();

        // translate the feedback figure
        PrecisionRectangle rect = new PrecisionRectangle(getInitialFeedbackBounds().getCopy());
        getHostFigure().translateToAbsolute(rect);
        rect.translate(request.getMoveDelta());
        rect.resize(request.getSizeDelta());
        getHostFigure().translateToRelative(rect);
        adjustRect(rect);

        Point normalPoint;
        if (getHost().getParent() instanceof AbstractConnectionEditPart) {
            // In case of a label of an edge, there is specific computation according to the "location" of the label
            // (begin, center, end). Indeed, in GMF, the location is stored according to the reference point (begin,
            // center, end).
            PointList ptList = ((AbstractConnectionEditPart) getHost().getParent()).getConnectionFigure().getPoints();
            normalPoint = EdgeLabelQuery.offsetFromRelativeCoordinate(rect.getCenter(), ptList, refPoint);
        } else {
            // For a label of a shape, the location is "just" relative to the shape origin.
            normalPoint = LabelHelper.offsetFromRelativeCoordinate(getHostFigure(), rect, refPoint);
        }

        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();

        ICommand moveCommand = new org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand(editingDomain, DiagramUIMessages.MoveLabelCommand_Label_Location,
                new EObjectAdapter((View) getHost().getModel()), normalPoint);
        return new ICommandProxy(moveCommand);
    }

    /**
     * adjust the rectangle used for the move command; the default implementation assumes no behavior, clients can
     * override this function to change this behavior.
     *
     * @param rect
     *            Rect to adjust
     */
    protected void adjustRect(PrecisionRectangle rect) {
        // do nothing
    }

    @Override
    protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
        super.showChangeBoundsFeedback(request);

        IFigure p = getDragSourceFeedbackFigure();
        Rectangle r = p.getBounds();
        Point refPoint = getReferencePoint();

        // translate the feedback figure
        PrecisionRectangle rect = new PrecisionRectangle(getInitialFeedbackBounds().getCopy());
        getHostFigure().translateToAbsolute(rect);
        rect.translate(request.getMoveDelta());
        rect.resize(request.getSizeDelta());
        p.translateToRelative(rect);
        p.setBounds(rect);

        // translate the refPoint
        PrecisionRectangle ref = new PrecisionRectangle(new Rectangle(refPoint.x, refPoint.y, 0, 0));
        getHostFigure().translateToAbsolute(ref);
        p.translateToRelative(ref);

        Point midTop = new Point(r.x + r.width / 2, r.y);
        Point midBottom = new Point(r.x + r.width / 2, r.y + r.height);
        Point midLeft = new Point(r.x, r.y + r.height / 2);
        Point midRight = new Point(r.x + r.width, r.y + r.height / 2);

        Point startPoint = midTop;

        int x = r.x + r.width / 2 - refPoint.x;
        int y = r.y + r.height / 2 - refPoint.y;

        if (y > 0 && y > x && y > -x) {
            startPoint = midTop;
        } else if (y < 0 && y < x && y < -x) {
            startPoint = midBottom;
        } else if (x < 0 && y > x && y < -x) {
            startPoint = midRight;
        } else {
            startPoint = midLeft;
        }

        tether.setStart(startPoint);
        tether.setEnd(ref.getLocation());
    }

    /*
     * Inspired from #getMoveCommand(ChangeBoundsRequest)
     */
    @Override
    protected Command getAlignCommand(AlignmentRequest request) {
        Point refPoint = getReferencePoint();
        PrecisionRectangle locationAndSize = new PrecisionRectangle(getHostFigure().getBounds());
        getHostFigure().translateToAbsolute(locationAndSize);
        locationAndSize = (PrecisionRectangle) request.getTransformedRectangle(locationAndSize);
        getHostFigure().translateToRelative(locationAndSize);
        adjustRect(locationAndSize);

        Point normalPoint;
        if (getHost().getParent() instanceof AbstractConnectionEditPart) {
            // In case of a label of an edge, there is specific computation according to the "location" of the label
            // (begin, center, end). Indeed, in GMF, the location is stored according to the reference point (begin,
            // center, end).
            PointList ptList = ((AbstractConnectionEditPart) getHost().getParent()).getConnectionFigure().getPoints();
            normalPoint = EdgeLabelQuery.offsetFromRelativeCoordinate(locationAndSize.getCenter(), ptList, refPoint);
        } else {
            // For a label of a shape, the location is "just" relative to the shape origin.
            normalPoint = LabelHelper.offsetFromRelativeCoordinate(getHostFigure(), locationAndSize, refPoint);
        }

        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();

        ICommand alignCommand = new org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand(editingDomain, DiagramUIMessages.MoveLabelCommand_Label_Location,
                new EObjectAdapter((View) getHost().getModel()), normalPoint);
        return new ICommandProxy(alignCommand);
    }
}
