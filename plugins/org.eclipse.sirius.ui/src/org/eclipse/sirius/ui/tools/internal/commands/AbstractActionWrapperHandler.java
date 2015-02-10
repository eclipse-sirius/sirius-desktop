/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * A command handler wrapper for existing action.
 * 
 * @author mchauvin
 */
public abstract class AbstractActionWrapperHandler extends AbstractHandler {

    private IObjectActionDelegate action;

    /**
     * Set the action to wrap.
     * 
     * @param action
     *            the action to wrap
     */
    public AbstractActionWrapperHandler(final IObjectActionDelegate action) {
        this.action = action;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        action.selectionChanged(null, HandlerUtil.getCurrentSelection(event));
        action.run(null);
        return null;
    }

}
