/*******************************************************************************
 * Copyright (c) 2019 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.elk;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.elk.core.service.LayoutMapping;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * A command to execute one by one {@link IELKLayoutExtension}.
 * 
 * @author fbarbin
 *
 */
public class ELKLayoutExtensionCommand extends AbstractTransactionalCommand {

    private List<IELKLayoutExtension> extensions = new ArrayList<>();

    private LayoutMapping layoutMapping;

    private GmfLayoutCommand parentGMFLayoutCommand;

    /**
     * Default constructor.
     * 
     * @param extensions
     *            the list of {@link IELKLayoutExtension} to call during the execution of this command.
     * @param parentGMFLayoutCommand
     *            the {@link GmfLayoutCommand} on which this command will be chained.
     * @param layoutMapping
     *            the ELK {@link LayoutMapping}.
     */
    public ELKLayoutExtensionCommand(GmfLayoutCommand parentGMFLayoutCommand, List<IELKLayoutExtension> extensions, LayoutMapping layoutMapping) {
        super(parentGMFLayoutCommand.getEditingDomain(), parentGMFLayoutCommand.getLabel(), null);
        this.parentGMFLayoutCommand = parentGMFLayoutCommand;
        this.extensions.addAll(extensions);
        this.layoutMapping = layoutMapping;
    }


    @Override
    public void dispose() {
        super.dispose();
        this.extensions.clear();
        this.layoutMapping = null;
    }

    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        this.extensions.forEach(e -> e.afterGMFCommandApplied(this.parentGMFLayoutCommand, this.layoutMapping));
        return CommandResult.newOKCommandResult();
    }

}
