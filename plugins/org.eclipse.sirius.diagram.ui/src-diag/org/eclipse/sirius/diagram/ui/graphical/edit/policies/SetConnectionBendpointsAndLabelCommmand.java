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
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.commands.SetConnectionBendpointsCommand;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.ui.business.api.query.ConnectionEditPartQuery;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.locator.EdgeLabelsComputationUtil;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * This class allows to update edge label Node position when updating
 * bendpoints.
 *
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 *
 */
public class SetConnectionBendpointsAndLabelCommmand extends SetConnectionBendpointsCommand {

    /**
     * The labels with their offset computed during the call to
     * {@link #setLabelsToUpdate(org.eclipse.gef.ConnectionEditPart)}. This list
     * is then used during the execution of the command to update the label
     * location.
     */
    private Map<Node, Point> labelsWithNewOffset;

    /** Old bendpoints used in case of feedback that impact the edge. */
    private PointList oldBendPointsList;

    /**
     * Default constructor.
     *
     * @param editingDomain
     *            the editing domain through which model changes are made
     */
    public SetConnectionBendpointsAndLabelCommmand(TransactionalEditingDomain editingDomain) {
        super(editingDomain);
    }

    @Override
    protected IStatus doExecute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        updateGMFLabelsOffset();
        return super.doExecute(monitor, info);
    }

    /**
     * Set labels to update according to a connectionEditPart (all labels of
     * this connection will be update). <BR>
     * This method must be called after having called the
     * {@link #setNewPointList(PointList, org.eclipse.draw2d.ConnectionAnchor, org.eclipse.draw2d.ConnectionAnchor)}
     * of {@link #setNewPointList(PointList, Point, Point)} method.
     *
     * @param connectionEditPart
     *            The connection from which to get the potential three labels to
     *            update
     */
    public void setLabelsToUpdate(org.eclipse.gef.ConnectionEditPart connectionEditPart) {
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

        computeGMFLabelsOffset(labelEditPartsToUpdate, connectionEditPart, getNewPointList());
    }

    /**
     * Set labels to update according to a connectionEditPart (all labels of
     * this connection will be update). This method must be used if the edge
     * figure is updated (through feedback) during the move. Indeed, in this
     * case, we can not use the figure to retrieve the old points.<BR>
     * This method must be called after having called the
     * {@link #setNewPointList(PointList, org.eclipse.draw2d.ConnectionAnchor, org.eclipse.draw2d.ConnectionAnchor)}
     * of {@link #setNewPointList(PointList, Point, Point)} method.
     *
     * @param connectionEditPart
     *            The connection from which to get the potential three labels to
     *            update
     * @param originalPoints
     *            The points of the edge before the move.
     */
    public void setLabelsToUpdate(org.eclipse.gef.ConnectionEditPart connectionEditPart, PointList originalPoints) {
        oldBendPointsList = originalPoints;
        setLabelsToUpdate(connectionEditPart);
    }

    /**
     * Update {@link Bounds} of the labels {@link Node}.
     *
     * @param newBendPointsList
     *            Bendpoints list after the edge modification
     */
    private void computeGMFLabelsOffset(List<AbstractDEdgeNameEditPart> labelEditPartsToUpdate, org.eclipse.gef.ConnectionEditPart connectionEditPart, PointList newBendPointsList) {
        labelsWithNewOffset = Maps.newHashMap();
        // For each label, compute the new offset, and also update the
        // LabelLocator
        for (AbstractDEdgeNameEditPart labelEditPartToUpdate : labelEditPartsToUpdate) {
            computeGMFLabelOffset(connectionEditPart, labelEditPartToUpdate, newBendPointsList);
        }

    }

    /**
     * Update {@link Bounds} of a label {@link Node}.
     *
     * @param labelEdgeEditPart
     *            the editPart of the edge label to be updated
     * @param newBendPointsList
     *            Bendpoints list after the edge modification
     */
    private void computeGMFLabelOffset(org.eclipse.gef.ConnectionEditPart connectionEditPart, AbstractDEdgeNameEditPart labelEditPartToUpdate, PointList newBendPointsList) {
        Point newLabelOffset = null;
        Node labelNodeToUpdate = (Node) labelEditPartToUpdate.getModel();
        if (connectionEditPart instanceof ConnectionEditPart && connectionEditPart.getModel() instanceof Edge) {
            PointList oldBendpoints = oldBendPointsList;
            if (oldBendpoints == null) {
                oldBendpoints = ((ConnectionEditPart) connectionEditPart).getConnectionFigure().getPoints();
            }
            boolean isEdgeWithObliqueRoutingStyle = new ConnectionEditPartQuery(connectionEditPart).isEdgeWithObliqueRoutingStyle();
            LayoutConstraint layoutConstraint = labelNodeToUpdate.getLayoutConstraint();
            if (layoutConstraint instanceof Bounds) {
                Bounds bounds = (Bounds) layoutConstraint;
                newLabelOffset = new EdgeLabelsComputationUtil(oldBendpoints, newBendPointsList, isEdgeWithObliqueRoutingStyle, new Point(bounds.getX(), bounds.getY()),
                        labelEditPartToUpdate.getKeyPoint()).calculateGMFLabelOffset();
            }
        }

        if (newLabelOffset != null) {
            labelsWithNewOffset.put(labelNodeToUpdate, newLabelOffset);
        }
    }

    /**
     * Update {@link Bounds} of the labels {@link Node}.
     *
     * @param newBendPointsList
     *            Bendpoints list after the edge modification
     */
    private void updateGMFLabelsOffset() {

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
