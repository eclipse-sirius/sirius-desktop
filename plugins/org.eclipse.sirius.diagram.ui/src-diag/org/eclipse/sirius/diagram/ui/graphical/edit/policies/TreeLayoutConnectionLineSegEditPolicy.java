/*******************************************************************************
 * Copyright (c) 2012, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.BendpointLocator;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.gef.ui.internal.editpolicies.LineMode;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.diagram.ui.business.api.query.ConnectionEditPartQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.ConnectionQuery;
import org.eclipse.sirius.diagram.ui.business.internal.command.TreeLayoutSetConnectionBendpointsCommand;
import org.eclipse.sirius.diagram.ui.internal.edit.handles.SiriusBendpointMoveHandle;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.InitialPointsOfRequestDataManager;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.SiriusConnectionBendpointEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.operation.CenterEdgeEndModelChangeOperation;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;

/**
 * A specific ConnectionLineSegEditPolicy to override
 * getBendpointsChangedCommand to change all GMF edges of this tree (and not
 * only the moved edge).
 *
 * @author jdupont
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
@SuppressWarnings("restriction")
public class TreeLayoutConnectionLineSegEditPolicy extends SiriusConnectionBendpointEditPolicy {
    private InitialPointsOfRequestDataManager initialPointsManager = new InitialPointsOfRequestDataManager();

    /**
     * Default constructor.
     */
    public TreeLayoutConnectionLineSegEditPolicy() {
        super(LineMode.ORTHOGONAL_FREE);
    }

    @Override
    protected Command getBendpointsChangedCommand(BendpointRequest request) {
        PointList originalPoints = InitialPointsOfRequestDataManager.getOriginalPoints(request);
        Command result = super.getBendpointsChangedCommand(request);
        // Search the SetConnectionBendpointsAndLabelCommmand to call
        // setLabelsToUpdate
        if (result instanceof CompoundCommand) {
            for (Object subCmd : ((CompoundCommand) result).getChildren()) {
                if (subCmd instanceof ICommandProxy) {
                    ICommand iCommand = ((ICommandProxy) subCmd).getICommand();
                    if (iCommand instanceof SetConnectionBendpointsAndLabelCommmand) {
                        ((SetConnectionBendpointsAndLabelCommmand) iCommand).setLabelsToUpdate((org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart) getHost(), originalPoints);
                    }
                }
            }
        } else if (result instanceof ICommandProxy) {
            ICommand iCommand = ((ICommandProxy) result).getICommand();
            if (iCommand instanceof SetConnectionBendpointsAndLabelCommmand) {
                ((SetConnectionBendpointsAndLabelCommmand) iCommand).setLabelsToUpdate((org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart) getHost(), originalPoints);
            }
        }
        return result;
    }

    /**
     * Override to launch a specific SetConnectionBendpointsCommand (
     * {@link TreeLayoutSetConnectionBendpointsCommand}. {@inheritDoc}
     *
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConnectionBendpointEditPolicy#getBendpointsChangedCommand(org.eclipse.draw2d.Connection,
     *      org.eclipse.gmf.runtime.notation.Edge)
     */
    @Override
    protected Command getBendpointsChangedCommand(Connection connection, Edge edge) {
        boolean isTreeLayout = false;
        if (new ConnectionQuery(connection).isOrthogonalTreeBranch(connection.getPoints())) {
            if (getHost() instanceof ConnectionEditPart) {
                if (new ConnectionEditPartQuery((ConnectionEditPart) getHost()).isLayoutComponent()) {
                    isTreeLayout = true;
                }
            }
        }

        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
        if (!isTreeLayout) {
            // we add a new command based on CenterEdgeEndModelChangeOperation
            // to center the edge end(s) if needed.
            CompoundCommand compoundCommand = new CompoundCommand();
            // Start : Copied from
            // ConnectionBendpointEditPolicy.getBendpointsChangedCommand to
            // replace SetConnectionBendpointsCommand by a
            // SetConnectionBendpointsAndLabelCommmand.
            Point ptRef1 = connection.getSourceAnchor().getReferencePoint();
            getConnection().translateToRelative(ptRef1);

            Point ptRef2 = connection.getTargetAnchor().getReferencePoint();
            getConnection().translateToRelative(ptRef2);

            SetConnectionBendpointsAndLabelCommmand sbbCommand = new SetConnectionBendpointsAndLabelCommmand(editingDomain);
            sbbCommand.setEdgeAdapter(new EObjectAdapter(edge));
            sbbCommand.setNewPointList(connection.getPoints(), ptRef1, ptRef2);
            // End :

            compoundCommand.add(new ICommandProxy(sbbCommand));
            ICommand command = CommandFactory.createICommand(editingDomain, new CenterEdgeEndModelChangeOperation((ConnectionEditPart) getHost(), edge));
            compoundCommand.add(new ICommandProxy(command));
            return compoundCommand;
        }

        Point ptRef1 = connection.getSourceAnchor().getReferencePoint();
        getConnection().translateToRelative(ptRef1);

        Point ptRef2 = connection.getTargetAnchor().getReferencePoint();
        getConnection().translateToRelative(ptRef2);

        TreeLayoutSetConnectionBendpointsCommand sbbCommand = new TreeLayoutSetConnectionBendpointsCommand(editingDomain);
        sbbCommand.setEdgeAdapter(new EObjectAdapter(edge));
        sbbCommand.setNewPointList(connection.getPoints(), ptRef1, ptRef2);

        return new ICommandProxy(sbbCommand);
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

    /**
     * Override to store the initial points of the edge in the request.
     */
    @Override
    protected void showMoveLineSegFeedback(BendpointRequest request) {
        initialPointsManager.storeInitialPointsInRequest(request, (ConnectionEditPart) getHost());
        super.showMoveLineSegFeedback(request);
    }

    /**
     * Override only to modify private showMoveOrthogonalBenspointFeedback
     * method.
     */
    @Override
    public void showSourceFeedback(Request request) {
        MoveEdgeGroupManager nodesGroupMoveManager = new MoveEdgeGroupManager(request);
        if (nodesGroupMoveManager.isToolActivated()) {
            nodesGroupMoveManager.showGroupFeedback();
        } else {
            nodesGroupMoveManager.eraseGroupFeedback();
            if (getLineSegMode() != LineMode.OBLIQUE) {
                if (REQ_CREATE_BENDPOINT.equals(request.getType())) {
                    showMoveLineSegFeedback((BendpointRequest) request);
                } else if (REQ_MOVE_BENDPOINT.equals(request.getType())) {
                    showMoveOrthogonalBendpointFeedback2((BendpointRequest) request);
                }

            } else {
                if (REQ_MOVE_BENDPOINT.equals(request.getType())) {
                    showMoveBendpointFeedback((BendpointRequest) request);
                } else if (REQ_CREATE_BENDPOINT.equals(request.getType())) {
                    showCreateBendpointFeedback((BendpointRequest) request);
                }
            }
        }
    }

    /**
     * Draws feedback for moving a bend point of a rectilinear connection. See
     * {@link org.eclipse.gmf.runtime.gef.ui.internal.editpolicies.ConnectionBendpointEditPolicy#showMoveOrthogonalBenspointFeedback(BendpointRequest)}
     * .
     *
     * @param request
     *            Bendpoint request
     */
    protected void showMoveOrthogonalBendpointFeedback2(BendpointRequest request) {
        // Change visibility of super method and call it. The bugzilla 331779
        // exists to make this method protected.
        if (!ReflectionHelper.invokeMethodWithoutException(this, org.eclipse.gmf.runtime.gef.ui.internal.editpolicies.ConnectionBendpointEditPolicy.class, "showMoveOrthogonalBenspointFeedback", //$NON-NLS-1$
                new Class[] { BendpointRequest.class }, new Object[] { request }, true)) {
            DiagramUIPlugin.INSTANCE.log(new Status(IStatus.WARNING, DiagramUIPlugin.ID,
                    "Impossible to call showMoveOrthogonalBenspointFeedback by reflection to handle edge label correctly during edge move (bug 465328).")); //$NON-NLS-1$
        } else {
            initialPointsManager.storeInitialPointsInRequest(request, (ConnectionEditPart) getHost());
        }
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
