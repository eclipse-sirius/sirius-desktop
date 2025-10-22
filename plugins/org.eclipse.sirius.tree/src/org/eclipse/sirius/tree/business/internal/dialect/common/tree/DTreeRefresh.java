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
package org.eclipse.sirius.tree.business.internal.dialect.common.tree;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.sirius.synchronizer.CreatedOutput;
import org.eclipse.sirius.synchronizer.Mapping;
import org.eclipse.sirius.synchronizer.MappingHiearchyTable;
import org.eclipse.sirius.synchronizer.ModelToModelSynchronizer;
import org.eclipse.sirius.synchronizer.SemanticPartitionInvalidator;
import org.eclipse.sirius.synchronizer.SignatureProvider;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.tree.tools.internal.Messages;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Update the {@link DTree} model according to the semantic model and the
 * {@link org.eclipse.sirius.tree.description.TreeDescription} model.
 * 
 * @author cbrun
 */
public class DTreeRefresh {

    private DTreeItemContainer container;

    private SemanticPartitionInvalidator invalidator;

    private Iterable<? extends TreeItemMapping> mappings;

    private GlobalContext ctx;

    /**
     * Creates a new DTreeRefresh.
     * 
     * @param tree
     *            The tree to refresh
     * @param mappings
     *            the item mappings
     * @param invalidator
     *            the {@link SemanticPartitionInvalidator}
     * @param ctx
     *            the global context
     */
    public DTreeRefresh(DTreeItemContainer tree, Iterable<? extends TreeItemMapping> mappings, SemanticPartitionInvalidator invalidator, GlobalContext ctx) {
        this.container = tree;
        this.invalidator = invalidator;
        this.ctx = ctx;
        this.mappings = mappings;
    }

    /**
     * Refreshes the tree lazily, i.e. does not refresh sub tree of collapsed {@link DTreeItemContainer}.
     * 
     * @param monitor
     *            a {@link IProgressMonitor} to use
     */
    public void refresh(IProgressMonitor monitor) {
        refresh(false, monitor);
    }

    /**
     * Refreshes the tree.
     * 
     * @param fullRefresh
     *            true to do a full refresh of {@link DTreeItemContainer} even sub tree of collapsed
     *            {@link DTreeItemContainer}
     * 
     * @param monitor
     *            a {@link IProgressMonitor} to use
     */
    public void refresh(boolean fullRefresh, IProgressMonitor monitor) {
        try {
            monitor.beginTask(Messages.DTreeUserInteraction_treeRefresh, 8);
            if (ctx.getModelAccessor().getPermissionAuthority().canEditInstance(container)) {
                MappingHiearchyTable hierarchy = new MappingHiearchyTable();
                SignatureProvider signProvider = new TreeSignatureProvider(hierarchy);

                SemanticPartitionProvider semProvider = new SemanticPartitionProvider(this.ctx);
                final TreeMappingProvider provider = new TreeMappingProvider(semProvider);
                Iterable<Mapping> providedMappings = Iterables.transform(mappings, new Function<TreeItemMapping, Mapping>() {

                    @Override
                    public Mapping apply(TreeItemMapping from) {
                        return provider.getOrCreate(from);
                    }
                });
                if (monitor.isCanceled()) {
                    throw new OperationCanceledException();
                }
                monitor.worked(1);

                hierarchy.compute(Lists.newArrayList(providedMappings));
                if (monitor.isCanceled()) {
                    throw new OperationCanceledException();
                }
                monitor.worked(1);

                DTreePreRefreshStatus pre = new DTreePreRefreshStatus(ctx, provider);
                ModelToModelSynchronizer refresher = new ModelToModelSynchronizer(this.invalidator, hierarchy, pre, signProvider);

                CreatedOutput cDiag = buildOutput(provider);
                if (monitor.isCanceled()) {
                    throw new OperationCanceledException();
                }
                monitor.worked(1);

                refresher.update(cDiag, fullRefresh, new SubProgressMonitor(monitor, 5));
            }
        } finally {
            monitor.done();
        }
    }

    private CreatedOutput buildOutput(final TreeMappingProvider provider) {
        if (container instanceof DTree) {
            DTree tree = (DTree) container;
            OutputDTreeDescriptor existingDTree = new OutputDTreeDescriptor(tree.getTarget(), tree.getDescription(), provider);
            CreatedDTree cDiag = new CreatedDTree(ctx, tree, existingDTree);
            return cDiag;
        } else if (container instanceof DTreeItem) {
            DTreeItem item = (DTreeItem) container;
            DTreeItemContainer treeItemcontainer = item.getContainer();
            OutputTreeItemDescriptor descriptor = new OutputTreeItemDescriptor(treeItemcontainer, item.getTarget(), item.getActualMapping(), 0, provider);
            CreatedTreeItem created = new CreatedTreeItem(ctx, item, descriptor);
            return created;
        }
        throw new RuntimeException(Messages.DTreeRefresh_unknownRepresentationContainer);
    }
}
