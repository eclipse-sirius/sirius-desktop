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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.common.tools.api.listener.NotificationUtil;
import org.eclipse.sirius.business.api.helper.concern.ConcernService;
import org.eclipse.sirius.diagram.tools.internal.filter.FilterTools;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.description.concern.ConcernDescription;
import org.eclipse.sirius.viewpoint.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.viewpoint.description.filter.Filter;
import org.eclipse.sirius.viewpoint.description.filter.FilterDescription;
import org.eclipse.sirius.viewpoint.description.filter.VariableFilter;

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
        super(domain, "Activate filters");
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
            NotificationUtil.sendNotification(diagram, org.eclipse.sirius.common.tools.api.listener.Notification.Kind.START, org.eclipse.sirius.common.tools.api.listener.Notification.VISIBILITY_UPDATE);
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
            diagram.setFilterVariableHistory(ViewpointFactory.eINSTANCE.createFilterVariableHistory());
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
                final Map<String, EObject> variables = FilterTools.askForFilterValues(vp, filter);
                filter.setFilterContext(variables);
                containsVariableFilters = true;
            }
        }
        return containsVariableFilters;
    }
}
