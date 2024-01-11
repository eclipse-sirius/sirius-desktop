/*******************************************************************************
 * Copyright (c) 2007, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.table.model.business.internal.operations;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.LineContainer;
import org.eclipse.sirius.table.model.internal.Messages;

/**
 * Common operations for DColumn subclasses.
 *
 * @author cbrun
 */
public final class DColumnOperations {

    private DColumnOperations() {

    }

    /**
     * Sort the column cells considering their index and return them.
     *
     * @param column
     *            column.
     * @return a sorted set of cells.
     */
    public static Collection<DCell> getOrderedCells(final DColumn column) {
        final Map<DLine, Integer> lineIndices = new HashMap<>();
        DTable table = column.getTable();
        if (table != null) {
            fillIndices(table, lineIndices, 0);
        }
        Comparator<DCell> comparator = (a, b) -> {
            int result = 0;
            DLine lineA = a.getLine();
            DLine lineB = b.getLine();
            if (lineA == null) {
                result = -1;
            } else if (lineB == null) {
                result = 1;
            } else {
                Integer aIndex = lineIndices.get(lineA);
                Integer bIndex = lineIndices.get(lineB);
                if (aIndex == null || bIndex == null) {
                    throw new RuntimeException(Messages.Table_UnexpectedExceptionMessage);
                }
                return aIndex - bIndex;
            }
            return result;
        };
        return column.getCells().stream().sorted(comparator).toList();
    }

    private static int fillIndices(LineContainer container, final Map<DLine, Integer> lineIndices, int i) {
        int j = i;
        for (DLine line : container.getLines()) {
            if (line.getContainer() != null) {
                lineIndices.put(line, j);
            } else {
                lineIndices.put(line, -1);
            }
            j += 1;
            j = fillIndices(line, lineIndices, j);
        }
        return j;
    }
}
