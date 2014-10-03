/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/

package org.eclipse.sirius.diagram.ui.internal.operation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.render.util.DiagramImageUtils;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A Change Model Operation that reset the diagram origin bounds.
 * 
 * @author Florian Barbin
 *
 */
public class ResetOriginChangeModelOperation extends AbstractModelChangeOperation<Void> {

    private DiagramEditPart diagramEditPart;

    private int MARGIN = 20;

    private List<Connection> maskedConnections = new ArrayList<Connection>();

    /**
     * Constructor to perform this operation by using the displayed figures
     * bounds.
     * 
     * @param diagramEditPart
     *            the root Diagram EditPart.
     */
    public ResetOriginChangeModelOperation(DiagramEditPart diagramEditPart) {
        this.diagramEditPart = diagramEditPart;
    }

    @Override
    public String getName() {
        return "Reset Diagram Origin";
    }

    @Override
    public Void execute() {
        routeInvalidEdges();
        Point topLeft = getTopLeftCoordinates();
        shiftAllTopDiagramElements(topLeft);
        if (!topLeft.equals(new Point(0, 0))) {
            for (Connection currentConnection : maskedConnections) {
                // we use this workaround because of the masked edges which are
                // not
                // routed when we perform the reset origin. If we do not
                // translate masked edges points, the figure is still located as
                // before the reset origin and that causes persistent scroll-bar
                // if the old location is out of the viewer bounds.
                currentConnection.getPoints().performTranslate(-topLeft.x, -topLeft.y);
            }
        }
        return null;
    }

    /**
     * Layout masked edges to have the correct bounds.<br />
     * Some edges can be masked because of a scrollbar on a container. If one of
     * the edge end is masked by the scroll size, the edge is masked too. The
     * OrthogonalLayout doesn't relocate the masked edges and that causes wrong
     * diagram bounds computation and persistent scroll bar if the old edge
     * location is out of the current bounds.
     */
    private void routeInvalidEdges() {
        List<ConnectionEditPart> connections = diagramEditPart.getConnections();
        for (ConnectionEditPart connection : connections) {
            IFigure figure = connection.getFigure();
            Object model = connection.getModel();
            if (figure instanceof PolylineConnection && model instanceof Edge) {
                if (!figure.isVisible() && ((Edge) model).isVisible()) {
                    figure.setVisible(true);
                    ((PolylineConnection) figure).layout();
                    figure.setVisible(false);
                    maskedConnections.add((Connection) figure);
                }
            }
        }

    }

    private void shiftAllTopDiagramElements(Point topLeft) {
        Object model = this.diagramEditPart.getModel();
        if (model instanceof Diagram) {
            Diagram gmfDiagram = (Diagram) model;
            for (Object view : gmfDiagram.getChildren()) {
                if (view instanceof Node) {
                    LayoutConstraint constraint = ((Node) view).getLayoutConstraint();
                    if (constraint instanceof Location) {
                        Location location = (Location) constraint;
                        location.setX(location.getX() - topLeft.x());
                        location.setY(location.getY() - topLeft.y());
                    }
                }
            }
        }
    }

    private Point getTopLeftCoordinates() {
        List<?> primaryEditParts = diagramEditPart.getPrimaryEditParts();
        removeInvalidEdges(primaryEditParts);
        Iterable<IGraphicalEditPart> iterable = Iterables.filter(primaryEditParts, IGraphicalEditPart.class);
        Rectangle bounds = DiagramImageUtils.calculateImageRectangle(Lists.newArrayList(iterable), MARGIN, new Dimension(0, 0));
        return bounds.getLocation();

    }

    /**
     * If an edge source or target has just been hidden, or the edge is hidden
     * by user, the edge editPart can still be among the primary EditParts (but
     * not visible anymore). We remove them from the list to compute the correct
     * bounds. Note that some edges can be hidden due to a source or target
     * which is no visible (because of scroll bar within a container for
     * instance); in this case consider the edge as visible.
     * 
     * @param primaryEditParts
     *            the primary editParts list.
     */
    private void removeInvalidEdges(List<?> primaryEditParts) {
        for (Iterator<?> iterator = primaryEditParts.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            if (next instanceof AbstractConnectionEditPart) {
                IFigure figure = ((AbstractConnectionEditPart) next).getFigure();
                Object model = ((AbstractConnectionEditPart) next).getModel();
                if (model instanceof View && !((View) model).isVisible() && !figure.isVisible()) {
                    iterator.remove();
                }
            }
        }
    }
}
