/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *    Felix Dorner <felix.dorner@gmail.com> - Bug 535648
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.refresh.listeners;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.ModelChangeTrigger;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.internal.refresh.SiriusDiagramSessionEventBroker;
import org.eclipse.sirius.diagram.ui.business.api.helper.graphicalfilters.CompositeFilterApplicationBuilder;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.LockStatus;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * A ModelChangeTrigger listener to refresh filter applications on each
 * {@link org.eclipse.sirius.diagram.DDiagramElement} of the current {@link DDiagram} .
 * 
 * @author mporhel
 */
public class FilterListener implements ModelChangeTrigger {

    /**
     * Priority of this {@link ModelChangeTrigger}.
     */
    public static final int COMPOSITE_FILTER_REFRESH_PRIORITY = SiriusDiagramSessionEventBroker.PRIORITY + 1;

    private DDiagram dDiagram;

    private TransactionalEditingDomain domain;

    /**
     * Default constructor.
     * 
     * @param dDiagram
     *            the {@link DDiagram} to update
     * @param domain
     *            the contextual {@link TransactionalEditingDomain}
     */
    public FilterListener(DDiagram dDiagram, TransactionalEditingDomain domain) {
        this.dDiagram = dDiagram;
        this.domain = domain;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int priority() {
        return COMPOSITE_FILTER_REFRESH_PRIORITY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Option<Command> localChangesAboutToCommit(Collection<Notification> notifications) {
        Command cmd = new FilteredElementsUpdateCommand(domain, dDiagram);
        return Options.newSome(cmd);
    }

    /**
     * Specific command to compute composite filter application.
     * 
     * @author mporhel
     */
    private static class FilteredElementsUpdateCommand extends RecordingCommand {

        private DDiagram diagram;

        public FilteredElementsUpdateCommand(TransactionalEditingDomain domain, DDiagram dDiagram) {
            super(domain);
            this.diagram = dDiagram;
        }

        @Override
        protected void doExecute() {
            IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(diagram);
            if (!(permissionAuthority != null && LockStatus.LOCKED_BY_OTHER.equals(permissionAuthority.getLockStatus(diagram)))) {
                CompositeFilterApplicationBuilder builder = new CompositeFilterApplicationBuilder(diagram);
                builder.computeCompositeFilterApplications();
            }
        }
    }
}
