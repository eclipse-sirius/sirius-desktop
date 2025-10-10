/*******************************************************************************
 * Copyright (c) 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.interpreter.internal.view.listener;

import java.util.Iterator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.ui.interpreter.internal.view.InterpreterView;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

/**
 * This will be installed on the workbench page on which this view is displayed in order to listen to selection events
 * corresponding to Notifiers.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class NotifierSelectionListener implements ISelectionListener {

    /**
     * This interpreter view.
     */
    private final InterpreterView view;

    /**
     * Constructs this listener.
     * 
     * @param view
     *            The view on which the selection should not be triggered.
     * @param realTimeThread
     */
    public NotifierSelectionListener(InterpreterView view) {
        this.view = view;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @SuppressWarnings("unchecked")
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        if (!selection.isEmpty() && selection instanceof IStructuredSelection && part != this.view) {
            boolean cleared = false;
            final Iterator<Object> selectionIterator = ((IStructuredSelection) selection).iterator();
            while (selectionIterator.hasNext()) {
                final Object next = selectionIterator.next();
                final Notifier nextNotifier;
                if (next instanceof Notifier) {
                    nextNotifier = (Notifier) next;
                } else {
                    final Notifier adaptedNotifier = adaptAs(next, Notifier.class);
                    if (adaptedNotifier != null) {
                        nextNotifier = adaptedNotifier;
                    } else {
                        nextNotifier = adaptAs(next, EObject.class);
                    }
                }
                if (nextNotifier != null) {
                    // At least one of the selected objects is a Notifier, clear current selection
                    if (!cleared) {
                        view.clearSelection();
                        cleared = true;
                    }
                    view.addToSelection(nextNotifier);
                }
            }
            // If the selection changed somehow, relaunch the real-time evaluation
            if (cleared && view.getRealTimeThread() != null) {
                view.getRealTimeThread().setDirty();
            }
        }
    }

    /**
     * Adapts the given object to the given class.
     * 
     * @param object
     *            The object to adapt
     * @param clazz
     *            The class to which the object should be adapted
     * @param <T>
     *            The type of the class to obtain
     * @return The adapted object
     */
    @SuppressWarnings("unchecked")
    private <T> T adaptAs(Object object, Class<T> clazz) {
        if (object instanceof IAdaptable) {
            return (T) ((IAdaptable) object).getAdapter(clazz);
        }
        return (T) Platform.getAdapterManager().getAdapter(object, clazz);
    }

}
