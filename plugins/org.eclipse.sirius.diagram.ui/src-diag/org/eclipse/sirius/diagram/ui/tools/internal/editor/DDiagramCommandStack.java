/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.editor;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIDebugOptions;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIPlugin;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIStatusCodes;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramEditDomain;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.WrappingCommandIgnoringAffectedFiles;
import org.eclipse.sirius.ecore.extender.business.api.permission.exception.LockedInstanceException;
import org.eclipse.sirius.tools.api.SiriusPlugin;

/**
 * The diagram command stack.
 * 
 * @author mchauvin
 */
@SuppressWarnings("restriction")
public class DDiagramCommandStack extends DiagramCommandStack {

    /**
     * Construct a new instance.
     * 
     * @param diagramEditDomain
     *            the diagram edit domain
     */
    public DDiagramCommandStack(IDiagramEditDomain diagramEditDomain) {
        super(diagramEditDomain);
    }

    @Override
    public void execute(final org.eclipse.gef.commands.Command command, final IProgressMonitor monitor) {
        if (command == null || !command.canExecute()) {
            return;
        }
        final ICommand original = DiagramCommandStack.getICommand(command);
        execute(getWrappingCommandIgnoringAffectedFiles(original), monitor);
    }

    /**
     * Executes the supplied command.
     * 
     * @param command
     *            the command to execute
     * @param progressMonitor
     *            the progress monitor
     * @param progressMonitor
     */
    @Override
    protected void execute(ICommand command, IProgressMonitor progressMonitor) {

        final IProgressMonitor monitor = progressMonitor != null ? progressMonitor : new NullProgressMonitor();

        try {
            command.addContext(getUndoContext());
            getOperationHistory().execute(command, monitor, null);

        } catch (ExecutionException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof Exception && (cause instanceof LockedInstanceException || cause.getClass().getSimpleName().equals("NoPermissionException"))) { //$NON-NLS-1$
                SiriusPlugin.getDefault().warning(cause.getMessage(), (Exception) cause);
            } else {
                defaultLog(e);
            }
        }
    }

    private void defaultLog(ExecutionException e) {
        Trace.catching(DiagramUIPlugin.getInstance(), DiagramUIDebugOptions.EXCEPTIONS_CATCHING, getClass(), "execute", e); //$NON-NLS-1$
        Log.error(DiagramUIPlugin.getInstance(), DiagramUIStatusCodes.COMMAND_FAILURE, "execute", e); //$NON-NLS-1$
    }

    private ICommand getWrappingCommandIgnoringAffectedFiles(final ICommand original) {
        return new WrappingCommandIgnoringAffectedFiles(original);
    }

}
