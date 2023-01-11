/*******************************************************************************
 * Copyright (c) 2015, 2023 Obeo.
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
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.api.command.DTreeItemExpansionChangeCommand;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;
import org.eclipse.sirius.tree.business.internal.helper.RefreshTreeElementTask;
import org.eclipse.sirius.tree.ui.provider.Messages;

/**
 * Refresh tree elements when a SWT TreeItem is expanded.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 */
public class ExpandDTreeItemRunnableWithProgress extends AbstractDTreeItemRunnableWithProgress {

    private final Map<DTreeItem, Integer> dTreeItemsWithDepth;

    /**
     * Constructor to expand one tree item.
     * 
     * @param session
     *            the session
     * @param dTreeItem
     *            the tree item
     * @param expand
     *            true to expand, false to collapse
     */
    public ExpandDTreeItemRunnableWithProgress(Session session, DTreeItem dTreeItem) {
        super(session, dTreeItem);
        this.dTreeItemsWithDepth = Map.of(dTreeItem, 0);
    }

    /**
     * Constructor to expand several items in the same time.
     * 
     * @param session
     *            the session
     * @param dTreeItemsWithDepth
     * @param all
     */
    public ExpandDTreeItemRunnableWithProgress(Session session, Map<DTreeItem, Integer> dTreeItemsWithDepth, boolean all) {
        super(session, true, dTreeItemsWithDepth == null ? 0 : dTreeItemsWithDepth.size());
        this.dTreeItemsWithDepth = dTreeItemsWithDepth;
    }

    /**
     * Launch tree item expanding.
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
            String commandLabel = getCommandLabel(dTreeItemsWithDepth.keySet().iterator().next(), isCollapseOrExpandAll());
            monitor.beginTask(commandLabel, 1);
            CompoundCommand expandDTreeItemsCmd = new CompoundCommand(commandLabel);
            dTreeItemsWithDepth.forEach((dTreeItem, depth) -> {
                expandDTreeItemsCmd.append(
                        ExpandDTreeItemRunnableWithProgress.getExpandCommandForDTreeItem(dTreeItem, getDomain(), getGlobalContext(), getSession().getSiriusPreferences().isAutoRefresh(), depth));
            });
            getCommandStack().execute(expandDTreeItemsCmd);
            if (getCommandStack().getMostRecentCommand() != expandDTreeItemsCmd) {
                // In case of monitor cancel the last executed command in
                // the commandStack will not be expandDTreeItemCmd then we
                // throw a InterruptedException to cancel the swt TreeItem
                // expand
                throw new InterruptedException();
            }
        } finally {
            monitor.done();
        }
    }

    /**
     * Get the expand command for this tree item and add the depth as an adapter.
     * 
     * @param dTreeItem
     *            The tree item to expand
     * @param domain
     *            The domain to use for the command
     * @param globalContext
     *            The global context to use for the command
     * @param isAutoRefresh
     *            true if the auto refresh mode is activate
     * @param currentDepth
     *            the current depth of the tree item
     * @return The command to expand the tree item
     */
    public static CompoundCommand getExpandCommandForDTreeItem(DTreeItem dTreeItem, TransactionalEditingDomain domain, GlobalContext globalContext, boolean isAutoRefresh, int currentDepth) {
        CompoundCommand expandDTreeItemCmd = new CompoundCommand(MessageFormat.format(Messages.ExpandDTreeItemRunnableWithProgress_expandTreeItem, dTreeItem.getName()));
        // Add the depth data as an Adapter. This adapter will be removed by the pre-commit listener.
        ExpandAllDepthAdapter.createAdapterOnDTreeItem(dTreeItem, currentDepth + 1);
        expandDTreeItemCmd.append(new DTreeItemExpansionChangeCommand(globalContext, domain, dTreeItem, new NullProgressMonitor(), true));
        if (!isAutoRefresh) {
            SiriusCommand result = new SiriusCommand(domain);
            result.getTasks().add(new RefreshTreeElementTask(dTreeItem));
            expandDTreeItemCmd.append(result);
        }
        return expandDTreeItemCmd;
    }

    /**
     * Get the command label.
     * 
     * @param dTreeItem
     *            DTreeItem used to build the command label.
     * @param isCollapseOrExpandAll
     *            true if the command concerns a collapse/expand all, false otherwise.
     * @return the command label
     */
    public static String getCommandLabel(DTreeItem dTreeItem, boolean isCollapseOrExpandAll) {
        return getCommandLabel(dTreeItem, Messages.ExpandDTreeItemRunnableWithProgress_expandTreeItem, Messages.ExpandDTreeItemRunnableWithProgress_expandAllTreeItems, isCollapseOrExpandAll);
    }

}
