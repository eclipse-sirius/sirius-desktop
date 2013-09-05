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
package org.eclipse.sirius.diagram.tools.internal.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;

import org.eclipse.sirius.common.tools.api.listener.NotificationUtil;
import org.eclipse.sirius.DDiagram;
import org.eclipse.sirius.DSemanticDiagram;
import org.eclipse.sirius.business.api.helper.concern.ConcernService;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.description.concern.ConcernDescription;
import org.eclipse.sirius.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.description.filter.Filter;
import org.eclipse.sirius.description.filter.FilterDescription;
import org.eclipse.sirius.description.filter.VariableFilter;
import org.eclipse.sirius.diagram.tools.internal.filter.FilterTools;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.command.SiriusCommand;

/**
 * Change filter activation.
 * 
 * @author mchauvin
 */
public class ChangeFilterActivation extends AbstractChangeActivation {

    private final FilterDescription filter;

    /**
     * Constructor.
     * 
     * @see AbstractChangeActivation constructor
     * 
     * @param part
     *            the diagram workbench part
     * @param diagram
     *            Diagram
     * @param filter
     *            the filter to change status
     * @param activate
     *            Activation of the layer
     */
    public ChangeFilterActivation(final IDiagramWorkbenchPart part, final DDiagram diagram, final FilterDescription filter, final boolean activate) {
        super(part, diagram, activate);
        this.filter = filter;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Runnable#run()
     */
    public void run() {

        final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(diagram);
        final DCommand command = new SiriusCommand(domain, null);
        command.getTasks().add(new AbstractCommandTask() {
            /**
             * {@inheritDoc}
             * 
             * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
             */
            public void execute() {
                NotificationUtil.sendNotification(diagram, org.eclipse.sirius.common.tools.api.listener.Notification.Kind.START, org.eclipse.sirius.common.tools.api.listener.Notification.VISIBILITY_UPDATE);

                if (activate) {
                    handleActivation(diagram, filter);
                } else {
                    diagram.getActivatedFilters().remove(filter);
                    ConcernService.resetCurrentConcern(diagram);
                }
            }

            /**
             * {@inheritDoc}
             * 
             * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
             */
            public String getLabel() {
                return "hide or show filter";
            }

        });

        // This DDiagramSynchronizer.refesh is useless
        // command.getTasks().add(new RefreshDElementTask((DDiagram)
        // designerElement));
        command.setLabel(activate ? "Activate " : "Deactivate " + filter.getName() + " filter");

        domain.getCommandStack().execute(command);
    }

    private static boolean handleVariableInit(final CompositeFilterDescription composite, final DSemanticDiagram vp) throws InterruptedException {
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

    private void handleActivation(final DDiagram diagram, final FilterDescription filterDescription) {
        ConcernDescription oldConcern = null;
        boolean containsVariableFilters = false;
        final List<FilterDescription> activatedFilters = diagram.getActivatedFilters();
        final List<FilterDescription> activatedFiltersCopy = new ArrayList<FilterDescription>(activatedFilters);
        if (diagram.getCurrentConcern() != null) {
            oldConcern = diagram.getCurrentConcern();
            ConcernService.resetCurrentConcern(diagram);
        }
        try {

            if (filterDescription instanceof CompositeFilterDescription && diagram instanceof DSemanticDiagram) {
                containsVariableFilters = ChangeFilterActivation.handleVariableInit((CompositeFilterDescription) filterDescription, (DSemanticDiagram) diagram);
            }
            // need to remove previous filters to handle creation of new
            // graphical elements on variable filters activation.
            if (containsVariableFilters && activatedFiltersCopy.size() > 0) {
                activatedFilters.clear();
            }
            activatedFilters.add(filterDescription);
            // reactivation of previous activated filters.
            if (containsVariableFilters && activatedFiltersCopy.size() > 0) {
                activatedFilters.addAll(activatedFiltersCopy);
            }
        } catch (final InterruptedException e) {
            ConcernService.setCurrentConcern(diagram, oldConcern);
        }
    }

}
