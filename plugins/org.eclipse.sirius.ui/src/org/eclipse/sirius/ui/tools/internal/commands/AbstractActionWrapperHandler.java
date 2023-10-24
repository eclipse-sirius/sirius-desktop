/*******************************************************************************
 * Copyright (c) 2013, 2023 THALES GLOBAL SERVICES.
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

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        action.selectionChanged(null, HandlerUtil.getCurrentSelection(event));
        action.run(null);
        action.selectionChanged(null, null);
        return null;
    }

}
