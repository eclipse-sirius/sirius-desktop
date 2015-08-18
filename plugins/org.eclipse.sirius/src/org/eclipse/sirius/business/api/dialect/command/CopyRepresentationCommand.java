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
package org.eclipse.sirius.business.api.dialect.command;

import java.util.Collection;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * Specific command to copy requested representations.
 * 
 * @author mporhel
 * 
 */
public class CopyRepresentationCommand extends RecordingCommand {

    private Collection<DRepresentation> representations;

    private Session session;

    private String newName;

    /**
     * Specific command to copy the given representations.
     * 
     * @param domain
     *            the current editing domain.
     * @param representations
     *            the representations to copy.
     * @param newName
     *            the name of new representations.
     * @param session
     *            the current session.
     */
    public CopyRepresentationCommand(TransactionalEditingDomain domain, Collection<DRepresentation> representations, String newName, Session session) {
        super(domain, "Copy representations");
        this.representations = representations;
        this.newName = newName;
        this.session = session;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (representations == null || session == null) {
            return;
        }

        for (final DRepresentation representation : representations) {
            DialectManager.INSTANCE.copyRepresentation(representation, getName(representation), session, null);
        }
    }

    private String getName(final DRepresentation representation) {
        if (representations.size() == 1) {
            return newName;
        } else {
            return newName + " " + representation.getName(); //$NON-NLS-1$
        }
    }
}
