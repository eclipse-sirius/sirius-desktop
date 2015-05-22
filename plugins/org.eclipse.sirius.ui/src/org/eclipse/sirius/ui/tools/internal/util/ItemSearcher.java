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
package org.eclipse.sirius.ui.tools.internal.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTableViewerManager;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Class which look for from a {@link DRepresentationElement} the first
 * referencing {@link Item}.
 * 
 * NOTE : to be execute with Display#asyncExec(Runnable).
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ItemSearcher extends RunnableWithResult.Impl<Item> implements RunnableWithResult<Item> {

    private Tree tree;

    private DRepresentationElement dRepresentationElement;

    /**
     * Construct a {@link ItemSearcher} to get the first {@link Item} in
     * <code>tree</code> which references the
     * <code>dRepresentationElement</code>.
     * 
     * @param tree
     *            the {@link Tree} on which look for a {@link Item}
     * 
     * @param dRepresentationElement
     *            the {@link DRepresentationElement} for which look for a
     *            {@link Item}
     */
    public ItemSearcher(Tree tree, DRepresentationElement dRepresentationElement) {
        super();
        this.tree = tree;
        this.dRepresentationElement = dRepresentationElement;
    }

    /**
     * {@inheritDoc}
     */
    public void run() {
        if (!tree.isDisposed()) {
            Item result = getTreeItem();
            if (result == null) {
                result = getTreeColumn();
            }
            if (result != null) {
                setResult(result);
            }
        }
    }

    private TreeItem getTreeItem() {
        TreeItem result = null;
        Object data = tree.getData();
        if (data != null && data.equals(dRepresentationElement)) {
            // WARNING this class is only do to look for TreeItem
        } else {
            TreeItem[] items = tree.getItems();
            for (TreeItem treeItem : items) {
                data = treeItem.getData();
                if (data != null && data.equals(dRepresentationElement)) {
                    result = treeItem;
                    break;
                } else if (isParentOfDTreeElementTreeItem(treeItem)) {
                    result = getTreeItem(treeItem);
                    break;
                }
            }
        }
        return result;
    }

    private TreeItem getTreeItem(TreeItem parentTreeItem) {
        TreeItem result = null;
        TreeItem[] items = parentTreeItem.getItems();
        for (TreeItem treeItem : items) {
            Object data = treeItem.getData();
            if (data != null && data.equals(dRepresentationElement)) {
                result = treeItem;
                break;
            } else if (isParentOfDTreeElementTreeItem(treeItem)) {
                result = getTreeItem(treeItem);
                break;
            }
        }
        return result;
    }

    /**
     * Look for in {@link Tree#getColumns()} list.
     * 
     * @return the first found {@link TreeColumn} referencing the
     *         <code>dRepresentationElement</code>
     */
    private TreeColumn getTreeColumn() {
        TreeColumn result = null;
        if (dRepresentationElement == null) {
            result = tree.getColumn(0);
        } else {
            TreeColumn[] items = tree.getColumns();
            for (TreeColumn treeColumn : items) {
                Object data = treeColumn.getData(AbstractDTableViewerManager.TABLE_COLUMN_DATA);
                if (data != null && data.equals(dRepresentationElement)) {
                    result = treeColumn;
                    break;
                }
            }
        }
        return result;
    }

    private boolean isParentOfDTreeElementTreeItem(TreeItem treeItem) {
        Object data = treeItem.getData();
        EObject eContainer = dRepresentationElement;
        boolean isParentOfDTreeElementTreeItem = eContainer != null && data != null && data.equals(eContainer);
        while (eContainer != null && !isParentOfDTreeElementTreeItem) {
            eContainer = eContainer.eContainer();
            isParentOfDTreeElementTreeItem = eContainer != null && data != null && data.equals(eContainer);
        }

        return isParentOfDTreeElementTreeItem;
    }

}
