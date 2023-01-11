/*******************************************************************************
 * Copyright (c) 2023 Obeo.
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
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.api.command.DTreeItemExpansionChangeCommand;
import org.eclipse.sirius.tree.ui.provider.Messages;

/**
 * Refresh tree elements when a SWT TreeItem is collapsed.
 * 
 * @author Laurent Redor
 */
public class CollapseDTreeItemRunnableWithProgress extends AbstractDTreeItemRunnableWithProgress {

    private final List<DTreeItem> dTreeItems;

    /**
     * Default constructor.
     * 
     * @param session
     *            the session
     * @param dTreeItem
     *            the tree item
     */
    public CollapseDTreeItemRunnableWithProgress(Session session, DTreeItem dTreeItem) {
        super(session, dTreeItem);
        this.dTreeItems = List.of(dTreeItem);
    }

    /**
     * Constructor to collapse several items in the same time.
     * 
     * @param session
     * @param dTreeItemsToColllapse
     *            List of {@link DTreeItem}s to collapse. The last element corresponds to the root used to launch
     *            "collapse all" action.
     */
    public CollapseDTreeItemRunnableWithProgress(Session session, List<DTreeItem> dTreeItemsToColllapse) {
        super(session, true, dTreeItemsToColllapse == null ? 0 : dTreeItemsToColllapse.size());
        this.dTreeItems = dTreeItemsToColllapse;
    }

    /**
     * Launch tree item(s) collapsing.
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
            String commandLabel = AbstractDTreeItemRunnableWithProgress.getCommandLabel(dTreeItems.get(dTreeItems.size() - 1), Messages.CollapseDTreeItemRunnableWithProgress_collapseTreeItem,
                    Messages.CollapseDTreeItemRunnableWithProgress_collapseAllTreeItems, isCollapseOrExpandAll());
            monitor.beginTask(commandLabel, 1);
            CompoundCommand collapseDTreeItemsCmd = new CompoundCommand(commandLabel);
            for (DTreeItem dTreeItem : dTreeItems) {
                collapseDTreeItemsCmd.append(new DTreeItemExpansionChangeCommand(getGlobalContext(), getDomain(), dTreeItem, monitor, false));
            }
            getCommandStack().execute(collapseDTreeItemsCmd);
        } finally {
            monitor.done();
        }
    }
}
