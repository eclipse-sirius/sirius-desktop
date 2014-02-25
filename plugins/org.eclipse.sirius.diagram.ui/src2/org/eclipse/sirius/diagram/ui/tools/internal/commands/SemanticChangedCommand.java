/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramElementEditPartOperation;

/**
 * Specific command to update the edit part when semantic changed.
 * 
 * @author mporhel
 */
public final class SemanticChangedCommand extends RecordingCommand {

    private final IDiagramElementEditPart part;

    private final Notification msg;

    /**
     * Constructor.
     * 
     * @param domain
     *            the editing domain.
     * @param msg
     *            the notification
     * @param editpart
     *            the diagram element edit part to update.
     */
    public SemanticChangedCommand(TransactionalEditingDomain domain, IDiagramElementEditPart editpart, Notification msg) {
        super(domain, "Semantic changed");
        this.part = editpart;
        this.msg = msg;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (part != null && msg != null) {
            DiagramElementEditPartOperation.semanticChanged(part, msg);
        }
    }
}
