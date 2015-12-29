/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.commands;

import java.util.Iterator;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.common.tools.api.listener.NotificationUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.business.api.helper.concern.ConcernService;
import org.eclipse.sirius.diagram.description.concern.ConcernDescription;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.description.filter.VariableFilter;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.filter.FilterTools;

/**
 * Specific command to set the current concern.
 *
 * @author mporhel
 */
public class SetCurrentConcernCommand extends RecordingCommand {

    private final ConcernDescription desc;

    private final DDiagram diagram;

    /**
     * Constructor.
     *
     * @param domain
     *            the editing domain
     * @param diagram
     *            the current diagram
     * @param desc
     *            the requested concern description
     */
    public SetCurrentConcernCommand(final TransactionalEditingDomain domain, DDiagram diagram, final ConcernDescription desc) {
        super(domain, Messages.SetCurrentConcernCommand_label);
        this.diagram = diagram;
        this.desc = desc;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canUndo() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (diagram == null || desc == null) {
            return;
        }

        NotificationUtil.sendNotification(diagram, org.eclipse.sirius.common.tools.api.listener.Notification.Kind.START, org.eclipse.sirius.common.tools.api.listener.Notification.VISIBILITY_UPDATE);

        final ConcernDescription previousConcern = diagram.getCurrentConcern();
        if (diagram.getFilterVariableHistory() == null) {
            diagram.setFilterVariableHistory(DiagramFactory.eINSTANCE.createFilterVariableHistory());
        }
        try {
            final Iterator<FilterDescription> itFilter = desc.getFilters().iterator();
            while (itFilter.hasNext()) {
                final FilterDescription fil = itFilter.next();
                if (fil instanceof CompositeFilterDescription) {
                    final Iterator<?> it2 = ((CompositeFilterDescription) fil).getFilters().iterator();
                    while (it2.hasNext()) {
                        final Object objFilter = it2.next();
                        /*
                         * If we have a variable filter we need to open the
                         * dialog and set the different variables..
                         */
                        if (objFilter instanceof VariableFilter && diagram instanceof DSemanticDiagram) {
                            final VariableFilter filter = (VariableFilter) objFilter;
                            FilterTools.askForFilterValues((DSemanticDiagram) diagram, filter);
                        }
                    }
                }
            }
            ConcernService.setCurrentConcern(diagram, desc);
        } catch (final InterruptedException e) {
            ConcernService.setCurrentConcern(diagram, previousConcern);
        }
    }
}
