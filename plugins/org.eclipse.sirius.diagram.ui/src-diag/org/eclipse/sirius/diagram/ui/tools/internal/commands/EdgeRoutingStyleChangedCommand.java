/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.commands;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;

/**
 * Specific command to update the routing style of an edge edit part.
 *
 * @author mporhel
 */
public final class EdgeRoutingStyleChangedCommand extends RecordingCommand {

    private final IDiagramEdgeEditPart editpart;

    private final Notification msg;

    /**
     * Constructor.
     *
     * @param domain
     *            the editing domain.
     * @param msg
     *            the notification
     * @param editpart
     *            the edge edit part to update.
     */
    public EdgeRoutingStyleChangedCommand(TransactionalEditingDomain domain, IDiagramEdgeEditPart editpart, Notification msg) {
        super(domain, Messages.EdgeRoutingStyleChangedCommand_label);
        this.editpart = editpart;
        this.msg = msg;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (editpart == null || msg == null) {
            return;
        }

        editpart.routingStyleChanged(msg);
    }
}
