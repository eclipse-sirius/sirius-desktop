/*******************************************************************************
 * Copyright (c) 2007, 2022 THALES GLOBAL SERVICES.
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
     * Value defined by the system property
     * "org.eclipse.sirius.ui.enableCreatedElementsConstraintInSelectElementsListener". If true, if the default list of
     * elements is not empty, the list of elements returned is filtered with the default selected elements. If false,
     * the value returned by the elementsToSelect expression is considered without the above constraint.
     */
    private boolean restoreBehaviorEnablingDirectEditOnAlphanumericKey = Boolean.valueOf(System.getProperty("org.eclipse.sirius.ui.restoreBehaviorEnablingDirectEditOnAlphanumericKey", "false")); //$NON-NLS-1$ //$NON-NLS-2$

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
            if (restoreBehaviorEnablingDirectEditOnAlphanumericKey) {
                result = result || event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED
                        && (Character.isLetterOrDigit(event.character) || DTableColumnViewerEditorActivationStrategy.isActivationKey(event));
            } else {
                result = result || event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED && DTableColumnViewerEditorActivationStrategy.isActivationKey(event);
            }
            // otherActivations
            result = result || event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC || event.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL;
        }
        return result;
    }

    /**
     * Check if the event corresponds to an activation key (space, F2 and Enter).
     * 
     * @param event
     *            The event to check
     * @return true is the event corresponds to an activation key
     */
    public static boolean isActivationKey(final ColumnViewerEditorActivationEvent event) {
        return event.keyCode == SWT.CR || event.keyCode == ' ' || event.keyCode == SWT.F2 || event.keyCode == SWT.KEYPAD_CR;
    }
}
