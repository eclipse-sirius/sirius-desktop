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

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.common.tools.api.listener.NotificationUtil;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.business.api.helper.concern.ConcernService;

/**
 * Specific command to set current concern to default.
 * 
 * @author mporhel
 */
public final class SetDefaultConcernCommand extends RecordingCommand {

    private final DDiagram diag;

    /**
     * Constructor.
     * 
     * @param domain
     *            the editing domain.
     * @param diagram
     *            the current diagram.
     */
    public SetDefaultConcernCommand(TransactionalEditingDomain domain, DDiagram diagram) {
        super(domain, "Set current concern to default");
        this.diag = diagram;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (diag == null) {
            return;
        }

        if (diag.getDescription() != null && diag.getDescription().getDefaultConcern() != null) {
            ConcernService.setCurrentConcern(diag, diag.getDescription().getDefaultConcern());
        } else {
            ConcernService.setCurrentConcern(diag, null);
        }

        NotificationUtil.sendNotification(diag, org.eclipse.sirius.common.tools.api.listener.Notification.Kind.START, org.eclipse.sirius.common.tools.api.listener.Notification.VISIBILITY_UPDATE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canUndo() {
        /* we should not be able to undo this */
        return false;
    }
}
