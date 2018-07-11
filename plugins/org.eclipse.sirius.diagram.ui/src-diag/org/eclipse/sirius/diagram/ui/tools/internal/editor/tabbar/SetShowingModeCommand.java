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

/**
 * Command switching the status of the feature {@link DDiagram#isIsInShowingMode()}.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class SetShowingModeCommand extends AbstractTransactionalCommand {

    private DDiagram diagram;

    private boolean activate;

    /**
     * Init the command.
     * 
     * @param domain
     *            the {@link TransactionalEditingDomain} to use.
     * @param diagram
     *            the {@link DDiagram} to use.
     * @param label
     *            the label to use.
     * @param activate
     *            true if the mode should be activated. False if it should be disabled.
     */
    public SetShowingModeCommand(TransactionalEditingDomain domain, DDiagram diagram, String label, boolean activate) {
        super(domain, label, null);
        this.diagram = diagram;
        this.activate = activate;
    }

    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        diagram.setIsInShowingMode(activate);
        return CommandResult.newOKCommandResult();
    }

}
