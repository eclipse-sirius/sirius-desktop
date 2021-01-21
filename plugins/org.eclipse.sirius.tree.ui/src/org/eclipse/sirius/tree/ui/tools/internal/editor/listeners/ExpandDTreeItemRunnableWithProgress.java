/*******************************************************************************
 * Copyright (c) 2015, 2021 Obeo.
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
package org.eclipse.sirius.tree.ui.tools.internal.editor.listeners;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.api.command.DTreeItemExpansionChangeCommand;
import org.eclipse.sirius.tree.business.internal.dialect.common.tree.TreeRefreshContext;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;
import org.eclipse.sirius.tree.business.internal.helper.RefreshTreeElementTask;
import org.eclipse.sirius.tree.ui.provider.Messages;

/**
 * Refresh tree elements when a SWT TreeItem is expanded.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 */
public class ExpandDTreeItemRunnableWithProgress implements IRunnableWithProgress {

    private final Session session;

    private final DTreeItem dTreeItem;

    private final boolean expand;

    /**
     * Default constructor.
     * 
     * @param session
     *            the session
     * @param dTreeItem
     *            the tree item
     * @param expand
     *            true to expand, false to collapse
     */
    public ExpandDTreeItemRunnableWithProgress(Session session, DTreeItem dTreeItem, boolean expand) {
        this.dTreeItem = dTreeItem;
        this.session = session;
        this.expand = expand;
    }

    /**
     * Launch tree item expanding/collapsing.
     * 
     * @param monitor
     *            the progress monitor
     * @throws InvocationTargetException
     *             Invocation Target Exception
     * @throws InterruptedException
     *             Interrupted Exception
     */
    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        try {
            TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
            CommandStack commandStack = domain.getCommandStack();
            GlobalContext globalContext = new TreeRefreshContext(session.getModelAccessor(), session.getInterpreter(), session.getSemanticResources(), session.getTransactionalEditingDomain());
            if (expand) {
                monitor.beginTask(Messages.ExpandDTreeItemRunnableWithProgress_treeItemExpanding, 1);
                CompoundCommand expandDTreeItemCmd = new CompoundCommand(MessageFormat.format(Messages.ExpandDTreeItemRunnableWithProgress_expandTreeItem, dTreeItem.getName()));
                expandDTreeItemCmd.append(new DTreeItemExpansionChangeCommand(globalContext, domain, dTreeItem, monitor, true));
                if (!session.getSiriusPreferences().isAutoRefresh()) {
                    SiriusCommand result = new SiriusCommand(domain);
                    result.getTasks().add(new RefreshTreeElementTask(dTreeItem));
                    expandDTreeItemCmd.append(result);
                }
                commandStack.execute(expandDTreeItemCmd);
                if (commandStack.getMostRecentCommand() != expandDTreeItemCmd) {
                    // In case of monitor cancel the last executed command in
                    // the commandStack will not be expandDTreeItemCmd then we
                    // throw a InterruptedException to cancel the swt TreeItem
                    // expand
                    throw new InterruptedException();
                }
            } else {
                monitor.beginTask(Messages.ExpandDTreeItemRunnableWithProgress_treeItemCollapsing, 1);
                Command collapseDTreeItemCmd = new DTreeItemExpansionChangeCommand(globalContext, domain, dTreeItem, monitor, false);
                commandStack.execute(collapseDTreeItemCmd);
            }
        } finally {
            monitor.done();
        }
    }

}
