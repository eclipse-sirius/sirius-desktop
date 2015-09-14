/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.provider.Messages;

/**
 * @was-generated
 */
public class SiriusReorientConnectionViewCommand extends AbstractTransactionalCommand {

    /**
     * @was-generated
     */
    private IAdaptable edgeAdaptor;

    /**
     * @was-generated
     */
    public SiriusReorientConnectionViewCommand(TransactionalEditingDomain editingDomain, String label) {
        super(editingDomain, label, null);
    }

    /**
     * @was-generated
     */
    @Override
    public List getAffectedFiles() {
        View view = (View) edgeAdaptor.getAdapter(View.class);
        if (view != null) {
            return getWorkspaceFiles(view);
        }
        return super.getAffectedFiles();
    }

    /**
     * @was-generated
     */
    public IAdaptable getEdgeAdaptor() {
        return edgeAdaptor;
    }

    /**
     * @was-generated
     */
    public void setEdgeAdaptor(IAdaptable edgeAdaptor) {
        this.edgeAdaptor = edgeAdaptor;
    }

    /**
     * @was-generated
     */
    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info) {
        assert null != edgeAdaptor : Messages.SiriusReorientConnectionViewCommand_nullChild;
        Edge edge = (Edge) getEdgeAdaptor().getAdapter(Edge.class);
        assert null != edge : Messages.SiriusReorientConnectionViewCommand_nullEdge;
        View tempView = edge.getSource();
        edge.setSource(edge.getTarget());
        edge.setTarget(tempView);
        return CommandResult.newOKCommandResult();
    }
}
