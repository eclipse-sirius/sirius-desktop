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
package org.eclipse.sirius.common.ui.tools.api.util;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;

/**
 * This class is a wrapper to {@link IObjectActionDelegate} which extends
 * {@link Action}. This is useful when you need to add in code a popup menu
 * action already defined as an {@link IObjectActionDelegate}
 * 
 * @author Mariot Chauvin (mchauvin)
 */
public class IObjectActionDelegateWrapper extends Action {

    /** The wrapped delegated action. */
    protected IObjectActionDelegate action;

    /** the current selection. */
    protected ISelection currentSelection;

    /**
     * Constructor.
     * 
     * @param action
     *            the delegated action to wrap
     * 
     * @param text
     *            the string used as the text for the action, or
     *            <code>null</code> if there is no text
     */
    public IObjectActionDelegateWrapper(final IObjectActionDelegate action, final String text) {
        super(text);
        this.action = action;
    }

    /**
     * Constructor with icon.
     * 
     * @param action
     *            the delegated action to wrap
     * 
     * @param text
     *            the string used as the text for the action, or
     *            <code>null</code> if there is no text
     * @param desc
     *            the image descriptor
     */
    public IObjectActionDelegateWrapper(final IObjectActionDelegate action, final String text, final ImageDescriptor desc) {
        super(text, desc);
        this.action = action;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        action.run(this);
    }

    /**
     * Get the wrapped action.
     * 
     * @return the wrapped action.
     */
    public IObjectActionDelegate getWrappedAction() {
        return this.action;
    }

    /**
     * notify a selection change.
     * 
     * @param selection
     *            the new selection
     */
    public void selectionChanged(final ISelection selection) {
        this.currentSelection = selection;
    }
}
