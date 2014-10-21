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

package org.eclipse.sirius.diagram.ui.internal.edit.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.diagram.ui.internal.operation.CenterEdgeEndModelChangeOperation;

import com.google.common.collect.Iterables;

/**
 * A command that centers (if needed) the incoming and outgoing edges of the
 * given graphicalEditPart.
 * 
 * @author Florian Barbin
 *
 */
public class CenterEditPartEdgesCommand extends AbstractTransactionalCommand {

    IGraphicalEditPart editPart;

    /**
     * Constructor.
     * 
     * @param graphicalEditPart
     *            the edit part for which we need to keep center edges (if they
     *            should be)
     */
    public CenterEditPartEdgesCommand(IGraphicalEditPart graphicalEditPart) {
        super(graphicalEditPart.getEditingDomain(), "Center Edges", null);
        editPart = graphicalEditPart;
    }

    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {

        List<Object> edges = new ArrayList<Object>();
        edges.addAll(editPart.getSourceConnections());
        edges.addAll(editPart.getTargetConnections());
        for (ConnectionEditPart connection : Iterables.filter(edges, ConnectionEditPart.class)) {
            Object model = connection.getModel();
            if (model instanceof Edge) {
                CenterEdgeEndModelChangeOperation centerEdgeEndModelChangeOperation = new CenterEdgeEndModelChangeOperation((Edge) model, false);
                centerEdgeEndModelChangeOperation.execute();
            }

        }

        return CommandResult.newOKCommandResult();
    }

    @Override
    public void dispose() {
        editPart = null;
        super.dispose();
    }

}
