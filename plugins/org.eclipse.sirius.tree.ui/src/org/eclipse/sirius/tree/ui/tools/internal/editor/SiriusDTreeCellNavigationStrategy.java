/*******************************************************************************
 * Copyright (c) 2022 Obeo
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

import java.util.Optional;

import org.eclipse.jface.viewers.CellNavigationStrategy;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TreeViewerRow;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.tree.ui.tools.internal.editor.listeners.TreeItemExpansionManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.TreeItem;

/**
 * This is a specific cell navigation strategy to allow to create, if not already done, the children elements when a
 * tree item is expanded with the right arrow key. This class reuses the behavior of {@link TreeItemExpansionManager}
 * called when the user clicks on the plus or minus button with the mouse.
 * 
 * @author Laurent Redor
 *
 */
public class SiriusDTreeCellNavigationStrategy extends CellNavigationStrategy {
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

    @Override
    public void collapse(ColumnViewer viewer, ViewerCell cellToCollapse, Event event) {
        if (cellToCollapse != null && cellToCollapse.getItem() instanceof TreeItem) {
            TreeItemExpansionManager.handleTreeCollapse(event, Optional.of((TreeItem) cellToCollapse.getItem()), session, permissionAuthority);
        }
    }

    @Override
    public void expand(ColumnViewer viewer, ViewerCell cellToExpand, Event event) {
        if (cellToExpand != null && cellToExpand.getItem() instanceof TreeItem) {
            TreeItemExpansionManager.handleTreeExpand(event, Optional.of((TreeItem) cellToExpand.getItem()), session, permissionAuthority);
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
        boolean result = cellToExpand != null && ((TreeItem) cellToExpand.getItem()).getItemCount() > 0 && !((TreeItem) cellToExpand.getItem()).getExpanded();
        if (result) {
            result = result && event.keyCode == SWT.ARROW_RIGHT && isFirstColumnCell(cellToExpand);
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
