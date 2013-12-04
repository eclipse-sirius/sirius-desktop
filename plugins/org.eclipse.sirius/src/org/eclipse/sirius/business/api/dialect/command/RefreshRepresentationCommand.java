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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * Create representation command.
 * 
 * @author mchauvin
 * @since 0.9.0
 * @deprecated since 3.1 (replace by {@link RefreshRepresentationsCommand}
 */
@Deprecated
public class RefreshRepresentationCommand extends RecordingCommand {

    private DRepresentation representation;

    private IProgressMonitor monitor;

    /**
     * Construct a new instance.
     * 
     * @param domain
     *            the editing domain.
     * @param representation
     *            the representation to refresh.
     * @param monitor
     *            a progress monitor.
     */
    public RefreshRepresentationCommand(TransactionalEditingDomain domain, DRepresentation representation, IProgressMonitor monitor) {
        super(domain, "Refresh representation");
        this.representation = representation;
        this.monitor = monitor;
    }

    /**
     * Construct a new instance.
     * 
     * @param domain
     *            the editing domain.
     * @param representation
     *            the representation to refresh.
     */
    public RefreshRepresentationCommand(TransactionalEditingDomain domain, DRepresentation representation) {
        this(domain, representation, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (representation == null) {
            return;
        }

        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }
        DialectManager.INSTANCE.refresh(representation, monitor);
    }

}
