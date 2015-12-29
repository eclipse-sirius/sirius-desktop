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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.common.tools.api.listener.NotificationUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.business.api.helper.concern.ConcernService;
import org.eclipse.sirius.diagram.description.concern.ConcernDescription;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.diagram.description.filter.Filter;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.description.filter.VariableFilter;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.filter.FilterTools;

/**
 * Specific command to update activated filters.
 *
 * @author mporhel
 */
public final class ActivateFiltersCommand extends RecordingCommand {

    private final Collection<FilterDescription> newElements;

    private final DDiagram diagram;

    /**
     * Constructor.
     *
     * @param domain
     *            the editing domain.
     * @param diagram
     *            the current diagram.
     * @param newElements
     *            elements to activate
     */
    public ActivateFiltersCommand(TransactionalEditingDomain domain, DDiagram diagram, Collection<FilterDescription> newElements) {
        super(domain, Messages.ActivateFiltersCommand_label);
        this.newElements = newElements;
        this.diagram = diagram;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (diagram == null || newElements == null) {
            return;
        }

        initFilterVariablesHistory();

        doActivateFilters();

        if (newElements.size() > 0) {
            NotificationUtil.sendNotification(diagram, org.eclipse.sirius.common.tools.api.listener.Notification.Kind.START,
                    org.eclipse.sirius.common.tools.api.listener.Notification.VISIBILITY_UPDATE);
        }
    }

    private void doActivateFilters() {
        boolean containsVariableFilters = false;
        final List<FilterDescription> newActivatedFilters = new ArrayList<FilterDescription>();
        final List<FilterDescription> previousActivatedFilters = new ArrayList<FilterDescription>(diagram.getActivatedFilters());

        ConcernDescription oldConcern = getAndResetConcern();

        try {
            for (FilterDescription filterDesc : newElements) {
                if (filterDesc instanceof CompositeFilterDescription && diagram instanceof DSemanticDiagram) {
                    containsVariableFilters = containsVariableFilters | handleVariableInit((CompositeFilterDescription) filterDesc, (DSemanticDiagram) diagram);
                }
                /*
                 * In all the cases, if we've got a filter, then enable it..
                 */
                newActivatedFilters.add(filterDesc);
            }
            // need to remove previous filters to handle creation of
            // new graphical elements on variable filters activation.
            if (containsVariableFilters && previousActivatedFilters.size() > 0) {
                diagram.getActivatedFilters().clear();
            }
            diagram.getActivatedFilters().addAll(newActivatedFilters);
            // reactivation of previous activated filters.
            if (containsVariableFilters && previousActivatedFilters.size() > 0) {
                diagram.getActivatedFilters().addAll(previousActivatedFilters);
            }
        } catch (final InterruptedException e) {
            ConcernService.setCurrentConcern(diagram, oldConcern);
        }
    }

    private ConcernDescription getAndResetConcern() {
        ConcernDescription oldConcern = null;
        if (newElements.size() > 0 && diagram.getCurrentConcern() != null) {
            oldConcern = diagram.getCurrentConcern();
            ConcernService.resetCurrentConcern(diagram);
        }
        return oldConcern;
    }

    private void initFilterVariablesHistory() {
        if (diagram.getFilterVariableHistory() == null) {
            diagram.setFilterVariableHistory(DiagramFactory.eINSTANCE.createFilterVariableHistory());
        }
    }

    private boolean handleVariableInit(final CompositeFilterDescription composite, final DSemanticDiagram vp) throws InterruptedException {
        boolean containsVariableFilters = false;
        final Iterator<Filter> it = composite.getFilters().iterator();
        while (it.hasNext()) {
            final Filter objFilter = it.next();
            /*
             * If we have a variable filter we need to open the dialog and set
             * the different variables..
             */
            if (objFilter instanceof VariableFilter) {
                final VariableFilter filter = (VariableFilter) objFilter;
                FilterTools.askForFilterValues(vp, filter);
                containsVariableFilters = true;
            }
        }
        return containsVariableFilters;
    }
}
