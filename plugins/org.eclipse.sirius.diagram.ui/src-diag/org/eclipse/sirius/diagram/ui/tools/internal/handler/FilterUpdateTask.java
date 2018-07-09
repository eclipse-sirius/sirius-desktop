/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.common.tools.api.listener.NotificationUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.helper.concern.ConcernService;
import org.eclipse.sirius.diagram.description.concern.ConcernDescription;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.diagram.description.filter.Filter;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.description.filter.VariableFilter;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.filter.FilterTools;

/**
 * A task adding or removing a VSM filter.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public final class FilterUpdateTask extends AbstractCommandTask {

    private DDiagram diagram;

    private FilterDescription filter;

    private boolean activate;

    /**
     * Init the task.
     * 
     * @param diagram
     *            the {@link DDiagram} that from which filters will be handled.
     * @param filter
     *            the filter to add or remove.
     * @param activate
     *            true if the filter should be added. False if it should be rmeoved.
     */
    public FilterUpdateTask(DDiagram diagram, FilterDescription filter, boolean activate) {
        this.diagram = diagram;
        this.filter = filter;
        this.activate = activate;
    }

    @Override
    public void execute() {
        NotificationUtil.sendNotification(diagram, org.eclipse.sirius.common.tools.api.listener.Notification.Kind.START, org.eclipse.sirius.common.tools.api.listener.Notification.VISIBILITY_UPDATE);

        if (activate) {
            handleActivation();
        } else {
            diagram.getActivatedFilters().remove(filter);
            ConcernService.resetCurrentConcern(diagram);
        }
    }

    @Override
    public String getLabel() {
        return Messages.ChangeFilterActivation_label;
    }

    private boolean handleVariableInit(final CompositeFilterDescription composite, final DSemanticDiagram vp) throws InterruptedException {
        boolean containsVariableFilters = false;
        final Iterator<Filter> it = composite.getFilters().iterator();
        while (it.hasNext()) {
            final Filter objFilter = it.next();
            /*
             * If we have a variable filter we need to open the dialog and set the different variables..
             */
            if (objFilter instanceof VariableFilter) {
                final VariableFilter theFilter = (VariableFilter) objFilter;
                FilterTools.askForFilterValues(vp, theFilter);
                containsVariableFilters = true;
            }
        }
        return containsVariableFilters;
    }

    private void handleActivation() {
        ConcernDescription oldConcern = null;
        boolean containsVariableFilters = false;
        final List<FilterDescription> activatedFilters = diagram.getActivatedFilters();
        final List<FilterDescription> activatedFiltersCopy = new ArrayList<FilterDescription>(activatedFilters);
        if (diagram.getCurrentConcern() != null) {
            oldConcern = diagram.getCurrentConcern();
            ConcernService.resetCurrentConcern(diagram);
        }
        try {

            if (filter instanceof CompositeFilterDescription && diagram instanceof DSemanticDiagram) {
                containsVariableFilters = handleVariableInit((CompositeFilterDescription) filter, (DSemanticDiagram) diagram);
            }
            // need to remove previous filters to handle creation of new
            // graphical elements on variable filters activation.
            if (containsVariableFilters && activatedFiltersCopy.size() > 0) {
                activatedFilters.clear();
            }
            activatedFilters.add(filter);
            // reactivation of previous activated filters.
            if (containsVariableFilters && activatedFiltersCopy.size() > 0) {
                activatedFilters.addAll(activatedFiltersCopy);
            }
        } catch (final InterruptedException e) {
            ConcernService.setCurrentConcern(diagram, oldConcern);
        }
    }

}
