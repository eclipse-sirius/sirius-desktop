/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.dialect.command;

import java.util.Set;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.Messages;

/**
 * Specific command to delete the given representations.
 * 
 * @author mporhel
 */
public class DeleteRepresentationCommand extends RecordingCommand {

    private Set<DRepresentation> representations;

    private Session session;

    /**
     * Specific command to delete the given representations.
     * 
     * @param session
     *            the session on which to delete the {@link DRepresentation}s.
     *            WARNING : can only delete only {@link DRepresentation}s owned
     *            by this {@link Session} because we have one
     *            TransactionalEditingDomain per {@link Session}.
     * @param representations
     *            {@link Set} of {@link DRepresentation}s to delete.
     */
    public DeleteRepresentationCommand(Session session, Set<DRepresentation> representations) {
        super(session.getTransactionalEditingDomain(), Messages.DeleteRepresentationCommand_label);
        this.session = session;
        this.representations = representations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        for (DRepresentation dRepresentation : representations) {
            DialectManager.INSTANCE.deleteRepresentation(dRepresentation, session);
        }
    }

}
