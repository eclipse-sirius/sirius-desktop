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
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.synchronizer.SemanticPartitionInvalidator;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.api.query.TreeDescriptionQuery;
import org.eclipse.sirius.tree.business.internal.dialect.common.tree.DTreeRefresh;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;
import org.eclipse.sirius.tree.tools.internal.Messages;

/**
 * This class is responsible for providing an entry point to all the user interactions available on an opened DTree
 * instance.
 * 
 * @author cbrun
 * 
 */
public class DTreeUserInteraction {

    private DTree tree;

    private GlobalContext ctx;

    /**
     * Creates a new DTreeUserInteraction.
     * 
     * @param tree
     *            the DTree
     * @param ctx
     *            the GlobalContext
     */
    public DTreeUserInteraction(DTree tree, GlobalContext ctx) {
        this.tree = tree;
        this.ctx = ctx;
    }

    /**
     * Refreshes the content of the DTree.
     * 
     * @param monitor
     *            a {@link IProgressMonitor} to give progression
     * 
     * @return this user interaction for convenience
     */
    public DTreeUserInteraction refreshContent(IProgressMonitor monitor) {
        return refreshContent(false, monitor);
    }

    /**
     * Refreshes the content of the {@link DTree}.
     * 
     * @param fullRefresh
     *            true to do a full refresh of {@link DTree} even sub tree of collapsed
     *            {@link org.eclipse.sirius.tree.DTreeItemContainer}
     * 
     * @param monitor
     *            a {@link IProgressMonitor} to use
     * 
     * @return this user interaction for convenience
     */
    public DTreeUserInteraction refreshContent(boolean fullRefresh, IProgressMonitor monitor) {
        try {
            monitor.beginTask(Messages.DTreeUserInteraction_treeRefresh, 1);
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.REFRESH_TREE_KEY);
            SemanticPartitionInvalidator invalidator = new SemanticPartitionInvalidator();
            DTreeRefresh refresher = new DTreeRefresh(tree, new TreeDescriptionQuery(tree.getDescription()).getAllDescendantMappings(), invalidator, ctx);
            refresher.refresh(fullRefresh, new SubProgressMonitor(monitor, 1));
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.REFRESH_TREE_KEY);
        } finally {
            monitor.done();
        }
        return this;
    }

    /**
     * Expands all items of the DTree recursively.
     * 
     * @return this user interaction
     */
    public DTreeUserInteraction expandAll() {
        return expand(new NullProgressMonitor());
    }

    /**
     * Expands all items of the DTree recursively.
     * 
     * @param monitor
     *            a {@link IProgressMonitor} to give progression
     * 
     * @return this user interaction
     */
    public DTreeUserInteraction expandAll(IProgressMonitor monitor) {
        try {
            monitor.beginTask(Messages.DTreeItemUserInteraction_treeItemExpanding, tree.getOwnedTreeItems().size());
            for (DTreeItem child : tree.getOwnedTreeItems()) {
                new DTreeItemUserInteraction(child, ctx).expandAll(new SubProgressMonitor(monitor, 1));
            }
        } finally {
            monitor.done();
        }
        return this;
    }

    /**
     * Expand all root items of the DTree.
     * 
     * @return this user interaction
     * 
     */
    public DTreeUserInteraction expand() {
        return expand(new NullProgressMonitor());
    }

    /**
     * Expand all root items of the DTree.
     * 
     * @return this user interaction
     * 
     * @param monitor
     *            a {@link IProgressMonitor} to give progression
     */
    public DTreeUserInteraction expand(IProgressMonitor monitor) {
        try {
            monitor.beginTask(Messages.DTreeItemUserInteraction_treeItemExpanding, tree.getOwnedTreeItems().size());
            for (DTreeItem child : tree.getOwnedTreeItems()) {
                if (!child.isExpanded()) {
                    new DTreeItemUserInteraction(child, ctx).expand(new SubProgressMonitor(monitor, 1));
                } else {
                    monitor.worked(1);
                }
            }
        } finally {
            monitor.done();
        }
        return this;
    }
}
