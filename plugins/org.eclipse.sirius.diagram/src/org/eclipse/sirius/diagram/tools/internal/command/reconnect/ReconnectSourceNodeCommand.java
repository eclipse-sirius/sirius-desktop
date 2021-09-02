/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.tools.internal.command.reconnect;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.tools.api.Messages;

/**
 * Specific command to change the source node and the semantic target of a
 * DEdge.
 * 
 * @author mporhel
 */
public final class ReconnectSourceNodeCommand extends RecordingCommand {

    private final DEdge edge;

    private final EdgeTarget reconnectionTarget;

    private final EObject semanticTarget;

    /**
     * Constructor.
     * 
     * @param domain
     *            the editing domain.
     * @param reconnectionTarget
     *            the reconnection target
     * @param semanticTarget
     *            the semantic target
     * @param edge
     *            the DEdge to update.
     */
    public ReconnectSourceNodeCommand(TransactionalEditingDomain domain, DEdge edge, EdgeTarget reconnectionTarget, EObject semanticTarget) {
        super(domain, Messages.ReconnectSourceNodeCommand_commandLabel);
        this.edge = edge;
        this.reconnectionTarget = reconnectionTarget;
        this.semanticTarget = semanticTarget;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (edge == null) {
            return;
        }

        edge.setSourceNode(reconnectionTarget);
        edge.setTarget(semanticTarget);
    }
}
