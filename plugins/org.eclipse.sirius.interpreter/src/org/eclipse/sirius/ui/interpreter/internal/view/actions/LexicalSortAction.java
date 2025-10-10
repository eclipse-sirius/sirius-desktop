/*******************************************************************************
 * Copyright (c) 2014, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.interpreter.internal.view.actions;

import org.eclipse.sirius.ui.interpreter.internal.IInterpreterConstants;
import org.eclipse.sirius.ui.interpreter.internal.InterpreterImages;
import org.eclipse.sirius.ui.interpreter.internal.InterpreterMessages;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.ViewerComparator;

/**
 * This will serve as the base class for our "viewer sorting" actions.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class LexicalSortAction extends Action {
    /** Name of this action. */
    private static final String NAME = InterpreterMessages.getString("interpreter.action.sort.name"); //$NON-NLS-1$

    /** The tooltip we'll show for this action. */
    private static final String TOOLTIP_TEXT = InterpreterMessages.getString("interpreter.action.sort.tooltip"); //$NON-NLS-1$

    /** The viewer that should be cleared through this action. */
    private final StructuredViewer viewer;

    /**
     * Instantiates the "sort" action given the viewer it should operate on.
     * 
     * @param viewer
     *            The viewer that should be sorted through this action.
     */
    public LexicalSortAction(final StructuredViewer viewer) {
        super(NAME, IAction.AS_CHECK_BOX);
        setToolTipText(TOOLTIP_TEXT);
        setImageDescriptor(InterpreterImages.getImageDescriptor(IInterpreterConstants.SORT_ACTION_ICON));
        this.viewer = viewer;
    }

    /**
     * Returns the viewer on which this action operates.
     * 
     * @return The viewer on which this action operates.
     */
    public StructuredViewer getViewer() {
        return viewer;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.IAction.run()
     */
    @Override
    public void run() {
        if (getViewer() != null) {
            if (isChecked()) {
                getViewer().setComparator(new ViewerComparator());
            } else {
                getViewer().setComparator(null);
            }
        }
    }
}
