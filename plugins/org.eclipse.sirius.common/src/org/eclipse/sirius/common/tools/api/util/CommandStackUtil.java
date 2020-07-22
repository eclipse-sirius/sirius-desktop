/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.common.tools.api.util;

import java.util.ArrayList;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.AbstractTransactionalCommandStack;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.emf.workspace.ResourceUndoContext;

/**
 * This class should contains useful static functions related to {@link CommandStack}. It could be added in a new
 * plug-in org.eclipse.sirius.ext.emf.workspace to be more clean.
 * 
 * @author Laurent Redor
 */
public final class CommandStackUtil {

    /**
     * Private constructor to avoid instantiation.
     */
    private CommandStackUtil() {
    }

    /**
     * By default the {@link CommandStack.flush()} "disposes all the commands in the stack". Concretely, it removes the
     * default context from the list of contexts of all operations. And operations without context, after this, are
     * removed from history. In several cases, it is not enough. The {@link ResourceUndoContext} must also be removed.
     * This is what this method done to really removed operations from IOperationHistory.
     * 
     * @param commandStack
     *            The command stack to flush
     */
    public static void flushOperations(CommandStack commandStack) {
        // Remove ResourceUndoContext, this must be done before commandStack.flush(), to have the Undo/RedoActionHandler
        // update the actions
        if (commandStack instanceof AbstractTransactionalCommandStack && commandStack instanceof IWorkspaceCommandStack) {
            TransactionalEditingDomain ted = ((AbstractTransactionalCommandStack) commandStack).getDomain();
            for (Resource resource : new ArrayList<Resource>(ted.getResourceSet().getResources())) {
                ((IWorkspaceCommandStack) commandStack).getOperationHistory().dispose(new ResourceUndoContext(ted, resource), true, true, true);
            }
        }
        // Remove CommandStack default UndoContext
        commandStack.flush();
    }

}
