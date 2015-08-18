/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.provider;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableTreeViewer;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.ui.tools.internal.util.ItemSearcher;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

/**
 * A {@link Runnable} to update a {@link DTableTreeViewer} from the
 * {@link TableUIUpdater}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TableUIUpdaterRunnable implements Runnable {

    private DTableViewerManager dTableViewerManager;

    private DTableTreeViewer dTableTreeViewer;

    private Set<DLine> toExpands;

    private Set<DLine> toCollapses;

    private Set<Object> toRefreshInViewerWithUpdateLabels;

    private boolean launchGlobalRefreshWithoutUpdateLabels;

    private boolean launchGlobalRefreshWithUpdateLabels;

    private Object[] objectsToUpdateInViewer;

    private Set<DColumn> dColumnsToUpdateDirectly;

    private Set<DColumn> dColumnsWidthToUpdate;

    private Set<DColumn> dColumnsToRemove;

    private Map<DColumn, Integer> dColumnsToAdd;

    private Map<DColumn, Boolean> dColumnsToVisibilityChanged;

    private boolean updateHeaderColumnWidth;

    /**
     * Default constructor.
     * 
     * @param dTableViewerManager
     *            the {@link DTableViewerManager} to add/remove columns
     * @param dTableTreeViewer
     *            the {@link DTableTreeViewer} to update
     * @param toExpands
     *            the elements to expand
     * @param toCollapses
     *            the elements to collapse
     * @param toRefreshInViewerWithUpdateLabels
     *            the elements to refresh
     * @param launchGlobalRefreshWithUpdateLabels
     *            true to launch a refresh with updateLabels at true
     * @param launchGlobalRefreshWithoutUpdateLabels
     *            true to launch a refresh with updateLabels at false
     * @param objectsToUpdateInViewer
     *            the elements to update
     * @param dColumnsToUpdateDirectly
     *            columns to update directly
     * @param dColumnsWidthToUpdate
     *            columns for which update width
     * @param dColumnsToRemove
     *            {@link DColumn}s for which remove existing swt columns
     * @param dColumnsToAdd
     *            new {@link DColumn}s for which create new swt columns
     * @param dColumnsToVisibilityChanged
     *            columns for which change visibility
     * @param updateHeaderColumnWidth
     *            true to update header column width
     */
    // CHECKSTYLE:OFF
    public TableUIUpdaterRunnable(DTableViewerManager dTableViewerManager, DTableTreeViewer dTableTreeViewer, Set<DLine> toExpands, Set<DLine> toCollapses,
            Set<Object> toRefreshInViewerWithUpdateLabels, boolean launchGlobalRefreshWithoutUpdateLabels, boolean launchGlobalRefreshWithUpdateLabels, Object[] objectsToUpdateInViewer,
            Set<DColumn> dColumnsToUpdateDirectly, Set<DColumn> dColumnsWidthToUpdate, Set<DColumn> dColumnsToRemove, Map<DColumn, Integer> dColumnsToAdd,
            Map<DColumn, Boolean> dColumnsToVisibilityChanged, boolean updateHeaderColumnWidth) {
        // CHECKSTYLE:ON
        this.dTableTreeViewer = dTableTreeViewer;
        this.dTableViewerManager = dTableViewerManager;
        this.toRefreshInViewerWithUpdateLabels = toRefreshInViewerWithUpdateLabels;
        this.objectsToUpdateInViewer = objectsToUpdateInViewer;
        this.toExpands = toExpands;
        this.toCollapses = toCollapses;
        this.dColumnsToRemove = dColumnsToRemove;
        this.dColumnsToAdd = dColumnsToAdd;
        this.launchGlobalRefreshWithoutUpdateLabels = launchGlobalRefreshWithoutUpdateLabels;
        this.launchGlobalRefreshWithUpdateLabels = launchGlobalRefreshWithUpdateLabels;
        this.dColumnsWidthToUpdate = dColumnsWidthToUpdate;
        this.updateHeaderColumnWidth = updateHeaderColumnWidth;
        this.dColumnsToVisibilityChanged = dColumnsToVisibilityChanged;
        this.dColumnsToUpdateDirectly = dColumnsToUpdateDirectly;
    }

    @Override
    public void run() {
        if (dTableTreeViewer != null && dTableTreeViewer.getControl() != null && !dTableTreeViewer.getControl().isDisposed()) {
            expand();
            collapse();
            refresh();
            /* No need to update objects which got refresh already... */
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.UPDATE_SWT_LINE_KEY);
            dTableTreeViewer.update(objectsToUpdateInViewer, null);
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.UPDATE_SWT_LINE_KEY);
            removeColumns();
            addColumns();

            if (launchGlobalRefreshWithoutUpdateLabels || launchGlobalRefreshWithUpdateLabels) {
                DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.REFRESH_TABLE_KEY);
                dTableTreeViewer.refresh(launchGlobalRefreshWithUpdateLabels);
                DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.REFRESH_TABLE_KEY);
            }
            if (updateHeaderColumnWidth) {
                DTable dTable = (DTable) dTableTreeViewer.getInput();
                Tree tree = dTableTreeViewer.getTree();
                TreeColumn headerTreeColumn = tree.getColumn(0);
                if (headerTreeColumn.getWidth() != dTable.getHeaderColumnWidth()) {
                    headerTreeColumn.setWidth(dTable.getHeaderColumnWidth());
                }
            }
            updateColumns();
            updateColumnVisibility();
        }
    }

    private void expand() {
        for (Object itemtoExpand : toExpands) {
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.CHANGE_SWT_LINE_COLLAPSE_STATE_KEY);
            dTableTreeViewer.setExpandedState(itemtoExpand, true);
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.CHANGE_SWT_LINE_COLLAPSE_STATE_KEY);
        }
    }

    private void collapse() {
        for (Object itemToCollapse : toCollapses) {
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.CHANGE_SWT_LINE_COLLAPSE_STATE_KEY);
            dTableTreeViewer.setExpandedState(itemToCollapse, false);
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.CHANGE_SWT_LINE_COLLAPSE_STATE_KEY);
        }
    }

    private void refresh() {
        for (Object itemToRefresh : toRefreshInViewerWithUpdateLabels) {
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.REFRESH_SWT_TABLE_KEY);
            dTableTreeViewer.refresh(itemToRefresh, true);
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.REFRESH_SWT_TABLE_KEY);
        }
    }

    private void removeColumns() {
        for (DColumn dColumn : dColumnsToRemove) {
            dTableViewerManager.removeOldColumn(dColumn);
        }
    }

    private void addColumns() {
        for (Entry<DColumn, Integer> entry : dColumnsToAdd.entrySet()) {
            DColumn newColumn = entry.getKey();
            Integer position = entry.getValue();
            DTableEditorUtil.addNewColumn(dTableViewerManager, position, newColumn);
        }
        if (!dColumnsToAdd.isEmpty()) {
            dTableTreeViewer.refresh();
        }
    }

    private void updateColumns() {
        for (DColumn dColumnToUpdateDirectly : dColumnsToUpdateDirectly) {
            for (int i = 0; i < dTableTreeViewer.getTree().getColumns().length; i++) {
                CellLabelProvider labelProvider = dTableTreeViewer.getLabelProvider(i);
                if (labelProvider instanceof DelegatingStyledCellLabelProvider) {
                    // Use the original label provider if possible
                    IStyledLabelProvider styledLabelProvider = ((DelegatingStyledCellLabelProvider) labelProvider).getStyledStringProvider();
                    if (styledLabelProvider instanceof CellLabelProvider) {
                        labelProvider = (CellLabelProvider) styledLabelProvider;
                    }
                }
                if (labelProvider instanceof DTableColumnLabelProvider && ((DTableColumnLabelProvider) labelProvider).isProvideColumn(dColumnToUpdateDirectly)) {
                    DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.SET_COLUMN_NAME_KEY);

                    TreeColumn treeColumn = dTableTreeViewer.getTree().getColumn(i);

                    ILabelProvider dTableColumnHeaderLabelProvider = new DTableColumnHeaderLabelProvider();

                    String text = dTableColumnHeaderLabelProvider.getText(dColumnToUpdateDirectly);
                    treeColumn.setText(text);

                    Image image = dTableColumnHeaderLabelProvider.getImage(dColumnToUpdateDirectly);
                    treeColumn.setImage(image);

                    DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.SET_COLUMN_NAME_KEY);
                }
            }
        }

        if (dTableViewerManager.getEditor().isPropertiesUpdateEnabled()) {
            for (DColumn dColumn : dColumnsWidthToUpdate) {
                Tree tree = dTableTreeViewer.getTree();
                ItemSearcher itemSearcher = new ItemSearcher(tree, dColumn);
                itemSearcher.run();
                Object result = itemSearcher.getResult();
                if (result instanceof TreeColumn) {
                    TreeColumn treeColumn = (TreeColumn) result;
                    if (treeColumn.getWidth() != dColumn.getWidth()) {
                        treeColumn.setWidth(dColumn.getWidth());
                        // The setting of the column data is also needed here
                        // since Luna because the above width will be reset by
                        // AbstractColumnLayout.layoutTableTree otherwise.
                        ((TreeColumnLayout) treeColumn.getParent().getParent().getLayout()).setColumnData(treeColumn, new ColumnPixelData(dColumn.getWidth()));
                    }
                }
            }
        }
    }

    private void updateColumnVisibility() {
        for (Entry<DColumn, Boolean> entry : dColumnsToVisibilityChanged.entrySet()) {
            DColumn dColumn = entry.getKey();
            Boolean visible = entry.getValue();
            for (int i = 0; i < dTableTreeViewer.getTree().getColumns().length; i++) {
                CellLabelProvider labelProvider = dTableTreeViewer.getLabelProvider(i);
                if (labelProvider instanceof DelegatingStyledCellLabelProvider) {
                    // Use the original label provider if possible
                    IStyledLabelProvider styledLabelProvider = ((DelegatingStyledCellLabelProvider) labelProvider).getStyledStringProvider();
                    if (styledLabelProvider instanceof CellLabelProvider) {
                        labelProvider = (CellLabelProvider) styledLabelProvider;
                    }
                }
                if (labelProvider instanceof DTableColumnLabelProvider && ((DTableColumnLabelProvider) labelProvider).isProvideColumn(dColumn)) {
                    DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.CHANGE_SWT_COLUMN_VISIBLE_STATE_KEY);
                    final TreeColumn treeColumn = dTableTreeViewer.getTree().getColumn(i);
                    handleColumn(treeColumn, visible);
                    DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.CHANGE_SWT_COLUMN_VISIBLE_STATE_KEY);
                }
            }
        }
    }

    private void handleColumn(final TreeColumn treeColumn, boolean visible) {
        if (visible) {
            // Show column
            final Integer restoredWith = (Integer) treeColumn.getData("restoredWidth"); //$NON-NLS-1$
            if (restoredWith != null) {
                treeColumn.setWidth(restoredWith.intValue());
                ((TreeColumnLayout) treeColumn.getParent().getParent().getLayout()).setColumnData(treeColumn, new ColumnPixelData(restoredWith.intValue()));
            } else {
                treeColumn.pack();
                ((TreeColumnLayout) treeColumn.getParent().getParent().getLayout()).setColumnData(treeColumn, new ColumnPixelData(treeColumn.getWidth()));
            }
        } else {
            // Hide column
            treeColumn.setData("restoredWidth", Integer.valueOf(treeColumn.getWidth())); //$NON-NLS-1$
            treeColumn.setWidth(0);
            ((TreeColumnLayout) treeColumn.getParent().getParent().getLayout()).setColumnData(treeColumn, new ColumnPixelData(0));
        }
    }

}
