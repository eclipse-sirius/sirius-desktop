/*******************************************************************************
 * Copyright (c) 2015, 2021 Obeo.
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
package org.eclipse.sirius.business.internal.dialect.command;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * This command performs a refresh only for impacted elements.
 * 
 * @author Florian Barbin
 */
public class RefreshImpactedElementsCommand extends RecordingCommand {

    private Collection<Notification> notifications;

    private Collection<DRepresentation> representations;

    private IProgressMonitor monitor;

    /**
     * Construct a new instance.
     * 
     * @param domain
     *            the editing domain.
     * @param monitor
     *            a progress monitor.
     * @param representationsToRefresh
     *            the representations to refresh.
     * @param notifications
     *            the notifications that concern this refresh. This list does not contain touch notifications.
     */
    public RefreshImpactedElementsCommand(TransactionalEditingDomain domain, IProgressMonitor monitor, Collection<DRepresentation> representationsToRefresh, Collection<Notification> notifications) {
        super(domain, Messages.RefreshImpactedElementsCommand_label);
        this.monitor = monitor;
        this.representations = representationsToRefresh;
        this.notifications = notifications;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }

        try {
            monitor.beginTask(Messages.RefreshImpactedElementsCommand_label, representations.size());
            for (DRepresentation representation : representations) {
                if (safeRefresh(representation)) {
                    DialectManager.INSTANCE.refreshImpactedElements(representation, notifications, new SubProgressMonitor(monitor, 1));
                } else {
                    monitor.worked(1);
                }
            }
        } finally {
            monitor.done();
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
