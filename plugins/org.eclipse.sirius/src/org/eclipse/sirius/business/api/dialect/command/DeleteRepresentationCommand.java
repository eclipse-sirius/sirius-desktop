/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.Messages;

/**
 * Specific command to delete the given representations.
 * 
 * @author mporhel
 */
public class DeleteRepresentationCommand extends RecordingCommand {

    private Set<DRepresentationDescriptor> repDescriptors;

    private Session session;

    /**
     * Specific command to delete the given representations.
     * 
     * @param session
     *            the session on which to delete the {@link DRepresentation}s.
     *            WARNING : can only delete only {@link DRepresentation}s owned
     *            by this {@link Session} because we have one
     *            TransactionalEditingDomain per {@link Session}.
     * @param repDescriptors
     *            {@link Set} of {@link DRepresentationDescriptor}s
     *            corresponding to {@link DRepresentation}s to delete.
     */
    public DeleteRepresentationCommand(Session session, Set<DRepresentationDescriptor> repDescriptors) {
        super(session.getTransactionalEditingDomain(), Messages.DeleteRepresentationCommand_label);
        this.session = session;
        this.repDescriptors = repDescriptors;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        for (DRepresentationDescriptor repDescriptor : repDescriptors) {
            DialectManager.INSTANCE.deleteRepresentation(repDescriptor, session);
        }
    }

}
