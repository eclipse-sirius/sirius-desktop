/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.business.api.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.business.api.query.DCellQuery;
import org.eclipse.sirius.table.business.api.query.DTableQuery;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.description.SystemColors;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Utility methods to handle Table models.
 * 
 * @author lredor
 * 
 */
public final class TableUIHelper {

    private TableUIHelper() {

    }

    /**
     * Transform a table to a table descriptor.
     * 
     * @param table
     *            table to transform.
     * @param inverse
     *            true if table should invert table/columns.
     * @param filler
     *            class responsible for getting the cell,line and column
     *            content.
     * @return a list of list representing the table.
     */
    protected static List<List<String>> toTableDescriptor(final DTable table, final boolean inverse, final TableFiller filler) {
        final List<List<String>> expected = new ArrayList<List<String>>();
        if (!inverse) {
            final List<String> header = new ArrayList<String>();
            header.add(""); //$NON-NLS-1$
            for (final DColumn column : table.getColumns()) {
                if (column.isVisible()) {
                    header.add(filler.getContent(column));
                }
            }
            expected.add(header);
            for (final DLine line : table.getLines()) {
                if (line.isVisible()) {
                    TableUIHelper.addLineToDescriptor(filler, expected, line);
                }
            }
        } else {
            final List<String> header = new ArrayList<String>();
            header.add("");
            for (final DLine line : table.getLines()) {
                if (line.isVisible()) {
                    header.add(filler.getContent(line));
                }
            }
            expected.add(header);
            for (final DColumn column : table.getColumns()) {
                if (column.isVisible()) {
                    final List<String> listColumn = new ArrayList<String>();
                    listColumn.add(filler.getContent(column));
                    for (DLine line : new DTableQuery(TableHelper.getTable(column)).getAllLines()) {
                        if (line.isVisible()) {
                            Option<DCell> optionalCell = TableHelper.getCell(line, column);
                            if (optionalCell.some()) {
                                listColumn.add(filler.getContent(optionalCell.get()));
                            } else {
                                listColumn.add(TableFiller.BLANK_MARKER);
                            }
                        }
                    }
                    expected.add(listColumn);
                }
            }
        }
        return expected;
    }

    /**
     * Transform a graphical tree to a table descriptor.
     * 
     * @param tree
     *            tree to transform.
     * @param filler
     *            class responsible for getting the tree content.
     * @return a list of list representing the table.
     */
    protected static List<List<String>> toTableDescriptor(final Tree tree, final TableFiller filler) {
        final List<List<String>> expected = new ArrayList<List<String>>();
        final List<String> header = new ArrayList<String>();
        for (final TreeColumn treeColumn : tree.getColumns()) {
            header.add(filler.getContent(treeColumn));
        }
        expected.add(header);
        for (final TreeItem treeItem : tree.getItems()) {
            TableUIHelper.addLineToDescriptor(filler, expected, treeItem);
        }
        return expected;
    }

    private static void addLineToDescriptor(final TableFiller filler, final List<List<String>> expected, final DLine line) {
        final List<String> listLine = new ArrayList<String>();
        listLine.add(filler.getContent(line));
        DTable parentTable = TableHelper.getTable(line);
        for (DColumn column : parentTable.getColumns()) {
            if (column.isVisible()) {
                Option<DCell> optionalCell = TableHelper.getCell(line, column);
                if (optionalCell.some()) {
                    listLine.add(filler.getContent(optionalCell.get()));
                } else {
                    listLine.add(filler.getContent(line, column));
                }
            }
        }
        expected.add(listLine);
        for (DLine subLine : line.getLines()) {
            TableUIHelper.addLineToDescriptor(filler, expected, subLine);
        }
    }

    private static void addLineToDescriptor(final TableFiller filler, final List<List<String>> expected, final TreeItem treeItem) {
        if (treeItem.getExpanded() || treeItem.getItemCount() == 0) {
            final List<String> listLine = new ArrayList<String>();
            for (int i = 0; i < treeItem.getParent().getColumnCount(); i++) {
                listLine.add(filler.getContent(treeItem, i));
            }
            expected.add(listLine);
            for (final TreeItem subTreeItem : treeItem.getItems()) {
                TableUIHelper.addLineToDescriptor(filler, expected, subTreeItem);
            }
        }
    }

    /**
     * Export the given table to HTML format.
     * 
     * @param table
     *            table to export.
     * @param inverse
     *            true : inverse lines and columns.
     * @return a string with the HTML table.
     */
    public static String toContentHTMl(final DTable table, final boolean inverse) {
        return TableUIHelper.toHTML(TableUIHelper.toTableDescriptor(table, inverse, new TableFiller()));
    }

    /**
     * Export the given tree to HTML format.
     * 
     * @param tree
     *            tree to export.
     * @return a string with the HTML table.
     */
    public static String toContentHTMl(final Tree tree) {
        return TableUIHelper.toHTML(TableUIHelper.toTableDescriptor(tree, new TableFiller()));
    }

