/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tree.business.api.interaction;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.synchronizer.SemanticPartitionInvalidator;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.api.query.DTreeItemQuery;
import org.eclipse.sirius.tree.business.api.query.TreeDescriptionQuery;
import org.eclipse.sirius.tree.business.internal.dialect.common.tree.DTreeRefresh;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;
import org.eclipse.sirius.tree.tools.internal.Messages;

/**
 * Class allowing to synchronizer a {@link DTreeItem} model according to its semantic model.
 * 
 * @author cbrun
 */
public class DTreeItemUserInteraction {

    private DTreeItem item;

    private GlobalContext ctx;

    /**
     * Creates a new DTreeItemUserInteraction.
     * 
     * @param item
     *            the ETreeItem
     * @param ctx
     *            the Global Context
     */
    public DTreeItemUserInteraction(DTreeItem item, GlobalContext ctx) {
        this.item = item;
        this.ctx = ctx;
    }

    /**
     * Expands the treeItem.
     */
    public void expand() {
        expand(new NullProgressMonitor());
    }

    /**
     * Expands the treeItem.
     * 
     * @param monitor
     *            a {@link IProgressMonitor} to give progression
     */
    public void expand(IProgressMonitor monitor) {
        item.setExpanded(true);
        refreshContent(false, monitor);
    }

    /**
     * Expands all child of the treeItem.
     * 
     * @param monitor
     *            a {@link IProgressMonitor} to give progression
     */
    public void expandAll(IProgressMonitor monitor) {
        try {
            monitor.beginTask(Messages.DTreeItemUserInteraction_treeItemExpanding, item.getOwnedTreeItems().size() + 1);
            expand(new SubProgressMonitor(monitor, 1));
            for (DTreeItem child : item.getOwnedTreeItems()) {
                new DTreeItemUserInteraction(child, ctx).expandAll(new SubProgressMonitor(monitor, 1));
            }
        } finally {
            monitor.done();
        }
    }

    /**
     * Refresh the content of the TreeItem.
     */
    public void refreshContent() {
        refreshContent(false);
    }

    /**
     * Refresh the content of the TreeItem.
     * 
     * @param fullRefresh
     *            true to do a full refresh of the {@link DTreeItem} and its children recursively
     */
    public void refreshContent(boolean fullRefresh) {
        refreshContent(fullRefresh, new NullProgressMonitor());
    }

    /**
     * Refresh the content of the TreeItem.
     * 
     * @param fullRefresh
     *            true to do a full refresh of the {@link DTreeItem} and its children recursively
     * 
     * @param monitor
     *            a {@link IProgressMonitor} to give progression
     */
    public void refreshContent(boolean fullRefresh, IProgressMonitor monitor) {
        SemanticPartitionInvalidator invalidator = new SemanticPartitionInvalidator();
        Option<DTree> parentTree = new DTreeItemQuery(item).getParentTree();
        if (parentTree.some()) {
            DTreeRefresh refresher = new DTreeRefresh(item, new TreeDescriptionQuery(parentTree.get().getDescription()).getAllDescendantMappings(), invalidator, ctx);
            refresher.refresh(fullRefresh, monitor);
        }
    }

    /**
     * Edits the treeItem with the given newLabel.
     * 
     * @param newLabel
     *            the new value for this DTreeItem
     */
    public void directEdit(String newLabel) {
        // TODO
    }

    /**
     * Collapse the treeItem.
     */
    public void collapse() {
        collapse(new NullProgressMonitor());
    }

    /**
     * Collapses the treeItem.
     * 
     * @param monitor
     *            a {@link IProgressMonitor} to give progression
     */
    public void collapse(IProgressMonitor monitor) {
        item.setExpanded(false);
    }

}
