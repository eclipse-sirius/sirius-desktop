/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        action.selectionChanged(null, HandlerUtil.getCurrentSelection(event));
        action.run(null);
        return null;
    }

}
