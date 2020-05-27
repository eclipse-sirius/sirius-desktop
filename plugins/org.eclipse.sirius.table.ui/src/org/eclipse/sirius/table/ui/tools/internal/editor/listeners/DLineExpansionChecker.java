/*******************************************************************************
 * Copyright (c) 2011, 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.ui.tools.internal.editor.listeners;

import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.sirius.table.ui.tools.internal.editor.utils.TreeColumnWidthSetter;
import org.eclipse.sirius.ui.tools.internal.editor.ChangeExpandeStateRunnable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

/**
 * {@link Listener} to prevents expansion/collapse {@link Event} while {@link IPermissionAuthority} disallow
 * {@link TablePackage#DLINE__COLLAPSED} change. Manage also column resize to update
 * {@link TablePackage#DTABLE__HEADER_COLUMN_WIDTH} or {@link TablePackage#DCOLUMN__WIDTH}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DLineExpansionChecker implements Listener {

    private DTable dTable;

    private DTableViewerManager dTableViewerManager;

    private IPermissionAuthority permissionAuthority;

    private Control control;

    /**
     * Default constructor.
     * 
     * @param control
     *            the {@link Control} to listen
     * @param dTableViewerManager
     *            the {@link DTableViewerManager}
     * @param permissionAuthority
     *            {@link IPermissionAuthority} to know if user can collapse/expand {@link TreeItem} or resize of column.
     */
    public DLineExpansionChecker(Control control, DTableViewerManager dTableViewerManager, IPermissionAuthority permissionAuthority) {
        this.control = control;
        this.dTableViewerManager = dTableViewerManager;
        this.dTable = (dTableViewerManager != null && dTableViewerManager.getEditor() != null) ? dTableViewerManager.getEditor().getTableModel() : null;
        this.permissionAuthority = permissionAuthority;
        control.getDisplay().addFilter(SWT.Expand, this);
        control.getDisplay().addFilter(SWT.Collapse, this);
        control.getDisplay().addFilter(SWT.Resize, this);
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
        case SWT.Resize:
            handleTreeColumnWidthResize(event);
            break;
        default:
            break;
        }

    }

    /**
     * Handle the undo of the swt TreeItem collapse if the current {@link IPermissionAuthority} disallow it.
     */
    private void handleTreeCollapse(Event event) {
        if (!isEventForDLineExpandable(event)) {
            event.type = SWT.None;
            final TreeItem treeItem = (TreeItem) event.item;
            Display.getDefault().asyncExec(new ChangeExpandeStateRunnable(treeItem, true));
        }
    }

    /**
     * Handle the undo the swt TreeItem expansion if the current {@link IPermissionAuthority} disallow it.
     */
    private void handleTreeExpand(Event event) {
        if (!isEventForDLineExpandable(event)) {
            event.type = SWT.None;
            final TreeItem treeItem = (TreeItem) event.item;
            Display.getDefault().asyncExec(new ChangeExpandeStateRunnable(treeItem, false));
        }
    }

    private void handleTreeColumnWidthResize(Event event) {
        // Keep call to isPropertiesUpdateEnabled() because the resize event at
        // opening comes from the swt layout
        if (!isEventForHeaderColumnResizable(event) && event.widget instanceof TreeColumn && dTableViewerManager != null && dTableViewerManager.getEditor().isPropertiesUpdateEnabled()) {
            event.type = SWT.None;
            final TreeColumn treeColumn = (TreeColumn) event.widget;
            Object data = treeColumn.getData(DTableViewerManager.TABLE_COLUMN_DATA);
            int modelWidth = -1;
            if (data == null) {
                modelWidth = dTable.getHeaderColumnWidth();
            } else if (data instanceof DColumn) {
                DColumn dColumn = (DColumn) data;
                modelWidth = dColumn.getWidth();
            }
            if (treeColumn.getWidth() != modelWidth) {
                Runnable treeColumnWidthSetter = new TreeColumnWidthSetter(treeColumn, modelWidth);
                Display.getDefault().asyncExec(treeColumnWidthSetter);
            }
        }
    }

    /**
     * Tells if the specified {@link Event} is a event of a {@link TreeItem} collapse/expansion which should be allowed
     * by the current {@link IPermissionAuthority}.
     * 
     * @param event
     *            the specified {@link Event}
     * @return true if the specified {@link Event} is allowed by the current {@link IPermissionAuthority}, false else
     */
    private boolean isEventForDLineExpandable(Event event) {
        boolean isEventForDLineExpandable = true;
        if (event.item instanceof TreeItem) {
            TreeItem treeItem = (TreeItem) event.item;
            Object data = treeItem.getData();
            if (data instanceof DLine) {
                DLine dLine = (DLine) data;
                boolean canEditFeature = permissionAuthority != null && permissionAuthority.canEditFeature(dLine, TablePackage.Literals.DLINE__COLLAPSED.getName());
                isEventForDLineExpandable = canEditFeature;
            }
        }
        return isEventForDLineExpandable;
    }

    /**
     * Tells if the specified {@link Event} is a event of a {@link TreeColumn#getWidth()} resize which should be allowed
     * by the current {@link IPermissionAuthority}.
     * 
     * @param event
     *            the specified {@link Event}
     * @return true if the specified {@link Event} is allowed by the current {@link IPermissionAuthority}, false else
     */
    private boolean isEventForHeaderColumnResizable(Event event) {
        boolean isEventForHeaderColumnResizable = true;
        if (event.widget instanceof TreeColumn) {
            TreeColumn treeColumn = (TreeColumn) event.widget;
            Object data = treeColumn.getData(DTableViewerManager.TABLE_COLUMN_DATA);
            if (data == null) {
                boolean canEditFeature = permissionAuthority != null && permissionAuthority.canEditFeature(dTable, TablePackage.Literals.DTABLE__HEADER_COLUMN_WIDTH.getName());
                isEventForHeaderColumnResizable = canEditFeature;
            } else if (data instanceof DColumn) {
                DColumn dColumn = (DColumn) data;
                boolean canEditFeature = permissionAuthority != null && permissionAuthority.canEditFeature(dColumn, TablePackage.Literals.DCOLUMN__WIDTH.getName());
                isEventForHeaderColumnResizable = canEditFeature;
            }
        }
        return isEventForHeaderColumnResizable;
    }

    /**
     * remove This {@link DLineExpansionChecker} as {@link Listener} of the Tree.
     */
    public void dispose() {
        control.getDisplay().removeFilter(SWT.Expand, this);
        control.getDisplay().removeFilter(SWT.Collapse, this);
        control.getDisplay().removeFilter(SWT.Resize, this);
        dTable = null;
        dTableViewerManager = null;
        permissionAuthority = null;
        control = null;
    }

}
