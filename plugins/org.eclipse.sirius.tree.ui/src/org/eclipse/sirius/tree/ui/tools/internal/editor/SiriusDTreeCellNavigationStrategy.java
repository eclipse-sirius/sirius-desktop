/*******************************************************************************
 * Copyright (c) 2022, 2023 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.tools.internal.editor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.jface.viewers.CellNavigationStrategy;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TreeViewerRow;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.tree.ui.tools.internal.editor.listeners.TreeItemExpansionManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.TreeItem;

/**
 * This is a specific cell navigation strategy to allow to create, if not already done, the children elements when a
 * tree item is expanded with the right arrow key. This class reuses the behavior of {@link TreeItemExpansionManager}
 * called when the user clicks on the plus or minus button with the mouse.<BR/>
 * The implementations for "collapse all" and "expand all" are not similar because for the collapse, all the tree items
 * to collapse are known. For the expand, they could be discovered, because of a refresh, during the expand of another
 * item.
 * 
 * @author Laurent Redor
 *
 */
public class SiriusDTreeCellNavigationStrategy extends CellNavigationStrategy {
    /**
     * The depth to which children of current items should be expanded when using "Expand all" feature.
     */
    private static int expandDepthLimit = 20;

    private Session session;

    private IPermissionAuthority permissionAuthority;

    /**
     * Default constructor.
     * 
     * @param session
     *            The current session
     */
    public SiriusDTreeCellNavigationStrategy(Session session) {
        this.session = session;
        this.permissionAuthority = session.getModelAccessor().getPermissionAuthority();
    }

    /**
     * Set the expand depth limit when using the expand all. By default, it is set to 20.
     * 
     * @param depth
     *            The new depth to apply as a limit of the expand all.
     */
    public static void setExpandDepthLimit(int depth) {
        expandDepthLimit = depth;
    }

    @Override
    public void collapse(ColumnViewer viewer, ViewerCell cellToCollapse, Event event) {
        if (cellToCollapse != null && cellToCollapse.getItem() instanceof TreeItem) {
            Control control = viewer.getControl();
            control.setRedraw(false);
            try {
                TreeItem currentTreeItem = (TreeItem) cellToCollapse.getItem();
                if ((event.stateMask & SWT.MOD2) != 0) {
                    // The shift key is pressed, collapse the current item and all its sub tree items
                    List<TreeItem> subItems = new ArrayList<>();
                    collectSubItems(currentTreeItem, subItems);
                    TreeItemExpansionManager.handleTreeCollapse(event, subItems, session, permissionAuthority, !currentTreeItem.getExpanded());
                } else {
                    // Collapse the current item
                    TreeItemExpansionManager.handleTreeCollapse(event, Optional.of(currentTreeItem), session, permissionAuthority);
                }
            } finally {
                control.setRedraw(true);
            }
        }
    }

    /**
     * Return all the children to be collapsed, including the <code>currentTreeItem</code>.
     * 
     * @param currentTreeItem
     *            The current tree item used as parent. It is added at the end of the list (children first).
     * @return all the children to be collapsed, including the <code>currentTreeItem</code>.
     */
    private void collectSubItems(TreeItem currentTreeItem, List<TreeItem> subItems) {
        for (int i = 0; i < currentTreeItem.getItemCount(); i++) {
            collectSubItems(currentTreeItem.getItem(i), subItems);
        }
        subItems.add(currentTreeItem);
    }

    @Override
    public void expand(ColumnViewer viewer, ViewerCell cellToExpand, Event event) {
        if (cellToExpand != null && cellToExpand.getItem() instanceof TreeItem) {
            Control control = viewer.getControl();
            control.setRedraw(false);
            try {
                TreeItem currentTreeItem = (TreeItem) cellToExpand.getItem();
                // The expand all status (shift key pressed or not), is sent to TreeItemExpansionManager
                TreeItemExpansionManager.handleTreeExpand(event, Optional.of(currentTreeItem), session, permissionAuthority, (event.stateMask & SWT.MOD2) != 0, expandDepthLimit);
            } finally {
                control.setRedraw(true);
            }
        }
    }

    /*
     * (non-Javadoc) Code copied from
     * org.eclipse.jface.viewers.TreeViewerFocusCellManager.TREE_NAVIGATE#isCollapseEvent(org.eclipse.jface.viewers.
     * ColumnViewer, org.eclipse.jface.viewers.ViewerCell, org.eclipse.swt.widgets.Event)
     */
    @Override
    public boolean isCollapseEvent(ColumnViewer viewer, ViewerCell cellToCollapse, Event event) {
        return cellToCollapse != null && ((TreeItem) cellToCollapse.getItem()).getExpanded() && event.keyCode == SWT.ARROW_LEFT && isFirstColumnCell(cellToCollapse);
    }

    /*
     * (non-Javadoc) Code copied from
     * org.eclipse.jface.viewers.TreeViewerFocusCellManager.TREE_NAVIGATE#isExpandEvent(org.eclipse.jface.viewers.
     * ColumnViewer, org.eclipse.jface.viewers.ViewerCell, org.eclipse.swt.widgets.Event)
     */
    @Override
    public boolean isExpandEvent(ColumnViewer viewer, ViewerCell cellToExpand, Event event) {
        boolean result = cellToExpand != null && ((TreeItem) cellToExpand.getItem()).getItemCount() > 0;
        if (result) {
            // If the shift key is pressed, even if the current item is expanded, the event is considered as an expand
            // event because it corresponds to an "expand all" and it possible to have to expanded items.
            result = !((TreeItem) cellToExpand.getItem()).getExpanded() || ((event.stateMask & SWT.MOD2) != 0);
            if (result) {
                result = event.keyCode == SWT.ARROW_RIGHT && isFirstColumnCell(cellToExpand);
            }
        }
        return result;
    }

    /*
     * (non-Javadoc) Code copied from
     * org.eclipse.jface.viewers.TreeViewerFocusCellManager.TREE_NAVIGATE#isFirstColumnCell(org.eclipse.jface.viewers.
     * ViewerCell)
     */
    private boolean isFirstColumnCell(ViewerCell cell) {
        if (cell.getViewerRow() instanceof TreeViewerRow) {
            return ((TreeViewerRow) cell.getViewerRow()).getVisualIndex(cell.getColumnIndex()) == 0;
        } else {
            return false;
        }
    }
}