    /**
     * Transform a table descriptor to an HTML representation.
     * 
     * @param descriptor
     *            the table descriptor.
     * @return an HTML table.
     */
    public static String toHTML(final List<List<String>> descriptor) {
        int[] columnMaxSize;
        int nbColumnMax = -1;
        /*
         * Let's count the max number of columns
         */
        for (int i = 0; i < descriptor.size(); i++) {
            final Collection<String> line = descriptor.get(i);
            if (line.size() > nbColumnMax) {
                nbColumnMax = line.size();
            }
        }
        /*
         * Now lets init the max size of columns.
         */
        columnMaxSize = new int[nbColumnMax];
        for (int i = 0; i < nbColumnMax; i++) {
            columnMaxSize[i] = -1;
        }

        for (int i = 0; i < descriptor.size(); i++) {
            final List<String> line = descriptor.get(i);
            for (int j = 0; j < line.size(); j++) {
                if (line.get(j).length() > columnMaxSize[j]) {
                    columnMaxSize[j] = line.get(j).length();
                }
            }
        }

        final StringBuffer result = new StringBuffer();
        result.append("<table>\n"); //$NON-NLS-1$
        for (int i = 0; i < descriptor.size(); i++) {
            final List<String> line = descriptor.get(i);
            result.append("<tr>"); //$NON-NLS-1$
            for (String column : line) {
                if (i == 0) {
                    result.append("<th>"); //$NON-NLS-1$
                } else {
                    result.append("<td>"); //$NON-NLS-1$
                }
                final StringBuffer textToAppend = new StringBuffer();
                final int charsToAdd = columnMaxSize[line.indexOf(column)] - column.length();
                for (int c = 0; c < charsToAdd; c++) {
                    textToAppend.append(TableFiller.BLANK_MARKER);
                }
                result.append(column);
                result.append(textToAppend);
                if (i == 0) {
                    result.append("</th>"); //$NON-NLS-1$
                } else {
                    result.append("</td>"); //$NON-NLS-1$
                }
            }
            result.append("</tr>\n"); //$NON-NLS-1$

        }
        result.append("</table>"); //$NON-NLS-1$
        return result.toString();
    }

    /**
     * Add a list of strings to a table descriptor.
     * 
     * @param expected
     *            table descriptor to update.
     * @param strings
     *            data to add.
     */
    public static void addLineToTable(final List<List<String>> expected, final String[] strings) {
        expected.add(Arrays.asList(strings));
    }

    /**
     * Export the given table to HTML format using the named color names as
     * table content.
     * 
     * @param table
     *            table to export.
     * @param inverse
     *            true : inverse lines and columns.
     * @return a string with the HTML table.
     */
    public static String toForegroundStyleHTMl(final DTable table, final boolean inverse) {
        return TableUIHelper.toHTML(TableUIHelper.toTableDescriptor(table, inverse, new TableFiller() {
            @Override
            public String getContent(final DCell cell) {
                return TableUIHelper.getForegroundStyleName(new DCellQuery(cell).getForegroundStyleToApply());
            }
        }));

    }

    /**
     * Export the given table to HTML format using the named color names as
     * table content.
     * 
     * @param tree
     *            tree to export.
     * @return a string with the HTML table.
     */
    public static String toForegroundStyleHTMl(final Tree tree) {
        return TableUIHelper.toHTML(TableUIHelper.toTableDescriptor(tree, new TableFiller() {
            @Override
            public String getContent(final TreeItem treeItem, final int index) {
                String result = BLANK_MARKER;
                final String text = treeItem.getText(index);
                if (index == 0) {
                    result = text;
                } else if (!isCellExist(treeItem, index - 1)) {
                    final Color foregroundColor = treeItem.getForeground(index);
                    final RGBValues newRGB = RGBValues.create(foregroundColor.getRed(), foregroundColor.getGreen(), foregroundColor.getBlue());
                    result = TableUIHelper.getColorName(newRGB);
                }
                return result;
            }

            private boolean isCellExist(TreeItem treeItem, int index) {
                if (treeItem.getData() instanceof DLine) {
                    final DLine dline = (DLine) treeItem.getData();
                    return !TableHelper.getCell(dline, TableHelper.getTable(dline).getColumns().get(index)).some();
                }
                return false;
            }
        }));
    }

    /**
     * Export the given table to HTML format using the named color names as
     * table content.
     * 
     * @param table
     *            table to export.
     * @param inverse
     *            true : inverse lines and columns.
     * @return a string with the HTML table.
     */
    public static String toBackgroundStyleHTMl(final DTable table, final boolean inverse) {
        return TableUIHelper.toHTML(TableUIHelper.toTableDescriptor(table, inverse, new TableFiller() {
            @Override
            public String getContent(final DCell cell) {
                return TableUIHelper.getBackgroundStyleName(new DCellQuery(cell).getBackgroundStyleToApply());
            }

            @Override
            public String getContent(final DLine line, final DColumn column) {
                return TableUIHelper.getBackgroundStyleName(TableHelper.getBackgroundStyleToApply(line, column));
            }
        }));

    }

