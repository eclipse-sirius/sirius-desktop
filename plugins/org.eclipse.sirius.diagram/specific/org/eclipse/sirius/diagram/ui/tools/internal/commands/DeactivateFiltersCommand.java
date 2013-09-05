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
package org.eclipse.sirius.diagram.ui.tools.internal.commands;

import java.util.Collection;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import org.eclipse.sirius.common.tools.api.listener.NotificationUtil;
import org.eclipse.sirius.DDiagram;
import org.eclipse.sirius.business.api.helper.concern.ConcernService;
import org.eclipse.sirius.description.filter.FilterDescription;

/**
 * Specific command to update activated filters.
 * 
 * @author mporhel
 */
public final class DeactivateFiltersCommand extends RecordingCommand {

    private final Collection<FilterDescription> oldElements;

    private final DDiagram diagram;

    /**
     * Constructor.
     * 
     * @param domain
     *            the editing domain.
     * @param diagram
     *            the current diagram.
     * @param oldElements
     *            elements to deactivate
     */
    public DeactivateFiltersCommand(TransactionalEditingDomain domain, DDiagram diagram, Collection<FilterDescription> oldElements) {
        super(domain, "Deactivate filters");
        this.oldElements = oldElements;
        this.diagram = diagram;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (diagram == null || oldElements == null) {
            return;
        }

        for (FilterDescription filter : oldElements) {
            diagram.getActivatedFilters().remove(filter);
        }

        if (oldElements.size() > 0) {
            ConcernService.resetCurrentConcern(diagram);
            NotificationUtil.sendNotification(diagram, org.eclipse.sirius.common.tools.api.listener.Notification.Kind.START, org.eclipse.sirius.common.tools.api.listener.Notification.VISIBILITY_UPDATE);
        }
    }
}
