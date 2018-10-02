/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.business.internal.command;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.internal.dialect.DiagramDialectServices;
import org.eclipse.sirius.diagram.ui.provider.Messages;

/**
 * Specific command to create and store gmf diagram.
 * 
 * @author mporhel
 */
public final class CreateAndStoreGMFDiagramCommand extends RecordingCommand {

    private final DSemanticDiagram diag;

    private final Session session;

    /**
     * Constructor.
     * 
     * @param session
     *            the current session.
     * @param diag
     *            the diagram to refresh.
     */
    public CreateAndStoreGMFDiagramCommand(Session session, DSemanticDiagram diag) {
        super(session.getTransactionalEditingDomain(), Messages.CreateAndStoreGMFDiagramCommand_label);
        this.session = session;
        this.diag = diag;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        DiagramDialectServices.createAndStoreGMFDiagram(session, diag);
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
