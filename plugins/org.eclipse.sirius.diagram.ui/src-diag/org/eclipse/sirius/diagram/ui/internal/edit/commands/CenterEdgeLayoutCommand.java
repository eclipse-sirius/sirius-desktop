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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.operation.CenterEdgeEndModelChangeOperation;

/**
 * After an automatic layout, the edge centering could be broken. This command
 * executes {@link CenterEdgeEndModelChangeOperation} on each edges potentially
 * impacted by the given containerEditPart layout.
 * 
 * @author Florian Barbin
 *
 */
public class CenterEdgeLayoutCommand extends AbstractTransactionalCommand {

    private GraphicalEditPart editPart;

    /**
     * Constructor.
     * 
     * @param graphicalEditPart
     *            the graphical edit part layouted.
     */
    public CenterEdgeLayoutCommand(GraphicalEditPart graphicalEditPart) {
        super(graphicalEditPart.getEditingDomain(), "", null); //$NON-NLS-1$
        editPart = graphicalEditPart;
    }

    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        Object model = editPart.getModel();
        if (model instanceof View) {
            View view = (View) model;
            Set<Edge> edges = new HashSet<Edge>();

            // if the entire diagram is layouted.
            if (view instanceof Diagram) {
                edges.addAll(((Diagram) view).getEdges());
            }
            // we select only related edges otherwise.
            else {
                ViewUtil.getAllRelatedEdgesForView(view, edges);
            }
            for (Edge edge : edges) {
                CenterEdgeEndModelChangeOperation centerEdgeEndModelChangeOperation = new CenterEdgeEndModelChangeOperation(edge, false);
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
