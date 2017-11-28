/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.table;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableItem;

/**
 * Test class.
 * 
 * @author dlecan
 */
public class HideRevealTableColumnsTest extends AbstractHideRevealTableElementsTest<SWTBotTable> {

    private static final String DIALOG_TITLE = "Hide/Show table columns";

    private static final String CONTEXTUAL_MENU_HIDE_ELEMENT = "Hide/Show columns...";
    
    @Override
    public void testHideAllElements() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        super.testHideAllElements();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getElementCount() {
        // columnCount - 1 because first column cannot be hidden
        return treeTable.columnCount() - 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> getElementsHeader() {
        return UIThreadRunnable.syncExec(new Result<List<String>>() {
            public List<String> run() {
                final List<String> result = new ArrayList<>();
                final int columnCount = getElementCount();

                for (int i = 1; i <= columnCount; i++) {
                    final TreeColumn column = treeTable.widget.getColumn(i);
                    final DColumn dColumn = (DColumn) column.getData(DTableViewerManager.TABLE_COLUMN_DATA);

                    if (isColumnVisible(dColumn) && column.getWidth() > 0) {
                        result.add(column.getText());
                    }
                }
                return result;
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected DialogTable<SWTBotTable> getInnerTable(final SWTBot parent) {
        final SWTBotTable hideElementTable = parent.table();
        return new DialogTable<SWTBotTable>() {

            /**
             * {@inheritDoc}
             */
            public String getTableItem(final int index) {
                return hideElementTable.getTableItem(index).getText();
            }

            /**
             * 
             * {@inheritDoc}
             */
            public int elementCount() {
                return hideElementTable.rowCount();
            }

            /**
             * {@inheritDoc}
             */
            public SWTBotTable getDialogTable() {
                return hideElementTable;
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getVisibleElementCount() {
        // Visible column means :
        // - For the first line
        // - Corresponding DColumn is visible
        final int columnCount = getElementCount();

        return UIThreadRunnable.syncExec(new Result<Integer>() {
            public Integer run() {
                int nb = 0;
                for (int i = 1; i <= columnCount; i++) {
                    final TreeColumn column = treeTable.widget.getColumn(i);
                    final DColumn dColumn = (DColumn) column.getData(DTableViewerManager.TABLE_COLUMN_DATA);

                    if (isColumnVisible(dColumn) && column.getWidth() > 0) {
                        nb++;
                    }
                }
                return nb;
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String uncheckElement(final DialogTable<SWTBotTable> dialogTable, final int relativeElementIndex) {
        return uncheckAllElements(dialogTable, new int[] { relativeElementIndex }).get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getContextualMenuLabel() {
        return CONTEXTUAL_MENU_HIDE_ELEMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getDialogTitle() {
        return DIALOG_TITLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> uncheckAllElements(final DialogTable<SWTBotTable> dialogTable, final int[] relativeElementIndex) {
        final SWTBotTable swtBotTable = dialogTable.getDialogTable();

        final List<String> result = new ArrayList<>();

        for (final int i : relativeElementIndex) {
            final SWTBotTableItem tableItem = swtBotTable.getTableItem(i);
            tableItem.uncheck();
            result.add(tableItem.getText());
        }

        return result;
    }

    private boolean isColumnVisible(final DColumn dColumn) {
        return dColumn.isVisible();
    }
}
