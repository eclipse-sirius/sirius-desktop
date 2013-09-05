/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.editor;

import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;

/**
 * This activationStrategy doesn't enable cell editor on simple mouse click.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class DTableColumnViewerEditorActivationStrategy extends ColumnViewerEditorActivationStrategy {

    /**
     * Default constructor.
     * 
     * @param viewer
     *            the viewer the editor support is attached to
     */
    public DTableColumnViewerEditorActivationStrategy(final ColumnViewer viewer) {
        super(viewer);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy#isEditorActivationEvent(org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent)
     */
    @Override
    protected boolean isEditorActivationEvent(final ColumnViewerEditorActivationEvent event) {
        boolean result = false;
        final boolean singleSelect = ((IStructuredSelection) getViewer().getSelection()).size() == 1;
        if (singleSelect) {
            // mouseActivation
            result = result || event.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION;
            // keyboardActivation
            result = result || event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED
                    && (Character.isLetterOrDigit(event.character) || DTableColumnViewerEditorActivationStrategy.isActivationKey(event));
            // otherActivations
            result = result || event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC || event.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL;
        }
        return result;
    }

    /**
     * Check if the event corresponds to an activation key (space, F2 and
     * Enter).
     * 
     * @param event
     *            The event to check
     * @return true is the event corresponds to an activation key
     */
    public static boolean isActivationKey(final ColumnViewerEditorActivationEvent event) {
        return event.keyCode == SWT.CR || event.keyCode == ' ' || event.keyCode == SWT.F2 || event.keyCode == SWT.KEYPAD_CR;
    }
}
