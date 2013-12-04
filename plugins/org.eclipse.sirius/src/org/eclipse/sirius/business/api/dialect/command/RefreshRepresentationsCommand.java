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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import com.google.common.collect.Lists;

import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Refresh representations command. Launch the refresh for all kind of
 * representations and launch a compute visibility for the DDiagram.
 * 
 * @author lredor
 * @since 0.9.0
 */
public class RefreshRepresentationsCommand extends RecordingCommand {

    private Collection<DRepresentation> representations;

    private IProgressMonitor monitor;

    /**
     * Construct a new instance.
     * 
     * @param domain
     *            the editing domain.
     * @param representationsToRefresh
     *            the representations to refresh.
     * @deprecated use
     *             {@link RefreshRepresentationsCommand#RefreshRepresentationsCommand(TransactionalEditingDomain, IProgressMonitor, DRepresentation...)}
     *             instead instead
     */
    public RefreshRepresentationsCommand(TransactionalEditingDomain domain, DRepresentation... representationsToRefresh) {
        this(domain, null, representationsToRefresh);
    }

    /**
     * Construct a new instance.
     * 
     * @param domain
     *            the editing domain.
     * @param representationsToRefresh
     *            the representations to refresh.
     * @deprecated use
     *             {@link RefreshRepresentationsCommand#RefreshRepresentationsCommand(TransactionalEditingDomain, IProgressMonitor, Collection)}
     *             instead
     */
    public RefreshRepresentationsCommand(TransactionalEditingDomain domain, Collection<DRepresentation> representationsToRefresh) {
        this(domain, null, representationsToRefresh);
    }

    /**
     * Construct a new instance.
     * 
     * @param domain
     *            the editing domain.
     * @param monitor
     *            a progress monitor.
     * @param representationsToRefresh
     *            the representations to refresh.
     * 
     */
    public RefreshRepresentationsCommand(TransactionalEditingDomain domain, IProgressMonitor monitor, Collection<DRepresentation> representationsToRefresh) {
        super(domain, "Refresh representation");
        this.monitor = monitor;
        this.representations = representationsToRefresh;
    }

    /**
     * Construct a new instance.
     * 
     * @param domain
     *            the editing domain.
     * @param monitor
     *            a progress monitor.
     * @param representationsToRefresh
     *            the representations to refresh.
     * 
     */
    public RefreshRepresentationsCommand(TransactionalEditingDomain domain, IProgressMonitor monitor, DRepresentation... representationsToRefresh) {
        this(domain, monitor, Lists.newArrayList(representationsToRefresh));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (representations.size() == 0) {
            return;
        }

        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }

        for (DRepresentation representation : representations) {
            if (safeRefresh(representation)) {
                DialectManager.INSTANCE.refresh(representation, monitor);
            }
        }
    }

    private boolean safeRefresh(DRepresentation representation) {
        boolean safeRefresh = representation != null;
        if (representation instanceof DSemanticDecorator) {
            safeRefresh = ((DSemanticDecorator) representation).getTarget() != null;
        }
        return safeRefresh;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.common.command.AbstractCommand#canExecute()
     */
    @Override
    public boolean canExecute() {
        return representations.size() != 0;
    }

}
