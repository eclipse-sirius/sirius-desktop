/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.BendpointLocator;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editpolicies.ConnectionLineSegEditPolicy;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.diagram.ui.business.api.query.ConnectionEditPartQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.ConnectionQuery;
import org.eclipse.sirius.diagram.ui.business.internal.command.TreeLayoutSetConnectionBendpointsCommand;
import org.eclipse.sirius.diagram.ui.internal.edit.handles.SiriusBendpointMoveHandle;
import org.eclipse.sirius.diagram.ui.internal.operation.CenterEdgeEndModelChangeOperation;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;

/**
 * A specific ConnectionLineSegEditPolicy to override
 * getBendpointsChangedCommand to change all GMF edges of this tree (and not
 * only the moved edge).
 * 
 * @author jdupont
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class TreeLayoutConnectionLineSegEditPolicy extends ConnectionLineSegEditPolicy {

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
            compoundCommand.add(super.getBendpointsChangedCommand(connection, edge));
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
}
