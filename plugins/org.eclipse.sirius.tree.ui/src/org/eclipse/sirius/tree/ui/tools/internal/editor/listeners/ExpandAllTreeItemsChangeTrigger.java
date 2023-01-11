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

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.ModelChangeTrigger;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.TreePackage;
import org.eclipse.sirius.tree.business.internal.dialect.common.tree.TreeRefreshContext;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;

/**
 * This ModelChangeTrigger is used to expand children discovered during the expand of a parent. It is added during an
 * "expand all" action.
 * 
 * @author Laurent Redor
 */
public class ExpandAllTreeItemsChangeTrigger implements ModelChangeTrigger {

    /**
     * Keep only {@link Notification}s considering a status change of expand feature of a DTreeItem.
     */
    public static final NotificationFilter IS_IMPACTING = new NotificationFilter.Custom() {
        @Override
        public boolean matches(Notification notification) {
            return notification.getEventType() == Notification.SET && TreePackage.eINSTANCE.getDTreeItem_Expanded().equals(notification.getFeature());
        }
    };

    private Session session;

    private int expandDepthLimit;

    /**
     * Default constructor.
     * 
     * @param session
     *            The session to use to build the command
     * @param expandDepthLimit
     *            Define the depth to which tree items should be expanded.
     */
    public ExpandAllTreeItemsChangeTrigger(Session session, int expandDepthLimit) {
        super();
        this.session = session;
        this.expandDepthLimit = expandDepthLimit;
    }

    @Override
    public Option<Command> localChangesAboutToCommit(Collection<Notification> notifications) {
        Map<DTreeItem, Integer> dTreeItemsExpandedWithdDepth = getDTreeItemsExpandedWithDepth(notifications);
        if (dTreeItemsExpandedWithdDepth.size() > 0) {
            TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
            GlobalContext globalContext = new TreeRefreshContext(session.getModelAccessor(), session.getInterpreter(), session.getSemanticResources(), session.getTransactionalEditingDomain());
            CompoundCommand expandDTreeItemsCmd = new CompoundCommand(ExpandDTreeItemRunnableWithProgress.getCommandLabel(dTreeItemsExpandedWithdDepth.keySet().iterator().next(), true));
            boolean isAutoRefresh = session.getSiriusPreferences().isAutoRefresh();
            dTreeItemsExpandedWithdDepth.forEach((dTreeItemExpanded, depth) -> {
                completeCommandWithChildren(dTreeItemExpanded, expandDTreeItemsCmd, domain, globalContext, isAutoRefresh, depth);
            });
            return Options.newSome(expandDTreeItemsCmd);
        }
        return Options.newNone();
    }

    /**
     * Complete the compound command with the collapsed children. If a children is already expanded, this method goes
     * through sub-children until depth limit is reached.
     * 
     * @param parentTreeItem
     *            The parent tree item
     * @param expandDTreeItemsCmd
     *            The compound command to complete
     * @param domain
     *            The {@link TransactionalEditingDomain} to construct the command
     * @param globalContext
     *            The {@link GlobalContext} to construct the command
     * @param isAutoRefresh
     *            true is the autoRefresh mode is enabled, false otherwise.
     * @param currentDepth
     *            The depth of the parent tree item
     */
    private void completeCommandWithChildren(DTreeItem parentTreeItem, CompoundCommand expandDTreeItemsCmd, TransactionalEditingDomain domain, GlobalContext globalContext, boolean isAutoRefresh,
            int currentDepth) {
        if (currentDepth < this.expandDepthLimit) {
            for (Iterator<DTreeItem> iterator = parentTreeItem.getOwnedTreeItems().iterator(); iterator.hasNext(); /* */) {
                DTreeItem dTreeItem = iterator.next();
                if (dTreeItem.isExpanded()) {
                    completeCommandWithChildren(dTreeItem, expandDTreeItemsCmd, domain, globalContext, isAutoRefresh, currentDepth + 1);
                } else {
                    expandDTreeItemsCmd.append(ExpandDTreeItemRunnableWithProgress.getExpandCommandForDTreeItem(dTreeItem, domain, globalContext, isAutoRefresh, currentDepth));
                }
            }
        }
    }

    /**
     * Get the {@link DTreeItem} from the {@link Notification}s and its associated depth. The depth is stored in an
     * adapter added before when calling the expand action.
     * 
     * @param notifications
     *            List of notifications concerned by this pre-commit listener.
     * @return A list of DTreeItem with their associated depths.
     */
    protected Map<DTreeItem, Integer> getDTreeItemsExpandedWithDepth(Collection<Notification> notifications) {
        Map<DTreeItem, Integer> result = new HashMap<>();
        for (Notification notification : notifications) {
            if (notification.getNotifier() instanceof DTreeItem) {
                DTreeItem dTreeItem = (DTreeItem) notification.getNotifier();
                result.put(dTreeItem, ExpandAllDepthAdapter.getDepth(dTreeItem));
            }
        }
        return result;
    }

    @Override
    public int priority() {
        return 0;
    }

}
