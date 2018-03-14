/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.management;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription;

/**
 * Manages the tool filter listeners for a DDiagram. If a notification of the diagram resource set changes the filter
 * condition, the tool associated will be updated.
 * 
 * @author mchauvin
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class ToolFilterDescriptionListenersManager {

    /**
     * The diagram from which we handle tool filters.
     */

    private TransactionalEditingDomain domain;

    private Collection<ToolFilterDescriptionListenerForUpdate> listeners;

    private DDiagram dDiagram;

    /**
     * Init the manager.
     * 
     * @param theDDiagram
     *            the diagram
     */
    public void init(DDiagram theDDiagram) {
        this.dDiagram = theDDiagram;
        /* remove previously registered listeners */
        removeListeners();

        this.domain = TransactionUtil.getEditingDomain(dDiagram);

        listeners = new LinkedHashSet<>();
    }

    /**
     * Add listeners for given filters.
     * 
     * @param interpreter
     *            the interpreter to use to evaluate the preconditions.
     * 
     * @param filters
     *            the filters
     */
    public void addListenersForFilters(IInterpreter interpreter, Collection<ToolFilterDescription> filters) {
        // If a DDiagram editor has a tool with a feature change
        // listener on a containment feature of DDiagram/DDiagramElement/Style
        // (or one of their super/sub types), and if the user deletes the
        // current diagram, the tool will be updated in post commit
        // (detachment/delete notification), and the null domain case has to be
        // handled as the gmf diagram is detached and the editor and its
        // ToolFilterDescriptionListenersManager are asynchronously closed/disposed.
        if (listeners != null && domain != null) {
            for (final ToolFilterDescription filter : filters) {
                ToolFilterDescriptionListenerForUpdate listener = getToolFilterDescriptionListener(interpreter, filter);
                domain.addResourceSetListener(listener);
                listeners.add(listener);
            }
        }
    }

    /**
     * Returns the instance of {@link ToolFilterDescriptionListenerForUpdate} to use to listen to tool filters
     * activation/deactivation.
     * 
     * @param interpreter
     *            the interpreter to use to evaluate the preconditions.
     * @param filter
     *            the filters
     * @return the instance of {@link ToolFilterDescriptionListenerForUpdate} to use.
     */
    protected ToolFilterDescriptionListenerForUpdate getToolFilterDescriptionListener(IInterpreter interpreter, ToolFilterDescription filter) {
        return new ToolFilterDescriptionListenerForUpdate(interpreter, filter, dDiagram);
    }

    /**
     * Dispose cleanly this listeners manager.
     */
    public void dispose() {
        removeListeners();
        domain = null;
    }

    private void removeListeners() {
        if (listeners != null & domain != null) {
            for (final ToolFilterDescriptionListenerForUpdate listener : listeners) {
                domain.removeResourceSetListener(listener);
            }
            listeners = null;
        }
    }
}
