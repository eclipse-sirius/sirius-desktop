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
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import com.google.common.collect.Lists;

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

    private boolean fullRefresh;

    /**
     * Construct a new instance.
     * 
     * @param domain
     *            the editing domain.
     * @param fullRefresh
     *            true to do a full refresh, all view model elements are created
     *            and refreshed even ones no directly visible to end user
     *            through UI parts. Note that a full refresh could not finish in
     *            some case like recursive import mapping.
     * @param monitor
     *            a progress monitor.
     * @param representationsToRefresh
     *            the representations to refresh.
     */
    public RefreshRepresentationsCommand(TransactionalEditingDomain domain, boolean fullRefresh, IProgressMonitor monitor, Collection<DRepresentation> representationsToRefresh) {
        super(domain, "Refresh representation");
        this.fullRefresh = fullRefresh;
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
     */
    public RefreshRepresentationsCommand(TransactionalEditingDomain domain, IProgressMonitor monitor, Collection<DRepresentation> representationsToRefresh) {
        this(domain, false, monitor, representationsToRefresh);
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
     */
    public RefreshRepresentationsCommand(TransactionalEditingDomain domain, IProgressMonitor monitor, DRepresentation... representationsToRefresh) {
        this(domain, false, monitor, Lists.newArrayList(representationsToRefresh));
    }

    /**
     * Construct a new instance.
     * 
     * @param domain
     *            the editing domain.
     * @param fullRefresh
     *            true to do a full refresh, all view model elements are created
     *            and refreshed even ones no directly visible to end user
     *            through UI parts. Note that a full refresh could not finish in
     *            some case like recursive import mapping.
     * @param monitor
     *            a progress monitor.
     * @param representationsToRefresh
     *            the representations to refresh.
     */
    public RefreshRepresentationsCommand(TransactionalEditingDomain domain, boolean fullRefresh, IProgressMonitor monitor, DRepresentation... representationsToRefresh) {
        this(domain, fullRefresh, monitor, Lists.newArrayList(representationsToRefresh));
    }

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
                DialectManager.INSTANCE.refresh(representation, fullRefresh, monitor);
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

    @Override
    public boolean canExecute() {
        return representations.size() != 0;
    }

}
