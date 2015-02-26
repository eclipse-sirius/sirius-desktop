/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.debug.statusline;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.CreateEditPoliciesOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Control;

/**
 * Provides Edit Policy on Diagram to display mouse location.
 * 
 * @author ymortier
 */
public class StatusLineEditPolicyProvider implements IEditPolicyProvider {

    /** the property change support. */
    private final List<IProviderChangeListener> listeners;

    /**
     * Create a new {@link StatusLineEditPolicyProvider}.
     */
    public StatusLineEditPolicyProvider() {
        this.listeners = new ArrayList<IProviderChangeListener>();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider#createEditPolicies(org.eclipse.gef.EditPart)
     */
    public void createEditPolicies(final EditPart editPart) {
        if (editPart instanceof DiagramEditPart) {
            final DiagramEditPart diagramEditPart = (DiagramEditPart) editPart;
            final RootEditPart root = diagramEditPart.getRoot();
            final EditPartViewer viewer = root.getViewer();

            if (viewer != null) {
                final MouseListeners mouseListeners = new MouseListeners(viewer);

                viewer.getControl().addDisposeListener(new DisposeListener() {

                    public void widgetDisposed(final DisposeEvent e) {
                        e.widget.removeDisposeListener(this);
                        if (e.widget instanceof Control) {
                            ((Control) e.widget).removeMouseMoveListener(mouseListeners);
                            ((Control) e.widget).removeMouseTrackListener(mouseListeners);
                        }
                    }
                });
                viewer.getControl().addMouseMoveListener(mouseListeners);
                viewer.getControl().addMouseTrackListener(mouseListeners);
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.core.service.IProvider#addProviderChangeListener(org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener)
     */
    public void addProviderChangeListener(final IProviderChangeListener listener) {
        this.listeners.add(listener);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.core.service.IProvider#provides(org.eclipse.gmf.runtime.common.core.service.IOperation)
     */
    public boolean provides(final IOperation operation) {
        boolean result = false;
        if (operation instanceof CreateEditPoliciesOperation) {
            result = true;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.core.service.IProvider#removeProviderChangeListener(org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener)
     */
    public void removeProviderChangeListener(final IProviderChangeListener listener) {
        this.listeners.remove(listener);
    }
}
