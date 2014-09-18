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
package org.eclipse.sirius.table.ui.tools.internal.editor.provider;

import java.util.List;
import java.util.Set;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TreeColumn;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableTreeViewer;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.sirius.table.ui.tools.internal.editor.utils.TreeColumnWidthQuery;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTableViewerManager;

/**
 * Utility class providing facilities for manipulating a
 * {@link DTableTreeViewer} (create columns, update columns width...).
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public final class DTableEditorUtil {
    private DTableEditorUtil() {
        // Prevents instanciation/
    }

    /**
     * Adds a new column to the given viewer, at the given position.
     * 
     * @param treeViewerManager
     *            the table viewer on which the new column should be added
     * @param position
     *            the position of the column
     * @param newColumn
     *            the {@link DColumn} describing the column
     */
    public static void addNewColumn(AbstractDTableViewerManager treeViewerManager, int position, DColumn newColumn) {
        DTableTreeViewer dTableTreeViewer = (DTableTreeViewer) treeViewerManager.getTreeViewer();
        ((DTableViewerManager) treeViewerManager).addNewColumn(newColumn, position, true);
        TreeColumn[] columns = dTableTreeViewer.getTree().getColumns();
        // Adding a call to pack() so that the created column has the
        // expected size
        if (columns.length > position) {
            boolean oldIsPropertiesUpdate = treeViewerManager.getEditor().isPropertiesUpdateEnabled();
            treeViewerManager.getEditor().enablePropertiesUpdate(false);
            columns[position].pack();
            treeViewerManager.getEditor().enablePropertiesUpdate(oldIsPropertiesUpdate);
        }
    }

    /**
     * Updates the viewer columns according to the given {@link DTable} :
     * creates, deletes and update columns width if needed.
     * 
     * @param treeViewerManager
     *            the table viewer to update
     * @param dTable
     *            the {@link DTable} reflecting the expected viewer state
     */
    public static void updateViewerColumns(AbstractDTableViewerManager treeViewerManager, DTable dTable) {
        DTableTreeViewer dTableTreeViewer = (DTableTreeViewer) treeViewerManager.getTreeViewer();
        TreeColumn[] treeColumns = dTableTreeViewer.getTree().getColumns();

        // Step 1: update header column width
        TreeColumn treeColumn = treeColumns[0];
        TreeColumnWidthQuery treeColumnWidthQuery = new TreeColumnWidthQuery(treeColumn);
        Display.getDefault().syncExec(treeColumnWidthQuery);
        int widgetWidth = treeColumnWidthQuery.getResult();
        if (dTable.getHeaderColumnWidth() != widgetWidth && dTable.getHeaderColumnWidth() > 0) {
            treeColumn.setWidth(dTable.getHeaderColumnWidth());
        }

        // Step 2: update other columns width
        Set<DColumn> handledDColumns = Sets.newLinkedHashSet();
        for (int i = 1; i < treeColumns.length; i++) {
            treeColumn = treeColumns[i];
            DColumn dColumn = (DColumn) treeColumn.getData(DTableViewerManager.TABLE_COLUMN_DATA);

            if (dColumn != null && dTable.getColumns().contains(dColumn)) {
                handledDColumns.add(dColumn);
                treeColumnWidthQuery = new TreeColumnWidthQuery(treeColumn);
                Display.getDefault().syncExec(treeColumnWidthQuery);
                widgetWidth = treeColumnWidthQuery.getResult();
                // If the DColumn as a default width (0) then resizing at
                // opening should not impact the model
                if (dColumn.isVisible() && dColumn.getWidth() > 0 && dColumn.getWidth() != widgetWidth) {
                    treeColumn.setWidth(dColumn.getWidth());
                }
            } else {
                // Step 3: handle deleted columns
                ((DTableViewerManager) treeViewerManager).removeOldColumn(dColumn);

            }
        }
        // Step 4: handle added columns
        for (DColumn newColumn : Sets.difference(Sets.newLinkedHashSet(dTable.getColumns()), handledDColumns)) {
            int position = dTable.getColumns().indexOf(newColumn) + 1;
            addNewColumn(treeViewerManager, position, newColumn);
        }

        // Step 5: update expanded elemeents
        dTableTreeViewer.setExpandedElements(TableHelper.getExpandedLines(dTable).toArray());
    }

    /**
     * Indicates whether the given TreeColumn is the last column of its parent
     * tree.
     * 
     * @param treeColumn
     *            the treeColumn to test
     * @return true if the given treeColumn is the last column of its parent
     *         tree, false otherwise.
     */
    public static boolean isLastColumn(TreeColumn treeColumn) {
        List<TreeColumn> allTreeColumns = Lists.newArrayList(treeColumn.getParent().getColumns());
        return allTreeColumns.indexOf(treeColumn) == (allTreeColumns.size() - 1);
    }

}
