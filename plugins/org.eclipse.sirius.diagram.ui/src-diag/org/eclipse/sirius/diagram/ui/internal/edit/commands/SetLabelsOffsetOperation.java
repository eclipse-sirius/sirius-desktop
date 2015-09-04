/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.commands;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.ui.business.api.query.ConnectionEditPartQuery;
import org.eclipse.sirius.diagram.ui.graphical.edit.part.specific.BracketEdgeEditPart;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SetConnectionBendpointsAndLabelCommmand;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.locator.EdgeLabelQuery;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Operation concerning the label offset. Used to delegate from the
 * {@link SetLabelsOffsetCommmand} and from the
 * {@link SetConnectionBendpointsAndLabelCommmand}.
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class SetLabelsOffsetOperation {
    /**
     * The labels with their offset computed during the call to
     * {@link #setLabelsToUpdate(ConnectionEditPart)}. This list is then used
     * during the execution of the operations to update the label location.
     */
    private Map<Node, Point> labelsWithNewOffset;

    /** The old points list, used in case of feedback that impacts the edge. */
    private PointList oldBendPointsList;

    /** The new points list. */
    private PointList newPointList;

    /**
     * Method to set the newPointList.
     *
     * @param newPointList
     *            The new points list
     */
    public void setNewPointList(PointList newPointList) {
        this.newPointList = new PointList(newPointList.size());
        for (int i = 0; i < newPointList.size(); i++) {
            this.newPointList.addPoint(newPointList.getPoint(i));
        }
    }

    /**
     * Set labels to update according to a connectionEditPart (all labels of
     * this connection will be update). This method must be used if the edge
     * figure is updated (through feedback) during the move. Indeed, in this
     * case, we can not use the figure to retrieve the old points.<BR>
     * This method must be called after having called the
     * {@link #setNewPointList(PointList)} method.
     *
     * @param connectionEditPart
     *            The connection from which to get the potential three labels to
     *            update
     * @param originalPoints
     *            The points of the edge before the move.
     */
    public void setLabelsToUpdate(ConnectionEditPart connectionEditPart, PointList originalPoints) {
        oldBendPointsList = originalPoints;
        setLabelsToUpdate(connectionEditPart);
    }

    /**
     * Set labels to update according to a connectionEditPart (all labels of
     * this connection will be update). <BR>
     * This method must be called after having called the
     * {@link #setNewPointList(PointList)} method.
     *
     * @param connectionEditPart
     *            The connection from which to get the potential three labels to
     *            update
     */
    public void setLabelsToUpdate(ConnectionEditPart connectionEditPart) {
        List<AbstractDEdgeNameEditPart> labelEditPartsToUpdate = Lists.newArrayList();
        List<?> children = connectionEditPart.getChildren();
        for (Object child : children) {
            if (child instanceof AbstractDEdgeNameEditPart) {
                Object view = ((AbstractDEdgeNameEditPart) child).getModel();
                if (view instanceof Node) {
                    labelEditPartsToUpdate.add((AbstractDEdgeNameEditPart) child);
                }
            }
        }

        computeGMFLabelsOffset(labelEditPartsToUpdate, connectionEditPart);
    }

    /**
     * Update {@link Bounds} of the labels {@link Node}.
     *
     * @param labelEditPartsToUpdate
     *            List of labels to update
     * @param connectionEditPart
     *            The connection having these labels
     */
    private void computeGMFLabelsOffset(List<AbstractDEdgeNameEditPart> labelEditPartsToUpdate, ConnectionEditPart connectionEditPart) {
        labelsWithNewOffset = Maps.newHashMap();
        // For each label, compute the new offset
        for (AbstractDEdgeNameEditPart labelEditPartToUpdate : labelEditPartsToUpdate) {
            computeGMFLabelOffset(labelEditPartToUpdate, connectionEditPart);
        }

    }

    /**
     * Update {@link Bounds} of a label {@link Node}.
     *
     * @param labelEdgeEditPart
     *            the editPart of the edge label to be updated
     * @param connectionEditPart
     *            The connection having these labels
     */
    private void computeGMFLabelOffset(AbstractDEdgeNameEditPart labelEditPartToUpdate, ConnectionEditPart connectionEditPart) {
        Point newLabelOffset = null;
        Node labelNodeToUpdate = (Node) labelEditPartToUpdate.getModel();
        if (connectionEditPart.getModel() instanceof Edge) {
            PointList oldBendpoints = oldBendPointsList;
            if (oldBendpoints == null) {
                oldBendpoints = connectionEditPart.getConnectionFigure().getPoints();
            }
            boolean isEdgeWithObliqueRoutingStyle = new ConnectionEditPartQuery(connectionEditPart).isEdgeWithObliqueRoutingStyle();
            LayoutConstraint layoutConstraint = labelNodeToUpdate.getLayoutConstraint();
            if (layoutConstraint instanceof Bounds) {
                Bounds bounds = (Bounds) layoutConstraint;
                newLabelOffset = new EdgeLabelQuery(oldBendpoints, newPointList, isEdgeWithObliqueRoutingStyle, new Point(bounds.getX(), bounds.getY()), labelEditPartToUpdate.getFigure().getSize(),
                        labelEditPartToUpdate.getKeyPoint(), connectionEditPart instanceof BracketEdgeEditPart).calculateGMFLabelOffset();
            }
        }

        if (newLabelOffset != null) {
            labelsWithNewOffset.put(labelNodeToUpdate, newLabelOffset);
        }
    }

    /**
     * Update the offset ({@link Bounds}) of the labels {@link Node}. This
     * method must be called after setting the newPointList and the
     * labelToUpdate.
     */
    public void updateGMFLabelsOffset() {
        // Update Bounds of the three labels Node (Center, Begin and End)
        Set<Entry<Node, Point>> entries = labelsWithNewOffset.entrySet();
        for (Entry<Node, Point> entry : entries) {
            LayoutConstraint layoutConstraint = entry.getKey().getLayoutConstraint();
            if (layoutConstraint instanceof Bounds) {
                Bounds bounds = (Bounds) layoutConstraint;
                bounds.setX(entry.getValue().x);
                bounds.setY(entry.getValue().y);
            }
        }
    }
}
