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
package org.eclipse.sirius.diagram.ui.tools.internal.palette;

import java.util.Collection;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.tool.ToolFilterDescriptionListener;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.palette.PaletteManager;
import org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription;

import com.google.common.collect.Sets;

/**
 * Manager the listeners for tool filter.
 * 
 * @author mchauvin
 */
public class PaletteToolFilterDescriptionListenersManager implements Runnable {

    private PaletteManager paletteManager;

    private Diagram diagram;

    private TransactionalEditingDomain domain;

    private Collection<ToolFilterDescriptionListener> listeners;

    PaletteToolFilterDescriptionListenersManager(final PaletteManager paletteManager) {
        this.paletteManager = paletteManager;
    }

    /**
     * Init the manager.
     * 
     * @param gmfDiagram
     *            the GMF diagram
     */
    public void init(Diagram gmfDiagram) {
        /* remove previously registered listeners */
        removeListeners();

        this.diagram = gmfDiagram;
        this.domain = TransactionUtil.getEditingDomain(diagram);
        listeners = Sets.newLinkedHashSet();
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
        // If a DDiagram editor has a palette tool with a feature change
        // listener on a containment feature of DDiagram/DDiagramElement/Style
        // (or one of their super/sub types), and if the user deletes the
        // current diagram, the palette will be updated in post commit
        // (detachment/delete notification), and the null domain case has to be
        // handled as the gmf diagram is detached and the editor and its
        // PaletteToolFilterListenersManager are asynchronously closed/disposed.
        if (listeners != null && domain != null) {
            for (final ToolFilterDescription filter : filters) {
                ToolFilterDescriptionListener listener = new ToolFilterDescriptionListener(interpreter, filter, (DDiagram) diagram.getElement());
                listener.setUpdateRunnable(this);
                domain.addResourceSetListener(listener);
                listeners.add(listener);
            }
        }
    }

    /**
     * Dispose cleanly this listeners manager.
     */
    public void dispose() {
        removeListeners();
        diagram = null;
        domain = null;
    }

    private void removeListeners() {
        if (listeners != null & domain != null) {
            for (final ToolFilterDescriptionListener listener : listeners) {
                domain.removeResourceSetListener(listener);
            }
            listeners = null;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Runnable#run()
     */
    public void run() {
        paletteManager.update(diagram);
    }
}
