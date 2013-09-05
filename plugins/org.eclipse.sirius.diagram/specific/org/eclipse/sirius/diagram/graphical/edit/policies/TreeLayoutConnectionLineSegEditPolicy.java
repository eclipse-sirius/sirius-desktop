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
package org.eclipse.sirius.diagram.graphical.edit.policies;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editpolicies.ConnectionLineSegEditPolicy;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Edge;

import org.eclipse.sirius.diagram.business.api.query.ConnectionEditPartQuery;
import org.eclipse.sirius.diagram.business.api.query.ConnectionQuery;
import org.eclipse.sirius.diagram.business.internal.command.TreeLayoutSetConnectionBendpointsCommand;

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
        if (!isTreeLayout) {
            return super.getBendpointsChangedCommand(connection, edge);
        }

        Point ptRef1 = connection.getSourceAnchor().getReferencePoint();
        getConnection().translateToRelative(ptRef1);

        Point ptRef2 = connection.getTargetAnchor().getReferencePoint();
        getConnection().translateToRelative(ptRef2);

        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
        TreeLayoutSetConnectionBendpointsCommand sbbCommand = new TreeLayoutSetConnectionBendpointsCommand(editingDomain);
        sbbCommand.setEdgeAdapter(new EObjectAdapter(edge));
        sbbCommand.setNewPointList(connection.getPoints(), ptRef1, ptRef2);

        return new ICommandProxy(sbbCommand);
    }
}
