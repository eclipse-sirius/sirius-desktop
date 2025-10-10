/*******************************************************************************
 * Copyright (c) 2010, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.acceleo.ui.interpreter.internal.view.actions;

import org.eclipse.acceleo.ui.interpreter.internal.IInterpreterConstants;
import org.eclipse.acceleo.ui.interpreter.internal.InterpreterImages;
import org.eclipse.acceleo.ui.interpreter.internal.InterpreterMessages;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.Viewer;

/**
 * This will serve as the base class for our "viewer clearing" actions.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public abstract class AbstractClearViewerAction extends Action {
    /** Name of this action. */
    private static final String NAME = InterpreterMessages.getString("interpreter.action.clear.name"); //$NON-NLS-1$

    /** The tooltip we'll show for this action. */
    private static final String TOOLTIP_TEXT = InterpreterMessages.getString("interpreter.action.clear.tooltip"); //$NON-NLS-1$

    /** The viewer that should be cleared through this action. */
    private final Viewer viewer;

    /**
     * Instantiates the "clear" action given the viewer it should operate on.
     * 
     * @param viewer
     *            The viewer that should be cleared through this action.
     */
    public AbstractClearViewerAction(final Viewer viewer) {
        super(NAME, IAction.AS_PUSH_BUTTON);
        setToolTipText(TOOLTIP_TEXT);
        setImageDescriptor(InterpreterImages.getImageDescriptor(IInterpreterConstants.CLEAR_ACTION_ICON));
        this.viewer = viewer;
    }

    /**
     * Returns the viewer on which this action operates.
     * 
     * @return The viewer on which this action operates.
     */
    public Viewer getViewer() {
        return viewer;
    }
}
