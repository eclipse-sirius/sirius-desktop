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
package org.eclipse.sirius.diagram.tools.internal.palette;

import java.util.Collection;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.tool.ToolFilterDescriptionListener;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.tools.api.graphical.edit.palette.PaletteManager;
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
     * @param filters
     *            the filters
     */
    public void addListenersForFilters(Collection<ToolFilterDescription> filters) {
        for (final ToolFilterDescription filter : filters) {
            ToolFilterDescriptionListener listener = new ToolFilterDescriptionListener(filter, (DDiagram) diagram.getElement());
            listener.setUpdateRunnable(this);
            domain.addResourceSetListener(listener);
            listeners.add(listener);
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
