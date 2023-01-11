/*******************************************************************************
 * Copyright (c) 2015, 2023 Obeo. 
 * All rights reserved. This program
 * and the accompanying materials are made available under the terms of the
 * Eclipse Public License 2.0 which accompanies this distribution, and is
t https://www.eclipse.org/legal/epl-2.0/
t
t SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.tools.internal.editor.listeners;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.TreePackage;
import org.eclipse.sirius.tree.ui.provider.Messages;
import org.eclipse.sirius.tree.ui.provider.TreeUIPlugin;
import org.eclipse.sirius.ui.tools.internal.editor.ChangeExpandeStateRunnable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * Class responsible to DTree model update following a swt collapse/expand TreeItem if the {@link IPermissionAuthority}
 * allows it.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TreeItemExpansionManager implements Listener {

    private Tree tree;

    private Session session;

    private IPermissionAuthority permissionAuthority;

    /**
     * Default constructor.
     * 
     * @param tree
     *            the {@link Tree} for which listens expand and collapse events
     * @param session
     *            the {@link Session} owning the DTree to update on expand or collapse event
     */
    public TreeItemExpansionManager(Tree tree, Session session) {
        this.tree = tree;
        this.session = session;
        tree.addListener(SWT.Expand, this);
        tree.addListener(SWT.Collapse, this);
        tree.addListener(SWT.Dispose, this);
        permissionAuthority = session.getModelAccessor().getPermissionAuthority();
    }

    /**
     * Overridden to handle {@link SWT#Collapse} and {@link SWT#Expand} events.
     * 
     * {@inheritDoc}
     */
    @Override
    public void handleEvent(Event event) {
        switch (event.type) {
        case SWT.Collapse:
            handleTreeCollapse(event, Optional.empty(), session, permissionAuthority);
            break;
        case SWT.Expand:
            handleTreeExpand(event, Optional.empty(), session, permissionAuthority, false, 0);
            break;
        case SWT.Dispose:
            handleDispose();
            break;
        default:
            break;
        }

    }

    /**
     * Handle the collapse of a DTreeItem according to a graphical collapse of a TreeItem. If the collapse of a
     * DTreeItem is not allowed because of the current {@link IPermissionAuthority}, the graphical collapse is undone.
     * 
     * @param event
     *            the specified {@link Event}
     * @param currentTreeItem
     *            The optional tree item to expand. If not available, the event is used to get the tree item.
     * @param currentSession
     *            the current session
     * @param currentPermissionAuthority
     *            The {@link IPermissionAuthority} responsible to validate if the element is editable
     */
    public static void handleTreeCollapse(Event event, Optional<TreeItem> currentTreeItem, Session currentSession, IPermissionAuthority currentPermissionAuthority) {
        TreeItem treeItem = null;
        if (currentTreeItem.isPresent()) {
            treeItem = currentTreeItem.get();
        } else if (event.item instanceof TreeItem) {
            treeItem = (TreeItem) event.item;
        }
        if (treeItem != null) {
            handleTreeCollapse(event, List.of(treeItem), currentSession, currentPermissionAuthority);
        }
    }

    /**
     * Handle the collapse of a list of DTreeItem according to a graphical collapse of a TreeItem. If the collapse of a
     * DTreeItem is not allowed because of the current {@link IPermissionAuthority}, the graphical collapse is undone.
     * 
     * @param event
     *            the specified {@link Event}
     * @param treeItemsToCollapse
     *            list of the {@link TreeItem}s to collapse. The root of a "collapse all" is the last tree item of this
     *            list.
     * @param currentSession
     *            the current session
     * @param currentPermissionAuthority
     *            The {@link IPermissionAuthority} responsible to validate if the element is editable
     */
    public static void handleTreeCollapse(Event event, List<TreeItem> treeItemsToCollapse, Session currentSession, IPermissionAuthority currentPermissionAuthority) {
        if (treeItemsToCollapse.size() > 0) {
            // Get the last tree item of the list, ie the root collapse tree item, in case of "collapse all".
            TreeItem treeItem = treeItemsToCollapse.get(treeItemsToCollapse.size() - 1);
            Object data = treeItem.getData();
            boolean isAlreadyCollapsed = !treeItem.getExpanded();
            boolean isCollapseToRevert = false;
            if (data instanceof DTreeItem) {
                DTreeItem dTreeItem = (DTreeItem) data;
                // We only check the permission authority on the first tree item
                if (isForDTreeItemExpandable(treeItem, currentPermissionAuthority)) {
                    IWorkbench wb = PlatformUI.getWorkbench();
                    IProgressService ps = wb.getProgressService();
                    try {
                        if (treeItemsToCollapse.size() == 1 && dTreeItem.isExpanded()) {
                            ps.busyCursorWhile(new CollapseDTreeItemRunnableWithProgress(currentSession, dTreeItem));
                        } else if (treeItemsToCollapse.size() > 1) {
                            ps.busyCursorWhile(new CollapseDTreeItemRunnableWithProgress(currentSession, getDTreeItemsFromTreeItems(treeItemsToCollapse)));
                        }
                    } catch (InvocationTargetException e) {
                        TreeUIPlugin.INSTANCE.log(new Status(IStatus.ERROR, TreeUIPlugin.ID,
                                MessageFormat.format(Messages.TreeItemExpansionManager_expandOrCollaseError, Messages.TreeItemExpansionManager_treeCollapsing), e));
                    } catch (InterruptedException e) {
                        isCollapseToRevert = !isAlreadyCollapsed;
                    }
                } else {
                    isCollapseToRevert = !isAlreadyCollapsed;
                }
            }
            if (isCollapseToRevert || (!isForDTreeItemExpandable(treeItem, currentPermissionAuthority) && !isAlreadyCollapsed)) {
                // The graphical "collapse" has been marked to be reverted during the process, so revert it.
                event.type = SWT.None;
                new ChangeExpandeStateRunnable(treeItem, true).run();
            }
        }
    }

    /**
     * Get the list of {@link DTreeItem}s to collapse according to {@link TreeItem}s to collapse.
     * 
     * @param treeItemsToCollapse
     * @return
     */
    private static List<DTreeItem> getDTreeItemsFromTreeItems(List<TreeItem> treeItemsToCollapse) {
        List<DTreeItem> result = new ArrayList<>();
        for (TreeItem treeItem : treeItemsToCollapse) {
            Object data = treeItem.getData();
            if (data instanceof DTreeItem) {
                result.add((DTreeItem) data);
            }
        }
        return result;
    }

    /**
     * Handle the expand of a DTreeItem according to a graphical expand of a TreeItem. If the expand of a DTreeItem is
     * not allowed because of the current {@link IPermissionAuthority}, the graphical expand is undone.
     * 
     * @param event
     *            the specified {@link Event}
     * @param currentTreeItem
     *            the current tree item, or null of the event is supposed to have the data
     * @param currentSession
     *            the current session
     * @param currentPermissionAuthority
     *            The {@link IPermissionAuthority} responsible to validate if the element is editable
     * @param all
     *            true if an "expand all" is triggered, false if only the current tree item is expanded
     * @param expandDepthLimit
     *            Only used when <code>all</code> is true, it defines the depth to which children of current items
     *            should be expanded.
     */
    public static void handleTreeExpand(Event event, Optional<TreeItem> currentTreeItem, Session currentSession, IPermissionAuthority currentPermissionAuthority, boolean all, int expandDepthLimit) {
        TreeItem treeItem = null;
        if (currentTreeItem.isPresent()) {
            treeItem = currentTreeItem.get();
        } else if (event.item instanceof TreeItem) {
            treeItem = (TreeItem) event.item;
        }
        if (treeItem != null) {
            boolean isAlreadyExpanded = treeItem.getExpanded();
            Object data = treeItem.getData();
            if (data instanceof DTreeItem) {
                DTreeItem dTreeItem = (DTreeItem) data;
                boolean isExpandToRevert = false;
                if (isForDTreeItemExpandable(treeItem, currentPermissionAuthority)) {
                    ExpandAllTreeItemsChangeTrigger modelChangeTrigger = null;
                    if (all) {
                        // Add a pre-commit listener to expand children of the expanded tree item. Indeed, new children
                        // can be created during the expand so it is not possible to create the command before the
                        // pre-commit.
                        modelChangeTrigger = new ExpandAllTreeItemsChangeTrigger(currentSession, expandDepthLimit);
                        currentSession.getEventBroker().addLocalTrigger(ExpandAllTreeItemsChangeTrigger.IS_IMPACTING, modelChangeTrigger);
                    }
                    try {
                        IWorkbench wb = PlatformUI.getWorkbench();
                        IProgressService ps = wb.getProgressService();
                        try {
                            if (dTreeItem.isExpanded() && all) {
                                // The item is already expanded, launch the runnable on all his known collapsed children
                                // (respecting the depth).
                                ps.busyCursorWhile(new ExpandDTreeItemRunnableWithProgress(currentSession, getAllChilrenWithDepth(treeItem, 1, expandDepthLimit), all));
                            } else {
                                // The runnable with expand only one tree item. For the expand all case, the children
                                // will be opened just after with the pre-commit added in #handleTreeExpand(Event,
                                // Optional<TreeItem>, Session, IPermissionAuthority, boolean, int)
                                ps.busyCursorWhile(new ExpandDTreeItemRunnableWithProgress(currentSession, dTreeItem));
                            }
                        } catch (InvocationTargetException e) {
                            TreeUIPlugin.INSTANCE.log(new Status(IStatus.ERROR, TreeUIPlugin.ID,
                                    MessageFormat.format(Messages.TreeItemExpansionManager_expandOrCollaseError, Messages.TreeItemExpansionManager_treeExpanding), e));
                        } catch (InterruptedException e) {
                            isExpandToRevert = !isAlreadyExpanded;
                        }
                    } finally {
                        if (all) {
                            currentSession.getEventBroker().removeLocalTrigger(modelChangeTrigger);
                        }
                    }
                } else {
                    isExpandToRevert = !isAlreadyExpanded;
                }
                if (isExpandToRevert) {
                    // The graphical "expansion" has been marked to be reverted during the process, so revert it.
                    new ChangeExpandeStateRunnable(treeItem, false).run();
                }
            }
        }
    }

    /**
     * Tells if the specified {@link TreeItem} concerned a DTreeIem for which collapse/expansion should be allowed by
     * the current {@link IPermissionAuthority}.
     * 
     * @param treeItem
     *            the specified {@link TreeItem}
     * @param currentPermissionAuthority
     *            The {@link IPermissionAuthority} responsible to validate if the element is editable
     * @return true if the specified {@link Event} is allowed by the current {@link IPermissionAuthority}, false else
     */
    private static boolean isForDTreeItemExpandable(TreeItem treeItem, IPermissionAuthority currentPermissionAuthority) {
        boolean isForDTreeItemExpandable = true;
        if (treeItem != null) {
            Object data = treeItem.getData();
            if (data instanceof DTreeItem) {
                DTreeItem dTreeItem = (DTreeItem) data;
                boolean canEditFeature = currentPermissionAuthority != null && currentPermissionAuthority.canEditFeature(dTreeItem, TreePackage.Literals.DTREE_ITEM__EXPANDED.getName());
                isForDTreeItemExpandable = canEditFeature;
            }
        }
        return isForDTreeItemExpandable;
    }

    private static Map<DTreeItem, Integer> getAllChilrenWithDepth(TreeItem parent, int depth, int expandDepthLimit) {
        Map<DTreeItem, Integer> result = new LinkedHashMap<>();
        if (depth < expandDepthLimit) {
            for (int i = 0; i < parent.getItemCount(); i++) {
                TreeItem treeItem = parent.getItems()[i];
                Object data = treeItem.getData();
                if (data instanceof DTreeItem) {
                    DTreeItem dTreeItem = (DTreeItem) data;
                    if (dTreeItem.isExpanded()) {
                        result.putAll(getAllChilrenWithDepth(treeItem, depth + 1, expandDepthLimit));
                    } else {
                        result.put(dTreeItem, depth);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Dispose this resource.
     */
    private void handleDispose() {
        if (tree != null) {
            tree.removeListener(SWT.Dispose, this);
            tree.removeListener(SWT.Collapse, this);
            tree.removeListener(SWT.Expand, this);
        }
        tree = null;
        session = null;
        permissionAuthority = null;
    }
}
