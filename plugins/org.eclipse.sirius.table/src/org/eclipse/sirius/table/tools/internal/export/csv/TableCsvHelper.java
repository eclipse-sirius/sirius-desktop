/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.tools.internal.export.csv;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;

/**
 * An helper.
 * 
 * @author mchauvin
 */
public final class TableCsvHelper {

    /** end of line char. */
    public static final char EOL_CHARACTER = '\n';

    private static short maxOffsetReached;

    private TableCsvHelper() {

    }

    /**
     * Get table descriptors for given table.
     * 
     * @param table
     *            table to transform.
     * @return a list of string lines representing the table.
     */
    public static Iterable<Iterable<String>> getTableDescriptor(final DTable table) {
        /* Create a table filler that provides labels for line, column, cell. */
        final TableFiller filler = new TableFiller(';', '|');
        /*
         * Create the descriptors structure i.e a list of line. Each line is a
         * list of cell value.
         */
        final List<List<String>> descriptors = new ArrayList<>();

        descriptors.add(TableCsvHelper.getHeader(table, filler));

        // Offset that handles sub lines.
        final short offset = 0; // Default position i.e label in the first
                                // column.
        // Reset max offset reached.
        maxOffsetReached = 0;
        // Loop over lines to get values according to headers.
        for (DLine line : table.getLines()) {
            TableCsvHelper.addLineToDescriptor(line, descriptors, filler, offset);
        }
        return (Iterable) descriptors;
    }

    private static List<String> getHeader(final DTable table, final TableFiller filler) {
        /* Header line. */
        final List<String> header = new ArrayList<>();
        /* Add blank cell to shift the header line against the first row labels. */
        header.add(StringUtil.EMPTY_STRING);
        /* Loop over table columns to get header cell values. */
        for (DColumn column : table.getColumns()) {
            // If column is hidden, not add to CSV export
            if (column.isVisible()) {
                header.add(filler.getContent(column));
            }
        }
        return header;
    }

    /**
     * Add line descriptor to given descriptors.
     * 
     * @param filler
     * @param descriptors
     * @param line
     * @param baseOffset
     */
    private static void addLineToDescriptor(final DLine line, final List<List<String>> descriptors, final TableFiller filler, final short baseOffset) {
        // If line is hidden, not added to CSV export
        if (line.isVisible()) {
            final List<String> lineCellValues = new ArrayList<>();
            // Blank character according to the specified offset.
            if (baseOffset > 0) {
                for (int i = 0; i < baseOffset; i++) {
                    lineCellValues.add(StringUtil.EMPTY_STRING);
                }
            }
            // Add the label at the beginning.
            lineCellValues.add(filler.getContent(line));
            // Add blank character according to the max offset reached from the
            // specified offset.
            for (int i = baseOffset; i < maxOffsetReached; i++) {
                lineCellValues.add(StringUtil.EMPTY_STRING);
            }
            for (DColumn column : TableHelper.getTable(line).getColumns()) {
                // If column is hidden, element of column not add to CSV export
                if (column.isVisible()) {
                    Option<DCell> optionalCell = TableHelper.getCell(line, column);
                    if (optionalCell.some()) {
                        lineCellValues.add(filler.getContent(optionalCell.get()));
                    } else {
                        lineCellValues.add(StringUtil.EMPTY_STRING);
                    }
                }
            }
            // Add the line cell values in descriptors.
            descriptors.add(lineCellValues);
            final EList<DLine> subLines = line.getLines();

            short offset = baseOffset;
            // If subLines exist, shift forward the offset.
            if (!subLines.isEmpty()) {
                offset++;
                // Update existing descriptors lines to add a new empty value
                // due to
                // the offset shift if needed.
                // Indeed, a new empty value is only required if depth tree (for
                // column labels) is increased.
                if (offset > maxOffsetReached) {
                    for (List<String> descriptorLine : descriptors) {
                        descriptorLine.add(offset, StringUtil.EMPTY_STRING);
                    }
                    maxOffsetReached = offset;
                }
            }
            for (DLine subLine : subLines) {
                TableCsvHelper.addLineToDescriptor(subLine, descriptors, filler, offset);
            }
            // If subLines exist, shift backward the offset.
            if (!subLines.isEmpty()) {
                offset--;
            }
        }
    }
}
