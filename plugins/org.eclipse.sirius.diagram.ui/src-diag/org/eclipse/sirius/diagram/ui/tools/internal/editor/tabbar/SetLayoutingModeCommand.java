/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.provider.Messages;

/**
 * A command that changes the LayoutingMode of the given {@link DDiagram} and updates the image assocatied to the
 * Action.
 *
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 *
 */
public class SetLayoutingModeCommand extends AbstractTransactionalCommand {

    /**
     * The {@link DDiagram} on witch the layouting mode should be switched.
     */
    private DDiagram diagram;

    /**
     * Indicates whether the Layouting Mode should be enabled.
     */
    private boolean layoutingModeShouldBeEnabled;

    /**
     * Constructor.
     *
     * @param editingDomain
     *            the editing domain
     * @param diagram
     *            the {@link DDiagram} on witch the layouting mode should be switched
     * @param layoutingModeShouldBeEnabled
     *            indicates whether the layouting mode should be enabled or disabled
     */
    public SetLayoutingModeCommand(TransactionalEditingDomain editingDomain, DDiagram diagram, boolean layoutingModeShouldBeEnabled) {
        super(editingDomain, Messages.SetLayoutingModeCommandAndUpdateActionImage_activateLabel, null);
        this.diagram = diagram;
        this.layoutingModeShouldBeEnabled = layoutingModeShouldBeEnabled;
    }

    /**
     *
     * {@inheritDoc}
     *
     * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor,
     *      org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        CommandResult commandResult = CommandResult.newOKCommandResult();
        if (LayoutingModeSwitchingAction.diagramAllowsLayoutingMode(diagram)) {
            this.diagram.setIsInLayoutingMode(layoutingModeShouldBeEnabled);
        }
        return commandResult;
    }
}
