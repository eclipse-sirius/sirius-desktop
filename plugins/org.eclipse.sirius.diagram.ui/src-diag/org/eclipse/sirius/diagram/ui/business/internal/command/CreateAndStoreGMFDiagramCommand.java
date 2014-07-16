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
package org.eclipse.sirius.diagram.ui.business.internal.command;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.internal.dialect.DiagramDialectServices;

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
        super(session.getTransactionalEditingDomain(), "Refresh diagram on opening");
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
