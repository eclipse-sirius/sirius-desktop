/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES and others
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
package org.eclipse.sirius.tree.business.api.command;

import java.text.MessageFormat;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.api.interaction.DTreeItemUserInteraction;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;
import org.eclipse.sirius.tree.tools.internal.Messages;

/**
 * EMF Command to expand/collapse a {@link DTreeItem}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DTreeItemExpansionChangeCommand extends RecordingCommand {

    private GlobalContext globalContext;

    private DTreeItem dTreeItem;

    private boolean expand;

    private IProgressMonitor monitor;

    /**
     * Default constructor.
     * 
     * @param globalContext
     *            the {@link GlobalContext} to synchronize the model.
     * @param domain
     *            the {@link TransactionalEditingDomain} on which execute this command
     * @param dTreeItem
     *            the {@link DTreeItem} to expand/collapse
     * @param expand
     *            true to expand, false to collapse
     */
    public DTreeItemExpansionChangeCommand(GlobalContext globalContext, TransactionalEditingDomain domain, DTreeItem dTreeItem, boolean expand) {
        super(domain, MessageFormat.format(expand ? Messages.DTreeItemExpansionChangeCommand_expandItem : Messages.DTreeItemExpansionChangeCommand_collapseItem, dTreeItem.getName()));
        this.globalContext = globalContext;
        this.dTreeItem = dTreeItem;
        this.expand = expand;
        this.monitor = new NullProgressMonitor();
    }

    /**
     * Default constructor.
     * 
     * @param globalContext
     *            the {@link GlobalContext} to synchronize the model.
     * @param domain
     *            the {@link TransactionalEditingDomain} on which execute this command
     * @param dTreeItem
     *            the {@link DTreeItem} to expand/collapse
     * @param expand
     *            true to expand, false to collapse
     * @param monitor
     *            a {@link IProgressMonitor} to give progression
     */
    public DTreeItemExpansionChangeCommand(GlobalContext globalContext, TransactionalEditingDomain domain, DTreeItem dTreeItem, IProgressMonitor monitor, boolean expand) {
        super(domain, MessageFormat.format(expand ? Messages.DTreeItemExpansionChangeCommand_expandItem : Messages.DTreeItemExpansionChangeCommand_collapseItem, dTreeItem.getName()));
        this.globalContext = globalContext;
        this.dTreeItem = dTreeItem;
        this.expand = expand;
        this.monitor = monitor;
    }

    @Override
    protected void doExecute() {
        if (expand) {
            new DTreeItemUserInteraction(dTreeItem, globalContext).expand(monitor);
        } else {
            new DTreeItemUserInteraction(dTreeItem, globalContext).collapse(monitor);
        }
    }
}
