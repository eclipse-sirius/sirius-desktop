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
package org.eclipse.sirius.diagram.ui.internal.edit.policies;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.BendpointLocator;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConnectionBendpointEditPolicy;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.gef.ui.internal.editpolicies.LineMode;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.MoveEdgeGroupManager;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SetConnectionBendpointsAndLabelCommmand;
import org.eclipse.sirius.diagram.ui.internal.edit.handles.SiriusBendpointCreationInvisibleHandle;
import org.eclipse.sirius.diagram.ui.internal.edit.handles.SiriusBendpointMoveHandle;
import org.eclipse.sirius.diagram.ui.internal.edit.handles.SiriusLineSegMoveInvisibleHandle;

/**
 * A specific policy to handle the snapToAll and moveEdgeGroup features.
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
@SuppressWarnings("restriction")
public class SiriusConnectionBendpointEditPolicy extends ConnectionBendpointEditPolicy {
    private InitialPointsOfRequestDataManager initialPointsManager = new InitialPointsOfRequestDataManager();

    /**
     * Default constructor.
     */
    public SiriusConnectionBendpointEditPolicy() {
        super(LineMode.OBLIQUE);
    }

    public SiriusConnectionBendpointEditPolicy(LineMode orthogonalFree) {
        super(orthogonalFree);
    }

    @Override
    public Command getCommand(Request request) {
        MoveEdgeGroupManager nodesGroupMoveManager = new MoveEdgeGroupManager(request);
        if (nodesGroupMoveManager.isToolActivated()) {
            return nodesGroupMoveManager.getCommand();

        } else {
            return super.getCommand(request);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    protected List createManualHandles() {
        List list = new ArrayList();
        ConnectionEditPart connEP = (ConnectionEditPart) getHost();
        PointList points = getConnection().getPoints();
        for (int i = 1; i < points.size() - 1; i++) {
            addInvisibleCreationHandle(list, connEP, i - 1);
            // Use a SiriusBendpointMoveHandle to handle the snap to all shapes
            // feature
            list.add(new SiriusBendpointMoveHandle(connEP, i, new BendpointLocator(getConnection(), i)));
        }
        addInvisibleCreationHandle(list, connEP, points.size() - 2);
        return list;
    }

    @Override
    protected Command getBendpointsChangedCommand(BendpointRequest request) {
        PointList originalPoints = InitialPointsOfRequestDataManager.getOriginalPoints(request);
        Command result = super.getBendpointsChangedCommand(request);
        if (result instanceof ICommandProxy) {
            ICommand iCommand = ((ICommandProxy) result).getICommand();
            if (iCommand instanceof SetConnectionBendpointsAndLabelCommmand) {
                ((SetConnectionBendpointsAndLabelCommmand) iCommand).setLabelsToUpdate((org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart) getHost(), originalPoints);
            }
        }
        return result;
    }

    @Override
    protected Command getBendpointsChangedCommand(Connection connection, Edge edge) {
        Point ptRef1 = connection.getSourceAnchor().getReferencePoint();
        getConnection().translateToRelative(ptRef1);

        Point ptRef2 = connection.getTargetAnchor().getReferencePoint();
        getConnection().translateToRelative(ptRef2);

        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();

        SetConnectionBendpointsAndLabelCommmand sbbCommand = new SetConnectionBendpointsAndLabelCommmand(editingDomain);
        sbbCommand.setEdgeAdapter(new EObjectAdapter(edge));
        sbbCommand.setNewPointList(connection.getPoints(), ptRef1, ptRef2);

        return new ICommandProxy(sbbCommand);
    }

    @Override
    public void showSourceFeedback(Request request) {
        MoveEdgeGroupManager nodesGroupMoveManager = new MoveEdgeGroupManager(request);
        if (nodesGroupMoveManager.isToolActivated()) {
            nodesGroupMoveManager.showGroupFeedback();
        } else {
            nodesGroupMoveManager.eraseGroupFeedback();
            super.showSourceFeedback(request);
        }
    }

    @Override
    public void eraseSourceFeedback(Request request) {
        MoveEdgeGroupManager nodesGroupMoveManager = new MoveEdgeGroupManager(request);
        nodesGroupMoveManager.eraseGroupFeedback();
        super.eraseSourceFeedback(request);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    protected void addInvisibleCreationHandle(List list, org.eclipse.gef.ConnectionEditPart connEP, int i) {
        if (getLineSegMode() != LineMode.OBLIQUE) {
            list.add(new SiriusLineSegMoveInvisibleHandle(connEP, i));
        } else {
            list.add(new SiriusBendpointCreationInvisibleHandle(connEP, i));
        }
    }

    @Override
    protected void showMoveBendpointFeedback(BendpointRequest request) {
        initialPointsManager.storeInitialPointsInRequest(request, (ConnectionEditPart) getHost());
        super.showMoveBendpointFeedback(request);
    }

    @Override
    protected void showCreateBendpointFeedback(BendpointRequest request) {
        initialPointsManager.storeInitialPointsInRequest(request, (ConnectionEditPart) getHost());
        super.showCreateBendpointFeedback(request);
    }

    /**
     * Override to clean the initial points of the edge.
     */
    @Override
    protected void eraseConnectionFeedback(BendpointRequest request, boolean removeFeedbackFigure) {
        super.eraseConnectionFeedback(request, removeFeedbackFigure);
        if (removeFeedbackFigure) {
            initialPointsManager.eraseInitialPoints(getConnection());
        }
    }
}
