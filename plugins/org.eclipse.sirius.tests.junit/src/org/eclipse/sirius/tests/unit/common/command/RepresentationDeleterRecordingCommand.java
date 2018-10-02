/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.common.command;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

/**
 * A RecordingCommand to delete a diagram representation.
 * 
 * @author smonnier
 */
public class RepresentationDeleterRecordingCommand extends RecordingCommand {

    private DRepresentationDescriptor repDescriptor;

    private Session session;

    /**
     * Default constructor.
     * 
     * @param domain
     *            my domain
     * @param repDescriptor
     *            my diagram
     * @param session
     *            my session
     */
    public RepresentationDeleterRecordingCommand(TransactionalEditingDomain domain, DRepresentationDescriptor repDescriptor, Session session) {
        super(domain);
        this.repDescriptor = repDescriptor;
        this.session = session;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        DialectManager.INSTANCE.deleteRepresentation(repDescriptor, session);
    }

}
