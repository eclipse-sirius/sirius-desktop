/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.business.api.interaction;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.synchronizer.SemanticPartitionInvalidator;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.api.query.DTreeItemQuery;
import org.eclipse.sirius.tree.business.api.query.TreeDescriptionQuery;
import org.eclipse.sirius.tree.business.internal.dialect.common.tree.DTreeRefresh;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;

/**
 * Class allowing to synchronizer a {@link DTreeItem} model according to its
 * semantic model.
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
        item.setExpanded(true);
        refreshContent();
    }

    /**
     * Expands all child of the treeItem.
     */
    public void expandAll() {
        expand();
        for (DTreeItem child : item.getOwnedTreeItems()) {
            new DTreeItemUserInteraction(child, ctx).expandAll();
        }
    }

    /**
     * Refresh the content of the TreeItem.
     */
    public void refreshContent() {
        SemanticPartitionInvalidator invalidator = new SemanticPartitionInvalidator();
        Option<DTree> parentTree = new DTreeItemQuery(item).getParentTree();
        if (parentTree.some()) {
            DTreeRefresh refresher = new DTreeRefresh(item, new TreeDescriptionQuery(parentTree.get().getDescription()).getAllDescendantMappings(), invalidator, ctx);
            refresher.refresh(new NullProgressMonitor());
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
     * Collapses the treeItem.
     */
    public void collapse() {
        item.setExpanded(false);
    }

}