    /**
     * Export the given table to HTML format using the width of the column as
     * table content.
     * 
     * @param tree
     *            tree to export.
     * @return a string with the HTML table.
     */
    public static String toColumnWidthHTMl(final Tree tree) {
        return TableUIHelper.toHTML(TableUIHelper.toTableDescriptor(tree, new TableFiller() {
            @Override
            public String getContent(final TreeItem treeItem, final int index) {
                String result = BLANK_MARKER;
                // The last column is compute automatically even if the initial
                // width is specified
                if (index != treeItem.getParent().getColumnCount() - 1) {
                    int width = treeItem.getParent().getColumn(index).getWidth();
                    result = Integer.toString(width);
                }
                return result;
            }
        }));
    }

    /**
     * Export the given table to HTML format using the width of the column as
     * table content.
     * 
     * @param table
     *            table to export.
     * @param inverse
     *            true : inverse lines and columns.
     * @return a string with the HTML table.
     */
    public static String toColumnWidthHTMl(final DTable table, final boolean inverse) {
        return TableUIHelper.toHTML(TableUIHelper.toTableDescriptor(table, inverse, new TableFiller() {
            @Override
            public String getContent(final DCell cell) {
                DColumn lastColumn = table.getColumns().get(table.getColumns().size() - 1);
                if (lastColumn.equals(cell.getColumn())) {
                    // the last column is compute automatically even if the
                    // initial width is specified
                    return BLANK_MARKER;
                } else {
                    return Integer.toString(cell.getColumn().getWidth());
                }
            }

            /**
             * {@inheritDoc}
             * 
             * @see org.eclipse.sirius.table.business.api.helper.TableFiller#getContent(org.eclipse.sirius.table.metamodel.table.DLine)
             */
            @Override
            public String getContent(DLine line) {
                return "Undefined";
                // return
                // Integer.toString(TableHelper.getTable(line).getLineHeaderWidth());
            }
        }));

    }

    /**
     * Export the given table to HTML format using the named color names as
     * table content.
     * 
     * @param tree
     *            tree to export.
     * @return a string with the HTML table.
     */
    public static String toBackgroundStyleHTMl(final Tree tree) {
        return TableUIHelper.toHTML(TableUIHelper.toTableDescriptor(tree, new TableFiller() {
            @Override
            public String getContent(final TreeItem treeItem, final int index) {
                String result = treeItem.getText(index);
                if (index != 0) {
                    final Color backgroundColor = treeItem.getBackground(index);
                    final RGBValues newRGB = RGBValues.create(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue());
                    result = TableUIHelper.getColorName(newRGB);
                }
                return result;
            }
        }));
    }

    private static String getForegroundStyleName(final Option<DTableElementStyle> optionalTableElementStyle) {
        if (optionalTableElementStyle.some() && optionalTableElementStyle.get().getForegroundColor() != null) {
            return TableUIHelper.getColorName(optionalTableElementStyle.get().getForegroundColor());
        } else {
            return StringUtil.EMPTY_STRING;
        }
    }

    private static String getBackgroundStyleName(final Option<DTableElementStyle> optionalTableElementStyle) {
        if (optionalTableElementStyle.some() && optionalTableElementStyle.get().getBackgroundColor() != null) {
            return TableUIHelper.getColorName(optionalTableElementStyle.get().getBackgroundColor());
        } else {
            return StringUtil.EMPTY_STRING;
        }
    }

    private static String getColorName(final RGBValues color) {
        final SystemColors closest = VisualBindingManager.getDefault().findClosestStandardColor(color);
        final boolean exactMatch = TableUIHelper.same(color, VisualBindingManager.getDefault().getRGBValuesFor(closest));
        if (exactMatch) {
            return closest.getName();
        } else {
            return "unsupported color : " + color;
        }
    }

    private static boolean same(final RGBValues a, final RGBValues b) {
        return a.getRed() == b.getRed() && a.getGreen() == b.getGreen() && a.getBlue() == b.getBlue();
    }
}

/**
 * Fill a table with values.
 * 
 * @author lredor
 */
class TableFiller {
    protected static final String BLANK_MARKER = "_"; //$NON-NLS-1$

    public String getContent(final DLine line) {
        return line.getLabel();
    }

    public String getContent(final DColumn column) {
        return column.getLabel();
    }

    public String getContent(final DCell cell) {
        String label = new DCellQuery(cell).getExportableLabel();
        if (!StringUtil.isEmpty(label)) {
            return cell.getLabel();
        }
        return BLANK_MARKER;
    }

    public String getContent(final TreeColumn treeColumn) {
        return treeColumn.getText();
    }

    /**
     * @param treeItem
     *            The current item
     * @param index
     *            the column index
     * @return the content stored at the given column index in the receiver
     */
    public String getContent(final TreeItem treeItem, final int index) {
        return treeItem.getText(index);
    }

    public String getContent(final DLine line, final DColumn column) {
        return BLANK_MARKER;
    }
}
