/*******************************************************************************
 * Copyright (c) 2014, 2020 THALES GLOBAL SERVICES and others.
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

package org.eclipse.sirius.diagram.ui.internal.operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.IExpandableFigure;
import org.eclipse.gmf.runtime.diagram.ui.render.util.DiagramImageUtils;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A Change Model Operation that reset the diagram origin bounds.
 * 
 * @author Florian Barbin
 * 
 */
public class ResetOriginChangeModelOperation extends AbstractModelChangeOperation<Void> {

    /** The margin on top and left around the bounding box of all figures. */
    public static final int MARGIN = 20;

    /** The diagram or a container edit part. */
    private GraphicalEditPart containerEditPart;


    private List<Connection> maskedConnections = new ArrayList<Connection>();

    /**
     * Constructor to perform this operation by using the displayed figures
     * bounds of this diagram.
     * 
     * @param diagramEditPart
     *            the root Diagram EditPart.
     */
    public ResetOriginChangeModelOperation(DiagramEditPart diagramEditPart) {
        this.containerEditPart = diagramEditPart;
    }

    /**
     * Constructor to perform this operation by using the displayed figures
     * bounds of this container.
     * 
     * @param diagramEditPart
     *            the root Diagram EditPart.
     */
    public ResetOriginChangeModelOperation(AbstractDiagramContainerEditPart containerEditPart) {
        this.containerEditPart = containerEditPart;
    }

    @Override
    public String getName() {
        String name = Messages.ResetOriginChangeModelOperation_name;
        if (containerEditPart instanceof DiagramEditPart) {
            name = Messages.ResetOriginChangeModelOperation_nameOnDiagram;
        } else if (containerEditPart instanceof AbstractDiagramContainerEditPart) {
            name = Messages.ResetOriginChangeModelOperation_nameOnContainer;
        }
        return name;
    }

    @Override
    public Void execute() {
        routeInvalidEdges();
        // Compute primary edit parts (first level edit parts of the container)
        List<?> primaryEditParts = getPrimaryEditParts();
        removeInvalidEdges(primaryEditParts);
        List<IGraphicalEditPart> primaryGraphicalEditParts = Lists.newArrayList(Iterables.filter(primaryEditParts, IGraphicalEditPart.class));
        // Get top left coordinates of bounding box
        Point topLeft = calculateBoundinBox(primaryGraphicalEditParts).getLocation();
        // Shift all primary edit parts (except edges)
        shiftAllTopDiagramElements(primaryGraphicalEditParts, topLeft);
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
        if (containerEditPart instanceof DiagramEditPart) {
            List<ConnectionEditPart> connections = ((DiagramEditPart) containerEditPart).getConnections();
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
    }

    private void shiftAllTopDiagramElements(List<IGraphicalEditPart> editParts, Point topLeft) {
        for (IGraphicalEditPart editPart : editParts) {
            if (!(editPart instanceof ConnectionEditPart)) {
                if (editPart.getModel() instanceof Node) {
                    LayoutConstraint constraint = ((Node) editPart.getModel()).getLayoutConstraint();
                    if (constraint instanceof Location) {
                        Location location = (Location) constraint;
                        location.setX(location.getX() - topLeft.x());
                        location.setY(location.getY() - topLeft.y());
                    }
                }
            }
        }
    }

    /**
     * Calculates the bounding box around given edit parts.
     * 
     * @param editParts
     *            given edit parts
     * @return the edit parts bounding box
     */
    private Rectangle calculateBoundinBox(List<IGraphicalEditPart> editParts) {
        Rectangle result = null;
        if (containerEditPart instanceof DiagramEditPart) {
            result = DiagramImageUtils.calculateImageRectangle(editParts, MARGIN, new Dimension(0, 0));
        } else if (containerEditPart instanceof AbstractDiagramContainerEditPart) {
            result = calculateBoundinBoxRelativeToContainer(editParts, MARGIN, new Dimension(0, 0));
        }
        if (result == null) {
            result = new Rectangle();
        }
        return result;
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

    /**
     * Gets the primary editparts on this surface, that is, the top-level shapes
     * and connectors.
     * 
     * @return List of primary edit parts. If there are none then it returns a
     *         Collections.EMPTY_LIST, which is immutable
     */
    List<?> getPrimaryEditParts() {
        List<?> result = null;
        if (containerEditPart instanceof DiagramEditPart) {
            result = ((DiagramEditPart) containerEditPart).getPrimaryEditParts();
        } else {
            for (Object child : containerEditPart.getChildren()) {
                if (child instanceof AbstractDNodeContainerCompartmentEditPart) {
                    result = ((AbstractDNodeContainerCompartmentEditPart) child).getChildren();
                }
            }
        }
        if (result == null) {
            result = Collections.EMPTY_LIST;
        }
        return result;
    }

    /**
     * Calculates the bounding box around given edit parts. The bounding box is
     * relative to container. Method inspired from
     * {@link DiagramImageUtils#calculateImageRectangle(List, double, Dimension)}
     * 
     * @param editparts
     *            given edit parts
     * @param frameSize
     *            frame around the bounding box
     * @param defaultSize
     *            if there are no edit parts, the size of the bounding box will
     *            be the default one.
     * @return the edit parts bounding box
     */
    public static final Rectangle calculateBoundinBoxRelativeToContainer(List<IGraphicalEditPart> editparts, double frameSize, Dimension defaultSize) {
        double minX = editparts.isEmpty() ? 0 : Double.MAX_VALUE;
        double maxX = editparts.isEmpty() ? 0 : Double.MIN_VALUE;
        double minY = editparts.isEmpty() ? 0 : Double.MAX_VALUE;
        double maxY = editparts.isEmpty() ? 0 : Double.MIN_VALUE;

        for (IGraphicalEditPart editPart : editparts) {
            IFigure figure = editPart.getFigure();
            Rectangle bounds = null;
            if (figure instanceof IExpandableFigure)
                bounds = ((IExpandableFigure) figure).getExtendedBounds().getCopy();
            else
                bounds = figure.getBounds().getCopy();

            minX = Math.min(minX, bounds.preciseX());
            maxX = Math.max(maxX, bounds.preciseX() + bounds.preciseWidth());
            minY = Math.min(minY, bounds.preciseY());
            maxY = Math.max(maxY, bounds.preciseY() + bounds.preciseHeight());
        }

        PrecisionRectangle rect = new PrecisionRectangle();
        rect.setPreciseWidth(maxX - minX);
        rect.setPreciseHeight(maxY - minY);

        if (defaultSize != null) {
            if (rect.preciseHeight() <= 0) {
                rect.setPreciseHeight(defaultSize.preciseWidth());
            }
            if (rect.preciseHeight() <= 0) {
                rect.setPreciseHeight(defaultSize.preciseHeight());
            }
        }

        rect.setPreciseX(minX - frameSize);
        rect.setPreciseY(minY - frameSize);
        rect.setPreciseWidth(rect.preciseWidth() + 2 * frameSize);
        rect.setPreciseHeight(rect.preciseHeight() + 2 * frameSize);
        return rect;
    }
}
