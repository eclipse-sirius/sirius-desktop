/*******************************************************************************
 * Copyright (c) 2015 Obeo. 
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
 * Class responsible to DTree model update following a swt collapse/expand
 * TreeItem if the {@link IPermissionAuthority} allows it.
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
     *            the {@link Session} owning the DTree to update on expand or
     *            collapse event
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
            handleTreeCollapse(event);
            break;
        case SWT.Expand:
            handleTreeExpand(event);
            break;
        case SWT.Dispose:
            handleDispose();
            break;
        default:
            break;
        }

    }

    /**
     * Handle the undo of the swt TreeItem collapse if the current {@link IPermissionAuthority} disallow it.
     */
    private void handleTreeCollapse(Event event) {
        handleTreeCollapse(event, Optional.empty(), session, permissionAuthority);
    }

    /**
     * Handle the undo of the swt TreeItem collapse if the current {@link IPermissionAuthority} disallow it.
     * 
     * @param event
     *            the specified {@link Event}
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
            Object data = treeItem.getData();
            if (data instanceof DTreeItem) {
                DTreeItem dTreeItem = (DTreeItem) data;
                if (isForDTreeItemExpandable(treeItem, currentPermissionAuthority)) {
                    treeExpandingCollapsingAction(currentSession, treeItem, dTreeItem, false, Messages.TreeItemExpansionManager_treeCollapsing);
                } else {
                    new ChangeExpandeStateRunnable(treeItem, true).run();
                }
            }
        }
        if (!isForDTreeItemExpandable(treeItem, currentPermissionAuthority)) {
            event.type = SWT.None;
            new ChangeExpandeStateRunnable(treeItem, true).run();
        }
    }

    /**
     * Handle the undo of the swt TreeItem expansion if the current {@link IPermissionAuthority} disallow it.
     */
    private void handleTreeExpand(Event event) {
        handleTreeExpand(event, Optional.empty(), session, permissionAuthority);
    }

    /**
     * Handle the undo of the swt TreeItem expansion if the current {@link IPermissionAuthority} disallow it.
     * 
     * @param event
     *            the specified {@link Event}
     * @param currentTreeItem
     *            the current tree item, or null of the event is supposed to have the data
     * @param currentSession
     *            the current session
     * @param currentPermissionAuthority
     *            The {@link IPermissionAuthority} responsible to validate if the element is editable
     */
    public static void handleTreeExpand(Event event, Optional<TreeItem> currentTreeItem, Session currentSession, IPermissionAuthority currentPermissionAuthority) {
        TreeItem treeItem = null;
        if (currentTreeItem.isPresent()) {
            treeItem = currentTreeItem.get();
        } else if (event.item instanceof TreeItem) {
            treeItem = (TreeItem) event.item;
        }
        if (treeItem != null) {
            Object data = treeItem.getData();
            if (data instanceof DTreeItem) {
                DTreeItem dTreeItem = (DTreeItem) data;
                if (isForDTreeItemExpandable(treeItem, currentPermissionAuthority)) {
                    treeExpandingCollapsingAction(currentSession, treeItem, dTreeItem, true, Messages.TreeItemExpansionManager_treeExpanding);
                } else {
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

    /**
     * Expanding/collapsing a DTreeItem.
     * 
     * @param currentSession
     *            the current session
     * @param treeItem
     *            the {@link TreeItem} concerned by the collapse/expand
     * @param dTreeItem
     *            the {@link DTreeItem} to update according to collapse/expand
     * @param expand
     *            true to expand, false to collapse
     * @param errorMessage
     *            the error message while expanding/collapsing the tree
     */
    private static void treeExpandingCollapsingAction(final Session currentSession, final TreeItem treeItem, final DTreeItem dTreeItem, final boolean expand, final String errorMessage) {
        if ((expand && !dTreeItem.isExpanded()) || (!expand && dTreeItem.isExpanded())) {
            IWorkbench wb = PlatformUI.getWorkbench();
            IProgressService ps = wb.getProgressService();
            try {
                ps.busyCursorWhile(new ExpandDTreeItemRunnableWithProgress(currentSession, dTreeItem, expand));
            } catch (InvocationTargetException e) {
                TreeUIPlugin.INSTANCE.log(new Status(IStatus.ERROR, TreeUIPlugin.ID, MessageFormat.format(Messages.TreeItemExpansionManager_expandOrCollaseError, errorMessage), e));
            } catch (InterruptedException e) {
                new ChangeExpandeStateRunnable(treeItem, !expand).run();
            }
        }
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
