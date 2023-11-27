/*******************************************************************************
 * Copyright (c) 2011, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.sirius.diagram.ui.tools.api.action.FindElementAction;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * A command to find an element.
 * 
 * @author mchauvin
 */
public class FindElementCommand extends AbstractHandler {

    private IObjectActionDelegate action;

    /**
     * Construct a new instance.
     */
    public FindElementCommand() {
        action = new FindElementAction();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        action.selectionChanged(null, HandlerUtil.getCurrentSelection(event));
        action.run(null);
        action.selectionChanged(null, null);
        return null;
    }

}
