/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.part;

import java.util.List;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.SelectionManager;

/**
 * Manages a viewer's selection model. Selection management includes representing a form of selection which is available
 * to clients of a viewer as an ISelection. It also includes managing the notion of focus, which is closely tied to the
 * current selection. The selection manager provides the mechanism for modifying the selection and any validation.
 * <P>
 * This subclass allows to disable the {@link #fireSelectionChanged()} method, useful to preserve the selection across
 * update operations (drag'n'drop of element, or edge reconnection, for example).
 * 
 * @author lredor
 */
public class SiriusSelectionManager extends SelectionManager {

    /**
     * New capability to enable or disable the fire notification. In other words, if this field is false, the method
     * {@link SelectionManager#fireSelectionChanged()} has no effect.
     */
    private boolean isFireEnabled = true;

    /**
     * Default Constructor.
     */
    protected SiriusSelectionManager() {
    }

    /**
     * Creates the default implementation for a selection manager.
     * 
     * @return the default selection manager
     * @since 3.2
     */
    public static SiriusSelectionManager createDefault() {
        return new SiriusSelectionManager();
    }

    /**
     * This method override the super method to add the capability to disable the fire notification.<BR/>
     * For internal use only.
     * 
     * @param viewer
     *            viewer
     * @param selection
     *            selection
     * @param notifier
     *            notifier
     * @override
     */
    public void internalInitialize(EditPartViewer viewer, List selection, Runnable notifier) {
        Runnable conditionRunnable = new Runnable() {

            @Override
            public void run() {
                if (isFireEnabled) {
                    notifier.run();
                }

            }
        };
        super.internalInitialize(viewer, selection, conditionRunnable);
    }

    /**
     * Enable the fire notification. This is the default state, it causes the viewer to fire selection changed
     * notification to all listeners when {@link #fireSelectionChanged()} is called.
     */
    public void enableFireNotification() {
        isFireEnabled = true;
    }

    /**
     * Disable the fire notification. This method must be used carefully. It allow the viewer to preserve the selection
     * across update operations (drag'n'drop of element, or edge reconnection, for example). The method
     * {@link #enableFireNotification()} must be called as soon as the update operation is finished.
     */
    public void disableFireNotification() {
        isFireEnabled = false;
    }
}

