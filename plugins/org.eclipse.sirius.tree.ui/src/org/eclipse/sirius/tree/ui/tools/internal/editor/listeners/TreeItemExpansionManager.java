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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
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

    private ModelAccessor modelAccessor;

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
        modelAccessor = session.getModelAccessor();
        permissionAuthority = modelAccessor.getPermissionAuthority();
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
     * Handle the undo of the swt TreeItem collapse if the current
     * {@link IPermissionAuthority} disallow it.
     */
    private void handleTreeCollapse(Event event) {
        if (event.item instanceof TreeItem) {
            TreeItem treeItem = (TreeItem) event.item;
            Object data = treeItem.getData();
            if (data instanceof DTreeItem) {
                DTreeItem dTreeItem = (DTreeItem) data;
                if (isEventForDTreeItemExpandable(event)) {
                    treeExpandingCollapsingAction(treeItem, dTreeItem, false, Messages.TreeItemExpansionManager_treeCollapsing);
                } else {
                    new ChangeExpandeStateRunnable(treeItem, true).run();
                }
            }
        }
        if (!isEventForDTreeItemExpandable(event)) {
            event.type = SWT.None;
            final TreeItem treeItem = (TreeItem) event.item;
            new ChangeExpandeStateRunnable(treeItem, true).run();
        }
    }

    /**
     * Handle the undo the swt TreeItem expansion if the current
     * {@link IPermissionAuthority} disallow it.
     */
    private void handleTreeExpand(Event event) {
        if (event.item instanceof TreeItem) {
            TreeItem treeItem = (TreeItem) event.item;
            Object data = treeItem.getData();
            if (data instanceof DTreeItem) {
                DTreeItem dTreeItem = (DTreeItem) data;
                if (isEventForDTreeItemExpandable(event)) {
                    treeExpandingCollapsingAction(treeItem, dTreeItem, true, Messages.TreeItemExpansionManager_treeExpanding);
                } else {
                    new ChangeExpandeStateRunnable(treeItem, false).run();
                }
            }
        }
    }

    /**
     * Tells if the specified {@link Event} is a event of a {@link TreeItem}
     * collapse/expansion which should be allowed by the current
     * {@link IPermissionAuthority}.
     * 
     * @param event
     *            the specified {@link Event}
     * @return true if the specified {@link Event} is allowed by the current
     *         {@link IPermissionAuthority}, false else
     */
    private boolean isEventForDTreeItemExpandable(Event event) {
        boolean isEventForDTreeItemExpandable = true;
        if (event.item instanceof TreeItem) {
            TreeItem treeItem = (TreeItem) event.item;
            Object data = treeItem.getData();
            if (data instanceof DTreeItem) {
                DTreeItem dTreeItem = (DTreeItem) data;
                boolean canEditFeature = permissionAuthority != null && permissionAuthority.canEditFeature(dTreeItem, TreePackage.Literals.DTREE_ITEM__EXPANDED.getName());
                isEventForDTreeItemExpandable = canEditFeature;
            }
        }
        return isEventForDTreeItemExpandable;
    }

    /**
     * Expanding/collapsing a DTreeItem.
     * 
     * @param treeItem
     *            the {@link TreeItem} concerned by the collapse/expand
     * @param dTreeItem
     *            the {@link DTreeItem} to update according to collapse/expand
     * @param expand
     *            true to expand, false to collapse
     * @param errorMessage
     *            the error message while expanding/collapsing the tree
     */
    private void treeExpandingCollapsingAction(final TreeItem treeItem, final DTreeItem dTreeItem, final boolean expand, final String errorMessage) {
        if ((expand && !dTreeItem.isExpanded()) || (!expand && dTreeItem.isExpanded())) {
            IWorkbench wb = PlatformUI.getWorkbench();
            IProgressService ps = wb.getProgressService();
            try {
                ps.busyCursorWhile(new ExpandDTreeItemRunnableWithProgress(session, dTreeItem, expand));
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
    }
}
