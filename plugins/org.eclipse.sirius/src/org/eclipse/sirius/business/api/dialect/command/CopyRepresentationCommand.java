/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.dialect.command;

import java.util.Collection;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

/**
 * Specific command to copy requested representations.
 * 
 * @author mporhel
 * 
 */
public class CopyRepresentationCommand extends RecordingCommand {

    private Collection<DRepresentationDescriptor> representationDescriptors;

    private Session session;

    private String newName;

    /**
     * Specific command to copy the given representations.
     * 
     * @param domain
     *            the current editing domain.
     * @param representationDescriptors
     *            the {@link DRepresentationDescriptor} referencing the representations to copy.
     * @param newName
     *            the name of new representations.
     * @param session
     *            the current session.
     */
    public CopyRepresentationCommand(TransactionalEditingDomain domain, Collection<DRepresentationDescriptor> representationDescriptors, String newName, Session session) {
        super(domain, Messages.CopyRepresentationCommand_label);
        this.representationDescriptors = representationDescriptors;
        this.newName = newName;
        this.session = session;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (representationDescriptors == null || session == null) {
            return;
        }

        for (final DRepresentationDescriptor representationDescriptor : representationDescriptors) {
            DialectManager.INSTANCE.copyRepresentation(representationDescriptor, getName(representationDescriptor), session, null);
        }
    }

    private String getName(final DRepresentationDescriptor representationDescriptor) {
        if (representationDescriptors.size() == 1) {
            return newName;
        } else {
            return newName + " " + representationDescriptor.getName(); //$NON-NLS-1$
        }
    }
}
